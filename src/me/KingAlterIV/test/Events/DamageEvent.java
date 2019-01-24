package me.KingAlterIV.test.Events;

import me.KingAlterIV.test.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DamageEvent implements Listener {

    @EventHandler
    public void Damage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
            ItemStack item = player.getInventory().getItemInMainHand();

            Double randomDamage = getDamage(item);
            String randomDamageRounded = String.format("%.2f", randomDamage);

            if (randomDamage != null) {

                e.setDamage(Double.parseDouble(randomDamageRounded));
                String hologramName = ChatColor.translateAlternateColorCodes('&', "&c" + String.valueOf(e.getDamage()));
                SpawnDamageIndicator(e.getEntity(), String.valueOf(hologramName));
            }
        }
    }

    public void SpawnDamageIndicator(Entity e, String name){
        ArmorStand damageHologram = (ArmorStand) e.getWorld().spawn(e.getLocation(), ArmorStand.class);
        damageHologram.setCustomName(name);
        damageHologram.setCustomNameVisible(true);
        damageHologram.setVisible(false);
        damageHologram.setGravity(false);
        damageHologram.setSmall(true);
        damageHologram.setInvulnerable(true);
        damageHologram.setMarker(true);
        new BukkitRunnable(){

            @Override
            public void run() {
                damageHologram.remove();
            }
        }.runTaskLater((Plugin) Main.getInstance(),40L);
    }

    private double getDamage(ItemStack item) {
        if (item == null) return 0;

        if (!item.hasItemMeta()) return 0;

        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) return 0;

        String line = null;
        for (String string : meta.getLore()) {
            if (string.contains("Damage: ") == true){
                line = ChatColor.stripColor(string).replace("Damage: ", "");
                String[] damages = line.split("[-]");

                Double minDamage = Double.parseDouble(damages[0]);
                Double maxDamage = Double.parseDouble(damages[1]);

                Random r = new Random();
                double randomDamage = minDamage + (maxDamage - minDamage) * r.nextDouble();

                return randomDamage;
            }
        }
        return 0;
    }

}
