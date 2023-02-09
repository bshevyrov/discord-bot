package ua.com.company;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import ua.com.company.handler.SlashCommandHandler;
import ua.com.company.utils.PropertiesReader;

public class App {
    //TODO BUMPER UPDATE for Owner and some roles to update bots

//public static final String GUILD_ID="967764101256331304";    //Цей-Во
public static final String GUILD_ID="1064965633743278081";    //СЕРВЕР ЖУРБАКИ
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
        jda.awaitReady().addEventListener(new SlashCommandHandler(jda, jda.getGuildById(GUILD_ID)));
//        try {
//            Thread.sleep(5*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        MessageReader reader = new MessageReader();
//        reader.getLastMessage(jda);


    }
}
