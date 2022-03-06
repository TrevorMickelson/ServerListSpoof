package com.codepunisher.serverlistspoof.commands;

import com.codepunisher.serverlistspoof.ServerListSpoof;
import com.codepunisher.serverlistspoof.handlers.SpoofConfig;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * Not creating a "sls" super command because
 * only one command needs to exist
 */
public class SpoofReloadCmd extends Command {
    private final SpoofConfig spoofConfig = ServerListSpoof.getInstance().getSpoofConfig();

    public SpoofReloadCmd() {
        super("slsreload");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // Checking for permission
        if (!sender.hasPermission(spoofConfig.getPermission())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', spoofConfig.getPrefix() + "You don't have permission to execute this command!"));
            return;
        }

        spoofConfig.reload();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', spoofConfig.getPrefix() + "The config has been reloaded!"));
    }
}
