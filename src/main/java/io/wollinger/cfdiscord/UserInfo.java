package io.wollinger.cfdiscord;

import java.util.UUID;

public class UserInfo {
    private UUID uuid;
    private String discordID;

    public UserInfo(UUID uuid, String discordID) {
        this.uuid = uuid;
        this.discordID = discordID;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getDiscordID() {
        return discordID;
    }
}
