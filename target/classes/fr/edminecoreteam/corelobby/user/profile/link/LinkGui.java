package fr.edminecoreteam.corelobby.user.profile.link;

import fr.edminecoreteam.corelobby.Core;
import fr.edminecoreteam.corelobby.user.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.user.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.user.profile.friends.FriendInfo;
import fr.edminecoreteam.corelobby.user.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.user.profile.settings.SettingInfo;
import fr.edminecoreteam.corelobby.utils.SkullNBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class LinkGui implements Listener
{
    private static Core api = Core.getInstance();
    private static ItemStack getSkull(String url) { return SkullNBT.getSkull(url); }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();
        if (it == null) {  return; }
        if (inv.getName().equalsIgnoreCase("§8Profil (Réseaux Sociaux)"))
        {
            e.setCancelled(true);
            if (it.getType() == Material.SKULL_ITEM || it.getType() == Material.DIODE || it.getType() == Material.ENDER_PEARL)
            {
                if(it.getItemMeta().getDisplayName() == "§c§lProfil")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    ProfileGUI.gui(p);
                }
                if(it.getItemMeta().getDisplayName() == "§d§lAmis §c❤")
                {
                    FriendInfo friendInfo = new FriendInfo(p.getName());
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    FriendGui.gui(p, 1, friendInfo.getFriendPageNumber());
                }
                if(it.getItemMeta().getDisplayName() == "§9§lGroupes")
                {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                    GroupGui.gui(p);
                }
            }
        }
    }

    public static void gui(Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "§8Profil (Réseaux Sociaux)");
        p.openInventory(inv);
        LinkData linkData = new LinkData(p.getUniqueId().toString());
        FriendInfo friendInfo = new FriendInfo(p.getName());
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

                    if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Profil (Réseaux Sociaux)")) { cancel(); }

                    ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1);
                    ItemMeta decoM = deco.getItemMeta();
                    decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    decoM.setDisplayName("§r");
                    deco.setItemMeta(decoM);
                    inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
                    inv.setItem(45, deco); inv.setItem(53, deco); inv.setItem(36, deco); inv.setItem(44, deco);


                    ItemStack profil = getSkull("http://textures.minecraft.net/texture/299c799b38ab1999c354332a74b3a49687012738225682d58159be2b8a2b");
                    ItemMeta profilM = profil.getItemMeta();
                    profilM.setDisplayName("§c§lProfil");
                    ArrayList<String> loreprofil = new ArrayList<String>();
                    loreprofil.add("");
                    loreprofil.add(" §aDescription:");
                    loreprofil.add(" §f▶ §7Profil de §a" + p.getName());
                    loreprofil.add("");
                    loreprofil.add("§8➡ §fCliquez pour y accéder.");
                    profilM.setLore(loreprofil);
                    profil.setItemMeta(profilM);
                    inv.setItem(18, profil);

                    ItemStack guild = getSkull("http://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077");
                    ItemMeta guildM = guild.getItemMeta();
                    guildM.setDisplayName("§b§lVotre Guild");
                    ArrayList<String> loreguild = new ArrayList<String>();
                    loreguild.add("");
                    loreguild.add(" §aDescription:");
                    loreguild.add(" §f▶ §7Affichez les informations");
                    loreguild.add(" §f  §7à propos de votre guild.");
                    loreguild.add("");
                    loreguild.add("§8➡ §fCliquez pour y accéder.");
                    guildM.setLore(loreguild);
                    guild.setItemMeta(guildM);
                    inv.setItem(26, guild);

                    ItemStack frienDprofil = getSkull("http://textures.minecraft.net/texture/8cc95d7622ec32c5cc31ff7a9e2d545f951179a6c78af837b4e756c1f75a61");
                    ItemMeta frienDprofilM = frienDprofil.getItemMeta();
                    frienDprofilM.setDisplayName("§d§lAmis §c❤");
                    ArrayList<String> lorefrienDprofil = new ArrayList<String>();
                    lorefrienDprofil.add("");
                    lorefrienDprofil.add(" §aDescription:");
                    lorefrienDprofil.add(" §f▶ §7Visionnez et gérez");
                    lorefrienDprofil.add(" §f  §7votre liste d'amis.");
                    lorefrienDprofil.add("");
                    lorefrienDprofil.add(" §f▶ §7Nombre d'amis: §6" + friendInfo.getFriendCount());
                    lorefrienDprofil.add("");
                    lorefrienDprofil.add("§8➡ §fCliquez pour y accéder.");
                    frienDprofilM.setLore(lorefrienDprofil);
                    frienDprofil.setItemMeta(frienDprofilM);
                    inv.setItem(35, frienDprofil);

                    ItemStack groupprofil = getSkull("http://textures.minecraft.net/texture/5593da74e9688413c237f3ce324d7085aca88dfa4b7257c2da0bdfc34563077");
                    ItemMeta groupprofilM = groupprofil.getItemMeta();
                    groupprofilM.setDisplayName("§9§lGroupes");
                    ArrayList<String> loregroupprofil = new ArrayList<String>();
                    loregroupprofil.add("");
                    loregroupprofil.add(" §aDescription:");
                    loregroupprofil.add(" §f▶ §7Créez un groupe et");
                    loregroupprofil.add(" §f  §7invitez-y vos amis afin");
                    loregroupprofil.add(" §f  §7de jouer ensemble sur le serveur.");
                    loregroupprofil.add("");
                    loregroupprofil.add("§8➡ §fCliquez pour y accéder.");
                    groupprofilM.setLore(loregroupprofil);
                    groupprofil.setItemMeta(groupprofilM);
                    inv.setItem(27, groupprofil);

                    ItemStack tiktok = getSkull("http://textures.minecraft.net/texture/a8e415b5c1eb45663da6fdae5798dd199b214d47676b392ca306f79293c97f84");
                    ItemMeta tiktokM = tiktok.getItemMeta();
                    tiktokM.setDisplayName("§f§lTikTok");
                    ArrayList<String> loretiktok = new ArrayList<String>();
                    loretiktok.add("");
                    loretiktok.add(" §aDescription:");
                    loretiktok.add(" §f▶ §7Utilisez simplement");
                    loretiktok.add(" §f  §7votre pseudo tiktok.");
                    loretiktok.add("");
                    loretiktok.add(" §dInformations:");
                    loretiktok.add(" §f▶ §7Actif: §e" + linkData.getTikTok());
                    loretiktok.add("");
                    loretiktok.add("§8➡ §fCliquez pour changer.");
                    tiktokM.setLore(loretiktok);
                    tiktok.setItemMeta(tiktokM);
                    inv.setItem(20, tiktok);

                    ItemStack youtube = getSkull("http://textures.minecraft.net/texture/3488545d57c9eed52c3e547e96c45dabbb7cf5f98d4c8fe61dc6f69aba0aef96");
                    ItemMeta youtubeM = youtube.getItemMeta();
                    youtubeM.setDisplayName("§c§lYoutube");
                    ArrayList<String> loreyoutube = new ArrayList<String>();
                    loreyoutube.add("");
                    loreyoutube.add(" §aDescription:");
                    loreyoutube.add(" §f▶ §7Si vous n'avez pas");
                    loreyoutube.add(" §f  §7d'url personnalisé,");
                    loreyoutube.add(" §f  §7utilisez ce type de lien.");
                    loreyoutube.add(" §f▶ §bUCMhv1zIvoNr6iyvFS5P4Kow");
                    loreyoutube.add("");
                    loreyoutube.add(" §dInformations:");
                    loreyoutube.add(" §f▶ §7Actif: §e" + linkData.getYoutube());
                    loreyoutube.add("");
                    loreyoutube.add("§8➡ §fCliquez pour changer.");
                    youtubeM.setLore(loreyoutube);
                    youtube.setItemMeta(youtubeM);
                    inv.setItem(21, youtube);

                    ItemStack kick = getSkull("http://textures.minecraft.net/texture/d42a4802b6b2deb49cfbb4b7e267e2f9ad45da24c73286f97bef91d21616496");
                    ItemMeta kickM = kick.getItemMeta();
                    kickM.setDisplayName("§a§lKick");
                    ArrayList<String> lorekick = new ArrayList<String>();
                    lorekick.add("");
                    lorekick.add(" §aDescription:");
                    lorekick.add(" §f▶ §7Utilisez simplement");
                    lorekick.add(" §f  §7votre pseudo kick.");
                    lorekick.add("");
                    lorekick.add(" §dInformations:");
                    lorekick.add(" §f▶ §7Actif: §e" + linkData.getKick());
                    lorekick.add("");
                    lorekick.add("§8➡ §fCliquez pour changer.");
                    kickM.setLore(lorekick);
                    kick.setItemMeta(kickM);
                    inv.setItem(22, kick);

                    ItemStack twitch = getSkull("http://textures.minecraft.net/texture/46be65f44cd21014c8cddd0158bf75227adcb1fd179f4c1acd158c88871a13f");
                    ItemMeta twitchM = twitch.getItemMeta();
                    twitchM.setDisplayName("§5§lTwitch");
                    ArrayList<String> loretwitch = new ArrayList<String>();
                    loretwitch.add("");
                    loretwitch.add(" §aDescription:");
                    loretwitch.add(" §f▶ §7Utilisez simplement");
                    loretwitch.add(" §f  §7votre pseudo twitch.");
                    loretwitch.add("");
                    loretwitch.add(" §dInformations:");
                    loretwitch.add(" §f▶ §7Actif: §e" + linkData.getTwitch());
                    loretwitch.add("");
                    loretwitch.add("§8➡ §fCliquez pour changer.");
                    twitchM.setLore(loretwitch);
                    twitch.setItemMeta(twitchM);
                    inv.setItem(23, twitch);

                    ItemStack spotify = getSkull("http://textures.minecraft.net/texture/fe792d38e4168d26de7783984cafffc60b48838d81f2296076fe14dedd6");
                    ItemMeta spotifyM = spotify.getItemMeta();
                    spotifyM.setDisplayName("§a§lSpotify");
                    ArrayList<String> lorespotify = new ArrayList<String>();
                    lorespotify.add("");
                    lorespotify.add(" §aDescription:");
                    lorespotify.add(" §f▶ §7Utilisez simplement");
                    lorespotify.add(" §f  §7votre pseudo spotify.");
                    lorespotify.add("");
                    lorespotify.add(" §dInformations:");
                    lorespotify.add(" §f▶ §7Actif: §e" + linkData.getSpotify());
                    lorespotify.add("");
                    lorespotify.add("§8➡ §fCliquez pour changer.");
                    spotifyM.setLore(lorespotify);
                    spotify.setItemMeta(spotifyM);
                    inv.setItem(24, spotify);

                    ItemStack instagram = getSkull("http://textures.minecraft.net/texture/ac88d6163fabe7c5e62450eb37a074e2e2c88611c998536dbd8429faa0819453");
                    ItemMeta instagramM = instagram.getItemMeta();
                    instagramM.setDisplayName("§d§lInstagram");
                    ArrayList<String> loreinstagram = new ArrayList<String>();
                    loreinstagram.add("");
                    loreinstagram.add(" §aDescription:");
                    loreinstagram.add(" §f▶ §7Utilisez simplement");
                    loreinstagram.add(" §f  §7votre pseudo instagram.");
                    loreinstagram.add("");
                    loreinstagram.add(" §dInformations:");
                    loreinstagram.add(" §f▶ §7Actif: §e" + linkData.getInstagram());
                    loreinstagram.add("");
                    loreinstagram.add("§8➡ §fCliquez pour changer.");
                    instagramM.setLore(loreinstagram);
                    instagram.setItemMeta(instagramM);
                    inv.setItem(29, instagram);

                    ItemStack twitter = getSkull("http://textures.minecraft.net/texture/6ad46a422ae59603fd889c25344ff67bc843af8ee518932c2e2ad07cdbf939b3");
                    ItemMeta twitterM = twitter.getItemMeta();
                    twitterM.setDisplayName("§b§lTwitter");
                    ArrayList<String> loretwitter = new ArrayList<String>();
                    loretwitter.add("");
                    loretwitter.add(" §aDescription:");
                    loretwitter.add(" §f▶ §7Utilisez simplement");
                    loretwitter.add(" §f  §7votre pseudo twitter.");
                    loretwitter.add("");
                    loretwitter.add(" §dInformations:");
                    loretwitter.add(" §f▶ §7Actif: §e" + linkData.getTwitter());
                    loretwitter.add("");
                    loretwitter.add("§8➡ §fCliquez pour changer.");
                    twitterM.setLore(loretwitter);
                    twitter.setItemMeta(twitterM);
                    inv.setItem(30, twitter);

                    ItemStack discord = getSkull("http://textures.minecraft.net/texture/739ee7154979b3f87735a1c8ac087814b7928d0576a2695ba01ed61631942045");
                    ItemMeta discordM = discord.getItemMeta();
                    discordM.setDisplayName("§9§lDiscord");
                    ArrayList<String> lorediscord = new ArrayList<String>();
                    lorediscord.add("");
                    lorediscord.add(" §aDescription:");
                    lorediscord.add(" §f▶ §7Utilisez simplement");
                    lorediscord.add(" §f  §7votre pseudo discord.");
                    lorediscord.add("");
                    lorediscord.add(" §dInformations:");
                    lorediscord.add(" §f▶ §7Actif: §e" + linkData.getDiscord());
                    lorediscord.add("");
                    lorediscord.add("§8➡ §fCliquez pour changer.");
                    discordM.setLore(lorediscord);
                    discord.setItemMeta(discordM);
                    inv.setItem(31, discord);

                    ItemStack snapchat = getSkull("http://textures.minecraft.net/texture/8735829ad0a878f960d794a61e3f1725ffd1a0d54f74cd94652cb69b24e3");
                    ItemMeta snapchatM = snapchat.getItemMeta();
                    snapchatM.setDisplayName("§e§lSnapChat");
                    ArrayList<String> loresnapchat = new ArrayList<String>();
                    loresnapchat.add("");
                    loresnapchat.add(" §aDescription:");
                    loresnapchat.add(" §f▶ §7Utilisez simplement");
                    loresnapchat.add(" §f  §7votre pseudo snap.");
                    loresnapchat.add("");
                    loresnapchat.add(" §dInformations:");
                    loresnapchat.add(" §f▶ §7Actif: §e" + linkData.getSnapchat());
                    loresnapchat.add("");
                    loresnapchat.add("§8➡ §fCliquez pour changer.");
                    snapchatM.setLore(loresnapchat);
                    snapchat.setItemMeta(snapchatM);
                    inv.setItem(32, snapchat);

                    ItemStack reddit = getSkull("http://textures.minecraft.net/texture/9e98ad3191f7023633abbfcab47fa035df1600fd3f8f6e9b14f8ad1017277111");
                    ItemMeta redditM = reddit.getItemMeta();
                    redditM.setDisplayName("§6§lReddit");
                    ArrayList<String> lorereddit = new ArrayList<String>();
                    lorereddit.add("");
                    lorereddit.add(" §aDescription:");
                    lorereddit.add(" §f▶ §7Utilisez simplement");
                    lorereddit.add(" §f  §7votre pseudo reddit.");
                    lorereddit.add("");
                    lorereddit.add(" §dInformations:");
                    lorereddit.add(" §f▶ §7Actif: §e" + linkData.getReddit());
                    lorereddit.add("");
                    lorereddit.add("§8➡ §fCliquez pour changer.");
                    redditM.setLore(lorereddit);
                    reddit.setItemMeta(redditM);
                    inv.setItem(33, reddit);

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
