package ua.com.company;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class BumperCommandReaction extends ListenerAdapter {

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String rsl = "";
        if (event.getFullCommandName().equals("bumper add")) {
            rsl = new Bumper().add(event.getMember());
            event.reply(rsl)
                    .setEphemeral(true)
                    .queue(); // reply immediately
            return;
        }
        if (event.getFullCommandName().equals("bumper list")) {
            String bumpers = new Bumper().findAll();
            event.reply(bumpers)
                    .setEphemeral(true)
                    .queue(); // reply immediately
            return;
        }
        if (event.getFullCommandName().equals("bumper remove")) {
            rsl = new Bumper().remove(event.getMember());
            event.reply(rsl)
                    .setEphemeral(true)
                    .queue(); // reply immediately
            return;
        }
    }
}