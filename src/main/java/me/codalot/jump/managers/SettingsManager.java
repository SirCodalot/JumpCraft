package me.codalot.jump.managers;

import me.codalot.core.files.ResourceFile;
import me.codalot.core.managers.Manager;
import me.codalot.jump.JumpCraft;

public class SettingsManager implements Manager {

    private JumpCraft plugin;
    private String file;

    public SettingsManager(JumpCraft plugin, String file) {
        this.plugin = plugin;
        this.file = file;
    }

    @Override
    public void load() {
        ResourceFile file = new ResourceFile(plugin, this.file);
    }

    @Override
    public void save() {

    }
}
