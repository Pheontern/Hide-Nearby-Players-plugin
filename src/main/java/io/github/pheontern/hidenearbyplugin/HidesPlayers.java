package io.github.pheontern.hidenearbyplugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class HidesPlayers implements Runnable {
    private final HideNearbyPlugin plugin = HideNearbyPlugin.getPlugin();

    //Loops through players with hide activated as well as all online players to hide and show players correctly.
    @Override
    public void run() {
        List<Player> onlinePlayers = new ArrayList<>(this.plugin.getServer().getOnlinePlayers());
        List<Player> playersWithHide = new ArrayList<>(this.plugin.playersWithHide);

        for (Player player : playersWithHide){

            List<Player> distantPlayers = new ArrayList<>(onlinePlayers);
            List<Entity> nearbyEntities = new ArrayList<>(player.getNearbyEntities(this.plugin.hideDistance, this.plugin.hideDistance, this.plugin.hideDistance));

            for (Entity nearbyEntity : nearbyEntities) {

                if (nearbyEntity instanceof Player nearbyPlayer) {

                    if (player.canSee(nearbyPlayer)) {
                        player.hidePlayer(this.plugin, nearbyPlayer);
                    }
                    distantPlayers.remove(nearbyPlayer);
                }
            }
            showInvisible(distantPlayers, player);
        }
    }

    public static void showInvisible(List<Player> playersToShow, Player player){
        for (Player playerToShow : playersToShow){
            if (!player.canSee(playerToShow)) {
                player.showPlayer(HideNearbyPlugin.getPlugin(), playerToShow);
            }
        }
    }

}
