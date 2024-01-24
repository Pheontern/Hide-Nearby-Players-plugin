package io.github.pheontern.hidenearbyplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.*;

public final class HideNearbyPlugin extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        // Plugin startup logic

        BukkitScheduler scheduler = this.getServer().getScheduler();

        scheduler.runTaskTimer(this, new HidesPlayers(this), 20, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));

    }

}

