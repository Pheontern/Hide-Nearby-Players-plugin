package io.github.pheontern.hidenearbyplugin.listeners;

import io.github.pheontern.hidenearbyplugin.HideNearbyPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

//Listener with methods for players joining and leaving.
public class OnPlayerJoinOrLeave implements Listener {

    private final HideNearbyPlugin plugin;

    public OnPlayerJoinOrLeave(HideNearbyPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    //Deactivates hide for players that log out.
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        plugin.playersWithHide.remove(event.getPlayer());
    }

}
