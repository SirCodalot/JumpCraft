package me.codalot.jump.listener;

import me.codalot.core.listeners.CodalotListener;
import me.codalot.core.managers.types.PlayerManager;
import me.codalot.jump.JumpCraft;
import me.codalot.jump.player.JumpPlayer;
import me.codalot.jump.player.PlayerState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.UUID;

@SuppressWarnings("unused")
public class StateListener extends CodalotListener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        JumpPlayer jPlayer = getPlayer(player.getUniqueId());

        if (jPlayer == null)
            return;

        if (player.isFlying()) {
            jPlayer.setState(PlayerState.FLY);
            return;
        }

        if (!player.isOnGround()) {
            jPlayer.setState(PlayerState.FALL);
            return;
        }

        if (player.isSneaking()) {
            jPlayer.setState(PlayerState.SNEAK);
            return;
        }

        if (player.isSprinting()) {
            jPlayer.setState(PlayerState.SPRINT);
            return;
        }

            if (event.getFrom().distance(event.getTo()) == 0)
                jPlayer.setState(PlayerState.IDLE);
            else
                jPlayer.setState(PlayerState.WALK);
    }

    private JumpPlayer getPlayer(UUID uuid) {
        return (JumpPlayer) JumpCraft.getInstance().getManager(PlayerManager.class).getPlayer(uuid);
    }

    private JumpPlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

}
