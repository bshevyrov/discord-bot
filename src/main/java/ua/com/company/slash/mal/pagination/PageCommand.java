package ua.com.company.slash.mal.pagination;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.ArrayList;
import java.util.List;

public class PageCommand {

    public void onSlashCommand(SlashCommandInteractionEvent e) {

        e.deferReply().queue();

        EmbedBuilder msg = new EmbedBuilder();
        msg.setTitle("Pagination");
        msg.setDescription("Hello World! This is the first page");
        msg.setFooter("Page 1/4");
        msg.setColor(0x33cc33);
        List<Button> buttons = new ArrayList<>();
        buttons.add(Button.primary("page_1", Emoji.fromUnicode("\u23EA")));
        buttons.add(Button.primary("page_1", Emoji.fromUnicode("\u25C0")));
        buttons.add(Button.danger("page_cancel", Emoji.fromUnicode("\u274C")));
        buttons.add(Button.primary("page_2", Emoji.fromUnicode("\u25B6")));
        buttons.add(Button.primary("page_4", Emoji.fromUnicode("\u23E9")));

        e.replyEmbeds(msg.build()).addActionRow(buttons).queue();
    }
}