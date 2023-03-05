package ua.com.company.logic.bumper.task;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerScheduleExecute {
    private  ScheduledExecutorService executor;
    private final TimerTask timerTask;
    private final long delay;

    public  ScheduledExecutorService getExecutor() {
        return executor;
    }

    public  TimerTask getTimerTask() {
        return timerTask;
    }

    public void startSchedule() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(timerTask, delay, delay, TimeUnit.MINUTES);
    }
    public void startSchedule(boolean firstDelay) {
        executor = Executors.newSingleThreadScheduledExecutor();
        if (!firstDelay) {
            executor.scheduleWithFixedDelay(timerTask, 0, delay, TimeUnit.MINUTES);
        } else {
            startSchedule();
        }
    }

    public TimerScheduleExecute(TimerTask timerTask, Long delayInMin) {
        this.timerTask = timerTask;
        this.delay = delayInMin;
    }
}
