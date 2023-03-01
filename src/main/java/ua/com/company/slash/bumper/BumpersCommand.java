package ua.com.company.slash.bumper;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;
import ua.com.company.entity.Bumper;
import ua.com.company.handler.Slash;

import java.util.List;

//ADMIN BUMP COMMAND
public class BumpersCommand implements Slash {


    @Override
    public void onSlashCommandEvent(SlashCommandInteractionEvent event) {
        String rsl = "";

        if (event.getFullCommandName().equals("bumpers add") && event.getOption("name") != null) {
            if(!event.getOption("name").getAsUser().isBot()){
            rsl = new Bumper().add((event.getOption("name").getAsMember()));
            event.reply(rsl)
                    .setEphemeral(true)
                    .queue(); // reply immediately
            return;
            }
        }

        if (event.getFullCommandName().equals("bumpers remove") && event.getOption("name") != null) {
            rsl = new Bumper().remove((event.getOption("name").getAsMember()));
            event.reply(rsl)
                    .setEphemeral(true)
                    .queue(); // reply immediately
            return;
        }
    }



    @Override
    public String getName() {
        return "bumpers";
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


    public List<OptionData> getAddOptions() {
        return List.of(
                new OptionData(OptionType.USER, "name", "Member to add.", true)
        );
    }

    public List<OptionData> getRemoveOptions() {
        return List.of(
                new OptionData(OptionType.USER, "name", "Member to remove.", true)
        );
    }

    @Override
    public CommandData getCommandData() {
        return new CommandDataImpl(getName(), getDescription())
                .addSubcommands(
                        new SubcommandData("add", "Add SOMEONE to list.")
                                .addOptions(getAddOptions()))
                .addSubcommands(
                        new SubcommandData("remove", "Remove SOMEONE to list.")
                                .addOptions(getRemoveOptions()))
                .setGuildOnly(isGuildOnly())
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED);
    }

}
