package io.github.pheontern.hidenearbyplugin.commands;

import io.github.pheontern.hidenearbyplugin.HideNearbyPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HideTickDelay implements CommandExecutor {

    private final HideNearbyPlugin plugin = HideNearbyPlugin.getPlugin();

    //Changes the tick-delay between each check for distance between players. Stored in config.yml.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args){

        if (sender instanceof Player player) {

            if (player.isOp()) {
                try {
                    int input = Integer.parseInt(args[0]);
                    if (input > 0 && input <= 20) {
                        this.plugin.tickDelay = input;
                        player.sendMessage(Component.text("Next time the server is restarted, the tick delay will change to " + input + " ticks. (Default is 3)").color(TextColor.fromHexString("#78f562")));
                        return true;
                    }
                }
                catch (Exception ignored){}
                player.sendMessage(Component.text("Enter a whole number above 0 and up to 20. (E.g. 5, 3, 11)").color(TextColor.fromHexString("#e8574f")));
            }
            else {
                player.sendMessage(Component.text("Permission denied.").color(TextColor.fromHexString("#e8574f")));
            }

        }
        return true;
    }
}
