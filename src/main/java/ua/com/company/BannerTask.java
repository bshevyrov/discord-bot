package ua.com.company;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

import static ua.com.company.BannerTaskExecutor.scheduleFor;

public class BannerTask {
    //TODO NEED TO BE 0:00:00 time!!!

    void extracted(ActivityCount activityCount, VoiceCount voiceCount, Guild guild, ZonedDateTime when) {


//        ZonedDateTime when = ZonedDateTime.of(LocalDate.now(ZoneId.of("Europe/Kiev")),
//                localTime, ZoneId.of("Europe/Kiev"));
        MessageCounter messageCounter = new MessageCounter();
        ScheduledFuture<?> job = scheduleFor(() -> {
            List<TextChannel> channels = guild.getTextChannels();
            //remove offtop chanell
            // channels.remove(jda.getTextChannelById(1086225112514175011L));
            LocalDate date = LocalDate.now(ZoneId.of("Europe/Kiev"));
            channels.forEach(
                    messageChannel -> {
                        try {
                            messageCounter.getMessageCountDuring(when, messageChannel).get()
                                    .forEach(activityCount::addMessageCount);
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
            );
            Map<String, String> lst = new HashMap<>();
            voiceCount.stopStart();
            User user = activityCount.getList().get(0).keySet().stream().findFirst().get();
            lst.put("avatar", user.getAvatarUrl());
            lst.put("name", guild.getMember(user).getNickname() == null ? user.getName() : guild.getMember(user).getNickname());
            lst.put("status", getStatus(guild, user));
            lst.put("minutes", String.valueOf(activityCount.getList().get(0).values().stream().findFirst().get().getMinutes()));
            lst.put("message", String.valueOf(activityCount.getList().get(0).values().stream().findFirst().get().getMessages()));


//            ActivityCount.Count count = (ActivityCount.Count) map.get(0).get(1);
//            System.out.println(String.valueOf(guild.getMember(user).getActivities()));

            BannerWriter bannerWriter = new BannerWriter();
            try {
                bannerWriter.method(lst.get("avatar"),
                        lst.get("name"),
                        lst.get("status"),
                        lst.get("message"),
                        lst.get("minutes")

                );
            } catch (IOException e) {
                e.printStackTrace();
            }

        }, when);

        //wait until thread finish
        try {
            job.get();
//            endSchedule();
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
