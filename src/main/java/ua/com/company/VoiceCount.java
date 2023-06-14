package ua.com.company;


import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class VoiceCount extends ListenerAdapter {


    public VoiceCount() {

    }

    //TODO replace map with write to file
  private Map<User, VoiceDuration> participant = new HashMap<>();
  private   Map<User, Integer> rsl = new HashMap<>();

    @Override
    public void onGuildVoiceUpdate(@NotNull GuildVoiceUpdateEvent event) {
        VoiceDuration voiceDuration = new VoiceDuration();
        if (event.getChannelJoined() != null) {
            User user = event.getEntity().getUser();
            voiceDuration.setStart();
            participant.put(user, voiceDuration);
            // call methods that need to know on any join
        }
        if (event.getChannelLeft() != null) {
            User user = event.getEntity().getUser();
            if (participant.get(user) == null) {
                System.out.println("ERROR! NO START VOICE");
            } else {
                user = event.getEntity().getUser();
                endUserVoice(user);
                participant.remove(user);
            }

            // call methods that need to know on any leave
        }
       /* if (joinedChannel != null && leftChannel != null) {
            // call methods that need to know on any move
        }*/

    }

    public Map<User,Integer> getResultMap() {
// participant.values().forEach(voiceDuration -> {
//           //always not null??
//            if (voiceDuration != null) {
//                voiceDuration.setFinish();
//                voiceDuration.setStart();
//            }

            participant.forEach((user, voiceDuration) -> {
                endUserVoice(user);
                participant.get(user).setStart();
            });


       // });
        Map<User,Integer> tmp = Map.copyOf(rsl);
        //memory leak? size really 0??
        rsl.clear();
        return tmp;
    }

    private void endUserVoice(User user) {
        if (rsl.containsKey(user)) {
            int tmp = rsl.get(user);
            rsl.put(user, tmp + participant.get(user).setFinish());
        } else {
            rsl.put(user, participant.get(user).setFinish());
        }
    }

    private static class VoiceDuration {
        private Instant start;


        public VoiceDuration() {
        }

        //todo check time to zone
        private void setStart() {

            this.start = Instant.now();
        }

        private int setFinish() {

            int rsl = Duration.between(start, Instant.now()).toMinutes() < 1 ? 0 : (int) Duration.between(start, Instant.now()).toMinutes();
            start = null;
            return rsl;
        }
    }
}
