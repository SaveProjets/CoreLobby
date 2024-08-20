package fr.edminecoreteam.corelobby.visual.scoreboard;

import fr.edminecoreteam.corelobby.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class LeaveScoreboardEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Core.getInstance().getScoreboardManager().onLogout(e.getPlayer());
    }
}