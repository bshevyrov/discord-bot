package ua.com.company.message;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import ua.com.company.Bumper;
import ua.com.company.utils.BumperConstants;

import java.util.Calendar;
import java.util.TimeZone;

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
        if (isNight()) {
            NewCircleTimerTask.sendChannelMessage(textChannel, "I don't send any PM's at night :new_moon_with_face: . Please BUMP someone!!");
            return;
        }

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

    private boolean isNight() {
        int hour = Calendar.getInstance(TimeZone.getTimeZone("Europe/Kiev"))
                .get(Calendar.HOUR_OF_DAY);
        return hour > 0 && hour < 12;
    }

}
