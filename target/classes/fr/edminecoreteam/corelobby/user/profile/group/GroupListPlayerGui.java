package fr.edminecoreteam.corelobby.user.profile.group;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.user.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.user.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.user.profile.friends.FriendInfo;
import fr.edminecoreteam.corelobby.user.profile.ranks.Rank;
import fr.edminecoreteam.corelobby.user.profile.ranks.RankData;
import fr.edminecoreteam.corelobby.user.profile.settings.SettingData;
import fr.edminecoreteam.corelobby.utils.SkullNBT;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

public class GroupListPlayerGui implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }
    static HashMap<Player, Integer> pageCount = new HashMap<Player, Integer>();
    static HashMap<Player, String> playerTools = new HashMap<Player, String>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        if (inv.getName().contains("§8Profil (Liste du groupe)"))
        {
            PartyData pInfo = new PartyData(p.getName());
            FriendInfo friendInfo = new FriendInfo(p.getName());
            int groupID = pInfo.getGroupName();
            int MaxPage = pInfo.getGroupPageNumber(groupID);
            e.setCancelled(true);
            if (it.getType() == Material.ARROW)
            {
                if(it.getItemMeta().getDisplayName() == "§8§l⬇ §7Retour §8§l⬇")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    GroupGui.gui(p);
                }
            }
            if (it.getType() == Material.SKULL_ITEM)
            {
                if(it.getItemMeta().getDisplayName() == "§c§lProfil")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    ProfileGUI.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§d§lAmis §c❤")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FriendGui.gui(p, 1, friendInfo.getFriendPageNumber());
                }
                if(it.getItemMeta().getDisplayName() == "§9§lGroupes")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    GroupGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§8➡ §7Page Suivante")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    pageCount.put(p, pageCount.get(p) + 1);
                    gui(p, pageCount.get(p), MaxPage, 1);
                }
                if(it.getItemMeta().getDisplayName() == "§8⬅ §7Page Précédente")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    pageCount.put(p, pageCount.get(p) - 1);
                    gui(p, pageCount.get(p), MaxPage, 1);
                }
            }
            if (it.getType() == Material.SKULL_ITEM)
            {
                if(it.getItemMeta().getDisplayName() == "§8⬅ §7Page Précédente" || it.getItemMeta().getDisplayName() == "§8➡ §7Page Suivante" || it.getItemMeta().getDisplayName() == "§c§lProfil" || it.getItemMeta().getDisplayName() == "§b§lVotre Guild" || it.getItemMeta().getDisplayName() == "§9§lGroupes" || it.getItemMeta().getDisplayName() == "§d§lAmis §c❤")
                {
                    e.setCancelled(true);
                    return;
                }
                else
                {
                    String target = it.getItemMeta().getDisplayName().replace("§e", "");
                    if (pInfo.getGroupLeader(groupID).contains(p.getName()))
                    {
                        if (target.equalsIgnoreCase(p.getName()))
                        {
                            e.setCancelled(true);
                            return;
                        }
                        playerTools.put(p, target);
                        playerTools(p, 2);
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    }
                    else if (pInfo.getGroupMods(groupID).contains(p.getName()))
                    {
                        if (target.equalsIgnoreCase(p.getName()))
                        {
                            e.setCancelled(true);
                            return;
                        }
                        if (pInfo.getGroupLeader(groupID).contains(target))
                        {
                            e.setCancelled(true);
                            return;
                        }
                        if (pInfo.getGroupMods(groupID).contains(target))
                        {
                            e.setCancelled(true);
                            return;
                        }
                        playerTools.put(p, target);
                        playerTools(p, 1);
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick2(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) { return; }

        if (inv.getName().equalsIgnoreCase("§8Profil (Gestion de " + playerTools.get(p) + ")"))
        {
            e.setCancelled(true);
            PartyData pInfo = new PartyData(p.getName());
            int groupID = pInfo.getGroupName();
            if (it.getType() == Material.ARROW)
            {
                if(it.getItemMeta().getDisplayName() == "§8§l⬇ §7Retour §8§l⬇")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    GroupListPlayerGui.gui(p, 1, pInfo.getGroupPageNumber(pInfo.getGroupName()), 0);
                }
            }
            if (pInfo.getGroupLeader(groupID).contains(p.getName()))
            {
                if (it.getType() == Material.RAW_FISH && it.getItemMeta().getDisplayName().contains("§c§lBannir")) {
                    confirmBanGroup(p, playerTools.get(p));
                }
                if (it.getType() == Material.DARK_OAK_DOOR_ITEM && it.getItemMeta().getDisplayName().contains("§c§lExpulser")) {
                    confirmKickGroup(p, playerTools.get(p));
                }
                if (it.getType() == Material.EMERALD && it.getItemMeta().getDisplayName().contains("§6§lPromote")) {
                    confirmPromoteGroup(p, playerTools.get(p));
                }
                if (it.getType() == Material.WRITTEN_BOOK && it.getItemMeta().getDisplayName().contains("§b§lGestion du rang de")) {
                    PartyData targetInfo = new PartyData(playerTools.get(p));
                    if (targetInfo.getRank(groupID) == 0)
                    {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        targetInfo.setRank(1, groupID);
                        if (pInfo.getGroupLeader(groupID).contains(p.getName()))
                        {
                            playerTools(p, 2);
                        }
                        else if (pInfo.getGroupMods(groupID).contains(p.getName()))
                        {
                            playerTools(p, 1);
                        }
                    }
                    else if (targetInfo.getRank(groupID) == 1)
                    {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        targetInfo.setRank(0, groupID);
                        if (pInfo.getGroupLeader(groupID).contains(p.getName()))
                        {
                            playerTools(p, 2);
                        }
                        else if (pInfo.getGroupMods(groupID).contains(p.getName()))
                        {
                            playerTools(p, 1);
                        }
                    }
                }
                if (it.getType() == Material.ENDER_PEARL && it.getItemMeta().getDisplayName().contains("§a§lTéléporter")) {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("BungeeCommand");
                    out.writeUTF("p warp" + playerTools.get(p));
                    p.sendPluginMessage((Plugin)api, "BungeeCord", out.toByteArray());
                }
            }
            else if (pInfo.getGroupMods(groupID).contains(p.getName()))
            {
                if (it.getType() == Material.RAW_FISH && it.getItemMeta().getDisplayName().contains("§c§lBannir")) {
                    confirmBanGroup(p, playerTools.get(p));
                }
                if (it.getType() == Material.WOODEN_DOOR && it.getItemMeta().getDisplayName().contains("§c§lExpulser")) {
                    confirmKickGroup(p, playerTools.get(p));
                }
            }
        }
    }

    public static void playerTools(Player p, int leaderOrMod)
    {
        if (leaderOrMod == 1)
        {
            Inventory inv = Bukkit.createInventory(null, 27, "§8Profil (Gestion de " + playerTools.get(p) + ")");
            p.openInventory(inv);
            new BukkitRunnable() {
                int t = 0;
                public void run() {

                    if (!p.getOpenInventory().getTitle().contains("§8Profil (Gestion de " + playerTools.get(p) + ")")) { playerTools.remove(p); cancel(); }
                    ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
                    ItemMeta decoM = deco.getItemMeta();
                    decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    decoM.setDisplayName("§r");
                    deco.setItemMeta(decoM);
                    inv.setItem(0, deco); inv.setItem(8, deco);
                    inv.setItem(18, deco); inv.setItem(26, deco);
                    ++t;
                    if (t == 10) {
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 10L);

            ItemStack back = new ItemStack(Material.ARROW, 1);
            ItemMeta backM = back.getItemMeta();
            backM.setDisplayName("§8§l⬇ §7Retour §8§l⬇");
            back.setItemMeta(backM);
            inv.setItem(22, back);

            ItemStack ban = new ItemStack(Material.RAW_FISH, 1);
            ItemMeta banM = ban.getItemMeta();
            banM.setDisplayName("§c§lBannir " + playerTools.get(p));
            ArrayList<String> loreban = new ArrayList<String>();
            loreban.add("");
            loreban.add(" §aDescription:");
            loreban.add(" §f▶ §7Cliquez pour bannir " + playerTools.get(p));
            loreban.add(" §f  §7de votre groupe.");
            loreban.add("");
            loreban.add("§8➡ §fCliquez pour bannir.");
            banM.setLore(loreban);
            ban.setItemMeta(banM);
            inv.setItem(12, ban);

            ItemStack kick = new ItemStack(Material.DARK_OAK_DOOR_ITEM, 1);
            ItemMeta kickM = kick.getItemMeta();
            kickM.setDisplayName("§c§lExpulser " + playerTools.get(p));
            ArrayList<String> lorekick = new ArrayList<String>();
            lorekick.add("");
            lorekick.add(" §aDescription:");
            lorekick.add(" §f▶ §7Cliquez pour expulser " + playerTools.get(p));
            lorekick.add(" §f  §7de votre groupe.");
            lorekick.add("");
            lorekick.add("§8➡ §fCliquez pour expulser.");
            kickM.setLore(lorekick);
            kick.setItemMeta(kickM);
            inv.setItem(14, kick);
        }
        else if (leaderOrMod == 2)
        {
            Inventory inv = Bukkit.createInventory(null, 27, "§8Profil (Gestion de " + playerTools.get(p) + ")");
            p.openInventory(inv);
            PartyData pToolsInfo = new PartyData(playerTools.get(p));
            int pToolsGroupID = pToolsInfo.getGroupName();
            new BukkitRunnable() {
                int t = 0;
                public void run() {

                    if (!p.getOpenInventory().getTitle().contains("§8Profil (Gestion de " + playerTools.get(p) + ")")) { playerTools.remove(p); cancel(); }
                    ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
                    ItemMeta decoM = deco.getItemMeta();
                    decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    decoM.setDisplayName("§r");
                    deco.setItemMeta(decoM);
                    inv.setItem(0, deco); inv.setItem(8, deco);
                    inv.setItem(18, deco); inv.setItem(26, deco);
                    ++t;
                    if (t == 10) {
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 10L);

            ItemStack back = new ItemStack(Material.ARROW, 1);
            ItemMeta backM = back.getItemMeta();
            backM.setDisplayName("§8§l⬇ §7Retour §8§l⬇");
            back.setItemMeta(backM);
            inv.setItem(22, back);

            ItemStack setrank = new ItemStack(Material.WRITTEN_BOOK, 1);
            ItemMeta setrankM = setrank.getItemMeta();
            setrankM.setDisplayName("§b§lGestion du rang de " + playerTools.get(p));
            ArrayList<String> loresetrank = new ArrayList<String>();
            loresetrank.add("");
            loresetrank.add(" §aDescription:");
            loresetrank.add(" §f▶ §7Modifier le rang de " + playerTools.get(p));
            loresetrank.add(" §f  §7parmi les choix suivants.");
            loresetrank.add("");
            loresetrank.add(" §dInformations:");
            if (pToolsInfo.getRank(pToolsGroupID) == 1)
            {
                loresetrank.add(" §f➟ §9[modérateur]");
                loresetrank.add(" §f▶ §8[joueur]");
            }
            else if (pToolsInfo.getRank(pToolsGroupID) == 0)
            {
                loresetrank.add(" §f▶ §8[modérateur]");
                loresetrank.add(" §f➟ §7[joueur]");
            }
            loresetrank.add("");
            loresetrank.add("§8➡ §fCliquez pour changer.");
            setrankM.setLore(loresetrank);
            setrank.setItemMeta(setrankM);
            inv.setItem(11, setrank);

            ItemStack ban = new ItemStack(Material.RAW_FISH, 1);
            ItemMeta banM = ban.getItemMeta();
            banM.setDisplayName("§c§lBannir " + playerTools.get(p));
            ArrayList<String> loreban = new ArrayList<String>();
            loreban.add("");
            loreban.add(" §aDescription:");
            loreban.add(" §f▶ §7Cliquez pour bannir " + playerTools.get(p));
            loreban.add(" §f  §7de votre groupe.");
            loreban.add("");
            loreban.add("§8➡ §fCliquez pour bannir.");
            banM.setLore(loreban);
            ban.setItemMeta(banM);
            inv.setItem(12, ban);

            ItemStack kick = new ItemStack(Material.DARK_OAK_DOOR_ITEM, 1);
            ItemMeta kickM = kick.getItemMeta();
            kickM.setDisplayName("§c§lExpulser " + playerTools.get(p));
            ArrayList<String> lorekick = new ArrayList<String>();
            lorekick.add("");
            lorekick.add(" §aDescription:");
            lorekick.add(" §f▶ §7Cliquez pour expulser " + playerTools.get(p));
            lorekick.add(" §f  §7de votre groupe.");
            lorekick.add("");
            lorekick.add("§8➡ §fCliquez pour expulser.");
            kickM.setLore(lorekick);
            kick.setItemMeta(kickM);
            inv.setItem(13, kick);

            ItemStack promote = new ItemStack(Material.EMERALD, 1);
            ItemMeta promoteM = promote.getItemMeta();
            promoteM.setDisplayName("§6§lPromote " + playerTools.get(p) + " Leader");
            ArrayList<String> lorepromote = new ArrayList<String>();
            lorepromote.add("");
            lorepromote.add(" §aDescription:");
            lorepromote.add(" §f▶ §7Cliquez pour rendre " + playerTools.get(p));
            lorepromote.add(" §f  §6§lLeader §7du groupe.");
            lorepromote.add("");
            lorepromote.add("§8➡ §fCliquez pour changer.");
            promoteM.setLore(lorepromote);
            promote.setItemMeta(promoteM);
            inv.setItem(14, promote);

            ItemStack warp = new ItemStack(Material.ENDER_PEARL, 1);
            ItemMeta warpM = warp.getItemMeta();
            warpM.setDisplayName("§a§lTéléporter " + playerTools.get(p));
            ArrayList<String> lorewarp= new ArrayList<String>();
            lorewarp.add("");
            lorewarp.add(" §aDescription:");
            lorewarp.add(" §f▶ §7Cliquez pour téléporter " + playerTools.get(p));
            lorewarp.add(" §f  §7sur votre serveur actuel.");
            lorewarp.add("");
            lorewarp.add("§8➡ §fCliquez pour téléporter.");
            warpM.setLore(lorewarp);
            warp.setItemMeta(warpM);
            inv.setItem(15, warp);
        }
    }

    public static void gui(Player p, int Page, int MaxPage, int sort) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil (Liste du groupe) " + Page + "/" + MaxPage);
        p.openInventory(inv);
        FriendInfo friendInfo = new FriendInfo(p.getName());
        PartyData pInfo = new PartyData(p.getName());
        pageCount.put(p, Page);
        int friendPerPage = 10;

        ItemStack back = new ItemStack(Material.ARROW, 1);
        ItemMeta backM = back.getItemMeta();
        backM.setDisplayName("§8§l⬇ §7Retour §8§l⬇");
        back.setItemMeta(backM);
        inv.setItem(49, back);

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


        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (!p.getOpenInventory().getTitle().contains("§8Profil (Liste du groupe)")) { cancel(); }
                ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
                ItemMeta decoM = deco.getItemMeta();
                decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                decoM.setDisplayName("§r");
                deco.setItemMeta(decoM);
                inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);
                ++t;
                if (t == 10) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)api, 0L, 10L);

        ItemStack profil = getSkull("http://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b");
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

        ItemStack friendprofil = getSkull("http://textures.minecraft.net/texture/8cc95d7622ec32c5cc31ff7a9e2d545f951179a6c78af837b4e756c1f75a61");
        ItemMeta friendprofilM = friendprofil.getItemMeta();
        friendprofilM.setDisplayName("§d§lAmis §c❤");
        ArrayList<String> lorefriendprofil = new ArrayList<String>();
        lorefriendprofil.add("");
        lorefriendprofil.add(" §aDescription:");
        lorefriendprofil.add(" §f▶ §7Visionnez et gérez");
        lorefriendprofil.add(" §f  §7votre liste d'amis.");
        lorefriendprofil.add("");
        lorefriendprofil.add(" §f▶ §7Nombre d'amis: §6" + friendInfo.getFriendCount());
        lorefriendprofil.add("");
        lorefriendprofil.add("§8➡ §fCliquez pour y accéder.");
        friendprofilM.setLore(lorefriendprofil);
        friendprofil.setItemMeta(friendprofilM);
        inv.setItem(35, friendprofil);

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

        int groupeID = pInfo.getGroupName();
        int slot = 20;
        int friendsCount = 0;
        for (String groups : pInfo.getGroupForPage(Page, groupeID)) {
            ++friendsCount;
            RankData rankInfo = new RankData(groups);
            SettingData settingInfo = new SettingData(groups);
            ItemStack group = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
            SkullMeta groupM = (SkullMeta) group.getItemMeta();
            groupM.setOwner(groups);
            groupM.setDisplayName("§e" + groups);
            ArrayList<String> loregroup = new ArrayList<String>();
            loregroup.add("");
            loregroup.add(" §dInformation:");
            if (pInfo.getGroupLeader(groupeID).contains(groups))
            {
                loregroup.add(" §f▶ §7Rang de groupe: §6§lLeader");
            }
            else if (pInfo.getGroupMods(groupeID).contains(groups))
            {
                loregroup.add(" §f▶ §7Rang de groupe: §9§lModérateur");
            }
            else if (pInfo.getGroupPlayers(groupeID).contains(groups))
            {
                loregroup.add(" §f▶ §7Rang de groupe: §7Joueur");
            }
            if (rankInfo.getRankTypeString().equalsIgnoreCase("staff"))
            {
                loregroup.add(" §f▶ §7Grade: " + Rank.powerToRank(rankInfo.getRankModuleString()).getDisplayName());
            }
            if (rankInfo.getRankTypeString().equalsIgnoreCase("static"))
            {
                if (rankInfo.getRankIDString() > 0)
                {
                    loregroup.add(" §f▶ §7Grade: " + Rank.powerToRank(rankInfo.getRankIDString()).getDisplayName());
                }
                else if (rankInfo.getRankIDString() == 0)
                {
                    loregroup.add(" §f▶ §7Grade: §caucun");
                }
            }
            if (rankInfo.getRankTypeString().equalsIgnoreCase("tempo"))
            {
                loregroup.add(" §f▶ §7Grade: " + Rank.powerToRank(rankInfo.getRankIDString()).getDisplayName());
            }
            if (rankInfo.getRankTypeString().equalsIgnoreCase("module"))
            {
                loregroup.add(" §f▶ §7Grade: " + Rank.powerToRank(rankInfo.getRankModuleString()).getDisplayName());
            }
            if (settingInfo.getGroupFollowString().equalsIgnoreCase("activer"))
            {
                loregroup.add(" §f▶ §7Suivi de groupe: §aActivé");
            }
            else
            {
                loregroup.add(" §f▶ §7Suivi de groupe: §cDésactivé");
            }
            loregroup.add("");
            if (pInfo.getGroupLeader(groupeID).contains(p.getName()) || pInfo.getGroupMods(groupeID).contains(p.getName()))
            {
                if (!groups.equalsIgnoreCase(p.getName()))
                {
                    loregroup.add("§8➡ §fCliquez pour y accéder.");
                }
            }
            groupM.setLore(loregroup);
            group.setItemMeta(groupM);


            if (friendsCount == friendPerPage)
            {
                inv.setItem(slot, group);
            }
            else if (friendsCount != friendPerPage)
            {
                inv.setItem(slot, group);
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

    public static void confirmBanGroup(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez bannir §c" + target + " §fdu groupe?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour bannir " + target + ".").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p ban " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void confirmKickGroup(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez expulser §c" + target + " §fdu groupe?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour expulser " + target + ".").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p kick " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void confirmPromoteGroup(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez promouvoir §a" + target + " §fchef du groupe?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour promouvoir " + target + ".").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p promote " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }
}
