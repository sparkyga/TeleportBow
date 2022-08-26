package me.sparkyga.teleportbow.listeners;

import me.sparkyga.teleportbow.TeleportBow;
import me.sparkyga.teleportbow.utility.BowUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class TeleportBowListener implements Listener {

    public TeleportBowListener(TeleportBow plugin) {
        this.plugin = plugin;
        this.bowUtils = new BowUtils(plugin);
    }

    private final TeleportBow plugin;
    private final BowUtils bowUtils;


    @EventHandler
    public void onArrowLand(ProjectileHitEvent e) {

        //check to see if it was shot by the teleport bow
        if (e.getEntity().getShooter() instanceof Player p) {

            ItemStack itemInMainHand = p.getInventory().getItemInMainHand();

            if (itemInMainHand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("bow-name")))) {

                Location location = e.getEntity().getLocation();

                p.teleport(location);
                e.getEntity().remove();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("teleported-message")));
                p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);

            }

        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        if(plugin.getConfig().getBoolean("give-on-join")) {

            Player p = e.getPlayer();
            p.getInventory().addItem(bowUtils.createteleportbow());
            p.getInventory().addItem(new ItemStack(Material.ARROW, 1));

            p.sendMessage(ChatColor.AQUA + "Welcome dawg, here is a fancy shmancy teleport bow that you can play with.");

        }

    }

}


