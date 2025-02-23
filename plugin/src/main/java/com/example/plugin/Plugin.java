package com.example.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.example.plugin.Initializers.CommandInit;
import com.example.plugin.Initializers.EventInit;


public class Plugin extends JavaPlugin {
    public static JavaPlugin plugin;
    
    @Override
    public void onEnable() {
        Plugin.plugin = this;
        getLogger().info("Plugin is Starting!");
        this.saveDefaultConfig();
        getLogger().info("Config Loaded!");
        CommandInit.init();
        getLogger().info("Registered commands!");
        EventInit.init();
        getLogger().info("Registered Events!");

    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin is Disabling!");
    }
}
