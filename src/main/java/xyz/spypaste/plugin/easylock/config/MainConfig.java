package xyz.spypaste.plugin.easylock.config;

import xyz.spypaste.plugin.easylock.Main;
import xyz.spypaste.plugin.easylock.database.DataBaseType;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by Spypaste on 2017/03/29.
 */
public class MainConfig {
    private FileConfiguration configuration;

    public boolean loadConfig() {
        Boolean isError = true;
        Main instance = Main.getInstance();
        configuration = instance.getConfig();
        String type = configuration.getString("database.type");
        if (type.equalsIgnoreCase("mysql")) {
            dataBaseType = DataBaseType.mysql;
            if (configuration.contains("database.mysql.host")
                    && configuration.contains("database.mysql.db")
                    && configuration.contains("database.mysql.user")
                    && configuration.contains("database.mysql.pass")) {
                dataBaseHost = configuration.getString("database.mysql.host");
                dataBaseDbName = configuration.getString("database.mysql.db");
                dataBaseUser = configuration.getString("database.mysql.user");
                dataBasePassWord = configuration.getString("database.mysql.password");
                isError = false;
            }
        } else if (type.equalsIgnoreCase("sqlite")) {
            dataBaseType = DataBaseType.sqlite;
            if (configuration.contains("database.sqlite.filename")) {
                dataBaseFile = getDataBaseFile();
                isError = false;
            }
        }
        if (configuration.contains("lang")){

        }
        return isError;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    private DataBaseType dataBaseType;

    public DataBaseType getDataBaseType() {
        return dataBaseType;
    }

    private String dataBaseHost;

    public String getDataBaseHost() {
        return dataBaseHost;
    }

    private String dataBaseDbName;

    public String getDataBaseDbName() {
        return dataBaseDbName;
    }

    private String dataBaseUser;

    public String getDataBaseUser() {
        return dataBaseUser;
    }

    private String dataBasePassWord;

    public String getDataBasePassWord() {
        return dataBasePassWord;
    }

    private String dataBaseFile;

    public String getDataBaseFile() {
        return dataBaseFile;
    }
    private String lang;

    public String getLang() {
        return lang;
    }
}
