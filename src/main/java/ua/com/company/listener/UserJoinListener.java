package ua.com.company.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.type.ChanelType;
import ua.com.company.type.RoleType;

import java.util.List;
import java.util.Objects;

public class UserJoinListener extends ListenerAdapter {
    /**
     * Method add roll suspended for new member
     *
     * @param event
     */
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        User user = event.getUser();
        if (user.isBot()) return;
        Guild guild = event.getGuild();
        JDA jda = event.getJDA();//TODO route create role
        guild.addRoleToMember(user, Objects.requireNonNull(jda.getRoleById(RoleType.SUSPENDED.name()), "Roll type is NULL"));
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        super.onGuildMemberRemove(event);
    }

    /**
     * When adding specific role, DENY all permission in all channels. Allow only view in VERIFICATION_CHANEL
     *
     * @param event
     */
    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        if (event.getRoles().contains(RoleType.SUSPENDED)) {//TODO Change RoleType.SUSPENDED to role entity


            Member member = event.getMember();
            Guild guild = event.getGuild();
            List<GuildChannel> channels = guild.getChannels();
            for (GuildChannel channel : channels) {
                if (channel.getName().equals(ChanelType.VERIFICATION_CHANEL.name())) {
                    channel.getPermissionContainer().upsertPermissionOverride(member).grant(Permission.VIEW_CHANNEL).queue();
                }
                channel.getPermissionContainer().upsertPermissionOverride(member).deny(List.of(Permission.values())).queue();
            }
        }
    }


    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        super.onGuildMemberRoleRemove(event);
    }

}
