package ua.com.company.logic.bumper.selfbot.http.impl;

import com.google.gson.Gson;
import ua.com.company.logic.bumper.selfbot.http.HttpRequestBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestImpl  {

    public HttpClient getHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }



    private URI uri;
    private String[] stringRepresentationOfHeaders;
    private HttpRequest.BodyPublisher body;


    public HttpRequestImpl(URI uri, Map<String, String> headers, String jsonBody) {
        Gson gson = new Gson();
        this.uri = uri;
        String[] headerStr = new String[headers.size() * 2];

        List<Map.Entry<String, String>> list = new ArrayList<>(headers.entrySet());
        int x = 0;
        for (int i = 0; i < list.size(); i++) {
            headerStr[x++] = list.get(i).getKey();
            headerStr[x++] = list.get(i).getValue();
        }
        this.stringRepresentationOfHeaders = headerStr;

        this.body = HttpRequest.BodyPublishers.ofString(jsonBody);
    }

    public HttpRequest createRequest() {
        return HttpRequest.newBuilder()
                .POST(body)
                .uri(uri)
                .headers(stringRepresentationOfHeaders)//wrap
                .build();
    }
    public HttpResponse<String> getResponse(){
        HttpRequest request = createRequest();
        HttpResponse<String> response = null;

        try {
            response = getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
                return response;
    }
}
