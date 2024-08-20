package fr.edminecoreteam.corelobby.user.profile.link;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LinkCommand implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("link")) {
                if (args.length == 0)
                {
                    LinkCommandMessage.getHelp(p);
                }
                if (args.length == 1 && args[0].equalsIgnoreCase("help"))
                {
                    LinkCommandMessage.getHelp(p);
                }
                if (args.length == 1 && args[0].equalsIgnoreCase("list"))
                {
                    LinkCommandMessage.getLinkList(p);
                }
                if (args.length == 2)
                {
                    if (args[0].equalsIgnoreCase("reset"))
                    {
                        if (args[1].equalsIgnoreCase("tiktok"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setTikTok("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §f§lTikTok §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("youtube"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setYoutube("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §c§lYoutube §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("kick"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setKick("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §a§lKick §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("twitch"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setTwitch("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §5§lTwitch §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("spotify"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setSpotify("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §a§lSpotify §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("instagram"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setInstagram("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §d§lInstagram §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("twitter"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setTwitter("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §b§lTwitter §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("discord"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setDiscord("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §5§lDiscord §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("snapchat"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setSnapchat("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §e§lSnap-Chat §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("reddit"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            data.setReddit("non défini");
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §6§lReddit §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                    }
                    else
                    {
                        LinkCommandMessage.getHelp(p);
                    }
                }
                if (args.length == 3)
                {
                    if (args[0].equalsIgnoreCase("set"))
                    {
                        if (args[1].equalsIgnoreCase("tiktok"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setTikTok(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §f§lTikTok §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("youtube"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setYoutube(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §c§lYoutube §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("kick"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setKick(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §a§lKick §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("twitch"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setTwitch(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §5§lTwitch §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("spotify"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setSpotify(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §a§lSpotify §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("instagram"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setInstagram(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §d§lInstagram §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("twitter"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setTwitter(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §b§lTwitter §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("discord"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setDiscord(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §5§lDiscord §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("snapchat"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setSnapchat(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §e§lSnap-Chat §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                        if (args[1].equalsIgnoreCase("reddit"))
                        {
                            LinkData data = new LinkData(p.getUniqueId().toString());
                            String linkName = args[2];
                            data.setReddit(linkName);
                            p.sendMessage("§d§lED-Link §8§l» §aSuccès ! §fVotre compte §6§lReddit §fa été actualisé.");
                            p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
                        }
                    }
                }
            }
        }
        return false;
    }
}
