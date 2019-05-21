package me.codalot.jump.managers;

import lombok.Getter;
import me.codalot.core.files.ResourceFile;
import me.codalot.core.managers.Manager;
import me.codalot.jump.JumpCraft;
import me.codalot.jump.objects.Modifier;

@Getter
public class SettingsManager implements Manager {

    private JumpCraft plugin;
    private String file;

    private boolean doubleJumpEnabled;
    private int doubleJumpLimit;
    private Modifier doubleJumpStrengthHorizontal;
    private Modifier doubleJumpStrengthVertical;

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
    }

    @Override
    public void save() {

    }
}
