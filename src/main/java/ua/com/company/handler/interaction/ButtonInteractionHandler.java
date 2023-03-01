//package ua.com.company.handler.interaction;
//
//import io.github.classgraph.ClassGraph;
//import net.dv8tion.jda.api.JDA;
//import net.dv8tion.jda.api.entities.Guild;
//import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
//import net.dv8tion.jda.api.hooks.ListenerAdapter;
//import org.jetbrains.annotations.NotNull;
//import ua.com.company.handler.slash.Slash;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ButtonInteractionHandler extends ListenerAdapter {
//    List<ButtonPress> btns = new ArrayList<>();
//    JDA jda;
//
//    public ButtonInteractionHandler() {
////        this.jda = jda;
//        ClassGraph classGraph = new ClassGraph();
//        List<ButtonPress> buttonPresses = new ArrayList<>();
//        classGraph.enableClassInfo()
//                .scan()
//                .getClassesImplementing(ButtonPress.class)
//                .forEach(classInfo -> {
//                    try {
//                        ButtonPress buttonPress = (ButtonPress) classInfo.loadClass().getDeclaredConstructor().newInstance();
//                        buttonPresses.add(buttonPress);
//                    } catch (RuntimeException | InvocationTargetException | InstantiationException |
//                             IllegalAccessException |
//                             NoSuchMethodException e) {
//                        throw new RuntimeException("Unable to add Slash with the reason " + e);
//                    }
//                });
//        registerSlashCommands(buttonPresses);
//    }
//
//    private void registerSlashCommand(ButtonPress buttonPress) {
//       // jda.addEventListener(buttonPress);
////            slashMap.put(slash.getName(), slash);
////
////            if (slash.isSpecificGuild()) {
////                guildCommandsData.addCommands(slash.getCommandData());
////            } else {
////                globalCommandsData.addCommands(slash.getCommandData());
////            }
//    }
//
//    public void registerSlashCommands(List<ButtonPress> buttonPresses) {
//        buttonPresses.forEach(this::registerSlashCommand);
////            queueCommands();
//    }
//
////        private void queueCommands() {
////            globalCommandsData.queue();
////            guildCommandsData.queue();
////        }
//
//    @Override
//    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
//        btns.forEach(buttonPress -> buttonPress.onButtonInteraction(event));
//    }
//
////    @Override
////        public void ( event) {
////            Slash slash = slashMap.get(event.getName());
////            slash.onSlashCommandEvent(event);
////        }
//}
//
//
