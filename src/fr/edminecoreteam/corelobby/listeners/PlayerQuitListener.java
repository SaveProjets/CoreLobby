package fr.edminecoreteam.corelobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.edminecoreteam.corelobby.dragonbar.BarUtil;

public class PlayerQuitListener implements Listener
{
    @EventHandler
    private void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (PlayerJoinListener.getCanDoubleJump().contains(p)) { PlayerJoinListener.getCanDoubleJump().remove(p); }
        BarUtil.removeBar(p);
    }
}
