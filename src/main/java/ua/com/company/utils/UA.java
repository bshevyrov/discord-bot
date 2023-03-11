package ua.com.company.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UA
{

    public String getUA(){
        Scanner scanner = null;
        String path;
        if (System.getenv("SERVER_ENVIRONMENT").equals("DEV")) {
            path = "src/main/resources/ua.txt";

        } else {//System.getenv("SERVER_ENVIRONMENT").equals("PROD")
            path = "ua.txt";
        }
        try {
           scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        while (scanner.hasNext()){
list.add(scanner.nextLine());
}
        System.out.println(list.size());
        return list.get(new Random().nextInt(list.size()-1));
    }
}
