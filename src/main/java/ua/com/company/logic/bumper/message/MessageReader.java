package ua.com.company.logic.bumper.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.Bumper;
import ua.com.company.exception.BumperNotFound;
import ua.com.company.handler.BotMessageCommand;
import ua.com.company.logic.bumper.bot.IBot;
import ua.com.company.utils.BumperConstants;
import ua.com.company.utils.PropertiesReader;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MessageReader extends ListenerAdapter {
    private final String CHANNEL_ID = PropertiesReader.getChannel(); //HARD CODED CHANEL IS
    //    List<String> triggerWords = List.of("фиксации", "bumped");
    List<String> triggerWords = List.of("bumped");
//    List<String> triggerWords = List.of("11", "qq");
//    private final String WORD_A = "11"; //HARD Bot word
    //    private final String WORD_A = "фиксации"; //HARD Bot word
//    private final String WORD_B = "qq"; //HARD CODED CHANEL IS
//    private final String WORD_B = "bumped"; //HHARD Bot word


    private ScheduledExecutorService executor;
    private NewCircleTimerTask tasktask;


    private Map<String, IBot> botMap;


    @Override
    public void onGenericMessage(GenericMessageEvent event) { //for Server Monitoring
        tasktask = new NewCircleTimerTask(event);
        if (event.getChannel().getId().equals(CHANNEL_ID)
        && event.getChannel().retrieveMessageById(event.getMessageId()).complete().getAuthor().isBot()) {
            BotMessageCommand botMessageCommand = new BotMessageCommand();
           botMap= botMessageCommand.getBotsMap();
           botMap.get(event.getChannel().retrieveMessageById(event.getMessageId()).complete().getAuthor().getAsTag())
                   .execute(event);



        }
    }

    private boolean isNewBumper(Message currentMessage) {
        return !Bumper.isExistId(getMemberFromEmbeddedDescription(currentMessage));
//        return !Bumper.isExistId(currentMessage.getReferencedMessage().getAuthor().getId());
    }



/*
    private boolean isValidMessage(Message currentMessage) {
        //loop all trigered words compare containing word
        return
                currentMessage.getAuthor().isBot()
                        &&
                        (currentMessage.getEmbeds().get(0)
                                .getDescription()
                                .contains(triggerWords.get(0)));
//                                || currentMessage.getEmbeds().contains(triggerWords.get(1)));
        //                        message.getEmbeds().forEach(messageEmbed -> System.out.println(messageEmbed.getDescription()));
    }
*/

    private void startSchedule(Event event) {
        tasktask = new NewCircleTimerTask(event);
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(tasktask, BumperConstants.PAUSE_BETWEEN_NEW_TASK,
                BumperConstants.PAUSE_BETWEEN_NEW_TASK, TimeUnit.MINUTES);
    }
}
