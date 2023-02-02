//package ua.com.company.listener;
//
//import net.dv8tion.jda.api.Permission;
//import net.dv8tion.jda.api.entities.Guild;
//import net.dv8tion.jda.api.entities.IPermissionHolder;
//import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
//import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
//import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
//import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
//import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
//import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import net.dv8tion.jda.api.interactions.commands.Command;
//import net.dv8tion.jda.api.interactions.commands.build.Commands;
//import net.dv8tion.jda.api.interactions.components.ActionRow;
//import net.dv8tion.jda.api.interactions.components.buttons.Button;
//import net.dv8tion.jda.api.interactions.components.text.TextInput;
//import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
//import net.dv8tion.jda.api.interactions.modals.Modal;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import ua.com.company.type.ChanelType;
//import ua.com.company.type.GreetingMassage;
//import ua.com.company.type.RoleType;
//
//import java.awt.*;
//import java.util.*;
//
//public class GuildJoinListener extends ListenerAdapter {
//    private final Logger log = LogManager.getLogger(this);
//
//    @Override
//    public void onGuildJoin(GuildJoinEvent event) {
//        Guild guild = event.getGuild();
//        //TODO Create special channel
//        createChannel(guild);
//        registerCommands(guild);
//        createNeededRole(guild);
//
//
//    }
//
//
//    /**
//     * Reaction on commands by Name
//     *
//     * @param event
//     */
//  /*  @Override
//   public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
//        TextChannel textChannel = (TextChannel) event.getChannel();
//        if (textChannel.getName().equalsIgnoreCase(ChanelType.VERIFICATION_CHANEL.name())) {
//            switch (event.getName()) {
//
//                case ("hi"):
////                    event.reply("Pin-Pin").setEphemeral(true) // reply or acknowledge
////                            .flatMap(v ->
////                                    event.getHook().editOriginalFormat(GreetingMassage.getMessage().getContentDisplay())
////                                    // then edit original
////                            ).queue(); // Queue both reply and edit
//                    event.deferReply(true).addContent("CONENT!!!")
////                                    .setEphemeral(true)
//                            .addActionRow(Button.primary("id", "Next step")).queue();
////                    event.getChannel().sendMessage(
////                            MessageCreateData.fromMessage(
////                                    GreetingMassage.getMessage()).getContent()).setActionRow(Button.primary("id", "lable")).queue();
//                    break;
//                default:
//                    break;
//
//                //Anothe command NAME
////                case ("greetings_message"):
////
////                    long time = System.currentTimeMillis();
////                    event.reply("Pong!").setEphemeral(true) // reply or acknowledge
////                            .flatMap(v ->
////                                    event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
////                            ).queue(); // Queue both reply and edit
////                    break;
//            }
//        }
//
//    }*/
//
//    @Override
//    public void onButtonInteraction(ButtonInteractionEvent event) {
//        if (event.getComponentId().equals("id")) {
//            TextInput subject = TextInput.create("name", "Name", TextInputStyle.SHORT)
//                    .setPlaceholder("What is your name")
//                    .setMinLength(2)
//                    .setMaxLength(26) // or setRequiredRange(10, 100)
//                    .build();
//
//            TextInput body = TextInput.create("age", "Age", TextInputStyle.SHORT)
//                    .setPlaceholder("your age here")
//                    .setRequiredRange(2, 2)
//                    .build();
//
//            Modal modal = Modal.create("modmail", "User Data").addActionRows(ActionRow.of(subject), ActionRow.of(body))
//                    .build();
//
//            event.replyModal(modal).queue();
//        } else if (event.getComponentId().equals("emoji")) {
//            event.editMessage("That button didn't say click me").queue(); // update the message
//        }
//    }
//
//
//    @Override
//    public void onModalInteraction(ModalInteractionEvent event) {
//        if (event.getModalId().equals("modmail")) {
//            String name = event.getValue("name").getAsString();
//            String age = event.getValue("age").getAsString();
//
//            if (Integer.parseInt(age) <= 16) {
//                event.reply("You too yaong").setEphemeral(true).queue();
//                return;
//            }
//
//            event.reply("Thanks for your request!").setEphemeral(true).queue();
//
//
//
//
//
//
//            //CHANGE ROLE;
//            event.getGuild().removeRoleFromMember(
//                    event.getMember(), Objects.requireNonNull(event.getGuild().getRoleById(RoleType.SUSPENDED.name()))).queue();
//
//
//             event.getGuild().addRoleToMember(
//                     event.getMember(),event.getJDA().getRoleById(RoleType.VERIFIED.name()))
//                    .queue();
//
//        }
//    }
//
//    /**
//     * create reauired roles in Guild
//     *
//     * @param guild
//     */
//    private void createNeededRole(Guild guild) {
//        guild.createRole()
//                .setName(RoleType.VERIFIED.name())
//                .setColor(Color.red)
//                .setHoisted(true)//https://support.discord.com/hc/en-us/community/posts/360060076751-Un-hoisted-Role-Hierarchy
//                .setMentionable(false)
//                .setPermissions()
//                .queue(role -> System.out.println("Create role VERIFIED"));
//
//
//        guild.createRole()
//                .setName(RoleType.SUSPENDED.name())
//                .setColor(Color.CYAN)
//                .setHoisted(true)//https://support.discord.com/hc/en-us/community/posts/360060076751-Un-hoisted-Role-Hierarchy
//                .setMentionable(false)
//                .setPermissions()
//                .queue(role -> System.out.println("Create role SUSPENDED"));
//    }
//
//    private void createChannel(Guild guild) {
//
//
//
////        guild.createTextChannel(ChanelType.VERIFICATION_CHANEL.name())
////                .queue(textChannel -> System.out.println("Created!"));
//
//
//
//
//        guild.createTextChannel(ChanelType.VERIFICATION_CHANEL.name())
//
////                .addPermissionOverride(Objects.requireNonNull(guild.getOwner()), EnumSet.of(Permission.VIEW_CHANNEL), null)
//                .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
//                .queue(textChannel ->  System.out.println("Created!")); // this actually sends the request to discord.
//    }
//
//
//
//
////                .addPermissionOverride(Objects.requireNonNull(guild.getRoleById(RoleType.VERIFIED.name()),"cant be null"), null,Permission.getPermissions(Permission.ALL_PERMISSIONS))
////                .addRolePermissionOverride(guild.getRoleById(RoleType.VERIFIED.name()), Permission.getPermissions(),Permission.getPermissions(Permission.ALL_PERMISSIONS))
//
//
////                .addPermissionOverride(Objects.requireNonNull(guild.getOwner(),"Can`t be a null"), Permission.getPermissions(Permission.ALL_PERMISSIONS),null)
////                .queue(textChannel ->  System.out.println("Created!"));
////
////
////
////    }
//
//    /**
//     * Register slash command and contextCommand in guild
//     *
//     * @param guild current guild
//     */
//    private void registerCommands(Guild guild) {
//
//        // Sets the global command list to the provided commands (removing all others)
//        guild.updateCommands().addCommands(
//                Commands.slash("hi", "Welcome message"),
//                Commands.context(Command.Type.MESSAGE, "Get message")
//        ).queue();
//
////                Commands.slash("ban", "Ban a user from the server")
////                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS) // only usable with ban permissions
////                                .setGuildOnly(true) // Ban command only works inside a guild
////                                .addOption(OptionType.USER, "user", "The user to ban", true) // required option of type user (target to ban)
////                                .addOption(OptionType.STRING, "reason", "The ban reason") // optional reason
//    }
//}
//
