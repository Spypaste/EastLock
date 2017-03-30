package xyz.spypaste.easylock.config;

import xyz.spypaste.easylock.config.config.MainConfig;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Spypaste on 2017/03/29.
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static MainConfig config;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        config = new MainConfig();
        config.loadConfig();
    }

    @Override
    public void onDisable() {

    }

    public void registerListener() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerListener(), this);
    }

    public static Main getInstance() {
        return instance;
    }

    public static MainConfig getMainConfig() {
        return config;
    }
}
