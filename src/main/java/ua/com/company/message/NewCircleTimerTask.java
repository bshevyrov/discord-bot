package ua.com.company.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.Event;
import ua.com.company.Bumper;
import ua.com.company.MessageReader;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NewCircleTimerTask extends TimerTask {
    private final Event event;
    private final String PRIVATE_MESSAGE = "TIME TO BUMP!!!";

    public NewCircleTimerTask(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        List<Bumper.Entity> bumpers = new ArrayList<>(Bumper.findAll());

        while (true) {

            for (Bumper.Entity bumper : bumpers) {
                ZonedDateTime lastBumpTime = bumper.getBumpTime();
                sendChannelMessage(bumper, "Send PM to" + bumper.getUsername());
                sendPrivateMessage(bumper, PRIVATE_MESSAGE);
                try {
                    Thread.sleep(/*5 * 60*/30 * 1000);// Timer between call another bumper
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (lastBumpTime.equals(bumper.getBumpTime())) {
                    sendChannelMessage(bumper, bumper.getUsername() + " dont answer(\nChoose another Member.");
                } else {
                    sendChannelMessage(bumper, bumper.getUsername() + " bumped. GREAT JOB");
                    this.cancel();
                }
                sendChannelMessage(bumper, "All bumpers ignore. Start again!");
            }
        }
    }

    /**
     * This Method message send Private Message
     *
     * @param bumper  bumper Entity to whom send message
     * @param message message String text that send
     */
    private void sendPrivateMessage(Bumper.Entity bumper, String message) {
        // Send message and delete 30 seconds later
        event.getJDA().getUserById(bumper.getId()).openPrivateChannel() // RestAction<PrivateChannel>
                .flatMap(channel -> channel.sendMessage(message)) // RestAction<Message>
                .delay(30, TimeUnit.SECONDS) // RestAction<Message> with delayed response
//                .delay(5, TimeUnit.MINUTES) // RestAction<Message> with delayed response
                .flatMap(Message::delete).queue();
    }

    /**
     * This method send message to channel
     *
     * @param bumper  Entity to whom send message
     * @param message String text that send
     */
    private void sendChannelMessage(Bumper.Entity bumper, String message) {
        TextChannel textChannel = event.getJDA().getTextChannelById(new MessageReader().getCHANNEL_ID());
        textChannel.sendMessage(message)
                .queue();
    }
}
