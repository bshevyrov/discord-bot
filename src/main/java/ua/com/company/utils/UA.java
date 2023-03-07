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
        try {
           scanner = new Scanner(new File("src/main/java/ua/com/company/utils/ua.txt"));
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
