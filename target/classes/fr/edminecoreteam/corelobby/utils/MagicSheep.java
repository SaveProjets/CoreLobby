package fr.edminecoreteam.corelobby.utils;

import fr.edminecoreteam.corelobby.Core;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class MagicSheep implements Listener
{
    private Map<UUID, Long> lastSpawnTimes = new HashMap<UUID, Long>();
    private static Core core = Core.getInstance();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        int maxSheepCount = 30;
        int currentSheepCount = 0;
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Sheep) {
                currentSheepCount++;
            }
        }
        if (currentSheepCount >= maxSheepCount) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        long lastSpawnTime = lastSpawnTimes.getOrDefault(player.getUniqueId(), 0L);
        if (currentTime - lastSpawnTime < 5000) {
            return;
        }
        Location playerLocation = player.getLocation();
        int spawnDistance = 10;
        if (playerLocation.distanceSquared(playerLocation.getWorld().getSpawnLocation()) < spawnDistance * spawnDistance) {
            return;
        }
        Random random = new Random();
        double x = playerLocation.getX() + (random.nextDouble() * 80 - 40);
        double z = playerLocation.getZ() + (random.nextDouble() * 80 - 40);
        double y = world.getHighestBlockYAt((int) x, (int) z);
        Sheep sheep = (Sheep) world.spawnEntity(new Location(world, x, y, z), EntityType.SHEEP);
        DyeColor[] colors = DyeColor.values();
        sheep.setColor(colors[random.nextInt(colors.length)]);
        net.minecraft.server.v1_8_R3.Entity znms1 = ((CraftEntity) sheep).getHandle();
        NBTTagCompound ztag1 = new NBTTagCompound();
        znms1.c(ztag1);
        ztag1.setBoolean("Silent", true);
        znms1.f(ztag1);
        lastSpawnTimes.put(player.getUniqueId(), currentTime);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntityType() == EntityType.SHEEP && event.getDamager() instanceof Player) {
            Sheep sheep = (Sheep) event.getEntity();
            Player p = (Player) event.getDamager();
            Vector direction = p.getLocation().getDirection();
            direction.setY(1).normalize(); // Set the y component to 0 and normalize the vector

            // Apply a force to the sheep in the direction of the player's facing direction
            sheep.setVelocity(direction.multiply(2));
            int hitsToKill = 2; // Number of hits required to kill the sheep
            if (!sheep.hasMetadata("hitCounter")) {
                sheep.setMetadata("hitCounter", new FixedMetadataValue(core, 1));
                Bukkit.getWorld(sheep.getWorld().getName()).playEffect(sheep.getLocation(), Effect.EXPLOSION_LARGE, Integer.MIN_VALUE);
                for (Player ps : Bukkit.getOnlinePlayers())
                {
                    ps.playSound(p.getLocation(), Sound.SHEEP_IDLE, 1.0f, 1.0f);
                }
            } else {
                int hitCount = sheep.getMetadata("hitCounter").get(0).asInt();
                if (hitCount == hitsToKill) {
                    sheep.setMetadata("hitCounter", new FixedMetadataValue(core, hitCount + 1));
                    Firework f = (Firework) sheep.getWorld().spawn(sheep.getLocation(), Firework.class);

                    FireworkMeta fm = f.getFireworkMeta();
                    fm.addEffect(FireworkEffect.builder()
                            .flicker(false)
                            .trail(true)
                            .with(Type.BALL)
                            .withColor(Color.RED)
                            .withFade(Color.BLUE)
                            .build());
                    fm.setPower(1);
                    f.setPassenger(sheep);
                    f.setFireworkMeta(fm);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
                        @SuppressWarnings("deprecation")
                        public void run() {
                            sheep.remove(); // Remove sheep entity
                            p.sendTitle("§cBoom !", "");
                        }
                    }, 25);
                    for (Player ps : Bukkit.getOnlinePlayers())
                    {
                        ps.playSound(p.getLocation(), Sound.SHEEP_IDLE, 1.0f, 1.0f);
                        ps.playSound(p.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
                    }
                } else if (hitCount < hitsToKill) {
                    sheep.setMetadata("hitCounter", new FixedMetadataValue(core, hitCount + 1));
                    Bukkit.getWorld(sheep.getWorld().getName()).playEffect(sheep.getLocation(), Effect.EXPLOSION_LARGE, Integer.MIN_VALUE);
                    for (Player ps : Bukkit.getOnlinePlayers())
                    {
                        ps.playSound(p.getLocation(), Sound.SHEEP_IDLE, 1.0f, 1.0f);
                        ps.playSound(p.getLocation(), Sound.CREEPER_HISS, 1.0f, 1.0f);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Sheep) {
            Sheep sheep = (Sheep) event.getRightClicked();
            Player player = event.getPlayer();
            if (sheep.getPassenger() == null) {
                sheep.setPassenger(player);
                player.sendMessage("§fVous ridez un " + getChatColorFromSheepColor(sheep.getColor()) + "moutmout §f!");
            }
        }
    }

    private ChatColor getChatColorFromSheepColor(DyeColor dyeColor) {
        // Associez les couleurs de mouton aux couleurs de texte correspondantes
        switch (dyeColor) {
            case WHITE:
                return ChatColor.WHITE;
            case ORANGE:
                return ChatColor.GOLD;
            case MAGENTA:
                return ChatColor.DARK_PURPLE;
            case LIGHT_BLUE:
                return ChatColor.BLUE;
            case YELLOW:
                return ChatColor.YELLOW;
            case LIME:
                return ChatColor.GREEN;
            case PINK:
                return ChatColor.LIGHT_PURPLE;
            case GRAY:
                return ChatColor.DARK_GRAY;
            case SILVER:
                return ChatColor.GRAY;
            case CYAN:
                return ChatColor.DARK_AQUA;
            case PURPLE:
                return ChatColor.DARK_PURPLE;
            case BLUE:
                return ChatColor.DARK_BLUE;
            case BROWN:
                return ChatColor.GOLD;
            case GREEN:
                return ChatColor.DARK_GREEN;
            case RED:
                return ChatColor.RED;
            case BLACK:
                return ChatColor.BLACK;
            default:
                return ChatColor.LIGHT_PURPLE;
        }
    }
}
