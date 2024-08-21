package fr.edminecoreteam.corelobby.changehub;

import java.util.ArrayList;
import java.util.HashMap;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
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

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;

public class ChangeHubGui implements Listener {
    private static Core api = Core.getInstance();
    static HashMap<Player, Integer> pageCount = new HashMap<Player, Integer>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        ChangeHubInfo changeHubInfo = new ChangeHubInfo("Lobby");

        int MaxPage = changeHubInfo.getServerPageNumber();
        if (it == null) {
            return;
        }

        if (inv.getName().contains("§8Changer De Hub")) {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM) {
                if (it.getItemMeta().getDisplayName().contains("Lobby")) {
                    String srv = it.getItemMeta().getDisplayName().replace("§a", "");
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF(srv);
                    p.sendPluginMessage((Plugin) api, "BungeeCord", out.toByteArray());
                }

                if (it.getItemMeta().getDisplayName() == "§8➡ §7Page Suivante") {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    pageCount.put(p, pageCount.get(p) + 1);
                    gui(p, pageCount.get(p), MaxPage);
                }
                if (it.getItemMeta().getDisplayName() == "§8⬅ §7Page Précédente") {
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

        if (settingInfo.getLang() == 0) {

            new BukkitRunnable() {
                int t = 0;

                public void run() {

                    if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Changer De Hub " + Page + "/" + MaxPage)) {
                        cancel();
                    }

                    ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 2);
                    ItemMeta decoM = deco.getItemMeta();
                    decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    decoM.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
                    decoM.setDisplayName("§r");
                    deco.setItemMeta(decoM);
                    inv.setItem(0, deco);
                    inv.setItem(8, deco);
                    inv.setItem(9, deco);
                    inv.setItem(17, deco);
                    inv.setItem(45, deco);
                    inv.setItem(53, deco);
                    inv.setItem(36, deco);
                    inv.setItem(44, deco);

                    ItemStack suivant = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§8➡ §7Page Suivante")
                            .setSkullUrl("https://textures.minecraft.net/texture/956a3618459e43b287b22b7e235ec699594546c6fcd6dc84bfca4cf30ab9311")
                            .toItemStack();

                    ItemStack precedent = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§8⬅ §7Page Précédente")
                            .setSkullUrl("https://textures.minecraft.net/texture/cdc9e4dcfa4221a1fadc1b5b2b11d8beeb57879af1c42362142bae1edd5")
                            .toItemStack();

                    if (Page != MaxPage) {

                        if (Page == 1) {
                            inv.setItem(50, suivant);
                        }
                        if (Page != 1) {
                            inv.setItem(50, suivant);
                            inv.setItem(48, precedent);
                        }
                    } else if (Page == MaxPage) {
                        if (MaxPage == 1) {
                            //Rien
                        } else if (MaxPage > 1) {
                            inv.setItem(48, precedent);
                        }
                    }

                    ++t;
                    if (t == 5) {
                        t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin) api, 0L, 15L);


            new BukkitRunnable() {
                int t = 0;

                public void run() {

                    if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Changer De Hub " + Page + "/" + MaxPage)) {
                        cancel();
                    }
                    int slot = 20;
                    int serverCount = 0;
                    for (String servers : changeHubInfo.getServerForPage(Page)) {
                        ++serverCount;
                        ChangeHubInfo serversInfo = new ChangeHubInfo(servers);

                        ArrayList<String> loreserver = new ArrayList<String>();
                        loreserver.add("");
                        loreserver.add(" §dInformation:");
                        if (serversInfo.getServerMotd().equalsIgnoreCase("faible")) {
                            loreserver.add(" §f▶ §7Présence: §a" + serversInfo.getServerMotd());
                        } else if (serversInfo.getServerMotd().equalsIgnoreCase("moyen")) {
                            loreserver.add(" §f▶ §7Présence: §6" + serversInfo.getServerMotd());
                        } else if (serversInfo.getServerMotd().equalsIgnoreCase("fort")) {
                            loreserver.add(" §f▶ §7Présence: §c" + serversInfo.getServerMotd());
                        } else if (serversInfo.getServerMotd().equalsIgnoreCase("complet")) {
                            loreserver.add(" §f▶ §7Présence: §4" + serversInfo.getServerMotd());
                        }

                        loreserver.add(" §f▶ §7Connecté(s): §e" + serversInfo.getServerOnlines());
                        loreserver.add(" §f▶ §7ID du serveur: §d#" + serversInfo.getServerID());
                        loreserver.add("");
                        if (p.getServer().getServerName().equalsIgnoreCase(servers)) {
                            loreserver.add("§8➡ §aActuellement connecté.");
                        } else {
                            loreserver.add("§8➡ §fCliquez pour rejoindre.");
                        }

                        ItemStack server = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                                .setName("§a" + servers)
                                .setLore(loreserver)
                                .setSkullUrl("https://textures.minecraft.net/texture/a3e9f4dbadde0f727c5803d75d8bb378fb9fcb4b60d33bec19092a3a2e7b07a9")
                                .toItemStack();

                        if (serverCount == serversPerPage) {
                            inv.setItem(slot, server);
                        } else if (serverCount != serversPerPage) {
                            inv.setItem(slot, server);
                        }
                        if (slot == 24) {
                            slot += 4;
                        }
                        if (slot != 25 || slot != 33 && slot < 33) {
                            slot += 1;
                        }
                        if (slot == 34) {
                            return;
                        }
                    }

                    ++t;
                    if (t == 100) {
                        t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin) api, 0L, 15L);
        }

    }
}
