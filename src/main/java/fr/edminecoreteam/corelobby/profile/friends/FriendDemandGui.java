package fr.edminecoreteam.corelobby.profile.friends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.utils.SkullNBT;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

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
        FriendInfo friendInfo = new FriendInfo(p.getName());
        pageCount.put(p, Page);
        int friendPerPage = 10;

        ItemStack back = new ItemBuilder(Material.ARROW, 1)
                .setName("§8§l⬇ §7Retour §8§l⬇")
                .toItemStack();
        inv.setItem(49, back);

        ItemStack suivant = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§8➡ §7Page Suivante")
                .setSkullUrl("https://textures.minecraft.net/texture/956a3618459e43b287b22b7e235ec699594546c6fcd6dc84bfca4cf30ab9311")
                .toItemStack();

        ItemStack precedent = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§8⬅ §7Page Précédente")
                .setSkullUrl("https://textures.minecraft.net/texture/cdc9e4dcfa4221a1fadc1b5b2b11d8beeb57879af1c42362142bae1edd5")
                .toItemStack();

        if (Page != MaxPage)
        {
            if (Page == 1)
            {
                inv.setItem(50, suivant);
            }
            if (Page != 1)
            {
                inv.setItem(50, suivant);
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
                inv.setItem(48, precedent);
            }
        }
                ItemStack deco = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5)
                        .setName("§r")
                        .addEnchant(Enchantment.DAMAGE_ALL, 1)
                        .hideEnchantments()
                        .toItemStack();
                inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);

        ArrayList<String> loreProfil = new ArrayList<String>();
        loreProfil.add("");
        loreProfil.add(" §aDescription:");
        loreProfil.add(" §f▶ §7Profil de §a" + p.getName());
        loreProfil.add("");
        loreProfil.add("§8➡ §fCliquez pour y accéder.");

        ItemStack profil = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§c§lProfil")
                .setLore(loreProfil)
                .setSkullUrl("https://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b")
                .toItemStack();
        inv.setItem(18, profil);

        ArrayList<String> loreFriendProfilDemand = new ArrayList<String>();
        loreFriendProfilDemand.add("");
        loreFriendProfilDemand.add(" §aDescription:");
        loreFriendProfilDemand.add(" §f▶ §7Visionnez et gérez");
        loreFriendProfilDemand.add(" §f  §7vos demandes d'amis.");
        loreFriendProfilDemand.add("");
        loreFriendProfilDemand.add(" §f▶ §7Vos demandes: §6" + friendInfo.getFriendDemandCount());
        loreFriendProfilDemand.add("");
        loreFriendProfilDemand.add("§8➡ §fCliquez pour y accéder.");

        ItemStack friendProfilDemand = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§a§lDemandes d'amis")
                .setLore(loreFriendProfilDemand)
                .setSkullUrl("https://textures.minecraft.net/texture/69c5f5d046bdee6c3996d5c4deab28b39cd0fa2d934cc2733f149d6bcbdf8ca")
                .toItemStack();
        inv.setItem(3, friendProfilDemand);

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

        ArrayList<String> loreAddFriend = new ArrayList<String>();
        loreAddFriend.add("");
        loreAddFriend.add(" §aDescription:");
        loreAddFriend.add(" §f▶ §7Ajouter de manière simple");
        loreAddFriend.add(" §f  §7un joueur de votre choix.");
        loreAddFriend.add("");
        loreAddFriend.add("§8➡ §fCliquez pour y accéder.");

        ItemStack addFriend = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setName("§d§lAjouter un ami")
                .setLore(loreAddFriend)
                .setSkullUrl("https://textures.minecraft.net/texture/f3bfe4131a6f612c75b45d80839bcb37edd7e8717e695a3e64ce9d033beafe6")
                .toItemStack();
        inv.setItem(4, addFriend);


        int slot = 20;
        int friendsCount = 0;
        for (String friends : friendInfo.getFriendRequestForPage(Page)) {
            ++friendsCount;

            ArrayList<String> loreFriend = new ArrayList<String>();
            loreFriend.add("");
            loreFriend.add("§8➡ §fClique: §aAccepter");
            loreFriend.add("§8➡ §fDROP: §cRefuser");

            ItemStack friend = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                    .setName("§a" + friends)
                    .setLore(loreFriend)
                    .setSkullOwner(friends)
                    .toItemStack();


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
