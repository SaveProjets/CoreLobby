package fr.edminecoreteam.corelobby.listeners;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.user.profile.link.LinkData;
import fr.edminecoreteam.corelobby.user.profile.ranks.RankInfo;
import fr.edminecoreteam.corelobby.user.profile.settings.SettingInfo;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

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
        //SpawnAnimationLikeGTA.run(p);

        loadPlayerInfo(p);
        if (PlayerJoinListener.getCanDoubleJump().contains(p)) {
            p.setAllowFlight(true);
            p.setFlying(false);
        }
        else {
            p.setAllowFlight(false);
            p.setFlying(false);
        }
    }

    public static void loadPlayerInfo(Player p)
    {
        RankInfo rankInfo  = new RankInfo(p);
        SettingInfo settingInfo = new SettingInfo(p);
        LinkData linkData = new LinkData(p.getUniqueId().toString());
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
        linkData.createLink();
        Core.getInstance().getScoreboardManager().onLogin(p);
    }
}
