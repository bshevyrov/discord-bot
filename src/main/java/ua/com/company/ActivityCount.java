package ua.com.company;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ActivityCount implements EventListener {

    public ActivityCount() {
    }

    private Map<User, ActivityCount.Count> participant = new HashMap<>();
    private List<User> blacklist = new LinkedList<>();


    public void clear() {
        participant.clear();
    }

    public void addMessageCount(User user, int messageCount) {
        if(isBlacklisted(user)){ return;}
        Count count = participant.getOrDefault(user, new Count());
        count.addMessages(messageCount);
        participant.put(user, count);

    }

    public void addMinutesCount(User user, int minuteCount) {
        if(isBlacklisted(user)){ return;}
        Count count = participant.getOrDefault(user, new Count());
        count.addMinutes(minuteCount);
        participant.put(user, count);
    }

    public Map<User, Count> getCurrentStateMap(Guild guild) {
        List<TextChannel> channels = new LinkedList<>(guild.getTextChannels());
        Map<User,ActivityCount.Count> rsl= new HashMap<>();
        MessageCounter messageCounter = new MessageCounter();
        //remove offtop chanell
        channels.remove(guild.getTextChannelById(1086225112514175011L));
        channels.forEach(
                messageChannel -> {
                    try {
                        messageCounter.getMessageCountDuring(ZonedDateTime.now(ZoneId.of("Europe/Kiev")), messageChannel).get()
                                .forEach((user, integer) -> {
                                    if (!this.isBlacklisted(user)) {
                                        this.addMessageCount(user, integer);
                                    }
                                });
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
        VoiceCount voiceCount = (VoiceCount) guild.getJDA().getEventManager().getRegisteredListeners().stream()
                .filter(o -> o instanceof VoiceCount).findFirst().get();
        voiceCount.getResultMap().forEach(this::addMinutesCount);
rsl.putAll(participant);
participant.clear();
        return rsl;
    }

    public List<Map<User, ActivityCount.Count>> getList(Guild guild) {
        List<Map<User, ActivityCount.Count>> rsl = new ArrayList<>();
        getCurrentStateMap(guild).forEach((key, value) -> rsl.add(new HashMap<>() {{
            put(key, value);
        }}));
        return rsl;
    }

    /**
     *
     * @return Copy of blacklist.
     */
    public List<User> getBlacklist() {
        return new ArrayList<>(blacklist);
    }

    public void removeFromBlacklist(User user) {
        blacklist.remove(user);
    }

    public void addToBlacklist(User user) {
        blacklist.add(user);
    }

    public boolean isBlacklisted(User user){
      return   blacklist.contains(user);
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
    }

    public class Count implements Comparable<Count> {
        public Count() {
        }

        private int minutes;
        private int messages;

        //formula minute =4 message
        public int getTotalScore() {
            return getMinutes() + getMessages();
        }

        public int getMinutes() {
            return minutes;
        }

        public int getMessages() {
            return messages;
        }

        private void addMinutes(int minutes) {
            this.minutes += minutes;
        }

        private void addMessages(int messages) {
            this.messages += messages;
        }


        @Override
        public int compareTo(@NotNull Count o) {
//            return this.getTotalScore() - o.getTotalScore();

            //reverserd first maxt last mon desc
            return o.getTotalScore() - this.getTotalScore();
        }
    }
}


