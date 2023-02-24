package ua.com.company.selenium;

import org.apache.commons.collections4.ListUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaRunCommand {
    private JavaRunCommand() {
    }

    public static Map<String, List<Double>> init() {


        String s = null;
        List<String> bufferedReader = new ArrayList<>();
        Map<String, List<Double>> mapCoords = new HashMap<>();
        try {

            // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("/usr/bin/python3 /mnt/hdd/.IdeaProjects/discord-bot/src/main/resources/python/curves.py");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            bufferedReader = stdInput.lines().collect(Collectors.toList());
            /*while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }*/

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

//            System.exit(0);
        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
//            System.exit(-1);
        }

        List<List<String>> partition = ListUtils.partition(bufferedReader, bufferedReader.size() / 2);
        List<Double> x = partition.get(0).stream()
                .mapToDouble(Double::parseDouble)
                .boxed()
                .collect(Collectors.toList());

        List<Double> y = partition.get(1).stream()
                .mapToDouble(Double::parseDouble)
                .boxed()
                .collect(Collectors.toList());

        mapCoords.put("x", x);
        mapCoords.put("y", y);
        return mapCoords;

    }
}


