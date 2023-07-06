package fr.edminecoreteam.corelobby.profile.group;

import java.util.ArrayList;

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

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.listeners.PlayerChatListener;
import fr.edminecoreteam.corelobby.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.profile.friends.FriendInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;


public class GroupGui implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }

        if (inv.getName().contains("§8Profil (Groupe)"))
        {
            FriendInfo friendInfo = new FriendInfo(p.getName());
            PartyData groupInfo = new PartyData(p.getName());
            e.setCancelled(true);
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
            }
            if (it.getType() == Material.SKULL_ITEM && it.getItemMeta().getDisplayName() == "§fCréer un groupe") {
                sendInvite(p);
            }
            if (it.getType() == Material.SKULL_ITEM && it.getItemMeta().getDisplayName() == "§9§lInviter un joueur") {
                sendInvite(p);
            }
            if (it.getType() == Material.SKULL_ITEM && it.getItemMeta().getDisplayName() == "§aRejoindre un groupe") {
                joinGroup(p);
            }
            if (it.getType() == Material.EYE_OF_ENDER && it.getItemMeta().getDisplayName() == "§d§lTéléporter votre groupe") {
                confirmTeleportPlayers(p);
            }
            if (it.getType() == Material.EYE_OF_ENDER && it.getItemMeta().getDisplayName() == "§d§lRejoindre votre Leader") {
                confirmTeleportToLeader(p);
            }
            if (it.getType() == Material.PAPER && it.getItemMeta().getDisplayName() == "§6§lÉcrire un message") {
                sendChat(p);
            }
            if (it.getType() == Material.SKULL_ITEM && it.getItemMeta().getDisplayName() == "§e§lGérez vos joueurs") {
                p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                GroupListPlayerGui.gui(p, 1, groupInfo.getGroupPageNumber(groupInfo.getGroupName()), 0);
            }
            if (it.getType() == Material.SKULL_ITEM && it.getItemMeta().getDisplayName() == "§e§lListe du groupe") {
                p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                GroupListPlayerGui.gui(p, 1, groupInfo.getGroupPageNumber(groupInfo.getGroupName()), 0);
            }
            if (it.getType() == Material.INK_SACK && it.getItemMeta().getDisplayName() == "§c§lQuitter le groupe") {
                confirmLeaveGroup(p);
                p.closeInventory();
            }
            if (it.getType() == Material.INK_SACK && it.getItemMeta().getDisplayName() == "§c§lDissoudre le groupe") {
                confirmDisbandGroup(p);
                p.closeInventory();
            }
            if (it.getType() == Material.BUCKET && it.getItemMeta().getDisplayName() == "§e§lGestion du chat") {
                int groupID = groupInfo.getGroupName();
                if (groupInfo.getGroupLeader(groupID).contains(p.getName()))
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    groupInfo.setChat("enable", groupID);
                    gui(p);
                }
            }
            if (it.getType() == Material.NAME_TAG && it.getItemMeta().getDisplayName() == "§f§lTag du groupe") {
                int groupID = groupInfo.getGroupName();
                if (groupInfo.getGroupLeader(groupID).contains(p.getName()))
                {
                    createGroupTag(p);
                    p.closeInventory();
                }
            }
            if (it.getType() == Material.MILK_BUCKET && it.getItemMeta().getDisplayName() == "§e§lGestion du chat") {
                int groupID = groupInfo.getGroupName();
                if (groupInfo.getGroupLeader(groupID).contains(p.getName()))
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    groupInfo.setChat("disable", groupID);
                    gui(p);
                }
            }
            if (it.getType() == Material.POWERED_MINECART && it.getItemMeta().getDisplayName() == "§b§lStatut du groupe") {
                int groupID = groupInfo.getGroupName();
                if (groupInfo.getGroupLeader(groupID).contains(p.getName()))
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    groupInfo.setStatus(2, groupID);
                    gui(p);
                }
            }
            if (it.getType() == Material.STORAGE_MINECART && it.getItemMeta().getDisplayName() == "§b§lStatut du groupe") {
                int groupID = groupInfo.getGroupName();
                if (groupInfo.getGroupLeader(groupID).contains(p.getName()))
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    groupInfo.setStatus(1, groupID);
                    gui(p);
                }
            }
        }
    }

    public static void gui(Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil (Groupe) ");
        p.openInventory(inv);
        FriendInfo friendInfo = new FriendInfo(p.getName());
        PartyData pData = new PartyData(p.getName());


        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (!p.getOpenInventory().getTitle().contains("§8Profil (Groupe)")) { cancel(); }
                ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)11);
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

        ItemStack groupprofil = getSkull("http://textures.minecraft.net/texture/bc76b4e9fd87b77b8c95a8d18484168cbeb78fab5d529d6b6c132246cc3cbac");
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

        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (!p.getOpenInventory().getTitle().contains("§8Profil (Groupe)")) { cancel(); }
                if (pData.hasGroup())
                {
                    int groupID = pData.getGroupName();

                    ItemStack sendGroupMessage = new ItemStack(Material.PAPER, 1);
                    ItemMeta sendGroupMessageM = sendGroupMessage.getItemMeta();
                    sendGroupMessageM.setDisplayName("§6§lÉcrire un message");
                    ArrayList<String> loresendGroupMessage = new ArrayList<String>();
                    loresendGroupMessage.add("");
                    loresendGroupMessage.add(" §aDescription:");
                    loresendGroupMessage.add(" §f▶ §7Envoyez de manière");
                    loresendGroupMessage.add(" §f  §7simple un message au");
                    loresendGroupMessage.add(" §f  §7joueurs de votre groupe.");
                    loresendGroupMessage.add("");
                    loresendGroupMessage.add("§8➡ §fCliquez pour envoyer.");
                    sendGroupMessageM.setLore(loresendGroupMessage);
                    sendGroupMessage.setItemMeta(sendGroupMessageM);
                    inv.setItem(23, sendGroupMessage);

                    if (pData.getGroupLeader(groupID).contains(p.getName()))
                    {
                        ItemStack creategroup = getSkull("http://textures.minecraft.net/texture/eaea5d7127214ee2cca1b82ef82f8ae55929325766ea84dde18c11de0c7d591");
                        ItemMeta creategroupM = creategroup.getItemMeta();
                        creategroupM.setDisplayName("§9§lInviter un joueur");
                        ArrayList<String> lorecreategroup = new ArrayList<String>();
                        lorecreategroup.add("");
                        lorecreategroup.add(" §aDescription:");
                        lorecreategroup.add(" §f▶ §7Invitez un joueur");
                        lorecreategroup.add(" §f  §7de manière simple");
                        lorecreategroup.add(" §f  §7dans votre groupe.");
                        lorecreategroup.add("");
                        lorecreategroup.add("§8➡ §fCliquez pour y accéder.");
                        creategroupM.setLore(lorecreategroup);
                        creategroup.setItemMeta(creategroupM);
                        inv.setItem(4, creategroup);

                        ItemStack leaveGroup = new ItemStack(Material.INK_SACK, 1, (short)1);
                        ItemMeta leaveGroupM = leaveGroup.getItemMeta();
                        leaveGroupM.setDisplayName("§c§lDissoudre le groupe");
                        ArrayList<String> loreleaveGroup = new ArrayList<String>();
                        loreleaveGroup.add("");
                        loreleaveGroup.add(" §aDescription:");
                        loreleaveGroup.add(" §f▶ §7Dissolvez votre groupe");
                        loreleaveGroup.add(" §f  §7en appuyant ici.");
                        loreleaveGroup.add("");
                        loreleaveGroup.add("§8➡ §fCliquez pour y accéder.");
                        leaveGroupM.setLore(loreleaveGroup);
                        leaveGroup.setItemMeta(leaveGroupM);
                        inv.setItem(49, leaveGroup);

                        ItemStack groupTag = new ItemStack(Material.NAME_TAG, 1);
                        ItemMeta groupTagM = groupTag.getItemMeta();
                        groupTagM.setDisplayName("§f§lTag du groupe");
                        ArrayList<String> loregroupTag = new ArrayList<String>();
                        loregroupTag.add("");
                        loregroupTag.add(" §aDescription:");
                        loregroupTag.add(" §f▶ §7Affichez aux yeux");
                        loregroupTag.add(" §f  §7des joueurs les jeux");
                        loregroupTag.add(" §f  §7sur lesquels vous jouez !");
                        loregroupTag.add("");
                        loregroupTag.add(" §dInformations:");
                        loregroupTag.add(" §f▶ §7Tag du groupe: §b" + pData.getGroupTag(groupID, pData.getGroupLeader(groupID).get(0)));
                        loregroupTag.add("");
                        loregroupTag.add("§8➡ §fCliquez pour changer.");
                        groupTagM.setLore(loregroupTag);
                        groupTag.setItemMeta(groupTagM);
                        inv.setItem(22, groupTag);

                        ItemStack teleport = new ItemStack(Material.EYE_OF_ENDER, 1);
                        ItemMeta teleportM = teleport.getItemMeta();
                        teleportM.setDisplayName("§d§lTéléporter votre groupe");
                        ArrayList<String> loreteleport = new ArrayList<String>();
                        loreteleport.add("");
                        loreteleport.add(" §aDescription:");
                        loreteleport.add(" §f▶ §7Cliquez pour téléporter");
                        loreteleport.add(" §f  §7les joueurs de votre");
                        loreteleport.add(" §f  §7groupe sur votre serveur.");
                        loreteleport.add("");
                        loreteleport.add("§8➡ §fCliquez pour confirmer.");
                        teleportM.setLore(loreteleport);
                        teleport.setItemMeta(teleportM);
                        inv.setItem(31, teleport);

                        ItemStack playerList = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta playerListM = (SkullMeta) playerList.getItemMeta();
                        playerListM.setDisplayName("§e§lGérez vos joueurs");
                        playerListM.setOwner(pData.getGroupLeader(groupID).get(0));
                        ArrayList<String> loreplayerList = new ArrayList<String>();
                        loreplayerList.add("");
                        loreplayerList.add(" §aDescription:");
                        loreplayerList.add(" §f▶ §7Gérez les joueurs");
                        loreplayerList.add(" §f  §7de votre groupe via");
                        loreplayerList.add(" §f  §7cette liste.");
                        loreplayerList.add("");
                        loreplayerList.add(" §dInformations:");
                        loreplayerList.add(" §f▶ §7Nombre de joueurs: §a" + pData.getGroupList(groupID).size());
                        loreplayerList.add("");
                        loreplayerList.add("§8➡ §fCliquez pour y accéder.");
                        playerListM.setLore(loreplayerList);
                        playerList.setItemMeta(playerListM);
                        inv.setItem(21, playerList);

                        if (pData.getChat(groupID, pData.getLeader(groupID)).equalsIgnoreCase("enable"))
                        {
                            ItemStack activeChat = new ItemStack(Material.MILK_BUCKET, 1);
                            ItemMeta activeChatM = activeChat.getItemMeta();
                            activeChatM.setDisplayName("§e§lGestion du chat");
                            ArrayList<String> loreactiveChat = new ArrayList<String>();
                            loreactiveChat.add("");
                            loreactiveChat.add(" §aDescription:");
                            loreactiveChat.add(" §f▶ §7Activez ou désactivez");
                            loreactiveChat.add(" §f  §7le chat du groupe.");
                            loreactiveChat.add("");
                            loreactiveChat.add(" §dInformations:");
                            loreactiveChat.add(" §f➟ §a[activé]");
                            loreactiveChat.add(" §f▶ §8[désactivé]");
                            loreactiveChat.add("");
                            loreactiveChat.add("§8➡ §fCliquez pour y accéder.");
                            activeChatM.setLore(loreactiveChat);
                            activeChat.setItemMeta(activeChatM);
                            inv.setItem(32, activeChat);
                        }
                        else
                        {
                            ItemStack activeChat = new ItemStack(Material.BUCKET, 1);
                            ItemMeta activeChatM = activeChat.getItemMeta();
                            activeChatM.setDisplayName("§e§lGestion du chat");
                            ArrayList<String> loreactiveChat = new ArrayList<String>();
                            loreactiveChat.add("");
                            loreactiveChat.add(" §aDescription:");
                            loreactiveChat.add(" §f▶ §7Envoyez de manière");
                            loreactiveChat.add(" §f  §7simple un message au");
                            loreactiveChat.add(" §f  §7joueurs de votre groupe.");
                            loreactiveChat.add("");
                            loreactiveChat.add(" §dInformations:");
                            loreactiveChat.add(" §f▶ §8[activé]");
                            loreactiveChat.add(" §f➟ §c[désactivé]");
                            loreactiveChat.add("");
                            loreactiveChat.add("§8➡ §fCliquez pour y accéder.");
                            activeChatM.setLore(loreactiveChat);
                            activeChat.setItemMeta(activeChatM);
                            inv.setItem(32, activeChat);
                        }

                        if (pData.getGroupStatus() == 1)
                        {
                            ItemStack publicOrPrivate = new ItemStack(Material.POWERED_MINECART, 1);
                            ItemMeta publicOrPrivateM = publicOrPrivate.getItemMeta();
                            publicOrPrivateM.setDisplayName("§b§lStatut du groupe");
                            ArrayList<String> lorepublicOrPrivate = new ArrayList<String>();
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §aDescription:");
                            lorepublicOrPrivate.add(" §f▶ §7Votre groupe est joignable");
                            lorepublicOrPrivate.add(" §f  §7uniquement sur invitations.");
                            lorepublicOrPrivate.add(" §f▶ §d/§fp invite §a[joueur]§7.");
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §dInformations:");
                            lorepublicOrPrivate.add(" §f▶ §8[publique]");
                            lorepublicOrPrivate.add(" §f➟ §c[privé]");
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add("§8➡ §fCliquez pour changer.");
                            publicOrPrivateM.setLore(lorepublicOrPrivate);
                            publicOrPrivate.setItemMeta(publicOrPrivateM);
                            inv.setItem(30, publicOrPrivate);
                        }
                        else if (pData.getGroupStatus() == 2)
                        {
                            ItemStack publicOrPrivate = new ItemStack(Material.STORAGE_MINECART, 1);
                            ItemMeta publicOrPrivateM = publicOrPrivate.getItemMeta();
                            publicOrPrivateM.setDisplayName("§b§lStatut du groupe");
                            ArrayList<String> lorepublicOrPrivate = new ArrayList<String>();
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §aDescription:");
                            lorepublicOrPrivate.add(" §f▶ §7Votre groupe est joignable");
                            lorepublicOrPrivate.add(" §f  §7par tous le mondes.");
                            lorepublicOrPrivate.add(" §f▶ §d/§fp join §a" + pData.getGroupLeader(groupID).get(0) + "§7.");
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §dInformations:");
                            lorepublicOrPrivate.add(" §f➟ §a[publique]");
                            lorepublicOrPrivate.add(" §f▶ §8[privé]");
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add("§8➡ §fCliquez pour changer.");
                            publicOrPrivateM.setLore(lorepublicOrPrivate);
                            publicOrPrivate.setItemMeta(publicOrPrivateM);
                            inv.setItem(30, publicOrPrivate);
                        }
                    }
                    else if (pData.getGroupMods(groupID).contains(p.getName()))
                    {
                        ItemStack creategroup = getSkull("http://textures.minecraft.net/texture/eaea5d7127214ee2cca1b82ef82f8ae55929325766ea84dde18c11de0c7d591");
                        ItemMeta creategroupM = creategroup.getItemMeta();
                        creategroupM.setDisplayName("§9§lInviter un joueur");
                        ArrayList<String> lorecreategroup = new ArrayList<String>();
                        lorecreategroup.add("");
                        lorecreategroup.add(" §aDescription:");
                        lorecreategroup.add(" §f▶ §7Invitez un joueur");
                        lorecreategroup.add(" §f  §7de manière simple");
                        lorecreategroup.add(" §f  §7dans votre groupe.");
                        lorecreategroup.add("");
                        lorecreategroup.add("§8➡ §fCliquez pour y accéder.");
                        creategroupM.setLore(lorecreategroup);
                        creategroup.setItemMeta(creategroupM);
                        inv.setItem(4, creategroup);

                        ItemStack leaveGroup = new ItemStack(Material.INK_SACK, 1, (short)1);
                        ItemMeta leaveGroupM = leaveGroup.getItemMeta();
                        leaveGroupM.setDisplayName("§c§lQuitter le groupe");
                        ArrayList<String> loreleaveGroup = new ArrayList<String>();
                        loreleaveGroup.add("");
                        loreleaveGroup.add(" §aDescription:");
                        loreleaveGroup.add(" §f▶ §7Quittez votre groupe");
                        loreleaveGroup.add(" §f  §7en appuyant ici.");
                        loreleaveGroup.add("");
                        loreleaveGroup.add("§8➡ §fCliquez pour y accéder.");
                        leaveGroupM.setLore(loreleaveGroup);
                        leaveGroup.setItemMeta(leaveGroupM);
                        inv.setItem(49, leaveGroup);

                        ItemStack teleport = new ItemStack(Material.EYE_OF_ENDER, 1);
                        ItemMeta teleportM = teleport.getItemMeta();
                        teleportM.setDisplayName("§d§lRejoindre votre Leader");
                        ArrayList<String> loreteleport = new ArrayList<String>();
                        loreteleport.add("");
                        loreteleport.add(" §aDescription:");
                        loreteleport.add(" §f▶ §7Cliquez pour vous téléporter");
                        loreteleport.add(" §f  §7sur le serveur de votre Leader.");
                        loreteleport.add("");
                        loreteleport.add("§8➡ §fCliquez pour confirmer.");
                        teleportM.setLore(loreteleport);
                        teleport.setItemMeta(teleportM);
                        inv.setItem(31, teleport);

                        ItemStack playerList = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta playerListM = (SkullMeta) playerList.getItemMeta();
                        playerListM.setDisplayName("§e§lGérez vos joueurs");
                        playerListM.setOwner(pData.getGroupLeader(groupID).get(0));
                        ArrayList<String> loreplayerList = new ArrayList<String>();
                        loreplayerList.add("");
                        loreplayerList.add(" §aDescription:");
                        loreplayerList.add(" §f▶ §7Gérez les joueurs");
                        loreplayerList.add(" §f  §7de votre groupe via");
                        loreplayerList.add(" §f  §7cette liste.");
                        loreplayerList.add("");
                        loreplayerList.add(" §dInformations:");
                        loreplayerList.add(" §f▶ §7Nombre de joueurs: §a" + pData.getGroupList(groupID).size());
                        loreplayerList.add("");
                        loreplayerList.add("§8➡ §fCliquez pour y accéder.");
                        playerListM.setLore(loreplayerList);
                        playerList.setItemMeta(playerListM);
                        inv.setItem(21, playerList);

                        ItemStack groupTag = new ItemStack(Material.NAME_TAG, 1);
                        ItemMeta groupTagM = groupTag.getItemMeta();
                        groupTagM.setDisplayName("§f§lTag du groupe");
                        ArrayList<String> loregroupTag = new ArrayList<String>();
                        loregroupTag.add("");
                        loregroupTag.add(" §aDescription:");
                        loregroupTag.add(" §f▶ §7Affichez aux yeux");
                        loregroupTag.add(" §f  §7des joueurs les jeux");
                        loregroupTag.add(" §f  §7sur lesquels vous jouez !");
                        loregroupTag.add("");
                        loregroupTag.add(" §dInformations:");
                        loregroupTag.add(" §f▶ §7Tag du groupe: §b" + pData.getGroupTag(groupID, pData.getGroupLeader(groupID).get(0)));
                        loregroupTag.add("");
                        groupTagM.setLore(loregroupTag);
                        groupTag.setItemMeta(groupTagM);
                        inv.setItem(22, groupTag);

                        if (pData.getChat(groupID, pData.getLeader(groupID)).equalsIgnoreCase("enable"))
                        {
                            ItemStack activeChat = new ItemStack(Material.MILK_BUCKET, 1);
                            ItemMeta activeChatM = activeChat.getItemMeta();
                            activeChatM.setDisplayName("§e§lGestion du chat");
                            ArrayList<String> loreactiveChat = new ArrayList<String>();
                            loreactiveChat.add("");
                            loreactiveChat.add(" §aDescription:");
                            loreactiveChat.add(" §f▶ §7Activez ou désactivez");
                            loreactiveChat.add(" §f  §7le chat du groupe.");
                            loreactiveChat.add("");
                            loreactiveChat.add(" §dInformations:");
                            loreactiveChat.add(" §f➟ §a[activé]");
                            loreactiveChat.add(" §f▶ §8[désactivé]");
                            loreactiveChat.add("");
                            activeChatM.setLore(loreactiveChat);
                            activeChat.setItemMeta(activeChatM);
                            inv.setItem(32, activeChat);
                        }
                        else
                        {
                            ItemStack activeChat = new ItemStack(Material.BUCKET, 1);
                            ItemMeta activeChatM = activeChat.getItemMeta();
                            activeChatM.setDisplayName("§e§lGestion du chat");
                            ArrayList<String> loreactiveChat = new ArrayList<String>();
                            loreactiveChat.add("");
                            loreactiveChat.add(" §aDescription:");
                            loreactiveChat.add(" §f▶ §7Envoyez de manière");
                            loreactiveChat.add(" §f  §7simple un message au");
                            loreactiveChat.add(" §f  §7joueurs de votre groupe.");
                            loreactiveChat.add("");
                            loreactiveChat.add(" §dInformations:");
                            loreactiveChat.add(" §f▶ §8[activé]");
                            loreactiveChat.add(" §f➟ §c[désactivé]");
                            loreactiveChat.add("");
                            activeChatM.setLore(loreactiveChat);
                            activeChat.setItemMeta(activeChatM);
                            inv.setItem(32, activeChat);
                        }

                        PartyData leaderData = new PartyData(pData.getGroupLeader(groupID).get(0));
                        if (leaderData.getGroupStatus() == 1)
                        {
                            ItemStack publicOrPrivate = new ItemStack(Material.POWERED_MINECART, 1);
                            ItemMeta publicOrPrivateM = publicOrPrivate.getItemMeta();
                            publicOrPrivateM.setDisplayName("§b§lStatut du groupe");
                            ArrayList<String> lorepublicOrPrivate = new ArrayList<String>();
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §aDescription:");
                            lorepublicOrPrivate.add(" §f▶ §7Votre groupe est joignable");
                            lorepublicOrPrivate.add(" §f  §7uniquement sur invitations.");
                            lorepublicOrPrivate.add(" §f▶ §d/§fp invite §a[joueur]§7.");
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §dInformations:");
                            lorepublicOrPrivate.add(" §f▶ §8[publique]");
                            lorepublicOrPrivate.add(" §f➟ §c[privé]");
                            lorepublicOrPrivate.add("");
                            publicOrPrivateM.setLore(lorepublicOrPrivate);
                            publicOrPrivate.setItemMeta(publicOrPrivateM);
                            inv.setItem(30, publicOrPrivate);
                        }
                        else if (leaderData.getGroupStatus() == 2)
                        {
                            ItemStack publicOrPrivate = new ItemStack(Material.STORAGE_MINECART, 1);
                            ItemMeta publicOrPrivateM = publicOrPrivate.getItemMeta();
                            publicOrPrivateM.setDisplayName("§b§lStatut du groupe");
                            ArrayList<String> lorepublicOrPrivate = new ArrayList<String>();
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §aDescription:");
                            lorepublicOrPrivate.add(" §f▶ §7Votre groupe est joignable");
                            lorepublicOrPrivate.add(" §f  §7par tous le mondes.");
                            lorepublicOrPrivate.add(" §f▶ §d/§fp join §a" + pData.getGroupLeader(groupID).get(0) + "§7.");
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §dInformations:");
                            lorepublicOrPrivate.add(" §f➟ §a[publique]");
                            lorepublicOrPrivate.add(" §f▶ §8[privé]");
                            lorepublicOrPrivate.add("");
                            publicOrPrivateM.setLore(lorepublicOrPrivate);
                            publicOrPrivate.setItemMeta(publicOrPrivateM);
                            inv.setItem(30, publicOrPrivate);
                        }
                    }
                    else if (pData.getGroupPlayers(groupID).contains(p.getName()))
                    {
                        ItemStack leaveGroup = new ItemStack(Material.INK_SACK, 1, (short)1);
                        ItemMeta leaveGroupM = leaveGroup.getItemMeta();
                        leaveGroupM.setDisplayName("§c§lQuitter le groupe");
                        ArrayList<String> loreleaveGroup = new ArrayList<String>();
                        loreleaveGroup.add("");
                        loreleaveGroup.add(" §aDescription:");
                        loreleaveGroup.add(" §f▶ §7Quittez votre groupe");
                        loreleaveGroup.add(" §f  §7en appuyant ici.");
                        loreleaveGroup.add("");
                        loreleaveGroup.add("§8➡ §fCliquez pour y accéder.");
                        leaveGroupM.setLore(loreleaveGroup);
                        leaveGroup.setItemMeta(leaveGroupM);
                        inv.setItem(49, leaveGroup);

                        ItemStack playerList = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        SkullMeta playerListM = (SkullMeta) playerList.getItemMeta();
                        playerListM.setDisplayName("§e§lListe du groupe");
                        playerListM.setOwner(pData.getGroupLeader(groupID).get(0));
                        ArrayList<String> loreplayerList = new ArrayList<String>();
                        loreplayerList.add("");
                        loreplayerList.add(" §aDescription:");
                        loreplayerList.add(" §f▶ §7Visionnez la liste");
                        loreplayerList.add(" §f  §7des joueurs présents");
                        loreplayerList.add(" §f  §7dans le groupe.");
                        loreplayerList.add("");
                        loreplayerList.add(" §dInformations:");
                        loreplayerList.add(" §f▶ §7Nombre de joueurs: §a" + pData.getGroupList(groupID).size());
                        loreplayerList.add("");
                        loreplayerList.add("§8➡ §fCliquez pour y accéder.");
                        playerListM.setLore(loreplayerList);
                        playerList.setItemMeta(playerListM);
                        inv.setItem(21, playerList);

                        ItemStack groupTag = new ItemStack(Material.NAME_TAG, 1);
                        ItemMeta groupTagM = groupTag.getItemMeta();
                        groupTagM.setDisplayName("§f§lTag du groupe");
                        ArrayList<String> loregroupTag = new ArrayList<String>();
                        loregroupTag.add("");
                        loregroupTag.add(" §aDescription:");
                        loregroupTag.add(" §f▶ §7Affichez aux yeux");
                        loregroupTag.add(" §f  §7des joueurs les jeux");
                        loregroupTag.add(" §f  §7sur lesquels vous jouez !");
                        loregroupTag.add("");
                        loregroupTag.add(" §dInformations:");
                        loregroupTag.add(" §f▶ §7Tag du groupe: §b" + pData.getGroupTag(groupID, pData.getGroupLeader(groupID).get(0)));
                        loregroupTag.add("");
                        groupTagM.setLore(loregroupTag);
                        groupTag.setItemMeta(groupTagM);
                        inv.setItem(22, groupTag);

                        ItemStack teleport = new ItemStack(Material.EYE_OF_ENDER, 1);
                        ItemMeta teleportM = teleport.getItemMeta();
                        teleportM.setDisplayName("§d§lRejoindre votre Leader");
                        ArrayList<String> loreteleport = new ArrayList<String>();
                        loreteleport.add("");
                        loreteleport.add(" §aDescription:");
                        loreteleport.add(" §f▶ §7Cliquez pour vous téléporter");
                        loreteleport.add(" §f  §7sur le serveur de votre Leader.");
                        loreteleport.add("");
                        loreteleport.add("§8➡ §fCliquez pour confirmer.");
                        teleportM.setLore(loreteleport);
                        teleport.setItemMeta(teleportM);
                        inv.setItem(31, teleport);

                        if (pData.getChat(groupID, pData.getLeader(groupID)).equalsIgnoreCase("enable"))
                        {
                            ItemStack activeChat = new ItemStack(Material.MILK_BUCKET, 1);
                            ItemMeta activeChatM = activeChat.getItemMeta();
                            activeChatM.setDisplayName("§e§lGestion du chat");
                            ArrayList<String> loreactiveChat = new ArrayList<String>();
                            loreactiveChat.add("");
                            loreactiveChat.add(" §aDescription:");
                            loreactiveChat.add(" §f▶ §7Activez ou désactivez");
                            loreactiveChat.add(" §f  §7le chat du groupe.");
                            loreactiveChat.add("");
                            loreactiveChat.add(" §dInformations:");
                            loreactiveChat.add(" §f➟ §a[activé]");
                            loreactiveChat.add(" §f▶ §8[désactivé]");
                            loreactiveChat.add("");
                            activeChatM.setLore(loreactiveChat);
                            activeChat.setItemMeta(activeChatM);
                            inv.setItem(32, activeChat);
                        }
                        else
                        {
                            ItemStack activeChat = new ItemStack(Material.BUCKET, 1);
                            ItemMeta activeChatM = activeChat.getItemMeta();
                            activeChatM.setDisplayName("§e§lGestion du chat");
                            ArrayList<String> loreactiveChat = new ArrayList<String>();
                            loreactiveChat.add("");
                            loreactiveChat.add(" §aDescription:");
                            loreactiveChat.add(" §f▶ §7Envoyez de manière");
                            loreactiveChat.add(" §f  §7simple un message au");
                            loreactiveChat.add(" §f  §7joueurs de votre groupe.");
                            loreactiveChat.add("");
                            loreactiveChat.add(" §dInformations:");
                            loreactiveChat.add(" §f▶ §8[activé]");
                            loreactiveChat.add(" §f➟ §c[désactivé]");
                            loreactiveChat.add("");
                            activeChatM.setLore(loreactiveChat);
                            activeChat.setItemMeta(activeChatM);
                            inv.setItem(32, activeChat);
                        }

                        PartyData leaderData = new PartyData(pData.getGroupLeader(groupID).get(0));
                        if (leaderData.getGroupStatus() == 1)
                        {
                            ItemStack publicOrPrivate = new ItemStack(Material.POWERED_MINECART, 1);
                            ItemMeta publicOrPrivateM = publicOrPrivate.getItemMeta();
                            publicOrPrivateM.setDisplayName("§b§lStatut du groupe");
                            ArrayList<String> lorepublicOrPrivate = new ArrayList<String>();
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §aDescription:");
                            lorepublicOrPrivate.add(" §f▶ §7Votre groupe est joignable");
                            lorepublicOrPrivate.add(" §f  §7uniquement sur invitations.");
                            lorepublicOrPrivate.add(" §f▶ §d/§fp invite §a[joueur]§7.");
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §dInformations:");
                            lorepublicOrPrivate.add(" §f▶ §8[publique]");
                            lorepublicOrPrivate.add(" §f➟ §c[privé]");
                            lorepublicOrPrivate.add("");
                            publicOrPrivateM.setLore(lorepublicOrPrivate);
                            publicOrPrivate.setItemMeta(publicOrPrivateM);
                            inv.setItem(30, publicOrPrivate);
                        }
                        else if (leaderData.getGroupStatus() == 2)
                        {
                            ItemStack publicOrPrivate = new ItemStack(Material.STORAGE_MINECART, 1);
                            ItemMeta publicOrPrivateM = publicOrPrivate.getItemMeta();
                            publicOrPrivateM.setDisplayName("§b§lStatut du groupe");
                            ArrayList<String> lorepublicOrPrivate = new ArrayList<String>();
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §aDescription:");
                            lorepublicOrPrivate.add(" §f▶ §7Votre groupe est joignable");
                            lorepublicOrPrivate.add(" §f  §7par tous le mondes.");
                            lorepublicOrPrivate.add(" §f▶ §d/§fp join §a" + pData.getGroupLeader(groupID).get(0) + "§7.");
                            lorepublicOrPrivate.add("");
                            lorepublicOrPrivate.add(" §dInformations:");
                            lorepublicOrPrivate.add(" §f➟ §a[publique]");
                            lorepublicOrPrivate.add(" §f▶ §8[privé]");
                            lorepublicOrPrivate.add("");
                            publicOrPrivateM.setLore(lorepublicOrPrivate);
                            publicOrPrivate.setItemMeta(publicOrPrivateM);
                            inv.setItem(30, publicOrPrivate);
                        }
                    }
                }
                else
                {
                    ItemStack creategroup = getSkull("http://textures.minecraft.net/texture/eaea5d7127214ee2cca1b82ef82f8ae55929325766ea84dde18c11de0c7d591");
                    ItemMeta creategroupM = creategroup.getItemMeta();
                    creategroupM.setDisplayName("§fCréer un groupe");
                    ArrayList<String> lorecreategroup = new ArrayList<String>();
                    lorecreategroup.add("");
                    lorecreategroup.add(" §aDescription:");
                    lorecreategroup.add(" §f▶ §7Invitez un joueur");
                    lorecreategroup.add(" §f  §7de manière simple");
                    lorecreategroup.add(" §f  §7dans votre groupe.");
                    lorecreategroup.add("");
                    lorecreategroup.add("§8➡ §fCliquez pour y accéder.");
                    creategroupM.setLore(lorecreategroup);
                    creategroup.setItemMeta(creategroupM);
                    inv.setItem(22, creategroup);

                    ItemStack joingroup = getSkull("http://textures.minecraft.net/texture/66362ba4b2c7b05314bf25e95f760b99fe4b341dc841770a27c244bcebfd5e8");
                    ItemMeta joingroupM = joingroup.getItemMeta();
                    joingroupM.setDisplayName("§aRejoindre un groupe");
                    ArrayList<String> lorejoingroup = new ArrayList<String>();
                    lorejoingroup.add("");
                    lorejoingroup.add(" §aDescription:");
                    lorejoingroup.add(" §f▶ §7Rejoignez un groupe");
                    lorejoingroup.add(" §f  §7en choisissant celui");
                    lorejoingroup.add(" §f  §7qui vous correspond");
                    lorejoingroup.add(" §f  §7le mieux.");
                    lorejoingroup.add("");
                    lorejoingroup.add("§8➡ §fCliquez pour y accéder.");
                    joingroupM.setLore(lorejoingroup);
                    joingroup.setItemMeta(joingroupM);
                    inv.setItem(31, joingroup);
                }
                ++t;
                if (t == 10) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)api, 0L, 40L);

            /*int slot = 20;
        	int friendsCount = 0;
            for (String friends : friendInfo.getFriendForPage(Page)) {
            	++friendsCount;
            	AccountInfo friendsInfo = new AccountInfo(friends);
            	ItemStack friend = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
                SkullMeta friendM = (SkullMeta) friend.getItemMeta();
                friendM.setOwner(friends);
                friendM.setDisplayName("§a" + friends);
                ArrayList<String> lorefriend = new ArrayList<String>();
                lorefriend.add("");
                lorefriend.add(" §dInformation:");
                lorefriend.add(" §f▶ §7Statut: " + friendsInfo.isOnline());
                lorefriend.add("");
                lorefriend.add("§8➡ §fDROP: §8Supprimer");
                friendM.setLore(lorefriend);
                friend.setItemMeta(friendM);


                if (friendsCount == friendPerPage)
                {
                	inv.setItem(slot, friend);
                }
                else if (friendsCount != friendPerPage)
                {
                	inv.setItem(slot, friend);
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
            }*/

    }

    private void createGroupTag(Player p) {
        PlayerChatListener.getCreateGroupTag().add(p);
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage("");
        p.sendMessage(" §7● §fVeuillez préciser un tag de groupe.");
        p.sendMessage(" §7● §fEntrez §cCANCEL §fdans le chat pour annuler.");
        p.sendMessage("");
        new BukkitRunnable() {
            int t = 15;
            public void run() {
                if (!PlayerChatListener.getCreateGroupTag().contains(p))
                {
                    cancel();
                }
                --t;
                if (t == 0) {
                    if (PlayerChatListener.getCreateGroupTag().contains(p))
                    {
                        PlayerChatListener.getCreateGroupTag().remove(p);
                        p.sendMessage("§cAction annuler, vous avez mis trop de temps...");
                    }
                    cancel();
                }
            }
        }.runTaskTimer((Plugin)Core.getInstance(), 0L, 20L);
    }

    private void sendInvite(Player p) {
        PlayerChatListener.getChatBoxInvitePlayerInGroup().add(p);
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage("");
        p.sendMessage(" §7● §fVeuillez préciser un joueur pour l'inviter.");
        p.sendMessage(" §7● §fEntrez §cCANCEL §fdans le chat pour annuler.");
        p.sendMessage("");
        new BukkitRunnable() {
            int t = 15;
            public void run() {
                if (!PlayerChatListener.getChatBoxInvitePlayerInGroup().contains(p))
                {
                    cancel();
                }
                --t;
                if (t == 0) {
                    if (PlayerChatListener.getChatBoxInvitePlayerInGroup().contains(p))
                    {
                        PlayerChatListener.getChatBoxInvitePlayerInGroup().remove(p);
                        p.sendMessage("§cAction annuler, vous avez mis trop de temps...");
                    }
                    cancel();
                }
            }
        }.runTaskTimer((Plugin)Core.getInstance(), 0L, 20L);
    }

    private void sendChat(Player p) {
        PlayerChatListener.getSendChatGroup().add(p);
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage("");
        p.sendMessage(" §7● §fVeuillez écrire votre message.");
        p.sendMessage(" §7● §fEntrez §cCANCEL §fdans le chat pour annuler.");
        p.sendMessage(" §7● §fVous avez 30 secondes pour écrire votre message.");
        p.sendMessage("");
        new BukkitRunnable() {
            int t = 30;
            public void run() {
                if (!PlayerChatListener.getSendChatGroup().contains(p))
                {
                    cancel();
                }
                --t;
                if (t == 0) {
                    if (PlayerChatListener.getSendChatGroup().contains(p))
                    {
                        PlayerChatListener.getSendChatGroup().remove(p);
                        p.sendMessage("§cAction annuler, vous avez mis trop de temps...");
                    }
                    cancel();
                }
            }
        }.runTaskTimer((Plugin)Core.getInstance(), 0L, 20L);
    }

    public static void confirmSendInvite(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez inviter §b§l" + target + "§7?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour inviter " + target + ".").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p invite " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    private void joinGroup(Player p) {
        PlayerChatListener.getChatBoxJoinGroup().add(p);
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage("");
        p.sendMessage(" §7● §fVeuillez préciser un joueur pour le rejoindre.");
        p.sendMessage(" §7● §fEntrez §cCANCEL §fdans le chat pour annuler.");
        p.sendMessage("");
        new BukkitRunnable() {
            int t = 15;
            public void run() {
                if (!PlayerChatListener.getChatBoxJoinGroup().contains(p))
                {
                    cancel();
                }
                --t;
                if (t == 0) {
                    if (PlayerChatListener.getChatBoxJoinGroup().contains(p))
                    {
                        PlayerChatListener.getChatBoxJoinGroup().remove(p);
                        p.sendMessage("§cAction annulée, vous avez mis trop de temps...");
                    }
                    cancel();
                }
            }
        }.runTaskTimer((Plugin)Core.getInstance(), 0L, 20L);
    }

    public static void confirmJoinGroup(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez rejoindre §b§l" + target + "§7?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour rejoindre " + target + ".").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p join " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void confirmLeaveGroup(Player p) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez quitter le groupe?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour quitter le groupe.").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p leave"));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void confirmTeleportPlayers(Player p) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez téléporter le groupe?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour téléporter le groupe.").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p warp all"));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void confirmTeleportToLeader(Player p) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez téléporter vers votre Leader?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour vous téléporter.").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p tp"));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void confirmDisbandGroup(Player p) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez dissoudre le groupe?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour dissoudre le groupe.").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p disband"));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void sendGroupMessage(Player p, String message) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous votre message?");
        p.sendMessage(" §7● §b" + message);
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour envoyer ce message.").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p chat " + message));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void confirmChangeGroupTag(Player p, String tag) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §9§lInformations §9(groupe):");
        p.sendMessage(" §7● §fConfirmez-vous votre tag?");
        p.sendMessage(" §7● §b" + tag);
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez ici pour envoyer votre tag.").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/grouptag " + tag));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }
}
