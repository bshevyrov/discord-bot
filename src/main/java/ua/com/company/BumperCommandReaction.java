package ua.com.company;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            String bumpers = createAnswer(Bumper.findAll());
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

    private String createAnswer(Set<Bumper.Entity> bumpers) {
        List<String> list = bumpers.stream()
                .map(bumper -> bumper.getUsername() + " " + bumper.getBumpTime())
                .collect(Collectors.toList());
        if (list.size() == 0) {
            return "There is no bumpers in the list.";
        }
        StringBuilder resultAnswer = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            resultAnswer.append(i + 1);
            resultAnswer.append(". ");
            resultAnswer.append(list.get(i));
            resultAnswer.append("\n");
        }
        return resultAnswer.toString();
    }

}