package io.wollinger.cfdiscord;

import java.util.HashMap;
import java.util.UUID;

public class UserStorage {
    private HashMap<UUID, UserInfo> uuidStorage = new HashMap<>();
    private HashMap<String, UserInfo> discordIDStorage = new HashMap<>();

    public UserStorage () {

    }

    public void addUser(UserInfo info) {
        uuidStorage.put(info.getUUID(), info);
        discordIDStorage.put(info.getDiscordID(), info);
    }

    public UserInfo getUser(UUID uuid) {
        if(uuidStorage.containsKey(uuid))
            return uuidStorage.get(uuid);
        return null;
    }

    public UserInfo getUser(String discordID) {
        if(discordIDStorage.containsKey(discordID))
            return discordIDStorage.get(discordID);
        return null;
    }

}
