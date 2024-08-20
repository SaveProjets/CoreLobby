package fr.edminecoreteam.corelobby.visual.scoreboard;

import fr.edminecoreteam.corelobby.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinScoreboardEvent implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Core.getInstance().getScoreboardManager().onLogin(e.getPlayer());
    }
}