package fr.edminecoreteam.corelobby.listeners;

import fr.edminecoreteam.corelobby.Core;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpawnAnimationLikeGTA
{
    public static Core core = Core.getInstance();

    public static void run(Player p)
    {
        p.setGameMode(GameMode.SPECTATOR);
        p.sendTitle("", "§aChargement...");
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

                int speed = 25;
                Vector dir = loc2.toVector().subtract(p.getLocation().toVector()).normalize();
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 0.5f, 1.0f);
                p.setVelocity(dir.multiply(speed));
                new BukkitRunnable() {

                    public void run() {
                        p.setVelocity(dir.multiply(speed));
                    }
                }.runTaskLater((Plugin) core, 5);

                new BukkitRunnable() {

                    public void run() {
                        p.setVelocity(dir.multiply(speed));
                    }
                }.runTaskLater((Plugin) core, 10);

                new BukkitRunnable() {

                    public void run() {
                        p.setVelocity(dir.multiply(speed));
                    }
                }.runTaskLater((Plugin) core, 15);

                new BukkitRunnable() {

                    public void run() {
                        p.setVelocity(dir.multiply(speed));
                    }
                }.runTaskLater((Plugin) core, 20);

                new BukkitRunnable() {

                    public void run() {
                        p.setVelocity(dir.multiply(speed));
                    }
                }.runTaskLater((Plugin) core, 25);

                new BukkitRunnable() {

                    public void run() {
                        p.setVelocity(dir.multiply(speed));
                    }
                }.runTaskLater((Plugin) core, 30);
            }
        }.runTaskLater((Plugin) core, 30L);

        new BukkitRunnable() {

            public void run() {
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 1));
                Location loc3 = new Location(Bukkit.getWorld(core.getConfig().getString("SpawnAnimation.loc3.name")),
                        (float)core.getConfig().getLong("SpawnAnimation.loc3.x"), (float)core.getConfig().getLong("SpawnAnimation.loc3.y"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc3.z"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc3.t"), (float)core.getConfig().getLong("SpawnAnimation.loc3.b"));

                p.teleport(loc3);
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                new BukkitRunnable() {

                    public void run() {
                        clearEffects(p);
                    }
                }.runTaskLater((Plugin) core, 7);
            }
        }.runTaskLater((Plugin) core, 120L);

        new BukkitRunnable() {

            public void run() {
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 1));
                Location loc4 = new Location(Bukkit.getWorld(core.getConfig().getString("SpawnAnimation.loc4.name")),
                        (float)core.getConfig().getLong("SpawnAnimation.loc4.x"), (float)core.getConfig().getLong("SpawnAnimation.loc4.y"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc4.z"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc4.t"), (float)core.getConfig().getLong("SpawnAnimation.loc4.b"));

                p.teleport(loc4);
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                new BukkitRunnable() {

                    public void run() {
                        clearEffects(p);
                    }
                }.runTaskLater((Plugin) core, 7);
            }
        }.runTaskLater((Plugin) core, 150L);

        new BukkitRunnable() {

            public void run() {
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 1));
                Location loc5 = new Location(Bukkit.getWorld(core.getConfig().getString("SpawnAnimation.loc5.name")),
                        (float)core.getConfig().getLong("SpawnAnimation.loc5.x"), (float)core.getConfig().getLong("SpawnAnimation.loc5.y"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc5.z"),
                        (float)core.getConfig().getLong("SpawnAnimation.loc5.t"), (float)core.getConfig().getLong("SpawnAnimation.loc5.b"));

                p.teleport(loc5);
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                new BukkitRunnable() {

                    public void run() {
                        clearEffects(p);
                    }
                }.runTaskLater((Plugin) core, 7);
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

    public static void clearEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
}
