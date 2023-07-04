package fr.edminecoreteam.corelobby.profile.friends;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.edminecoreteam.corelobby.edorm.MySQL;

public class FriendData
{
    protected static String table;
    private String p;

    public FriendData(String p)
    {
        this.p = p;
    }

    static {
        FriendData.table = "ed_friends";
    }

    public List<String> getFriendList() {
        List<String> friendList = new ArrayList<String>();
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT friend_name FROM " + table + " WHERE player_name = ?");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                friendList.add(rs.getString("friend_name"));
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return friendList;
        }
        return friendList;
    }

    public int getFriendPageNumber() {
        int Page = 1;
        int PlayerOnPage = 0;
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT friend_name FROM " + table + " WHERE player_name = ?");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
            ps.close();
            return Page;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getFriendForPage(int Page) {
        int friendPerPage = 10;
        int friendOnPage = 0;
        int sqlPage = 1;
        List<String> friendList = new ArrayList<String>();
        List<String> friendPageList = new ArrayList<String>();
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT friend_name FROM " + table + " WHERE player_name = ?");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (sqlPage != Page)
                {
                    if (friendPerPage != friendOnPage)
                    {
                        friendList.add(rs.getString("friend_name"));
                        ++friendOnPage;
                    }
                    if(friendPerPage == friendOnPage)
                    {
                        friendOnPage = 0;
                        ++sqlPage;
                        friendList.add(rs.getString("friend_name"));
                        ++friendOnPage;
                    }
                }
                else if (sqlPage == Page)
                {
                    if (friendPerPage != friendOnPage)
                    {
                        friendPageList.add(rs.getString("friend_name"));
                        ++friendOnPage;
                    }
                    if(friendPerPage == friendOnPage)
                    {
                        friendPageList.add(rs.getString("friend_name"));
                        ++friendOnPage;
                    }
                }
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return friendPageList;
    }

    public List<String> getFriendRequest() {
        List<String> requestList = new ArrayList<String>();
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT request_friend FROM " + table + " WHERE request_name = ? AND status_demand = '2'");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                requestList.add(rs.getString("request_friend"));
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return requestList;
        }
        return requestList;
    }

    public int getFriendRequestPageNumber() {
        int Page = 1;
        int PlayerOnPage = 0;
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT request_friend FROM " + table + " WHERE request_name = ? AND status_demand = '2'");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
            ps.close();
            return Page;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getFriendRequestForPage(int Page) {
        int friendPerPage = 10;
        int friendOnPage = 0;
        int sqlPage = 1;
        List<String> friendList = new ArrayList<String>();
        List<String> friendPageList = new ArrayList<String>();
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT request_friend FROM " + table + " WHERE request_name = ? AND status_demand = '2'");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (sqlPage != Page)
                {
                    if (friendPerPage != friendOnPage)
                    {
                        friendList.add(rs.getString("request_friend"));
                        ++friendOnPage;
                    }
                    if(friendPerPage == friendOnPage)
                    {
                        friendOnPage = 0;
                        ++sqlPage;
                        friendList.add(rs.getString("request_friend"));
                        ++friendOnPage;
                    }
                }
                else if (sqlPage == Page)
                {
                    if (friendPerPage != friendOnPage)
                    {
                        friendPageList.add(rs.getString("request_friend"));
                        ++friendOnPage;
                    }
                    if(friendPerPage == friendOnPage)
                    {
                        friendPageList.add(rs.getString("request_friend"));
                        ++friendOnPage;
                    }
                }
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return friendPageList;
    }


    public List<String> getFavList(){
        List<String> favList = new ArrayList<String>();
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT friend_name FROM " + table + " WHERE player_name = ? AND isFavoris = 1");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                favList.add(rs.getString("friend_name"));
            }
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            return favList;
        }
        return favList;
    }

    public Integer getFriendCount() {
        Integer counter = 0;
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT friend_name FROM " + table + " WHERE player_name = ?");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ++counter;
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return counter;
        }
        return counter;
    }

    public Integer getFriendDemandCount() {
        Integer counter = 0;
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT request_friend FROM " + table + " WHERE request_name = ? AND status_demand = '2'");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ++counter;
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return counter;
        }
        return counter;
    }
}
