package ua.com.company;

import com.sun.tools.javac.Main;
import ua.com.company.selenium.JavaRunCommand;
import ua.com.company.selenium.MainClass;

public class App {
    //TODO BUMPER UPDATE for Owner and some roles to update bots


    public static void main(String[] args) throws InterruptedException {
//        MainClass mainClass = new MainClass();
//
//        mainClass.init();
//


        MainClass curves = new MainClass();
        curves.init();

   /* JDA jda = JDABuilder.createDefault(PropertiesReader.getToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.DIRECT_MESSAGES)

                .addEventListeners(new MessageReader())
                .addEventListeners(new ListenerEvents())
                .addEventListeners()
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();
        jda.awaitReady().addEventListener(new SlashCommandHandler(jda, jda.getGuildById(PropertiesReader.getGuild())));
*/
    }


}
