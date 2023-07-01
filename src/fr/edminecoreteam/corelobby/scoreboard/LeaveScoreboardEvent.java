package fr.edminecoreteam.corelobby.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.edminecoreteam.corelobby.Core;


public class LeaveScoreboardEvent implements Listener
{
    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        Core.getInstance().getScoreboardManager().onLogout(p);
    }
}