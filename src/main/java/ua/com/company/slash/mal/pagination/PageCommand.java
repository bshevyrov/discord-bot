package ua.com.company.slash.mal.pagination;

public class PageCommand {

    public void onSlashCommand(SlashCommandEvent e) {

        e.deferReply().queue();

        EmbedBuilder msg = new EmbedBuilder();
        msg.setTitle("Pagination");
        msg.setDescription("Hello World! This is the first page");
        msg.setFooter("Page 1/4");
        msg.setColor(0x33cc33);
        List<Button> buttons = new ArrayList<Button>();
        buttons.add(Button.primary("page_1", Emoji.fromUnicode("⏪")));
        buttons.add(Button.primary("page_1", Emoji.fromUnicode("◀")));
        buttons.add(Button.danger("page_cancel", Emoji.fromUnicode("❌")));
        buttons.add(Button.primary("page_2", Emoji.fromUnicode("▶")));
        buttons.add(Button.primary("page_4", Emoji.fromUnicode("⏩")));

        e.replyEmbeds(msg.build()).addActionRow(buttons).queue();
}
}