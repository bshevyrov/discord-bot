//package ua.com.company.logic.bumper.selfbot.wss;
//
//import net.dv8tion.jda.internal.requests.WebSocketClient;
//import ua.com.company.logic.bumper.selfbot.http.impl.HttpRequestImpl;
//import ua.com.company.utils.PropertiesReader;
//
//import java.net.Socket;
//import java.net.URI;
//import java.net.http.HttpResponse;
//import java.net.http.WebSocket;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
//public class Heartbeat {
///*
//
//
//
//   private String status = "online";
//
//   private String usertoken = PropertiesReader.getUserToken();
//   private URI url = URI.create("https://discordapp.com/api/v9/users/@me");
//
//
//   private Map<String,String> headers = new HashMap<>(){{
//       put("Authorization",usertoken);
//       put("Content-Type","application/json");
//   }} ;
//
//    HttpResponse<String> response = new HttpRequestImpl(url,headers,"").getResponse();
//
////    userinfo = requests.get('https://discordapp.com/api/v9/users/@me', headers=headers).json()
//    public void getResp(){
//        System.out.println(response.body());
//    }
// String   username = response.body().;["username"]
//    discriminator = userinfo["discriminator"]
//    userid = userinfo["id"]
//
//    def onliner(token, status):
//*/
//
//WebSocketClientImpl webSocketClient = new WebSocketClientImpl(URI.create("wss://gateway.discord.gg/?v=9&encoding=json"));
// public void init(){
//     webSocketClient.connect();
//    answer:
//     if(webSocketClient.getMessage().isDone()==false){
//         try {
//             Thread.sleep(200);
//             break answer;
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//     }
//     try {
//         System.out.println(webSocketClient.getMessage().get().getD().getHeartbeat_interval());
//     } catch (InterruptedException e) {
//         e.printStackTrace();
//     } catch (ExecutionException e) {
//         e.printStackTrace();
//     }
// }
//
//
//  /*  ws = websocket.WebSocket()
//            ws.connect('wss://gateway.discord.gg/?v=9&encoding=json')
//    start = json.loads(ws.recv())
//    heartbeat = start['d']['heartbeat_interval']
//    auth = {"op": 2,"d": {"token": token,"properties": {"$os": "Windows 10","$browser": "Google Chrome","$device": "Windows"},"presence": {"status": status,"afk": False}},"s": None,"t": None}
//    ws.send(json.dumps(auth))
//    online = {"op":1,"d":"None"}
//    time.sleep(heartbeat / 1000)
//            ws.send(json.dumps(online))
//
//    def run_onliner():
//            os.system("clear")
//    print(f"Logged in as {username}#{discriminator} ({userid}).")
//  while True:
//    onliner(usertoken, status)
//    time.sleep(30)
//
//
//*/
//
//
//
//
//}
