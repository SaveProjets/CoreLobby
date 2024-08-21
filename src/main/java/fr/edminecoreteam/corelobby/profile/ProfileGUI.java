package fr.edminecoreteam.corelobby.profile;

import java.util.ArrayList;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
import fr.edminecoreteam.corelobby.profile.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.account.AccountInfo;
import fr.edminecoreteam.corelobby.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.profile.friends.FriendInfo;
import fr.edminecoreteam.corelobby.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.profile.link.LinkGui;
import fr.edminecoreteam.corelobby.profile.ranks.RankInfo;
import fr.edminecoreteam.corelobby.profile.settings.SettingGui;
import fr.edminecoreteam.corelobby.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;

public class ProfileGUI implements Listener {
    private static Core api = Core.getInstance();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) {
            return;
        }
        if (inv.getName().equalsIgnoreCase("§8Profil")) {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE || it.getType() == Material.ENDER_PEARL) {
                if (it.getItemMeta().getDisplayName() == "§d§lAmis §c❤") {
                    FriendInfo friendInfo = new FriendInfo(p.getName());
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FriendGui.gui(p, 1, friendInfo.getFriendPageNumber());
                }
                if (it.getItemMeta().getDisplayName() == "§9§lVos Réglages") {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    SettingGui.guiSocial(p);
                }
                if (it.getItemMeta().getDisplayName() == "§9§lGroupes") {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    GroupGui.gui(p);
                }
                if (it.getItemMeta().getDisplayName() == "§f§lStatut de votre compte") {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    SettingInfo settingInfo = new SettingInfo(p);
                    settingInfo.updateAccountState();
                    gui(p);
                }
                if (it.getItemMeta().getDisplayName() == "§6§lVos Réseaux Sociaux") {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    LinkGui.gui(p);
                }
            }
        }
    }

    public static void gui(Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil");
        p.openInventory(inv);
        AccountInfo accountInfo = new AccountInfo(p);
        RankInfo rankInfo = new RankInfo(p);
        FriendInfo friendInfo = new FriendInfo(p.getName());
        SettingInfo settingInfo = new SettingInfo(p);

        /*
         * Type de pages;
         *  - Liste d'amis: 0
         *  - Liste de demandes: 1
         */

        // if (settingInfo.getLang() == 0) {

        ItemStack deco = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 14)
                .setName("§r")
                .addEnchant(Enchantment.DAMAGE_ALL, 1)
                .hideEnchantments()
                .toItemStack();
        inv.setItem(0, deco);
        inv.setItem(8, deco);
        inv.setItem(9, deco);
        inv.setItem(17, deco);
        inv.setItem(45, deco);
        inv.setItem(53, deco);
        inv.setItem(36, deco);
        inv.setItem(44, deco);

        ArrayList<String> loreProfil = new ArrayList<String>();
        loreProfil.add("");
        loreProfil.add(" §aDescription:");
        loreProfil.add(" §f▶ §7Profil de §a" + p.getName());
        loreProfil.add("");
        loreProfil.add("§8➡ §fCliquez pour y accéder.");

        ItemStack profil = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§c§lProfil")
                .setLore(loreProfil)
                .setSkullUrl("https://textures.minecraft.net/texture/565f23ddc6e83ab7d0c4aa9c5744af7b96bc739bc83a96cb1f2b18d671f")
                .toItemStack();
        inv.setItem(18, profil);

        ArrayList<String> loreGuild = new ArrayList<String>();
        loreGuild.add("");
        loreGuild.add(" §aDescription:");
        loreGuild.add(" §f▶ §7Affichez les informations");
        loreGuild.add(" §f  §7à propos de votre guild.");
        loreGuild.add("");
        loreGuild.add("§8➡ §fCliquez pour y accéder.");

        ItemStack guild = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§b§lVotre Guild")
                .setLore(loreGuild)
                .setSkullUrl("https://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077")
                .toItemStack();
        inv.setItem(26, guild);

        ArrayList<String> loreFriendProfil = new ArrayList<String>();
        loreFriendProfil.add("");
        loreFriendProfil.add(" §aDescription:");
        loreFriendProfil.add(" §f▶ §7Visionnez et gérez");
        loreFriendProfil.add(" §f  §7votre liste d'amis.");
        loreFriendProfil.add("");
        loreFriendProfil.add(" §f▶ §7Nombre d'amis: §6" + friendInfo.getFriendCount());
        loreFriendProfil.add("");
        loreFriendProfil.add("§8➡ §fCliquez pour y accéder.");

        ItemStack friendProfil = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§d§lAmis §c❤")
                .setLore(loreFriendProfil)
                .setSkullUrl("https://textures.minecraft.net/texture/8cc95d7622ec32c5cc31ff7a9e2d545f951179a6c78af837b4e756c1f75a61")
                .toItemStack();
        inv.setItem(35, friendProfil);

        ArrayList<String> loreGroupProfil = new ArrayList<String>();
        loreGroupProfil.add("");
        loreGroupProfil.add(" §aDescription:");
        loreGroupProfil.add(" §f▶ §7Créez un groupe et");
        loreGroupProfil.add(" §f  §7invitez-y vos amis afin");
        loreGroupProfil.add(" §f  §7de jouer ensemble sur le serveur.");
        loreGroupProfil.add("");
        loreGroupProfil.add("§8➡ §fCliquez pour y accéder.");

        ItemStack groupProfil = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§9§lGroupes")
                .setLore(loreGroupProfil)
                .setSkullUrl("https://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077")
                .toItemStack();
        inv.setItem(27, groupProfil);

        ArrayList<String> loreGlobalInfos = new ArrayList<String>();
        loreGlobalInfos.add("");
        loreGlobalInfos.add(" §dInformations:");
        loreGlobalInfos.add(" §f▶ §7Compte: §b" + p.getName());
        loreGlobalInfos.add(" §f▶ §7ID de votre compte: §a" + accountInfo.getAccountID());
        if (rankInfo.getRankID() > 0) {
            loreGlobalInfos.add(" §f▶ §7Votre grade: " + Rank.powerToRank(rankInfo.getRankID()).getDisplayName());
        } else if (rankInfo.getRankID() == 0) {
            loreGlobalInfos.add(" §f▶ §7Votre grade: §caucun");
        }
        loreGlobalInfos.add(" §f▶ §7Fragments d'âmes: §b" + accountInfo.getFragmentsDames() + "§b✵");
        loreGlobalInfos.add(" §f▶ §7Eclats divins: §d" + accountInfo.getEclatsDivins() + "§d❁");
        loreGlobalInfos.add(" §f▶ §7Argent: §a" + accountInfo.getArgent() + "§a✪");
        loreGlobalInfos.add(" §f▶ §7Temps de jeu: §b...");
        loreGlobalInfos.add(" §f▶ §7Première connexion: §f" + accountInfo.getFirstConnection());
        loreGlobalInfos.add(" §f▶ §7Quêtes complétées: §e...");
        loreGlobalInfos.add(" §f▶ §7Votre parrain: §a...");
        loreGlobalInfos.add(" §f▶ §7Votre guild: §b...");
        loreGlobalInfos.add(" §f▶ §7Cosmétiques possédés: §d" + accountInfo.getTotalCosmetics());
        loreGlobalInfos.add(" §f▶ §7Parties jouées: §6" + accountInfo.getTotalPlayedPartys());
        loreGlobalInfos.add("");

        ItemStack globalInfos = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§f§lInformations Globales")
                .setLore(loreGlobalInfos)
                .setSkullOwner(p.getName())
                .toItemStack();
        inv.setItem(4, globalInfos);

        ArrayList<String> loreBooster = new ArrayList<String>();
        loreBooster.add("");
        loreBooster.add(" §aDescription:");
        loreBooster.add(" §f▶ §7Affichez vos booster et utilisez-");
        loreBooster.add(" §f  §7les pour booster vos coins.");
        loreBooster.add("");
        loreBooster.add("§8➡ §fCliquez pour y accéder.");

        ItemStack booster = new ItemBuilder(Material.EXP_BOTTLE, 1)
                .setName("§c§lVos Boosters")
                .setLore(loreBooster)
                .toItemStack();
        inv.setItem(23, booster);

        ArrayList<String> loreLanguage = new ArrayList<String>();
        loreLanguage.add("");
        loreLanguage.add(" §aDescription:");
        loreLanguage.add(" §f▶ §7Changez votre langue");
        loreLanguage.add(" §f  §7de manière simple.");
        loreLanguage.add("");
        loreLanguage.add(" §dInformations:");
        loreLanguage.add(" §f➟ §a[français]");
        loreLanguage.add(" §f▶ §8[anglais]");
        loreLanguage.add(" §f▶ §8[espagnol]");
        loreLanguage.add(" §f▶ §8[allemand]");
        loreLanguage.add("");
        loreLanguage.add("§8➡ §fCliquez pour changer.");

        ItemStack language = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§e§lVotre langue")
                .setLore(loreLanguage)
                .setSkullUrl("https://textures.minecraft.net/texture/51269a067ee37e63635ca1e723b676f139dc2dbddff96bbfef99d8b35c996bc")
                .toItemStack();
        inv.setItem(21, language);

        ArrayList<String> loreRankings = new ArrayList<String>();
        loreRankings.add("");
        loreRankings.add(" §aDescription:");
        loreRankings.add(" §f▶ §7Affichez les différents");
        loreRankings.add(" §f  §7classements des divers jeux.");
        loreRankings.add("");
        loreRankings.add("§8➡ §fCliquez pour y accéder.");

        ItemStack rankings = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§d§lClassements De Edmine")
                .setLore(loreRankings)
                .setSkullUrl("https://textures.minecraft.net/texture/55dfa284aa15324e5178561f803f5976228d95115583ab031266ae24ee1a99d1")
                .toItemStack();
        inv.setItem(22, rankings);

        ArrayList<String> loreStat = new ArrayList<String>();
        String accountState = settingInfo.getAccountState();
        loreStat.add("");
        loreStat.add(" §aDescription:");
        loreStat.add(" §f▶ §7Modifiez votre statut");
        loreStat.add(" §f  §7aux yeux des joueurs.");
        loreStat.add("");
        if (accountState.contains("En-Ligne")) {
            loreStat.add(" §f▶ §7Toutes les personnes ayant accès");
            loreStat.add("   §7à votre profil vous vois connecté.");
            loreStat.add("");
            loreStat.add(" §dInformations:");
            loreStat.add(" §f▶ §8[Hors-Ligne]");
            loreStat.add(" §f▶ §8[Occupé]");
            loreStat.add(" §f▶ §8[Inactif]");
            loreStat.add(" §f➟ §a[En-Ligne]");
        } else if (accountState.contains("Inactif")) {
            loreStat.add(" §f▶ §7Toutes les personnes ayant accès");
            loreStat.add("   §7à votre profil vous vois inactif.");
            loreStat.add("");
            loreStat.add(" §dInformations:");
            loreStat.add(" §f▶ §8[Hors-Ligne]");
            loreStat.add(" §f▶ §8[Occupé]");
            loreStat.add(" §f➟ §e[Inactif]");
            loreStat.add(" §f▶ §8[En-Ligne]");
        } else if (accountState.contains("Occupé")) {
            loreStat.add(" §f▶ §7Toutes les personnes ayant accès");
            loreStat.add("   §7à votre profil vous vois occupé.");
            loreStat.add("");
            loreStat.add(" §dInformations:");
            loreStat.add(" §f▶ §8[Hors-Ligne]");
            loreStat.add(" §f➟ §5[Occupé]");
            loreStat.add(" §f▶ §8[Inactif]");
            loreStat.add(" §f▶ §8[En-Ligne]");
        } else if (accountState.contains("Hors-Ligne")) {
            loreStat.add(" §f▶ §7Toutes les personnes ayant accès");
            loreStat.add("   §7à votre profil vous vois déconnecté.");
            loreStat.add("");
            loreStat.add(" §dInformations:");
            loreStat.add(" §f➟ §c[Hors-Ligne]");
            loreStat.add(" §f▶ §8[Occupé]");
            loreStat.add(" §f▶ §8[Inactif]");
            loreStat.add(" §f▶ §8[En-Ligne]");
        }
        loreStat.add("");
        loreStat.add("§8➡ §fCliquez pour changer.");

        ItemStack stat = new ItemBuilder(Material.ENDER_PEARL)
                .setName("§f§lStatut de votre compte")
                .setLore(loreStat)
                .toItemStack();
        inv.setItem(29, stat);

        ArrayList<String> loreSettings = new ArrayList<String>();
        loreSettings.add("");
        loreSettings.add(" §aDescription:");
        loreSettings.add(" §f▶ §7Modifiez à votre guise les");
        loreSettings.add(" §f  §7différents réglages disponibles.");
        loreSettings.add("");
        loreSettings.add("§8➡ §fCliquez pour y accéder.");

        ItemStack settings = new ItemBuilder(Material.DIODE)
                .setName("§9§lVos Réglages")
                .setLore(loreSettings)
                .toItemStack();
        inv.setItem(31, settings);

        ArrayList<String> loreStats = new ArrayList<String>();
        loreStats.add("");
        loreStats.add(" §aDescription:");
        loreStats.add(" §f▶ §7Accédez à vos statistiques");
        loreStats.add(" §f  §7en fonction des jeux joués.");
        loreStats.add("");
        loreStats.add("§8➡ §fCliquez pour y accéder.");

        ItemStack stats = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§e§lVos Statistiques")
                .setLore(loreStats)
                .setSkullUrl("https://textures.minecraft.net/texture/17980b940af858f910943464ee00359287cb0b5810680b60b89be4210dda0ed1")
                .toItemStack();
        inv.setItem(32, stats);

        ArrayList<String> loreSuccess = new ArrayList<String>();
        loreSuccess.add("");
        loreSuccess.add(" §aDescription:");
        loreSuccess.add(" §f▶ §7Accédez à vos succès que vous");
        loreSuccess.add(" §f  §7avez réalisés en terminant les");
        loreSuccess.add(" §f  §7quêtes proposées par le serveur.");
        loreSuccess.add("");
        loreSuccess.add("§8➡ §fCliquez pour y accéder.");

        ItemStack success = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§a§lVos Succès")
                .setLore(loreSuccess)
                .setSkullUrl("https://textures.minecraft.net/texture/cdcdee6d06df234b8e603328b96c57f3a312e79aabfc3be72a8b421878ed68cf")
                .toItemStack();
        inv.setItem(33, success);

        ArrayList<String> loreSocialMedia = new ArrayList<String>();
        loreSocialMedia.add("");
        loreSocialMedia.add(" §aDescription:");
        loreSocialMedia.add(" §f▶ §7Visionnez, gérez et");
        loreSocialMedia.add(" §f  §7dévoilez vos réseaux ");
        loreSocialMedia.add(" §f  §7au grand public ! ");
        loreSocialMedia.add("");
        loreSocialMedia.add("§8➡ §fCliquez pour y accéder.");

        ItemStack socialMedia = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§6§lVos Réseaux Sociaux")
                .setLore(loreSocialMedia)
                .setSkullUrl("https://textures.minecraft.net/texture/739ee7154979b3f87735a1c8ac087814b7928d0576a2695ba01ed61631942045")
                .toItemStack();

        ItemStack socialMedia1 = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§6§lVos Réseaux Sociaux")
                .setLore(loreSocialMedia)
                .setSkullUrl("https://textures.minecraft.net/texture/6ad46a422ae59603fd889c25344ff67bc843af8ee518932c2e2ad07cdbf939b3")
                .toItemStack();

        ItemStack socialMedia2 = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§6§lVos Réseaux Sociaux")
                .setLore(loreSocialMedia)
                .setSkullUrl("https://textures.minecraft.net/texture/ed2b05abe4976c0af1f54b8d34e656d14383174ff5c6eba613a8a8405498cb15")
                .toItemStack();

        new BukkitRunnable() {
            int t = 0;

            public void run() {

                if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Profil")) {
                    cancel();
                }


                if (t == 0) {
                    inv.setItem(30, socialMedia);
                }
                if (t == 1) {
                    inv.setItem(30, socialMedia1);
                }
                if (t == 2) {
                    inv.setItem(30, socialMedia2);
                }

                ++t;
                if (t == 3) {
                    t = 0;
                    run();
                }
            }
        }.runTaskTimer((Plugin) api, 0L, 15L);

        //}
    }
}
