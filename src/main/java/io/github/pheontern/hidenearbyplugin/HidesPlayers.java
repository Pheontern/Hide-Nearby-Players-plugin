package io.github.pheontern.hidenearbyplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class HidesPlayers implements Runnable {
    private final HideNearbyPlugin plugin;
    public HidesPlayers(HideNearbyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : this.plugin.getServer().getOnlinePlayers()){
            for (Entity closePlayer : player.getNearbyEntities(10,10,10)){
                if (closePlayer instanceof Player){
                    ((Player) closePlayer).hidePlayer(this.plugin, player);
                }
            }
        }
    }
}
