package me.codalot.jump;

import me.codalot.core.CodalotPlugin;
import me.codalot.core.listeners.types.CPlayerListener;
import me.codalot.core.managers.types.ListenerManager;
import me.codalot.core.managers.types.PlayerManager;
import me.codalot.jump.listeners.DoubleJumpListener;
import me.codalot.jump.managers.SettingsManager;
import me.codalot.jump.player.JumpPlayer;

import java.util.ArrayList;

public class JumpCraft extends CodalotPlugin {

    @Override
    public void onEnable() {
        managers = new ArrayList<>();

        managers.add(new SettingsManager(this, "settings"));
        managers.add(new PlayerManager<>(this, JumpPlayer.class));
        managers.add(new ListenerManager(this,
                new CPlayerListener(this),
                new DoubleJumpListener()
        ));

        super.onEnable();
    }

    public static SettingsManager getSettings() {
        return getInstance().getManager(SettingsManager.class);
    }
}
