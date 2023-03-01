package ua.com.company.button;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ua.com.company.model.MALPagination;

//public class MalButtonInteraction implements ButtonPress {
public class MalButtonInteraction extends ListenerAdapter {

//private long userId;
//private long interactionId;



//    public MalButtonInteraction(long userId, long interactionId) {
//        this.interactionId = interactionId;
//        this.userId = userId;
//    }
private int page;


    public MalButtonInteraction() {
    }

    //
    //map message id/ list anime;
    //delete after 15 min





    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
 event.deferEdit().queue();
        if(event.getUser().getIdLong()==event.getMessage().getInteraction().getUser().getIdLong()){
            System.out.println("PRESS");
            page=Integer.parseInt(event.getMessage().getEmbeds().get(0).getFooter().getText().substring(5));//PAGE 1. 5 is space

            if(event.getInteraction().getButton().getId().equals("page_next")){
                event.getMessage().editMessageEmbeds(new MALPagination(page+1).getMessageEmbed(event.getMessageIdLong())).queue();
            }

        } else {
            System.out.println("INTRUDER");
        }
    }

}
