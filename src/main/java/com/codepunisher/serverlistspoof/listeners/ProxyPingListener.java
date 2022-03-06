package com.codepunisher.serverlistspoof.listeners;

import com.codepunisher.serverlistspoof.ServerListSpoof;
import com.codepunisher.serverlistspoof.models.ServerList;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * This works by updating the player count
 * based on what gets pinged, if a configured
 * ServerList protocol version pings the server
 * then the value is updated, but if anything else
 * pings the server (like a player) it's reset to normal
 *
 * This will always be off-sync, which gives the illusion
 * to the server list sites that there are more players
 * whilst not affecting the in game server list
 */
public class ProxyPingListener implements Listener {
    private final ServerListSpoof main = ServerListSpoof.getInstance();

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        ServerPing.Players players = event.getResponse().getPlayers();
        int protocolVersion = event.getConnection().getVersion();
        ServerList serverList = main.getStorage().getServerList(protocolVersion);

        // Sending bungee report
        if (main.getSpoofConfig().isBungeeReporting())
            sendBungeeReport(protocolVersion);

        // Displaying fake information to server list site
        if (serverList != null) {
            displayInformation(players, serverList.getMultiplyAmount());
            return;
        }

        // Displaying normal information to player
        displayInformation(players, 0);
    }

    /**
     * Displays online player count info
     */
    private void displayInformation(ServerPing.Players players, double multiply) {
        int onlineCount = main.getProxy().getOnlineCount();

        if (multiply > 0)
            onlineCount = (int) Math.ceil(onlineCount * multiply);

        players.setOnline(onlineCount);
    }

    /**
     * Sends the protocol version
     * to the bungee console
     *
     * Only sending if version is less or
     * equal to the max configured value
     */
    private void sendBungeeReport(int protocolVersion) {
        if (protocolVersion <= main.getSpoofConfig().getReportValueMax())
            System.out.println(ChatColor.translateAlternateColorCodes('&', main.getSpoofConfig().getPrefix() + "Protocol Version: " + protocolVersion));
    }
}
