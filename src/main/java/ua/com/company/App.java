package ua.com.company;

import ua.com.company.selenium.DiscordServerInfo;

public class App {
    //TODO BUMPER UPDATE for Owner and some roles to update bots


    public static void main(String[] args) throws InterruptedException {
        //init web driver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

//        MainClass mainClass = new MainClass();
//
//        mainClass.init();
//


        DiscordServerInfo curves = new DiscordServerInfo();
        curves.init();

   /* JDA jda = JDABuilder.createDefault(PropertiesReader.getBotToken())
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
