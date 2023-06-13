package ua.com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BannerWriter {
    public void method(String avatarUrl,String memberName,String memberStatus,String messageCount, String minutesCount) throws IOException {
        int DISCORD_AVATAR_SIZE = 128;

        BufferedImage background = ImageIO.read(
                new URL("https://media.discordapp.net/attachments/1086227895791398953/1102713165852188822/Untitled_Artwork.png"));


//        BufferedImage avatar = ImageIO.read(
//                new URL("https://cdn.discordapp.com/avatars/232929050652311553/d28baec466a01e42a92118d175e5246e.png"));
BufferedImage avatar = ImageIO.read(
                new URL(avatarUrl));


        BufferedImage output = new BufferedImage(DISCORD_AVATAR_SIZE, DISCORD_AVATAR_SIZE, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillOval(0, 0, DISCORD_AVATAR_SIZE, DISCORD_AVATAR_SIZE);

            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(avatar, 0, 0, null);
        } finally {
            g2.dispose();
        }

//        return output;


        Graphics g = background.getGraphics();

        //add avatar to background
        g.drawImage(output, 75, 239, 200, 200, null);
        //add name
        g.setFont(new Font("TimesRoman", Font.PLAIN, 33));
        long LENGTH_OF_NAME = 14L;
        if (memberName.length() > LENGTH_OF_NAME) {
            StringBuilder builder = new StringBuilder(memberName.substring(0, 13));
            builder.append("...");
            memberName = builder.toString();
        }
        memberName = memberName.chars()
                .mapToObj(c -> (char) c)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

        g.drawString(memberName, 333, 333);


        //add status
        g.setFont(new Font("TimesRoman", Font.PLAIN, 33));
        if (memberStatus.length() > LENGTH_OF_NAME) {
            StringBuilder builder = new StringBuilder(memberStatus.substring(0, 13));
            builder.append("...");
            memberStatus = builder.toString();
        }
        memberStatus = memberStatus.chars()
                .mapToObj(c -> (char) c)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

        g.drawString(memberStatus, 333, 400);

        //add voice minutes
        g.drawString(minutesCount+"хв", 750, 250);


        //add message count
        g.drawString(messageCount, 753, 445);


        ImageIO.write(background, "png", Files.newOutputStream(Paths.get("src/main/resources/1111.png")));


    }

}
