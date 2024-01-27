package io.github.pheontern.hidenearbyplugin;

import io.github.pheontern.hidenearbyplugin.commands.ChangeHideDistance;
import io.github.pheontern.hidenearbyplugin.commands.ToggleHideNearby;
import io.github.pheontern.hidenearbyplugin.listeners.OnPlayerJoinOrLeave;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.*;

import java.util.*;
import java.util.logging.Logger;


public final class HideNearbyPlugin extends JavaPlugin{

    private static HideNearbyPlugin plugin;
    private FileConfiguration config;
    private double defaultHideDistance;

    public static HideNearbyPlugin getPlugin(){
        return plugin;
    }

    //List with all players that have hide activated.
    public final List<Player> playersWithHide = new ArrayList<>();

    //List of chosen distances for each player, loaded from config file.
    public final Map<String, Double> hideDistances = new HashMap<>();



    //Initializes necessary objects when plugin is loaded. Starts scheduler to check distance between players and hide them.
    @Override
    public void onEnable() {
        plugin = this;
        config = this.getConfig();

        Logger logger = this.getLogger();

        loadConfig();
        registerEvents();
        registerCommands();
        runScheduler();

        logger.info("HideNearbyPlugin has initialized and works.");
    }

    private void loadConfig(){
        config.options().copyDefaults(true);
        this.saveDefaultConfig();
        defaultHideDistance = config.getDouble("default-hide-distance", 2.5);
    }

    //If no distance is stored in the hashmap, loads it from the file. Returns the resulting distance.
    public double loadPlayerHideDistance(Player player){
        double distance;
        String uuid = player.getUniqueId().toString();
        if (!hideDistances.containsKey(uuid)) {
            ConfigurationSection hideSection = config.getConfigurationSection("hide-distances");
            if (hideSection != null) {
                distance = hideSection.getDouble(uuid, defaultHideDistance);
            }
            else{
                distance = defaultHideDistance;
            }
            hideDistances.put(uuid, distance);
        }
        else {
            distance = hideDistances.get(uuid);
        }
        return distance;
    }

    private void runScheduler(){
        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.runTaskTimer(this, new HidesPlayers(), 20, 3);
    }

    private void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new OnPlayerJoinOrLeave(), this);
    }

    private void registerCommands(){
        this.getCommand("toggleHide").setExecutor(new ToggleHideNearby());
        this.getCommand("hideDistance").setExecutor(new ChangeHideDistance());
    }

    private void saveDataToConfig(){
        for (String uuid : hideDistances.keySet()){
            double distance = hideDistances.get(uuid);
            if (distance != defaultHideDistance){
                config.set(String.format("hide-distances.%s", uuid), distance);
            }
        }
    }

    @Override
    public void onDisable() {
        saveDataToConfig();
        this.saveConfig();
    }
}
