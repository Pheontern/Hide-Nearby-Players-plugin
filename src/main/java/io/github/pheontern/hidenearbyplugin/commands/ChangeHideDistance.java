package io.github.pheontern.hidenearbyplugin.commands;

import io.github.pheontern.hidenearbyplugin.HideNearbyPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChangeHideDistance implements CommandExecutor {

    private final HideNearbyPlugin plugin = HideNearbyPlugin.getPlugin();

    //Changes the distance below which players are hidden when hide is enabled for them.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args){

        if (sender instanceof Player player) {

            if (player.isOp()) {
                try {
                    double input = Double.parseDouble(args[0]);
                    this.plugin.hideDistance = input;
                    player.sendMessage(Component.text("Changed hide distance to " + input + " blocks.").color(TextColor.fromHexString("#78f562")));
                } catch (Exception e) {
                    player.sendMessage(Component.text("Please specify a distance. (E.g. 5, 2.5, 1)").color(TextColor.fromHexString("#e8574f")));
                }
            }
            else{
                player.sendMessage(Component.text("Permission denied.").color(TextColor.fromHexString("#e8574f")));
            }

        }

        return true;

    }

}
