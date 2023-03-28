package ua.com.company.logic.bumper.selfbot.http;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Map;

public interface HttpRequestBuilder { //TODO or CLASS with extends
default HttpClient  getHttpClient(){
    return HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    }
//    void setPostData(HttpRequest.BodyPublisher body);
    void setPostData(String message);

    void setHeader(Map<String, String> headers);

    void setUri(URI uri);


}
