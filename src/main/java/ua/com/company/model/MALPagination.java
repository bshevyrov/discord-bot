package ua.com.company.model;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import ua.com.company.logic.mal.MalRequestHandler;
import ua.com.company.utils.MALConverter;

import java.util.*;
import java.util.stream.Collectors;

public class MALPagination {
    private static Map<Long, List<MALResponse>> messageContext = new HashMap<>();
    private int currentPage;
    private String search;
    private List<Button> buttons = new ArrayList<>();

    public static Map<Long, List<MALResponse>> getMessageContext() {
        return messageContext;
    }

    public static void setMessageContext(Long messageLongId, List<MALResponse> responseList) {
        //HOW TO ADD NEW TO LIST/ NOT REWRITE
        if (messageContext.get(messageLongId) == null) {
            messageContext.put(messageLongId, responseList);
        } else {
            messageContext.get(messageLongId).addAll(responseList);
        }
    }


    public MALPagination(int page, String search) {
        this.search = search;
        this.currentPage = page;
    }


    private MessageEmbed createEmbeddedPage(Long messageLongId, int currentPage) {
        EmbedBuilder eb = new EmbedBuilder();
        MALResponse response = getMessageContext().get(messageLongId).get(currentPage - 1);

        eb.setAuthor(search);
        eb.setFooter("Page " + currentPage);
        eb.setColor(new Random().nextInt(Integer.MAX_VALUE));
        eb.setTitle(response.getTitle());
        eb.addField("Type: ", response.getType(), true);
        eb.addField("Status: ", response.getStatus(), true);
        eb.addField("\u200b", "\u200b",true);//empty field
        if (response instanceof MALAnimeResponse) {
            eb.addField("Episodes: ", String.valueOf(((MALAnimeResponse) response).getEpisodes()), true);
            eb.addField("Duration: ", ((MALAnimeResponse) response).getEpisodeLengthInSec() / 60 + "min", true);
        }
        if (response instanceof MALMangaResponse) {
            eb.addField("Volumes: ", String.valueOf(((MALMangaResponse) response).getVolumes()), true);
            eb.addField("Chapters: ", String.valueOf(((MALMangaResponse) response).getChapters()), true);
        }
        eb.addField("\u200b", "\u200b",true);//empty field
        eb.addField("Genre: ", response.getGenres().toString(), false);
        if (response instanceof MALAnimeResponse) {
            eb.addField("Studio: ", ((MALAnimeResponse) response).getStudios().toString(), false);
        }
        if (response instanceof MALMangaResponse) {
            eb.addField("Author: ", ((MALMangaResponse) response).getAuthors().toString(), false);

        }
        eb.addField("Synonyms: ", Arrays.toString(response.getTitleSynonyms()), false);
        eb.addField("English: ", response.getTitleEnglish(), false);
        eb.addField("Japanese: ", response.getTitleJapanese(), false);
        //TODO AIRING DATA
        eb.setDescription(response.getSynopsis());
        eb.setThumbnail(response.getPicture());

//        eb.build();
        return eb.build();
    }

    public MessageEmbed getMessageEmbed(Long messageLongId) {
        if (messageContext.get(messageLongId).size() - currentPage == 5) {//if there are 5 more anime in list, add new anime from mal
            if(messageContext.get(messageLongId).get(0) instanceof MALAnimeResponse){
                setMessageContext(messageLongId,
                        MalRequestHandler.getAnimeListByTitle(search, getMessageContext().get(messageLongId).size()).stream()
                                //ANIME&
                                .map(MALConverter::animeToMALResponse)
                                .collect(Collectors.toList()));
            }

            if(messageContext.get(messageLongId).get(0) instanceof MALMangaResponse){
                setMessageContext(messageLongId,
                        MalRequestHandler.getMangaListByTitle(search, getMessageContext().get(messageLongId).size()).stream()
                                //ANIME&
                                .map(MALConverter::mangaToMALResponse)
                                .collect(Collectors.toList()));
            }

           /* setMessageContext(messageLongId,
                    MalRequestHandler.getAnimeListByTitle(search, getMessageContext().get(messageLongId).size()).stream()
                           //ANIME&
                            .map(MALConverter::animeToMALResponse)
                            .collect(Collectors.toList()));*/
        }
        return createEmbeddedPage(messageLongId, currentPage);
    }

    public List<Button> getActiveRowButtons(long messageLongId) {
        buttons.clear();
        if (currentPage == 1) {
            buttons.add(Button.secondary("page_first", Emoji.fromUnicode("⏮")).asDisabled());
            buttons.add(Button.secondary("page_prev", Emoji.fromUnicode("◀")).asDisabled());
        } else {
            buttons.add(Button.secondary("page_first", Emoji.fromUnicode("⏮")));
            buttons.add(Button.secondary("page_prev", Emoji.fromUnicode("◀")));
        }
        buttons.add(Button.secondary("page_cancel", Emoji.fromUnicode("❌")));
        if (currentPage >= messageContext.get(messageLongId).size()) {
            buttons.add(Button.secondary("page_next", Emoji.fromUnicode("▶")).asDisabled());
        } else {
            buttons.add(Button.secondary("page_next", Emoji.fromUnicode("▶")));
        }
//        buttons.add(Button.secondary("page_last",Emoji.fromUnicode("⏭")));
        return buttons;
    }
}
