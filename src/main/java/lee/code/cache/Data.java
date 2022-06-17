package lee.code.cache;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class Data {

    protected RedisCache plugin;

    public Data(RedisCache plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    @Getter private String hostIP;
    @Getter private String hostPass;
    @Getter private int hostPort;
    @Getter private int hostTimeout;

    private void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration fileConfiguration = plugin.getConfig();

        hostIP = fileConfiguration.getString("host");
        hostPass = fileConfiguration.getString("password");
        hostPort = fileConfiguration.getInt("port");
        hostTimeout = fileConfiguration.getInt("timeout");
    }
}
