package ua.com.company.entity;

import java.util.List;

public class MALResponse {

    private String title;
    private String[] titleSynonyms;
    private String titleEnglish;
    private String titleJapanese;
    private String synopsis;
    private List<String> genres;
    private String type;
    private String status;
    private String pictures;
    private int episodeLengthInMillis;
    private int episodes;
    private List<String> studios;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTitleSynonyms() {
        return titleSynonyms;
    }

    public void setTitleSynonyms(String[] titleSynonyms) {
        this.titleSynonyms = titleSynonyms;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public void setTitleJapanese(String titleJapanese) {
        this.titleJapanese = titleJapanese;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public int getEpisodeLengthInMillis() {
        return episodeLengthInMillis;
    }

    public void setEpisodeLengthInMillis(int episodeLengthInMillis) {
        this.episodeLengthInMillis = episodeLengthInMillis;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public List<String> getStudios() {
        return studios;
    }

    public void setStudios(List<String> studios) {
        this.studios = studios;
    }

    public MALResponse() {
    }
}
