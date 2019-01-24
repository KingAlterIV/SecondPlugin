package me.KingAlterIV.test.Events;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import me.KingAlterIV.test.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmorEquipListener implements Listener {

    @EventHandler
    public void ArmorListen(ArmorEquipEvent e) {
        new BukkitRunnable(){

            @Override
            public void run() {
                Player player = e.getPlayer();
                ItemStack helmet = e.getPlayer().getInventory().getHelmet();
                ItemStack chestplate = e.getPlayer().getInventory().getChestplate();
                ItemStack leggings = e.getPlayer().getInventory().getLeggings();
                ItemStack boots = e.getPlayer().getInventory().getBoots();

                double helmetProtection = getProtection(helmet);
                double chestProtection = getProtection(chestplate);
                double leggingsProtection = getProtection(leggings);
                double bootsProtection = getProtection(boots);

                double removeHelmetProtection = findProtection(helmet);
                double removeChestplateProtection = findProtection(chestplate);
                double removeLeggingsProtection = findProtection(leggings);
                double removeBootsProtection = findProtection(boots);

                double helmetHealth = getHealth(helmet);
                double chestplateHealth = getHealth(chestplate);
                double leggingsHealth = getHealth(leggings);
                double bootsHealth = getHealth(boots);

                double protectionValue = helmetProtection + chestProtection + leggingsProtection + bootsProtection;
                double removeValue = removeHelmetProtection + removeChestplateProtection + removeLeggingsProtection + removeBootsProtection;

                double healthValue = helmetHealth + chestplateHealth + leggingsHealth + bootsHealth;

                player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue((removeValue*-1) + protectionValue);
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20 + healthValue);
                player.sendMessage(String.valueOf(protectionValue) + String.valueOf(chestProtection) + " " + player.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() + " " + String.valueOf(removeValue));
            }
        }.runTaskLater((Plugin) Main.getInstance(),1L);

    }

    private double getProtection(ItemStack item) {
        if (item == null) return 0;

        if (!item.hasItemMeta()) return 0;

        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) return 0;

        String line = null;
        for (String string : meta.getLore()) {
            if (string.contains("Protection: ") == true){
                line = ChatColor.stripColor(string).replace("Protection: ", "");
                return Double.parseDouble(line);
            }
        }
        return 0;
    }

    private double getHealth(ItemStack item) {
        if (item == null) return 0;

        if (!item.hasItemMeta()) return 0;

        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) return 0;

        String line = null;
        for (String string : meta.getLore()) {
            if (string.contains("Health: ") == true){
                line = ChatColor.stripColor(string).replace("Health: ", "");
                return Double.parseDouble(line);
            }
        }
        return 0;
    }

    private double findProtection(ItemStack item){
        if (item == null) return 0;

        Material material = item.getType();

        if ((material.toString().contains("LEATHER_HELMET"))){
            return 1;
        }
        if ((material.toString().contains("LEATHER_CHESTPLATE"))){
            return 3;
        }
        if ((material.toString().contains("LEATHER_LEGGINGS"))){
            return 2;
        }
        if ((material.toString().contains("LEATHER_BOOTS"))){
            return 1;
        }

        if ((material.toString().contains("GOLDEN_HELMET"))){
            return 2;
        }
        if ((material.toString().contains("GOLDEN_CHESTPLATE"))){
            return 5;
        }
        if ((material.toString().contains("GOLDEN_LEGGINGS"))){
            return 3;
        }
        if ((material.toString().contains("GOLDEN_BOOTS"))){
            return 1;
        }

        if ((material.toString().contains("CHAINMAIL_HELMET"))){
            return 2;
        }
        if ((material.toString().contains("CHAINMAIL_CHESTPLATE"))){
            return 5;
        }
        if ((material.toString().contains("CHAINMAIL_LEGGINGS"))){
            return 4;
        }
        if ((material.toString().contains("CHAINMAIL_BOOTS"))){
            return 1;
        }

        if ((material.toString().contains("IRON_HELMET"))){
            return 2;
        }
        if ((material.toString().contains("IRON_CHESTPLATE"))){
            return 6;
        }
        if ((material.toString().contains("IRON_LEGGINGS"))){
            return 5;
        }
        if ((material.toString().contains("IRON_BOOTS"))){
            return 2;
        }

        if ((material.toString().contains("DIAMOND_HELMET"))){
            return 3;
        }
        if ((material.toString().contains("DIAMOND_CHESTPLATE"))){
            return 8;
        }
        if ((material.toString().contains("DIAMOND_LEGGINGS"))){
            return 6;
        }
        if ((material.toString().contains("DIAMOND_BOOTS"))){
            return 3;
        }

        return 0;
    }

}

