package fr.edminecoreteam.corelobby.utils;

import fr.edminecoreteam.corelobby.Core;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomSounds {
    public static void openShopSound(final Player p) {
        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (!p.isOnline()) { cancel(); }


                if (t == 0) {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 0.7f);
                } else if (t == 1) {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.3f);
                } else if (t == 2) {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.6f);
                }
                ++t;
                if (t == 3) {
                    run();
                }
            }
        }.runTaskTimer(Core.getInstance(), 0L,3L);
    }
}
