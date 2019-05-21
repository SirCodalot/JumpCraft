package me.codalot.jump.player;

import lombok.Getter;
import lombok.Setter;
import me.codalot.core.files.YamlFile;
import me.codalot.core.player.CPlayer;
import me.codalot.jump.JumpCraft;
import me.codalot.jump.events.PlayerStateChangeEvent;
import me.codalot.jump.managers.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.UUID;

@Getter
public class JumpPlayer extends CPlayer {

    @Setter private PlayerState state;

    private Location previousLocation;

    private int airJumps;

    public JumpPlayer(UUID uuid, YamlFile file) {
        super(uuid, file);

        state = PlayerState.IDLE;
        previousLocation = getPlayer().getLocation();
        airJumps = 0;

        Bukkit.getScheduler().runTaskTimer(JumpCraft.getInstance(), this::update, 10, 1); // TODO use one runnable for all players
    }

    private void update() {
        updateState();

        previousLocation = getPlayer().getLocation();

        if (getPlayer().isOnGround()) {
            airJumps = 0;
            getPlayer().setAllowFlight(true);
        }

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

    public boolean inSurvival() {
        return getPlayer().getGameMode() == GameMode.SURVIVAL || getPlayer().getGameMode() == GameMode.ADVENTURE;
    }

    public void airJump() {
        SettingsManager settings = JumpCraft.getSettings();

        if (settings.getDoubleJumpLimit() != 0 && airJumps >= settings.getDoubleJumpLimit())
            return;

        Vector velocity = getPlayer().getVelocity();
        velocity.setX(settings.getDoubleJumpStrengthHorizontal().modify(velocity.getX()));
        velocity.setZ(settings.getDoubleJumpStrengthHorizontal().modify(velocity.getZ()));
        velocity.setY(settings.getDoubleJumpStrengthVertical().modify(velocity.getY()));
        getPlayer().setVelocity(velocity);

        airJumps++;

        if (airJumps == settings.getDoubleJumpLimit())
            getPlayer().setAllowFlight(false);
    }
}
