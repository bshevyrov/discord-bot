package ua.com.company.logic.bumper.message;

import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.handler.BotMessageCommand;
import ua.com.company.logic.bumper.bot.IBot;
import ua.com.company.utils.PropertiesReader;

import java.util.Map;


public class MessageReader extends ListenerAdapter {
    private final String CHANNEL_ID = PropertiesReader.getChannel(); //HARD CODED CHANEL IS

    private Map<String, IBot> botMap;


    @Override
    public void onGenericMessage(GenericMessageEvent event) { //for Server Monitoring
//TODO check if guild not test than full if check
        if (event.getChannel().getId().equals(CHANNEL_ID)
                && !event.getGuild().getSelfMember().getUser().equals(
                event.getChannel().retrieveMessageById(event.getMessageId()).complete().getAuthor())
                && event.getChannel().retrieveMessageById(event.getMessageId()).complete().getAuthor().isBot()
        ) {
            BotMessageCommand botMessageCommand = new BotMessageCommand();
            botMap = botMessageCommand.getBotsMap();
//            System.out.println((event.getChannel().retrieveMessageById(event.getMessageId()).complete().getAuthor().getAsTag()));
            String messageBotAuthorTag = event.getChannel().retrieveMessageById(event.getMessageId()).complete().getAuthor().getAsTag();
            if (botMap.containsKey(messageBotAuthorTag)) {
                botMap.get(messageBotAuthorTag).execute(event);
            }
        }
    }
}
