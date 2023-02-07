package ua.com.company.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;
import ua.com.company.Bumper;
import ua.com.company.MessageReader;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NewCircleTimerTask extends TimerTask {
    private final Event event;
    private final String YAP_TWIST_ID = "437682338009317376";
    private final String MY_ID = "232929050652311553";
    private final String ALEXXL_ID = "1065267975092969613";
    private final String MY_GUILD_ID = "1064965633743278081";
    private final String PRIVATE_MESSAGE = "TIME TO BUMP!!!";

    public NewCircleTimerTask(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        TextChannel textChannel = event.getJDA().getTextChannelById(new MessageReader().getCHANNEL_ID());

        List<Bumper.Entity> bumpers = new ArrayList<>(Bumper.findAll());

        while (true) {

            for (Bumper.Entity bumper : bumpers) {
                ZonedDateTime lastBumpTime = bumper.getBumpTime();
                sendChannelMessage(bumper, textChannel, "Send PM to " + bumper.getUsername());
                sendPrivateMessage(bumper, textChannel, PRIVATE_MESSAGE);

                try {
                    Thread.sleep(/*5 * 60*/30 * 1000);// Timer between call another bumper
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (lastBumpTime.equals(bumper.getBumpTime())) {
                    sendChannelMessage(bumper, textChannel, bumper.getUsername() + " dont answer(\nChoose another Member.");
                } else {
                    sendChannelMessage(bumper, textChannel, bumper.getUsername() + " bumped. GREAT JOB");
                    this.cancel();
                }
                sendChannelMessage(bumper, textChannel, "All bumpers ignore. Start again!");
            }

        }
    }

    /**
     * This Method message send Private Message
     *
     * @param bumper  bumper Entity to whom send message
     * @param message message String text that send
     */
    private void sendPrivateMessage(Bumper.Entity bumper, TextChannel context, String message) {
        // Send message and delete 30 seconds later
        event.getJDA().retrieveUserById(bumper.getId()).complete()
                .openPrivateChannel() // RestAction<PrivateChannel>
                .flatMap(channel -> channel.sendMessage(message)) // RestAction<Message>
                .delay(30, TimeUnit.SECONDS) // RestAction<Message> with delayed response
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
     * @param bumper  Entity to whom send message
     * @param message String text that send
     */
    private void sendChannelMessage(Bumper.Entity bumper, TextChannel context, String message) {
        context.sendMessage(message)
                .queue();
    }
}
