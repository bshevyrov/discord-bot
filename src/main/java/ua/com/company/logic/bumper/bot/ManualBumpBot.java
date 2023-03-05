package ua.com.company.logic.bumper.bot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.model.Bumper;
import ua.com.company.exception.BumperNotFound;
import ua.com.company.logic.bumper.message.MessageSender;
import ua.com.company.logic.bumper.task.ManualBotTimerTask;
import ua.com.company.logic.bumper.task.SingleScheduleExecute;
import ua.com.company.utils.BumperConstants;
import ua.com.company.utils.PropertiesReader;

import java.time.ZoneId;

public abstract class ManualBumpBot implements IBot {
    private Bumper.Entity bumper;
    private static SingleScheduleExecute singleScheduleExecute;
    private MessageSender messageSender;

    @Override
    public abstract String getTag();

    @Override
    public void execute(GenericMessageEvent event) {
        if (singleScheduleExecute == null) {
            singleScheduleExecute = new SingleScheduleExecute(new ManualBotTimerTask(event), BumperConstants.PAUSE_BETWEEN_MANUAL_NEW_TASK);
        }
        TextChannel channel = event.getGuild().getTextChannelById(PropertiesReader.getChannel());
//           channel.retrieveMessageById("1074671286938251304")
        channel.retrieveMessageById(event.getMessageId())
                .queue(message -> {
/*
                        if (isNewBumper(message)) {
                            event.getGuild().retrieveMemberById(getMemberFromEmbeddedDescription(message))
                                    .queue(Bumper::add);

                             *//*   Bumper.add(event.getGuild()
                                        .getMemberById(
                                                getMemberFromEmbeddedDescription(message)));*//*
                        }*/
                    try {
//                        bumper = Bumper.findById(getMemberFromEmbeddedDescription(message));
                        bumper = Bumper.findById(message.getAuthor().getId());
                        bumper.
                                setBumpTime(message.getTimeCreated()
                                        .atZoneSameInstant(ZoneId.of("Europe/Kiev")));
                        ((ManualBotTimerTask) singleScheduleExecute.getTimerTask()).setMessageSenderInterrupted(true);
                    } catch (BumperNotFound e) {
//       log.error(e.getMessage());
                    }
                    if (singleScheduleExecute.getExecutor() == null) {
                        singleScheduleExecute.startSchedule();
                    } else {
                        //this equivalent to executor.shutdownNow(); because close all
//                                NewCircleTimerTask.setBumped(true);
//                                NewCircleTimerTask.getThread().interrupt();
                        new MessageSender(channel,message.getAuthor()).sendBumped();
                        ((ManualBotTimerTask) singleScheduleExecute.getTimerTask()).getMessageSenderThread().stop();

                        singleScheduleExecute.getExecutor().shutdownNow();
                        singleScheduleExecute.startSchedule();
                    }

                });

    }

    private String getMemberFromEmbeddedDescription(Message message) {
        String embeddedMessageDescription = message.getEmbeds().get(0).getDescription();
        return embeddedMessageDescription.substring(embeddedMessageDescription.indexOf('@') + 1, embeddedMessageDescription.indexOf('>'));
    }
}
