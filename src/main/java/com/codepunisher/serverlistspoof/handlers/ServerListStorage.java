package com.codepunisher.serverlistspoof.handlers;

import com.codepunisher.serverlistspoof.models.ServerList;

import java.util.HashSet;

public class ServerListStorage {
    private final HashSet<ServerList> serverLists = new HashSet<>();

    public void addServerList(ServerList serverList) {
        serverLists.add(serverList);
    }

    public void clearServerLists() {
        serverLists.clear();
    }

    public ServerList getServerList(int protocolVersion) {
        return serverLists.stream().filter(sl -> sl.getProtocolVersion() == protocolVersion).findAny().orElse(null);
    }
}
