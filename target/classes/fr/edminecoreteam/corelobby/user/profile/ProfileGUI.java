package fr.edminecoreteam.corelobby.user.profile;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.user.AccountInfo;
import fr.edminecoreteam.corelobby.user.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.user.profile.friends.FriendInfo;
import fr.edminecoreteam.corelobby.user.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.user.profile.link.LinkGui;
import fr.edminecoreteam.corelobby.user.profile.ranks.Rank;
import fr.edminecoreteam.corelobby.user.profile.ranks.RankInfo;
import fr.edminecoreteam.corelobby.user.profile.settings.SettingGui;
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

public class ProfileGUI implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        if (inv.getName().equalsIgnoreCase("§8Profil"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE || it.getType() == Material.ENDER_PEARL)
            {
                if(it.getItemMeta().getDisplayName() == "§d§lAmis §c❤")
                {
                    FriendInfo friendInfo = new FriendInfo(p.getName());
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FriendGui.gui(p, 1, friendInfo.getFriendPageNumber());
                }
                if(it.getItemMeta().getDisplayName() == "§9§lVos Réglages")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    SettingGui.guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§9§lGroupes")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    GroupGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§f§lStatut de votre compte")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    SettingInfo settingInfo = new SettingInfo(p);
                    settingInfo.updateAccountState();
                    gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§6§lVos Réseaux Sociaux")
                {
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

        if (settingInfo.getLang() == 0)
        {

            new BukkitRunnable() {
                int t = 0;
                public void run() {

                    if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Profil")) { cancel(); }

                    ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
                    ItemMeta decoM = deco.getItemMeta();
                    decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    decoM.setDisplayName("§r");
                    deco.setItemMeta(decoM);
                    inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                    inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);


                    ItemStack profil = getSkull("http://textures.minecraft.net/texture/565f23ddc6e83ab7d0c4aa9c5744af7b96bc739bc83a96cb1f2b18d671f");
                    ItemMeta profilM = profil.getItemMeta();
                    profilM.setDisplayName("§c§lProfil");
                    ArrayList<String> loreprofil = new ArrayList<String>();
                    loreprofil.add("");
                    loreprofil.add(" §aDescription:");
                    loreprofil.add(" §f▶ §7Profil de §a" + p.getName());
                    loreprofil.add("");
                    loreprofil.add("§8➡ §fCliquez pour y accéder.");
                    profilM.setLore(loreprofil);
                    profil.setItemMeta(profilM);
                    inv.setItem(18, profil);

                    ItemStack guild = getSkull("http://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077");
                    ItemMeta guildM = guild.getItemMeta();
                    guildM.setDisplayName("§b§lVotre Guild");
                    ArrayList<String> loreguild = new ArrayList<String>();
                    loreguild.add("");
                    loreguild.add(" §aDescription:");
                    loreguild.add(" §f▶ §7Affichez les informations");
                    loreguild.add(" §f  §7à propos de votre guild.");
                    loreguild.add("");
                    loreguild.add("§8➡ §fCliquez pour y accéder.");
                    guildM.setLore(loreguild);
                    guild.setItemMeta(guildM);
                    inv.setItem(26, guild);

                    ItemStack frienDprofil = getSkull("http://textures.minecraft.net/texture/8cc95d7622ec32c5cc31ff7a9e2d545f951179a6c78af837b4e756c1f75a61");
                    ItemMeta frienDprofilM = frienDprofil.getItemMeta();
                    frienDprofilM.setDisplayName("§d§lAmis §c❤");
                    ArrayList<String> lorefrienDprofil = new ArrayList<String>();
                    lorefrienDprofil.add("");
                    lorefrienDprofil.add(" §aDescription:");
                    lorefrienDprofil.add(" §f▶ §7Visionnez et gérez");
                    lorefrienDprofil.add(" §f  §7votre liste d'amis.");
                    lorefrienDprofil.add("");
                    lorefrienDprofil.add(" §f▶ §7Nombre d'amis: §6" + friendInfo.getFriendCount());
                    lorefrienDprofil.add("");
                    lorefrienDprofil.add("§8➡ §fCliquez pour y accéder.");
                    frienDprofilM.setLore(lorefrienDprofil);
                    frienDprofil.setItemMeta(frienDprofilM);
                    inv.setItem(35, frienDprofil);

                    ItemStack groupprofil = getSkull("http://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077");
                    ItemMeta groupprofilM = groupprofil.getItemMeta();
                    groupprofilM.setDisplayName("§9§lGroupes");
                    ArrayList<String> loregroupprofil = new ArrayList<String>();
                    loregroupprofil.add("");
                    loregroupprofil.add(" §aDescription:");
                    loregroupprofil.add(" §f▶ §7Créez un groupe et");
                    loregroupprofil.add(" §f  §7invitez-y vos amis afin");
                    loregroupprofil.add(" §f  §7de jouer ensemble sur le serveur.");
                    loregroupprofil.add("");
                    loregroupprofil.add("§8➡ §fCliquez pour y accéder.");
                    groupprofilM.setLore(loregroupprofil);
                    groupprofil.setItemMeta(groupprofilM);
                    inv.setItem(27, groupprofil);

                    ItemStack globalinfos = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
                    SkullMeta globalinfosM = (SkullMeta) globalinfos.getItemMeta();
                    globalinfosM.setOwner(p.getName());
                    globalinfosM.setDisplayName("§f§lInformations Globales");
                    ArrayList<String> loreglobalinfos = new ArrayList<String>();
                    loreglobalinfos.add("");
                    loreglobalinfos.add(" §dInformations:");
                    loreglobalinfos.add(" §f▶ §7Compte: §b" + p.getName());
                    loreglobalinfos.add(" §f▶ §7ID de votre compte: §a" + accountInfo.getAccountID());
                    if (rankInfo.getRankType().equalsIgnoreCase("staff"))
                    {
                        if (rankInfo.getRankID() >= 1)
                        {
                            loreglobalinfos.add(" §f▶ §7Votre grade: " + Rank.powerToRank(rankInfo.getRankID()).getDisplayName());
                        }
                        else if (rankInfo.getRankID() == 0)
                        {
                            loreglobalinfos.add(" §f▶ §7Votre grade: §caucun");
                        }
                        loreglobalinfos.add(" §f▶ §7Votre grade staff: " + Rank.powerToRank(rankInfo.getRankModule()).getDisplayName());
                    }
                    if (rankInfo.getRankType().equalsIgnoreCase("static"))
                    {
                        if (rankInfo.getRankID() > 0)
                        {
                            loreglobalinfos.add(" §f▶ §7Votre grade: " + Rank.powerToRank(rankInfo.getRankID()).getDisplayName());
                        }
                        else if (rankInfo.getRankID() == 0)
                        {
                            loreglobalinfos.add(" §f▶ §7Votre grade: §caucun");
                        }
                    }
                    if (rankInfo.getRankType().equalsIgnoreCase("tempo"))
                    {
                        loreglobalinfos.add(" §f▶ §7Votre grade: " + Rank.powerToRank(rankInfo.getRankID()).getDisplayName());
                    }
                    if (rankInfo.getRankType().equalsIgnoreCase("module"))
                    {
                        if (rankInfo.getRankID() >= 1)
                        {
                            loreglobalinfos.add(" §f▶ §7Votre grade: " + Rank.powerToRank(rankInfo.getRankID()).getDisplayName());
                        }
                        else if (rankInfo.getRankID() == 0)
                        {
                            loreglobalinfos.add(" §f▶ §7Votre grade: §caucun");
                        }
                        loreglobalinfos.add(" §f▶ §7Votre sous-grade: " + Rank.powerToRank(rankInfo.getRankModule()).getDisplayName());
                    }
                    loreglobalinfos.add(" §f▶ §7Fragments d'âmes: §b" + accountInfo.getFragmentsDames() + "§b✵");
                    loreglobalinfos.add(" §f▶ §7Eclats divins: §d" + accountInfo.getEclatsDivins() + "§d❁");
                    loreglobalinfos.add(" §f▶ §7Argent: §a" + accountInfo.getArgent() + "§a✪");
                    loreglobalinfos.add(" §f▶ §7Temps de jeu: §b...");
                    loreglobalinfos.add(" §f▶ §7Première connexion: §f" + accountInfo.getFirstConnection());
                    loreglobalinfos.add(" §f▶ §7Quêtes complétées: §e...");
                    loreglobalinfos.add(" §f▶ §7Votre parrain: §a...");
                    loreglobalinfos.add(" §f▶ §7Votre guild: §b...");
                    loreglobalinfos.add(" §f▶ §7Cosmétiques possédés: §d" + accountInfo.getTotalCosmetics());
                    loreglobalinfos.add(" §f▶ §7Parties jouées: §6" + accountInfo.getTotalPlayedPartys());
                    loreglobalinfos.add("");
                    globalinfosM.setLore(loreglobalinfos);
                    globalinfos.setItemMeta(globalinfosM);
                    inv.setItem(4, globalinfos);


                    ItemStack booster = new ItemStack(Material.EXP_BOTTLE, 1);
                    ItemMeta boosterM = booster.getItemMeta();
                    boosterM.setDisplayName("§c§lVos Boosters");
                    ArrayList<String> lorebooster = new ArrayList<String>();
                    lorebooster.add("");
                    lorebooster.add(" §aDescription:");
                    lorebooster.add(" §f▶ §7Affichez vos booster et utilisez-");
                    lorebooster.add(" §f  §7les pour booster vos coins.");
                    lorebooster.add("");
                    lorebooster.add("§8➡ §fCliquez pour y accéder.");
                    boosterM.setLore(lorebooster);
                    booster.setItemMeta(boosterM);
                    inv.setItem(23, booster);

                    ItemStack language = getSkull("http://textures.minecraft.net/texture/51269a067ee37e63635ca1e723b676f139dc2dbddff96bbfef99d8b35c996bc");
                    ItemMeta languageM = language.getItemMeta();
                    languageM.setDisplayName("§e§lVotre langue");
                    ArrayList<String> lorelanguage = new ArrayList<String>();
                    lorelanguage.add("");
                    lorelanguage.add(" §aDescription:");
                    lorelanguage.add(" §f▶ §7Changez votre langue");
                    lorelanguage.add(" §f  §7de manière simple.");
                    lorelanguage.add("");
                    lorelanguage.add(" §dInformations:");
                    lorelanguage.add(" §f➟ §a[français]");
                    lorelanguage.add(" §f▶ §8[anglais]");
                    lorelanguage.add(" §f▶ §8[espagnol]");
                    lorelanguage.add(" §f▶ §8[allemand]");
                    lorelanguage.add("");
                    lorelanguage.add("§8➡ §fCliquez pour changer.");
                    languageM.setLore(lorelanguage);
                    language.setItemMeta(languageM);
                    inv.setItem(21, language);

                    ItemStack rankings = getSkull("http://textures.minecraft.net/texture/55dfa284aa15324e5178561f803f5976228d95115583ab031266ae24ee1a99d1");
                    ItemMeta rankingsM = rankings.getItemMeta();
                    rankingsM.setDisplayName("§d§lClassements De Edmine");
                    ArrayList<String> lorerankings = new ArrayList<String>();
                    lorerankings.add("");
                    lorerankings.add(" §aDescription:");
                    lorerankings.add(" §f▶ §7Affichez les différents");
                    lorerankings.add(" §f  §7classements des divers jeux.");
                    lorerankings.add("");
                    lorerankings.add("§8➡ §fCliquez pour y accéder.");
                    rankingsM.setLore(lorerankings);
                    rankings.setItemMeta(rankingsM);
                    inv.setItem(22, rankings);

                    ItemStack stat = new ItemStack(Material.ENDER_PEARL, 1);
                    ItemMeta statM = stat.getItemMeta();
                    statM.setDisplayName("§f§lStatut de votre compte");
                    ArrayList<String> lorestat = new ArrayList<String>();
                    lorestat.add("");
                    lorestat.add(" §aDescription:");
                    lorestat.add(" §f▶ §7Modifiez votre statut");
                    lorestat.add(" §f  §7aux yeux des joueurs.");
                    lorestat.add("");
                    if (settingInfo.getAccountState().contains("En-Ligne"))
                    {
                        lorestat.add(" §f▶ §7Toutes les personnes ayant accès");
                        lorestat.add("   §7à votre profil vous vois connecté.");
                        lorestat.add("");
                        lorestat.add(" §dInformations:");
                        lorestat.add(" §f▶ §8[Hors-Ligne]");
                        lorestat.add(" §f▶ §8[Occupé]");
                        lorestat.add(" §f▶ §8[Inactif]");
                        lorestat.add(" §f➟ §a[En-Ligne]");
                    }
                    else if (settingInfo.getAccountState().contains("Inactif"))
                    {
                        lorestat.add(" §f▶ §7Toutes les personnes ayant accès");
                        lorestat.add("   §7à votre profil vous vois inactif.");
                        lorestat.add("");
                        lorestat.add(" §dInformations:");
                        lorestat.add(" §f▶ §8[Hors-Ligne]");
                        lorestat.add(" §f▶ §8[Occupé]");
                        lorestat.add(" §f➟ §e[Inactif]");
                        lorestat.add(" §f▶ §8[En-Ligne]");
                    }
                    else if (settingInfo.getAccountState().contains("Occupé"))
                    {
                        lorestat.add(" §f▶ §7Toutes les personnes ayant accès");
                        lorestat.add("   §7à votre profil vous vois occupé.");
                        lorestat.add("");
                        lorestat.add(" §dInformations:");
                        lorestat.add(" §f▶ §8[Hors-Ligne]");
                        lorestat.add(" §f➟ §5[Occupé]");
                        lorestat.add(" §f▶ §8[Inactif]");
                        lorestat.add(" §f▶ §8[En-Ligne]");
                    }
                    else if (settingInfo.getAccountState().contains("Hors-Ligne"))
                    {
                        lorestat.add(" §f▶ §7Toutes les personnes ayant accès");
                        lorestat.add("   §7à votre profil vous vois déconnecté.");
                        lorestat.add("");
                        lorestat.add(" §dInformations:");
                        lorestat.add(" §f➟ §c[Hors-Ligne]");
                        lorestat.add(" §f▶ §8[Occupé]");
                        lorestat.add(" §f▶ §8[Inactif]");
                        lorestat.add(" §f▶ §8[En-Ligne]");
                    }
                    lorestat.add("");
                    lorestat.add("§8➡ §fCliquez pour changer.");
                    statM.setLore(lorestat);
                    stat.setItemMeta(statM);
                    inv.setItem(29, stat);

                    ItemStack setting = new ItemStack(Material.DIODE, 1);
                    ItemMeta settingM = setting.getItemMeta();
                    settingM.setDisplayName("§9§lVos Réglages");
                    ArrayList<String> loresetting = new ArrayList<String>();
                    loresetting.add("");
                    loresetting.add(" §aDescription:");
                    loresetting.add(" §f▶ §7Modifiez à votre guise les");
                    loresetting.add(" §f  §7différents réglages disponibles.");
                    loresetting.add("");
                    loresetting.add("§8➡ §fCliquez pour y accéder.");
                    settingM.setLore(loresetting);
                    setting.setItemMeta(settingM);
                    inv.setItem(31, setting);

                    ItemStack stats = getSkull("http://textures.minecraft.net/texture/17980b940af858f910943464ee00359287cb0b5810680b60b89be4210dda0ed1");
                    ItemMeta statsM = stats.getItemMeta();
                    statsM.setDisplayName("§e§lVos Statistiques");
                    ArrayList<String> lorestats = new ArrayList<String>();
                    lorestats.add("");
                    lorestats.add(" §aDescription:");
                    lorestats.add(" §f▶ §7Accédez à vos statistiques");
                    lorestats.add(" §f  §7en fonction des jeux joués.");
                    lorestats.add("");
                    lorestats.add("§8➡ §fCliquez pour y accéder.");
                    statsM.setLore(lorestats);
                    stats.setItemMeta(statsM);
                    inv.setItem(32, stats);

                    ItemStack success = getSkull("http://textures.minecraft.net/texture/cdcdee6d06df234b8e603328b96c57f3a312e79aabfc3be72a8b421878ed68cf");
                    ItemMeta successM = success.getItemMeta();
                    successM.setDisplayName("§a§lVos Succès");
                    ArrayList<String> loresuccess = new ArrayList<String>();
                    loresuccess.add("");
                    loresuccess.add(" §aDescription:");
                    loresuccess.add(" §f▶ §7Accédez à vos succès que vous");
                    loresuccess.add(" §f  §7avez réalisés en terminant les");
                    loresuccess.add(" §f  §7quêtes proposées par le serveur.");
                    loresuccess.add("");
                    loresuccess.add("§8➡ §fCliquez pour y accéder.");
                    successM.setLore(loresuccess);
                    success.setItemMeta(successM);
                    inv.setItem(33, success);


                    if (t == 0)
                    {
                        ItemStack friendprofil = getSkull("http://textures.minecraft.net/texture/739ee7154979b3f87735a1c8ac087814b7928d0576a2695ba01ed61631942045");
                        ItemMeta friendprofilM = friendprofil.getItemMeta();
                        friendprofilM.setDisplayName("§6§lVos Réseaux Sociaux");
                        ArrayList<String> lorefriendprofil = new ArrayList<String>();
                        lorefriendprofil.add("");
                        lorefriendprofil.add(" §aDescription:");
                        lorefriendprofil.add(" §f▶ §7Visionnez, gérez et");
                        lorefriendprofil.add(" §f  §7dévoilez vos réseaux ");
                        lorefriendprofil.add(" §f  §7au grand public ! ");
                        lorefriendprofil.add("");
                        lorefriendprofil.add("§8➡ §fCliquez pour y accéder.");
                        friendprofilM.setLore(lorefriendprofil);
                        friendprofil.setItemMeta(friendprofilM);
                        inv.setItem(30, friendprofil);
                    }
                    if (t == 1)
                    {
                        ItemStack friendprofil = getSkull("http://textures.minecraft.net/texture/6ad46a422ae59603fd889c25344ff67bc843af8ee518932c2e2ad07cdbf939b3");
                        ItemMeta friendprofilM = friendprofil.getItemMeta();
                        friendprofilM.setDisplayName("§6§lVos Réseaux Sociaux");
                        ArrayList<String> lorefriendprofil = new ArrayList<String>();
                        lorefriendprofil.add("");
                        lorefriendprofil.add(" §aDescription:");
                        lorefriendprofil.add(" §f▶ §7Visionnez, gérez et");
                        lorefriendprofil.add(" §f  §7dévoilez vos réseaux ");
                        lorefriendprofil.add(" §f  §7au grand public ! ");
                        lorefriendprofil.add("");
                        lorefriendprofil.add("§8➡ §fCliquez pour y accéder.");
                        friendprofilM.setLore(lorefriendprofil);
                        friendprofil.setItemMeta(friendprofilM);
                        inv.setItem(30, friendprofil);
                    }
                    if (t == 2)
                    {
                        ItemStack friendprofil = getSkull("http://textures.minecraft.net/texture/ed2b05abe4976c0af1f54b8d34e656d14383174ff5c6eba613a8a8405498cb15");
                        ItemMeta friendprofilM = friendprofil.getItemMeta();
                        friendprofilM.setDisplayName("§6§lVos Réseaux Sociaux");
                        ArrayList<String> lorefriendprofil = new ArrayList<String>();
                        lorefriendprofil.add("");
                        lorefriendprofil.add(" §aDescription:");
                        lorefriendprofil.add(" §f▶ §7Visionnez, gérez et");
                        lorefriendprofil.add(" §f  §7dévoilez vos réseaux ");
                        lorefriendprofil.add(" §f  §7au grand public ! ");
                        lorefriendprofil.add("");
                        lorefriendprofil.add("§8➡ §fCliquez pour y accéder.");
                        friendprofilM.setLore(lorefriendprofil);
                        friendprofil.setItemMeta(friendprofilM);
                        inv.setItem(30, friendprofil);
                    }
                    if (t == 3)
                    {
                        ItemStack friendprofil = getSkull("http://textures.minecraft.net/texture/739ee7154979b3f87735a1c8ac087814b7928d0576a2695ba01ed61631942045");
                        ItemMeta friendprofilM = friendprofil.getItemMeta();
                        friendprofilM.setDisplayName("§6§lVos Réseaux Sociaux");
                        ArrayList<String> lorefriendprofil = new ArrayList<String>();
                        lorefriendprofil.add("");
                        lorefriendprofil.add(" §aDescription:");
                        lorefriendprofil.add(" §f▶ §7Visionnez, gérez et");
                        lorefriendprofil.add(" §f  §7dévoilez vos réseaux ");
                        lorefriendprofil.add(" §f  §7au grand public ! ");
                        lorefriendprofil.add("");
                        lorefriendprofil.add("§8➡ §fCliquez pour y accéder.");
                        friendprofilM.setLore(lorefriendprofil);
                        friendprofil.setItemMeta(friendprofilM);
                        inv.setItem(30, friendprofil);
                    }

                    ++t;
                    if (t == 5) {
                        t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 15L);

        }
    }
}
