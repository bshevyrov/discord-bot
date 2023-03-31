package ua.com.company.logic.bumper.bot.impl;

import ua.com.company.logic.bumper.bot.ManualBumpBot;

public class SDCMonitoringBot extends ManualBumpBot {

    @Override
//    public String getTag() {
//        return "Misato-San#6564";
//    }
    public String getTag() {
        return "SD.C Monitoring#9896";
    }

    @Override
    public String getSuccessMessage() {
        return "Успешный";
    }
//    @Override
//    public String getSuccessMessage() {
//        return "Asuka";
//    }
}
