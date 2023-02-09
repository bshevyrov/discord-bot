package ua.com.company.slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
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
        String rsl ="";
        if (event.getFullCommandName().equals("bumper add")&&event.getOption("name")==null) {
            rsl = new Bumper().add(event.getMember());
            System.out.println( event.getMember().getUser().getMutualGuilds().size());
            event.reply(rsl)
                    .setEphemeral(true)
                    .queue(); // reply immediately
            return;
        }

        if (event.getFullCommandName().equals("bumper add")&&event.getOption("name")!=null) {
            rsl = new Bumper().add((event.getOption("name").getAsMember()));
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
        if (event.getFullCommandName().equals("bumper remove")&&event.getOption("name")!=null) {
            rsl = new Bumper().remove((event.getOption("name").getAsMember()));
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
        return false;
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }


    public List<OptionData> getAddOptions() {
        return List.of(
                new OptionData(OptionType.USER, "name", "Member to add.", false)
        );
    }

    public List<OptionData> getRemoveOptions() {
        return List.of(
                new OptionData(OptionType.USER, "name", "Member to remove.", false)
        );
    }

    @Override
    public CommandData getCommandData() {
        return new CommandDataImpl(getName(), getDescription())
                .addSubcommands(
                        new SubcommandData("add", "Add me to list.")
                                .addOptions(getAddOptions()))
                .addSubcommands(
                        new SubcommandData("remove", "Remove me to list.")
                                .addOptions(getRemoveOptions()))
                .addSubcommands(new SubcommandData("list", "Show list of bumpers."))
                .setGuildOnly(isGuildOnly());
    }
}
