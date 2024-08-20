package fr.edminecoreteam.corelobby.user.profile.link;

import fr.edminecoreteam.corelobby.manager.edorm.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkData
{
    protected static String table;
    private String uuid;

    public LinkData(String uuid) {
        this.uuid = uuid;
    }

    static {
        LinkData.table = "ed_accounts_link";
    }

    public void createLink()
    {
        if (!hasLink())
        {
            try
            {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO " + table + " (player_uuid, youtube, twitch, kick, instagram, twitter, snapchat, reddit, discord, tiktok, spotify) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, uuid);
                ps.setString(2, "non défini");
                ps.setString(3, "non défini");
                ps.setString(4, "non défini");
                ps.setString(5, "non défini");
                ps.setString(6, "non défini");
                ps.setString(7, "non défini");
                ps.setString(8, "non défini");
                ps.setString(9, "non défini");
                ps.setString(10, "non défini");
                ps.setString(11, "non défini");
                ps.execute();
                ps.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public boolean hasLink()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_uuid FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void setYoutube(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET youtube = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setTwitch(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET twitch = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setKick(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET kick = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setInstagram(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET instagram = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setTwitter(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET twitter = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setSnapchat(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET snapchat = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setReddit(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET reddit = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setDiscord(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET discord = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setTikTok(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET tiktok = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setSpotify(String value)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET spotify = ? WHERE player_uuid = ?");
            ps.setString(1, value);
            ps.setString(2, uuid);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public String getYoutube()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT youtube FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("youtube");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getTwitch()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT twitch FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("twitch");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getKick()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT kick FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("kick");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getInstagram()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT instagram FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("instagram");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getTwitter()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT twitter FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("twitter");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getSnapchat()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT snapchat FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("snapchat");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getReddit()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT reddit FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("reddit");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getDiscord()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT discord FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("discord");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getTikTok()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT tiktok FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("tiktok");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public String getSpotify()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT spotify FROM " + table + " WHERE player_uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("spotify");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }
}
