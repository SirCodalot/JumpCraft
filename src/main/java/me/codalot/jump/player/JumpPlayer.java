package me.codalot.jump.player;

import lombok.Getter;
import lombok.Setter;
import me.codalot.core.files.YamlFile;
import me.codalot.core.player.CPlayer;
import me.codalot.jump.JumpCraft;
import me.codalot.jump.events.PlayerStateChangeEvent;
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
        previousLocation = getPlayer().getLocation();

        Bukkit.getScheduler().runTaskTimer(JumpCraft.getInstance(), this::update, 10, 1); // TODO use one runnable for all players
    }

    private void update() {
        updateState();

        previousLocation = getPlayer().getLocation();

        getPlayer().sendTitle("", state.toString().toLowerCase(), 0, 30, 0);
    }

    private void updateState() {
        PlayerState newState = getNewState();

        Bukkit.getPluginManager().callEvent(new PlayerStateChangeEvent(this, state, newState));

        this.state = newState;
    }

    private PlayerState getNewState() {
        if (getPlayer().isFlying())
            return PlayerState.FLY;

        if (!getPlayer().isOnGround())
            return PlayerState.FALL;

        if (getPlayer().isSneaking())
            return PlayerState.SNEAK;

        if (getPlayer().isSprinting())
            return PlayerState.SPRINT;

        if (getPlayer().getLocation().distance(previousLocation) > 0.1)
            return PlayerState.WALK;

        return PlayerState.IDLE;
    }
}
