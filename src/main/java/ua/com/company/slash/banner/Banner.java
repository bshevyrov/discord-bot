package ua.com.company.slash.banner;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;
import ua.com.company.ActivityCount;
import ua.com.company.handler.slash.Slash;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Banner implements Slash {


    @Override
    public void onSlashCommandEvent(SlashCommandInteractionEvent event) {
        ActivityCount activityCount = (ActivityCount) event.getJDA().getEventManager().getRegisteredListeners().stream()
                .filter(o -> o instanceof ActivityCount)
                .findFirst()
                .get();

        String type = event.getOption("type", OptionMapping::getAsString);
        Member member = event.getOption("member", OptionMapping::getAsMember);

        if (event.getFullCommandName().equals("banner leaderboard")) {
            event.deferReply().queue();
            event.getHook()
                    .sendMessageEmbeds(buildEmbedFromActivity(activityCount, event))
                    .setEphemeral(true)
                    .queue();
        }
        if (event.getFullCommandName().equals("banner blacklist")) {

            if (type.equals("add")) {
                if (member != null) {
                    activityCount.addToBlacklist(member.getUser());
                }
                activityCount.addToBlacklist(event.getUser());
            }
            if (type.equals("remove")) {
                if (member != null) {
                    activityCount.removeFromBlacklist(member.getUser());
                }
                activityCount.removeFromBlacklist(event.getUser());
            }
            if (type.equals("list")) {
                event.replyEmbeds(
                        buildEmbedFromBlacklist(activityCount.getBlacklist(), event)).queue();
            }

        }
    }

    @Override
    public String getName() {
        return "banner";
    }

    @Override
    public String getDescription() {
        return "Commands that interact with banner module.";
    }

    @Override
    public boolean isSpecificGuild() {
        return true;
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }


    public List<OptionData> getBlacklistOptions() {
        return List.of(
                new OptionData(OptionType.STRING, "type", "What do you want to do?", true)
                        .addChoice("add", "add")
                        .addChoice("remove", "remove")
                        .addChoice("list", "list"),
                new OptionData(OptionType.USER, "member", "If no member it`s apply to you", false)
        );
    }

    @Override
    public CommandData getCommandData() {
        return new CommandDataImpl(getName(), getDescription())
                .addSubcommands(
                        new SubcommandData("blacklist", "Add, remove,list of blacklist.")
                                .addOptions(getBlacklistOptions()))
                .addSubcommands(
                        new SubcommandData("leaderboard", "Show leaderboard."))
                .setGuildOnly(isGuildOnly())
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED);
    }

    private MessageEmbed buildEmbedFromActivity(ActivityCount activityCount, SlashCommandInteractionEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        final int[] number = {0};
        activityCount.getCurrentStateMap(event.getGuild()).forEach(
                (user, count) -> {
                    try {
                        eb.addField(
                                event.getGuild().retrieveMember(user).submit().get().getNickname() == null
                                        ? ++number[0] + ". " + user.getName()
                                        : ++number[0] + ". " + event.getGuild().retrieveMember(user).submit().get().getNickname(),
                                "Messages: " + count.getMessages() + " Minutes in voice: " + count.getMinutes(),
                                false);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });


        return eb.build();
    }

    private MessageEmbed buildEmbedFromBlacklist(List<User> users, SlashCommandInteractionEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        final int[] number = {0};
        users.forEach((user) -> {
            try {
                eb.addField(
                        event.getGuild().retrieveMember(user).submit().get().getNickname() == null
                                ? ++number[0] + ". " + user.getName()
                                : ++number[0] + ". " + event.getGuild().retrieveMember(user).submit().get().getNickname(),
                        "",
                        false);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        return eb.build();
    }

}
