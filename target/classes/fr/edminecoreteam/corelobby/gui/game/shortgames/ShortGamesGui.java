package fr.edminecoreteam.corelobby.gui.game.shortgames;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.gui.OnlinesForGroups;
import fr.edminecoreteam.corelobby.manager.gui.Gui;
import fr.edminecoreteam.corelobby.manager.gui.GuiButton;
import fr.edminecoreteam.corelobby.manager.gui.GuiItemBuilder;
import fr.edminecoreteam.corelobby.user.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
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

import java.util.ArrayList;
import java.util.stream.IntStream;

public class ShortGamesGui extends Gui<Core> {

    private final Player player;

    public ShortGamesGui(final Core core, final Player player) {
        super(core, "§8Menu Principal", 6);
        this.player = player;
    }

    @Override
    public void setup() {
        setItems(IntStream.of(0, 8, 9, 17, 44, 45, 53).toArray(), new GuiButton(new GuiItemBuilder(Material.STAINED_GLASS_PANE).setData(6).setFlags(ItemFlag.HIDE_ENCHANTS).setName(" ").build()));

        setItem(18, new GuiButton(
                new GuiItemBuilder(getSkull("http://textures.minecraft.net/texture/ddcd8fe8d4d5c05886db9f59127961549e602f30c51759998131b2ad25d264"))
                        .setName("§b§lMenu Principal")
                        .setLore(" ",
                                " §aDescription:",
                                " §f▶ §7Section principal.",
                                " ",
                                "§8➡ §fCliquez pour y accéder.").build()));

        setItem(27, new GuiButton(
                new GuiItemBuilder(getSkull("http://textures.minecraft.net/texture/c2eb28cd6c7524b14d59f3b8c465dfc78be8e0dac843e682c4252ae9459c78e4"))
                        .setName("§d§lServeur Customisé")
                        .setLore(" ",
                                " §aDescription:",
                                " §f▶ §7Ici, libre a votre imagination de",
                                " §f  §7créez ou jouez sur des parties custom.",
                                " ",
                                "§8➡ §fCliquez pour y accéder.").build()));

        setItem(26, new GuiButton(
                new GuiItemBuilder(getSkull("http://textures.minecraft.net/texture/633c89a3c529d5136be6c49a62be0383fc3722cc990142e5cb3cc96db199d7d"))
                        .setName("§f§lFonctions VIP")
                        .setLore(" ",
                                " §aDescription:",
                                " §f▶ §7...",
                                " ",
                                "§8➡ §fCliquez pour y accéder.").build()));

        setItem(35, new GuiButton(
                new GuiItemBuilder(getSkull("http://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b"))
                        .setName("§9§lParamètres")
                        .setLore(" ",
                                " §aDescription:",
                                " §f▶ §7Modifiez à votre guise les",
                                " §f  §7différents réglages disponibles.",
                                " ",
                                "§8➡ §fCliquez pour y accéder.").build()));

        setItem(30, new GuiButton(
                new GuiItemBuilder(Material.SNOW_BALL)
                        .setName("§e§lPaintBall")
                        .setLore(" ",
                                " §aDescription:",
                                " §f▶ §7Dans ce jeu, découvrez",
                                " §f  §7une bataille de boules de",
                                " §f  §7neige unique. Devenez le",
                                " §f  §7meilleur lanceur pour",
                                " §f  §7remporter la partie !",
                                " ",
                                " §dInformations:",
                                " §f▶ §7Joueur(s): §e" + OnlinesForGroups.getOnlinesFromGroup("paintball2vs4"),
                                " §f▶ §7File d'attente: ",
                                " ",
                                "§8➡ §fCliquez pour y accéder.").build()));

        setItem(32, new GuiButton(
                new GuiItemBuilder(Material.WATER_BUCKET)
                        .setName("§6§lDé À Coudre")
                        .setLore("",
                                " §aDescription:",
                                " §f▶ §7Dans ce jeu, lancez le dé à",
                                " §f  §7coudre et tentez de marquer",
                                " §f  §7le plus de points possible !",
                                " ",
                                " §dInformations:",
                                " §f▶ §7Joueur(s): §e" + OnlinesForGroups.getOnlinesFromGroup("deacoudre"),
                                " §f▶ §7File d'attente: ",
                                " ",
                                "§8➡ §fCliquez pour y accéder.").build(), event -> {

        }));
    }

    private static ItemStack getSkull(String url) {return SkullNBT.getSkull(url);}
}