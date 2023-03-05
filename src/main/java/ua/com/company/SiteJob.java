package ua.com.company;

import io.github.classgraph.ClassGraph;
import ua.com.company.logic.bumper.task.TimerScheduleExecute;
import ua.com.company.selenium.SiteBot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SiteJob {

    private   List<SiteBot>  getListSiteBots() {
        List<SiteBot> list = new ArrayList<>();

        ClassGraph classGraph = new ClassGraph();

        classGraph.enableClassInfo()
                .scan()
                .getClassesImplementing(SiteBot.class)
                .forEach(classInfo -> {
                    try {
                        SiteBot siteBot = (SiteBot) classInfo.loadClass().getDeclaredConstructor().newInstance();
                        list.add(siteBot);
                    } catch (RuntimeException | InvocationTargetException | InstantiationException |
                             IllegalAccessException |
                             NoSuchMethodException e) {
                        throw new RuntimeException("Unable to add Slash with the reason " + e);
                    }
                });
        return list;
    }
    public void start(){
        getListSiteBots().forEach(siteBot ->
                new TimerScheduleExecute(siteBot,siteBot.getTimeInMinToNextExecution()).startSchedule(false));
    }
}
