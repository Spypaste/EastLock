package xyz.spypaste.plugin.easylock.database;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import xyz.spypaste.plugin.easylock.Main;
import xyz.spypaste.plugin.easylock.config.MainConfig;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Spypaste on 2017/03/29.
 */
public class DataBaseManager {
    static Connection connection = null;

    public static Connection getConnection() {
        try {
            connectionClose();
            MainConfig config = Main.getMainConfig();
            if (config.getDataBaseType().equals(DataBaseType.mysql)) {
                connection = DriverManager.getConnection("jdbc:mysql://" + config.getDataBaseHost() + ":" + Integer.toString(config.getDataBasePort()) + "/" + config.getDataBaseDbName(), config.getDataBaseUser(), config.getDataBasePassWord());
            } else if (config.getDataBaseType().equals(DataBaseType.sqlite)) {
                File file = new File(Main.getInstance().getDataFolder(), config.getDataBaseFile());
                connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + ChatColor.RED + "データベースに接続できませんでした。");
            return null;
        }
        return connection;
    }

    public static Boolean connectionClose() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }
}
