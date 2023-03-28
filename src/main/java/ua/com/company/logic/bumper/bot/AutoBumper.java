package ua.com.company.logic.bumper.bot;

import ua.com.company.logic.bumper.selfbot.http.impl.Bump;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class AutoBumper {
    /* public HttpRequest getResult() {
         return HttpRequest.newBuilder()
                 .POST(body)
                 .uri(uri)
                 .headers(stringRepresentationOfHeaders)//wrap
                 .build();
     }
 public void init(){

     HttpRequest request = methodIvideonRequestBuilder.getResult();
     HttpResponse<String> response = null;
     try {
         response = methodIvideonRequestBuilder.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
     } catch (IOException | InterruptedException e) {
         log.error("Make method send error",e);
     }

     }

     */
    public void init() {
        Bump bump = new Bump();
        bump.setPostData("tttwweess");
        bump.setUri(URI.create(""));
        bump.setHeader(new HashMap<>());
        HttpRequest request = bump.getResult();
        HttpResponse<String> response = null;

        try {
            response = bump.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.statusCode());
    }
}

/*
//
//        public static final TimerUtil timer = new TimerUtil();
        public static boolean first = false;
        public static void sendMessage() throws IOException {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Discord Token:");
//            final String token = reader.readLine();
            final String token = "MTA2NTI2Nzk3NTA5Mjk2OTYxMw.G7AdT4.b_eqm3Z8hAfBitjnCNiZBDtgKOFSYaZKDqDxe4";
            System.out.println("Token Entered:" + token);
            System.out.print("Enter Channel ID:");
//            final String channelID = reader.readLine();
            final String channelID = PropertiesReader.getChannel();
            System.out.println("Channel ID Entered: " + channelID);
            System.out.print("Enter Message:");
//            final String message = reader.readLine();
            final String message = "Subscribe to my channel";
//            System.out.println("Message Entered: " + message);
//            System.out.print("Enter Delay in Milliseconds:");
//            long delay = 0;
//            try {
//                delay = Long.parseLong(reader.readLine());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println("Delay Entered: " + delay);
            System.out.println("Starting!");
            final String content = "{\"content\":\"112211335\",\"nonce\":\"1089640307512836096\",\"tts\":false,\"flags\":0}";
            final byte[] json = content.getBytes(StandardCharsets.UTF_8);
            final URL url = new URL("https://discordapp.com/api/v9/channels/" + channelID + "/messages");
//            timer.reset();
//            while (true) {
            HttpClient client =    HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();
                if (!first *//*|| timer.sleep(delay)*//*) {

                }
                    final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                    connection.addRequestProperty("User-Agent", "Mozilla/4.76");
                    connection.addRequestProperty("Authorization", token);
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Content-Length", String.valueOf(json.length));
                    try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
                        writer.write(json);
                        writer.flush();
                    } finally {
                        client.send()
                        System.out.println( connection.getResponseCode());
                        System.out.println( connection.);
                        connection.disconnect();
                    }
//                    if (!first) {
//                        timer.reset();
//                        first = true;
//                    }
                    System.out.println("Sent the message \""+message+"\" to the channel " + channelID+" with the delay !");
                }
            }
        }*/
//    }


//
//request header
//        POST /api/v9/channels/967765319244480532/messages HTTP/3
//                Host: discord.com
//                User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/111.0
//                Accept: */*
//Accept-Language: en-US,en;q=0.5
//Accept-Encoding: gzip, deflate, br
//Content-Type: application/json
//Authorization: MTA2NTI2Nzk3NTA5Mjk2OTYxMw.G7AdT4.b_eqm3Z8hAfBitjnCNiZBDtgKOFSYaZKDqDxe4
//X-Super-Properties: eyJvcyI6IkxpbnV4IiwiYnJvd3NlciI6IkZpcmVmb3giLCJkZXZpY2UiOiIiLCJzeXN0ZW1fbG9jYWxlIjoiZW4tVVMiLCJicm93c2VyX3VzZXJfYWdlbnQiOiJNb3ppbGxhLzUuMCAoWDExOyBVYnVudHU7IExpbnV4IHg4Nl82NDsgcnY6MTA5LjApIEdlY2tvLzIwMTAwMTAxIEZpcmVmb3gvMTExLjAiLCJicm93c2VyX3ZlcnNpb24iOiIxMTEuMCIsIm9zX3ZlcnNpb24iOiIiLCJyZWZlcnJlciI6IiIsInJlZmVycmluZ19kb21haW4iOiIiLCJyZWZlcnJlcl9jdXJyZW50IjoiIiwicmVmZXJyaW5nX2RvbWFpbl9jdXJyZW50IjoiIiwicmVsZWFzZV9jaGFubmVsIjoic3RhYmxlIiwiY2xpZW50X2J1aWxkX251bWJlciI6MTgzODEzLCJjbGllbnRfZXZlbnRfc291cmNlIjpudWxsLCJkZXNpZ25faWQiOjB9
//X-Discord-Locale: en-US
//X-Debug-Options: bugReporterEnabled
//Content-Length: 75
//Origin: https://discord.com
//DNT: 1
//Connection: keep-alive
//Referer: https://discord.com/channels/967764101256331304/967765319244480532
//Cookie: __dcfduid=bd1805b0cc0e11ed8dd691006fc0fc36; __sdcfduid=bd1805b1cc0e11ed8dd691006fc0fc3660d7a391496fc367eabe44091ccd16931302516b73984e092ee2b3dc68d5bc56; __cfruid=052161ea679d5486e482f360e2c27e2d039666f4-1679859919; __cf_bm=tXSqv.2sh7srviw97B3k322Hgpma1fvLdiQ9WM9wB3c-1679859921-0-AYSgqLFkTkKvXHmupLUrlmzTXnn1TFdxABh/ItWtECKl7qHg28YXpVduwgMN0BFGiTCO5mA2TW7M2vKzeYeN0Nl4w0KjP23mtXzilTxY29aXILz/st84YAg3Ay9sRRW/KA==; locale=en-US; OptanonConsent=isIABGlobal=false&datestamp=Sun+Mar+26+2023+22%3A45%3A21+GMT%2B0300+(Eastern+European+Summer+Time)&version=6.33.0&hosts=&landingPath=https%3A%2F%2Fdiscord.com%2F&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1
//Sec-Fetch-Dest: empty
//Sec-Fetch-Mode: cors
//Sec-Fetch-Site: same-origin
//TE: trailers
//

//
//pewquest body
//      {"content":"112211335","nonce":"1089636607041994752","tts":false,"flags":0}
//
//
//
// curl
//curl 'https://discord.com/api/v9/channels/967765319244480532/messages' -X POST -H 'User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/111.0' -H 'Accept: */*' -H 'Accept-Language: en-US,en;q=0.5' -H 'Accept-Encoding: gzip, deflate, br' -H 'Content-Type: application/json' -H 'Authorization: MTA2NTI2Nzk3NTA5Mjk2OTYxMw.G7AdT4.b_eqm3Z8hAfBitjnCNiZBDtgKOFSYaZKDqDxe4' -H 'X-Super-Properties: eyJvcyI6IkxpbnV4IiwiYnJvd3NlciI6IkZpcmVmb3giLCJkZXZpY2UiOiIiLCJzeXN0ZW1fbG9jYWxlIjoiZW4tVVMiLCJicm93c2VyX3VzZXJfYWdlbnQiOiJNb3ppbGxhLzUuMCAoWDExOyBVYnVudHU7IExpbnV4IHg4Nl82NDsgcnY6MTA5LjApIEdlY2tvLzIwMTAwMTAxIEZpcmVmb3gvMTExLjAiLCJicm93c2VyX3ZlcnNpb24iOiIxMTEuMCIsIm9zX3ZlcnNpb24iOiIiLCJyZWZlcnJlciI6IiIsInJlZmVycmluZ19kb21haW4iOiIiLCJyZWZlcnJlcl9jdXJyZW50IjoiIiwicmVmZXJyaW5nX2RvbWFpbl9jdXJyZW50IjoiIiwicmVsZWFzZV9jaGFubmVsIjoic3RhYmxlIiwiY2xpZW50X2J1aWxkX251bWJlciI6MTgzODEzLCJjbGllbnRfZXZlbnRfc291cmNlIjpudWxsLCJkZXNpZ25faWQiOjB9' -H 'X-Discord-Locale: en-US' -H 'X-Debug-Options: bugReporterEnabled' -H 'Origin: https://discord.com' -H 'DNT: 1' -H 'Connection: keep-alive' -H 'Referer: https://discord.com/channels/967764101256331304/967765319244480532' -H 'Cookie: __dcfduid=bd1805b0cc0e11ed8dd691006fc0fc36; __sdcfduid=bd1805b1cc0e11ed8dd691006fc0fc3660d7a391496fc367eabe44091ccd16931302516b73984e092ee2b3dc68d5bc56; __cfruid=052161ea679d5486e482f360e2c27e2d039666f4-1679859919; __cf_bm=tXSqv.2sh7srviw97B3k322Hgpma1fvLdiQ9WM9wB3c-1679859921-0-AYSgqLFkTkKvXHmupLUrlmzTXnn1TFdxABh/ItWtECKl7qHg28YXpVduwgMN0BFGiTCO5mA2TW7M2vKzeYeN0Nl4w0KjP23mtXzilTxY29aXILz/st84YAg3Ay9sRRW/KA==; locale=en-US; OptanonConsent=isIABGlobal=false&datestamp=Sun+Mar+26+2023+22%3A45%3A21+GMT%2B0300+(Eastern+European+Summer+Time)&version=6.33.0&hosts=&landingPath=https%3A%2F%2Fdiscord.com%2F&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1' -H 'Sec-Fetch-Dest: empty' -H 'Sec-Fetch-Mode: cors' -H 'Sec-Fetch-Site: same-origin' -H 'TE: trailers' --data-raw '{"content":"112211335","nonce":"1089636607041994752","tts":false,"flags":0}'