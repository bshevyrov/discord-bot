package ua.com.company.listener;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.type.ChanelType;
import ua.com.company.type.RoleType;

import java.util.List;

public class RoleCreateListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
      TextChannel textChannel = (TextChannel) event.getChannel();
        Member member = event.getMember();
         if(ChanelType.VERIFICATION_CHANEL.name().equalsIgnoreCase(textChannel.getName())){
             List<GuildChannel> channels = event.getGuild().getChannels();
             for (GuildChannel channel : channels) {
                 if (!channel.getName().equalsIgnoreCase(ChanelType.VERIFICATION_CHANEL.name())) {
                     channel.getPermissionContainer().upsertPermissionOverride(member).grant(List.of(Permission.values())).queue();
                     continue;
                 }
                 channel.getPermissionContainer().upsertPermissionOverride(member).deny(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
             }
             event.getGuild().removeRoleFromMember(event.getUser(),event.getGuild().getRolesByName(RoleType.SUSPENDED.name(),true).get(0)).queue();
         }
    }

    /**
     * When adding specific role, DENY all permission in all channels. Allow only view in VERIFICATION_CHANEL
     *
     * @param event
     */
    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        Member member = event.getMember();

        for (Role role : member.getRoles()) {
            if (role.getName().equals(RoleType.SUSPENDED.name())) {
                List<GuildChannel> channels = event.getGuild().getChannels();
                for (GuildChannel channel : channels) {
                    if (channel.getName().equalsIgnoreCase(ChanelType.VERIFICATION_CHANEL.name())) {
                        channel.getPermissionContainer().upsertPermissionOverride(member).grant(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                        continue;
                    }
                    channel.getPermissionContainer().upsertPermissionOverride(member).deny(List.of(Permission.values())).queue();
                }
             TextChannel textChannel = event.getGuild().getTextChannelCache().getElementsByName(ChanelType.VERIFICATION_CHANEL.name(),true).get(0);
                textChannel.sendMessage("Welcome to MY channel. If u are ready add reaction").queue();
            }
        }



    }

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        super.onGuildMemberRoleRemove(event);
    }
}
