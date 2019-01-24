package me.KingAlterIV.test.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.lang.Enum;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class ItemCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack getItem = getNewArmorItem(player);
            ItemStack item = getArmorInfo(getItem);

            player.sendMessage(String.valueOf(item.getItemMeta()));
            player.sendMessage(String.valueOf(getItem.getItemMeta()));

            player.getInventory().addItem(getItem);
        }

        return true;
    }

    public double randomDouble(Integer min, Integer max){
        Random r = new Random();
        return (min + (max - min) * r.nextDouble());
    }

    public double randomInteger(Integer min, Integer max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public boolean getChance(int minimalChance) {
        Random random = new Random();
        return random.nextInt(99) + 1 >= minimalChance;
    }

    public ItemStack getNewArmorItem(Player p){
        ItemStack Item = null;
        LinkedList<ItemStack> items = new LinkedList<>();

        items.add(new ItemStack(Material.LEATHER_HELMET));
        items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
        items.add(new ItemStack(Material.LEATHER_LEGGINGS));
        items.add(new ItemStack(Material.LEATHER_BOOTS));

        items.add(new ItemStack(Material.GOLDEN_HELMET));
        items.add(new ItemStack(Material.GOLDEN_CHESTPLATE));
        items.add(new ItemStack(Material.GOLDEN_LEGGINGS));
        items.add(new ItemStack(Material.GOLDEN_BOOTS));

        items.add(new ItemStack(Material.CHAINMAIL_HELMET));
        items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        items.add(new ItemStack(Material.CHAINMAIL_BOOTS));

        items.add(new ItemStack(Material.IRON_HELMET));
        items.add(new ItemStack(Material.IRON_CHESTPLATE));
        items.add(new ItemStack(Material.IRON_LEGGINGS));
        items.add(new ItemStack(Material.IRON_BOOTS));

        items.add(new ItemStack(Material.DIAMOND_HELMET));
        items.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
        items.add(new ItemStack(Material.DIAMOND_LEGGINGS));
        items.add(new ItemStack(Material.DIAMOND_BOOTS));

        Collections.shuffle(items);

        Item = items.pop();
        return Item;
    }

    public ItemStack getArmorInfo(ItemStack item){

        Integer hpHighest = 0;
        Integer hpLowest = 0;
        Integer prHighest = 0;
        Integer prLowest = 0;

        ItemMeta meta = item.getItemMeta();

        if (item.getType().toString().contains("LEATHER")){
            hpHighest = 10;
            prHighest = 1;
            hpLowest = 5;
            prLowest = -1;
        }

        if (item.getType().toString().contains("GOLDEN")){
            hpHighest = 7;
            prHighest = 2;
            hpLowest = 3;
            prLowest = 0;
        }

        if (item.getType().toString().contains("CHAINMAIL")){
            hpHighest = 5;
            prHighest = 3;
            hpLowest = 1;
            prLowest = 1;
        }

        if (item.getType().toString().contains("IRON")){
            hpHighest = 3;
            prHighest = 4;
            hpLowest = -1;
            prLowest = 2;
        }

        if (item.getType().toString().contains("DIAMOND")){
            hpHighest = 1;
            prHighest = 5;
            hpLowest = -3;
            prLowest = 3;
        }

        List<String> lore = new ArrayList<>();
        String randomLevel = String.valueOf(randomInteger(1, 100));

        String randomProtection = String.format("%.2f", randomDouble(prLowest, prHighest));

        String randomHealth = String.format("%.2f", randomDouble(hpLowest, hpHighest));

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&m----------------"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Level: &e" + randomLevel));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Protection: &e" + randomProtection));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Health: &e" + randomHealth));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&m----------------"));
        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        meta.setUnbreakable(true);

        item.setItemMeta(meta);
        return item;
    }

}
