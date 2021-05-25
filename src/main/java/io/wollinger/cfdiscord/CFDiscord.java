package io.wollinger.cfdiscord;

import org.bukkit.plugin.java.JavaPlugin;

public class CFDiscord extends JavaPlugin {
    private DiscordBot bot;

    @Override
    public void onEnable(){
        bot = new DiscordBot();
        bot.start();
    }

    @Override
    public void onDisable() {

    }

}
