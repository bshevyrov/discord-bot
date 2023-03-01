package ua.com.company.model;

import dev.katsute.mal4j.anime.Anime;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import ua.com.company.button.MalButtonInteraction;
import ua.com.company.utils.MALConverter;

import java.util.*;
import java.util.stream.Collectors;

public class MALPagination {
    private static Map<Long,List<MALResponse>> messageContext = new HashMap<>();
    private int currentPage;
//    private List<MALResponse> malResponses = new ArrayList<>();
    private List<Button> buttons = new ArrayList<>();

    public static Map<Long, List<MALResponse>> getMessageContext() {
        return messageContext;
    }

    public static void setMessageContext(Long messageLongId, List<MALResponse> responseList) {
        //HOW TO ADD NEW TO LIST/ NOT REWRITE
        if(messageContext.get(messageLongId)==null){
            messageContext.put(messageLongId,responseList);
        } else {
            messageContext.get(messageLongId).addAll(responseList);
        }
    }

    private MalButtonInteraction buttonPress;
//    public MALPagination(int currentPage, List<Anime> animeList) {
//        this.currentPage = currentPage;
//        malResponses.addAll(animeList.stream()
//                .map(MALConverter::animeToMALResponse)
//                .collect(Collectors.toList()));
//    }

    public MALPagination(int page) {
        this.currentPage=page;
    }


//    public MALPagination(List<Anime> animeList) {
//        this.currentPage = 1;
//        malResponses.addAll(animeList.stream()
//                .map(MALConverter::animeToMALResponse)
//                .collect(Collectors.toList()));
//    }


    private MessageEmbed createEmbeddedPage(Long messageLongId, int currentPage) {
        EmbedBuilder eb = new EmbedBuilder();
        MALResponse response = getMessageContext().get(messageLongId).get(currentPage-1);

        eb.setFooter("Page " + currentPage);
        eb.setColor(0x33cc33);
        eb.setTitle(response.getTitle());
        eb.addField("Type: ", response.getType(), true);
        eb.addField("Status: ", response.getStatus(), true);
        eb.addField("Duration: ", response.getEpisodeLengthInSec() / 60 + "min", true);
        eb.addField("Studio: ", response.getStudios().toString(), true);
        eb.addField("Genre: ", response.getGenres().toString(), false);
        eb.addField("Synonyms: ", Arrays.toString(response.getTitleSynonyms()), true);
        eb.addField("English: ", response.getTitleEnglish(), true);
        eb.addField("Japanese: ", response.getTitleJapanese(), true);
        eb.setDescription(response.getSynopsis());
        eb.setThumbnail(response.getPicture());

//        eb.build();
        return eb.build();
    }

    public MessageEmbed getMessageEmbed(Long messageLongId) {
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
