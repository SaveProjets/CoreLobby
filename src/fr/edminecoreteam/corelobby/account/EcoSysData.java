package fr.edminecoreteam.corelobby.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import fr.edminecoreteam.corelobby.edorm.MySQL;

public class EcoSysData
{
    private Player p;

    public EcoSysData(Player p)
    {
        this.p = p;
    }

    public void addFragmentsDames(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_fragments_d_ames = player_fragments_d_ames + ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removeFragmentsDames(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_fragments_d_ames = player_fragments_d_ames - ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setFragmentsDames(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_fragments_d_ames = ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Float getFragmentsDames()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_fragments_d_ames FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            ResultSet rs = preparedStatement.executeQuery();
            float coins = 0.0f;
            while (rs.next())
            {
                coins = rs.getFloat("player_fragments_d_ames");
            }
            preparedStatement.close();
            return coins;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0.0f;
        }
    }


    public void addEclatsDivins(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_eclats_divins = player_eclats_divins + ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removeEclatsDivins(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_eclats_divins = player_eclats_divins - ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setEclatsDivins(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_eclats_divins = ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Float getEclatsDivins()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_eclats_divins FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            ResultSet rs = preparedStatement.executeQuery();
            float coins = 0.0f;
            while (rs.next())
            {
                coins = rs.getFloat("player_eclats_divins");
            }
            preparedStatement.close();
            return coins;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0.0f;
        }
    }


    public void addArgent(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_argent = player_argent + ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removeArgent(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_argent = player_argent - ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setArgent(float amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_argent = ? WHERE player_name = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, p.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Float getArgent()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_argent FROM ed_accounts WHERE player_name = ?");
            preparedStatement.setString(1, p.getName());
            ResultSet rs = preparedStatement.executeQuery();
            float coins = 0.0f;
            while (rs.next())
            {
                coins = rs.getFloat("player_argent");
            }
            preparedStatement.close();
            return coins;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0.0f;
        }
    }
}
