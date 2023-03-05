package ua.com.company.logic.bumper.bot;

import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;

public interface IBot {
    String getTag();
    void execute(GenericMessageEvent event);
    String getSuccessMessage();
}
