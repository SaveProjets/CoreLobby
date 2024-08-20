package fr.edminecoreteam.corelobby.gui;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.gui.game.longgames.DeadSurvivorGui;
import fr.edminecoreteam.corelobby.gui.game.shortgames.ShortGamesGui;
import fr.edminecoreteam.corelobby.gui.game.shortgames.PaintBallGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandGame implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("game")) {
                if (args.length == 1)
                {
                    if (args[0].equalsIgnoreCase("paintball"))
                    {
                        PaintBallGui.gui(player);
                        return false;
                    }
                    if (args[0].equalsIgnoreCase("deacoudre"))
                    {
                        Core.getInstance().getGuiManager().open(player, new ShortGamesGui(Core.getInstance(), player));
                        return false;
                    }
                    if (args[0].equalsIgnoreCase("deadsurvivor"))
                    {
                        DeadSurvivorGui.gui(player);
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
