package xyz.spypaste.plugin.easylock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import xyz.spypaste.plugin.easylock.config.MainConfig;
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
        getConfig().options().copyDefaults(true);
        config = new MainConfig();
        if (!config.loadConfig()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "コンフィグを読み込むことができなかったため、EasyLockプラグインを停止します。");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        registerListener();
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
