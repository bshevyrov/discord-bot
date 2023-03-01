package ua.com.company.slash.mal;


import com.freya02.botcommands.api.components.InteractionConstraints;
import com.freya02.botcommands.api.pagination.paginator.Paginator;
import com.freya02.botcommands.api.pagination.paginator.PaginatorBuilder;
import dev.katsute.mal4j.anime.Anime;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import ua.com.company.entity.MALResponse;
import ua.com.company.handler.Slash;
import ua.com.company.logic.mal.MalRequestHandler;
import ua.com.company.utils.MALConverter;

import java.util.ArrayList;
import java.util.List;

public class MyAnimeListCommand implements Slash {
    @Override
    public void onSlashCommandEvent(SlashCommandInteractionEvent event) {
        String type = event.getOption("type", OptionMapping::getAsString);
        String title = event.getOption("title", OptionMapping::getAsString);

        event.deferReply().queue();
        System.out.println("11");
        List<Anime> search = new ArrayList<>();

        if (type.equals("anime")) {
            search = MalRequestHandler.getAnimeListByTitle(title);
        }

    }

    private EmbedBuilder createEmbeddedPage(MALResponse response) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(response.getTitle());
        eb.setDescription(response.getSynopsis());
        eb.addField("Status: ", response.getStatus(), false);
        eb.addField("Type: ", response.getType(), false);
        eb.addField("Duration: ", response.getEpisodeLengthInMillis() / 1000 * 60 + "min", false);
        eb.addField("Studio: ", response.getStudios().toString(), false);
        eb.addField("Genre: ", response.getGenres().toString(), false);

//        eb.build();
        return eb;
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
                new OptionData(OptionType.STRING, "type", "What do you want to search", true)
                        .addChoice("anime", "anime")
                        .addChoice("manga", "manga"),
                new OptionData(OptionType.STRING, "title", "Part of title to search", true)
        );
    }

    @Override
    public CommandData getCommandData() {
        return Slash.super.getCommandData();
    }
}
