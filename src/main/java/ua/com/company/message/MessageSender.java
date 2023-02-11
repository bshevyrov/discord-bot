package ua.com.company.message;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import ua.com.company.Bumper;
import ua.com.company.utils.BumperConstants;

public class MessageSender extends Thread {
    TextChannel textChannel;
    Bumper.Entity bumper;


    public MessageSender(TextChannel textChannel, Bumper.Entity bumper) {
        this.textChannel = textChannel;
        this.bumper = bumper;
    }

    @Override
    public void run() {
        NewCircleTimerTask.setMessageSenderInterrupted(true);
        NewCircleTimerTask.sendChannelMessage(textChannel, "Send PM to " + bumper.getUsername());
        NewCircleTimerTask.sendPrivateMessage(bumper, textChannel, BumperConstants.PRIVATE_MESSAGE);
        try {
            sleep(BumperConstants.DELAY_BEFORE_SEND_ANOTHER_MESSAGE);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if (isAlive()) {
            NewCircleTimerTask.setMessageSenderInterrupted(false);
            NewCircleTimerTask.sendChannelMessage(textChannel, bumper.getUsername() + " dont answer(\nChoose another Member.");
        }
    }
}
