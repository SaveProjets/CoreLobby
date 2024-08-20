package fr.edminecoreteam.corelobby.user.profile.settings;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


public class SettingInfo
{
    private Map<Player, SettingInfo> settingInfo;
    private Player p;

    SettingData settingData;

    public SettingInfo(Player p) {
        this.p = p;
        this.settingInfo = new HashMap<Player, SettingInfo>();
        this.settingData = new SettingData(p);
        this.settingInfo.put(p, this);
    }

    public Player getPlayer() { return p; }

    public void createSetting() { settingData.createSetting(); }
    public boolean hasSetting() { return settingData.hasSetting(); }


    public int getLang() { return settingData.getLang(); }
    public String getFriendRequest() { return settingData.getFriendRequest(); }
    public String getGroupRequest() { return settingData.getGroupRequest(); }
    public String getGuildRequest() { return settingData.getGuildRequest(); }
    public String getPrivateMessage() { return settingData.getPrivateMessage(); }
    public String getProfilView() { return settingData.getProfilView(); }
    public String getPlayersDisplay() { return settingData.getPlayersDisplay(); }
    public String getAccountState() { return settingData.getAccountState(); }
    public String getChatMention() { return settingData.getChatMention(); }
    public String getGroupFollow() { return settingData.getGroupFollow(); }
    public String getLiveNotification() { return settingData.getLiveNotification(); }
    public String getMessageConnection() { return settingData.getMessageConnection(); }
    public String getNightOrDay() { return settingData.getNightOrDay(); }
    public String getAutoTip() { return settingData.getAutoTip(); }
    public String getLobbyProtection() { return settingData.getLobbyProtection(); }
    public String getGuildChat() { return settingData.getGuildChat(); }
    public String getGuildNotification() { return settingData.getGuildNotification(); }

    public void updateLang()  { settingData.updateLang(); }
    public void updateFriendRequest() { settingData.updateFriendRequest(); }
    public void updateGroupRequest() { settingData.updateGroupRequest(); }
    public void updateGuildRequest() { settingData.updateGuildRequest(); }
    public void updatePrivateMessage() { settingData.updatePrivateMessage(); }
    public void updateProfilView() { settingData.updateProfilView(); }
    public void updatePlayersDisplay() { settingData.updatePlayersDisplay(); }
    public void updateAccountState() { settingData.updateAccountState(); }
    public void updateChatMention() { settingData.updateChatMention(); }
    public void updateGroupFollow() { settingData.updateGroupFollow(); }
    public void updateLiveNotification() { settingData.updateLiveNotification(); }
    public void updateMessageConnection() { settingData.updateMessageConnection(); }
    public void updateNightOrDay() { settingData.updateNightOrDay(); }
    public void updateAutoTip() { settingData.updateAutoTip(); }
    public void updateLobbyProtection() { settingData.updateLobbyProtection(); }
    public void updateGuildChat() { settingData.updateGuildChat(); }
    public void updateGuildNotification() { settingData.updateGuildNotification(); }




}
