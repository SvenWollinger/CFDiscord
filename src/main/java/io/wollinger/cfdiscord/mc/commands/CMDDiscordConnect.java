package io.wollinger.cfdiscord.mc.commands;

import io.wollinger.cfdiscord.Utils;
import io.wollinger.cfdiscord.mc.CFDiscord;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CMDDiscordConnect implements ICommand {
    private final CFDiscord cfDiscord;

    public CMDDiscordConnect(CFDiscord cfDiscord) {
        this.cfDiscord = cfDiscord;
    }

    @Override
    public void run(CommandSender sender, Command cmd, String labels, String[] args) {
        sender.sendMessage(Utils.generateToken(40, true, true));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command cmd, String labels, String[] args) {
        return null;
    }

    @Override
    public String getLabel() {
        return "discordconnect";
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }
}
