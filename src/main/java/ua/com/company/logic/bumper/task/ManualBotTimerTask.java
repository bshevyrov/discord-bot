package ua.com.company.logic.bumper.task;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.entity.Bumper;
import ua.com.company.logic.bumper.message.MessageSender;
import ua.com.company.utils.PropertiesReader;

import java.util.*;

public class ManualBotTimerTask extends TimerTask {
    private static Event event = null;
    private static TextChannel textChannel;
    private User author;

    private static MessageSender thread;

    public  MessageSender getMessageSenderThread() {
        return thread;
    }

    static boolean isMessageSenderInterrupted = false;

    public  void setMessageSenderInterrupted(boolean messageSenderInterrupted) {
        isMessageSenderInterrupted = messageSenderInterrupted;
    }
    private static boolean bumped = false;

    public static void setBumped(boolean bumped) {
        ManualBotTimerTask.bumped = bumped;
    }

    public ManualBotTimerTask(Event event) {
        this.event = event;
       this.author = ((GenericMessageEvent)event).getChannel().retrieveMessageById(
                ((GenericMessageEvent)event).getMessageId()).complete().getAuthor();
    }

    @Override
    public void run() {
        textChannel = event.getJDA().getTextChannelById(PropertiesReader.getChannel());

        List<Bumper.Entity> bumpers = new ArrayList<>(Bumper.findAll());
        while (!bumped) {

            for (Bumper.Entity bumper : bumpers) {
                thread = new MessageSender(textChannel, author, this);
                thread.start();
                try {
                    thread.join();
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
                bumped=false;
                return;
//                continue;
            }
        }

        bumped = false;
    }
/*

    */
/**
     * This Method message send Private Message
     *
     * @param bumper  bumper Entity to whom send message
     * @param message message String text that send
     *//*

    static void sendPrivateMessage(Bumper.Entity bumper, TextChannel context, String message) {
        // Send message and delete 30 seconds later
        event.getJDA().retrieveUserById(bumper.getId()).complete()
                .openPrivateChannel() // RestAction<PrivateChannel>
                .flatMap(channel -> channel.sendMessage(message)) // RestAction<Message>
                .delay(BumperConstants.DELAY_BEFORE_DELETE_MESSAGE, TimeUnit.SECONDS) // RestAction<Message> with delayed response
//                .delay(Duration.ofSeconds(30)) // RestAction<Message> with delayed response
//                .delay(5, TimeUnit.MINUTES) // RestAction<Message> with delayed response
                .flatMap(Message::delete)
                .queue(null, new ErrorHandler()
                        .ignore(ErrorResponse.UNKNOWN_MESSAGE) // if delete fails that's fine
                        .handle(
                                ErrorResponse.CANNOT_SEND_TO_USER,  // Fallback handling for blocked messages
                                (e) -> context.sendMessage("Cannot send direct message to bumper " + bumper.getUsername())
                                        .queue()));
        //https://stackoverflow.com/questions/61292993/whats-the-best-way-to-handle-not-being-able-to-send-a-private-message-to-a-user
        //Error handling
    }

    */
/**
     * This method send message to channel
     *
     * @param context TextChannel where send message
     * @param message String message
     *//*

    static void sendChannelMessage(TextChannel context, String message) {
        context.sendMessage(message)
                .queue();
    }


    public void sendBumped(Bumper.Entity bumper) {
        sendChannelMessage(textChannel, bumper.getUsername() + " bumped. GREAT JOB");
    }
*/

}
