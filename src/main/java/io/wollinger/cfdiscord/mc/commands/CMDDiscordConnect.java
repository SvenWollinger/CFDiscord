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
import org.bukkit.entity.Player;

import java.util.List;

public class CMDDiscordConnect implements ICommand {
    private final CFDiscord cfDiscord;

    public CMDDiscordConnect(CFDiscord cfDiscord) {
        this.cfDiscord = cfDiscord;
    }

    @Override
    public void run(CommandSender sender, Command cmd, String labels, String[] args) {
        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();

        String token = cfDiscord.TOKEN_IDENTIFIER + "=" + Utils.generateToken(70, true, true);
        cfDiscord.addPlayerToConnectionList(uuid, token);

        TextComponent tcToken = new TextComponent("<Copy token>");
        tcToken.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, token));
        tcToken.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(token.substring(0, 25) + "...")));
        tcToken.setBold(true);
        sender.spigot().sendMessage(new TextComponent("Here is your token:\n"), tcToken, new TextComponent("\nSimply dm the bot that token, it will know what to do! :)\n(This token is only usable for a minute or till you use the command again!)"));

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        cfDiscord.removePlayerFromConnectionList(uuid);
                    }
                },
                60000
        );
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
