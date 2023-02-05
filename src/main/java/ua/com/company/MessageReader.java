package ua.com.company;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.exception.BumperNotFound;

import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


public class MessageReader extends ListenerAdapter {
    private final String CHANNEL_ID = "1064965635311939727"; //HARD CODED CHANEL IS

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        if (event.getChannel().getId().equals(CHANNEL_ID)) {
            getLastMessage(event.getGuild());
        }
    }

    private MessageHistory history;

    public void getLastMessage(Guild guild) {
        Channel channel = guild.getTextChannelById(CHANNEL_ID);
        history = new MessageHistory((MessageChannel) channel);

        List<Message> mess = null;
        try {
            mess = history.retrievePast(50).submit().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        mess = mess.stream()
//                .filter(message -> message.getAuthor().isBot())
//                .filter(message -> message.getContentDisplay().contains("bumped") ||//HARD CODE
                .filter(message -> message.getContentDisplay().contains("qq") ||//HARD CODE
                        message.getContentDisplay().contains("11"))//HARD CODE
//                        message.getContentDisplay().contains("фиксации"))//HARD CODE
                .collect(Collectors.toList());

        Message currentMessage = mess.get(0);
        Bumper.Entity entity = null;
        if (!Bumper.isExistId(mess.get(0).getReferencedMessage().getAuthor().getId())) {
            Bumper.add(currentMessage.getReferencedMessage().getMember());
        }

        try {
            entity = Bumper.findById(mess.get(0).getReferencedMessage().getAuthor().getId());
        } catch (BumperNotFound e) {
//       log.error(e.getMessage());
        }
        entity.setBumpTime(currentMessage.getTimeCreated().atZoneSameInstant(ZoneId.of("Europe/Kiev")));

    }
}
