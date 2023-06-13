package ua.com.company;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BannerTaskExecutor {
    // Somewhere before the method, as field for example
// Use other pool sizes if desired
    static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static ScheduledFuture<?> scheduleFor(Runnable runnable, ZonedDateTime when) {
//        Instant now = Instant.now();

        Instant now = Instant.from(ZonedDateTime.now(ZoneId.of("Europe/Kiev")));
        // Use a different resolution if desired
        long secondsUntil = ChronoUnit.SECONDS.between(now, when.toInstant());
        System.out.println(secondsUntil);
        return scheduler.schedule(runnable, secondsUntil, TimeUnit.of(ChronoUnit.SECONDS));
    }
    public static void endSchedule(){
        scheduler.shutdown();
    }




}
