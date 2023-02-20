package ua.com.company.logic.bumper.bot.impl;

import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.logic.bumper.bot.IBot;

public class SDCMonitoringBot implements IBot {
    @Override
    public String getTag() {
        return "SD.C Monitoring#9896";
    }

    @Override
    public void execute(GenericMessageEvent event) {

    }
}
