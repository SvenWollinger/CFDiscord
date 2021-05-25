package io.wollinger.cfdiscord.mc.commands;

import io.wollinger.cfdiscord.Utils;
import io.wollinger.cfdiscord.mc.CFDiscord;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
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
        String token = Utils.generateToken(50, true, true);

        TextComponent line1 = new TextComponent("A random long token has been generated! Click the \"Copy me\" text below to copy it into your clipboard!\n");
        TextComponent line2 = new TextComponent("<Copy token>");
        line2.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, token));
        line2.setBold(true);
        TextComponent line3 = new TextComponent("\nNow message the bot of the server using the connect command like this:\n");
        TextComponent line4 = new TextComponent("!mcconnect <token here>");

        sender.spigot().sendMessage(line1, line2, line3, line4);
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
