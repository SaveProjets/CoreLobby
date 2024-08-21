package fr.edminecoreteam.corelobby.mainmenu.longgame;

import java.util.ArrayList;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.mainmenu.OnlinesForGroups;
import fr.edminecoreteam.corelobby.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.stats.sql.DeadSurvivorData;
import fr.edminecoreteam.corelobby.utils.SkullNBT;

public class DeadSurvivorGui implements Listener
{
    private static Core api = Core.getInstance();

    public static void gui(Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Menu Principal (Dead Survivor)");
        p.openInventory(inv);
        SettingInfo settingInfo = new SettingInfo(p);

        /*
         * Type de pages;
         *  - Liste d'amis: 0
         *  - Liste de demandes: 1
         */

        if (settingInfo.getLang() == 0)
        {

            new BukkitRunnable() {
                int t = 0;
                public void run() {

                    if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Menu Principal (Dead Survivor)")) { cancel(); }

                    ItemStack deco = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 14)
                            .setName("§r")
                            .addEnchant(Enchantment.DAMAGE_ALL, 1)
                            .hideEnchantments()
                            .toItemStack();

                    inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                    inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);

                    ItemStack deco1 = new ItemBuilder(Material.IRON_FENCE, 1)
                            .setName("§r")
                            .toItemStack();
                    inv.setItem(37, deco1); inv.setItem(38, deco1); inv.setItem(39, deco1); inv.setItem(40, deco1);
                    inv.setItem(41, deco1); inv.setItem(42, deco1); inv.setItem(43, deco1);

                    ArrayList<String> loremain = new ArrayList<String>();
                    loremain.add("");
                    loremain.add(" §aDescription:");
                    loremain.add(" §f▶ §7Section principal.");
                    loremain.add("");
                    loremain.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack main = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§b§lMenu Principal")
                            .setLore(loremain)
                            .setSkullUrl("https://textures.minecraft.net/texture/ddcd8fe8d4d5c05886db9f59127961549e602f30c51759998131b2ad25d264")
                            .toItemStack();
                    inv.setItem(18, main);

                    ArrayList<String> lorehost = new ArrayList<String>();
                    lorehost.add("");
                    lorehost.add(" §aDescription:");
                    lorehost.add(" §f▶ §7Ici, libre a votre imagination de");
                    lorehost.add(" §f  §7créez ou jouez sur des parties custom.");
                    lorehost.add("");
                    lorehost.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack host = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§d§lServeur customisé")
                            .setLore(lorehost)
                            .setSkullUrl("https://textures.minecraft.net/texture/c2eb28cd6c7524b14d59f3b8c465dfc78be8e0dac843e682c4252ae9459c78e4")
                            .toItemStack();
                    inv.setItem(27, host);

                    ArrayList<String> lorevip = new ArrayList<String>();
                    lorevip.add("");
                    lorevip.add(" §aDescription:");
                    lorevip.add(" §f▶ §7...");
                    lorevip.add("");
                    lorevip.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack vip = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§f§lFonctions VIP")
                            .setLore(lorevip)
                            .setSkullUrl("https://textures.minecraft.net/texture/633c89a3c529d5136be6c49a62be0383fc3722cc990142e5cb3cc96db199d7d")
                            .toItemStack();
                    inv.setItem(26, vip);

                    ArrayList<String> loresettings = new ArrayList<String>();
                    loresettings.add("");
                    loresettings.add(" §aDescription:");
                    loresettings.add(" §f▶ §7Modifiez à votre guise les");
                    loresettings.add(" §f  §7différents réglages disponibles.");
                    loresettings.add("");
                    loresettings.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack settings = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§9§lParamètres")
                            .setLore(loresettings)
                            .setSkullUrl("https://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b")
                            .toItemStack();
                    inv.setItem(35, settings);

                    ArrayList<String> loreplay = new ArrayList<String>();
                    loreplay.add("");
                    loreplay.add(" §dInformations:");
                    loreplay.add(" §f▶ §7Joueur(s):  §e" + OnlinesForGroups.getOnlinesFromGroup("deadsurvivor"));
                    loreplay.add(" §f▶ §7File d'attente: ");
                    loreplay.add("");
                    loreplay.add(" §6Mode de jeu:");
                    loreplay.add(" §f➟ §a[Quatuor]");
                    loreplay.add("");
                    loreplay.add("§8➡ §fClic Gauche: §8Jouer");
                    loreplay.add("§8➡ §fClic Droit: §8Changer de mode");

                    ItemStack play = new ItemBuilder(Material.DIAMOND_AXE, 1)
                            .setName("§e§lJouer")
                            .setLore(loreplay)
                            .toItemStack();
                    inv.setItem(22, play);

                    DeadSurvivorData dsData = new DeadSurvivorData(p.getName());

                    ArrayList<String> lorestat = new ArrayList<String>();
                    lorestat.add("");
                    lorestat.add(" §cInformations Tueur:");
                    lorestat.add(" §f▶ §7Victoires: §a" + dsData.getKillerWin());
                    lorestat.add(" §f▶ §7Défaites: §c" + dsData.getKillerLose());
                    lorestat.add(" §f▶ §7Kills: §d" + dsData.getKillerKills());
                    lorestat.add(" §f▶ §7Frappes: §b" + dsData.getKillerFrappes());
                    lorestat.add("");
                    lorestat.add(" §bInformations Survivant:");
                    lorestat.add(" §f▶ §7Victoires: §a" + dsData.getSurvivorWin());
                    lorestat.add(" §f▶ §7Défaites: §c" + dsData.getSurvivorLose());
                    lorestat.add(" §f▶ §7Réacteurs réparés: §b" + dsData.getSurvivorReacteurs());
                    lorestat.add(" §f▶ §7Survivants sauvés: §d" + dsData.getSurvivorSauver());
                    lorestat.add("");
                    lorestat.add(" §dInformations:");
                    lorestat.add(" §f▶ §7Parties jouée: §d" + dsData.getPlayedGames());
                    lorestat.add("");
                    lorestat.add("§8➡ §fCliquez pour y accéder.");

                   ItemStack stat = new ItemBuilder(Material.PAPER, 1)
                           .setName("§f§lStatistiques")
                           .setLore(lorestat)
                           .toItemStack();
                    inv.setItem(15, stat);

                    ArrayList<String> lorecreateHost = new ArrayList<String>();
                    lorecreateHost.add("");
                    lorecreateHost.add(" §aDescription:");
                    lorecreateHost.add(" §f▶ §7...");
                    lorecreateHost.add("");
                    lorecreateHost.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack createHost = new ItemBuilder(Material.COMMAND_MINECART, 1)
                            .setName("§6§lCréez votre serveur customisé !")
                            .setLore(lorecreateHost)
                            .toItemStack();
                    inv.setItem(11, createHost);

                    ArrayList<String> loredeadsurvivor1 = new ArrayList<String>();
                    loredeadsurvivor1.add("");
                    loredeadsurvivor1.add(" §aDescription:");
                    loredeadsurvivor1.add(" §f▶ §7Visionnez les différents");
                    loredeadsurvivor1.add(" §f  §7articles disponibles");
                    loredeadsurvivor1.add(" §f  §7sur les armes dead survivor.");
                    loredeadsurvivor1.add("");
                    loredeadsurvivor1.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack deadsurvivor1 = new ItemBuilder(Material.IRON_AXE, 1)
                            .setName("§4§lArmes Dead Survivor")
                            .setLore(loredeadsurvivor1)
                            .toItemStack();
                    inv.setItem(50, deadsurvivor1);

                    ArrayList<String> loredeadsurvivor = new ArrayList<String>();
                    loredeadsurvivor.add("");
                    loredeadsurvivor.add(" §aDescription:");
                    loredeadsurvivor.add(" §f▶ §7Visionnez les différents");
                    loredeadsurvivor.add(" §f  §7articles disponibles");
                    loredeadsurvivor.add(" §f  §7sur les skins dead survivor.");
                    loredeadsurvivor.add("");
                    loredeadsurvivor.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack deadsurvivor = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§4§lSkins Dead Survivor")
                            .setLore(loredeadsurvivor)
                            .setSkullUrl("https://textures.minecraft.net/texture/ec778558b3e858a92e3a31971d95eb4316fb868982c0f380aaa38b690cc41ce8")
                            .toItemStack();
                    inv.setItem(48, deadsurvivor);

                    ++t;
                    if (t == 6) {
                        t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 15L);

        }
    }
}
