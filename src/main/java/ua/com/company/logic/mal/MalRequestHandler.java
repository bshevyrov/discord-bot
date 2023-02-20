package ua.com.company.logic.mal;

import dev.katsute.mal4j.MyAnimeList;
import dev.katsute.mal4j.anime.Anime;
import ua.com.company.utils.PropertiesReader;

import java.util.List;

public class MalRequestHandler {
    public static void getAnimeListByTitle(String title){
        MyAnimeList mal = MyAnimeList.withClientID(PropertiesReader.getClientId());
        List<Anime> search =
                mal.getAnime()
                        .withQuery(title)
                        .withLimit(1)
                        .withOffset(1)
                        .includeNSFW(false)
                        .search();

        System.out.println(search.get(0).getSynopsis());
    }
}
