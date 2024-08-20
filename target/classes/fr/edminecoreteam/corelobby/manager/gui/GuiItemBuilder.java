package fr.edminecoreteam.corelobby.manager.gui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.*;

public class GuiItemBuilder {

    private final ItemStack item;

    public GuiItemBuilder(final ItemStack item) {
        this.item = item;
    }

    public GuiItemBuilder(final Material material) {
        this(new ItemStack(material));
    }

    public GuiItemBuilder(final String skullValue) {
        this(new ItemStack(Material.SKULL_ITEM,1, (byte) SkullType.PLAYER.ordinal()));
        final ItemMeta meta = item.getItemMeta();
        final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", skullValue));
        try {
            final Field field = meta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(meta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ignored) {
        }

        item.setItemMeta(meta);
    }

    public static GuiItemBuilder from(final ItemStack item) {
        return new GuiItemBuilder(item);
    }

    public GuiItemBuilder setAmount(final int amount) {
        item.setAmount(amount);
        return this;
    }

    public GuiItemBuilder setDurability(final int durability) {
        item.setDurability((short) durability);
        return this;
    }

    public GuiItemBuilder setData(final MaterialData data) {
        item.setData(data);
        return this;
    }

    public GuiItemBuilder setData(final int data) {
        item.setData(new MaterialData(data));
        return this;
    }

    public GuiItemBuilder setType(final Material type) {
        item.setType(type);
        return this;
    }

    public GuiItemBuilder setName(final String name) {
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public GuiItemBuilder setLore(final String... lore) {
        final ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }

    public GuiItemBuilder setLore(final List<String> lore) {
        final ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public boolean removeLoreLine(final int line) {
        final ItemMeta meta = item.getItemMeta();
        final List<String> lore = new ArrayList<>(meta.getLore());

        if (line < 0 || line > lore.size())
            return false;
        else {
            lore.remove(line);
            meta.setLore(lore);
            item.setItemMeta(meta);
            return true;
        }
    }

    public boolean removeLoreLine(final String line) {
        final ItemMeta meta = item.getItemMeta();
        final List<String> lore = new ArrayList<>(meta.getLore());

        if (!lore.contains(line))
            return true;
        else {
            lore.remove(line);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return true;
    }

    public GuiItemBuilder addLore(final String line) {
        final ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        if (meta.hasLore())
            lore = new ArrayList<>(meta.getLore());

        lore.add(line);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public GuiItemBuilder addLore(final String... lines) {
        final ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        if (meta.hasLore())
            lore = new ArrayList<>(meta.getLore());

        Collections.addAll(lore, lines);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public GuiItemBuilder addLore(final List<String> lines) {
        final ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        if (meta.hasLore())
            lore = new ArrayList<>(meta.getLore());

        lore.addAll(lines);

        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public boolean addLore(final String line, final int index) {
        final ItemMeta meta = item.getItemMeta();
        final List<String> lore = new ArrayList<>(meta.getLore());

        if (index < 0 || index > lore.size())
            return false;
        else {
            lore.set(index, line);
            meta.setLore(lore);
            item.setItemMeta(meta);
            return true;
        }
    }

    public GuiItemBuilder setFlags(final ItemFlag... flags) {
        final ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return this;
    }

    public GuiItemBuilder setEnchant(final Enchantment enchantment, final int level) {
        item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public GuiItemBuilder setGlowing(final boolean glowing) {
        final ItemMeta meta = item.getItemMeta();

        if (glowing) {
            item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            meta.removeEnchant(Enchantment.KNOCKBACK);
        }

        item.setItemMeta(meta);
        return this;
    }

    public GuiItemBuilder setUnbreakable(final boolean unbreakable) {
        final ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        item.setItemMeta(meta);
        return this;
    }

    public GuiItemBuilder setLeatherArmorColor(final Color color) {
        try {
            final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setColor(color);
            item.setItemMeta(meta);
        } catch (ClassCastException exception) {
            throw new ClassCastException("Non leather armor can't have a color");
        }
        return this;
    }

    public GuiItemBuilder setPotionEffect(final PotionEffectType effectType, final int duration, final int amplifier) {
        if (item.getType() != Material.POTION)
            throw new IllegalStateException("Non potion items can't have a effect");

        final PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        potionMeta.addCustomEffect(new PotionEffect(effectType, duration, amplifier), true);
        item.setItemMeta(potionMeta);
        return this;
    }

    public GuiItemBuilder setDyeColor(final DyeColor color) {
        item.setDurability(color.getData());
        return this;
    }

    public ItemStack build() {
        return item;
    }

}
