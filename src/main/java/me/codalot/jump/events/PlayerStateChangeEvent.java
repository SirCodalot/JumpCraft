package me.codalot.jump.events;

import lombok.Getter;
import me.codalot.jump.player.JumpPlayer;
import me.codalot.jump.player.PlayerState;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

@Getter
public class PlayerStateChangeEvent extends PlayerEvent {

    @Getter
    private static final HandlerList handlerList = new HandlerList();

    private JumpPlayer jPlayer;

    private PlayerState from;
    private PlayerState to;

    public PlayerStateChangeEvent(JumpPlayer jPlayer, PlayerState from, PlayerState to) {
        super(jPlayer.getPlayer());

        this.jPlayer = jPlayer;
        this.from = from;
        this.to = to;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}
