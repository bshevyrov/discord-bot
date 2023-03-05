//package ua.com.company.logic.bumper.bot;
//
//import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
//import net.dv8tion.jda.api.events.message.GenericMessageEvent;
//import ua.com.company.logic.bumper.task.AutoBotTimerTask;
//import ua.com.company.logic.bumper.task.ManualBotTimerTask;
//import ua.com.company.logic.bumper.task.TimerScheduleExecute;
//import ua.com.company.utils.BumperConstants;
//import ua.com.company.utils.PropertiesReader;
//
//public abstract class AutoBumpBot implements IBot {
//    private static TimerScheduleExecute timerScheduleExecute;
//
//    @Override
//    public abstract String getTag();
//
//    public abstract String getSlashCommand();
//
//    @Override
//    public void execute(GenericMessageEvent event) {
//
//        if (timerScheduleExecute == null) {
//            timerScheduleExecute = new TimerScheduleExecute(new AutoBotTimerTask(event, getSlashCommand()),
//                    BumperConstants.PAUSE_BETWEEN_AUTO_NEW_TASK);
//        }
//        MessageChannel channel = event.getGuild().getTextChannelById(PropertiesReader.getChannel());
////           channel.retrieveMessageById("1074671286938251304")
//        channel.retrieveMessageById(event.getMessageId())
//                .queue(message -> {
//
//                    if (timerScheduleExecute.getExecutor() == null) {
//                        timerScheduleExecute.startSchedule();
//                    } else {
//
//                        ((ManualBotTimerTask) timerScheduleExecute.getTimerTask()).getMessageSenderThread().stop();
//
//                        timerScheduleExecute.getExecutor().shutdownNow();
//                        timerScheduleExecute.startSchedule();
//                    }
//
//                });
//    }
//}
