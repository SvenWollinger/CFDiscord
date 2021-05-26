package io.wollinger.cfdiscord.mc;

import io.wollinger.cfdiscord.discord.DiscordBot;
import io.wollinger.cfdiscord.mc.commands.CMDDiscordConnect;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CFDiscord extends JavaPlugin {
    private DiscordBot bot;

    private final HashMap<String, String> connectionList = new HashMap<>();

    public static String TOKEN_IDENTIFIER = "CF_MC_ID";

    @Override
    public void onEnable(){
        saveDefaultConfig();
        CommandManager commandManager = new CommandManager(this);
        commandManager.addCommand(new CMDDiscordConnect(this));

        bot = new DiscordBot(this);
        bot.start();

    }

    @Override
    public void onDisable() {
        bot.shutdown();
    }

    public void stop() {
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public void addPlayerToConnectionList(String uuid, String token) {
        connectionList.put(uuid, token);
    }

    public String getPlayerFromConnectionListByUUID(String uuid) {
        if(connectionList.containsKey(uuid)) {
            return connectionList.get(uuid);
        }
        return null;
    }

    public String getPlayerFromConnectionListByToken(String token) {
        if(connectionList.containsValue(token)) {
            for (String key : connectionList.keySet()) {
                if(connectionList.get(key).equals(token))
                    return key;
            }
        }
        return null;
    }

    public void removePlayerFromConnectionList(String uuid) {
        connectionList.remove(uuid);
    }
}
