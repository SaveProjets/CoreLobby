package fr.edminecoreteam.corelobby.profile.group;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GroupTagCommand implements CommandExecutor
{

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("grouptag")) {
                if (args.length > 0)
                {
                    PartyData groupInfo = new PartyData(p.getName());
                    if (groupInfo.hasGroup())
                    {
                        int groupID = groupInfo.getGroupName();
                        if (groupInfo.getGroupLeader(groupID).contains(p.getName()))
                        {
                            String tag = String.join(" ", args);
                            groupInfo.setGroupTag(tag, groupID);
                            p.sendMessage("§b§lGroupe §8» §fVotre tag de groupe a été changer !");
                        }
                    }
                }
            }
        }
        return false;
    }
}
