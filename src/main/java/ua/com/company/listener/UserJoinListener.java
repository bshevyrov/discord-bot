package ua.com.company.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.type.ChanelType;
import ua.com.company.type.RoleType;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class UserJoinListener extends ListenerAdapter {
    private final Logger log = LogManager.getLogger(this);

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

       /* if (guild.getRoleCache().size() == 250) {
            log.error("Maximum roles on server. Can`t create new");
        }
        for (Role role : guild.getRoleCache()) {
            System.out.println( role.getName());
        }

        List<Role> roles = guild.getRoleCache().getElementsByName(RoleType.SUSPENDED.name(),true);
         if(roles.size()==0){
             guild.createRole()
                     .setName(RoleType.SUSPENDED.name())
                     .setColor(Color.CYAN)
                     .setHoisted(true)//https://support.discord.com/hc/en-us/community/posts/360060076751-Un-hoisted-Role-Hierarchy
                     .setMentionable(false)
                     .queue(role -> System.out.println("Create role"));
         }*/


        guild.addRoleToMember(
                user, Objects.requireNonNull(
                        guild.getRolesByName(RoleType.SUSPENDED.name(), true).get(0), "Roll type is NULL")).queue();
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
        Member member = event.getMember();

        for (Role role : member.getRoles()) {
            if (role.getName().equals(RoleType.SUSPENDED.name())) {

                Guild guild = event.getGuild();


                List<GuildChannel> channels = guild.getChannels();
                for (GuildChannel channel : channels) {
                    if (channel.getName().equals(ChanelType.VERIFICATION_CHANEL.name())) {
                        channel.getPermissionContainer().upsertPermissionOverride(member).grant(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND).queue();
                    }
                    channel.getPermissionContainer().upsertPermissionOverride(member).deny(List.of(Permission.values())).queue();
                }
            }
        }


    }




    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        super.onGuildMemberRoleRemove(event);
    }

}
