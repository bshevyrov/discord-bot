package ua.com.company.slash.mal.pagination;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.ArrayList;
import java.util.List;

public class ButtonClickEvent {
    public void onButtonClick(ButtonInteractionEvent e) {
        //check if clicking is user

        // Split the ID to 2 Strings
        // page_1 = page | 1
        String[] args = e.getButton().getId().split("_");

        // Check if button is a page button
        if (args[0].equalsIgnoreCase("page")) {

            // Check if user pressed the cancel button and delete the message
            if (args[1].equalsIgnoreCase("cancel")) {

                e.getMessage().delete().queue();
                return;

            }

            // Convert Pagenumber String to Integer
            int pageNum = Integer.valueOf(args[1]);

            EmbedBuilder msg = new EmbedBuilder();
            List<Button> buttons = new ArrayList<Button>();

            //Check which page number is used
            switch (pageNum) {

                case 1:
                    msg.setTitle("Pagination");
                    msg.setDescription("Hello World! This is the first page");
                    msg.setFooter("Page 1/4");
                    msg.setColor(0x33cc33);
                    buttons.add(Button.primary("page_1", Emoji.fromUnicode("⏪")));
                    buttons.add(Button.primary("page_1", Emoji.fromUnicode("◀")));
                    buttons.add(Button.danger("page_cancel", Emoji.fromUnicode("❌")));
                    buttons.add(Button.primary("page_2", Emoji.fromUnicode("▶")));
                    buttons.add(Button.primary("page_4", Emoji.fromUnicode("⏩")));
                    break;

                case 2:
                    msg.setTitle("Pagination");
                    msg.setDescription("Hello World! This is the second page");
                    msg.setFooter("Page 2/4");
                    msg.setColor(0x33cc33);
                    buttons.add(Button.primary("page_1", Emoji.fromUnicode("⏪")));
                    buttons.add(Button.primary("page_1", Emoji.fromUnicode("◀")));
                    buttons.add(Button.danger("page_cancel", Emoji.fromUnicode("❌")));
                    buttons.add(Button.primary("page_3", Emoji.fromUnicode("▶")));
                    buttons.add(Button.primary("page_4", Emoji.fromUnicode("⏩")));
                    break;

                case 3:
                    msg.setTitle("Pagination");
                    msg.setDescription("Hello World! This is the third page");
                    msg.setFooter("Page 3/4");
                    msg.setColor(0x33cc33);
                    buttons.add(Button.primary("page_1", Emoji.fromUnicode("⏪")));
                    buttons.add(Button.primary("page_2", Emoji.fromUnicode("◀")));
                    buttons.add(Button.danger("page_cancel", Emoji.fromUnicode("❌")));
                    buttons.add(Button.primary("page_4", Emoji.fromUnicode("▶")));
                    buttons.add(Button.primary("page_4", Emoji.fromUnicode("⏩")));
                    break;

                case 4:
                    msg.setTitle("Pagination");
                    msg.setDescription("Hello World! This is the last page");
                    msg.setFooter("Page 4/4");
                    msg.setColor(0x33cc33);
                    buttons.add(Button.primary("page_1", Emoji.fromUnicode("⏪")));
                    buttons.add(Button.primary("page_3", Emoji.fromUnicode("◀")));
                    buttons.add(Button.danger("page_cancel", Emoji.fromUnicode("❌")));
                    buttons.add(Button.primary("page_4", Emoji.fromUnicode("▶")));
                    buttons.add(Button.primary("page_4", Emoji.fromUnicode("⏩")));
                    break;

            }

            // Edit the Message
            e.getMessage().editMessageEmbeds(msg.build()).setActionRow(buttons).queue();

        }
    }
}
