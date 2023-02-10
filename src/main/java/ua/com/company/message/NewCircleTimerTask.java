package ua.com.company.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.requests.ErrorResponse;
import ua.com.company.Bumper;
import ua.com.company.utils.PropertiesReader;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class NewCircleTimerTask extends TimerTask {
    private final Event event;

    private final String PRIVATE_MESSAGE = "TIME TO BUMP!!!";
    private final long DELAY_BEFORE_DELETE_MESSAGE = 4*60*1000+30*1000;//4min 30 sec
    private final long DELAY_BEFORE_SEND_ANOTHER_MESSAGE = 5*60*1000;//5min

    public NewCircleTimerTask(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        TextChannel textChannel = event.getJDA().getTextChannelById(PropertiesReader.getChannel());

        if (isNight()) {
            sendChannelMessage(textChannel, "I don't send any PM's at night :new_moon_with_face: . Please BUMP someone!!");
//                this.cancel();
            return;
        }
        List<Bumper.Entity> bumpers = new ArrayList<>(Bumper.findAll());
        while (true) {

            for (Bumper.Entity bumper : bumpers) {
                ZonedDateTime lastBumpTime = bumper.getBumpTime();
                sendChannelMessage(textChannel, "Send PM to " + bumper.getUsername());
                sendPrivateMessage(bumper, textChannel, PRIVATE_MESSAGE);

                try {
                    Thread.sleep(DELAY_BEFORE_SEND_ANOTHER_MESSAGE);// Timer between call another bumper
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (lastBumpTime.equals(bumper.getBumpTime())) {
                    sendChannelMessage(textChannel, bumper.getUsername() + " dont answer(\nChoose another Member.");
                } else {
                    sendChannelMessage(textChannel, bumper.getUsername() + " bumped. GREAT JOB");
                    return;
                }
                sendChannelMessage(textChannel, "All bumpers ignore. Start again!");
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
                .delay(DELAY_BEFORE_DELETE_MESSAGE, TimeUnit.SECONDS) // RestAction<Message> with delayed response
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
    private void sendChannelMessage(TextChannel context, String message) {
        context.sendMessage(message)
                .queue();
    }

    private boolean isNight() {
        int hour = Calendar.getInstance(TimeZone.getTimeZone("Europe/Kiev"))
                .get(Calendar.HOUR_OF_DAY);
        return hour > 0 && hour < 12;
    }
}
