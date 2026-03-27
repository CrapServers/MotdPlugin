package noooooo.motdplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

// first time making something in java. i hate it. please send help.
public final class MotdPlugin extends JavaPlugin implements Listener {
    String[] motds = {};
    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        Logger log = Bukkit.getLogger();
        manager.registerEvents(this, this);
        File configFile = new File(this.getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            log.info("[MotdPlugin] Couldn't find config, Creating default config now.");
            List<String> configWrite = Arrays.asList("Put Your Motd(s) Here");
            this.getConfig().set("motds",configWrite);
            this.saveConfig();
        }
        List<String> templist = this.getConfig().getStringList("motds");
        motds = new String[templist.size()];
        motds = templist.toArray(motds);
    }

    @Override
    public void onDisable() {
        //System.out.println("AAAAAA");
    }
    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent event) {
        int rnd = new Random().nextInt(motds.length);
        event.setMotd(motds[rnd]);

    }
}
