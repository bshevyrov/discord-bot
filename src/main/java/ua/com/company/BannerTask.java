package ua.com.company;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

import static ua.com.company.BannerTaskExecutor.scheduleFor;

public class BannerTask {
    //TODO NEED TO BE 0:00:00 time!!!


    public void collectDataForBanner(ZonedDateTime zonedDateTime, Guild guild) {
//        LocalTime localTime = LocalTime.of(0, 0, 0);
//        ZoneId zoneId = ZoneId.of("Europe/Kiev");
        final ZonedDateTime[] zonedDateTimes = {zonedDateTime};
        ActivityCount activityCount = (ActivityCount) guild.getJDA().getEventManager().getRegisteredListeners().stream()
                .filter(o -> o instanceof ActivityCount)
                .findFirst()
                .get();

        new Thread(() -> {
            while (true) {
                new Thread(() -> {
                    System.out.println(zonedDateTimes[0]);
                    new BannerTask().extracted(activityCount, guild, zonedDateTimes[0]);
                }).run();
                zonedDateTimes[0] = zonedDateTimes[0].plusDays(1L);
            }
        }).start();


    }

    void extracted(ActivityCount activityCount, Guild guild, ZonedDateTime when) {
        MessageCounter messageCounter = new MessageCounter();
        ScheduledFuture<?> job = scheduleFor(() -> {

            List<TextChannel> channels = guild.getTextChannels();
            //remove offtop chanell
            channels.remove(guild.getTextChannelById(1086225112514175011L));
            channels.forEach(
                    messageChannel -> {
                        try {
                            messageCounter.getMessageCountDuring(when, messageChannel).get()
                                    .forEach((user, integer) -> {
                                        if (!activityCount.isBlacklisted(user)) {
                                            activityCount.addMessageCount(user, integer);
                                        }
                                    });
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
            );
//if X date was ignore voice.
            Instant now = Instant.from(ZonedDateTime.now(ZoneId.of("Europe/Kiev")));
            long secondsUntil = ChronoUnit.SECONDS.between(now, when.toInstant());
            if (secondsUntil > 0) {
                VoiceCount voiceCount = (VoiceCount) guild.getJDA().getEventManager().getRegisteredListeners().stream()
                        .filter(o -> o instanceof VoiceCount).findFirst().get();
                voiceCount.getResultMap().forEach(activityCount::addMinutesCount);
                voiceCount.clearResultMap();
            }

            Map<String, String> lst = new HashMap<>();
            User user = activityCount.getList().get(0).keySet().stream().findFirst().get();
            lst.put("avatar", user.getAvatarUrl());
            lst.put("name", guild.getMember(user).getNickname() == null ? user.getName() : guild.getMember(user).getNickname());
            lst.put("status", getStatus(guild, user));
            lst.put("minutes", String.valueOf(activityCount.getList().get(0).values().stream().findFirst().get().getMinutes()));
            lst.put("message", String.valueOf(activityCount.getList().get(0).values().stream().findFirst().get().getMessages()));

            activityCount.clear();

//            ActivityCount.Count count = (ActivityCount.Count) map.get(0).get(1);
//            System.out.println(String.valueOf(guild.getMember(user).getActivities()));

            BannerWriter bannerWriter = new BannerWriter();
            try {
                guild.getManager().setBanner(Icon.from(
                                bannerWriter.makeBackground(lst.get("avatar"),
                                        lst.get("name"),
                                        lst.get("status"),
                                        lst.get("message"),
                                        lst.get("minutes")
                                )
                        )
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, when);

        //wait until thread finish
        try {
            job.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private String getStatus(Guild guild, User user) {
        AtomicReference<String> status = new AtomicReference<>("");

        guild.retrieveMember(user).complete().getActivities().forEach((activity -> {
            if (activity.getType().equals(Activity.ActivityType.CUSTOM_STATUS)) {
                status.set(activity.getName());
            }
        }));
        return status.get();
    }
}
