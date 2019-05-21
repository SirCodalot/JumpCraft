package me.codalot.jump.managers;

import lombok.Getter;
import me.codalot.core.files.ResourceFile;
import me.codalot.core.managers.Manager;
import me.codalot.jump.JumpCraft;
import me.codalot.jump.objects.Modifier;

import java.util.List;

@Getter
public class SettingsManager implements Manager {

    private JumpCraft plugin;
    private String file;

    private boolean doubleJumpEnabled;
    private int doubleJumpLimit;
    private Modifier doubleJumpStrengthHorizontal;
    private Modifier doubleJumpStrengthVertical;

    private boolean wallClimbEnabled;
    private double wallClimbLimit;
    private double wallClimbSpeed;
    private List<String> wallClimbAllowedBlocks;

    public SettingsManager(JumpCraft plugin, String file) {
        this.plugin = plugin;
        this.file = file;
    }

    @Override
    public void load() {
        ResourceFile file = new ResourceFile(plugin, this.file);

        doubleJumpEnabled = file.getBoolean("double-jump.enabled");
        doubleJumpLimit = file.getInt("double-jump.limit");
        doubleJumpStrengthHorizontal = new Modifier(file.getString("double-jump.strength.horizontal"));
        doubleJumpStrengthVertical = new Modifier(file.getString("double-jump.strength.vertical"));

        wallClimbEnabled = file.getBoolean("wall-climb.enabled");
        wallClimbLimit = file.getDouble("wall-climb.limit");
        wallClimbSpeed = file.getDouble("wall-climb.speed");
        wallClimbAllowedBlocks = file.getStringList("wall-climb.allowed-blocks");
    }

    @Override
    public void save() {

    }
}
