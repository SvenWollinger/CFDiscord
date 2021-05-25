package io.wollinger.cfdiscord;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class LogManager {
    public static void log(String message, Level level) {
        Bukkit.getLogger().log(level, "[CFDiscord] " + message);
    }
}
