package fr.edminecoreteam.corelobby.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.dragonbar.BarListener;
import fr.edminecoreteam.corelobby.profile.link.LinkData;
import fr.edminecoreteam.corelobby.profile.ranks.RankInfo;
import fr.edminecoreteam.corelobby.profile.settings.SettingInfo;

public class PlayerJoinListener implements Listener
{
    private static Core core = Core.getInstance();
    public static List<Player> candoublejump;
    public PlayerJoinListener() { candoublejump = new ArrayList<Player>(); }
    public static List<Player> getCanDoubleJump() { return PlayerJoinListener.candoublejump; }

    @SuppressWarnings("deprecation")
    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        RankInfo rankInfo  = new RankInfo(p);
        SettingInfo settingInfo = new SettingInfo(p);
        BarListener barListener = new BarListener(p);
        LinkData linkData = new LinkData(p.getUniqueId().toString());
        Location spawn = new Location(Bukkit.getWorld(core.getConfig().getString("Spawn.name")),
                (float)core.getConfig().getLong("Spawn.x"), (float)core.getConfig().getLong("Spawn.y"), (float)core.getConfig().getLong("Spawn.z"),
                (float)core.getConfig().getLong("Spawn.t"), (float)core.getConfig().getLong("Spawn.b"));
        p.teleport(spawn);
        p.setGameMode(GameMode.ADVENTURE);
        p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 1.0f, 1.0f);
        if (rankInfo.getRankID() >= 3) { getCanDoubleJump().add(p); }
        if (rankInfo.getRankModule() >= 10) { getCanDoubleJump().add(p); }
        p.setMaxHealth(20);
        LevelListeners.checkLevelAndXP(p);
        settingInfo.createSetting();
        if (settingInfo.getNightOrDay().equalsIgnoreCase("activer"))
        {
            p.setPlayerTime(14000, true);
        }
        else
        {
            p.setPlayerTime(6000, true);
        }
        p.sendTitle("§e§kII§r §6§lEDMINE§r §f§lNETWORK§r §e§kII§r", "§7Bon retour à la maison.");
        barListener.launch();

        linkData.createLink();
    }


}
