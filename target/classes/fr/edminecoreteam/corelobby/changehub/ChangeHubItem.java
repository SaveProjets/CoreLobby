package fr.edminecoreteam.corelobby.changehub;

import fr.edminecoreteam.corelobby.user.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ChangeHubItem implements Listener
{

    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        ChangeHubInfo changeHubInfo = new ChangeHubInfo("Lobby");
        if (it == null) {  return; }
        if (it.getType() == Material.SKULL_ITEM)
        {
            if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lChanger De Hub §7• Clique"))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
                ChangeHubGui.gui(p, 1, changeHubInfo.getServerPageNumber());
            }
            if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lChange Hub §7• Click"))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
                ChangeHubGui.gui(p, 1, changeHubInfo.getServerPageNumber());
            }
            if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCentro de cambios §7• Clic"))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
                ChangeHubGui.gui(p, 1, changeHubInfo.getServerPageNumber());
            }
            if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lHub wechseln §7• Sklick"))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
                ChangeHubGui.gui(p, 1, changeHubInfo.getServerPageNumber());
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack it = e.getItem();
        if (it == null) { return; }
        Action a = e.getAction();
        ChangeHubInfo changeHubInfo = new ChangeHubInfo("Lobby");
        if (it.getType() == Material.SKULL_ITEM
                && it.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lChanger De Hub §7• Clique")
                && (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK))
        {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
            ChangeHubGui.gui(p, 1, changeHubInfo.getServerPageNumber());
        }
        if (it.getType() == Material.SKULL_ITEM
                && it.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lChange Hub §7• Click")
                && (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK))
        {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
            ChangeHubGui.gui(p, 1, changeHubInfo.getServerPageNumber());
        }
        if (it.getType() == Material.SKULL_ITEM
                && it.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCentro de cambios §7• Clic")
                && (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK))
        {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
            ChangeHubGui.gui(p, 1, changeHubInfo.getServerPageNumber());
        }
        if (it.getType() == Material.SKULL_ITEM
                && it.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lHub wechseln §7• Sklick")
                && (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK))
        {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
            ChangeHubGui.gui(p, 1, changeHubInfo.getServerPageNumber());
        }
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        SettingInfo settingInfo = new SettingInfo(p);

        if (settingInfo.getLang() == 0)
        {
            ItemStack profil = getSkull("http://textures.minecraft.net/texture/7b7cda83e845f17f96eb686a9edfed3026ac65d74e54bdf283dd09d9c254dee7");
            SkullMeta profilM = (SkullMeta)profil.getItemMeta();
            profilM.setDisplayName("§d§lChanger De Hub §7• Clique");
            profil.setItemMeta((ItemMeta)profilM);
            p.getInventory().setItem(7, profil);
        }
        if (settingInfo.getLang() == 1)
        {
            ItemStack profil = getSkull("http://textures.minecraft.net/texture/7b7cda83e845f17f96eb686a9edfed3026ac65d74e54bdf283dd09d9c254dee7");
            SkullMeta profilM = (SkullMeta)profil.getItemMeta();
            profilM.setDisplayName("§d§lChange Hub §7• Click");
            profil.setItemMeta((ItemMeta)profilM);
            p.getInventory().setItem(7, profil);
        }
        if (settingInfo.getLang() == 2)
        {
            ItemStack profil = getSkull("http://textures.minecraft.net/texture/7b7cda83e845f17f96eb686a9edfed3026ac65d74e54bdf283dd09d9c254dee7");
            SkullMeta profilM = (SkullMeta)profil.getItemMeta();
            profilM.setDisplayName("§d§lCentro de cambios §7• Clic");
            profil.setItemMeta((ItemMeta)profilM);
            p.getInventory().setItem(7, profil);
        }
        if (settingInfo.getLang() == 3)
        {
            ItemStack profil = getSkull("http://textures.minecraft.net/texture/7b7cda83e845f17f96eb686a9edfed3026ac65d74e54bdf283dd09d9c254dee7");
            SkullMeta profilM = (SkullMeta)profil.getItemMeta();
            profilM.setDisplayName("§d§lHub wechseln §7• Sklick");
            profil.setItemMeta((ItemMeta)profilM);
            p.getInventory().setItem(7, profil);
        }
    }

}
