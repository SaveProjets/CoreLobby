package fr.edminecoreteam.corelobby.utils;

import fr.edminecoreteam.corelobby.manager.edorm.MySQL;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PingServers
{

    public static boolean getServerStatut(String IPAdress, int port)
    {
        boolean online = false;
        try
        {
            Socket s = new Socket(IPAdress, port);
            // ONLINE
            s.close();
            online = true;
            return online;
        }
        catch (UnknownHostException ex)
        {
            ex.toString();
        }
        catch (IOException et)
        {
            et.toString();
        }
        return online;
    }


    public static List<String> getList()
    {
        return getGroupList();
    }

    public static List<String> getGroupList()
    {
        List<String> list = new ArrayList<String>();

        //Ajouter les groupes ici
        list.add("Lobby");
        list.add("Login");

        return list;

    }

    public int getServerPerGroup()
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT server_id FROM ed_servers WHERE server_name = ?");
            preparedStatement.setString(1, "ProxyNetwork");
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                result = rs.getInt("server_id");
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
}
