package io.github.pheontern.hidenearbyplugin.commands;

import io.github.pheontern.hidenearbyplugin.HideNearbyPlugin;
import io.github.pheontern.hidenearbyplugin.HidesPlayers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ToggleHideNearby implements CommandExecutor {

    private final HideNearbyPlugin plugin = HideNearbyPlugin.getPlugin();

    //Enables or disables hide for player that executes this command. Confirmation message is sent in chat.
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args){

        if (sender instanceof Player player) {

            int playerIndex = this.plugin.playersWithHide.indexOf(player);

            if (playerIndex == -1) {
                this.plugin.playersWithHide.add(player);
                player.sendMessage(Component.text("Players closer than " + this.plugin.hideDistance + " blocks are now hidden.").color(TextColor.fromHexString("#78f562")));
            }
            else {
                this.plugin.playersWithHide.remove(playerIndex);
                HidesPlayers.showInvisible(new ArrayList<>(this.plugin.getServer().getOnlinePlayers()), player);
                player.sendMessage(Component.text("Nearby players are no longer hidden.").color(TextColor.fromHexString("#e8574f")));
            }

        }

        return true;
    }

}
