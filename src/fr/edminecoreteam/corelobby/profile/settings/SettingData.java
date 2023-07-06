package fr.edminecoreteam.corelobby.profile.settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import fr.edminecoreteam.corelobby.edorm.MySQL;

public class SettingData
{
    private Player p;
    private String pS;

    public SettingData(Player p)
    {
        this.p = p;
    }

    public SettingData(String pS)
    {
        this.pS = pS;
    }

    public void createSetting()
    {
        if (!hasSetting())
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO ed_settings (player_name, player_uuid, player_lang, player_friend_sort, player_friend_request, player_group_request, player_guild_request, player_private_message, player_profil_view, player_players_display, player_account_state, player_chat_mention, player_group_follow, player_live_notification, player_message_connection, player_night_or_day, player_auto_tip, player_lobby_protection, player_guild_chat, player_guild_notification) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, p.getName()); /*player_name*/
                preparedStatement.setString(2, p.getUniqueId().toString()); /*player_name*/
                preparedStatement.setInt(3, 0); /*player_lang*/
                preparedStatement.setInt(4, 1);/*player_friend_sort*/
                preparedStatement.setString(5, "bas"); /*player_friend_request*/
                preparedStatement.setString(6, "bas"); /*player_group_request*/
                preparedStatement.setString(7, "bas"); /*player_guild_request*/
                preparedStatement.setString(8, "bas"); /*player_private_message*/
                preparedStatement.setString(9, "bas"); /*player_profil_view*/
                preparedStatement.setString(10, "bas"); /*player_players_display*/
                preparedStatement.setString(11, "En-Ligne"); /*player_account_state*/
                preparedStatement.setString(12, "bas"); /*player_chat_mention*/
                preparedStatement.setString(13, "activer"); /*player_group_follow*/
                preparedStatement.setString(14, "activer"); /*player_live_notification*/
                preparedStatement.setString(15, "aucun"); /*player_message_connection*/
                preparedStatement.setString(16, "desactiver"); /*player_night_or_day*/
                preparedStatement.setString(17, "desactiver"); /*player_auto_tip*/
                preparedStatement.setString(18, "desactiver"); /*player_lobby_protection*/
                preparedStatement.setString(19, "bas"); /*player_guild_chat*/
                preparedStatement.setString(20, "desactiver"); /*player_guild_notification*/
                preparedStatement.execute();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public boolean hasSetting()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_lang FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            e.toString();
            return false;
        }
    }

    public int getLang()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_lang FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getInt("player_lang");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public String getFriendRequest()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_friend_request FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_friend_request");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getGroupRequest()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_group_request FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_group_request");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getGuildRequest()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_guild_request FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_guild_request");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getPrivateMessage()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_private_message FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_private_message");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getProfilView()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_profil_view FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_profil_view");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getPlayersDisplay()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_players_display FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_players_display");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getAccountState()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_account_state FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_account_state");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getChatMention()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_chat_mention FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_chat_mention");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getGroupFollow()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_group_follow FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_group_follow");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getGroupFollowString()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_group_follow FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, pS);
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_group_follow");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getLiveNotification()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_live_notification FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_live_notification");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getMessageConnection()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_message_connection FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_message_connection");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getNightOrDay()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_night_or_day FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_night_or_day");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getAutoTip()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_auto_tip FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_auto_tip");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getLobbyProtection()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_lobby_protection FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_lobby_protection");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getGuildChat()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_guild_chat FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_guild_chat");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getGuildNotification()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_guild_notification FROM ed_settings WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String result = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getString("player_guild_notification");
            }
            preparedStatement.close();
            return result;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }


    public void updateLang()
    {
        if (getLang() == 0)
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_lang = ? WHERE player_name = ?");
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getLang() == 1)
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_lang = ? WHERE player_name = ?");
                preparedStatement.setInt(1, 2);
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getLang() == 2)
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_lang = ? WHERE player_name = ?");
                preparedStatement.setInt(1, 3);
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getLang() == 3)
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_lang = ? WHERE player_name = ?");
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateFriendRequest()
    {
        if (getFriendRequest().contains("bas"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_friend_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "haut");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getFriendRequest().contains("haut"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_friend_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "max");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getFriendRequest().contains("max"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_friend_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "bas");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateGroupRequest()
    {
        if (getGroupRequest().contains("bas"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_group_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "moyen");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGroupRequest().contains("moyen"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_group_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "haut");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGroupRequest().contains("haut"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_group_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "max");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGroupRequest().contains("max"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_group_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "bas");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateGuildRequest()
    {
        if (getGuildRequest().contains("bas"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_guild_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "moyen");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGuildRequest().contains("moyen"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_guild_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "haut");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGuildRequest().contains("haut"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_guild_request = ? WHERE player_name = ?");
                preparedStatement.setString(1, "bas");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updatePrivateMessage()
    {
        if (getPrivateMessage().contains("bas"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_private_message = ? WHERE player_name = ?");
                preparedStatement.setString(1, "moyen");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getPrivateMessage().contains("moyen"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_private_message = ? WHERE player_name = ?");
                preparedStatement.setString(1, "haut");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getPrivateMessage().contains("haut"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_private_message = ? WHERE player_name = ?");
                preparedStatement.setString(1, "max");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getPrivateMessage().contains("max"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_private_message = ? WHERE player_name = ?");
                preparedStatement.setString(1, "bas");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateProfilView()
    {
        if (getProfilView().contains("bas"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_profil_view = ? WHERE player_name = ?");
                preparedStatement.setString(1, "moyen");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getProfilView().contains("moyen"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_profil_view = ? WHERE player_name = ?");
                preparedStatement.setString(1, "haut");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getProfilView().contains("haut"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_profil_view = ? WHERE player_name = ?");
                preparedStatement.setString(1, "max");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getProfilView().contains("max"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_profil_view = ? WHERE player_name = ?");
                preparedStatement.setString(1, "bas");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updatePlayersDisplay()
    {
        if (getPlayersDisplay().contains("bas"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_players_display = ? WHERE player_name = ?");
                preparedStatement.setString(1, "moyen");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getPlayersDisplay().contains("moyen"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_players_display = ? WHERE player_name = ?");
                preparedStatement.setString(1, "haut");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getPlayersDisplay().contains("haut"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_players_display = ? WHERE player_name = ?");
                preparedStatement.setString(1, "max");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getPlayersDisplay().contains("max"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_players_display = ? WHERE player_name = ?");
                preparedStatement.setString(1, "bas");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateAccountState()
    {
        if (getAccountState().contains("En-Ligne"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_account_state = ? WHERE player_name = ?");
                preparedStatement.setString(1, "Inactif");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getAccountState().contains("Inactif"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_account_state = ? WHERE player_name = ?");
                preparedStatement.setString(1, "Occupé");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getAccountState().contains("Occupé"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_account_state = ? WHERE player_name = ?");
                preparedStatement.setString(1, "Hors-Ligne");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getAccountState().contains("Hors-Ligne"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_account_state = ? WHERE player_name = ?");
                preparedStatement.setString(1, "En-Ligne");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateChatMention()
    {
        if (getChatMention().contains("bas"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_chat_mention = ? WHERE player_name = ?");
                preparedStatement.setString(1, "moyen");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getChatMention().contains("moyen"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_chat_mention = ? WHERE player_name = ?");
                preparedStatement.setString(1, "haut");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getChatMention().contains("haut"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_chat_mention = ? WHERE player_name = ?");
                preparedStatement.setString(1, "max");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getChatMention().contains("max"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_chat_mention = ? WHERE player_name = ?");
                preparedStatement.setString(1, "bas");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateGroupFollow()
    {
        if (getGroupFollow().equalsIgnoreCase("activer"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_group_follow = ? WHERE player_name = ?");
                preparedStatement.setString(1, "desactiver");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGroupFollow().equalsIgnoreCase("desactiver"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_group_follow = ? WHERE player_name = ?");
                preparedStatement.setString(1, "activer");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateLiveNotification()
    {
        if (getLiveNotification().equalsIgnoreCase("activer"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_live_notification = ? WHERE player_name = ?");
                preparedStatement.setString(1, "desactiver");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getLiveNotification().equalsIgnoreCase("desactiver"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_live_notification = ? WHERE player_name = ?");
                preparedStatement.setString(1, "activer");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateMessageConnection()
    {
        if (getMessageConnection().equalsIgnoreCase("activer"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_message_connection = ? WHERE player_name = ?");
                preparedStatement.setString(1, "desactiver");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getMessageConnection().equalsIgnoreCase("desactiver"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_message_connection = ? WHERE player_name = ?");
                preparedStatement.setString(1, "activer");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateNightOrDay()
    {
        if (getNightOrDay().equalsIgnoreCase("activer"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_night_or_day = ? WHERE player_name = ?");
                preparedStatement.setString(1, "desactiver");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getNightOrDay().equalsIgnoreCase("desactiver"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_night_or_day = ? WHERE player_name = ?");
                preparedStatement.setString(1, "activer");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateAutoTip()
    {
        if (getAutoTip().equalsIgnoreCase("activer"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_auto_tip = ? WHERE player_name = ?");
                preparedStatement.setString(1, "desactiver");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getAutoTip().equalsIgnoreCase("desactiver"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_auto_tip = ? WHERE player_name = ?");
                preparedStatement.setString(1, "activer");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateLobbyProtection()
    {
        if (getLobbyProtection().equalsIgnoreCase("activer"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_lobby_protection = ? WHERE player_name = ?");
                preparedStatement.setString(1, "desactiver");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getLobbyProtection().equalsIgnoreCase("desactiver"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_lobby_protection = ? WHERE player_name = ?");
                preparedStatement.setString(1, "activer");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateGuildChat()
    {
        if (getGuildChat().contains("bas"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_guild_chat = ? WHERE player_name = ?");
                preparedStatement.setString(1, "moyen");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGuildChat().contains("moyen"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_guild_chat = ? WHERE player_name = ?");
                preparedStatement.setString(1, "max");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGuildChat().contains("max"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_guild_chat = ? WHERE player_name = ?");
                preparedStatement.setString(1, "bas");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public void updateGuildNotification()
    {
        if (getGuildNotification().equalsIgnoreCase("activer"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_guild_notification = ? WHERE player_name = ?");
                preparedStatement.setString(1, "desactiver");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
        else if (getGuildNotification().equalsIgnoreCase("desactiver"))
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_guild_notification = ? WHERE player_name = ?");
                preparedStatement.setString(1, "activer");
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

}
