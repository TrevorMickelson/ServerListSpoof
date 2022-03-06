package com.codepunisher.serverlistspoof;

import com.codepunisher.serverlistspoof.commands.SpoofReloadCmd;
import com.codepunisher.serverlistspoof.handlers.ServerListStorage;
import com.codepunisher.serverlistspoof.handlers.SpoofConfig;
import com.codepunisher.serverlistspoof.listeners.ProxyPingListener;
import net.md_5.bungee.api.plugin.Plugin;

public class ServerListSpoof extends Plugin {
    private static ServerListSpoof spoofMain;
    public static ServerListSpoof getInstance() { return spoofMain; }

    private SpoofConfig spoofConfig;
    public SpoofConfig getSpoofConfig() { return spoofConfig; }

    private final ServerListStorage storage = new ServerListStorage();
    public ServerListStorage getStorage() { return storage; }

    @Override
    public void onEnable() {
        spoofMain = this;
        spoofConfig = new SpoofConfig();
        getProxy().getPluginManager().registerListener(this, new ProxyPingListener());
        getProxy().getPluginManager().registerCommand(this, new SpoofReloadCmd());
    }
}
