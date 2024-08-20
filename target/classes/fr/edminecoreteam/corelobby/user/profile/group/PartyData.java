package fr.edminecoreteam.corelobby.user.profile.group;

import fr.edminecoreteam.corelobby.manager.edorm.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PartyData
{
    protected static String table;
    protected static String ban;
    private String p;
    private String t;

    public PartyData(String p)
    {
        this.p = p;
    }
    public PartyData(String p, String t)
    {
        this.p = p;
        this.t = t;
    }

    static {
        PartyData.table = "ed_party_list";
        PartyData.ban = "ed_party_ban";
    }

    public void createGroup()
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO " + table + " (group_name, group_leader, group_player, group_status, group_player_limit, group_chat, group_tag, group_player_rank, group_player_is_ban) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, ThreadLocalRandom.current().nextInt(100000, 999999));
            ps.setString(2, p);
            ps.setString(3, p);
            ps.setInt(4, 1);
            ps.setInt(5, 15);
            ps.setString(6, "enable");
            ps.setString(7, "non défini");
            ps.setInt(8, 2);
            ps.setInt(9, 0);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean hasGroup()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_name FROM " + table + " WHERE group_player = ?");
            preparedStatement.setString(1, p);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void joinGroup(int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO " + table + " (group_name, group_player, group_player_rank, group_player_is_ban) VALUES (?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, p);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void banPlayerFromAGroup(int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO " + ban + " (group_name, group_player) VALUES (?, ?)");
            ps.setInt(1, id);
            ps.setString(2, p);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void unbanPlayerFromAGroup(int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM " + ban + " WHERE group_name = ? AND group_player = ?");
            ps.setInt(1, id);
            ps.setString(2, p);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isBan(int id)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_player FROM " + ban + " WHERE group_name = ? AND group_player = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, p);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void removeGroup(int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM " + table + " WHERE group_name = ?");
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removeBan(int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM " + ban + " WHERE group_name = ?");
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void leaveGroup()
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM " + table + " WHERE group_player = ?");
            ps.setString(1, p);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setRank(int rank, int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET group_player_rank = ? WHERE group_player = ? AND group_name = ?");
            ps.setInt(1, rank);
            ps.setString(2, p);
            ps.setInt(3, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setStatus(int status, int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET group_status = ? WHERE group_player = ? AND group_name = ?");
            ps.setInt(1, status);
            ps.setString(2, p);
            ps.setInt(3, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setChat(String status, int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET group_chat = ? WHERE group_player = ? AND group_name = ?");
            ps.setString(1, status);
            ps.setString(2, p);
            ps.setInt(3, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setGroupTag(String tag, int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET group_tag = ? WHERE group_player = ? AND group_name = ?");
            ps.setString(1, tag);
            ps.setString(2, p);
            ps.setInt(3, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setLimit(int limit, int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET group_player_limit = ? WHERE group_player = ? AND group_name = ?");
            ps.setInt(1, limit);
            ps.setString(2, p);
            ps.setInt(3, id);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int getRank(int id)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_player_rank FROM " + table + " WHERE group_player = ? AND group_name = ?");
            preparedStatement.setString(1, p);
            preparedStatement.setInt(2, id);
            ResultSet rs = preparedStatement.executeQuery();
            int value = 0;
            while (rs.next())
            {
                value = rs.getInt("group_player_rank");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }



    public void addPlayerLimit(int limit)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET group_player_limit = group_player_limit + ? WHERE group_player_limit = ? WHERE group_player = ? AND group_leader = ?");
            ps.setInt(1, limit);
            ps.setString(2, p);
            ps.setString(3, p);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removePlayerLimit(int rank)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET group_player_limit = group_player_limit - ? WHERE group_player_limit = ? WHERE group_player = ? AND group_leader = ?");
            ps.setInt(1, rank);
            ps.setString(2, p);
            ps.setString(3, p);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void promotePlayerToLeader(int id)
    {
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET group_player_rank = ? WHERE group_player = ? AND group_leader = ?");
            ps.setInt(1, id);
            ps.setString(2, p);
            ps.setString(3, t);
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int getGroupName()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_name FROM " + table + " WHERE group_player = ?");
            preparedStatement.setString(1, p);
            ResultSet rs = preparedStatement.executeQuery();
            int value = 0;
            while (rs.next())
            {
                value = rs.getInt("group_name");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLimit(int id)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_player_limit FROM " + table + " WHERE group_name = ? AND group_player_rank = '2'");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            int value = 0;
            while (rs.next())
            {
                value = rs.getInt("group_player_limit");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int getGroupStatus()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_status FROM " + table + " WHERE group_player = ? AND group_player_rank = '2'");
            preparedStatement.setString(1, p);
            ResultSet rs = preparedStatement.executeQuery();
            int value = 0;
            while (rs.next())
            {
                value = rs.getInt("group_status");
            }
            preparedStatement.close();
            return value;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public List<String> getGroupPlayers(int id)
    {
        List<String> friendList = new ArrayList<String>();
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT group_player FROM " + table + " WHERE group_name = ? AND group_player_rank = '0'");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                friendList.add(rs.getString("group_player"));
            }
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return friendList;
        }
        return friendList;
    }

    public List<String> getGroupMods(int id)
    {
        List<String> friendList = new ArrayList<String>();
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT group_player FROM " + table + " WHERE group_name = ? AND group_player_rank = '1'");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                friendList.add(rs.getString("group_player"));
            }
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return friendList;
        }
        return friendList;
    }

    public List<String> getGroupLeader(int id)
    {
        List<String> friendList = new ArrayList<String>();
        try
        {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT group_player FROM " + table + " WHERE group_name = ? AND group_player_rank = '2'");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                friendList.add(rs.getString("group_player"));
            }
            ps.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return friendList;
        }
        return friendList;
    }

    public List<String> getGroupList(int id) {
        List<String> friendList = new ArrayList<String>();
        for (String s : getGroupLeader(id))
        {
            friendList.add(s);
        }
        for (String s : getGroupMods(id))
        {
            friendList.add(s);
        }
        for (String s : getGroupPlayers(id))
        {
            friendList.add(s);
        }
        return friendList;
    }

    public int getGroupPageNumber(int id) {
        int Page = 1;
        int PlayerOnPage = 0;

        for (@SuppressWarnings("unused") String s : getGroupList(id))
        {
            if (PlayerOnPage == 10)
            {
                PlayerOnPage = 0;
                ++Page;
            }
            else
            {
                ++PlayerOnPage;
            }
        }
        return Page;
    }

    public List<String> getGroupForPage(int Page, int id) {
        int friendPerPage = 10;
        int friendOnPage = 0;
        int sqlPage = 1;
        List<String> friendList = new ArrayList<String>();
        List<String> friendPageList = new ArrayList<String>();

        for (String s : getGroupList(id))
        {
            if (sqlPage != Page)
            {
                if (friendPerPage != friendOnPage)
                {
                    friendList.add(s);
                    ++friendOnPage;
                }
                if(friendPerPage == friendOnPage)
                {
                    friendOnPage = 0;
                    ++sqlPage;
                    friendList.add(s);
                    ++friendOnPage;
                }
            }
            else if (sqlPage == Page)
            {
                if (friendPerPage != friendOnPage)
                {
                    friendPageList.add(s);
                    ++friendOnPage;
                }
                if(friendPerPage == friendOnPage)
                {
                    friendPageList.add(s);
                    ++friendOnPage;
                }
            }
        }
        return friendPageList;
    }

    public String getChat(int id, String leader)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_chat FROM " + table + " WHERE group_name = ? AND group_leader = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, leader);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("group_chat");
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

    public String getGroupTag(int id, String leader)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_tag FROM " + table + " WHERE group_name = ? AND group_leader = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, leader);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("group_tag");
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

    public String getLeader(int id)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT group_leader FROM " + table + " WHERE group_name = ? AND group_player_rank = '2'");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            String value = "";
            while (rs.next())
            {
                value = rs.getString("group_leader");
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
