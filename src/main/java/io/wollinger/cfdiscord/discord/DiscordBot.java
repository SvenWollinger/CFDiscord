package io.wollinger.cfdiscord.discord;

import io.wollinger.cfdiscord.LogManager;
import io.wollinger.cfdiscord.mc.CFDiscord;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.configuration.file.FileConfiguration;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;

public class DiscordBot {
    private JDA jda;
    private final CFDiscord cfDiscord;

    public DiscordBot(CFDiscord cfDiscord) {
        this.cfDiscord = cfDiscord;
    }

    public void start() {
        LogManager.log("Setting up discord bot", Level.INFO);

        FileConfiguration config = cfDiscord.getConfig();
        String token = config.getString("token");

        if(token.equals("EnterTokenHere")) {
            LogManager.log("Token was not setup, please enter it into the config.yml!", Level.WARNING);
            cfDiscord.stop();
            return;
        }

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setBulkDeleteSplittingEnabled(false);
        String playing = config.getString("playing");
        LogManager.log("Settings status to \"playing " + playing + "\"", Level.INFO);
        builder.setActivity(Activity.playing(playing));


        LogManager.log("Starting discord bot", Level.INFO);
        try {
            jda = builder.build();
            jda.awaitReady();
        } catch (LoginException | InterruptedException e) {
            LogManager.log("Error while starting discord bot", Level.SEVERE);
            LogManager.log("Message: " + e.getMessage(), Level.SEVERE);
            cfDiscord.stop();
            return;
        }
        LogManager.log("Discord bot started", Level.INFO);
        new DiscordCommandManager(this);

    }

    public void shutdown() {
        if(jda != null)
            jda.shutdown();
    }

    public JDA getJDA() {
        return jda;
    }

    public CFDiscord getCFDiscord() {
        return cfDiscord;
    }

}
