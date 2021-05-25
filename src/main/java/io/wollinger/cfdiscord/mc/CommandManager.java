package io.wollinger.cfdiscord.mc;

import io.wollinger.cfdiscord.mc.commands.ICommand;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private final CFDiscord cfDiscord;
    private HashMap<String, ICommand> commands = new HashMap<>();

    public CommandManager(CFDiscord cfDiscord) {
        this.cfDiscord = cfDiscord;
    }

    public void addCommand(ICommand command) {
        cfDiscord.getCommand(command.getLabel()).setExecutor(this);
        cfDiscord.getCommand(command.getLabel()).setTabCompleter(this);
        commands.put(command.getLabel(), command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender && commands.get(command.getName()).isPlayerOnly()) {
            //TODO: Add proper text
            return true;
        }
        if(commands.containsKey(command.getName()))
            commands.get(command.getName()).run(sender, command, label, args);
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
