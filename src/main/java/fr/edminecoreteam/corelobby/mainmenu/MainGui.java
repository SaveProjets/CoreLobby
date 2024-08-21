package fr.edminecoreteam.corelobby.mainmenu;

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
import fr.edminecoreteam.corelobby.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;

public class MainGui implements Listener
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
                    ItemStack deco = new ItemBuilder(Material.STAINED_GLASS_PANE, 1 , (byte) 3)
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
                            .setSkullUrl("https://textures.minecraft.net/texture/47ad48f18cad99e06dfa4e59f7e486864f974daa985f6d6e657032b23eafca7")
                            .toItemStack();
                    inv.setItem(18, main);

                    ArrayList<String> lorehost = new ArrayList<String>();
                    lorehost.add("");
                    lorehost.add(" §aDescription:");
                    lorehost.add(" §f▶ §7Ici, libre à votre imagination de");
                    lorehost.add(" §f  §7créer ou jouer sur des parties custom.");
                    lorehost.add("");
                    lorehost.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack host = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§d§lServeur Customisé")
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

                    ArrayList<String> lorebackToHub = new ArrayList<String>();
                    lorebackToHub.add("");
                    lorebackToHub.add(" §aDescription:");
                    lorebackToHub.add(" §f▶ §7Revenez rapidement");
                    lorebackToHub.add(" §f▶ §7au point de départ.");
                    lorebackToHub.add("");
                    lorebackToHub.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack backToHub = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§aRetour vers le hub")
                            .setLore(lorebackToHub)
                            .setSkullUrl("https://textures.minecraft.net/texture/cf7cdeefc6d37fecab676c584bf620832aaac85375e9fcbff27372492d69f")
                            .toItemStack();
                    inv.setItem(24, backToHub);

                    ArrayList<String> lorevipZone = new ArrayList<String>();
                    lorevipZone.add("");
                    lorevipZone.add(" §aDescription:");
                    lorevipZone.add(" §f▶ §7Rendez-vous pour un aller");
                    lorevipZone.add(" §f▶ §7direct vers la zone VIP.");
                    lorevipZone.add("");
                    lorevipZone.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack vipZone = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§fZone §f§lVIP")
                            .setLore(lorevipZone)
                            .setSkullUrl("https://textures.minecraft.net/texture/74b662d3b529a187261cab86c6e56423bf876aa249d030faeaf34362f3447277")
                            .toItemStack();
                    inv.setItem(33, vipZone);

                    ArrayList<String> lorelongGame = new ArrayList<String>();
                    lorelongGame.add("");
                    lorelongGame.add(" §aDescription:");
                    lorelongGame.add(" §f▶ §7Une envie de Try-Hard?");
                    lorelongGame.add(" §f▶ §7Pas de problèmes ! Vous ne");
                    lorelongGame.add(" §f  §7manquerais de rien par ici...");
                    lorelongGame.add("");
                    lorelongGame.add(" §dDurée de jeu:");
                    lorelongGame.add(" §f▶ §7Estimation §8» §a20 à 45 min.");
                    lorelongGame.add("");
                    lorelongGame.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack longGame = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName("§7Jeux: §c§lLongs")
                            .setLore(lorelongGame)
                            .setSkullUrl("https://textures.minecraft.net/texture/b88fb6b2b3efa6ab299f9f4e7d8c1a1d7338753bdf8fef81075f2155943bc69")
                            .toItemStack();
                    inv.setItem(21, longGame);

                    ArrayList<String> loreotherGame = new ArrayList<String>();
                    loreotherGame.add("");
                    loreotherGame.add(" §aDescription:");
                    loreotherGame.add(" §f▶ §7Vous êtes plutôt survie");
                    loreotherGame.add(" §f  §7ou pvp ? Jouez a des jeux");
                    loreotherGame.add(" §f  §7tels que, du farms, du 1vs1");
                    loreotherGame.add(" §f  §7ou bien du ffa, bref amusez-vous !");
                    loreotherGame.add("");
                    loreotherGame.add(" §dDurée de jeu:");
                    loreotherGame.add(" §f▶ §7Estimation §8» §aindéterminée.");
                    loreotherGame.add("");
                    loreotherGame.add("§8➡ §fCliquez pour y accéder.");

                    ItemStack otherGame = new ItemBuilder(Material.DIAMOND_PICKAXE, 1)
                            .setName("§7Jeux: §6§lDivers")
                            .setLore(loreotherGame)
                            .toItemStack();
                    inv.setItem(29, otherGame);

                    ArrayList<String> loreshortGame = new ArrayList<String>();
                    loreshortGame.add("");
                    loreshortGame.add(" §aDescription:");
                    loreshortGame.add(" §f▶ §7Vous ne voulez pas vous");
                    loreshortGame.add(" §f  §7prendre la tête? ");
                    loreshortGame.add(" §f▶ §7Jouez rapidement sur les");
                    loreshortGame.add(" §f  §7mini-jeux de votre choix.");
                    loreshortGame.add("");
                    loreshortGame.add(" §dDurée de jeu:");
                    loreshortGame.add(" §f▶ §7Estimation §8» §a5 à 10 min.");
                    loreshortGame.add("");
                    loreshortGame.add("§8➡ §fCliquez pour y accéder.");

                    String shortGameName = "§7Jeux: §d§lCourts";

                    ItemStack shortGame0 = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName(shortGameName)
                            .setLore(loreshortGame)
                            .setSkullUrl("https://textures.minecraft.net/texture/26a4112df1e4bce2a5e28417f3aaff79cd66e885c3724554102cef8eb8")
                            .toItemStack();

                    ItemStack shortGame1 = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName(shortGameName)
                            .setLore(loreshortGame)
                            .setSkullUrl("https://textures.minecraft.net/texture/6de55a395a2246445b45f9a6d68872344bbea54f362d529fc5b0b857ea58326b")
                            .toItemStack();

                    ItemStack shortGame2 = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName(shortGameName)
                            .setLore(loreshortGame)
                            .setSkullUrl("https://textures.minecraft.net/texture/d9ec22818d1fbfc8167fbe36728b28240e34e16469a2929d03fdf511bf2ca1")
                            .toItemStack();

                    ItemStack shortGame3 = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName(shortGameName)
                            .setLore(loreshortGame)
                            .setSkullUrl("https://textures.minecraft.net/texture/839af477eb627815f723a5662556ec9dfcbab5d494d338bd214232f23e446")
                            .toItemStack();

                    ItemStack shortGame4 = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
                            .setName(shortGameName)
                            .setLore(loreshortGame)
                            .setSkullUrl("https://textures.minecraft.net/texture/26a4112df1e4bce2a5e28417f3aaff79cd66e885c3724554102cef8eb8")
                            .toItemStack();

                    if (t == 0)
                    {
                        inv.setItem(20, shortGame0);
                    }
                    if (t == 1)
                    {
                        inv.setItem(20, shortGame1);
                    }
                    if (t == 2)
                    {
                        inv.setItem(20, shortGame2);
                    }
                    if (t == 3)
                    {
                        inv.setItem(20, shortGame3);
                    }
                    if (t == 4)
                    {
                        inv.setItem(20, shortGame4);
                    }

                    ++t;
                    if (t == 5) {
                        t = 0;
                        run();
                    }
                }
            }.runTaskTimer((Plugin)api, 0L, 15L);

        }
    }
}
