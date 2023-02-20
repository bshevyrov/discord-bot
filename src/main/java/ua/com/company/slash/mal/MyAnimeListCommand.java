package ua.com.company.slash.mal;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ua.com.company.handler.Slash;
import ua.com.company.logic.mal.MalRequestHandler;

import java.util.List;

public class MyAnimeListCommand implements Slash {
    @Override
    public void onSlashCommandEvent(SlashCommandInteractionEvent event) {
        String type = event.getOption("type", OptionMapping::getAsString);
        String title = event.getOption("title",OptionMapping::getAsString);
        MalRequestHandler.getAnimeListByTitle(title);

    }

    @Override
    public String getName() {
        return "mal";
    }

    @Override
    public String getDescription() {
        return "Get info from My Anime List";
    }

    @Override
    public boolean isSpecificGuild() {
        return true;
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.STRING,"type","What do you want to search",true)
                        .addChoice("anime","anime")
                        .addChoice("manga","manga"),
                new OptionData(OptionType.STRING,"title","Part of title to search",true)
        );
    }

    @Override
    public CommandData getCommandData() {
        return Slash.super.getCommandData();
    }
}
