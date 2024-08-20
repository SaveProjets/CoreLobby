package fr.edminecoreteam.corelobby.profile.link;

import org.bukkit.entity.Player;


public class LinkCommandMessage
{
    public static void getHelp(Player player)
    {
        player.sendMessage("");
        player.sendMessage(" §7» §a§lCentre d'aide §a(Link):");
        player.sendMessage("");
        player.sendMessage(" §7• §d/§flink help §8§l» §7Aide du ed-link.");
        player.sendMessage(" §7• §d/§flink list §8§l» §7Liste de vos réseaux.");
        player.sendMessage(" §7• §d/§flink §b[pseudo] §8§l» §7Réseaux d'un joueur.");
        player.sendMessage(" §7• §d/§flink set §d[réseau] §b[nom] §8§l» §7Définir un réseau social.");
        player.sendMessage(" §7• §d/§flink reset §d[réseau] §8§l» §7Supprimer un réseau social.");
        player.sendMessage("");
    }

    public static void getLinkList(Player player)
    {
        LinkData data = new LinkData(player.getUniqueId().toString());
        player.sendMessage("");
        player.sendMessage(" §7» §a§lListe de vos réseaux §a(Link):");
        player.sendMessage("");
        player.sendMessage(" §f• §7TikTok: §f" + data.getTikTok());
        player.sendMessage(" §f• §7Youtube: §c" + data.getYoutube());
        player.sendMessage(" §f• §7Kick: §a" + data.getKick());
        player.sendMessage(" §f• §7Twitch: §5" + data.getTwitch());
        player.sendMessage(" §f• §7Spotify: §a" + data.getSpotify());
        player.sendMessage(" §f• §7Instagram: §d" + data.getInstagram());
        player.sendMessage(" §f• §7Twitter: §b" + data.getTwitter());
        player.sendMessage(" §f• §7Discord: §9" + data.getDiscord());
        player.sendMessage(" §f• §7SnapChat: §e" + data.getSnapchat());
        player.sendMessage(" §f• §7Reddit: §6" + data.getReddit());
        player.sendMessage("");
    }
}
