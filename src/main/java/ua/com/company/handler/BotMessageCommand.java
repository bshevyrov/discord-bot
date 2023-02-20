package ua.com.company.handler;

import io.github.classgraph.ClassGraph;
import ua.com.company.logic.bumper.bot.IBot;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BotMessageCommand {
    public Map<String,IBot> getBotsMap() {
        ClassGraph classGraph = new ClassGraph();
        Map<String, IBot> botMap = new HashMap();
        classGraph.enableClassInfo()
                .scan()
                .getClassesImplementing(IBot.class)
                .forEach(classInfo -> {
                    try {
                        IBot iBot = (IBot) classInfo.loadClass().getDeclaredConstructor().newInstance();
                        botMap.put(iBot.getTag(), iBot);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException("Unable to add IBot with the reason " + e);
                    }
                });
        return botMap;
    }
}
