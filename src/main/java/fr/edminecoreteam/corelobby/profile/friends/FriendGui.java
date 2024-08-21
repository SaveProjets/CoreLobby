package fr.edminecoreteam.corelobby.profile.friends;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.account.AccountInfo;
import fr.edminecoreteam.corelobby.listeners.PlayerChatListener;
import fr.edminecoreteam.corelobby.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.utils.SkullNBT;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;


public class FriendGui implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }
    static HashMap<Player, Integer> pageCount = new HashMap<Player, Integer>();

    private static String favorisSymbol = " §e✯";

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack it = e.getCurrentItem();
        InventoryAction a = e.getAction();
        ClickType c = e.getClick();
        FriendInfo friendInfo = new FriendInfo(p.getName());
        int MaxPage = friendInfo.getFriendPageNumber();
        if (it == null) {  return; }

        if (inv.getName().contains("§8Profil (Amis)"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM)
            {
                if(it.getItemMeta().getDisplayName() == "§c§lProfil")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    ProfileGUI.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§9§lGroupes")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    GroupGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§a§lDemandes d'amis")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FriendDemandGui.gui(p, 1, friendInfo.getFriendRequestPageNumber());
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
            if (a == InventoryAction.DROP_ONE_SLOT) {
                if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE) {
                    if (it.getItemMeta().getDisplayName() == "§8⬅ §7Page Précédente" || it.getItemMeta().getDisplayName() == "§8➡ §7Page Suivante" || it.getItemMeta().getDisplayName() == "§c§lProfil" || it.getItemMeta().getDisplayName() == "§b§lVotre Guild" || it.getItemMeta().getDisplayName() == "§9§lGroupes" || it.getItemMeta().getDisplayName() == "§d§lAmis §c❤" || it.getItemMeta().getDisplayName() == "§d§lAjoutez un ami" || it.getItemMeta().getDisplayName() == "§a§lDemandes d'amis") {
                        e.setCancelled(true);
                    } else {
                        String target = it.getItemMeta().getDisplayName().replace("§a", "");
                        p.closeInventory();
                        confirmRemoveFriend(p, target);
                    }
                }
            }
            if(c == ClickType.RIGHT || c == ClickType.LEFT) {
                if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE) {
                    if (it.getItemMeta().getDisplayName() == "§8⬅ §7Page Précédente" || it.getItemMeta().getDisplayName() == "§8➡ §7Page Suivante" || it.getItemMeta().getDisplayName() == "§c§lProfil" || it.getItemMeta().getDisplayName() == "§b§lVotre Guild" || it.getItemMeta().getDisplayName() == "§9§lGroupes" || it.getItemMeta().getDisplayName() == "§d§lAmis §c❤" || it.getItemMeta().getDisplayName() == "§d§lAjoutez un ami" || it.getItemMeta().getDisplayName() == "§a§lDemandes d'amis") {
                        e.setCancelled(true);
                    } else {
                        if (it.getItemMeta().getDisplayName().contains(favorisSymbol)) {
                            String target = it.getItemMeta().getDisplayName().replace("§a", "");
                            target = target.replace(favorisSymbol, "");
                            p.closeInventory();
                            confirmRemoveFavoris(p, target);
                        } else {
                            String target = it.getItemMeta().getDisplayName().replace("§a", "");
                            p.closeInventory();
                            confirmAddFavoris(p, target);
                        }
                    }
                }
                if (it.getItemMeta().getDisplayName().equalsIgnoreCase("§f§lTriez vos amis")){
                    if(friendInfo.getFriendSort() == 2){
                        friendInfo.setFriendSort(p.getName(), 3);
                    }else if(friendInfo.getFriendSort() == 3){
                        friendInfo.setFriendSort(p.getName(), 1);
                    }else{
                        friendInfo.setFriendSort(p.getName(), 2);
                    }
                    gui(p, 1, friendInfo.getFriendPageNumber());
                }

            }


        }
        if (it.getType() == Material.SKULL_ITEM && it.getItemMeta().getDisplayName() == "§d§lAjoutez un ami") {
            addFriend(p);
        }
    }

    public static void gui(Player p, int Page, int MaxPage) {


        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil (Amis) " + Page + "/" + MaxPage);
        FriendInfo friendInfo = new FriendInfo(p.getName());
        pageCount.put(p, Page);
        int friendPerPage = 10;

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



                ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)6);
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

        ItemStack friendprofil = getSkull("http://textures.minecraft.net/texture/847f40b2a22edb1a887469a6198daf468eccc495e35de8d49eae65b48468cf8");
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

        ItemStack friendprofildemand = getSkull("http://textures.minecraft.net/texture/27e8abb6786cf0c8b7f83da36bf5a452edf54d20e230963298ea77b8c3f2d015");
        ItemMeta friendprofildemandM = friendprofildemand.getItemMeta();
        friendprofildemandM.setDisplayName("§a§lDemandes d'amis");
        ArrayList<String> lorefriendprofildemand = new ArrayList<String>();
        lorefriendprofildemand.add("");
        lorefriendprofildemand.add(" §aDescription:");
        lorefriendprofildemand.add(" §f▶ §7Visionnez et gérez");
        lorefriendprofildemand.add(" §f  §7vos demandes d'amis.");
        lorefriendprofildemand.add("");
        lorefriendprofildemand.add(" §f▶ §7Vos demandes: §6" + friendInfo.getFriendDemandCount());
        lorefriendprofildemand.add("");
        lorefriendprofildemand.add("§8➡ §fCliquez pour y accéder.");
        friendprofildemandM.setLore(lorefriendprofildemand);
        friendprofildemand.setItemMeta(friendprofildemandM);
        inv.setItem(3, friendprofildemand);

        ItemStack addfriend = getSkull("http://textures.minecraft.net/texture/f3bfe4131a6f612c75b45d80839bcb37edd7e8717e695a3e64ce9d033beafe6");
        SkullMeta addfriendM = (SkullMeta) addfriend.getItemMeta();
        addfriendM.setDisplayName("§d§lAjoutez un ami");
        ArrayList<String> loreaddfriend = new ArrayList<String>();
        loreaddfriend.add("");
        loreaddfriend.add(" §aDescription:");
        loreaddfriend.add(" §f▶ §7Ajoutez de manière simple");
        loreaddfriend.add(" §f  §7un joueur de votre choix.");
        loreaddfriend.add("");
        loreaddfriend.add("§8➡ §fCliquez pour y accéder.");
        addfriendM.setLore(loreaddfriend);
        addfriend.setItemMeta(addfriendM);
        inv.setItem(4, addfriend);


        ItemStack sortIt = new ItemStack(Material.MAGMA_CREAM, 1);
        ItemMeta sortItM = sortIt.getItemMeta();
        sortItM.setDisplayName("§f§lTriez vos amis");
        ArrayList<String> loresortIt = new ArrayList<String>();
        loresortIt.add("");
        loresortIt.add(" §aDescription:");
        loresortIt.add(" §f▶ §7Cliquez pour trier facilement");
        loresortIt.add(" §f  §7votre liste d'amis.");
        loresortIt.add("");

        loresortIt.add(" §dTrier par:");
        if (friendInfo.getFriendSort() == 1){
            loresortIt.add(" §f▶ §aConnectés d'abord");
            loresortIt.add(" §f▶ §8Ordre Alphabétique");
            loresortIt.add(" §f▶ §8Favoris d'abord");
        }
        if (friendInfo.getFriendSort() == 2){
            loresortIt.add(" §f▶ §8Connectés d'abord");
            loresortIt.add(" §f▶ §aOrdre Alphabétique");
            loresortIt.add(" §f▶ §8Favoris d'abord");
        }
        if (friendInfo.getFriendSort() == 3){
            loresortIt.add(" §f▶ §8Connectés d'abord");
            loresortIt.add(" §f▶ §8Ordre Alphabétique");
            loresortIt.add(" §f▶ §aFavoris d'abord");
        }
        loresortIt.add("");
        loresortIt.add("§8➡ §fCliquez pour y accéder.");
        sortItM.setLore(loresortIt);
        sortIt.setItemMeta(sortItM);
        inv.setItem(5, sortIt);


       // friendInfo = setTrie(p, friendInfo, Page);

        int slot = 20;
        int friendsCount = 0;
        for (String friends : friendInfo.getFriendForPage(Page, friendInfo.getFriendSort())) {
            ++friendsCount;
            AccountInfo friendsInfo = new AccountInfo(friends);
            ItemStack friend = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
            SkullMeta friendM = (SkullMeta) friend.getItemMeta();
            friendM.setOwner(friends);
            if(friendsInfo.isFavoris(p.getUniqueId().toString()) == 1){
                friendM.setDisplayName("§a" + friends + favorisSymbol);
            }else{
                friendM.setDisplayName("§a" + friends);
            }
            ArrayList<String> lorefriend = new ArrayList<String>();
            lorefriend.add("");
            lorefriend.add(" §dInformation:");
            lorefriend.add(" §f▶ §7Statut: " + friendsInfo.isOnline());
            lorefriend.add("");
            if(friendsInfo.isFavoris(p.getUniqueId().toString()) == 1){
                lorefriend.add("§8➡ §fCLIC: §cSupprimer des favoris");
            }else{
                lorefriend.add("§8➡ §fCLIC: §eAjouter au favoris");
            }
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
                p.openInventory(inv);
                return;
            }
        }
        p.openInventory(inv);

    }

    /*private static FriendInfo setTrie(Player p, FriendInfo friendInfo, int page){
        if(getSort.get(p) == 1){
            Collections.sort(friendInfo.getFriendForPage(page,));
        }
        return friendInfo;
    }*/

    private void addFriend(Player p) {
        PlayerChatListener.getChatBoxAddFriend().add(p);
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §d§lInformations §d(amis):");
        p.sendMessage("");
        p.sendMessage(" §7● §fVeuillez préciser un joueur...");
        p.sendMessage(" §7● §fEntrez §cCANCEL §fdans le chat pour annuler.");
        p.sendMessage("");
        new BukkitRunnable() {
            int t = 15;
            public void run() {
                if (!PlayerChatListener.getChatBoxAddFriend().contains(p))
                {
                    cancel();
                }
                --t;
                if (t == 0) {
                    if (PlayerChatListener.getChatBoxAddFriend().contains(p))
                    {
                        PlayerChatListener.getChatBoxAddFriend().remove(p);
                        p.sendMessage("§cAction annulée, vous avez mis trop de temps...");
                    }
                    cancel();
                }
            }
        }.runTaskTimer((Plugin)Core.getInstance(), 0L, 20L);
    }

    private void confirmRemoveFriend(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §d§lInformations §d(amis):");
        p.sendMessage(" §7● §fVoulez-vous vraiment supprimer §c§l" + target + "§7?");
        TextComponent confirm = new TextComponent(" §f➡ §a[Confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez-ici pour supprimer " + target + ".").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f remove " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    private void confirmAddFavoris(Player p, String target){
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §d§lInformations §d(amis):");
        p.sendMessage(" §7● §fVoulez-vous vraiment ajouter §e§l" + target + "§f à vos favoris§7?");
        TextComponent confirm = new TextComponent(" §f➡ §a[Confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez-ici pour ajouter " + target + " à vos favoris.").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f favoris add " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    private void confirmRemoveFavoris(Player p, String target){
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §d§lInformations §d(amis):");
        p.sendMessage(" §7● §fVoulez-vous vraiment supprimer §c§l" + target + "§f de vos favoris§7?");
        TextComponent confirm = new TextComponent(" §f➡ §a[Confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez-ici pour supprimer " + target + " de vos favoris.").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f favoris remove " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    public static void confirmAddFriend(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §d§lInformations §d(amis):");
        p.sendMessage(" §7● §fConfirmez-vous que vous voulez ajouter §b§l" + target + "§7?");
        TextComponent confirm = new TextComponent(" §f➡ §a[confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez-ici pour ajouter " + target + ".").create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f add " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }
}
