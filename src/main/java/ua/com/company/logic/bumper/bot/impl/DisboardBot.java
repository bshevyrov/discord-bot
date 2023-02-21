package ua.com.company.logic.bumper.bot.impl;

import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.logic.bumper.bot.IBot;
import ua.com.company.logic.bumper.bot.ManualBumpBot;

public class DisboardBot extends ManualBumpBot {
    @Override
    public String getTag() {
        return "DISBOARD#2760";
    }

}
