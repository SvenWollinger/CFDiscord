package io.wollinger.cfdiscord.mc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface IMCCommand {
    void run(CommandSender sender, Command cmd, String labels, String[] args);
    List<String> tabComplete(CommandSender sender, Command cmd, String labels, String[] args);
    String getLabel();
    boolean isPlayerOnly();
}
