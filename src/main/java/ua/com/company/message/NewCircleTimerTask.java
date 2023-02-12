package ua.com.company.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;
import ua.com.company.Bumper;
import ua.com.company.utils.BumperConstants;
import ua.com.company.utils.PropertiesReader;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class NewCircleTimerTask extends TimerTask {
    private static Event event = null;
    private static TextChannel textChannel;

    private static MessageSender thread;

    public static MessageSender getThread() {
        return thread;
    }

    static boolean isMessageSenderInterrupted = false;

    public static void setMessageSenderInterrupted(boolean messageSenderInterrupted) {
        isMessageSenderInterrupted = messageSenderInterrupted;
    }
    private static boolean bumped = false;

    public static void setBumped(boolean bumped) {
        NewCircleTimerTask.bumped = bumped;
    }

    public NewCircleTimerTask(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        textChannel = event.getJDA().getTextChannelById(PropertiesReader.getChannel());

        if (isNight()) {
            sendChannelMessage(textChannel, "I don't send any PM's at night :new_moon_with_face: . Please BUMP someone!!");
            return;
        }

        List<Bumper.Entity> bumpers = new ArrayList<>(Bumper.findAll());
        while (!bumped) {

            for (Bumper.Entity bumper : bumpers) {
                System.out.println(bumper.getUsername());
                thread = new MessageSender(textChannel, bumper);
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
                sendChannelMessage(textChannel, "All bumpers ignore. Start again!");
            } else {
                bumped=false;
                return;
//                continue;
            }
        }

        bumped = false;
    }

    /**
     * This Method message send Private Message
     *
     * @param bumper  bumper Entity to whom send message
     * @param message message String text that send
     */
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

    /**
     * This method send message to channel
     *
     * @param context TextChannel where send message
     * @param message String message
     */
    static void sendChannelMessage(TextChannel context, String message) {
        context.sendMessage(message)
                .queue();
    }

    private boolean isNight() {
        int hour = Calendar.getInstance(TimeZone.getTimeZone("Europe/Kiev"))
                .get(Calendar.HOUR_OF_DAY);
        return hour > 0 && hour < 12;
    }

    public void sendBumped(Bumper.Entity bumper) {
        sendChannelMessage(textChannel, bumper.getUsername() + " bumped. GREAT JOB");
    }

}
