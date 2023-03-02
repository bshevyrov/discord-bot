package ua.com.company.button;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.model.MALPagination;

public class MalButtonInteraction extends ListenerAdapter {

    private int page;


    public MalButtonInteraction() {
    }

    //
    //map message id/ list anime;
    //delete after 15 min


    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        event.deferEdit().queue();
        if (event.getUser().getIdLong() == event.getMessage().getInteraction().getUser().getIdLong()) {
            page = Integer.parseInt(event.getMessage().getEmbeds().get(0).getFooter().getText().substring(5));//PAGE 1. 5 is space
            String search = event.getMessage().getEmbeds().get(0).getAuthor().getName();//SEARCH _title
//            String search = event.getMessage().getEmbeds().get(0).getFooter().getText().substring(7);//SEARCH _title

            if (event.getInteraction().getButton().getId().equals("page_next")) {
                event.getMessage().editMessageEmbeds(new MALPagination(page + 1, search).getMessageEmbed(event.getMessageIdLong()))
                        .setActionRow(new MALPagination(page + 1, search).getActiveRowButtons(event.getMessageIdLong())).queue();
            }
            if (event.getInteraction().getButton().getId().equals("page_first")) {
                event.getMessage().editMessageEmbeds(new MALPagination(1, search).getMessageEmbed(event.getMessageIdLong()))
                        .setActionRow(new MALPagination(1, search).getActiveRowButtons(event.getMessageIdLong())).queue();
            }
            if (event.getInteraction().getButton().getId().equals("page_prev")) {
                event.getMessage().editMessageEmbeds(new MALPagination(page - 1, search).getMessageEmbed(event.getMessageIdLong()))
                        .setActionRow(new MALPagination(page - 1, search).getActiveRowButtons(event.getMessageIdLong())).queue();
            }
            if (event.getInteraction().getButton().getId().equals("page_cancel")) {
                MALPagination.getMessageContext().remove(event.getMessageIdLong());
                event.getMessage().delete().queue();
            }

        }

    }
}
