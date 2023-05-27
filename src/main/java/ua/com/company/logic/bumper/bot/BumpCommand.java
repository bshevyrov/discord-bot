package ua.com.company.logic.bumper.bot;

import java.time.Duration;

public interface BumpCommand {

    String getCommand();
    Duration getDelay();
}
