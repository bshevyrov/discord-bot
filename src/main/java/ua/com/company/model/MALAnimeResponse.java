package ua.com.company.model;

import java.util.List;

public class MALAnimeResponse extends MALResponse{
    private int episodeLengthInSec;
    private int episodes;
    private List<String> studios;


    public int getEpisodeLengthInSec() {
        return episodeLengthInSec;
    }

    public void setEpisodeLengthInSec(int episodeLengthInSec) {
        this.episodeLengthInSec = episodeLengthInSec;
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
}
