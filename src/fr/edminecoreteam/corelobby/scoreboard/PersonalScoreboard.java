package fr.edminecoreteam.corelobby.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.Bukkit;
import java.util.UUID;
import org.bukkit.entity.Player;

import fr.edminecoreteam.corelobby.account.AccountInfo;
import fr.edminecoreteam.corelobby.changehub.ChangeHubInfo;
import fr.edminecoreteam.corelobby.profile.ranks.Rank;
import fr.edminecoreteam.corelobby.profile.ranks.RankInfo;
import fr.edminecoreteam.corelobby.profile.settings.SettingInfo;

public class PersonalScoreboard
{
    private Player player;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;

    PersonalScoreboard(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "Edmine");
        reloadData();
        objectiveSign.addReceiver((OfflinePlayer)player);
    }

    public void reloadData(){}

    public void setLines(String ip) {
        SettingInfo settingInfo = new SettingInfo(player);
        RankInfo rankInfo = new RankInfo(player);
        AccountInfo accountInfo = new AccountInfo(player);
        ChangeHubInfo serverInfo = new ChangeHubInfo(player.getServer().getServerName());
        ChangeHubInfo bungeeInfo = new ChangeHubInfo("ProxyNetwork");

        if (settingInfo.getLang() == 0) {
            objectiveSign.setDisplayName("§8• §6§lEDMINE§r §f§lNETWORK §8•");
            objectiveSign.setLine(0, "§1");
            objectiveSign.setLine(1, " §f➡ §d§lProfil");
            objectiveSign.setLine(2, "  §8• §7Compte: §f" + player.getName());
            if (rankInfo.getRankType().equalsIgnoreCase("staff") || rankInfo.getRankType().equalsIgnoreCase("module"))
            {
                objectiveSign.setLine(3, "  §8• §7Grade: " + Rank.powerToRank(rankInfo.getRankModule()).getDisplayName());
            }
            else
            {
                objectiveSign.setLine(3, "  §8• §7Grade: " + Rank.powerToRank(rankInfo.getRankID()).getDisplayName());
            }
            objectiveSign.setLine(4, "  §8• §7Fragments d'âmes: §b" + accountInfo.getFragmentsDames() + "§b✵");
            objectiveSign.setLine(5, "  §8• §7Eclats divins: §d" + accountInfo.getEclatsDivins() + "§d❁");
            objectiveSign.setLine(6, "  §8• §7Argent: §a" + accountInfo.getArgent() + "§a✪");
            objectiveSign.setLine(7, "§2");
            objectiveSign.setLine(8, " §f➡ §6§lServeurs");
            objectiveSign.setLine(9, "  §8• §7Lobby: §c#" + serverInfo.getServerName().replace("Lobby", ""));
            objectiveSign.setLine(10, "  §8• §7Connecté(s): §a" + bungeeInfo.getServerOnlines());
            objectiveSign.setLine(11, "§4");
            objectiveSign.setLine(12, " §8➡ " + ip);
            objectiveSign.updateLines();
        }
    }

    public void onLogout() {
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}
