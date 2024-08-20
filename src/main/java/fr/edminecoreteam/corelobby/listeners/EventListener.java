package fr.edminecoreteam.corelobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.edminecoreteam.corelobby.Core;

public class EventListener implements Listener
{
    private Core core = Core.getInstance();

    @EventHandler private void onWeatherChange(WeatherChangeEvent e) { e.setCancelled(true); }

    @EventHandler private void onItemDrop(PlayerDropItemEvent e) { e.setCancelled(true); }

    @SuppressWarnings("deprecation") @EventHandler
    private void onDamage(EntityDamageEvent e)
    {
        Entity p = e.getEntity();
        e.setCancelled(true);
        if (p instanceof Player) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                Location spawn = new Location(Bukkit.getWorld(core.getConfig().getString("Spawn.name")),
                        core.getConfig().getInt("Spawn.x"), core.getConfig().getInt("Spawn.y"), core.getConfig().getInt("Spawn.z"),
                        core.getConfig().getInt("Spawn.t"), core.getConfig().getInt("Spawn.b"));
                p.teleport(spawn);
                ((Player) p).sendTitle("§c§lHop Hop Hop !", "§7Tu ne peux pas quitter la cité des dieux...");
            }
        }
    }

    @EventHandler private void onHungerBarChange(FoodLevelChangeEvent e) { e.setFoodLevel(20); }

    @EventHandler
    public void chairEnter(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if (p.getItemInHand().getType() == Material.AIR)
            {
                if(e.getClickedBlock().getType() == Material.WOOD_STAIRS)
                {
                    Location blockLock = e.getClickedBlock().getLocation();
                    float x = (float) (blockLock.getBlockX() + 0.5);
                    float y = (float) (blockLock.getBlockY() - 0.9);
                    float z = (float) (blockLock.getBlockZ() + 0.5);

                    Location customloc = new Location(Bukkit.getWorld(p.getWorld().getName()), x, y, z);
                    ArmorStand armorStand = (ArmorStand)Bukkit.getWorld(p.getWorld().getName()).spawnEntity(customloc, EntityType.ARMOR_STAND);
                    armorStand.setSmall(true);
                    armorStand.setVisible(false);
                    armorStand.setCustomName("chair_" + p.getName());
                    armorStand.setCustomNameVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setPassenger(p);
					/*Location l = e.getClickedBlock().getLocation();
					World w = p.getWorld();
					arrow = w.spawn(l.add(0.5D, 0.2D, 0.5D), Arrow.class);
		            arrow.setPassenger(p);*/
                }
            }
        }
    }

    @EventHandler
    public void onClickItemsInHub(PlayerInteractEvent e)
    {
        if (e.getClickedBlock() == null) {  return; }

        if (e.getClickedBlock().getType() == Material.TRAP_DOOR)
        {
            e.setCancelled(true);
        }
        if (e.getClickedBlock().getType() == Material.FENCE_GATE)
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void chairLeave(PlayerToggleSneakEvent e)
    {
        Player p = e.getPlayer();
        World world = p.getWorld();

        for (Entity aS : world.getEntities())
        {
            if (aS instanceof ArmorStand)
            {
                ArmorStand armorStand = (ArmorStand)aS;
                String name = armorStand.getCustomName();
                if (name != null && name.equalsIgnoreCase("chair_" + p.getName()))
                {
                    aS.remove();
                }
            }
        }
    }
}
