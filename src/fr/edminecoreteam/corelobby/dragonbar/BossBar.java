package fr.edminecoreteam.corelobby.dragonbar;

import fr.edminecoreteam.corelobby.Core;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class BossBar extends BukkitRunnable
{
    private String title;
    private final HashMap<Player, EntityWither> withers = new HashMap<Player, EntityWither>();

    public BossBar(Core instance, String title) {
        this.title = title;
        runTaskTimer(instance, 0, 10);
    }

    public void addPlayer(Player p) {
        EntityWither wither = new EntityWither(((CraftWorld) p.getWorld()).getHandle());
        Location l = getWitherLocation(p.getLocation());
        wither.setCustomName(title);
        wither.setInvisible(true);
        wither.setLocation(l.getX(), l.getY(), l.getZ(), 0, 0);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(wither);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        withers.put(p, wither);
    }

    public void removePlayer(Player p) {
        EntityWither wither = withers.remove(p);
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(wither.getId());
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    public void setTitle(String title) {
        this.title = title;
        for (Map.Entry<Player, EntityWither> entry : withers.entrySet()) {
            EntityWither wither = entry.getValue();
            wither.setCustomName(title);
            PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(wither.getId(), wither.getDataWatcher(), true);
            ((CraftPlayer) entry.getKey()).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void setProgress(double progress) {
        for (Map.Entry<Player, EntityWither> en : withers.entrySet()) {
            /*EntityWither wither = en.getValue();
            DataWatcher dataWatcher = wither.getDataWatcher();
            dataWatcher.watch(6, (float) progress * wither.getMaxHealth());
            //wither.setHealth((float) (progress * wither.getMaxHealth()));
            PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(wither.getId(), dataWatcher, true);
            ((CraftPlayer)en.getKey()).getHandle().playerConnection.sendPacket(packet);*/

            EntityWither wither = en.getValue();

            // Construire le paquet de mise à jour de la barre de boss
            PacketPlayOutEntityMetadata packet = constructBossBarPacket(wither, (float) progress);

            // Envoyer le paquet au joueur
            ((CraftPlayer) en.getKey()).getHandle().playerConnection.sendPacket(packet);
        }
    }

    private PacketPlayOutEntityMetadata constructBossBarPacket(EntityWither wither, float progress) {
        DataWatcher dataWatcher = new DataWatcher(null);
        dataWatcher.a(6, progress * 300.0F); // Index 6 pour la santé

        // Créer un nouveau paquet de métadonnées d'entité pour mettre à jour la barre de boss
        return new PacketPlayOutEntityMetadata(wither.getId(), dataWatcher, true);
    }

    public Location getWitherLocation(Location l) {
        return l.add(l.getDirection().multiply(60));
    }

    @Override
    public void run() {
        for (Map.Entry<Player, EntityWither> en : withers.entrySet()) {
            EntityWither wither = en.getValue();
            Location l = getWitherLocation(en.getKey().getLocation());
            wither.setLocation(l.getX(), l.getY(), l.getZ(), 0, 0);
            PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(wither);
            ((CraftPlayer)en.getKey()).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
