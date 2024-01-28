package io.github.pheontern.hidenearbyplugin;

import io.github.pheontern.hidenearbyplugin.commands.*;
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
    private Logger logger;
    public double standardHideDistance = 2.5;
    public double verticalHideDistance = 2;
    public int tickDelay = 3;

    public static HideNearbyPlugin getPlugin(){
        return plugin;
    }

    //List with all players that have hide activated.
    public final List<Player> playersWithHide = new ArrayList<>();

    //Hashmap of chosen distances for each player, loaded from config file. UUID:s are keys.
    public final Map<String, Double> hideDistances = new HashMap<>();


    //Initializes necessary objects when plugin is loaded. Starts scheduler to check distance between players and hide them.
    @Override
    public void onEnable() {
        plugin = this;
        config = this.getConfig();
        logger = this.getLogger();

        loadConfig();
        registerEvents();
        registerCommands();
        runScheduler();

        logger.info("HideNearbyPlugin has initialized and works.");
    }

    private void loadConfig(){
        config.options().copyDefaults(true);
        this.saveDefaultConfig();
        standardHideDistance = config.getDouble("standard-hide-distance", 2.5);
        tickDelay = config.getInt("tick-delay", 3);
        verticalHideDistance = config.getDouble("vertical-distance", 2);
    }

    //If no distance is stored in the hashmap, loads it from the file. Returns the resulting distance.
    public double loadPlayerHideDistance(Player player){
        double distance;
        String uuid = player.getUniqueId().toString();
        if (!hideDistances.containsKey(uuid)) {
            ConfigurationSection hideSection = config.getConfigurationSection("hide-distances");
            if (hideSection != null) {
                distance = hideSection.getDouble(uuid, standardHideDistance);
            }
            else{
                distance = standardHideDistance;
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
        scheduler.runTaskTimer(this, new HidesPlayers(), 20, tickDelay);
    }

    private void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new OnPlayerJoinOrLeave(), this);
    }

    private void registerCommands(){
        this.getCommand("toggleHide").setExecutor(new ToggleHideNearby());
        this.getCommand("hideDistance").setExecutor(new ChangeHideDistance());
        this.getCommand("hideTickDelay").setExecutor(new HideTickDelay());
        this.getCommand("hideVerticalDistance").setExecutor(new HideVerticalDistance());
        this.getCommand("hideStandardDistance").setExecutor(new HideStandardDistance());
    }

    private void saveDataToConfig(){
        for (String uuid : hideDistances.keySet()){
            double distance = hideDistances.get(uuid);
            if (distance != standardHideDistance){
                config.set(String.format("hide-distances.%s", uuid), distance);
            }
        }

        config.set("tick-delay", tickDelay);
        config.set("standard-hide-distance", standardHideDistance);
        config.set("vertical-distance", verticalHideDistance);

        this.saveConfig();
    }

    @Override
    public void onDisable() {
        saveDataToConfig();
        logger.info("Player configurations have been saved.");
    }
}
