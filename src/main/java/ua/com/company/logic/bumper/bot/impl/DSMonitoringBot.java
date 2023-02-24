package ua.com.company.logic.bumper.bot.impl;

import ua.com.company.logic.bumper.bot.AutoBumpBot;

public class DSMonitoringBot extends AutoBumpBot {
    //3 hour
    @Override
//    public String getTag() {
//        return "DSMonitoring#0015";
//    }
    public String getTag() {
        return "Журбака#8713";
    }

    @Override
    public String getSlashCommand() {
        return "/like";
    }

/*    @Override
    public void execute(GenericMessageEvent event) {

    *//*    MessageChannel channel = event.getGuild().getTextChannelById(CHANNEL_ID);
//           channel.retrieveMessageById("1074671286938251304")
        channel.retrieveMessageById(event.getMessageId())
                .queue(message -> {

                    try {
//                        bumper = Bumper.findById(getMemberFromEmbeddedDescription(message));
                        bumper = Bumper.findById(message.getAuthor().getId());
                        bumper.
                                setBumpTime(message.getTimeCreated()
                                        .atZoneSameInstant(ZoneId.of("Europe/Kiev")));
                        ManualBotTimerTask.setMessageSenderInterrupted(true);
                    } catch (BumperNotFound e) {
//       log.error(e.getMessage());
                    }
                    if (TimerScheduleExecute.getExecutor() == null) {
                        TimerScheduleExecute.startSchedule(event);
                    } else {
                        //this equivalent to executor.shutdownNow(); because close all
//                                NewCircleTimerTask.setBumped(true);
//                                NewCircleTimerTask.getThread().interrupt();
                        TimerScheduleExecute.getTimerTask().sendBumped(bumper);
                        ManualBotTimerTask.getMessageSenderThread().stop();

                        TimerScheduleExecute.getExecutor().shutdownNow();
                        TimerScheduleExecute.startSchedule(event);
                    }

                });

        Ma
*//*

    }*/
}

//    public static void startSchedule(Event event) {
//        timerTask = new NewCircleTimerTask(event);
//        executor = Executors.newSingleThreadScheduledExecutor();
//        executor.scheduleWithFixedDelay(timerTask, BumperConstants.PAUSE_BETWEEN_NEW_TASK,
//                BumperConstants.PAUSE_BETWEEN_NEW_TASK, TimeUnit.MINUTES);
//    }