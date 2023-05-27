package ua.com.company.logic.bumper.bot.impl;

import java.util.ArrayList;


//{"type":2,"application_id":"302050872383242240","guild_id":"967764101256331304",
//        "channel_id":"967765319244480532","session_id":"424e8f47bf0a29ea84bd9de7825db3ba",
//        "data":{"version":"1051151064008769576","id":"947088344167366698","name":"bump","type":1,"options":[],
//        "application_command":{"id":"947088344167366698","application_id":"302050872383242240","version":"1051151064008769576",
//        "default_permission":true,"default_member_permissions":null,"type":1,"nsfw":false,"name":"bump",
//        "description":"Pushes your server to the top of all your server's tags and the front page",
//        "description_localized":"Bump this server.","dm_permission":true},
//        "attachments":[]},"nonce":"1090778403415523328"}
//



public class DiscordJSONRequest{
    private int type;
    private String application_id;
    private String guild_id;
    private String channel_id;
    private String session_id;
    private Data data;
    private String nonce;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    private static class Data{
    private String version;
    private String id;
    private String name;
    private int type;
    private ArrayList<Object> options;
    private ApplicationCommand application_command;
    private ArrayList<Object> attachments;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public ArrayList<Object> getOptions() {
            return options;
        }

        public void setOptions(ArrayList<Object> options) {
            this.options = options;
        }

        public ApplicationCommand getApplication_command() {
            return application_command;
        }

        public void setApplication_command(ApplicationCommand application_command) {
            this.application_command = application_command;
        }

        public ArrayList<Object> getAttachments() {
            return attachments;
        }

        public void setAttachments(ArrayList<Object> attachments) {
            this.attachments = attachments;
        }
    }

private static class ApplicationCommand{
    private String id;
    private String application_id;
    private String version;
    private boolean default_permission;
    private Object default_member_permissions;
    private int type;
    private boolean nsfw;
    private String name;
    private String description;
    private String description_localized;
    private boolean dm_permission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isDefault_permission() {
        return default_permission;
    }

    public void setDefault_permission(boolean default_permission) {
        this.default_permission = default_permission;
    }

    public Object getDefault_member_permissions() {
        return default_member_permissions;
    }

    public void setDefault_member_permissions(Object default_member_permissions) {
        this.default_member_permissions = default_member_permissions;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_localized() {
        return description_localized;
    }

    public void setDescription_localized(String description_localized) {
        this.description_localized = description_localized;
    }

    public boolean isDm_permission() {
        return dm_permission;
    }

    public void setDm_permission(boolean dm_permission) {
        this.dm_permission = dm_permission;
    }
}
}





