package com.codepunisher.serverlistspoof.handlers;

import com.codepunisher.serverlistspoof.ServerListSpoof;
import com.codepunisher.serverlistspoof.models.ServerList;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;

public class SpoofConfig {
    private final ServerListSpoof main = ServerListSpoof.getInstance();
    private final String fileName = "config.yml";
    private final String section = "ServerLists";

    private Configuration config;
    private String prefix;
    private String permission;
    private int reportValueMax;
    private boolean bungeeReporting;

    /**
     * Creating/loading file
     *
     * Loading all config values
     */
    public SpoofConfig() {
        createFile();
        loadFile();
        loadBasicValues();
        loadServerLists();
    }

    public String getPrefix() { return prefix; }
    public String getPermission() { return permission; }
    public int getReportValueMax() { return reportValueMax; }
    public boolean isBungeeReporting() { return bungeeReporting; }

    private void createFile() {
        File dataFolder = main.getDataFolder();

        if (!dataFolder.exists())
            dataFolder.mkdir();

        File file = new File(dataFolder, fileName);

        if (!file.exists()) {
            try (InputStream in = main.getResourceAsStream(fileName)) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFile() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(main.getDataFolder(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the configuration path of
     * a specific server list
     */
    private String getServerPath(String key) {
        return section + "." + key + ".";
    }

    public void saveFile() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(main.getDataFolder(), fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This sets the basic
     * values in the config, as in
     * anything other than the server
     * lists objects
     */
    public void loadBasicValues() {
        this.prefix = config.getString("Prefix");
        this.permission = config.getString("Permission");
        this.reportValueMax = config.getInt("ReportValueMax");
        this.bungeeReporting = config.getBoolean("BungeeReporting");
    }

    /**
     * This takes the server lists from
     * the config, stores them as objects
     * in a hash map
     */
    public void loadServerLists() {
        Collection<String> serverSection = config.getSection(section).getKeys();

        if (serverSection.isEmpty())
            return;

        serverSection.forEach(key -> {
            String path = getServerPath(key);

            int protocolVersion = config.getInt(path + "ProtocolVersion");
            double multipleAmount = config.getDouble(path + "MultiplyAmount");

            // Storing object
            main.getStorage().addServerList(new ServerList(key, protocolVersion, multipleAmount));
        });
    }

    /**
     * This saves the config file
     * and then resets everything
     */
    public void reload() {
        loadFile();
        main.getStorage().clearServerLists();
        loadBasicValues();
        loadServerLists();
    }
}
