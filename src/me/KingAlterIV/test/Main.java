package me.KingAlterIV.test;

import me.KingAlterIV.test.Commands.ItemCommand;
import me.KingAlterIV.test.Events.ArmorEquipListener;
import me.KingAlterIV.test.Events.DamageEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.System;

public class Main extends JavaPlugin {
    private static Main instance;

    public void onEnable() {
        System.out.println(Colorify("&aKingAlterIV's test plugin has loaded."));
        this.getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new ArmorEquipListener(), this);
        getCommand("giveItem").setExecutor(new ItemCommand());
    }

    public void onDisable(){
        System.out.println(Colorify("&cKingAlterIV's test plugin has been disabled"));
    }

    public Main() {
        instance = this;
    }

    public static Main getInstance(){
        return instance;
    }

    public String Colorify(String s){
        String string = ChatColor.translateAlternateColorCodes('&', s);
        return string;
    }


}

