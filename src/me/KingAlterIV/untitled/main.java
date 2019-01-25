package me.kingalteriv.untitled;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import me.kingalteriv.untitled.Event.ArmorChange;
import me.kingalteriv.untitled.SomewhatUsefulStuff.Color;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;

    Color Color = new Color();

    public void onEnable(){
        System.out.println(Color.ify("&aKingAlterIV's untitled plugin has enabled/loaded"));
        getServer().getPluginManager().registerEvents(new ArmorChange(), this);
    }

    public void onDisable(){
        System.out.println(Color.ify("&cKingAlterIV's untitiled plugin has disabled/unloaded"));
    }

    public main(){
        instance = this;
    }

    public static main getInstance(){
        return instance;
    }

}
