package xyz.spypaste.plugin.easylock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.spypaste.plugin.easylock.config.MainConfig;
import xyz.spypaste.plugin.easylock.database.DataBaseManager;

/**
 * Created by Spypaste on 2017/03/29.
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static MainConfig config;
    private static String prefix = "[EasyLock] ";
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        config = new MainConfig();
        if (!config.loadConfig()) {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "コンフィグを読み込むことができなかったため、EasyLockプラグインを停止します。");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (DataBaseManager.getConnection() == null){
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "データベースに接続することができなかったため、EasyLockプラグインを停止します。");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        DataBaseManager.connectionClose();
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

    public static String getPrefix() {
        return prefix;
    }
}
