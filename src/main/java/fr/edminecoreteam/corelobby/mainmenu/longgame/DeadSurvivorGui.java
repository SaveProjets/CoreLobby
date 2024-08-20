package fr.edminecoreteam.corelobby.mainmenu.longgame;

import java.util.ArrayList;

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
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }

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

                    ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
                    ItemMeta decoM = deco.getItemMeta();
                    decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    decoM.setDisplayName("§r");
                    deco.setItemMeta(decoM);
                    inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                    inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);

                    ItemStack deco1 = new ItemStack(Material.IRON_FENCE, 1);
                    ItemMeta deco1M = deco1.getItemMeta();
                    deco1M.setDisplayName("§r");
                    deco1.setItemMeta(deco1M);
                    inv.setItem(37, deco1); inv.setItem(38, deco1); inv.setItem(39, deco1); inv.setItem(40, deco1);
                    inv.setItem(41, deco1); inv.setItem(42, deco1); inv.setItem(43, deco1);

                    ItemStack main = getSkull("http://textures.minecraft.net/texture/ddcd8fe8d4d5c05886db9f59127961549e602f30c51759998131b2ad25d264");
                    ItemMeta mainM = main.getItemMeta();
                    mainM.setDisplayName("§b§lMenu Principal");
                    ArrayList<String> loremain = new ArrayList<String>();
                    loremain.add("");
                    loremain.add(" §aDescription:");
                    loremain.add(" §f▶ §7Section principal.");
                    loremain.add("");
                    loremain.add("§8➡ §fCliquez pour y accéder.");
                    mainM.setLore(loremain);
                    main.setItemMeta(mainM);
                    inv.setItem(18, main);

                    ItemStack host = getSkull("http://textures.minecraft.net/texture/c2eb28cd6c7524b14d59f3b8c465dfc78be8e0dac843e682c4252ae9459c78e4");
                    ItemMeta hostM = host.getItemMeta();
                    hostM.setDisplayName("§d§lServeur customisé");
                    ArrayList<String> lorehost = new ArrayList<String>();
                    lorehost.add("");
                    lorehost.add(" §aDescription:");
                    lorehost.add(" §f▶ §7Ici, libre a votre imagination de");
                    lorehost.add(" §f  §7créez ou jouez sur des parties custom.");
                    lorehost.add("");
                    lorehost.add("§8➡ §fCliquez pour y accéder.");
                    hostM.setLore(lorehost);
                    host.setItemMeta(hostM);
                    inv.setItem(27, host);

                    ItemStack vip = getSkull("http://textures.minecraft.net/texture/633c89a3c529d5136be6c49a62be0383fc3722cc990142e5cb3cc96db199d7d");
                    ItemMeta vipM = vip.getItemMeta();
                    vipM.setDisplayName("§f§lFonctions VIP");
                    ArrayList<String> lorevip = new ArrayList<String>();
                    lorevip.add("");
                    lorevip.add(" §aDescription:");
                    lorevip.add(" §f▶ §7...");
                    lorevip.add("");
                    lorevip.add("§8➡ §fCliquez pour y accéder.");
                    vipM.setLore(lorevip);
                    vip.setItemMeta(vipM);
                    inv.setItem(26, vip);

                    ItemStack settings = getSkull("http://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b");
                    ItemMeta settingsM = settings.getItemMeta();
                    settingsM.setDisplayName("§9§lParamètres");
                    ArrayList<String> loresettings = new ArrayList<String>();
                    loresettings.add("");
                    loresettings.add(" §aDescription:");
                    loresettings.add(" §f▶ §7Modifiez à votre guise les");
                    loresettings.add(" §f  §7différents réglages disponibles.");
                    loresettings.add("");
                    loresettings.add("§8➡ §fCliquez pour y accéder.");
                    settingsM.setLore(loresettings);
                    settings.setItemMeta(settingsM);
                    inv.setItem(35, settings);

                    ItemStack play = new ItemStack(Material.DIAMOND_AXE, 1);
                    ItemMeta playM = play.getItemMeta();
                    playM.setDisplayName("§e§lJouer");
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
                    playM.setLore(loreplay);
                    play.setItemMeta(playM);
                    inv.setItem(22, play);

                    DeadSurvivorData dsData = new DeadSurvivorData(p.getName());

                    ItemStack stat = new ItemStack(Material.PAPER, 1);
                    ItemMeta statM = stat.getItemMeta();
                    statM.setDisplayName("§f§lStatistiques");
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
                    statM.setLore(lorestat);
                    stat.setItemMeta(statM);
                    inv.setItem(15, stat);

                    ItemStack createHost = new ItemStack(Material.COMMAND_MINECART, 1);
                    ItemMeta createHostM = createHost.getItemMeta();
                    createHostM.setDisplayName("§6§lCréez votre serveur customisé !");
                    ArrayList<String> lorecreateHost = new ArrayList<String>();
                    lorecreateHost.add("");
                    lorecreateHost.add(" §aDescription:");
                    lorecreateHost.add(" §f▶ §7...");
                    lorecreateHost.add("");
                    lorecreateHost.add("§8➡ §fCliquez pour y accéder.");
                    createHostM.setLore(lorecreateHost);
                    createHost.setItemMeta(createHostM);
                    inv.setItem(11, createHost);

                    ItemStack deadsurvivor1 = new ItemStack(Material.IRON_AXE, 1);
                    ItemMeta deadsurvivor1M = deadsurvivor1.getItemMeta();
                    deadsurvivor1M.setDisplayName("§4§lArmes Dead Survivor");
                    ArrayList<String> loredeadsurvivor1 = new ArrayList<String>();
                    loredeadsurvivor1.add("");
                    loredeadsurvivor1.add(" §aDescription:");
                    loredeadsurvivor1.add(" §f▶ §7Visionnez les différents");
                    loredeadsurvivor1.add(" §f  §7articles disponibles");
                    loredeadsurvivor1.add(" §f  §7sur les armes dead survivor.");
                    loredeadsurvivor1.add("");
                    loredeadsurvivor1.add("§8➡ §fCliquez pour y accéder.");
                    deadsurvivor1M.setLore(loredeadsurvivor1);
                    deadsurvivor1.setItemMeta(deadsurvivor1M);
                    inv.setItem(50, deadsurvivor1);

                    ItemStack deadsurvivor = getSkull("http://textures.minecraft.net/texture/ec778558b3e858a92e3a31971d95eb4316fb868982c0f380aaa38b690cc41ce8");
                    ItemMeta deadsurvivorM = deadsurvivor.getItemMeta();
                    deadsurvivorM.setDisplayName("§4§lSkins Dead Survivor");
                    ArrayList<String> loredeadsurvivor = new ArrayList<String>();
                    loredeadsurvivor.add("");
                    loredeadsurvivor.add(" §aDescription:");
                    loredeadsurvivor.add(" §f▶ §7Visionnez les différents");
                    loredeadsurvivor.add(" §f  §7articles disponibles");
                    loredeadsurvivor.add(" §f  §7sur les skins dead survivor.");
                    loredeadsurvivor.add("");
                    loredeadsurvivor.add("§8➡ §fCliquez pour y accéder.");
                    deadsurvivorM.setLore(loredeadsurvivor);
                    deadsurvivor.setItemMeta(deadsurvivorM);
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
