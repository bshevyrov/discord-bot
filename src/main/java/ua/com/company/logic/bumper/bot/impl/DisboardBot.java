package ua.com.company.logic.bumper.bot.impl;

import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import ua.com.company.logic.bumper.bot.BumpCommand;
import ua.com.company.logic.bumper.bot.IBot;

import java.time.Duration;

public class DisboardBot implements IBot, BumpCommand {
    @Override
    public String getTag() {
        return "DISBOARD#2760";
    }

    @Override
    public void execute(GenericMessageEvent event) {

    }

    @Override
    public String getSuccessMessage() {
        return null;
    }

    @Override
    public String getCommand() {
        return null;
    }

    @Override
    public Duration getDelay() {
        return null;
    }
}



//POST /api/v9/interactions HTTP/3
 //       Host: discord.com
   //     User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/111.0
    //    Accept: */*
/*Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate, br
Authorization: MTA2NTI2Nzk3NTA5Mjk2OTYxMw.G9Z58A.nb_Wd_fPKV2D_Es9otDXngbuqfVPWbLoVWS2DU
X-Super-Properties: eyJvcyI6IkxpbnV4IiwiYnJvd3NlciI6IkZpcmVmb3giLCJkZXZpY2UiOiIiLCJzeXN0ZW1fbG9jYWxlIjoiZW4tVVMiLCJicm93c2VyX3VzZXJfYWdlbnQiOiJNb3ppbGxhLzUuMCAoWDExOyBVYnVudHU7IExpbnV4IHg4Nl82NDsgcnY6MTA5LjApIEdlY2tvLzIwMTAwMTAxIEZpcmVmb3gvMTExLjAiLCJicm93c2VyX3ZlcnNpb24iOiIxMTEuMCIsIm9zX3ZlcnNpb24iOiIiLCJyZWZlcnJlciI6IiIsInJlZmVycmluZ19kb21haW4iOiIiLCJyZWZlcnJlcl9jdXJyZW50IjoiIiwicmVmZXJyaW5nX2RvbWFpbl9jdXJyZW50IjoiIiwicmVsZWFzZV9jaGFubmVsIjoic3RhYmxlIiwiY2xpZW50X2J1aWxkX251bWJlciI6MTg0Nzg3LCJjbGllbnRfZXZlbnRfc291cmNlIjpudWxsLCJkZXNpZ25faWQiOjB9
X-Discord-Locale: en-US
X-Debug-Options: bugReporterEnabled
Content-Type: multipart/form-data; boundary=---------------------------294809666115618223992236547114
Content-Length: 865
Origin: https://discord.com
DNT: 1
Connection: keep-alive
Referer: https://discord.com/channels/967764101256331304/967765319244480532
Cookie: __dcfduid=25bb3a00ce8611ed85efa57d61b28473; __sdcfduid=25bb3a01ce8611ed85efa57d61b28473602688c4059325d84709ecac21b75286de1b41641586e1c14c611bc065d41f9d; __cfruid=d7d6cf36ba1f540fcc711fdbe6bcc155b5ac6910-1680131107; __cf_bm=ut7gv34vRFemaKC7loPpGBOApQr3inSTVnRaW5BWE90-1680132214-0-AQ0I1zZUdvaU0G9mwwhxaSUv+fUk7SaOTCAjSjYq6XQZP8k5SMiI7VKJwyrO9LMuH+icUk9QLVK+QfIIivbXdqTk6xRGI0obik7VVTYM6E3ShlcZ84rzXGtcAk14n4a8LA==; locale=en-US; OptanonConsent=isIABGlobal=false&datestamp=Thu+Mar+30+2023+02%3A05%3A09+GMT%2B0300+(Eastern+European+Summer+Time)&version=6.33.0&hosts=&landingPath=https%3A%2F%2Fdiscord.com%2F&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1
Sec-Fetch-Dest: empty
Sec-Fetch-Mode: cors
Sec-Fetch-Site: same-origin
        }*/