package ua.com.company;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.utils.TimeUtil;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MessageCounter {
    public MessageCounter() {
    }

    public CompletableFuture<ConcurrentMap<User, Integer>> getMessageCountDuring(ZonedDateTime localDate, MessageChannel channel) {
        // Get the boundary of the time as instant
        Instant startOfDayDT = localDate.plusSeconds(1L).minusDays(1L).toInstant();
        Instant endOfDayDT = localDate.minusSeconds(1L).toInstant();
        // Create pseudo-snowflake for the message to start from
        long endOfDay = TimeUtil.getDiscordTimestamp(endOfDayDT.toEpochMilli());

        ConcurrentMap<User, Integer> cMap = new ConcurrentHashMap<>();
        CompletableFuture<ConcurrentMap<User, Integer>> future = new CompletableFuture<>();
        channel.getIterableHistory()
                .skipTo(endOfDay) // start iterating backwards from the end of the day
                .forEachAsync(message -> {
                    // Check message creation time is in range of day
                    Instant time = message.getTimeCreated().toInstant();
                    if (time.isBefore(endOfDayDT) && time.isAfter(startOfDayDT)) {
                        if (!message.getAuthor().isBot()) {
                            if (cMap.get(message.getAuthor()) == null) {
                                cMap.put(message.getAuthor(), 1);
                            } else {
                                Integer integer = cMap.get(message.getAuthor());
                                cMap.put(message.getAuthor(), integer+1);
                            }
                        }
                    }
                    // Stop iterating when this is false
                    return time.isAfter(startOfDayDT);
                })
                .thenRun(() -> future.complete(cMap));
        return future;
    }
}