package fr.edminecoreteam.corelobby.mainmenu.longgame;

import java.util.ArrayList;

import fr.edminecoreteam.api.utils.builder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.mainmenu.OnlinesForGroups;
import fr.edminecoreteam.corelobby.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;

public class LongGamesGui implements Listener
{
    private static Core api = Core.getInstance();

    public static void gui(Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Menu Principal");
        p.openInventory(inv);
        SettingInfo settingInfo = new SettingInfo(p);

        /*
         * Type de pages;
         *  - Liste d'amis: 0
         *  - Liste de demandes: 1
         */

        if (settingInfo.getLang() == 0)
        {

            new BukkitRunnable() {
                int t = 0;
                public void run() {

                    if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Menu Principal")) { cancel(); }

                    ItemStack deco = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 6)
                            .setName("§r")
                            .addEnchant(Enchantment.DAMAGE_ALL, 1)
                            .hideEnchantments()
                            .toItemStack();
                    inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                    inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);

                    ArrayList<String> loremain = new ArrayList<String>();
                    loremain.add("");
                    loremain.add(" §aDescription:");
                    loremain.add(" §f▶ §7Section principal.");
                    loremain.add("");
                    loremain.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack main = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§b§lMenu Principal")
                            .setLore(loremain)
                            .setSkullUrl("https://textures.minecraft.net/texture/ddcd8fe8d4d5c05886db9f59127961549e602f30c51759998131b2ad25d264")
                            .toItemStack();
                    inv.setItem(18, main);

                    ArrayList<String> lorehost = new ArrayList<String>();
                    lorehost.add("");
                    lorehost.add(" §aDescription:");
                    lorehost.add(" §f▶ §7Ici, libre a votre imagination de");
                    lorehost.add(" §f  §7créez ou jouez sur des parties custom.");
                    lorehost.add("");
                    lorehost.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack host = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§d§lServeur customisé")
                            .setLore(lorehost)
                            .setSkullUrl("https://textures.minecraft.net/texture/c2eb28cd6c7524b14d59f3b8c465dfc78be8e0dac843e682c4252ae9459c78e4")
                            .toItemStack();
                    inv.setItem(27, host);

                    ArrayList<String> lorevip = new ArrayList<String>();
                    lorevip.add("");
                    lorevip.add(" §aDescription:");
                    lorevip.add(" §f▶ §7...");
                    lorevip.add("");
                    lorevip.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack vip = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§f§lFonctions VIP")
                            .setLore(lorevip)
                            .setSkullUrl("https://textures.minecraft.net/texture/633c89a3c529d5136be6c49a62be0383fc3722cc990142e5cb3cc96db199d7d")
                            .toItemStack();
                    inv.setItem(26, vip);

                    ArrayList<String> loresettings = new ArrayList<String>();
                    loresettings.add("");
                    loresettings.add(" §aDescription:");
                    loresettings.add(" §f▶ §7Modifiez à votre guise les");
                    loresettings.add(" §f  §7différents réglages disponibles.");
                    loresettings.add("");
                    loresettings.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack settings = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§9§lParamètres")
                            .setLore(loresettings)
                            .setSkullUrl("https://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b")
                            .toItemStack();
                    inv.setItem(35, settings);

                    ArrayList<String> loredeadSurvivor = new ArrayList<String>();
                    loredeadSurvivor.add("");
                    loredeadSurvivor.add(" §aDescription:");
                    loredeadSurvivor.add(" §f▶ §7Dans ce jeu,");
                    loredeadSurvivor.add(" §f  §7échappez-vous de la");
                    loredeadSurvivor.add(" §f  §7carte en activant tous");
                    loredeadSurvivor.add(" §f  §7les réacteurs.");
                    loredeadSurvivor.add(" §f▶ §7Attention ! Ne soyez pas");
                    loredeadSurvivor.add(" §f  §7l'un des pendus.");
                    loredeadSurvivor.add("");
                    loredeadSurvivor.add(" §dInformations:");
                    loredeadSurvivor.add(" §f▶ §7Joueur(s): §e" + OnlinesForGroups.getOnlinesFromGroup("deadsurvivor"));
                    loredeadSurvivor.add(" §f▶ §7File d'attente: ");
                    loredeadSurvivor.add("");
                    loredeadSurvivor.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack deadSurvivor = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§4§lDead Survivor")
                            .setLore(loredeadSurvivor)
                            .setSkullUrl("https://textures.minecraft.net/texture/ec778558b3e858a92e3a31971d95eb4316fb868982c0f380aaa38b690cc41ce8")
                            .toItemStack();
                    inv.setItem(22, deadSurvivor);


                    ++t;
                    if (t == 6) {
                        t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 15L);

        }
    }
}
