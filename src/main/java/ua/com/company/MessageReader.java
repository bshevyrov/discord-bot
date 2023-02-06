package ua.com.company;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.exception.BumperNotFound;
import ua.com.company.message.NewCircleTimerTask;

import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;


public class MessageReader extends ListenerAdapter {
    private final String CHANNEL_ID = "1064965635311939727"; //HARD CODED CHANEL IS
    private final String WORD_A = "11"; //HARD Bot word
    //    private final String WORD_A = "фиксации"; //HARD Bot word
    private final String WORD_B = "qq"; //HARD CODED CHANEL IS
//    private final String WORD_B = "bumped"; //HHARD Bot word

    public String getCHANNEL_ID() {
        return CHANNEL_ID;
    }

    private boolean isBumped = false;
    private Timer timer;
    private TimerTask task;
//    private final long FOUR_HOUR_DELAY=4*60*60*1000;
    private final long FOUR_HOUR_DELAY=60*1000;

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        if (event.getChannel().getId().equals(CHANNEL_ID)) {
            MessageChannel channel = event.getGuild().getTextChannelById(CHANNEL_ID);
            channel.retrieveMessageById(event.getMessageId())
                    .queue(message -> {
                        checkMessageAndDoJob(message, event);


                    });
            /*if (isBumped) {
                if (timer != null) {
                    task.cancel();
                }
                System.out.println("i bumped");
                task = new NewCircleTimerTask(event);
                timer = new Timer("Every 4 hour timer");
                System.out.println("start shedule");
                timer.schedule(task, FOUR_HOUR_DELAY);
            }*/
        }
    }

    private void checkMessageAndDoJob(Message currentMessage, Event event) {
        if (/*currentMessage.getAuthor().isBot()                &&*/
                (currentMessage.getContentDisplay().equals(WORD_A)
                        || currentMessage.getContentDisplay().equals(WORD_B))) {
            Bumper.Entity entity = null;

            if (!Bumper.isExistId(currentMessage.getReferencedMessage().getAuthor().getId())) {
                Bumper.add(currentMessage.getReferencedMessage().getMember());
            }
            try {
                entity = Bumper.findById(currentMessage.getReferencedMessage().getAuthor().getId());
            } catch (BumperNotFound e) {
//       log.error(e.getMessage());
            }
            entity.setBumpTime(currentMessage.getTimeCreated().atZoneSameInstant(ZoneId.of("Europe/Kiev")));
            isBumped=true;
        }
        if (isBumped) {
            isBumped=false;
            if (timer != null) {
                task.cancel();
            }
            task = new NewCircleTimerTask(event);
            timer = new Timer("Every 4 hour timer");
            timer.schedule(task, FOUR_HOUR_DELAY); //wait here
        }
    }
}
