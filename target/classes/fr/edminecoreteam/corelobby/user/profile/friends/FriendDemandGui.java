package fr.edminecoreteam.corelobby.user.profile.friends;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.user.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.user.profile.group.GroupGui;
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
import org.bukkit.event.inventory.InventoryAction;
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
import java.util.List;

public class FriendDemandGui implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }
    static HashMap<Player, Integer> pageCount = new HashMap<Player, Integer>();
    private List<Player> chatBoxAddFriend;
    public FriendDemandGui() {
        chatBoxAddFriend = new ArrayList<Player>();
    }
    public List<Player> getChatBoxAddFriend() {
        return this.chatBoxAddFriend;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        InventoryAction a = e.getAction();
        FriendInfo friendInfo = new FriendInfo(p.getName());
        int MaxPage = friendInfo.getFriendRequestPageNumber();
        int FMaxPage = friendInfo.getFriendPageNumber();
        if (it == null) {  return; }

        if (inv.getName().contains("§8Profil (Demandes D'Amis)"))
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
                if(it.getItemMeta().getDisplayName() == "§d§lAmis §c❤")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FriendGui.gui(p, 1, friendInfo.getFriendPageNumber());
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
                if(it.getItemMeta().getDisplayName() != "§c§lProfil" &&
                        it.getItemMeta().getDisplayName() != "§a§lDemandes d'amis" &&
                        it.getItemMeta().getDisplayName() != "§9§lGroupes" &&
                        it.getItemMeta().getDisplayName() != "§d§lAmis §c❤" &&
                        it.getItemMeta().getDisplayName() != "§d§lAjouter un ami" &&
                        it.getItemMeta().getDisplayName() != "§b§lVotre Guild")
                {
                    String target = it.getItemMeta().getDisplayName().replace("§a", "");
                    confirmAddFriend(p, target);
                }
            }
            if (it.getType() == Material.ARROW)
            {
                if(it.getItemMeta().getDisplayName() == "§8§l⬇ §7Retour §8§l⬇")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FriendGui.gui(p, 1, FMaxPage);
                }
            }
            if (a == InventoryAction.DROP_ONE_SLOT)
            {
                if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE)
                {
                    if(it.getItemMeta().getDisplayName() == "§c§lProfil" || it.getItemMeta().getDisplayName() == "§9§lVos Réglages" || it.getItemMeta().getDisplayName() == "§9§lGroupes" || it.getItemMeta().getDisplayName() == "§d§lAmis §c❤" || it.getItemMeta().getDisplayName() == "§d§lAjouter un ami")
                    {
                        e.setCancelled(true);
                    } else
                    {
                        String target = it.getItemMeta().getDisplayName().replace("§a", "");
                        p.closeInventory();
                        confirmRemoveFriend(p, target);
                    }
                }
            }

        }
    }

    public static void gui(Player p, int Page, int MaxPage) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil (Demandes D'Amis) " + Page + "/" + MaxPage);
        p.openInventory(inv);
        FriendInfo friendInfo = new FriendInfo(p.getName());
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

                if (!p.getOpenInventory().getTitle().contains("§8Profil (Demandes D'Amis)")) { cancel(); }
                ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
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

        ItemStack friendprofildemand = getSkull("http://textures.minecraft.net/texture/69c5f5d046bdee6c3996d5c4deab28b39cd0fa2d934cc2733f149d6bcbdf8ca");
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

        ItemStack addfriend = getSkull("http://textures.minecraft.net/texture/f3bfe4131a6f612c75b45d80839bcb37edd7e8717e695a3e64ce9d033beafe6");
        SkullMeta addfriendM = (SkullMeta) addfriend.getItemMeta();
        addfriendM.setDisplayName("§d§lAjouter un ami");
        ArrayList<String> loreaddfriend = new ArrayList<String>();
        loreaddfriend.add("");
        loreaddfriend.add(" §aDescription:");
        loreaddfriend.add(" §f▶ §7Ajouter de manière simple");
        loreaddfriend.add(" §f  §7un joueur de votre choix.");
        loreaddfriend.add("");
        loreaddfriend.add("§8➡ §fCliquez pour y accéder.");
        addfriendM.setLore(loreaddfriend);
        addfriend.setItemMeta(addfriendM);
        inv.setItem(4, addfriend);


        int slot = 20;
        int friendsCount = 0;
        for (String friends : friendInfo.getFriendRequestForPage(Page)) {
            ++friendsCount;
            ItemStack friend = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
            SkullMeta friendM = (SkullMeta) friend.getItemMeta();
            friendM.setOwner(friends);
            friendM.setDisplayName("§a" + friends);
            ArrayList<String> lorefriend = new ArrayList<String>();
            lorefriend.add("");
            lorefriend.add("§8➡ §fClique: §aAccepter");
            lorefriend.add("§8➡ §fDROP: §cRefuser");
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
        }

    }

    private void confirmRemoveFriend(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §d§lInformations §d(amis):");
        p.sendMessage(" §7● §fVoulez-vous vraiment refuser §c§l" + target + "§7?");
        TextComponent confirm = new TextComponent(" §f➡ §a[Confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez-ici pour refuser " + target).create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f deny " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }

    private void confirmAddFriend(Player p, String target) {
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage(" §7» §d§lInformations §d(amis):");
        p.sendMessage(" §7● §fVoulez-vous vraiment accepter §b§l" + target + "§7?");
        TextComponent confirm = new TextComponent(" §f➡ §a[Confirmer]");
        confirm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Cliquez-ici pour accepter " + target).create()));
        confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f accept " + target));
        p.spigot().sendMessage(confirm);
        p.sendMessage("");
    }
}
