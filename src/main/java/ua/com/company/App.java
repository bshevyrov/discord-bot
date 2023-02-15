package ua.com.company;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import ua.com.company.handler.SlashCommandHandler;
import ua.com.company.utils.PropertiesReader;

import java.util.function.BiConsumer;

public class App {
    //TODO BUMPER UPDATE for Owner and some roles to update bots


    public static void main(String[] args) throws InterruptedException {
                JDA jda = JDABuilder.createDefault(PropertiesReader.getToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.DIRECT_MESSAGES)
//                .addEventListeners(new UserJoinListener())
//                .addEventListeners(new GuildJoinListener())
//                .addEventListeners(new RoleCreateListener())
//                .addEventListeners(new ContextMenuBot())
//                .addEventListeners(new BumperCommand())
//                .addEventListeners(new BumperCommandReaction())
                .addEventListeners(new MessageReader())
                .addEventListeners(new ListenerEvents())
                .addEventListeners()
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();
        jda.awaitReady().addEventListener(new SlashCommandHandler(jda, jda.getGuildById(PropertiesReader.getGuild())));

    }
}
