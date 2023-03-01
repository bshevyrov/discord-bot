package ua.com.company;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import ua.com.company.button.MalButtonInteraction;
import ua.com.company.handler.slash.SlashCommandHandler;
import ua.com.company.logic.bumper.message.MessageReader;
import ua.com.company.utils.PropertiesReader;

import java.io.IOException;

public class App {
    //TODO BUMPER UPDATE for Owner and some roles to update bots


    public static void main(String[] args) throws InterruptedException, IOException {
        //init web driver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        //init bot
        JDA jda = JDABuilder.createDefault(PropertiesReader.getBotToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new MessageReader())
                .addEventListeners(new MalButtonInteraction())
                .addEventListeners(new ListenerEvents())
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();
        jda.awaitReady().addEventListener(
                new SlashCommandHandler(jda, jda.getGuildById(PropertiesReader.getGuild())));


    }
}
