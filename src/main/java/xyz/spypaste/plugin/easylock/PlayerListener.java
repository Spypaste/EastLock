package xyz.spypaste.plugin.easylock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

/**
 * Created by Spypaste on 2017/03/29.
 */
public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock().getType() != Material.CHEST) {
            return;
        }
        Location location = e.getClickedBlock().getLocation();
        Player player = e.getPlayer();
        String owner = ProtectManager.getProtectOwner(location);
        if (owner != null && !owner.equalsIgnoreCase(player.getUniqueId().toString())){
            e.setUseInteractedBlock(Event.Result.DENY);
            e.setUseItemInHand(Event.Result.DENY);
            String name = Main.getInstance().getServer().getOfflinePlayer(UUID.fromString(owner)).getName();
            if (name == null) name = "unknown";
            player.sendMessage(ChatColor.YELLOW + "This chest is protected by " + name);
            return;
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (!event.getBlock().getType().equals(Material.CHEST)){
            return;
        }
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (ProtectManager.addProtectOwner(event.getBlock().getLocation(), event.getPlayer())) {
                    player.sendMessage(ChatColor.YELLOW + "This chest is protected by you!");
                } else {
                    player.sendMessage(ChatColor.RED + "Could not protect due to server error!");
                }
            }
        });
    }
}
