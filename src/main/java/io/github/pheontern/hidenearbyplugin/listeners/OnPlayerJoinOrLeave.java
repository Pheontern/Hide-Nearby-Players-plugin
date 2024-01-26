package io.github.pheontern.hidenearbyplugin.listeners;

import io.github.pheontern.hidenearbyplugin.HideNearbyPlugin;
import io.github.pheontern.hidenearbyplugin.HidesPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

//Listener with methods for players joining and leaving.
public class OnPlayerJoinOrLeave implements Listener {

    private final HideNearbyPlugin plugin = HideNearbyPlugin.getPlugin();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    //Deactivates hide for players that log out.
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        this.plugin.playersWithHide.remove(event.getPlayer());
        HidesPlayers.showInvisible(new ArrayList<>(this.plugin.getServer().getOnlinePlayers()), event.getPlayer());
    }

}
