package ua.com.company;


import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.time.*;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.util.HashMap;
import java.util.Map;

public class VoiceCount extends ListenerAdapter {
    private ActivityCount activityCount;



    public VoiceCount(ActivityCount activityCount) {
        this.activityCount = activityCount;
    }

    //TODO replace map with write to file
    //TODO stop counting at night
    Map<User, VoiceDuration> participant = new HashMap<>();

    @Override
    public void onGuildVoiceUpdate(@NotNull GuildVoiceUpdateEvent event) {
        VoiceDuration voiceDuration = new VoiceDuration();
        if (event.getChannelJoined() != null) {
            User user = event.getEntity().getUser();
            voiceDuration.setStart();
            participant.put(user, voiceDuration);;
            // call methods that need to know on any join
        }
        if (event.getChannelLeft() != null) {
            User user = event.getEntity().getUser();
            if (participant.get(user) == null) {
                System.out.println("ERROR! NO START VOICE");
            } else {
                user = event.getEntity().getUser();
                activityCount.addMinutesCount(user, participant.get(user).setFinish());
                participant.remove(user);
            }

            // call methods that need to know on any leave
        }
       /* if (joinedChannel != null && leftChannel != null) {
            // call methods that need to know on any move
        }*/

    }

    public void stopStart() {
        participant.values().forEach(voiceDuration -> {
            if (voiceDuration != null) {
                voiceDuration.setFinish();
                voiceDuration.setStart();
            }
        });
    }

    private static class VoiceDuration {
        private Instant start;


        public VoiceDuration() {
        }

        //todo check time to zone
        private void setStart() {

            this.start =Instant.now();
        }

        private int setFinish() {

            int rsl = Duration.between(start,Instant.now()).toMinutes()<1?0: (int) Duration.between(start, Instant.now()).toMinutes();
            start = null;
            return rsl;
        }
    }
}
