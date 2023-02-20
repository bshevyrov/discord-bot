package ua.com.company.logic.bumper.bot.impl;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.Bumper;
import ua.com.company.exception.BumperNotFound;
import ua.com.company.logic.bumper.bot.IBot;
import ua.com.company.logic.bumper.message.NewCircleTimerTask;

import java.time.ZoneId;

public class ServerMonitoringBot implements IBot {
   private Bumper.Entity bumper;
    @Override
    public String getTag() {
        return "Server Monitoring#8312";
    }

    @Override
    public void execute(GenericMessageEvent event) {
        MessageChannel channel = event.getGuild().getTextChannelById(CHANNEL_ID);
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
                            bumper = Bumper.findById(getMemberFromEmbeddedDescription(message));
                            bumper.
                                    setBumpTime(message.getTimeCreated()
                                            .atZoneSameInstant(ZoneId.of("Europe/Kiev")));
                            NewCircleTimerTask.setMessageSenderInterrupted(true);
                        } catch (BumperNotFound e) {
//       log.error(e.getMessage());
                        }
                        if (executor == null) {
                            startSchedule(event);
                        } else {
                            //this equivalent to executor.shutdownNow(); because close all
//                                NewCircleTimerTask.setBumped(true);
//                                NewCircleTimerTask.getThread().interrupt();
                            tasktask.sendBumped(bumper);
                            NewCircleTimerTask.getMessageSenderThread().stop();

                            executor.shutdownNow();
                            startSchedule(event);
                        }

                });

    }
    private String getMemberFromEmbeddedDescription(Message message) {
        String embeddedMessageDescription = message.getEmbeds().get(0).getDescription();
        String memberId = embeddedMessageDescription.substring(embeddedMessageDescription.indexOf('@') + 1, embeddedMessageDescription.indexOf('>'));
        return memberId;
    }
}
