package ua.com.company.logic.bumper.selfbot.http.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.company.logic.bumper.selfbot.http.HttpRequestBuilder;
import ua.com.company.utils.PropertiesReader;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bump implements HttpRequestBuilder {
    private HttpRequest.BodyPublisher body;
    private URI uri;
    //    private Map<String, String> headers;
    private String[] stringRepresentationOfHeaders;
//    Gson gson = new Gson();
ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setPostData(String message)  {
        try {
            this.body=  HttpRequest.BodyPublishers.ofString(
                    mapper.writeValueAsString(new DiscordHttpMessage(message)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//                gson.toJson(new DiscordHttpMessage(message)));

    }

    @Override
    public void setHeader(Map<String, String> headerss) {
        Map<String, String> headers = new HashMap<>(){{
            put("Authorization",
                    PropertiesReader.getUserToken());
            put("Content-Type","application/json");
        }};

        String[] headerStr = new String[headers.size() * 2];
//        String[] headerStr = new String[headerss.size() * 2];
        List<Map.Entry<String, String>> list = new ArrayList<>(headers.entrySet());
        int x = 0;
        for (int i = 0; i < list.size(); i++) {
            headerStr[x++] = list.get(i).getKey();
            headerStr[x++] = list.get(i).getValue();
        }
        this.stringRepresentationOfHeaders = headerStr;
    }

    @Override
    public void setUri(URI uri) {
        this.uri = URI.create("https://discord.com/api/v9/channels/1064965635311939727/messages");
//        this.uri = uri;
    }

    public HttpRequest getResult() {
        return HttpRequest.newBuilder()
                .POST(body)
                .uri(uri)
                .headers(stringRepresentationOfHeaders)//wrap
                .build();
    }

    class DiscordHttpMessage {
        private String content;
        private int flag;
        private String nonce;
        private boolean tts;

        public DiscordHttpMessage(String content) {
            this.content = content;
        }
    }
}
