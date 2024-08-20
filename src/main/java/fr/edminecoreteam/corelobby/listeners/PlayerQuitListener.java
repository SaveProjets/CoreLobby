package fr.edminecoreteam.corelobby.listeners;

import fr.edminecoreteam.corelobby.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{
    private final static Core core = Core.getInstance();

    @EventHandler
    private void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (PlayerJoinListener.getCanDoubleJump().contains(p)) { PlayerJoinListener.getCanDoubleJump().remove(p); }
    }
}
