package ua.com.company;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.utils.TimeUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MessageCounter {
    public MessageCounter() {
    }

    public   CompletableFuture<  ConcurrentMap<User, Integer> > getMessageCountDuring(ZonedDateTime localDate, MessageChannel channel) {
        // Parse the date from the input
//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDateTime date = localDate.atStartOfDay();

//        Instant instant = Instant.now(); //can be LocalDateTime
//        ZoneId systemZone = ZoneId.of("Europe/Kiev"); // my timezone
//        ZoneOffset currentOffsetForMyZone = systemZone.getRules().getOffset(instant);


        // Get the boundary of the time as instant
        System.out.println("start day"+  localDate.plusSeconds(1L).minusDays(1L));
        Instant startOfDayDT = localDate.plusSeconds(1L).minusDays(1L).toInstant();
//        Instant startOfDayDT = Instant.ofEpochSecond(date.toEpochSecond(currentOffsetForMyZone));
//        Instant endOfDayDT = Instant.ofEpochSecond(date.plusDays(1).toEpochSecond(currentOffsetForMyZone));
        System.out.println("end day"+  localDate.minusSeconds(1L));

        Instant endOfDayDT = localDate.minusSeconds(1L).toInstant();
        // Create pseudo-snowflake for the message to start from
        long endOfDay = TimeUtil.getDiscordTimestamp(endOfDayDT.toEpochMilli());

        ConcurrentMap<User, Integer> cMap = new ConcurrentHashMap<>();
        CompletableFuture<  ConcurrentMap<User, Integer> > future = new CompletableFuture<>();
        channel.getIterableHistory()
                .skipTo(endOfDay) // start iterating backwards from the end of the day
                .forEachAsync(message -> {
                    // Check message creation time is in range of day
                    Instant time = message.getTimeCreated().toInstant();
                    if (time.isBefore(endOfDayDT) && time.isAfter(startOfDayDT)) {
                        if (!message.getAuthor().isBot()) {
                            if (cMap.get(message.getAuthor()) != null) {
                                cMap.computeIfPresent(message.getAuthor(), (user, integer) -> integer + 1);
                            } else {
                                cMap.put(message.getAuthor(), 1);
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
