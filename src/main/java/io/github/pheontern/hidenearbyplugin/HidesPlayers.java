package io.github.pheontern.hidenearbyplugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public final class HidesPlayers implements Runnable {
    private final HideNearbyPlugin plugin;
    public HidesPlayers(HideNearbyPlugin plugin) {
        this.plugin = plugin;
    }

    //Loops through players with hide activated as well as all online players to hide and show players correctly.
    @Override
    public void run() {
        Collection<? extends Player> onlinePlayers = this.plugin.getServer().getOnlinePlayers();

        List<Player> playersWithHide = this.plugin.playersWithHide;

        for (Player player : playersWithHide){

            List<Entity> nearbyEntities = player.getNearbyEntities(2.5,2.5,2.5);

            for (Entity nearbyEntity : nearbyEntities){

                if (nearbyEntity instanceof Player nearbyPlayer && player.canSee(nearbyPlayer)){

                    player.hidePlayer(this.plugin, nearbyPlayer);
                }
            }


            for (Player visiblePlayer : onlinePlayers){
                if (visiblePlayer != player && !player.canSee(visiblePlayer) && !nearbyEntities.contains(visiblePlayer)){
                    player.showPlayer(this.plugin, visiblePlayer);
                }
            }


        }
    }
}
