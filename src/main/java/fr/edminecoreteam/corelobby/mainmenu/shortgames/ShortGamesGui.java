package fr.edminecoreteam.corelobby.mainmenu.shortgames;

import java.util.ArrayList;

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

public class ShortGamesGui implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }

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

                    ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)6);
                    ItemMeta decoM = deco.getItemMeta();
                    decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    decoM.setDisplayName("§r");
                    deco.setItemMeta(decoM);
                    inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                    inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);

                    ItemStack main = getSkull("http://textures.minecraft.net/texture/ddcd8fe8d4d5c05886db9f59127961549e602f30c51759998131b2ad25d264");
                    ItemMeta mainM = main.getItemMeta();
                    mainM.setDisplayName("§b§lMenu Principal");
                    ArrayList<String> loremain = new ArrayList<String>();
                    loremain.add("");
                    loremain.add(" §aDescription:");
                    loremain.add(" §f▶ §7Section principal.");
                    loremain.add("");
                    loremain.add("§8➡ §fCliquez pour y accéder.");
                    mainM.setLore(loremain);
                    main.setItemMeta(mainM);
                    inv.setItem(18, main);

                    ItemStack host = getSkull("http://textures.minecraft.net/texture/c2eb28cd6c7524b14d59f3b8c465dfc78be8e0dac843e682c4252ae9459c78e4");
                    ItemMeta hostM = host.getItemMeta();
                    hostM.setDisplayName("§d§lServeur Customisé");
                    ArrayList<String> lorehost = new ArrayList<String>();
                    lorehost.add("");
                    lorehost.add(" §aDescription:");
                    lorehost.add(" §f▶ §7Ici, libre a votre imagination de");
                    lorehost.add(" §f  §7créez ou jouez sur des parties custom.");
                    lorehost.add("");
                    lorehost.add("§8➡ §fCliquez pour y accéder.");
                    hostM.setLore(lorehost);
                    host.setItemMeta(hostM);
                    inv.setItem(27, host);

                    ItemStack vip = getSkull("http://textures.minecraft.net/texture/633c89a3c529d5136be6c49a62be0383fc3722cc990142e5cb3cc96db199d7d");
                    ItemMeta vipM = vip.getItemMeta();
                    vipM.setDisplayName("§f§lFonctions VIP");
                    ArrayList<String> lorevip = new ArrayList<String>();
                    lorevip.add("");
                    lorevip.add(" §aDescription:");
                    lorevip.add(" §f▶ §7...");
                    lorevip.add("");
                    lorevip.add("§8➡ §fCliquez pour y accéder.");
                    vipM.setLore(lorevip);
                    vip.setItemMeta(vipM);
                    inv.setItem(26, vip);

                    ItemStack settings = getSkull("http://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b");
                    ItemMeta settingsM = settings.getItemMeta();
                    settingsM.setDisplayName("§9§lParamètres");
                    ArrayList<String> loresettings = new ArrayList<String>();
                    loresettings.add("");
                    loresettings.add(" §aDescription:");
                    loresettings.add(" §f▶ §7Modifiez à votre guise les");
                    loresettings.add(" §f  §7différents réglages disponibles.");
                    loresettings.add("");
                    loresettings.add("§8➡ §fCliquez pour y accéder.");
                    settingsM.setLore(loresettings);
                    settings.setItemMeta(settingsM);
                    inv.setItem(35, settings);

                    ItemStack paintball = new ItemStack(Material.SNOW_BALL, 1);
                    ItemMeta paintballM = paintball.getItemMeta();
                    paintballM.setDisplayName("§e§lPaintBall");
                    ArrayList<String> lorepaintball = new ArrayList<String>();
                    lorepaintball.add("");
                    lorepaintball.add(" §aDescription:");
                    lorepaintball.add(" §f▶ §7Dans ce jeu, découvrez");
                    lorepaintball.add(" §f  §7une bataille de boules de");
                    lorepaintball.add(" §f  §7neige unique. Devenez le");
                    lorepaintball.add(" §f  §7meilleur lanceur pour");
                    lorepaintball.add(" §f  §7remporter la partie !");
                    lorepaintball.add("");
                    lorepaintball.add(" §dInformations:");
                    lorepaintball.add(" §f▶ §7Joueur(s): §e" + OnlinesForGroups.getOnlinesFromGroup("paintball2vs4"));
                    lorepaintball.add(" §f▶ §7File d'attente: ");
                    lorepaintball.add("");
                    lorepaintball.add("§8➡ §fCliquez pour y accéder.");
                    paintballM.setLore(lorepaintball);
                    paintball.setItemMeta(paintballM);
                    inv.setItem(30, paintball);

                    ItemStack deacoudre = new ItemStack(Material.WATER_BUCKET, 1);
                    ItemMeta deacoudreM = deacoudre.getItemMeta();
                    deacoudreM.setDisplayName("§6§lDé À Coudre");
                    ArrayList<String> loredeacoudre = new ArrayList<String>();
                    loredeacoudre.add("");
                    loredeacoudre.add(" §aDescription:");
                    loredeacoudre.add(" §f▶ §7Dans ce jeu, lancez le dé à");
                    loredeacoudre.add(" §f  §7coudre et tentez de marquer");
                    loredeacoudre.add(" §f  §7le plus de points possible !");
                    loredeacoudre.add("");
                    loredeacoudre.add(" §dInformations:");
                    loredeacoudre.add(" §f▶ §7Joueur(s): §e" + OnlinesForGroups.getOnlinesFromGroup("deacoudre"));
                    loredeacoudre.add(" §f▶ §7File d'attente: ");
                    loredeacoudre.add("");
                    loredeacoudre.add("§8➡ §fCliquez pour y accéder.");
                    deacoudreM.setLore(loredeacoudre);
                    deacoudre.setItemMeta(deacoudreM);
                    inv.setItem(32, deacoudre);

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
