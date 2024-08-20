package fr.edminecoreteam.corelobby.gui;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.gui.game.longgames.DeadSurvivorGui;
import fr.edminecoreteam.corelobby.gui.game.longgames.LongGamesGui;
import fr.edminecoreteam.corelobby.gui.game.other.OtherGamesGui;
import fr.edminecoreteam.corelobby.gui.game.other.PracticeGui;
import fr.edminecoreteam.corelobby.gui.game.shortgames.DeACoudreGui;
import fr.edminecoreteam.corelobby.gui.game.shortgames.PaintBallGui;
import fr.edminecoreteam.corelobby.gui.game.shortgames.ShortGamesGui;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainInteractions implements Listener
{

    private static Core core = Core.getInstance();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        if (inv.getName().equalsIgnoreCase("§8Menu Principal"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE || it.getType() == Material.SNOW_BALL || it.getType() == Material.WATER_BUCKET || it.getType() == Material.DIAMOND_SWORD || it.getType() == Material.DIAMOND_PICKAXE)
            {
                if(it.getItemMeta().getDisplayName() == "§b§lMenu Principal")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    MainGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§7Jeux: §d§lCourts")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    Core.getInstance().getGuiManager().open(p, new ShortGamesGui(core, p));
                }
                if(it.getItemMeta().getDisplayName() == "§7Jeux: §c§lLongs")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    LongGamesGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§7Jeux: §6§lDivers")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    OtherGamesGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§e§lPaintBall")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    PaintBallGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§6§lDé À Coudre")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    DeACoudreGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§4§lDead Survivor")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    DeadSurvivorGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§f§lPractice")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    PracticeGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§aRetour vers le hub")
                {
                    Location spawn = new Location(Bukkit.getWorld(core.getConfig().getString("Spawn.name")),
                            (float)core.getConfig().getLong("Spawn.x"), (float)core.getConfig().getLong("Spawn.y"), (float)core.getConfig().getLong("Spawn.z"),
                            (float)core.getConfig().getLong("Spawn.t"), (float)core.getConfig().getLong("Spawn.b"));
                    p.teleport(spawn);
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                    p.sendTitle("§dTa-Dam !", "§7Comme par magie.");
                }
                if(it.getItemMeta().getDisplayName() == "§fZone §f§lVIP")
                {
                    Location spawn = new Location(Bukkit.getWorld(core.getConfig().getString("VipZone.name")),
                            (float)core.getConfig().getLong("VipZone.x"), (float)core.getConfig().getLong("VipZone.y"), (float)core.getConfig().getLong("VipZone.z"),
                            (float)core.getConfig().getLong("VipZone.t"), (float)core.getConfig().getLong("VipZone.b"));
                    p.teleport(spawn);
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                    p.sendTitle("§dTa-Dam !", "§7Comme par magie.");
                }
            }
        }
        if (inv.getName().equalsIgnoreCase("§8Menu Principal (PaintBall)"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE || it.getType() == Material.DIAMOND_AXE)
            {
                if(it.getItemMeta().getDisplayName() == "§b§lMenu Principal")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    MainGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§e§lJouer")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FoundGame.startFound(p, "PaintBall_TwovsFour_");
                    p.closeInventory();
                }
            }
        }
        if (inv.getName().equalsIgnoreCase("§8Menu Principal (Dé À Coudre)"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE || it.getType() == Material.DIAMOND_AXE)
            {
                if(it.getItemMeta().getDisplayName() == "§b§lMenu Principal")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    MainGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§e§lJouer")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FoundGame.startFound(p, "DeACoudre");
                    p.closeInventory();
                }
            }
        }
        if (inv.getName().equalsIgnoreCase("§8Menu Principal (Dead Survivor)"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE || it.getType() == Material.DIAMOND_AXE)
            {
                if(it.getItemMeta().getDisplayName() == "§b§lMenu Principal")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    MainGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§e§lJouer")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FoundGame.startFound(p, "DeadSurvivor");
                    p.closeInventory();
                }
            }
        }
        if (inv.getName().equalsIgnoreCase("§8Menu Principal (Practice)"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE || it.getType() == Material.DIAMOND_AXE || it.getType() == Material.IRON_CHESTPLATE)
            {
                if(it.getItemMeta().getDisplayName() == "§b§lMenu Principal")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    MainGui.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§e§lJouer")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FoundGame.startFound(p, "Practice");
                    p.closeInventory();
                }
            }
        }
    }
}
