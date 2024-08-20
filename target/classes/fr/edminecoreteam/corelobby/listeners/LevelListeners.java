package fr.edminecoreteam.corelobby.listeners;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.user.AccountInfo;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class LevelListeners
{
    private static Core api = Core.getInstance();

    public static void checkLevelAndXP(Player p)
    {
        AccountInfo accountInfo  = new AccountInfo(p);
        new BukkitRunnable() {
            int t = 0;
            public void run() {

                p.setLevel(accountInfo.getLevel());

                ++t;
                if (t == 50) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)api, 0L, 50L);
    }
}
