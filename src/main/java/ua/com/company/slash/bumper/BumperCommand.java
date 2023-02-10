package ua.com.company.slash.bumper;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;
import ua.com.company.Bumper;
import ua.com.company.handler.Slash;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BumperCommand implements Slash {


    @Override
    public void onSlashCommandEvent(SlashCommandInteractionEvent event) {
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

    @Override
    public String getName() {
        return "bumper";
    }

    @Override
    public String getDescription() {
        return "Notifications when it's time to BUMP!";
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
    public CommandData getCommandData() {
        return new CommandDataImpl(getName(), getDescription())
                .addSubcommands(
                        new SubcommandData("add", "Add me to list.")
                )
                .addSubcommands(
                        new SubcommandData("remove", "Remove me to list.")
                )
                .addSubcommands(new SubcommandData("list", "Show list of bumpers."))
                .setGuildOnly(isGuildOnly());
    }

}
