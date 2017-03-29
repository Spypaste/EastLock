package net.xyz.spypaste.easylock;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Spypaste on 2017/03/29.
 */
public class Main extends JavaPlugin{
    private static Main instance;
    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
}
