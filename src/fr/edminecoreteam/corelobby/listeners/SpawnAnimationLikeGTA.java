package fr.edminecoreteam.corelobby.listeners;

import fr.edminecoreteam.corelobby.Core;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpawnAnimationLikeGTA
{
    public static Core core = Core.getInstance();

    public static void run(Player p)
    {
        p.setGameMode(GameMode.SPECTATOR);

        Location loc1 = new Location(Bukkit.getWorld(core.getConfig().getString("SpawnAnimation.loc1.name")),
                (float)core.getConfig().getLong("SpawnAnimation.loc1.x"), (float)core.getConfig().getLong("SpawnAnimation.loc1.y"),
                (float)core.getConfig().getLong("SpawnAnimation.loc1.z"),
                (float)core.getConfig().getLong("SpawnAnimation.loc1.t"), (float)core.getConfig().getLong("SpawnAnimation.loc1.b"));
        p.teleport(loc1);

        new BukkitRunnable() {

            public void run() {
                Location loc2 = new Location(Bukkit.getWorld(core.getConfig().getString("SpawnAnimation.loc2.name")),
                        (float)core.getConfig().getLong("SpawnAnimation.loc2.x"), (float)core.getConfig().getLong("SpawnAnimation.loc2.y"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc2.z"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc2.t"), (float)core.getConfig().getLong("SpawnAnimation.loc2.b"));

                int speed = 8;
                Vector dir = loc2.toVector().subtract(p.getLocation().toVector()).normalize();
                p.setVelocity(dir.multiply(speed));
            }
        }.runTaskLater((Plugin) core, 50L);

        new BukkitRunnable() {

            public void run() {
                Location loc3 = new Location(Bukkit.getWorld(core.getConfig().getString("SpawnAnimation.loc3.name")),
                        (float)core.getConfig().getLong("SpawnAnimation.loc3.x"), (float)core.getConfig().getLong("SpawnAnimation.loc3.y"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc3.z"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc3.t"), (float)core.getConfig().getLong("SpawnAnimation.loc3.b"));

                p.teleport(loc3);
            }
        }.runTaskLater((Plugin) core, 120L);

        new BukkitRunnable() {

            public void run() {
                Location loc4 = new Location(Bukkit.getWorld(core.getConfig().getString("SpawnAnimation.loc4.name")),
                        (float)core.getConfig().getLong("SpawnAnimation.loc4.x"), (float)core.getConfig().getLong("SpawnAnimation.loc4.y"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc4.z"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc4.t"), (float)core.getConfig().getLong("SpawnAnimation.loc4.b"));

                p.teleport(loc4);
            }
        }.runTaskLater((Plugin) core, 150L);

        new BukkitRunnable() {

            public void run() {
                Location loc5 = new Location(Bukkit.getWorld(core.getConfig().getString("SpawnAnimation.loc5.name")),
                        (float)core.getConfig().getLong("SpawnAnimation.loc5.x"), (float)core.getConfig().getLong("SpawnAnimation.loc5.y"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc5.z"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc5.t"), (float)core.getConfig().getLong("SpawnAnimation.loc5.b"));

                p.teleport(loc5);
                PlayerJoinListener.loadPlayerInfo(p);
                if (PlayerJoinListener.getCanDoubleJump().contains(p)) {
                    p.setAllowFlight(true);
                    p.setFlying(false);
                }
                else {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
            }
        }.runTaskLater((Plugin) core, 180L);



    }
}
