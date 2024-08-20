package fr.edminecoreteam.corelobby.utils;

import fr.edminecoreteam.corelobby.manager.edorm.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerList
{
    public static List<String> getServer(String serverType)
    {
        try
        {
            String response;
            List<String> serverList = new ArrayList<String>();
            for(int i = 8; i > 0; i--)
            {
                if (i == 0) { return null; }
                int ServerNumber = i;
                response = serverType + ServerNumber;
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT server_name FROM ed_servers WHERE server_status = '1'");
                preparedStatement.setString(1, response);
                ResultSet rs = preparedStatement.executeQuery();
                String result = "";
                while (rs.next())
                {
                    result = rs.getString("server_name");
                    serverList.add(result);
                }
                preparedStatement.close();
            }
            return serverList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getAllServers()
    {
        try
        {
            List<String> serverList = new ArrayList<String>();
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT server_name FROM ed_servers");
            //preparedStatement.setString(1, response);
            ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next())
            {
                result = rs.getString("server_name");
                serverList.add(result);
            }
            preparedStatement.close();

            return serverList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
