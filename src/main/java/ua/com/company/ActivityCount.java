package ua.com.company;

import net.dv8tion.jda.api.entities.User;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ActivityCount {
    public ActivityCount() {
    }

    private Map<User, ActivityCount.Count> participant = new HashMap<>();

    public void clearParticipant() {
        participant.clear();
    }

    public void addMessageCount(User user, int messageCount) {
        if (participant.get(user) == null) {
            Count count = new Count();
            count.addMessages(messageCount);
            participant.put(user, count);
        } else {
            Count count = participant.get(user);
            count.addMessages(messageCount);
            participant.put(user, count);
        }
    }

    public void addMinutesCount(User user, int minuteCount) {
        if (participant.get(user) == null) {
            Count count = new Count();
            count.addMinutes(minuteCount);
            participant.put(user, count);
        } else {
            Count count = participant.get(user);
            count.addMinutes(minuteCount);
            participant.put(user, count);
        }
    }

    public Map<User, Count> getMap() {
        return participant.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(new ComparableComparator<>()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public List<Map<User, ActivityCount.Count>> getList() {
        List<Map<User, ActivityCount.Count>> rsl = new ArrayList<>();
        getMap().forEach((key, value) -> rsl.add(new HashMap<>() {{
            put(key, value);
        }}));
        return rsl;
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

