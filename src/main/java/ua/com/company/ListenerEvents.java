package ua.com.company;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListenerEvents extends ListenerAdapter {
    public static final Logger log = LogManager.getLogger(ListenerEvents.class);

    @Override
    public void onReady(ReadyEvent event) {
    log.info("Logged in as {}",event.getJDA().getSelfUser().getAsTag());
    }
}
