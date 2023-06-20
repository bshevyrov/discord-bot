package ua.com.company.logic.bumper.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;
import ua.com.company.logic.bumper.task.ManualBotTimerTask;
import ua.com.company.utils.BumperConstants;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MessageSender extends Thread {
    private final TextChannel textChannel;
    private User user;
    private ManualBotTimerTask timerTask;

    public MessageSender(TextChannel textChannel, User user , TimerTask timerTask) {
//        this.textChannel = textChannel;
//        this.bumper = bumper;
        this(textChannel,user);
        this.timerTask = (ManualBotTimerTask) timerTask;
    }

    public MessageSender(TextChannel textChannel, User user) {
        this.textChannel = textChannel;
        this.user = user;
    }

    public MessageSender(TextChannel textChannel) {
        this.textChannel = textChannel;
    }

    @Override
    public void run() {
        timerTask.setMessageSenderInterrupted(true);
        if (isNight()) {
            sendChannelMessage("I don't send any PM's at night :new_moon_with_face: . Please BUMP someone!!");
            return;
        }


//        sendChannelMessage("Send PM to " + user.getAsTag());
//              sendChannelMessage("Send PM to " + "<@"+user.getName()+">");
        AtomicReference<String> messageId = new AtomicReference<>("");
        textChannel.sendMessage("Send PM to " + "<@"+user.getId()+">")
                .delay( BumperConstants.DELAY_BEFORE_SEND_ANOTHER_MESSAGE, TimeUnit.SECONDS)
            .flatMap(Message::delete).queue();

//        sendPrivateMessage(user, textChannel, BumperConstants.PRIVATE_MESSAGE);
        try {
            sleep(BumperConstants.DELAY_BEFORE_SEND_ANOTHER_MESSAGE);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (isAlive()) {
            timerTask.setMessageSenderInterrupted(false);
            sendChannelMessage(user.getAsTag() + " dont answer(\nChoose another Member.");
        }
    }

    private boolean isNight() {
        int hour = Calendar.getInstance(TimeZone.getTimeZone("Europe/Kiev"))
                .get(Calendar.HOUR_OF_DAY);
        return hour > 0 && hour < 12;
    }

    /**
     * This Method message send Private Message
     *
     * @param bumper  bumper Entity to whom send message
     * @param message message String text that send
     */
    public void sendPrivateMessage(User bumper, TextChannel context, String message) {
        // Send message and delete 30 seconds later
        bumper
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
                                (e) -> context.sendMessage("Cannot send direct message to bumper " + bumper.getName())
                                        .queue()));
        //https://stackoverflow.com/questions/61292993/whats-the-best-way-to-handle-not-being-able-to-send-a-private-message-to-a-user
        //Error handling
    }

    /**
     * This method send message to channel
     *
     * @param message String message
     */
    public void sendChannelMessage(String message) {
        textChannel.sendMessage(message)
                .queue();
    }


    public void sendBumped() {
        sendChannelMessage(user.getAsTag() + " bumped. GREAT JOB");
    }

}
