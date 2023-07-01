package fr.edminecoreteam.corelobby.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.corelobby.Core;

public class CustomSounds
{
    private static Core core = Core.getInstance();

    public static void openShopSound(final Player p)
    {
        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (!p.isOnline()) { cancel(); }


                if (t == 0)
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 0.7f);
                }
                if (t == 1)
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.3f);
                }
                if (t == 2)
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.6f);
                }
                ++t;
                if (t == 3) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)core, 0L, 3L);
    }
}
