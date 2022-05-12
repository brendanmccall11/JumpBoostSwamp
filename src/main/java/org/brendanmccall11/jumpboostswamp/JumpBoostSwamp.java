package org.brendanmccall11.jumpboostswamp;

import org.bukkit.plugin.java.JavaPlugin;

public class JumpBoostSwamp extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Plugin(), this);
    }

    @Override
    public void onDisable() {
        // Do nothing
    }
}