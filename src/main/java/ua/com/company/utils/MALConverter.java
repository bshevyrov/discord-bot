package ua.com.company.utils;

import dev.katsute.mal4j.anime.Anime;
import dev.katsute.mal4j.anime.property.Studio;
import dev.katsute.mal4j.manga.Manga;
import dev.katsute.mal4j.manga.property.Author;
import dev.katsute.mal4j.property.Genre;
import ua.com.company.model.MALAnimeResponse;
import ua.com.company.model.MALMangaResponse;
import ua.com.company.model.MALResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class MALConverter {
    private MALConverter() {
    }

    public static MALResponse animeToMALResponse(Anime anime) {
        MALAnimeResponse response = new MALAnimeResponse();
        response.setTitle(anime.getTitle());
        response.setTitleJapanese(anime.getAlternativeTitles().getJapanese());
        response.setTitleEnglish(anime.getAlternativeTitles().getEnglish());
        response.setTitleSynonyms(anime.getAlternativeTitles().getSynonyms());
        response.setType(anime.getType().field());
//        response.setSynopsis(//TODO TRANSLATE);
        response.setSynopsis(anime.getSynopsis());
        response.setEpisodes(anime.getEpisodes());
        response.setStatus(anime.getStatus().field());

        //check if null and replace with UNKNOWN
        List<String> listGenre = new ArrayList<>();
        if (anime.getGenres()==null) {
            listGenre.add("UNKNOWN");
        } else {
            listGenre = Arrays.stream(anime.getGenres())
                    .map(Genre::getName)
                    .collect(Collectors.toList());
        }


        response.setGenres(listGenre);
        response.setStudios(Arrays.stream(anime.getStudios())
                .map(Studio::getName)
                .collect(Collectors.toList()));
        response.setEpisodeLengthInSec(anime.getAverageEpisodeLength());
        response.setPicture(anime.getMainPicture().getLargeURL());
//        response.setPicture(anime.getMainPicture().getMediumURL());
        return response;
    }
    public static MALResponse mangaToMALResponse(Manga manga) {
        MALMangaResponse response = new MALMangaResponse();
        response.setTitle(manga.getTitle());
        response.setTitleJapanese(manga.getAlternativeTitles().getJapanese());
        response.setTitleEnglish(manga.getAlternativeTitles().getEnglish());
        response.setTitleSynonyms(manga.getAlternativeTitles().getSynonyms());
        response.setType(manga.getType().field());
//        response.setSynopsis(//TODO TRANSLATE);
        response.setSynopsis(manga.getSynopsis());

        response.setVolumes(manga.getVolumes());

        response.setStatus(manga.getStatus().field());

        //check if null and replace with UNKNOWN
        List<String> listGenre = new ArrayList<>();
        if (manga.getGenres()==null) {
            listGenre.add("UNKNOWN");
        } else {
            listGenre = Arrays.stream(manga.getGenres())
                    .map(Genre::getName)
                    .collect(Collectors.toList());
        }


        response.setGenres(listGenre);
        response.setAuthors(Arrays.stream(manga.getAuthors())
                .map(author -> author.getRole() +": "+ author.getLastName() +" "+ author.getLastName())
                .collect(Collectors.toList()));
        response.setChapters(manga.getChapters());
        response.setPicture(manga.getMainPicture().getLargeURL());
//        response.setPicture(manga.getMainPicture().getMediumURL());
        return response;
    }
}
