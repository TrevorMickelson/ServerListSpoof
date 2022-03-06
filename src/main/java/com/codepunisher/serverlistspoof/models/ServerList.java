package com.codepunisher.serverlistspoof.models;

public class ServerList {
    private final String name;
    private final int protocolVersion;
    private final double multiplyAmount;

    public ServerList(String name, int protocolVersion, double multiplyAmount) {
        this.name = name;
        this.protocolVersion = protocolVersion;
        this.multiplyAmount = multiplyAmount;
    }

    public String getName() { return name; }
    public int getProtocolVersion() { return protocolVersion; }
    public double getMultiplyAmount() { return multiplyAmount; }
}
