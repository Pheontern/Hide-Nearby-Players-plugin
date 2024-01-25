package io.github.pheontern.hidenearbyplugin;

import io.github.pheontern.hidenearbyplugin.commands.ToggleHideNearby;
import io.github.pheontern.hidenearbyplugin.listeners.OnPlayerJoinOrLeave;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public final class HideNearbyPlugin extends JavaPlugin{

    //List with all players that have hide activated.
    public List<Player> playersWithHide = new ArrayList<>();


    //Initializes necessary objects when plugin is loaded. Starts scheduler to check distance between players and hide them.
    @Override
    public void onEnable() {
        // Plugin startup logic

        Logger logger = this.getLogger();
        logger.info("HideNearbyPlugin has initialized and works.");

        getServer().getPluginManager().registerEvents(new OnPlayerJoinOrLeave(this), this);

        getCommand("toggleHide").setExecutor(new ToggleHideNearby(this));

        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.runTaskTimer(this, new HidesPlayers(this), 20, 5);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
