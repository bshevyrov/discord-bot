package ua.com.company.logic.bumper.task;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.logic.bumper.message.MessageSender;
import ua.com.company.model.Bumper;
import ua.com.company.utils.PropertiesReader;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class ManualBotTimerTask extends TimerTask {
    private static Event event = null;
    private static TextChannel textChannel;
    private User author;

    private static MessageSender messageSender;

    public MessageSender getMessageSenderThread() {
        return messageSender;
    }

    static boolean isMessageSenderInterrupted = false;

    public void setMessageSenderInterrupted(boolean messageSenderInterrupted) {
        isMessageSenderInterrupted = messageSenderInterrupted;
    }

    private static boolean bumped = false;

    public static void setBumped(boolean bumped) {
        ManualBotTimerTask.bumped = bumped;
    }

    public ManualBotTimerTask(Event event) {
        this.event = event;
//        this.author = ((GenericMessageEvent) event).getChannel().retrieveMessageById(
//                ((GenericMessageEvent) event).getMessageId()).complete().getAuthor();
    }

    @Override
    public void run() {
        textChannel = event.getJDA().getTextChannelById(PropertiesReader.getChannel());

        List<Bumper.Entity> bumpers = new ArrayList<>(Bumper.findAll());
        while (!bumped) {

            for (Bumper.Entity bumper : bumpers) {
                User user = event.getJDA().getUserById(bumper.getId());
//                thread = new MessageSender(textChannel, author, this);
                messageSender = new MessageSender(textChannel, user, this);
                messageSender.start();
                try {
                    messageSender.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isMessageSenderInterrupted) {
                    break;
                }
            }
            if (!isMessageSenderInterrupted) {
                new MessageSender(textChannel).sendChannelMessage("All bumpers ignore. Start again!");
            } else {
                bumped = false;
                return;
//                continue;
            }
        }

        bumped = false;
    }


}
