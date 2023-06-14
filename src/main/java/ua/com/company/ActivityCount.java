package ua.com.company;

import net.dv8tion.jda.api.entities.User;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ActivityCount implements EventListener{
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


