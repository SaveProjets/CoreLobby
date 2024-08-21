package fr.edminecoreteam.corelobby.profile;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
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

import fr.edminecoreteam.corelobby.profile.settings.SettingInfo;


//import fr.edminecoreteam.corelobby.utils.SkullNBT;

public class ProfileItem implements Listener
{

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        if (it.getType() == Material.SKULL_ITEM)
        {
            if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lProfil §7• Clique") || it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lProfile §7• Click") || it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lPerfile §7• Clic") || it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lPerfile §7• Clic") || it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lProfil §7• Sklick"))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
                ProfileGUI.gui(p);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();
        if (it == null) { return; }

        if (it.getType() == Material.SKULL_ITEM
                && (it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lProfil §7• Clique") || it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lProfile §7• Click") || it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lPerfile §7• Clic") || it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lPerfile §7• Clic") || it.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lProfil §7• Sklick"))
                && (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK || a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK))
        {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
            ProfileGUI.gui(p);
        }
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        SettingInfo settingInfo = new SettingInfo(p);

        ItemBuilder ib = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                .setSkullOwner(p.getName());
        if(settingInfo.getLang() == 0){
            ib.setName("§c§lProfil §7• Clique");
        }else if(settingInfo.getLang() == 1){
            ib.setName("§c§lProfile §7• Click");
        }else if (settingInfo.getLang() == 2){
            ib.setName("§c§lPerfile §7• Clic");
        }else if (settingInfo.getLang() == 3){
            ib.setName("§c§lProfil §7• Sklick");
        }

        ItemStack profil = ib.toItemStack();

        p.getInventory().setItem(1, profil);
    }

}
