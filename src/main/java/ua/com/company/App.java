package ua.com.company;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import ua.com.company.utils.PropertiesReader;

public class App {
    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault(PropertiesReader.getToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.MESSAGE_CONTENT)
//                .addEventListeners(new UserJoinListener())
//                .addEventListeners(new GuildJoinListener())
//                .addEventListeners(new RoleCreateListener())
//                .addEventListeners(new ContextMenuBot())
                .addEventListeners(new GuildJoin())
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();


    }
}
