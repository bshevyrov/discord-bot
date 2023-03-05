package ua.com.company.model;

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
    private String picture;


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
      //  synopsis=synopsis.replace("\\n","\n");
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public MALResponse() {
    }
}
