package io.wollinger.cfdiscord.mc.commands;

import io.wollinger.cfdiscord.UserInfo;
import io.wollinger.cfdiscord.mc.CFDiscord;
import net.dv8tion.jda.api.entities.User;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MCCMDDiscordStatus implements IMCCommand{
    private final CFDiscord cfDiscord;

    public MCCMDDiscordStatus(CFDiscord cfDiscord) {
        this.cfDiscord = cfDiscord;
    }

    @Override
    public void run(CommandSender sender, Command cmd, String labels, String[] args) {
        UserInfo info = cfDiscord.getUserStorage().getUser(((Player)sender).getUniqueId());

        if(info != null) {
            cfDiscord.getDiscordBot().getJDA().retrieveUserById(info.getDiscordID()).queue(user -> {
                String discordName = user.getAsTag();
                String minecraftName = sender.getName();

                TextComponent tcDiscordName = new TextComponent(discordName);
                tcDiscordName.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(info.getDiscordID() + " (Click to copy)")));
                tcDiscordName.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, info.getDiscordID()));

                TextComponent tcMinecraftName = new TextComponent(minecraftName);
                tcMinecraftName.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(info.getUUID().toString() + " (Click to copy)")));
                tcMinecraftName.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, info.getUUID().toString()));

                sender.spigot().sendMessage(new TextComponent("Connected account:\nDiscord: "), tcDiscordName, new TextComponent("\nMinecraft Name: "), tcMinecraftName);
            });


        } else {
            sender.sendMessage("Not connected yet! Run /discordconnect first!");
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command cmd, String labels, String[] args) {
        return null;
    }

    @Override
    public String getLabel() {
        return "discordstatus";
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }
}
