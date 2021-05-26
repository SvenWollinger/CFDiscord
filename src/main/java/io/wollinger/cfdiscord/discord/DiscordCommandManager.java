package io.wollinger.cfdiscord.discord;

import io.wollinger.cfdiscord.UserInfo;
import io.wollinger.cfdiscord.Utils;
import io.wollinger.cfdiscord.mc.CFDiscord;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;

public class DiscordCommandManager extends ListenerAdapter {
    private final DiscordBot discordBot;

    public DiscordCommandManager(DiscordBot discordBot) {
        this.discordBot = discordBot;
        discordBot.getJDA().addEventListener(this);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        CFDiscord cfDiscord = discordBot.getCFDiscord();
        if(event.getChannelType() == ChannelType.PRIVATE) {
            String message = event.getMessage().getContentRaw();
            if(message.startsWith(CFDiscord.TOKEN_IDENTIFIER)) {
                String uuid = cfDiscord.getPlayerFromConnectionListByToken(message);
                if(uuid != null) {
                    cfDiscord.removePlayerFromConnectionList(uuid);
                    UserInfo userInfo = new UserInfo(Utils.stringToUUID(uuid), event.getAuthor().getId());
                    cfDiscord.getUserStorage().addUser(userInfo);

                    Player player = cfDiscord.getServer().getPlayer(Utils.stringToUUID(uuid));
                    if(player != null) {
                        player.sendMessage("Verified discord account <" + event.getAuthor().getAsTag() + ">");
                        event.getChannel().sendMessage("Verified minecraft account <" + player.getName() + ">").complete();
                    }
                } else {
                    event.getChannel().sendMessage("Token invalid.").complete();
                }
            }
        } else {
            String message = event.getMessage().getContentRaw();
            if(message.startsWith(CFDiscord.TOKEN_IDENTIFIER)) {
                String uuid = cfDiscord.getPlayerFromConnectionListByToken(message);
                if(uuid != null)
                    cfDiscord.removePlayerFromConnectionList(uuid);
                event.getMessage().reply("This token is now invalid! Please **ONLY** send them via direct message to me!").complete();
            }
        }
    }


}
