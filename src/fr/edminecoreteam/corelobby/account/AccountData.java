package fr.edminecoreteam.corelobby.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import fr.edminecoreteam.corelobby.edorm.MySQL;

public class AccountData
{
    private Player p;
    private String pS;

    public AccountData(Player p)
    {
        this.p = p;
    }

    public AccountData(String pS)
    {
        this.pS = pS;
    }


    public boolean hasaccount()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_id FROM ed_accounts WHERE player_name = ?");
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

    public void updateUUIDAccount()
    {
        if (hasaccount())
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_uuid = ? WHERE player_name = ?");
                preparedStatement.setString(1, p.getUniqueId().toString().replaceAll("-", ""));
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

    public int getAccountID()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_id FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_id");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAccountSID()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_id FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, pS);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_id");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int isFavoris(String p){
        try{
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT isFavoris FROM ed_friends WHERE player_name = ? AND friend_name = ?");
            preparedStatement.setString(1, p);
            preparedStatement.setString(2, pS);
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result = rs.getInt("isFavoris");
            }
            preparedStatement.close();
            if(result == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String isOnline()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT isOnline FROM ed_login WHERE player_name = ?");
            preparedStatement.setString(1, pS);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("isOnline");
            }
            preparedStatement.close();
            if (id == 1)
            {
                return "§aEn-Ligne";
            }
            else
            {
                return "§cHors-Ligne";
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getFirstConnection()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_first_connection FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            String response = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                response = rs.getString("player_first_connection");
            }
            preparedStatement.close();
            return response;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public int getTotalCosmetics()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_total_cosmetics FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_total_cosmetics");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTotalPlayedPartys()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_total_played_partys FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_total_played_partys");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLevel()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_level FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_level");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int getXpNeedToLevelUp()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_xp_need_to_level_up FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_xp_need_to_level_up");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
