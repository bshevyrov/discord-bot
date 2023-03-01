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

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if(event.getUser().getIdLong()==event.getMessage().getInteraction().getUser().getIdLong()){
            System.out.println("PRESS");
            page=Integer.parseInt(event.getMessage().getEmbeds().get(0).getFooter().getText());
            event.getMessage().editMessageEmbeds(new MALPagination())
        } else {
            System.out.println("INTRUDER");
        }
    }

    public Long getUser() {
        return userId;
    }

    public Long getMessage() {
        return interactionId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setInteractionId(long interactionId) {
        this.interactionId = interactionId;
    }
}
