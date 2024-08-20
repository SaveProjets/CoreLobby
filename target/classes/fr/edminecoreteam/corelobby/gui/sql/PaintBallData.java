package fr.edminecoreteam.corelobby.gui.sql;

import fr.edminecoreteam.corelobby.manager.edorm.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaintBallData {
    private String p;
    private String table;

    public PaintBallData(String p) {
        this.p = p;
        this.table = "ed_paintball";
    }

    public void createData()
    {
        if (!hasData())
        {
            try
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO " + table + " (player_name, player_played_games, player_victoires, player_defaites, player_kills, player_deaths, player_top_kill_streak) VALUES (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, p); /*player_name*/
                preparedStatement.setInt(2, 0); /*player_played_games*/
                preparedStatement.setInt(3, 0); /*player_victoires*/
                preparedStatement.setInt(4, 0); /*player_defaites*/
                preparedStatement.setInt(5, 0); /*player_kills*/
                preparedStatement.setInt(6, 0); /*player_deaths*/
                preparedStatement.setInt(7, 0); /*player_top_kill_streak*/
                preparedStatement.execute();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.toString();
            }
        }
    }

    public boolean hasData()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_victoires FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            e.toString();
            return false;
        }
    }

    public int getPlayedGames()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_played_games FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_played_games");
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

    public int getWin()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_victoires FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_victoires");
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

    public int getLose()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_defaites FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_defaites");
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

    public int getKills()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_kills FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_kills");
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

    public int getDeaths()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_deaths FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_deaths");
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

    public int getTopKillStreak()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_top_kill_streak FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("player_top_kill_streak");
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

    public void addPlayedGames(int amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_played_games = player_played_games + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addVictoires(int amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_victoires = player_victoires + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addDefaites(int amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_defaites = player_defaites + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addKills(int amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_kills = player_kills + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addDeaths(int amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_deaths = player_deaths + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setTopKillStreak(int amount)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_top_kill_streak = ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
