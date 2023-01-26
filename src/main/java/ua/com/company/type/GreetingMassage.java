package ua.com.company.type;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import net.dv8tion.jda.api.utils.messages.MessageEditBuilder;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class GreetingMassage {
    private static MessageEditData greetingMessage = new MessageEditBuilder().setContent("Default message").build();

    public static void setMessage(MessageEditData message) {
        greetingMessage = message;
    }

    public static MessageEditData getMessage() {

           return greetingMessage;

       }



}
