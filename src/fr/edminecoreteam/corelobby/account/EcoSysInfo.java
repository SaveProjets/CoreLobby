package fr.edminecoreteam.corelobby.account;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class EcoSysInfo
{
    private Map<Player, EcoSysInfo> ecoSysInfo;
    private Player p;
    private EcoSysData ecoSysData;
    //accountInfo = new HashMap<Player, AccountInfo>();

    public EcoSysInfo(Player p) {
        this.p = p;
        this.ecoSysInfo = new HashMap<Player, EcoSysInfo>();
        this.ecoSysData = new EcoSysData(p);
        this.ecoSysInfo.put(p, this);
    }

    public EcoSysInfo getAccountInfos(Player player) { return ecoSysInfo.get(player); }

    public String getPlayerName() { return p.getName(); }

    public void addFragmentsDames(float amout) { ecoSysData.addFragmentsDames(amout); }
    public void removeFragmentsDames(float amout) { ecoSysData.removeFragmentsDames(amout); }
    public void setFragmentsDames(float amout) { ecoSysData.setFragmentsDames(amout); }
    public Float getFragmentsDames() { return ecoSysData.getFragmentsDames(); }

    public void addEclatsDivins(float amout) { ecoSysData.addEclatsDivins(amout); }
    public void removeEclatsDivins(float amout) { ecoSysData.removeEclatsDivins(amout); }
    public void setEclatsDivins(float amout) { ecoSysData.setEclatsDivins(amout); }
    public Float getEclatsDivins() { return ecoSysData.getEclatsDivins(); }

    public void addArgent(float amout) { ecoSysData.addArgent(amout); }
    public void removeArgent(float amout) { ecoSysData.removeArgent(amout); }
    public void setArgent(float amout) { ecoSysData.setArgent(amout); }
    public Float getArgent() { return ecoSysData.getArgent(); }

}
