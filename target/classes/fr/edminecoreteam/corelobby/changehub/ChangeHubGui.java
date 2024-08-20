package fr.edminecoreteam.corelobby.changehub;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.user.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class ChangeHubGui implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }
    static HashMap<Player, Integer> pageCount = new HashMap<Player, Integer>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        ChangeHubInfo changeHubInfo = new ChangeHubInfo("Lobby");

        int MaxPage = changeHubInfo.getServerPageNumber();
        if (it == null) {  return; }

        if (inv.getName().contains("§8Changer De Hub"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM)
            {
                if (it.getItemMeta().getDisplayName().contains("Lobby"))
                {
                    String srv = it.getItemMeta().getDisplayName().replace("§a", "");
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF(srv);
                    p.sendPluginMessage((Plugin)api, "BungeeCord", out.toByteArray());
                }


                if(it.getItemMeta().getDisplayName() == "§8➡ §7Page Suivante")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    pageCount.put(p, pageCount.get(p) + 1);
                    gui(p, pageCount.get(p), MaxPage);
                }
                if(it.getItemMeta().getDisplayName() == "§8⬅ §7Page Précédente")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    pageCount.put(p, pageCount.get(p) - 1);
                    gui(p, pageCount.get(p), MaxPage);
                }
            }


        }
    }

    public static void gui(final Player p, final int Page, final int MaxPage) {

        final Inventory inv = Bukkit.createInventory(null, 54, "§8Changer De Hub " + Page + "/" + MaxPage);
        p.openInventory(inv);
        final ChangeHubInfo changeHubInfo = new ChangeHubInfo("Lobby");
        SettingInfo settingInfo = new SettingInfo(p);
        pageCount.put(p, Page);
        final int serversPerPage = 10;


        if (settingInfo.getLang() == 0)
        {

            new BukkitRunnable() {
                int t = 0;
                public void run() {

                    if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Changer De Hub " + Page + "/" + MaxPage)) { cancel(); }

                    ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)2);
                    ItemMeta decoM = deco.getItemMeta();
                    decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    decoM.setDisplayName("§r");
                    deco.setItemMeta(decoM);
                    inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                    inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);

                    if (Page != MaxPage)
                    {
                        if (Page == 1)
                        {
                            ItemStack suivant = getSkull("http://textures.minecraft.net/texture/956a3618459e43b287b22b7e235ec699594546c6fcd6dc84bfca4cf30ab9311");
                            ItemMeta suivantM = suivant.getItemMeta();
                            suivantM.setDisplayName("§8➡ §7Page Suivante");
                            suivant.setItemMeta(suivantM);
                            inv.setItem(50, suivant);
                        }
                        if (Page != 1)
                        {
                            ItemStack suivant = getSkull("http://textures.minecraft.net/texture/956a3618459e43b287b22b7e235ec699594546c6fcd6dc84bfca4cf30ab9311");
                            ItemMeta suivantM = suivant.getItemMeta();
                            suivantM.setDisplayName("§8➡ §7Page Suivante");
                            suivant.setItemMeta(suivantM);
                            inv.setItem(50, suivant);

                            ItemStack precedent = getSkull("http://textures.minecraft.net/texture/cdc9e4dcfa4221a1fadc1b5b2b11d8beeb57879af1c42362142bae1edd5");
                            SkullMeta precedentM = (SkullMeta)precedent.getItemMeta();
                            precedentM.setDisplayName("§8⬅ §7Page Précédente");
                            precedent.setItemMeta((ItemMeta)precedentM);
                            inv.setItem(48, precedent);
                        }
                    }
                    else if (Page == MaxPage)
                    {
                        if (MaxPage == 1)
                        {
                            //Rien
                        }
                        else if (MaxPage > 1)
                        {
                            ItemStack precedent = getSkull("http://textures.minecraft.net/texture/cdc9e4dcfa4221a1fadc1b5b2b11d8beeb57879af1c42362142bae1edd5");
                            SkullMeta precedentM = (SkullMeta)precedent.getItemMeta();
                            precedentM.setDisplayName("§8⬅ §7Page Précédente");
                            precedent.setItemMeta((ItemMeta)precedentM);
                            inv.setItem(48, precedent);
                        }
                    }

                    ++t;
                    if (t == 5) {
                        t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 15L);


            new BukkitRunnable() {
                int t = 0;
                public void run() {

                    if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Changer De Hub " + Page + "/" + MaxPage)) { cancel(); }
                    int slot = 20;
                    int serverCount = 0;
                    for (String servers : changeHubInfo.getServerForPage(Page)) {
                        ++serverCount;
                        ChangeHubInfo serversInfo = new ChangeHubInfo(servers);
                        if (serversInfo.getServerMotd().equalsIgnoreCase("faible"))
                        {
                            ItemStack server = getSkull("http://textures.minecraft.net/texture/a3e9f4dbadde0f727c5803d75d8bb378fb9fcb4b60d33bec19092a3a2e7b07a9");
                            SkullMeta serverM = (SkullMeta) server.getItemMeta();
                            serverM.setDisplayName("§a" + servers);
                            ArrayList<String> loreserver = new ArrayList<String>();
                            loreserver.add("");
                            loreserver.add(" §dInformation:");
                            loreserver.add(" §f▶ §7Présence: §a" + serversInfo.getServerMotd());
                            loreserver.add(" §f▶ §7Connecté(s): §e" + serversInfo.getServerOnlines());
                            loreserver.add(" §f▶ §7ID du serveur: §d#" + serversInfo.getServerID());
                            loreserver.add("");
                            if (p.getServer().getServerName().equalsIgnoreCase(servers))
                            {
                                loreserver.add("§8➡ §aActuellement connecté.");
                            }
                            else
                            {
                                loreserver.add("§8➡ §fCliquez pour rejoindre.");
                            }
                            serverM.setLore(loreserver);
                            server.setItemMeta(serverM);

                            if (serverCount == serversPerPage)
                            {
                                inv.setItem(slot, server);
                            }
                            else if (serverCount != serversPerPage)
                            {
                                inv.setItem(slot, server);
                            }
                            if (slot == 24) {
                                slot += 4;
                            }
                            if (slot != 25 || slot != 33  && slot < 33) {
                                slot += 1;
                            }
                            if (slot == 34) {
                                return;
                            }
                        }
                        else if (serversInfo.getServerMotd().equalsIgnoreCase("moyen"))
                        {
                            ItemStack server = getSkull("http://textures.minecraft.net/texture/13330fbed377c244f487e4bc5682d15af40d3ce4c32ee03fc24a7f952e7d29a9");
                            SkullMeta serverM = (SkullMeta) server.getItemMeta();
                            serverM.setDisplayName("§a" + servers);
                            ArrayList<String> loreserver = new ArrayList<String>();
                            loreserver.add("");
                            loreserver.add(" §dInformation:");
                            loreserver.add(" §f▶ §7Présence: §6" + serversInfo.getServerMotd());
                            loreserver.add(" §f▶ §7Connecté(s): §e" + serversInfo.getServerOnlines());
                            loreserver.add(" §f▶ §7ID du serveur: §d#" + serversInfo.getServerID());
                            loreserver.add("");
                            if (p.getServer().getServerName().equalsIgnoreCase(servers))
                            {
                                loreserver.add("§8➡ §aActuellement connecté.");
                            }
                            else
                            {
                                loreserver.add("§8➡ §fCliquez pour rejoindre.");
                            }
                            serverM.setLore(loreserver);
                            server.setItemMeta(serverM);

                            if (serverCount == serversPerPage)
                            {
                                inv.setItem(slot, server);
                            }
                            else if (serverCount != serversPerPage)
                            {
                                inv.setItem(slot, server);
                            }
                            if (slot == 24) {
                                slot += 4;
                            }
                            if (slot != 25 || slot != 33  && slot < 33) {
                                slot += 1;
                            }
                            if (slot == 34) {
                                return;
                            }
                        }
                        else if (serversInfo.getServerMotd().equalsIgnoreCase("fort"))
                        {
                            ItemStack server = getSkull("http://textures.minecraft.net/texture/2062d8d72f5891c71fab30d52e0481795b3d2d3d2ed2f8b9b517d7d2821e35d6");
                            SkullMeta serverM = (SkullMeta) server.getItemMeta();
                            serverM.setDisplayName("§a" + servers);
                            ArrayList<String> loreserver = new ArrayList<String>();
                            loreserver.add("");
                            loreserver.add(" §dInformation:");
                            loreserver.add(" §f▶ §7Présence: §c" + serversInfo.getServerMotd());
                            loreserver.add(" §f▶ §7Connecté(s): §e" + serversInfo.getServerOnlines());
                            loreserver.add(" §f▶ §7ID du serveur: §d#" + serversInfo.getServerID());
                            loreserver.add("");
                            if (p.getServer().getServerName().equalsIgnoreCase(servers))
                            {
                                loreserver.add("§8➡ §aActuellement connecté.");
                            }
                            else
                            {
                                loreserver.add("§8➡ §fCliquez pour rejoindre.");
                            }
                            serverM.setLore(loreserver);
                            server.setItemMeta(serverM);

                            if (serverCount == serversPerPage)
                            {
                                inv.setItem(slot, server);
                            }
                            else if (serverCount != serversPerPage)
                            {
                                inv.setItem(slot, server);
                            }
                            if (slot == 24) {
                                slot += 4;
                            }
                            if (slot != 25 || slot != 33  && slot < 33) {
                                slot += 1;
                            }
                            if (slot == 34) {
                                return;
                            }
                        }
                        else if (serversInfo.getServerMotd().equalsIgnoreCase("complet"))
                        {
                            ItemStack server = getSkull("http://textures.minecraft.net/texture/c65f3bae0d203ba16fe1dc3d1307a86a638be924471f23e82abd9d78f8a3fca");
                            SkullMeta serverM = (SkullMeta) server.getItemMeta();
                            serverM.setDisplayName("§a" + servers);
                            ArrayList<String> loreserver = new ArrayList<String>();
                            loreserver.add("");
                            loreserver.add(" §dInformation:");
                            loreserver.add(" §f▶ §7Présence: §4" + serversInfo.getServerMotd());
                            loreserver.add(" §f▶ §7Connecté(s): §e" + serversInfo.getServerOnlines());
                            loreserver.add(" §f▶ §7ID du serveur: §d#" + serversInfo.getServerID());
                            loreserver.add("");
                            if (p.getServer().getServerName().equalsIgnoreCase(servers))
                            {
                                loreserver.add("§8➡ §aActuellement connecté.");
                            }
                            else
                            {
                                loreserver.add("§8➡ §fCliquez pour rejoindre.");
                            }
                            serverM.setLore(loreserver);
                            server.setItemMeta(serverM);

                            if (serverCount == serversPerPage)
                            {
                                inv.setItem(slot, server);
                            }
                            else if (serverCount != serversPerPage)
                            {
                                inv.setItem(slot, server);
                            }
                            if (slot == 24) {
                                slot += 4;
                            }
                            if (slot != 25 || slot != 33  && slot < 33) {
                                slot += 1;
                            }
                            if (slot == 34) {
                                return;
                            }
                        }
                    }

                    ++t;
                    if (t == 100) {
                        t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 15L);
        }

    }
}
