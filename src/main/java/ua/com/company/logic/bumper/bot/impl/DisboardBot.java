package ua.com.company.logic.bumper.bot.impl;

import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.logic.bumper.bot.IBot;

public class DisboardBot implements IBot {
    @Override
    public String getTag() {
        return "DISBOARD#2760";
    }

    @Override
    public void execute(GenericMessageEvent event) {

    }
}
