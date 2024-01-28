package io.github.pheontern.hidenearbyplugin.commands;

import io.github.pheontern.hidenearbyplugin.HideNearbyPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HideVerticalDistance implements CommandExecutor {

    private final HideNearbyPlugin plugin = HideNearbyPlugin.getPlugin();

    //Changes the vertical hide distance. The vertical distance within which players are hidden. Stored in config.yml.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args){

        if (sender instanceof Player player) {

            if (player.isOp()) {
                try {
                    double input = Double.parseDouble(args[0]);
                    if (input > 0 && input <= 8) {
                        this.plugin.verticalHideDistance = input;
                        player.sendMessage(Component.text("Changed the vertical hide distance to " + input + " blocks.").color(TextColor.fromHexString("#78f562")));
                        return true;
                    }
                    else if (input == 0){
                        this.plugin.verticalHideDistance = input;
                        player.sendMessage(Component.text("Players will only be hidden when their hitboxes are touching with this setting.").color(TextColor.fromHexString("#f6fc49")));
                        return true;
                    }
                }
                catch (Exception ignored){}
                player.sendMessage(Component.text("Enter a decimal number between 0 and 8. (E.g. 5, 2.5, 1)").color(TextColor.fromHexString("#e8574f")));
            }
            else {
                player.sendMessage(Component.text("Permission denied.").color(TextColor.fromHexString("#e8574f")));
            }

        }
        return true;
    }
}
