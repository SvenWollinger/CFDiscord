package io.wollinger.cfdiscord.mc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface ICommand {
    public void run(CommandSender sender, Command cmd, String labels, String[] args);
    public List<String> tabComplete(CommandSender sender, Command cmd, String labels, String[] args);
    public String getLabel();
    public boolean isPlayerOnly();
}
