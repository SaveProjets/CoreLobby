package fr.edminecoreteam.corelobby.mainmenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.edminecoreteam.corelobby.mainmenu.longgame.DeadSurvivorGui;
import fr.edminecoreteam.corelobby.mainmenu.shortgames.DeACoudreGui;
import fr.edminecoreteam.corelobby.mainmenu.shortgames.PaintBallGui;


public class CommandGame implements CommandExecutor
{

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
                        DeACoudreGui.gui(player);
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
