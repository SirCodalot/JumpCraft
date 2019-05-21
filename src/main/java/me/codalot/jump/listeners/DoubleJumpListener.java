package me.codalot.jump.listeners;

import me.codalot.core.listeners.CodalotListener;
import me.codalot.core.managers.types.PlayerManager;
import me.codalot.jump.JumpCraft;
import me.codalot.jump.player.JumpPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleFlightEvent;

@SuppressWarnings("unused")
public class DoubleJumpListener extends CodalotListener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        final Player player = event.getPlayer();
        final JumpPlayer jPlayer = getPlayer(player);

        if (jPlayer == null)
            return;

        if (!jPlayer.inSurvival())
            return;

        event.setCancelled(true);
        jPlayer.airJump();

    }

    private static JumpPlayer getPlayer(Player player) {
        return (JumpPlayer) JumpCraft.getInstance().getManager(PlayerManager.class).getPlayer(player.getUniqueId());
    }

}
