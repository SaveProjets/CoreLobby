package fr.edminecoreteam.corelobby.listeners;

import fr.edminecoreteam.corelobby.user.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.user.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.user.profile.ranks.Rank;
import fr.edminecoreteam.corelobby.user.profile.ranks.RankInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerChatListener implements Listener
{
    public static List<Player> chatBoxAddFriend;
    public static List<Player> chatBoxInvitePlayerInGroup;
    public static List<Player> chatBoxJoinGroup;
    public static List<Player> createGroupTag;
    public static List<Player> sendChatGroup;
    public PlayerChatListener() {
        chatBoxAddFriend = new ArrayList<Player>();
        chatBoxInvitePlayerInGroup = new ArrayList<Player>();
        chatBoxJoinGroup = new ArrayList<Player>();
        sendChatGroup = new ArrayList<Player>();
        createGroupTag = new ArrayList<Player>();
    }

    public static List<Player> getChatBoxAddFriend() {
        return PlayerChatListener.chatBoxAddFriend;
    }

    public static List<Player> getChatBoxInvitePlayerInGroup() {
        return PlayerChatListener.chatBoxInvitePlayerInGroup;
    }

    public static List<Player> getChatBoxJoinGroup() {
        return PlayerChatListener.chatBoxJoinGroup;
    }

    public static List<Player> getSendChatGroup() {
        return PlayerChatListener.sendChatGroup;
    }

    public static List<Player> getCreateGroupTag() {
        return PlayerChatListener.createGroupTag;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        RankInfo rankInfo = new RankInfo(p);
        if (getCreateGroupTag().contains(p))
        {
            if (e.getMessage().toLowerCase().contains("cancel"))
            {
                e.setCancelled(true);
                getCreateGroupTag().remove(p);
                p.sendMessage("§cVous avez annulé votre changement de tag...");
                return;
            }
            else if (!e.getMessage().toLowerCase().contains("CANCEL"))
            {
                e.setCancelled(true);
                getCreateGroupTag().remove(p);
                GroupGui.confirmChangeGroupTag(p, e.getMessage());
                return;
            }
        }
        if (getChatBoxAddFriend().contains(p))
        {
            if (e.getMessage().toLowerCase().contains("cancel"))
            {
                e.setCancelled(true);
                getChatBoxAddFriend().remove(p);
                p.sendMessage("§cVous avez annulé votre recherche d'amis...");
                return;
            }
            else if (!e.getMessage().toLowerCase().contains("CANCEL"))
            {
                e.setCancelled(true);
                getChatBoxAddFriend().remove(p);
                FriendGui.confirmAddFriend(p, e.getMessage());
                return;
            }
        }
        if (getChatBoxInvitePlayerInGroup().contains(p))
        {
            if (e.getMessage().toLowerCase().contains("cancel"))
            {
                e.setCancelled(true);
                getChatBoxInvitePlayerInGroup().remove(p);
                p.sendMessage("§cVous avez annulé votre invitation...");
                return;
            }
            else if (!e.getMessage().toLowerCase().contains("CANCEL"))
            {
                e.setCancelled(true);
                getChatBoxInvitePlayerInGroup().remove(p);
                GroupGui.confirmSendInvite(p, e.getMessage());
                return;
            }
        }
        if (getChatBoxJoinGroup().contains(p))
        {
            if (e.getMessage().toLowerCase().contains("cancel"))
            {
                e.setCancelled(true);
                getChatBoxJoinGroup().remove(p);
                p.sendMessage("§cVous avez annulé votre recherche de groupe...");
                return;
            }
            else if (!e.getMessage().toLowerCase().contains("CANCEL"))
            {
                e.setCancelled(true);
                getChatBoxJoinGroup().remove(p);
                GroupGui.sendGroupMessage(p, e.getMessage());
                return;
            }
        }
        if (getSendChatGroup().contains(p))
        {
            if (e.getMessage().toLowerCase().contains("cancel"))
            {
                e.setCancelled(true);
                getSendChatGroup().remove(p);
                p.sendMessage("§cVous avez annulé votre envoi de message...");
                return;
            }
            else if (!e.getMessage().toLowerCase().contains("CANCEL"))
            {
                e.setCancelled(true);
                getSendChatGroup().remove(p);
                GroupGui.sendGroupMessage(p, e.getMessage());
                return;
            }
        }
        e.setCancelled(true);
        if (e.getMessage() != null) {

            if (rankInfo.getRankType().equalsIgnoreCase("static"))
            {
                if (rankInfo.getRankID() > 0)
                {
                    e.setFormat(Rank.powerToRank(rankInfo.getRankID()).getDisplayName() + p.getName() + " §8» §f" + e.getMessage().replace("&", "§"));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage(e.getFormat());
                    }
                }
                else if (rankInfo.getRankID() == 0)
                {
                    e.setFormat(Rank.powerToRank(rankInfo.getRankID()).getDisplayName() + p.getName() + " §8» §7" + e.getMessage());
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage(e.getFormat());
                    }
                }
            }
            if (rankInfo.getRankType().equalsIgnoreCase("tempo"))
            {
                if (rankInfo.getRankID() >= 3)
                {
                    e.setFormat(Rank.powerToRank(rankInfo.getRankID()).getDisplayName() + p.getName() + " §8» §f" + e.getMessage());
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage(e.getFormat());
                    }
                }
                else if (rankInfo.getRankID() < 3)
                {
                    e.setFormat(Rank.powerToRank(rankInfo.getRankID()).getDisplayName() + p.getName() + " §8» §7" + e.getMessage());
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage(e.getFormat());
                    }
                }
            }
            if (rankInfo.getRankType().equalsIgnoreCase("module"))
            {
                e.setFormat(Rank.powerToRank(rankInfo.getRankModule()).getDisplayName() + p.getName() + " §8» §f" + e.getMessage());
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(e.getFormat());
                }
            }
            if (rankInfo.getRankType().equalsIgnoreCase("staff"))
            {
                e.setFormat(Rank.powerToRank(rankInfo.getRankModule()).getDisplayName() + p.getName() + " §8» §f" + e.getMessage().replace("&", "§"));
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(e.getFormat());
                }
            }
        }
    }
}
