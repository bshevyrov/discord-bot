package ua.com.company.logic.mal;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.anime.Anime;
import ua.com.company.utils.PropertiesReader;

import java.lang.reflect.Field;
import java.util.List;

public class MalRequestHandler {
    //offset відступ від краю
    // offset 2 lim 5 = 2.3.4.5.6
    public static List<Anime> getAnimeListByTitle(String title, int offset) {
        MyAnimeList mal = MyAnimeList.withClientID(PropertiesReader.getClientId());
        List<Anime> search =
                mal.getAnime()
                        .withQuery(title)
                        .withLimit(10)
                        .withOffset(offset)
                        .includeNSFW(true)
                        .search();


        return search;


    }

    public static void toStringMaker(Object o) {

        Class<? extends Object> c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            try {
                System.out.format("%n%s: %s", name, field.get(o));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
