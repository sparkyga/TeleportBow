package me.sparkyga.teleportbow;

import me.sparkyga.teleportbow.commands.GiveCommand;
import me.sparkyga.teleportbow.listeners.TeleportBowListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportBow extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        System.out.println("[TeleportBow] is on!");

        getServer().getPluginManager().registerEvents(new TeleportBowListener(this), this);
        getCommand("givebow").setExecutor(new GiveCommand(this));
    }

    @Override
    public void onDisable() {
        System.out.println("The server is off!");
    }
}
