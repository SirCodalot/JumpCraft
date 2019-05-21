package me.codalot.jump.player;

import lombok.Getter;
import lombok.Setter;
import me.codalot.core.files.YamlFile;
import me.codalot.core.player.CPlayer;
import me.codalot.jump.JumpCraft;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

@Getter
public class JumpPlayer extends CPlayer {

    @Setter private PlayerState state;

    private Location previousLocation;

    public JumpPlayer(UUID uuid, YamlFile file) {
        super(uuid, file);

        state = PlayerState.IDLE;

        Bukkit.getScheduler().runTaskTimer(JumpCraft.getInstance(), this::updateMovement, 10, 1); // TODO use one runnable for all players
    }

    public void updateMovement() {
        if (isOffline())
            return;

        if (previousLocation == null ||
                (!getPlayer().isFlying() && !getPlayer().isSprinting() && !getPlayer().isSneaking() && getPlayer().isOnGround() &&
                        previousLocation.distance(getPlayer().getLocation()) == 0))
            state = PlayerState.IDLE;

        previousLocation = getPlayer().getLocation();

        getPlayer().sendTitle("", state.toString().toLowerCase(), 0, 30, 0);// TODO remove, this is just for debugging
    }

}
