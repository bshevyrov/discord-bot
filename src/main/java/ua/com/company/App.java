package ua.com.company;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import ua.com.company.button.MalButtonInteraction;
import ua.com.company.handler.slash.SlashCommandHandler;
import ua.com.company.logic.bumper.message.MessageReader;
import ua.com.company.utils.PropertiesReader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class App {
    //TODO BUMPER UPDATE for Owner and some roles to update bots


    public static void main(String[] args) throws InterruptedException {
        //init bot
        JDA jda = JDABuilder.createDefault(PropertiesReader.getBotToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.DIRECT_MESSAGES)
                .enableCache(CacheFlag.ACTIVITY)
                .addEventListeners(new MessageReader())
                .addEventListeners(new MalButtonInteraction())
                .addEventListeners(new VoiceCount())
                .addEventListeners(new ListenerEvents())
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();
        jda.awaitReady().addEventListener(
                new SlashCommandHandler(jda, jda.getGuildById(PropertiesReader.getGuild())));

//          new SiteScheduleExecute().init();

//        jda.getGuildById(PropertiesReader.getGuild()).getManager().setBanner()


        LocalTime localTime = LocalTime.of(0, 0, 0);
        ZoneId zoneId = ZoneId.of("Europe/Kiev");
        final ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDate.now(zoneId), localTime, zoneId);

        BannerTask bannerTask = new BannerTask();
        bannerTask.collectDataForBanner(zonedDateTime, jda.getGuildById(PropertiesReader.getGuild()));

        /*
        BannerTask bannerTask = new BannerTask();
//        LocalTime time = LocalTime.now();
        bannerTask.extracted(activityCount, voiceCount, jda.getGuildById(PropertiesReader.getGuild()),time );
*/


//        You can then use the job to monitor the execution and also cancel it, if desired.Example:
//
//        if (!job.isCancelled()) {
//            job.cancel(false);
//        }


/*        BannerWriter bannerWriter = new BannerWriter();
        bannerWriter.method("https://cdn.discordapp.com/avatars/705076842192830607/ff855907f774a21ac8804f36c287c4d8.png",
                "Member Name1234567890",
                "Member Status1234567890",
                "9",
                "9");*/


    }

}
