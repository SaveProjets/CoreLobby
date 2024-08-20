package fr.edminecoreteam.corelobby.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


public class AccountInfo
{
    private Map<Player, AccountInfo> accountInfo;
    private Map<String, AccountInfo> accountSInfo;
    private Player p;
    private String pS;
    private AccountData accountData;
    private EcoSysData ecoSysData;

    //accountInfo = new HashMap<Player, AccountInfo>();

    public AccountInfo(Player p) {
        this.p = p;
        this.accountInfo = new HashMap<Player, AccountInfo>();
        this.accountData = new AccountData(p);
        this.ecoSysData = new EcoSysData(p);
        this.accountInfo.put(p, this);
    }

    public AccountInfo(String pS) {
        this.pS = pS;
        this.accountSInfo = new HashMap<String, AccountInfo>();
        this.accountData = new AccountData(pS);
        this.accountSInfo.put(pS, this);
    }

    public AccountInfo getAccountInfos(Player player) { return accountInfo.get(player); }

    public Player getPlayer() { return p; }
    public String getSPlayer() { return pS; }
    public int getAccountID() { return accountData.getAccountID(); }
    public int getAccountSID() { return accountData.getAccountSID(); }

    public String getPlayerName() { return p.getName(); }

    public boolean hasAccount() { return accountData.hasaccount(); }
    public void updateUUIDAccount() { accountData.updateUUIDAccount(); }

    public String isOnline() { if( accountData.isOnline() == "§cHors-Ligne" ){return accountData.isOnline();}else{return accountData.getStatusPlayer();}}
    public int isFavoris(String pUUID){ return accountData.isFavoris(pUUID);}

    public Float getFragmentsDames() { return ecoSysData.getFragmentsDames(); }

    public Float getEclatsDivins() { return ecoSysData.getEclatsDivins(); }

    public Float getArgent() { return ecoSysData.getArgent(); }

    public String getFirstConnection() { return accountData.getFirstConnection(); }

    public int getTotalCosmetics() { return accountData.getTotalCosmetics(); }

    public int getTotalPlayedPartys() { return accountData.getTotalPlayedPartys(); }

    public int getLevel() { return accountData.getLevel(); }

    public int getXpNeedToLevelUp() { return accountData.getXpNeedToLevelUp(); }

}
