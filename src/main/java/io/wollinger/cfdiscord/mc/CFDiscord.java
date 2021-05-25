package io.wollinger.cfdiscord.mc;

import io.wollinger.cfdiscord.discord.DiscordBot;
import io.wollinger.cfdiscord.mc.commands.CMDDiscordConnect;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CFDiscord extends JavaPlugin {
    private DiscordBot bot;
    private CommandManager commandManager;

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

}
