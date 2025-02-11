package fr.edminecoreteam.corelobby.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import fr.edminecoreteam.corelobby.edorm.MySQL;

public class AccountData {
    private Player p;
    private String pS;

    public AccountData(Player p) {
        this.p = p;
    }

    public AccountData(String pS) {
        UUIDInfo uuidInfo = new UUIDInfo(pS);

        this.pS = uuidInfo.getUUID();
    }


    public boolean hasaccount() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_id FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, p.getUniqueId().toString());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.toString();
            return false;
        }
    }

    public void updateUUIDAccount() {
        if (hasaccount()) {
            try {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE ed_accounts SET player_uuid = ? WHERE player_name = ?");
                preparedStatement.setString(1, p.getUniqueId().toString());
                preparedStatement.setString(2, p.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.toString();
            }
        }
    }

    public int getAccountID() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_id FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, p.getUniqueId().toString());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("player_id");
            }
            preparedStatement.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAccountSID() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_id FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, pS);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("player_id");
            }
            preparedStatement.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int isFavoris(String pUUID) {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT isFavoris FROM ed_friends WHERE uuid_player = ? AND uuid_friend = ?");
            preparedStatement.setString(1, pUUID);
            preparedStatement.setString(2, pS);
            int result = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result = rs.getInt("isFavoris");
            }
            preparedStatement.close();
            if (result == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String isOnline() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT isOnline FROM ed_login WHERE player_uuid = ?");
            preparedStatement.setString(1, pS);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("isOnline");
            }
            preparedStatement.close();
            if (id == 1) {
                return "§aEn-Ligne";
            } else {
                return "§cHors-Ligne";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getFirstConnection() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_first_connection FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, p.getUniqueId().toString());
            String response = "";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                response = rs.getString("player_first_connection");
            }
            preparedStatement.close();
            return response;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getTotalCosmetics() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_total_cosmetics FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, p.getUniqueId().toString());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("player_total_cosmetics");
            }
            preparedStatement.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTotalPlayedPartys() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_total_played_partys FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, p.getUniqueId().toString());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("player_total_played_partys");
            }
            preparedStatement.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getLevel() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_level FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, p.getUniqueId().toString());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("player_level");
            }
            preparedStatement.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getXpNeedToLevelUp() {
        try {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_xp_need_to_level_up FROM ed_accounts WHERE player_uuid = ?");
            preparedStatement.setString(1, p.getUniqueId().toString());
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("player_xp_need_to_level_up");
            }
            preparedStatement.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getStatusPlayer() {
        {
            String playerStatus = new String();
            try {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_account_state FROM ed_settings WHERE player_uuid = ?");
                preparedStatement.setString(1, pS);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    if (rs.getString("player_account_state").equalsIgnoreCase("En-Ligne")){playerStatus = "§aEn-Ligne";}
                    if (rs.getString("player_account_state").equalsIgnoreCase("Hors-Ligne")){playerStatus = "§cHors-Ligne";}
                    if (rs.getString("player_account_state").equalsIgnoreCase("Occupé")){playerStatus = "§5Occupé";}
                    if (rs.getString("player_account_state").equalsIgnoreCase("Inactif")){playerStatus = "§eInactif";}


                    //playerStatus = rs.getString("player_account_state");
                }
                preparedStatement.close();
                return playerStatus;
            } catch (SQLException e) {
                e.printStackTrace();
                return "§aEn-ligne";
            }
        }
    }
}
