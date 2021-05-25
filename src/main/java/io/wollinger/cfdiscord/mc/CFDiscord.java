package io.wollinger.cfdiscord.mc;

import io.wollinger.cfdiscord.discord.DiscordBot;
import io.wollinger.cfdiscord.mc.commands.CMDDiscordConnect;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CFDiscord extends JavaPlugin {
    private DiscordBot bot;
    private CommandManager commandManager;

    private HashMap<String, String> connectionList = new HashMap<String, String>();

    public static String TOKEN_IDENTIFIER = "CF_MC_ID";

    @Override
    public void onEnable(){
        saveDefaultConfig();
        commandManager = new CommandManager(this);
        commandManager.addCommand(new CMDDiscordConnect(this));

        bot = new DiscordBot();
        bot.start(this);
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

    public String getPlayerFromConnectionList(String uuid) {
        if(connectionList.containsKey(uuid)) {
            return connectionList.get(uuid);
        }
        return null;
    }

    public void removePlayerFromConnectionList(String uuid) {
        connectionList.remove(uuid);
    }
}
