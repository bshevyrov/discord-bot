package ua.com.company.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionComponent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageEditData;
import ua.com.company.type.GreetingMassage;

import java.util.Objects;

public class ContextMenuBot extends ListenerAdapter {


    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
//        if(event.getName().equalsIgnoreCase("Get message")){
            Message message = event.getTarget()
                    .getReferencedMessage();


            GreetingMassage.setMessage((MessageEditData) message);

//

        }
//        if(Objects.requireNonNull(event.getMember()).isOwner()) {
//            Message message = event.getTarget();
//            GreetingMassage.setMessage(message);
//        }

    }
//}
