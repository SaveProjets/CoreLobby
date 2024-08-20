package fr.edminecoreteam.corelobby.visual.holograms;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.user.AccountInfo;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class HologramManager
{
    public static Core core = Core.getInstance();

    private Player p;
    private String spigot_online;
    private String server_id;

    public HologramManager(Player p) {
        this.p = p;
    }

    public HologramManager() {
        // TODO Auto-generated constructor stub
    }

    public void createHolograms() {
        for (String holo : core.getConfig().getConfigurationSection("holograms").getKeys(false))
        {
            String world = core.getConfig().getConfigurationSection("holograms").getString(holo + ".world");
            float x = (float) core.getConfig().getConfigurationSection("holograms").getDouble(holo + ".loc.x");
            float y = (float) core.getConfig().getConfigurationSection("holograms").getDouble(holo + ".loc.y");
            float z = (float) core.getConfig().getConfigurationSection("holograms").getDouble(holo + ".loc.z");

            float finalY = y;
            for (String lines : core.getConfig().getConfigurationSection("holograms." + holo + ".lines").getKeys(false))
            {
                finalY -= 0.3;
                holoManageNMS(world, x, finalY, z, holo, lines);
            }
        }
    }

    public void removeHolograms() {
        Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "minecraft:kill @e[type=ArmorStand]");
        System.out.println("Toutes les entités on été suprimer...");
    }

    private void holoManageNMS(String world, float x, float y, float z, String name, String lines) {
        new BukkitRunnable() {
            int t = 0;
            Location loc = new Location(Bukkit.getWorld(world), x, y, z);
            WorldServer ws = ((CraftWorld)loc.getWorld()).getHandle();
            EntityArmorStand nmsStand = new EntityArmorStand((World)ws);

            public void run() {

                if (Bukkit.getPlayer(p.getName()) == null) { cancel(); }

                if (t == 1)
                {
                    spigot_online = "&7Connecté(s): &a".replace("&", "§") + Bukkit.getServer().getOnlinePlayers().size();
                    server_id = getID();

                    nmsStand.setLocation(x, y, z, 0.0f, 0.0f);
                    nmsStand.setSmall(true);

                    nmsStand.setCustomName(encode(core.getConfig().getString("holograms." + name + ".lines." + lines)));

                    nmsStand.setCustomNameVisible(true);
                    nmsStand.setGravity(false);
                    nmsStand.setInvisible(true);
                    PacketPlayOutEntityDestroy packetDestroy = new PacketPlayOutEntityDestroy(nmsStand.getId());
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packetDestroy);
                    PacketPlayOutSpawnEntityLiving packetSpawn = new PacketPlayOutSpawnEntityLiving((EntityLiving)nmsStand);
                    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetSpawn);
                }

                if (t == 20) {
                    this.t = 0;
                }
                ++t;
            }
        }.runTaskTimer((Plugin)Core.getInstance(), 0L, 10L);
    }


    public String getID()
    {
        String id = "";
        List<Character> number = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
        char[] charArray;
        for (int length = (charArray = Bukkit.getServerName().toCharArray()).length, i = 0; i < length; ++i)
        {
            char cha = charArray[i];
            if (number.contains(cha))
            {
                id = String.valueOf(id) + new String(new char[] { cha });
            }
        }
        return id;
    }

    public String encode(String s) {
        AccountInfo info = new AccountInfo(p);
        String encoded = s
                .replace("&", "§")
                .replace("{e1}", "é")
                .replace("{e2}", "è")
                .replace("{e3}", "ê")
                .replace("{i1}", "î")
                .replace("{a1}", "à")
                .replace("{a2}", "â")
                .replace("{o1}", "ô")
                .replace("{love}", "❤")
                .replace("{valide}", "✔")
                .replace("{unvalid}", "✘")
                .replace("{flocon}", "✵")
                .replace("{eclats}", "❁")
                .replace("{money}", "✪")
                .replace("{star}", "✯")
                .replace("{right}", "➡")
                .replace("{left}", "⬅")
                .replace("{up}", "⬆")
                .replace("{down}", "⬇")
                .replace("{right_select}", "➟")
                .replace("{alert}", "⚠")
                .replace("{separator}", "»")
                .replace("{spigot_online}", spigot_online)
                .replace("{server_id}", server_id)
                .replace("{player_name}", p.getName())
                .replace("{fragments_dames}", info.getFragmentsDames() + " ✵")
                .replace("{eclats_divins}", info.getEclatsDivins() + " ❁")
                .replace("{argent}", info.getArgent() + " ✪")
                .replace("{euro}", "€");

        return encoded;
    }
}
