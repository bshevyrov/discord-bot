package ua.com.company;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.listener.GuildJoinListener;
import ua.com.company.listener.RoleCreateListener;
import ua.com.company.listener.UserJoinListener;
import ua.com.company.utils.PropertiesReader;

public class App {
    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault(PropertiesReader.getToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new UserJoinListener())
                .addEventListeners(new GuildJoinListener())
                .addEventListeners(new RoleCreateListener())
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();


    }
}
