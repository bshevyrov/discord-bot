package ua.com.company.handler;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import ua.com.company.logic.bumper.bot.IBot;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BotMessageCommand {
//    Ibot> implementing in abstract class ManuaBot and AUto bot> target (neded) classes extended abstarct clases
    public Map<String,IBot> getBotsMap() {
        ClassGraph classGraph = new ClassGraph();
        Map<String, IBot> botMap = new HashMap();
        classGraph.enableClassInfo()
                .scan()
                .getClassesImplementing(IBot.class)
                .forEach(classInfo -> {
                    for (ClassInfo subclass : classInfo.getSubclasses()) {
                        try {
                            IBot iBot = (IBot) subclass.loadClass().getDeclaredConstructor().newInstance();
                            botMap.put(iBot.getTag(), iBot);
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                                 NoSuchMethodException e) {
                            throw new RuntimeException("Unable to add IBot with the reason " + e);
                        }
                    }
                });
        return botMap;
    }
}
