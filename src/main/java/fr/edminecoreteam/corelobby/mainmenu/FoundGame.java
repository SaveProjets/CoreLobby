package fr.edminecoreteam.corelobby.mainmenu;

import java.util.ArrayList;
import java.util.List;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.changehub.ChangeHubInfo;

public class FoundGame implements Listener
{
    private static Core core = Core.getInstance();

    public static void startFound(Player p, String gameType)
    {
        if (!core.getFoundGame().contains(p.getName()))
        {
            core.getFoundGame().add(p.getName());
            setItem(p, 1);
            String waitingRoom = calculateRoom(gameType, 1);
            new BukkitRunnable() {
                int t = 0;
                int f = 0;
                public void run() {

                    if (!core.getFoundGame().contains(p.getName())) { cancel(); }
                    if (t == 0)
                    {
                        p.sendMessage("§e§lFile d'attente §8» §fVous avez rejoint la file " + waitingRoom + " §f...");
                    }
                    Core.getInstance().title.sendActionBar(p, "§aRecherche en cours... §7┃ §fTemps écoulé: §e" + convertTime(t));
                    ++t;
                    ++f;
                    if (f == 5) {
                        foundGame(p, gameType);
                        foundGameStatic(p, gameType);
                        f = 0;
                    }
                }
            }.runTaskTimer((Plugin)core, 0L, 20L);
        }
    }

    public static void foundGame(Player player, String gameType)
    {
        ChangeHubInfo srvInfo = new ChangeHubInfo(gameType);
        List<String> srvList = srvInfo.getServer();
        List<String> srvAttente = new ArrayList<String>();
        List<String> srvStart = new ArrayList<String>();

        for (String s : srvList)
        {
            ChangeHubInfo sInfo = new ChangeHubInfo(s);
            if (sInfo.getServerMotd().equalsIgnoreCase("WAITING"))
            {
                srvAttente.add(s);
            }
            else if (sInfo.getServerMotd().equalsIgnoreCase("STARTING"))
            {
                srvStart.add(s);
            }
        }

        if (srvStart.size() != 0)
        {
            if (core.getFoundGame().contains(player.getName()))
            {
                String waitingRoom = calculateRoom(gameType, 2);
                player.sendMessage("§e§lFile d'attente §8» §fConnexion en cours vers " + waitingRoom + " §f...");
                Core.getInstance().title.sendActionBar(player, "§aConnexion en cours...");
                core.getFoundGame().remove(player.getName());
                setItem(player, 2);
                String srv = srvStart.get(0);
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(srv);
                player.sendPluginMessage((Plugin)core, "BungeeCord", out.toByteArray());
            }
        }
        else
        {
            if (srvAttente.size() != 0)
            {
                if (core.getFoundGame().contains(player.getName()))
                {
                    String waitingRoom = calculateRoom(gameType, 2);
                    player.sendMessage("§e§lFile d'attente §8» §fConnexion en cours vers " + waitingRoom + " §f...");
                    Core.getInstance().title.sendActionBar(player, "§aConnexion en cours...");
                    core.getFoundGame().remove(player.getName());
                    setItem(player, 2);
                    String srv = srvAttente.get(0);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF(srv);
                    player.sendPluginMessage((Plugin)core, "BungeeCord", out.toByteArray());
                }
            }
        }
    }

    public static void foundGameStatic(Player player, String gameType)
    {
        ChangeHubInfo srvInfo = new ChangeHubInfo(gameType);
        List<String> srvList = srvInfo.getServer();

        if (srvList.size() != 0)
        {
            if (core.getFoundGame().contains(player.getName()))
            {
                String waitingRoom = calculateRoom(gameType, 2);
                player.sendMessage("§e§lFile d'attente §8» §fConnexion en cours vers " + waitingRoom + " §f...");
                Core.getInstance().title.sendActionBar(player, "§aConnexion en cours...");
                core.getFoundGame().remove(player.getName());
                setItem(player, 2);
                String srv = srvList.get(0);
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(srv);
                player.sendPluginMessage((Plugin)core, "BungeeCord", out.toByteArray());
            }
        }
    }

    public static void setItem(Player p, int item)
    {
        if (item == 1)
        {
            ItemStack found = new ItemBuilder(Material.BARRIER, 1)
                    .setName("§c§lRecherche en cours §7• Clique (Annuler)")
                    .toItemStack();
            p.getInventory().setItem(0, found);
        }
        if (item == 2)
        {
            MainItem.giveItem(p);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        if (it.getType() == Material.BARRIER)
        {
            if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRecherche en cours §7• Clique (Annuler)"))
            {
                e.setCancelled(true);
                core.getFoundGame().remove(p.getName());
                setItem(p, 2);
                p.sendMessage("§e§lFile d'attente §8» §cVous avez quitté la file d'attente...");
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();
        if (it == null) { return; }

        if (it.getType() == Material.BARRIER
                && it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRecherche en cours §7• Clique (Annuler)")
                && (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK))
        {
            e.setCancelled(true);
            core.getFoundGame().remove(p.getName());
            setItem(p, 2);
            p.sendMessage("§e§lFile d'attente §8» §cVous avez quitté la file d'attente...");
        }
    }

    private static String convertTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02dm %02ds", minutes, seconds);
    }

    private static String calculateRoom(String group, int found)
    {
        if (group.equalsIgnoreCase("PaintBall_TwovsFour_"))
        {
            if (found == 1)
            {
                return "§6PaintBall - 4vs4";
            }
            if (found == 2)
            {
                ChangeHubInfo srvInfo = new ChangeHubInfo(group);
                List<String> srvList = srvInfo.getServer();
                List<String> srvAttente = new ArrayList<String>();
                List<String> srvStart = new ArrayList<String>();

                for (String s : srvList)
                {
                    ChangeHubInfo sInfo = new ChangeHubInfo(s);
                    if (sInfo.getServerMotd().equalsIgnoreCase("WAITING"))
                    {
                        srvAttente.add(s);
                    }
                    else if (sInfo.getServerMotd().equalsIgnoreCase("STARTING"))
                    {
                        srvStart.add(s);
                    }
                }

                if (srvStart.size() != 0)
                {
                    ChangeHubInfo srvAttenteInfo = new ChangeHubInfo(srvStart.get(0));
                    return "§6pb_4vs4_" + srvAttenteInfo.getServerID();
                }
                else
                {
                    if (srvAttente.size() != 0)
                    {
                        ChangeHubInfo srvStartInfo = new ChangeHubInfo(srvAttente.get(0));
                        return "§6pb_4vs4_" + srvStartInfo.getServerID();
                    }
                }
            }
        }
        if (group.equalsIgnoreCase("DeACoudre"))
        {
            if (found == 1)
            {
                return "§6Dé À Coudre - Solo";
            }
            if (found == 2)
            {
                ChangeHubInfo srvInfo = new ChangeHubInfo(group);
                List<String> srvList = srvInfo.getServer();
                List<String> srvAttente = new ArrayList<String>();
                List<String> srvStart = new ArrayList<String>();

                for (String s : srvList)
                {
                    ChangeHubInfo sInfo = new ChangeHubInfo(s);
                    if (sInfo.getServerMotd().equalsIgnoreCase("WAITING"))
                    {
                        srvAttente.add(s);
                    }
                    else if (sInfo.getServerMotd().equalsIgnoreCase("STARTING"))
                    {
                        srvStart.add(s);
                    }
                }

                if (srvStart.size() != 0)
                {
                    ChangeHubInfo srvAttenteInfo = new ChangeHubInfo(srvStart.get(0));
                    return "§6dac_" + srvAttenteInfo.getServerID();
                }
                else
                {
                    if (srvAttente.size() != 0)
                    {
                        ChangeHubInfo srvStartInfo = new ChangeHubInfo(srvAttente.get(0));
                        return "§6dac_" + srvStartInfo.getServerID();
                    }
                }
            }
        }
        if (group.equalsIgnoreCase("DeadSurvivor"))
        {
            if (found == 1)
            {
                return "§6Dead Survivor - Quatuor";
            }
            if (found == 2)
            {
                ChangeHubInfo srvInfo = new ChangeHubInfo(group);
                List<String> srvList = srvInfo.getServer();
                List<String> srvAttente = new ArrayList<String>();
                List<String> srvStart = new ArrayList<String>();

                for (String s : srvList)
                {
                    ChangeHubInfo sInfo = new ChangeHubInfo(s);
                    if (sInfo.getServerMotd().equalsIgnoreCase("WAITING"))
                    {
                        srvAttente.add(s);
                    }
                    else if (sInfo.getServerMotd().equalsIgnoreCase("STARTING"))
                    {
                        srvStart.add(s);
                    }
                }

                if (srvStart.size() != 0)
                {
                    ChangeHubInfo srvAttenteInfo = new ChangeHubInfo(srvStart.get(0));
                    return "§6ds_" + srvAttenteInfo.getServerID();
                }
                else
                {
                    if (srvAttente.size() != 0)
                    {
                        ChangeHubInfo srvStartInfo = new ChangeHubInfo(srvAttente.get(0));
                        return "§6ds_" + srvStartInfo.getServerID();
                    }
                }
            }
        }
        if (group.equalsIgnoreCase("Practice"))
        {
            if (found == 1)
            {
                return "§6Practice";
            }
            if (found == 2)
            {
                ChangeHubInfo srvInfo = new ChangeHubInfo(group);
                List<String> srvList = srvInfo.getServer();



                if (srvList.size() != 0)
                {
                    ChangeHubInfo srvAttenteInfo = new ChangeHubInfo(srvList.get(0));
                    return "§6pt_" + srvAttenteInfo.getServerID();
                }
            }
        }
        return "";
    }
}
