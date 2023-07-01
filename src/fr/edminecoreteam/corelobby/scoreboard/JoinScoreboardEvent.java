package fr.edminecoreteam.corelobby.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.edminecoreteam.corelobby.Core;


public class JoinScoreboardEvent implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        Core.getInstance().getScoreboardManager().onLogin(p);
    }
}