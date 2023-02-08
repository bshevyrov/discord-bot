package ua.com.company;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;

import java.util.ArrayList;
import java.util.List;

public class BumperCommand extends ListenerAdapter {
    //TODO BUMPER AD STRICTLY for Owner end some roles/ add members by option.
    /**
     * https://jda.wiki/using-jda/interactions/#command-interactions
     *Add slash commands
     * @param event
     */
    @Override
    public void onGuildJoin(GuildJoinEvent event) {

        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("bumper", "Notifications when it's time to BUMP!")
             //   .addSubcommandGroups(new SubcommandGroupData("add","Add member to list.")
                        .addSubcommands(new SubcommandData("add","Add me to list.")
                        .addOption(OptionType.USER,"name","Member to add.",false)));

event.getGuild().updateCommands().addCommands(commandData).queue();



/*        event.getGuild().updateCommands().addCommands(
                Commands.slash("bumper", "Notifications when it's time to BUMP!")
//                        .addSubcommands(new SubcommandData("me add", "Add me to bumper list."))
                        .addSubcommands(new SubcommandData("add","Add member to list."))
                        .addOption(OptionType.USER,"name","Member to add.",false)
                        .addSubcommands(new SubcommandData("list", "Show list of bumpers."))
//                        .addSubcommands(new SubcommandData("me remove", "Remove me from bumpers list."))
                        .addSubcommands(new SubcommandData("remove","Remove from bumpers list"))
                        .addOption(OptionType.USER,"name","Member to remove",false)
        ).queue();*/
    }


}
