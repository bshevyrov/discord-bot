package ua.com.company;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.exception.BumperNotFound;
import ua.com.company.message.NewCircleTimerTask;
import ua.com.company.utils.BumperConstants;
import ua.com.company.utils.PropertiesReader;

import java.time.ZoneId;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MessageReader extends ListenerAdapter {
    private final String CHANNEL_ID = PropertiesReader.getChannel(); //HARD CODED CHANEL IS
        List<String> triggerWords = List.of("фиксации", "bumped");
//    List<String> triggerWords = List.of("11", "qq");
//    private final String WORD_A = "11"; //HARD Bot word
    //    private final String WORD_A = "фиксации"; //HARD Bot word
//    private final String WORD_B = "qq"; //HARD CODED CHANEL IS
//    private final String WORD_B = "bumped"; //HHARD Bot word


    private ScheduledExecutorService executor;
    private NewCircleTimerTask tasktask ;
    Bumper.Entity bumper;


    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        tasktask = new NewCircleTimerTask(event);
        if (event.getChannel().getId().equals(CHANNEL_ID)) {
            MessageChannel channel = event.getGuild().getTextChannelById(CHANNEL_ID);
            channel.retrieveMessageById(event.getMessageId())
                    .queue(message -> {
                        if (isValidMessage(message)) {
                            if (isNewBumper(message)) {
                                Bumper.add(message.getReferencedMessage().getMember());
                            }
                            try {
                                bumper = Bumper.findById(message.getReferencedMessage().getAuthor().getId());
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
                                NewCircleTimerTask.setBumped(true);
//                                NewCircleTimerTask.getThread().interrupt();
                                NewCircleTimerTask.getThread().stop();
                                tasktask.sendBumped(bumper);
                            }
                        }
                    });
        }
    }

    private boolean isNewBumper(Message currentMessage) {
        return !Bumper.isExistId(currentMessage.getReferencedMessage().getAuthor().getId());
    }

    private boolean isValidMessage(Message currentMessage) {
        return
                currentMessage.getAuthor().isBot()
                &&
                triggerWords.contains(currentMessage.getContentDisplay());
    }

    private void startSchedule(Event event) {

        tasktask = new NewCircleTimerTask(event);
        executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleWithFixedDelay(tasktask, BumperConstants.PAUSE_BETWEEN_NEW_TASK, BumperConstants.PAUSE_BETWEEN_NEW_TASK, TimeUnit.MINUTES);

    }
}
