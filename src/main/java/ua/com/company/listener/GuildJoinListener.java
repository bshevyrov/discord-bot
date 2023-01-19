package ua.com.company.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.type.ChanelType;
import ua.com.company.type.RoleType;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;

public class GuildJoinListener extends ListenerAdapter {
    private final Logger log = LogManager.getLogger(this);

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        JDA jda = event.getJDA();
        Guild guild = event.getGuild();

        if (guild.getRoleCache().size() == 250) {
            log.error("Maximum roles on server. Can`t create new");
        }

        guild.createTextChannel(ChanelType.VERIFICATION_CHANEL.name())
//                .addPermissionOverride((IPermissionHolder) guild.getMembers(), EnumSet.of(Permission.VIEW_CHANNEL),null)
                .queue(textChannel ->  System.out.println("Created!"));

        List<Role> roles = guild.getRoleCache().getElementsByName(RoleType.SUSPENDED.name(), true);
        if (roles.size() == 0) {
            createNeededRole(guild);
        } else {
            log.warn("Role " + RoleType.SUSPENDED.name() + " already exist. Please put bot higher than role in hierarchy");
        }


    }

    private void createNeededRole(Guild guild) {
        guild.createRole()
                .setName(RoleType.SUSPENDED.name())
                .setColor(Color.CYAN)
                .setHoisted(true)//https://support.discord.com/hc/en-us/community/posts/360060076751-Un-hoisted-Role-Hierarchy
                .setMentionable(false)
                .queue(role -> System.out.println("Create role"));
    }
}

