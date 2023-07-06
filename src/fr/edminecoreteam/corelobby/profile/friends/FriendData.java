package fr.edminecoreteam.corelobby.profile.friends;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public int getFriendSort(){
        int friendSort = 1;
        try{
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT player_friend_sort FROM ed_settings WHERE player_name = ?");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                friendSort = rs.getInt("player_friend_sort");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return friendSort;
        }
        return friendSort;
    }

    public static void setFriendSort(String p, int newSort){
        try{
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE ed_settings SET player_friend_sort = ? WHERE player_name = ?");
            ps.setInt(1, newSort);
            ps.setString(2, p);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.toString();
        }
    }

    public List<String> getFriendForPage(int Page, int playerSort) {
        int friendPerPage = 10;
        int friendOnPage = 0;
        int sqlPage = 1;
        List<String> friendList = new ArrayList<String>();
        List<String> friendPageList = new ArrayList<String>();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT friend_name,isFavoris FROM " + table + " WHERE player_name = ? ORDER BY isFavoris DESC, friend_name ASC");
            if(playerSort == 1){
                ps = MySQL.getConnection().prepareStatement("SELECT friend_name FROM " + table + ", ed_login WHERE " + table + ".player_name = ? AND friend_name = ed_login.player_name ORDER BY isOnline DESC, friend_name ASC");
            }
            if (playerSort == 2){
                ps = MySQL.getConnection().prepareStatement("SELECT friend_name FROM " + table + " WHERE player_name = ? ORDER BY friend_name ASC");
            }
            if (playerSort == 3){
                ps = MySQL.getConnection().prepareStatement("SELECT friend_name FROM " + table + ", ed_login WHERE " + table + ".player_name = ? AND friend_name = ed_login.player_name ORDER BY isFavoris DESC, isOnline DESC, friend_name ASC");
            }
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

   /* public List<String> getFriendForPage(int Page) {
        int friendPerPage = 10;
        int friendOnPage = 0;
        int sqlPage = 1;
        HashMap<String, Integer> test = new HashMap<String, Integer>();
        HashMap<String, Integer> pastest = new HashMap<String, Integer>();
        List<String> friendList = new ArrayList<String>();
        List<String> friendPageList = new ArrayList<String>();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT friend_name, isFavoris FROM " + table + " WHERE player_name = ? ORDER BY friend_name ASC");
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (sqlPage != Page)
                {
                    if (friendPerPage != friendOnPage)
                    {
                        test.put(rs.getString("friend_name"), rs.getInt("isFavoris"));
                        //friendList.add(rs.getString("friend_name"));
                        ++friendOnPage;
                    }
                    if(friendPerPage == friendOnPage)
                    {
                        friendOnPage = 0;
                        ++sqlPage;
                        test.put(rs.getString("friend_name"), rs.getInt("isFavoris"));
                        ++friendOnPage;
                    }
                }
                else if (sqlPage == Page)
                {
                    if (friendPerPage != friendOnPage)
                    {
                        pastest.put(rs.getString("friend_name"), rs.getInt("isFavoris"));

                        //friendPageList.add(rs.getString("friend_name"));
                        ++friendOnPage;
                    }
                    if(friendPerPage == friendOnPage)
                    {
                        pastest.put(rs.getString("friend_name"), rs.getInt("isFavoris"));

                       // friendPageList.add(rs.getString("friend_name"));
                        ++friendOnPage;
                    }
                }
            }
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, Integer>> testList = new ArrayList<>(pastest.entrySet());
        Collections.sort(testList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                int valueComparison = entry1.getValue().compareTo(entry2.getValue());
                if(valueComparison != 0){
                    return valueComparison;
                }else{
                    return entry1.getKey().compareTo(entry2.getKey());
                }
            }
        });

        for (Map.Entry<String, Integer> entry : testList){
            friendPageList.add(entry.getKey());
        }

        return friendPageList;
    }*/
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
