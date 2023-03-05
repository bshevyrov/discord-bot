package ua.com.company;


import okhttp3.Call;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;

public class TESTAPP {


    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        List<Callable<Integer>> list = new ArrayList<>();
        list.add(() -> 5);
        list.add(() -> 4);
        list.add(() -> 3);
        list.add(() -> 2);
        list.add(() -> 1);

//        List<Future<Integer>> ans= executorService.invokeAll(list);

       Map<String,Future<Integer>> map= new Hashtable<>();
       map.put("five", executorService.submit(list.get(0)));
       map.put("four", executorService.submit(list.get(1)));
       map.put("three", executorService.submit(list.get(2)));
       map.put("two", executorService.submit(list.get(3)));
       map.put("one", executorService.submit(list.get(4)));


//       ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
       ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
       scheduledExecutorService.schedule(() -> {
           scheduledExecutorService.schedule(()-> System.out.println("NEW SCHEDULE"),10,TimeUnit.SECONDS);
           System.out.println("create new schedule");
       }, 1, TimeUnit.SECONDS);
int time=2;
       scheduledExecutorService.schedule(() -> System.out.println("change to 15 secsec"),time,TimeUnit.SECONDS);
       scheduledExecutorService.schedule(() -> System.out.println("5 sec"),1,TimeUnit.SECONDS);
       scheduledExecutorService.schedule(() -> System.out.println("4 sec"),1,TimeUnit.SECONDS);
       scheduledExecutorService.schedule(() -> System.out.println("3 sec"),3,TimeUnit.SECONDS);
       scheduledExecutorService.schedule(() -> System.out.println("2 sec"),1,TimeUnit.SECONDS);
       scheduledExecutorService.schedule(() -> System.out.println("1 sec"),1,TimeUnit.SECONDS);
        System.out.println("end");
        time=15;
    }
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        // form parameters
//        Map<Object, Object> data = new HashMap<>();
//        data.put("translate_to", "uk");
//        data.put("translate_from", "");
//        data.put("text", "Tucked away in the English countryside lies the ominous manor of the Phantomhives, a family which has established itself as the cold and ruthless \"Queen's Watchdog\" as well as the head of London's criminal underground. After a tragedy leaves the Earl and his wife dead, many are shocked when their son, a young boy named Ciel, claims his place as the new Earl of the Phantomhive house. At first, many perceive him only as a child surrounded by a few eccentric servants. But they soon begin to realize that it is foolish to meddle with Ciel and his demonic butler Sebastian.\\nTaking place at the end of the 19th century, Kuroshitsuji follows these two as they face countless mysteries and dangers that plague England and threaten the Queen, uncovering the truth about what really happened to Ciel's parents in the process.\n");
//        data.put("TTK","2078.445053");
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .POST(ofFormData(data))
//                .uri(URI.create("https://www.m-translate.it/api/2/translate"))
//                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
//                .header("Accept",  "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
//                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        // print status code
//        System.out.println(response.statusCode());
//
//        // print response body
//        System.out.println( org.apache.commons.lang3.StringEscapeUtils.unescapeJava( response.body()));
//
//    }
//
//    // Sample: 'password=123&custom=secret&username=abc&ts=1570704369823'
//    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
//        var builder = new StringBuilder();
//        for (Map.Entry<Object, Object> entry : data.entrySet()) {
//            if (builder.length() > 0) {
//                builder.append("&");
//            }
//            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
//            builder.append("=");
//            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
//        }
//        return HttpRequest.BodyPublishers.ofString(builder.toString());
//    }


}


//
//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("https://ai-translate.p.rapidapi.com/translate"))
//            .header("content-type", "application/json")
//            .header("X-RapidAPI-Key", "a3856789c1msh52a15d444883610p192f6ajsnab9eae623474")
//            .header("X-RapidAPI-Host", "ai-translate.p.rapidapi.com")
//            .method("POST", HttpRequest.BodyPublishers.ofString("{
//                    \"texts\": [
//                    \"hello. world!\"
//    ],
//            \"tl\": \"uk\",
//            \"sl\": \"en\"
//            }"))
//            .build();
//            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());


//{
//        "code": 200,
//        "texts": [
//        "你好。 世界！",
//        "<b>你好。 谷歌！</b>",
//        "<i>你好。 迅速的！</i>"
//        ],
//        "tl": "zh"
//        }