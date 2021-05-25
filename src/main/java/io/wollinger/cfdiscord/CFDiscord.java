package io.wollinger.cfdiscord;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CFDiscord extends JavaPlugin {
    private DiscordBot bot;

    @Override
    public void onEnable(){
        saveDefaultConfig();

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

}
