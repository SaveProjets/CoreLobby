package fr.edminecoreteam.corelobby.visual.holograms;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinPacketHolograms implements Listener
{
    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        HologramManager holoManage = new HologramManager(p);
        holoManage.createHolograms();
    }
}
