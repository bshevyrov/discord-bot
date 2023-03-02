package ua.com.company.slash.mal;


import dev.katsute.mal4j.anime.Anime;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ua.com.company.handler.slash.Slash;
import ua.com.company.logic.mal.MalRequestHandler;
import ua.com.company.model.MALPagination;
import ua.com.company.model.MALResponse;
import ua.com.company.utils.MALConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyAnimeListCommand implements Slash {
    @Override
    public void onSlashCommandEvent(SlashCommandInteractionEvent event) {
        String type = event.getOption("type", OptionMapping::getAsString);
        String title = event.getOption("title", OptionMapping::getAsString);

        event.deferReply().queue();
        List<Anime> search = new ArrayList<>();
        List<MALResponse> responseList = new ArrayList<>();
        if (type.equals("anime")) {
//            search = MalRequestHandler.getAnimeListByTitle(title);
            responseList.addAll(MalRequestHandler.getAnimeListByTitle(title,0).stream()
                    .map(MALConverter::animeToMALResponse)
                    .collect(Collectors.toList()));
        }


//excecute work to delete context for 15 min
//        MALPagination malPagination = new MALPagination(search);
        MALPagination malPagination = new MALPagination(1,title);
        MALPagination.setMessageContext(-1L, responseList);
//        event.getHook().sendMessageEmbeds(new EmbedBuilder().setFooter("page 1").build())
        event.getHook().sendMessageEmbeds(malPagination.getMessageEmbed(-1L))
                .addActionRow(malPagination.getActiveRowButtons(-1L)).queue(message -> {
                    message.editMessageEmbeds(malPagination.getMessageEmbed(-1L));
                    List<MALResponse> tempRest = MALPagination.getMessageContext().remove(-1L);
                    MALPagination.setMessageContext(message.getIdLong(), tempRest);
                });
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
