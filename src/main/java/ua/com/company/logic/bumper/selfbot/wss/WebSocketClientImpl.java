package ua.com.company.logic.bumper.selfbot.wss;


import com.google.gson.Gson;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class WebSocketClientImpl extends WebSocketClient {
private Gson gson = new Gson();
private SocketMessage future = new SocketMessage();
    public WebSocketClientImpl(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onMessage(String s) {
        future.setDone(true);
         future = gson.fromJson(s,SocketMessage.class);
           }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
    public SocketMessage getMessage(){
        return future;

    }
}
