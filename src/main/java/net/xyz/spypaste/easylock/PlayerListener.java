package net.xyz.spypaste.easylock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Spypaste on 2017/03/29.
 */
public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock().getType() != Material.CHEST) {
            return;
        }
        Player player = e.getPlayer();
        Location location = e.getClickedBlock().getLocation();
        if (!isProtectBy(location,player)){
            e.setUseInteractedBlock(Event.Result.DENY);
            e.setUseItemInHand(Event.Result.DENY);
            return;
        }
    }
    private static Boolean isProtectBy(Location loc, Player player){
        return false;
    }
}
