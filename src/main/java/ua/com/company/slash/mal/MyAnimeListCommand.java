package ua.com.company.slash.mal;


import dev.katsute.mal4j.anime.Anime;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import net.dv8tion.jda.api.utils.messages.MessageEditData;
import net.dv8tion.jda.internal.requests.restaction.pagination.ReactionPaginationActionImpl;
import ua.com.company.entity.MALResponse;
import ua.com.company.handler.Slash;
import ua.com.company.logic.mal.MalRequestHandler;
import ua.com.company.utils.MALConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAnimeListCommand implements Slash {
    @Override
    public void onSlashCommandEvent(SlashCommandInteractionEvent event) {
        String type = event.getOption("type", OptionMapping::getAsString);
        String title = event.getOption("title", OptionMapping::getAsString);

        event.deferReply().queue();
        List<Anime> search = new ArrayList<>();

        if (type.equals("anime")) {
            search = MalRequestHandler.getAnimeListByTitle(title);
        }


        List<Button> buttons = new ArrayList<>();

        buttons.add(Button.secondary("page_first", Emoji.fromUnicode("⏮")));
        buttons.add(Button.secondary("page_1", Emoji.fromUnicode("◀")));
        buttons.add(Button.secondary("page_cancel", Emoji.fromUnicode("❌")));
        buttons.add(Button.secondary("page_2", Emoji.fromUnicode("▶")));
        buttons.add(Button.secondary("page_last", Emoji.fromUnicode("⏭")));



        event.getHook().sendMessageEmbeds(
                createEmbeddedPage(MALConverter.animeToMALResponse(search.get(0))))
                .addActionRow(buttons).queue();
//
    }


    private MessageEmbed createEmbeddedPage(MALResponse response) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setFooter("Page 1/4");
        eb.setColor(0x33cc33);
        eb.setTitle(response.getTitle());
        eb.setDescription(response.getSynopsis());
        eb.addField("Status: ", response.getStatus(), false);
        eb.addField("Type: ", response.getType(), false);
        eb.addField("Duration: ", response.getEpisodeLengthInMillis() / 1000 * 60 + "min", false);
        eb.addField("Studio: ", response.getStudios().toString(), false);
        eb.addField("Genre: ", response.getGenres().toString(), false);

//        eb.build();
        return eb.build();
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
