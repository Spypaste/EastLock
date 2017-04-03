package xyz.spypaste.plugin.easylock;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.spypaste.plugin.easylock.database.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Spypaste on 2017/04/03.
 */
public class ProtectManager {
    private static final String CREATE_TABLE_CMD =
            "CREATE TABLE IF NOT EXISTS protect_location" +
                    "(" +
                    "uuid VARCHAR(36) NOT NULL, " +
                    "loc_world VARCHAR(50) NOT NULL," +
                    "loc_x INT(3) NOT NULL," +
                    "loc_y INT(3) NOT NULL," +
                    "loc_z INT(3) NOT NULL" +
                    ");";
    private static final String GET_PROTECT_BLOCK_FOR_PLAYER =
            "SELECT uuid FROM protect_location WHERE loc_world=? AND loc_x=? AND loc_y=? AND loc_z=?";
    private static final String ADD_PROTECT_BLOCK_FOR_PLAYER =
            "INSERT INTO protect_location (uuid, loc_world , loc_x , loc_y , loc_z) VALUES (?, ?, ?, ?, ?);";

    public static boolean setup() {
        Connection connection = DataBaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_CMD);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        DataBaseManager.connectionClose();
        return true;
    }

    public static String getProtectOwner(Location loc) {
        try {
            PreparedStatement preparedStatement
                    = DataBaseManager.getConnection().prepareStatement(GET_PROTECT_BLOCK_FOR_PLAYER);
            preparedStatement.setString(1, loc.getWorld().getName());
            preparedStatement.setInt(2, loc.getBlockX());
            preparedStatement.setInt(3, loc.getBlockY());
            preparedStatement.setInt(4, loc.getBlockZ());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String uuid = resultSet.getString("uuid");
                return uuid;
            }
            preparedStatement.close();
        } catch (SQLException e) {
        } finally {
            DataBaseManager.connectionClose();
        }
        return null;
    }

    public static Boolean addProtectOwner(Location loc, Player player) {
        try {
            PreparedStatement preparedStatement
                    = DataBaseManager.getConnection().prepareStatement(ADD_PROTECT_BLOCK_FOR_PLAYER);
            preparedStatement.setString(1, player.getUniqueId().toString());
            preparedStatement.setString(2, loc.getWorld().getName());
            preparedStatement.setInt(3, loc.getBlockX());
            preparedStatement.setInt(4, loc.getBlockY());
            preparedStatement.setInt(5, loc.getBlockZ());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            DataBaseManager.connectionClose();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseManager.connectionClose();
        }
        return false;
    }
}
