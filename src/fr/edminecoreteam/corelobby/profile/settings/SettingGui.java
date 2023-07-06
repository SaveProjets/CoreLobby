package fr.edminecoreteam.corelobby.profile.settings;

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
import fr.edminecoreteam.corelobby.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.profile.friends.FriendInfo;
import fr.edminecoreteam.corelobby.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.utils.SkullNBT;

public class SettingGui implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        if (inv.getName().equalsIgnoreCase("§8Profil (Réglages)"))
        {
            e.setCancelled(true);
            SettingInfo settingInfo = new SettingInfo(p);
            if (it.getType() == Material.SKULL_ITEM
                    || it.getType() == Material.IRON_BARDING
                    || it.getType() == Material.ENDER_PEARL
                    || it.getType() == Material.BOOK_AND_QUILL
                    || it.getType() == Material.GLOWSTONE_DUST
                    || it.getType() == Material.PAPER
                    || it.getType() == Material.PAINTING
                    || it.getType() == Material.SIGN
                    || it.getType() == Material.SLIME_BALL
                    || it.getType() == Material.CARROT_ITEM
                    || it.getType() == Material.BED
                    || it.getType() == Material.DOUBLE_PLANT)
            {
                if(it.getItemMeta().getDisplayName() == "§d§lAmis §c❤")
                {
                    FriendInfo friendInfo = new FriendInfo(p.getName());
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FriendGui.gui(p, 1, friendInfo.getFriendPageNumber());
                }
                if(it.getItemMeta().getDisplayName() == "§9§lGroupes")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    GroupGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§c§lProfil")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    ProfileGUI.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§9§lRéglages sociaux")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§a§lRéglages lobby")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    guiLobby(p);
                }
                if(it.getItemMeta().getDisplayName() == "§b§lRéglages guild")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    guiGuild(p);
                }


                if(it.getItemMeta().getDisplayName() == "§aRequêtes d'amis")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateFriendRequest();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aRequêtes de groupes")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateGroupRequest();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aRequêtes de guilds")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateGuildRequest();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aMessages privés")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updatePrivateMessage();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aVisualisation du profil")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateProfilView();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aAffichages des joueurs")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updatePlayersDisplay();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aStatut du compte")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateAccountState();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aMentions du chat")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateChatMention();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aSuivre votre chef de groupe")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateGroupFollow();
                    guiSocial(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aNotifications de live")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateLiveNotification();
                    guiSocial(p);
                }


                if(it.getItemMeta().getDisplayName() == "§aMessage de connexion")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateMessageConnection();
                    guiLobby(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aMode sombre")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateNightOrDay();
                    guiLobby(p);
                    if (settingInfo.getNightOrDay().equalsIgnoreCase("activer"))
                    {
                        p.setPlayerTime(14000, true);
                    }
                    else
                    {
                        p.setPlayerTime(6000, true);
                    }
                }
                if(it.getItemMeta().getDisplayName() == "§aAuto-tip")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateAutoTip();
                    guiLobby(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aLobby protection")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateLobbyProtection();
                    guiLobby(p);
                }


                if(it.getItemMeta().getDisplayName() == "§aChat de guild")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateGuildChat();
                    guiGuild(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aNotifications de guild")
                {
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    settingInfo.updateGuildNotification();
                    guiGuild(p);
                }
            }
        }
    }

    public static void guiSocial(Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil (Réglages)");
        p.openInventory(inv);
        FriendInfo friendInfo = new FriendInfo(p.getName());
        SettingInfo settingInfo = new SettingInfo(p);

        /*
         * Type de pages;
         *  - Liste d'amis: 0
         *  - Liste de demandes: 1
         */


        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Profil (Réglages)")) { cancel(); }

                ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)11);
                ItemMeta decoM = deco.getItemMeta();
                decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                decoM.setDisplayName("§r");
                deco.setItemMeta(decoM);
                inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);


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

                ItemStack socialSettings = getSkull("http://textures.minecraft.net/texture/5729d03f7b643ec6fb25e392a88f059e6639b6dc593e0ba845879fcecf339be");
                ItemMeta socialSettingsM = socialSettings.getItemMeta();
                socialSettingsM.setDisplayName("§9§lRéglages sociaux");
                ArrayList<String> loresocialSettings = new ArrayList<String>();
                loresocialSettings.add("");
                loresocialSettings.add(" §aDescription:");
                loresocialSettings.add(" §f▶ §7Modifiez vos réglages sociaux.");
                loresocialSettings.add("");
                loresocialSettings.add("§8➡ §fCliquez pour y accéder.");
                socialSettingsM.setLore(loresocialSettings);
                socialSettings.setItemMeta(socialSettingsM);
                inv.setItem(3, socialSettings);

                ItemStack lobbySettings = getSkull("http://textures.minecraft.net/texture/d41d13a1855a75d6ae1ae2b2837d76d920c5f7739e64469841658f917fc0");
                ItemMeta lobbySettingsM = lobbySettings.getItemMeta();
                lobbySettingsM.setDisplayName("§a§lRéglages lobby");
                ArrayList<String> lorelobbySettings = new ArrayList<String>();
                lorelobbySettings.add("");
                lorelobbySettings.add(" §aDescription:");
                lorelobbySettings.add(" §f▶ §7Modifiez vos réglages lobby.");
                lorelobbySettings.add("");
                lorelobbySettings.add("§8➡ §fCliquez pour y accéder.");
                lobbySettingsM.setLore(lorelobbySettings);
                lobbySettings.setItemMeta(lobbySettingsM);
                inv.setItem(4, lobbySettings);

                ItemStack guildSettings = getSkull("http://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077");
                ItemMeta guildSettingsM = guildSettings.getItemMeta();
                guildSettingsM.setDisplayName("§b§lRéglages guild");
                ArrayList<String> loreguildSettings = new ArrayList<String>();
                loreguildSettings.add("");
                loreguildSettings.add(" §aDescription:");
                loreguildSettings.add(" §f▶ §7Modifiez vos réglages guild.");
                loreguildSettings.add("");
                loreguildSettings.add("§8➡ §fCliquez pour y accéder.");
                guildSettingsM.setLore(loreguildSettings);
                guildSettings.setItemMeta(guildSettingsM);
                inv.setItem(5, guildSettings);

                ItemStack friendDemand = getSkull("http://textures.minecraft.net/texture/76cbae7246cc2c6e888587198c7959979666b4f5a4088f24e26e075f140ae6c3");
                ItemMeta friendDemandM = friendDemand.getItemMeta();
                friendDemandM.setDisplayName("§aRequêtes d'amis");
                ArrayList<String> lorefriendDemand = new ArrayList<String>();
                lorefriendDemand.add("");
                lorefriendDemand.add(" §aDescription:");
                if (settingInfo.getFriendRequest().contains("bas"))
                {
                    lorefriendDemand.add(" §f▶ §7Tout le monde peut vous");
                    lorefriendDemand.add("   §7envoyer une demande d'ami.");
                    lorefriendDemand.add("");
                    lorefriendDemand.add(" §dInformations:");
                    lorefriendDemand.add(" §f▶ §8[Max]");
                    lorefriendDemand.add(" §f▶ §8[Haut]");
                    lorefriendDemand.add(" §f➟ §a[Bas]");
                }
                else if (settingInfo.getFriendRequest().contains("haut"))
                {
                    lorefriendDemand.add(" §f▶ §7Les staffs, votre");
                    lorefriendDemand.add("   §7groupe et votre guild peuvent");
                    lorefriendDemand.add("   §7vous envoyer une demande d'ami.");
                    lorefriendDemand.add("");
                    lorefriendDemand.add(" §dInformations:");
                    lorefriendDemand.add(" §f▶ §8[Max]");
                    lorefriendDemand.add(" §f➟ §6[Haut]");
                    lorefriendDemand.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getFriendRequest().contains("max"))
                {
                    lorefriendDemand.add(" §f▶ §7Uniquement les membres");
                    lorefriendDemand.add("   §7du staff peuvent vous");
                    lorefriendDemand.add("   §7envoyer une demande d'ami.");
                    lorefriendDemand.add("");
                    lorefriendDemand.add(" §dInformations:");
                    lorefriendDemand.add(" §f➟ §c[Max]");
                    lorefriendDemand.add(" §f▶ §8[Haut]");
                    lorefriendDemand.add(" §f▶ §8[Bas]");
                }
                lorefriendDemand.add("");
                lorefriendDemand.add("§8➡ §fCliquez pour changer.");
                friendDemandM.setLore(lorefriendDemand);
                friendDemand.setItemMeta(friendDemandM);
                inv.setItem(20, friendDemand);

                ItemStack groupDemand = new ItemStack(Material.ENDER_PEARL, 1);
                ItemMeta groupDemandM = groupDemand.getItemMeta();
                groupDemandM.setDisplayName("§aRequêtes de groupes");
                ArrayList<String> loregroupDemand = new ArrayList<String>();
                loregroupDemand.add("");
                loregroupDemand.add(" §aDescription:");
                if (settingInfo.getGroupRequest().contains("bas"))
                {
                    loregroupDemand.add(" §f▶ §7Tout le monde peut vous");
                    loregroupDemand.add("   §7envoyer une demande de groupe.");
                    loregroupDemand.add("");
                    loregroupDemand.add(" §dInformations:");
                    loregroupDemand.add(" §f▶ §8[Max]");
                    loregroupDemand.add(" §f▶ §8[Haut]");
                    loregroupDemand.add(" §f▶ §8[Moyen]");
                    loregroupDemand.add(" §f➟ §a[Bas]");
                }
                else if (settingInfo.getGroupRequest().contains("moyen"))
                {
                    loregroupDemand.add(" §f▶ §7Les staffs, vos amis");
                    loregroupDemand.add("   §7et votre guild peuvent");
                    loregroupDemand.add("   §7vous envoyer une demande de groupe.");
                    loregroupDemand.add("");
                    loregroupDemand.add(" §dInformations:");
                    loregroupDemand.add(" §f▶ §8[Max]");
                    loregroupDemand.add(" §f▶ §8[Haut]");
                    loregroupDemand.add(" §f➟ §e[Moyen]");
                    loregroupDemand.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getGroupRequest().contains("haut"))
                {
                    loregroupDemand.add(" §f▶ §7Les staffs et");
                    loregroupDemand.add("   §7vos amis peuvent vous");
                    loregroupDemand.add("   §7envoyer une demande de groupe.");
                    loregroupDemand.add("");
                    loregroupDemand.add(" §dInformations:");
                    loregroupDemand.add(" §f▶ §8[Max]");
                    loregroupDemand.add(" §f➟ §6[Haut]");
                    loregroupDemand.add(" §f▶ §8[Moyen]");
                    loregroupDemand.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getGroupRequest().contains("max"))
                {
                    loregroupDemand.add(" §f▶ §7Uniquement les membres");
                    loregroupDemand.add("   §7du staff peuvent vous");
                    loregroupDemand.add("   §7envoyer une demande de groupe.");
                    loregroupDemand.add("");
                    loregroupDemand.add(" §dInformations:");
                    loregroupDemand.add(" §f➟ §c[Max]");
                    loregroupDemand.add(" §f▶ §8[Haut]");
                    loregroupDemand.add(" §f▶ §8[Moyen]");
                    loregroupDemand.add(" §f▶ §8[Bas]");
                }
                loregroupDemand.add("");
                loregroupDemand.add("§8➡ §fCliquez pour changer.");
                groupDemandM.setLore(loregroupDemand);
                groupDemand.setItemMeta(groupDemandM);
                inv.setItem(21, groupDemand);

                ItemStack guildDemand = new ItemStack(Material.IRON_BARDING, 1);
                ItemMeta guildDemandM = guildDemand.getItemMeta();
                guildDemandM.setDisplayName("§aRequêtes de guilds");
                ArrayList<String> loreguildDemand = new ArrayList<String>();
                loreguildDemand.add("");
                loreguildDemand.add(" §aDescription:");
                if (settingInfo.getGuildRequest().contains("bas"))
                {
                    loreguildDemand.add(" §f▶ §7Tout le monde peut vous");
                    loreguildDemand.add("   §7envoyer une demande de guild.");
                    loreguildDemand.add("");
                    loreguildDemand.add(" §dInformations:");
                    loreguildDemand.add(" §f▶ §8[Haut]");
                    loreguildDemand.add(" §f▶ §8[Moyen]");
                    loreguildDemand.add(" §f➟ §a[Bas]");
                }
                else if (settingInfo.getGuildRequest().contains("moyen"))
                {
                    loreguildDemand.add(" §f▶ §7Les staffs, vos amis");
                    loreguildDemand.add("   §7et votre groupe peuvent");
                    loreguildDemand.add("   §7vous envoyer une demande de groupe.");
                    loreguildDemand.add("");
                    loreguildDemand.add(" §dInformations:");
                    loreguildDemand.add(" §f▶ §8[Haut]");
                    loreguildDemand.add(" §f➟ §e[Moyen]");
                    loreguildDemand.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getGuildRequest().contains("haut"))
                {
                    loreguildDemand.add(" §f▶ §7Personne peut vous");
                    loreguildDemand.add("   §7envoyer une demande de guild.");
                    loreguildDemand.add("");
                    loreguildDemand.add(" §dInformations:");
                    loreguildDemand.add(" §f➟ §6[Haut]");
                    loreguildDemand.add(" §f▶ §8[Moyen]");
                    loreguildDemand.add(" §f▶ §8[Bas]");
                }
                loreguildDemand.add("");
                loreguildDemand.add("§8➡ §fCliquez pour changer.");
                guildDemandM.setLore(loreguildDemand);
                guildDemand.setItemMeta(guildDemandM);
                inv.setItem(22, guildDemand);

                ItemStack privateMessage = new ItemStack(Material.BOOK_AND_QUILL, 1);
                ItemMeta privateMessageM = privateMessage.getItemMeta();
                privateMessageM.setDisplayName("§aMessages privés");
                ArrayList<String> loreprivateMessage = new ArrayList<String>();
                loreprivateMessage.add("");
                loreprivateMessage.add(" §aDescription:");
                if (settingInfo.getPrivateMessage().contains("bas"))
                {
                    loreprivateMessage.add(" §f▶ §7Tout le monde peut vous");
                    loreprivateMessage.add("   §7envoyer un message privé.");
                    loreprivateMessage.add("");
                    loreprivateMessage.add(" §dInformations:");
                    loreprivateMessage.add(" §f▶ §8[Max]");
                    loreprivateMessage.add(" §f▶ §8[Haut]");
                    loreprivateMessage.add(" §f▶ §8[Moyen]");
                    loreprivateMessage.add(" §f➟ §a[Bas]");
                }
                else if (settingInfo.getPrivateMessage().contains("moyen"))
                {
                    loreprivateMessage.add(" §f▶ §7Les staffs, vos amis,");
                    loreprivateMessage.add("   §7votre guild et votre");
                    loreprivateMessage.add("   §7groupe peuvent vous");
                    loreprivateMessage.add("   §7envoyer un message privé.");
                    loreprivateMessage.add("");
                    loreprivateMessage.add(" §dInformations:");
                    loreprivateMessage.add(" §f▶ §8[Max]");
                    loreprivateMessage.add(" §f▶ §8[Haut]");
                    loreprivateMessage.add(" §f➟ §e[Moyen]");
                    loreprivateMessage.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getPrivateMessage().contains("haut"))
                {
                    loreprivateMessage.add(" §f▶ §7Les staffs, vos amis");
                    loreprivateMessage.add("   §7et votre groupe");
                    loreprivateMessage.add("   §7peuvent vous");
                    loreprivateMessage.add("   §7envoyer un message privé.");
                    loreprivateMessage.add("");
                    loreprivateMessage.add(" §dInformations:");
                    loreprivateMessage.add(" §f▶ §8[Max]");
                    loreprivateMessage.add(" §f➟ §6[Haut]");
                    loreprivateMessage.add(" §f▶ §8[Moyen]");
                    loreprivateMessage.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getPrivateMessage().contains("max"))
                {
                    loreprivateMessage.add(" §f▶ §7Uniquement les membres");
                    loreprivateMessage.add("   §7du staff peuvent vous");
                    loreprivateMessage.add("   §7envoyer un message privé.");
                    loreprivateMessage.add("");
                    loreprivateMessage.add(" §dInformations:");
                    loreprivateMessage.add(" §f➟ §c[Max]");
                    loreprivateMessage.add(" §f▶ §8[Haut]");
                    loreprivateMessage.add(" §f▶ §8[Moyen]");
                    loreprivateMessage.add(" §f▶ §8[Bas]");
                }
                loreprivateMessage.add("");
                loreprivateMessage.add("§8➡ §fCliquez pour changer.");
                privateMessageM.setLore(loreprivateMessage);
                privateMessage.setItemMeta(privateMessageM);
                inv.setItem(23, privateMessage);

                ItemStack profilView = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
                SkullMeta profilViewM = (SkullMeta) profilView.getItemMeta();
                profilViewM.setDisplayName("§aVisualisation du profil");
                profilViewM.setOwner(p.getName());
                ArrayList<String> loreprofilView = new ArrayList<String>();
                loreprofilView.add("");
                loreprofilView.add(" §aDescription:");
                if (settingInfo.getProfilView().contains("bas"))
                {
                    loreprofilView.add(" §f▶ §7Tout le monde peut");
                    loreprofilView.add("   §7voir votre profil.");
                    loreprofilView.add("");
                    loreprofilView.add(" §dInformations:");
                    loreprofilView.add(" §f▶ §8[Max]");
                    loreprofilView.add(" §f▶ §8[Haut]");
                    loreprofilView.add(" §f▶ §8[Moyen]");
                    loreprofilView.add(" §f➟ §a[Bas]");
                }
                else if (settingInfo.getProfilView().contains("moyen"))
                {
                    loreprofilView.add(" §f▶ §7Les staffs, vos amis,");
                    loreprofilView.add("   §7votre guild et votre groupe");
                    loreprofilView.add("   §7peuvent voir votre profil.");
                    loreprofilView.add("");
                    loreprofilView.add(" §dInformations:");
                    loreprofilView.add(" §f▶ §8[Max]");
                    loreprofilView.add(" §f▶ §8[Haut]");
                    loreprofilView.add(" §f➟ §e[Moyen]");
                    loreprofilView.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getProfilView().contains("haut"))
                {
                    loreprofilView.add(" §f▶ §7Les staffs, vos amis");
                    loreprofilView.add("   §7et votre groupe");
                    loreprofilView.add("   §7peuvent voir votre profil.");
                    loreprofilView.add("");
                    loreprofilView.add(" §dInformations:");
                    loreprofilView.add(" §f▶ §8[Max]");
                    loreprofilView.add(" §f➟ §6[Haut]");
                    loreprofilView.add(" §f▶ §8[Moyen]");
                    loreprofilView.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getProfilView().contains("max"))
                {
                    loreprofilView.add(" §f▶ §7Uniquement les membres du");
                    loreprofilView.add("   §7staff peuvent voir votre profil.");
                    loreprofilView.add("");
                    loreprofilView.add(" §dInformations:");
                    loreprofilView.add(" §f➟ §c[Max]");
                    loreprofilView.add(" §f▶ §8[Haut]");
                    loreprofilView.add(" §f▶ §8[Moyen]");
                    loreprofilView.add(" §f▶ §8[Bas]");
                }
                loreprofilView.add("");
                loreprofilView.add("§8➡ §fCliquez pour changer.");
                profilViewM.setLore(loreprofilView);
                profilView.setItemMeta(profilViewM);
                inv.setItem(24, profilView);

                ItemStack playersDisplay = new ItemStack(Material.GLOWSTONE_DUST, 1);
                ItemMeta playersDisplayM = playersDisplay.getItemMeta();
                playersDisplayM.setDisplayName("§aAffichages des joueurs");
                ArrayList<String> loreplayersDisplay = new ArrayList<String>();
                loreplayersDisplay.add("");
                loreplayersDisplay.add(" §aDescription:");
                if (settingInfo.getPlayersDisplay().contains("bas"))
                {
                    loreplayersDisplay.add(" §f▶ §7Tout le monde peut");
                    loreplayersDisplay.add("   §7vous voir.");
                    loreplayersDisplay.add("");
                    loreplayersDisplay.add(" §dInformations:");
                    loreplayersDisplay.add(" §f▶ §8[Max]");
                    loreplayersDisplay.add(" §f▶ §8[Haut]");
                    loreplayersDisplay.add(" §f▶ §8[Moyen]");
                    loreplayersDisplay.add(" §f➟ §a[Bas]");
                }
                else if (settingInfo.getPlayersDisplay().contains("moyen"))
                {
                    loreplayersDisplay.add(" §f▶ §7Les staffs, vos amis,");
                    loreplayersDisplay.add("   §7votre guild et votre groupe");
                    loreplayersDisplay.add("   §7peuvent vous voir.");
                    loreplayersDisplay.add("");
                    loreplayersDisplay.add(" §dInformations:");
                    loreplayersDisplay.add(" §f▶ §8[Max]");
                    loreplayersDisplay.add(" §f▶ §8[Haut]");
                    loreplayersDisplay.add(" §f➟ §e[Moyen]");
                    loreplayersDisplay.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getPlayersDisplay().contains("haut"))
                {
                    loreplayersDisplay.add(" §f▶ §7Les staffs, vos");
                    loreplayersDisplay.add("   §7amis et votre groupe");
                    loreplayersDisplay.add("   §7peuvent vous voir.");
                    loreplayersDisplay.add("");
                    loreplayersDisplay.add(" §dInformations:");
                    loreplayersDisplay.add(" §f▶ §8[Max]");
                    loreplayersDisplay.add(" §f➟ §6[Haut]");
                    loreplayersDisplay.add(" §f▶ §8[Moyen]");
                    loreplayersDisplay.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getPlayersDisplay().contains("max"))
                {
                    loreplayersDisplay.add(" §f▶ §7Uniquement les membres du");
                    loreplayersDisplay.add("   §7staff peuvent vous voir.");
                    loreplayersDisplay.add("");
                    loreplayersDisplay.add(" §dInformations:");
                    loreplayersDisplay.add(" §f➟ §c[Max]");
                    loreplayersDisplay.add(" §f▶ §8[Haut]");
                    loreplayersDisplay.add(" §f▶ §8[Moyen]");
                    loreplayersDisplay.add(" §f▶ §8[Bas]");
                }
                loreplayersDisplay.add("");
                loreplayersDisplay.add("§8➡ §fCliquez pour changer.");
                playersDisplayM.setLore(loreplayersDisplay);
                playersDisplay.setItemMeta(playersDisplayM);
                inv.setItem(29, playersDisplay);

                ItemStack accountState = new ItemStack(Material.SIGN, 1);
                ItemMeta accountStateM = accountState.getItemMeta();
                accountStateM.setDisplayName("§aStatut du compte");
                ArrayList<String> loreaccountState = new ArrayList<String>();
                loreaccountState.add("");
                loreaccountState.add(" §aDescription:");
                if (settingInfo.getAccountState().contains("En-Ligne"))
                {
                    loreaccountState.add(" §f▶ §7Toutes les personnes ayant accès");
                    loreaccountState.add("   §7à votre profil vous vois connecté.");
                    loreaccountState.add("");
                    loreaccountState.add(" §dInformations:");
                    loreaccountState.add(" §f▶ §8[Hors-Ligne]");
                    loreaccountState.add(" §f▶ §8[Occupé]");
                    loreaccountState.add(" §f▶ §8[Inactif]");
                    loreaccountState.add(" §f➟ §a[En-Ligne]");
                }
                else if (settingInfo.getAccountState().contains("Inactif"))
                {
                    loreaccountState.add(" §f▶ §7Toutes les personnes ayant accès");
                    loreaccountState.add("   §7à votre profil vous vois inactif.");
                    loreaccountState.add("");
                    loreaccountState.add(" §dInformations:");
                    loreaccountState.add(" §f▶ §8[Hors-Ligne]");
                    loreaccountState.add(" §f▶ §8[Occupé]");
                    loreaccountState.add(" §f➟ §e[Inactif]");
                    loreaccountState.add(" §f▶ §8[En-Ligne]");
                }
                else if (settingInfo.getAccountState().contains("Occupé"))
                {
                    loreaccountState.add(" §f▶ §7Toutes les personnes ayant accès");
                    loreaccountState.add("   §7à votre profil vous vois occupée.");
                    loreaccountState.add("");
                    loreaccountState.add(" §dInformations:");
                    loreaccountState.add(" §f▶ §8[Hors-Ligne]");
                    loreaccountState.add(" §f➟ §5[Occupé]");
                    loreaccountState.add(" §f▶ §8[Inactif]");
                    loreaccountState.add(" §f▶ §8[En-Ligne]");
                }
                else if (settingInfo.getAccountState().contains("Hors-Ligne"))
                {
                    loreaccountState.add(" §f▶ §7Toutes les personnes ayant accès");
                    loreaccountState.add("   §7à votre profil vous vois déconnecté.");
                    loreaccountState.add("");
                    loreaccountState.add(" §dInformations:");
                    loreaccountState.add(" §f➟ §c[Hors-Ligne]");
                    loreaccountState.add(" §f▶ §8[Occupé]");
                    loreaccountState.add(" §f▶ §8[Inactif]");
                    loreaccountState.add(" §f▶ §8[En-Ligne]");
                }
                loreaccountState.add("");
                loreaccountState.add("§8➡ §fCliquez pour changer.");
                accountStateM.setLore(loreaccountState);
                accountState.setItemMeta(accountStateM);
                inv.setItem(30, accountState);

                ItemStack chatMention = new ItemStack(Material.PAPER, 1);
                ItemMeta chatMentionM = chatMention.getItemMeta();
                chatMentionM.setDisplayName("§aMentions du chat");
                ArrayList<String> lorechatMention = new ArrayList<String>();
                lorechatMention.add("");
                lorechatMention.add(" §aDescription:");
                if (settingInfo.getChatMention().contains("bas"))
                {
                    lorechatMention.add(" §f▶ §7Tout le monde peut");
                    lorechatMention.add("   §7vous mentionner.");
                    lorechatMention.add("");
                    lorechatMention.add(" §dInformations:");
                    lorechatMention.add(" §f▶ §8[Max]");
                    lorechatMention.add(" §f▶ §8[Haut]");
                    lorechatMention.add(" §f▶ §8[Moyen]");
                    lorechatMention.add(" §f➟ §a[Bas]");
                }
                else if (settingInfo.getChatMention().contains("moyen"))
                {
                    lorechatMention.add(" §f▶ §7Les staffs, vos amis");
                    lorechatMention.add("   §7et votre guild");
                    lorechatMention.add("   §7peuvent vous mentionner.");
                    lorechatMention.add("");
                    lorechatMention.add(" §dInformations:");
                    lorechatMention.add(" §f▶ §8[Max]");
                    lorechatMention.add(" §f▶ §8[Haut]");
                    lorechatMention.add(" §f➟ §e[Moyen]");
                    lorechatMention.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getChatMention().contains("haut"))
                {
                    lorechatMention.add(" §f▶ §7Les staffs et vos");
                    lorechatMention.add("   §7amis peuvent vous mentionner.");
                    lorechatMention.add("");
                    lorechatMention.add(" §dInformations:");
                    lorechatMention.add(" §f▶ §8[Max]");
                    lorechatMention.add(" §f➟ §6[Haut]");
                    lorechatMention.add(" §f▶ §8[Moyen]");
                    lorechatMention.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getChatMention().contains("max"))
                {
                    lorechatMention.add(" §f▶ §7Uniquement les membres du");
                    lorechatMention.add("   §7staff peuvent vous mentionner.");
                    lorechatMention.add("");
                    lorechatMention.add(" §dInformations:");
                    lorechatMention.add(" §f➟ §c[Max]");
                    lorechatMention.add(" §f▶ §8[Haut]");
                    lorechatMention.add(" §f▶ §8[Moyen]");
                    lorechatMention.add(" §f▶ §8[Bas]");
                }
                lorechatMention.add("");
                lorechatMention.add("§8➡ §fCliquez pour changer.");
                chatMentionM.setLore(lorechatMention);
                chatMention.setItemMeta(chatMentionM);
                inv.setItem(31, chatMention);

                ItemStack groupFollow = new ItemStack(Material.PAINTING, 1);
                ItemMeta groupFollowM = groupFollow.getItemMeta();
                groupFollowM.setDisplayName("§aSuivre votre chef de groupe");
                ArrayList<String> loregroupFollow = new ArrayList<String>();
                loregroupFollow.add("");
                loregroupFollow.add(" §aDescription:");
                if (settingInfo.getGroupFollow().equalsIgnoreCase("activer"))
                {
                    loregroupFollow.add(" §f▶ §7Vous suivez le chef");
                    loregroupFollow.add("   §7de groupe quand il");
                    loregroupFollow.add("   §7change de serveur.");
                    loregroupFollow.add("");
                    loregroupFollow.add(" §dInformations:");
                    loregroupFollow.add(" §f➟ §a[Activé]");
                    loregroupFollow.add(" §f▶ §8[Désactivé]");
                }
                else if (settingInfo.getGroupFollow().equalsIgnoreCase("desactiver"))
                {
                    loregroupFollow.add(" §f▶ §7Vous recevez une invitation");
                    loregroupFollow.add("   §7quand le chef de groupe");
                    loregroupFollow.add("   §7change de serveur.");
                    loregroupFollow.add("");
                    loregroupFollow.add(" §dInformations:");
                    loregroupFollow.add(" §f▶ §8[Activé]");
                    loregroupFollow.add(" §f➟ §c[Désactivé]");
                }
                loregroupFollow.add("");
                loregroupFollow.add("§8➡ §fCliquez pour changer.");
                groupFollowM.setLore(loregroupFollow);
                groupFollow.setItemMeta(groupFollowM);
                inv.setItem(32, groupFollow);

                ItemStack liveNotification = new ItemStack(Material.SLIME_BALL, 1);
                ItemMeta liveNotificationM = liveNotification.getItemMeta();
                liveNotificationM.setDisplayName("§aNotifications de live");
                ArrayList<String> loreliveNotification = new ArrayList<String>();
                loreliveNotification.add("");
                loreliveNotification.add(" §aDescription:");
                if (settingInfo.getLiveNotification().equalsIgnoreCase("activer"))
                {
                    loreliveNotification.add(" §f▶ §7Vous recevez toutes");
                    loreliveNotification.add("   §7les notifications de live.");
                    loreliveNotification.add("");
                    loreliveNotification.add(" §dInformations:");
                    loreliveNotification.add(" §f➟ §a[Activé]");
                    loreliveNotification.add(" §f▶ §8[Désactivé]");
                }
                else if (settingInfo.getLiveNotification().equalsIgnoreCase("desactiver"))
                {
                    loreliveNotification.add(" §f▶ §7Vous ne recevez pas");
                    loreliveNotification.add("   §7les notifications de live.");
                    loreliveNotification.add("");
                    loreliveNotification.add(" §dInformations:");
                    loreliveNotification.add(" §f▶ §8[Activé]");
                    loreliveNotification.add(" §f➟ §c[Désactivé]");
                }
                loreliveNotification.add("");
                loreliveNotification.add("§8➡ §fCliquez pour changer.");
                liveNotificationM.setLore(loreliveNotification);
                liveNotification.setItemMeta(liveNotificationM);
                inv.setItem(33, liveNotification);

                ++t;
                if (t == 15) {
                    t = 0;
                    run();
                }
            }
        }.runTaskTimer((Plugin)api, 0L, 15L);
    }

    public static void guiLobby(Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil (Réglages)");
        p.openInventory(inv);
        FriendInfo friendInfo = new FriendInfo(p.getName());
        SettingInfo settingInfo = new SettingInfo(p);

        /*
         * Type de pages;
         *  - Liste d'amis: 0
         *  - Liste de demandes: 1
         */


        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Profil (Réglages)")) { cancel(); }

                ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)11);
                ItemMeta decoM = deco.getItemMeta();
                decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                decoM.setDisplayName("§r");
                deco.setItemMeta(decoM);
                inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);


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

                ItemStack socialSettings = getSkull("http://textures.minecraft.net/texture/c2eb28cd6c7524b14d59f3b8c465dfc78be8e0dac843e682c4252ae9459c78e4");
                ItemMeta socialSettingsM = socialSettings.getItemMeta();
                socialSettingsM.setDisplayName("§9§lRéglages sociaux");
                ArrayList<String> loresocialSettings = new ArrayList<String>();
                loresocialSettings.add("");
                loresocialSettings.add(" §aDescription:");
                loresocialSettings.add(" §f▶ §7Modifiez vos réglages sociaux.");
                loresocialSettings.add("");
                loresocialSettings.add("§8➡ §fCliquez pour y accéder.");
                socialSettingsM.setLore(loresocialSettings);
                socialSettings.setItemMeta(socialSettingsM);
                inv.setItem(3, socialSettings);

                ItemStack lobbySettings = getSkull("http://textures.minecraft.net/texture/8831ca2dc68a161e75c3edda1c6349d41f55e57c71e967d451f4f881d7876a5");
                ItemMeta lobbySettingsM = lobbySettings.getItemMeta();
                lobbySettingsM.setDisplayName("§a§lRéglages lobby");
                ArrayList<String> lorelobbySettings = new ArrayList<String>();
                lorelobbySettings.add("");
                lorelobbySettings.add(" §aDescription:");
                lorelobbySettings.add(" §f▶ §7Modifiez vos réglages lobby.");
                lorelobbySettings.add("");
                lorelobbySettings.add("§8➡ §fCliquez pour y accéder.");
                lobbySettingsM.setLore(lorelobbySettings);
                lobbySettings.setItemMeta(lobbySettingsM);
                inv.setItem(4, lobbySettings);

                ItemStack guildSettings = getSkull("http://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077");
                ItemMeta guildSettingsM = guildSettings.getItemMeta();
                guildSettingsM.setDisplayName("§b§lRéglages guild");
                ArrayList<String> loreguildSettings = new ArrayList<String>();
                loreguildSettings.add("");
                loreguildSettings.add(" §aDescription:");
                loreguildSettings.add(" §f▶ §7Modifiez vos réglages guild.");
                loreguildSettings.add("");
                loreguildSettings.add("§8➡ §fCliquez pour y accéder.");
                guildSettingsM.setLore(loreguildSettings);
                guildSettings.setItemMeta(guildSettingsM);
                inv.setItem(5, guildSettings);


                ItemStack messageConnection = new ItemStack(Material.PAPER, 1);
                ItemMeta messageConnectionM = messageConnection.getItemMeta();
                messageConnectionM.setDisplayName("§aMessage de connexion");
                ArrayList<String> loremessageConnection = new ArrayList<String>();
                loremessageConnection.add("");
                loremessageConnection.add(" §aDescription:");
                if (settingInfo.getMessageConnection().equalsIgnoreCase("activer"))
                {
                    loremessageConnection.add(" §f▶ §7Votre message de");
                    loremessageConnection.add("   §7connection est actif.");
                    loremessageConnection.add("");
                    loremessageConnection.add(" §dNiveau:");
                    loremessageConnection.add(" §f➟ §a[Activé]");
                    loremessageConnection.add(" §f▶ §8[Désactivé]");
                }
                else if (settingInfo.getMessageConnection().equalsIgnoreCase("desactiver"))
                {
                    loremessageConnection.add(" §f▶ §7Votre message de");
                    loremessageConnection.add("   §7connection est inactif.");
                    loremessageConnection.add("");
                    loremessageConnection.add(" §dNiveau:");
                    loremessageConnection.add(" §f▶ §8[Activé]");
                    loremessageConnection.add(" §f➟ §c[Désactivé]");
                }
                loremessageConnection.add("");
                loremessageConnection.add("§8➡ §fCliquez pour changer.");
                messageConnectionM.setLore(loremessageConnection);
                messageConnection.setItemMeta(messageConnectionM);
                inv.setItem(20, messageConnection);

                ItemStack nightOrDay = new ItemStack(Material.CARROT_ITEM, 1);
                ItemMeta nightOrDayM = nightOrDay.getItemMeta();
                nightOrDayM.setDisplayName("§aMode sombre");
                ArrayList<String> lorenightOrDay = new ArrayList<String>();
                lorenightOrDay.add("");
                lorenightOrDay.add(" §aDescription:");
                if (settingInfo.getNightOrDay().equalsIgnoreCase("activer"))
                {
                    lorenightOrDay.add(" §f▶ §7Votre vision du");
                    lorenightOrDay.add("   §7hub affiche la nuit.");
                    lorenightOrDay.add("");
                    lorenightOrDay.add(" §dNiveau:");
                    lorenightOrDay.add(" §f➟ §a[Activé]");
                    lorenightOrDay.add(" §f▶ §8[Désactivé]");
                }
                else if (settingInfo.getNightOrDay().equalsIgnoreCase("desactiver"))
                {
                    lorenightOrDay.add(" §f▶ §7Votre vision du hub");
                    lorenightOrDay.add("   §7affiche le temps du jeu.");
                    lorenightOrDay.add("");
                    lorenightOrDay.add(" §dNiveau:");
                    lorenightOrDay.add(" §f▶ §8[Activé]");
                    lorenightOrDay.add(" §f➟ §c[Désactivé]");
                }
                lorenightOrDay.add("");
                lorenightOrDay.add("§8➡ §fCliquez pour changer.");
                nightOrDayM.setLore(lorenightOrDay);
                nightOrDay.setItemMeta(nightOrDayM);
                inv.setItem(21, nightOrDay);

                ItemStack autoTip = new ItemStack(Material.DOUBLE_PLANT, 1);
                ItemMeta autoTipM = autoTip.getItemMeta();
                autoTipM.setDisplayName("§aAuto-tip");
                ArrayList<String> loreautoTip = new ArrayList<String>();
                loreautoTip.add("");
                loreautoTip.add(" §aDescription:");
                if (settingInfo.getAutoTip().equalsIgnoreCase("activer"))
                {
                    loreautoTip.add(" §f▶ §7Vous utilisez l'auto-tip.");
                    loreautoTip.add("");
                    loreautoTip.add(" §dNiveau:");
                    loreautoTip.add(" §f➟ §a[Activé]");
                    loreautoTip.add(" §f▶ §8[Désactivé]");
                }
                else if (settingInfo.getAutoTip().equalsIgnoreCase("desactiver"))
                {
                    loreautoTip.add(" §f▶ §7Vous n'utilisez pas");
                    loreautoTip.add("   §7l'auto-tip.");
                    loreautoTip.add("");
                    loreautoTip.add(" §dNiveau:");
                    loreautoTip.add(" §f▶ §8[Activé]");
                    loreautoTip.add(" §f➟ §c[Désactivé]");
                }
                loreautoTip.add("");
                loreautoTip.add("§8➡ §fCliquez pour changer.");
                autoTipM.setLore(loreautoTip);
                autoTip.setItemMeta(autoTipM);
                inv.setItem(23, autoTip);

                ItemStack lobbyProtection = new ItemStack(Material.BED, 1);
                ItemMeta lobbyProtectionM = lobbyProtection.getItemMeta();
                lobbyProtectionM.setDisplayName("§aLobby protection");
                ArrayList<String> lorelobbyProtection = new ArrayList<String>();
                lorelobbyProtection.add("");
                lorelobbyProtection.add(" §aDescription:");
                if (settingInfo.getLobbyProtection().equalsIgnoreCase("activer"))
                {
                    lorelobbyProtection.add(" §f▶ §7Vous devez confirmer");
                    lorelobbyProtection.add(" §f▶ §7la commande /hub.");
                    lorelobbyProtection.add("");
                    lorelobbyProtection.add(" §dNiveau:");
                    lorelobbyProtection.add(" §f➟ §a[Activé]");
                    lorelobbyProtection.add(" §f▶ §8[Désactivé]");
                }
                else if (settingInfo.getLobbyProtection().equalsIgnoreCase("desactiver"))
                {
                    lorelobbyProtection.add(" §f▶ §7Vous ne devez pas");
                    lorelobbyProtection.add(" §f▶ §7confirmer la commande /hub.");
                    lorelobbyProtection.add("");
                    lorelobbyProtection.add(" §dNiveau:");
                    lorelobbyProtection.add(" §f▶ §8[Activé]");
                    lorelobbyProtection.add(" §f➟ §c[Désactivé]");
                }
                lorelobbyProtection.add("");
                lorelobbyProtection.add("§8➡ §fCliquez pour changer.");
                lobbyProtectionM.setLore(lorelobbyProtection);
                lobbyProtection.setItemMeta(lobbyProtectionM);
                inv.setItem(24, lobbyProtection);



                ++t;
                if (t == 15) {
                    t = 0;
                    run();
                }
            }
        }.runTaskTimer((Plugin)api, 0L, 15L);
    }

    public static void guiGuild(Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil (Réglages)");
        p.openInventory(inv);
        FriendInfo friendInfo = new FriendInfo(p.getName());
        SettingInfo settingInfo = new SettingInfo(p);

        /*
         * Type de pages;
         *  - Liste d'amis: 0
         *  - Liste de demandes: 1
         */


        new BukkitRunnable() {
            int t = 0;
            public void run() {

                if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Profil (Réglages)")) { cancel(); }

                ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)11);
                ItemMeta decoM = deco.getItemMeta();
                decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                decoM.setDisplayName("§r");
                deco.setItemMeta(decoM);
                inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);


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

                ItemStack socialSettings = getSkull("http://textures.minecraft.net/texture/c2eb28cd6c7524b14d59f3b8c465dfc78be8e0dac843e682c4252ae9459c78e4");
                ItemMeta socialSettingsM = socialSettings.getItemMeta();
                socialSettingsM.setDisplayName("§9§lRéglages sociaux");
                ArrayList<String> loresocialSettings = new ArrayList<String>();
                loresocialSettings.add("");
                loresocialSettings.add(" §aDescription:");
                loresocialSettings.add(" §f▶ §7Modifiez vos réglages sociaux.");
                loresocialSettings.add("");
                loresocialSettings.add("§8➡ §fCliquez pour y accéder.");
                socialSettingsM.setLore(loresocialSettings);
                socialSettings.setItemMeta(socialSettingsM);
                inv.setItem(3, socialSettings);

                ItemStack lobbySettings = getSkull("http://textures.minecraft.net/texture/d41d13a1855a75d6ae1ae2b2837d76d920c5f7739e64469841658f917fc0");
                ItemMeta lobbySettingsM = lobbySettings.getItemMeta();
                lobbySettingsM.setDisplayName("§a§lRéglages lobby");
                ArrayList<String> lorelobbySettings = new ArrayList<String>();
                lorelobbySettings.add("");
                lorelobbySettings.add(" §aDescription:");
                lorelobbySettings.add(" §f▶ §7Modifiez vos réglages lobby.");
                lorelobbySettings.add("");
                lorelobbySettings.add("§8➡ §fCliquez pour y accéder.");
                lobbySettingsM.setLore(lorelobbySettings);
                lobbySettings.setItemMeta(lobbySettingsM);
                inv.setItem(4, lobbySettings);

                ItemStack guildSettings = getSkull("http://textures.minecraft.net/texture/78a3324c77fdccd597774304bbda15517ea2359de53897aed09528d1c6ec9");
                ItemMeta guildSettingsM = guildSettings.getItemMeta();
                guildSettingsM.setDisplayName("§b§lRéglages guild");
                ArrayList<String> loreguildSettings = new ArrayList<String>();
                loreguildSettings.add("");
                loreguildSettings.add(" §aDescription:");
                loreguildSettings.add(" §f▶ §7Modifiez vos réglages guild.");
                loreguildSettings.add("");
                loreguildSettings.add("§8➡ §fCliquez pour y accéder.");
                guildSettingsM.setLore(loreguildSettings);
                guildSettings.setItemMeta(guildSettingsM);
                inv.setItem(5, guildSettings);


                ItemStack guildChat = new ItemStack(Material.PAPER, 1);
                ItemMeta guildChatM = guildChat.getItemMeta();
                guildChatM.setDisplayName("§aChat de guild");
                ArrayList<String> loreguildChat = new ArrayList<String>();
                loreguildChat.add("");
                loreguildChat.add(" §aDescription:");
                if (settingInfo.getGuildChat().contains("bas"))
                {
                    loreguildChat.add(" §f▶ §7Votre chat de");
                    loreguildChat.add("   §7guild est actif.");
                    loreguildChat.add("");
                    loreguildChat.add(" §dInformations:");
                    loreguildChat.add(" §f▶ §8[Max]");
                    loreguildChat.add(" §f▶ §8[Moyen]");
                    loreguildChat.add(" §f➟ §a[Bas]");
                }
                else if (settingInfo.getGuildChat().contains("moyen"))
                {
                    loreguildChat.add(" §f▶ §7Votre chat de guild");
                    loreguildChat.add("   §7est actif dans les lobbys.");
                    loreguildChat.add("   §7et en lobbys d'attentes.");
                    loreguildChat.add("");
                    loreguildChat.add(" §dInformations:");
                    loreguildChat.add(" §f▶ §8[Max]");
                    loreguildChat.add(" §f➟ §e[Moyen]");
                    loreguildChat.add(" §f▶ §8[Bas]");
                }
                else if (settingInfo.getGuildChat().contains("max"))
                {
                    loreguildChat.add(" §f▶ §7Votre chat de guild est");
                    loreguildChat.add("   §7totalement désactivé.");
                    loreguildChat.add("");
                    loreguildChat.add(" §dInformations:");
                    loreguildChat.add(" §f➟ §c[Max]");
                    loreguildChat.add(" §f▶ §8[Moyen]");
                    loreguildChat.add(" §f▶ §8[Bas]");
                }
                loreguildChat.add("");
                loreguildChat.add("§8➡ §fCliquez pour changer.");
                guildChatM.setLore(loreguildChat);
                guildChat.setItemMeta(guildChatM);
                inv.setItem(21, guildChat);

                ItemStack guildNotification = new ItemStack(Material.SLIME_BALL, 1);
                ItemMeta guildNotificationM = guildNotification.getItemMeta();
                guildNotificationM.setDisplayName("§aNotifications de guild");
                ArrayList<String> loreguildNotification = new ArrayList<String>();
                loreguildNotification.add("");
                loreguildNotification.add(" §aDescription:");
                if (settingInfo.getGuildNotification().equalsIgnoreCase("activer"))
                {
                    loreguildNotification.add(" §f▶ §7Vous recevez toutes");
                    loreguildNotification.add("   §7les notifications de guild.");
                    loreguildNotification.add("");
                    loreguildNotification.add(" §dNiveau:");
                    loreguildNotification.add(" §f➟ §a[Activé]");
                    loreguildNotification.add(" §f▶ §8[Désactivé]");
                }
                else if (settingInfo.getGuildNotification().equalsIgnoreCase("desactiver"))
                {
                    loreguildNotification.add(" §f▶ §7Vous ne recevez pas");
                    loreguildNotification.add("   §7les notifications de guild.");
                    loreguildNotification.add("");
                    loreguildNotification.add(" §dNiveau:");
                    loreguildNotification.add(" §f▶ §8[Activé]");
                    loreguildNotification.add(" §f➟ §c[Désactivé]");
                }
                loreguildNotification.add("");
                loreguildNotification.add("§8➡ §fCliquez pour changer.");
                guildNotificationM.setLore(loreguildNotification);
                guildNotification.setItemMeta(guildNotificationM);
                inv.setItem(23, guildNotification);





                ++t;
                if (t == 15) {
                    t = 0;
                    run();
                }
            }
        }.runTaskTimer((Plugin)api, 0L, 15L);
    }

}
