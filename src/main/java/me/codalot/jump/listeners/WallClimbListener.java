package me.codalot.jump.listeners;

import me.codalot.core.listeners.CodalotListener;
import me.codalot.core.managers.types.PlayerManager;
import me.codalot.jump.JumpCraft;
import me.codalot.jump.player.JumpPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

@SuppressWarnings("unused")
public class WallClimbListener extends CodalotListener {

//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent event) {
//        Player player = event.getPlayer();
//        JumpPlayer jPlayer = getPlayer(player);
//
//        if (jPlayer == null)
//            return;
//
//        if (canClimb(player))
//            jPlayer.climb();
//    }
//
//    private boolean canClimb(Player player) {
//        Location location = player.getLocation().clone();
//        Vector direction = player.getEyeLocation().getDirection().clone().setY(0);
//
//        Location blockLocation = location.clone().add(direction.normalize().multiply(0.43));
//
//        return JumpCraft.getSettings().getWallClimbAllowedBlocks().contains(blockLocation.getBlock().getType().toString());
//    }

    private static JumpPlayer getPlayer(Player player) {
        return (JumpPlayer) JumpCraft.getInstance().getManager(PlayerManager.class).getPlayer(player.getUniqueId());
    }

}
