package ua.com.company.logic.bumper.bot.impl;

import ua.com.company.logic.bumper.bot.ManualBumpBot;

public class SDCMonitoringBot extends ManualBumpBot {
  /*  private final String CHANNEL_ID = PropertiesReader.getChannel(); //HARD CODED CHANEL IS
    private Bumper.Entity bumper;*/

//    @Override
    public String getTag() {
        return "Misato-San#6564";
//    }
/*
    public String getTag() {
        return "SD.C Monitoring#9896";
//    }
*/

    /*@Override
    public void execute(GenericMessageEvent event) {

        MessageChannel channel = event.getGuild().getTextChannelById(CHANNEL_ID);
//           channel.retrieveMessageById("1074671286938251304")
        channel.retrieveMessageById(event.getMessageId())
                .queue(message -> {
*//*
                        if (isNewBumper(message)) {
                            event.getGuild().retrieveMemberById(getMemberFromEmbeddedDescription(message))
                                    .queue(Bumper::add);

                             *//**//*   Bumper.add(event.getGuild()
                                        .getMemberById(
                                                getMemberFromEmbeddedDescription(message)));*//**//*
                        }*//*
                    try {
//                        bumper = Bumper.findById(getMemberFromEmbeddedDescription(message));
                        bumper = Bumper.findById(message.getAuthor().getId());
                        bumper.
                                setBumpTime(message.getTimeCreated()
                                        .atZoneSameInstant(ZoneId.of("Europe/Kiev")));
                        NewCircleTimerTask.setMessageSenderInterrupted(true);
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
                        NewCircleTimerTask.getMessageSenderThread().stop();

                        TimerScheduleExecute.getExecutor().shutdownNow();
                        TimerScheduleExecute.startSchedule(event);
                    }

                });

    }

    private String getMemberFromEmbeddedDescription(Message message) {
        String embeddedMessageDescription = message.getEmbeds().get(0).getDescription();
        return embeddedMessageDescription.substring(embeddedMessageDescription.indexOf('@') + 1, embeddedMessageDescription.indexOf('>'));
    }*/

    }

//    @Override
//    public String getSuccessMessage() {
//        return "Успешный";
//    }
    @Override
    public String getSuccessMessage() {
        return "pioneer";
    }
}
