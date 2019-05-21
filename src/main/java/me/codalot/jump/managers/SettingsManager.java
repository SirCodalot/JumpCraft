package me.codalot.jump.managers;

import lombok.Getter;
import me.codalot.core.files.ResourceFile;
import me.codalot.core.managers.Manager;
import me.codalot.jump.JumpCraft;

@Getter
public class SettingsManager implements Manager {

    private JumpCraft plugin;
    private String file;

    private boolean doubleJumpEnabled;
    private int doubleJumpLimit;
    private double doubleJumpStrength;

    public SettingsManager(JumpCraft plugin, String file) {
        this.plugin = plugin;
        this.file = file;
    }

    @Override
    public void load() {
        ResourceFile file = new ResourceFile(plugin, this.file);

        doubleJumpEnabled = file.getBoolean("double-jump.enabled");
        doubleJumpLimit = file.getInt("double-jump.limit");
        doubleJumpStrength = file.getInt("double-jump.strength");
    }

    @Override
    public void save() {

    }
}
