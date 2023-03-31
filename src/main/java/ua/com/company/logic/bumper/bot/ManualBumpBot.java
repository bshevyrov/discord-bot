package ua.com.company.logic.bumper.bot;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.logic.bumper.message.MessageSender;
import ua.com.company.logic.bumper.task.ManualBotTimerTask;
import ua.com.company.logic.bumper.task.SingleScheduleExecute;
import ua.com.company.model.Bumper;
import ua.com.company.utils.BumperConstants;
import ua.com.company.utils.PropertiesReader;

public abstract class ManualBumpBot implements IBot {
    //??STATIC??
    private static SingleScheduleExecute singleScheduleExecute;

    @Override
    public abstract String getTag();

    @Override
    public void execute(GenericMessageEvent event) {
//        ManualBotTimerTask timerTask = new ManualBotTimerTask(event);

        TextChannel channel = event.getGuild().getTextChannelById(PropertiesReader.getChannel());
        channel.retrieveMessageById(event.getMessageId())
                .queue(message -> {
                    if (message.getEmbeds().get(0).getDescription().contains(getSuccessMessage())) {
                        User user = message.getInteraction().getUser();
                        Bumper.remove(user);
                        Bumper.add(user);

                        if (singleScheduleExecute == null) {
                            singleScheduleExecute = new SingleScheduleExecute(new ManualBotTimerTask(event), BumperConstants.PAUSE_BETWEEN_MANUAL_NEW_TASK);
                            singleScheduleExecute.startSchedule();
                        } else {
                            //this equivalent to executor.shutdownNow(); because close all
//                                NewCircleTimerTask.setBumped(true);
//                                NewCircleTimerTask.getThread().interrupt();
                            System.out.println("message id = "+message.getId());
                            new MessageSender(channel, message.getInteraction().getUser()).sendBumped();
                            ((ManualBotTimerTask) singleScheduleExecute.getTimerTask()).getMessageSenderThread().stop();

                            singleScheduleExecute.getExecutor().shutdownNow();
                            singleScheduleExecute.startSchedule();
                        }

                    }


                });

    }

}
