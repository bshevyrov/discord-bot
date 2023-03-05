package ua.com.company.logic.bumper.task;

import io.github.classgraph.ClassGraph;
import ua.com.company.selenium.SiteBot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SiteScheduleExecute {
   private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    private List<SiteBot> getListSiteBots() {
        List<SiteBot> list = new ArrayList<>();

        ClassGraph classGraph = new ClassGraph();

        classGraph.enableClassInfo()
                .scan()
                .getSubclasses(SiteBot.class)
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

    public void init(){
      getListSiteBots().forEach(siteBot -> executorService.schedule(siteBot,siteBot.getTimeInMinToNextExecution(),TimeUnit.MINUTES));
    }
}

