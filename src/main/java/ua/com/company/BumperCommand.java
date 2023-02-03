package ua.com.company;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class BumperCommand extends ListenerAdapter {
    /**
     * https://jda.wiki/using-jda/interactions/#command-interactions
     *Add slash commands
     * @param event
     */
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        event.getGuild().updateCommands().addCommands(
                Commands.slash("bumper", "Notifications when it's time to BUMP!")
                        .addSubcommands(new SubcommandData("add", "Add to bumper list."))
                        .addSubcommands(new SubcommandData("list", "Show list of bumpers."))
                        .addSubcommands(new SubcommandData("remove", "Remove from bumpers list."))
        ).queue();
    }


}
