package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.PasswordHashing;
import model.User;
import mySQL_Connection.MySQL_utils;

public class UserQueries {

    private static User extractUserFromResultSet(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setRoleID(rs.getInt("roleId"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstname(rs.getString("firstname"));
            user.setLastname(rs.getString("lastname"));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//Login
    public static void login(String username, String password) {
        Connection con = MySQL_utils.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? LIMIT 1");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (PasswordHashing.checkPassword(password, rs.getString("password"))) {
                    User user = User.getInstance();
                    user.setId(rs.getInt("id"));
                    user.setRoleID(rs.getInt("roleId"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                } else {
                    System.out.println("Wrong password");
                }

            } else {
                System.out.println("User didn't find");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static User getUserByID(int id) {
        Connection con = MySQL_utils.getConnection();
        String selectSQL = ("SELECT * FROM users WHERE id=?");
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return null;
    }

    public static List<User> getUsersByRoleID(int roleID) {

        Connection con = MySQL_utils.getConnection();
        String selectSQL = ("SELECT * FROM users u \n"
                + "INNER JOIN roles r ON u.roleid = r.id\n"
                + "WHERE r.id =?");
        PreparedStatement ps = null;
        List<User> users = new ArrayList();

        try {
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, roleID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return users;
    }

    public static List<User> getAllUsers() {
        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT * from users";
        PreparedStatement ps = null;
        List<User> users = new ArrayList();

        try {
            ps = con.prepareStatement(selectSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return users;
    }

    public static boolean insertUser(User user) {
        Connection con = MySQL_utils.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?, ?, ?, ?)");
            ps.setInt(1, user.getRoleID());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstname());
            ps.setString(5, user.getLastname());
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Insert completed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return false;
    }

    public static boolean updateUser(User user) {
        Connection con = MySQL_utils.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE users SET roleid=?, username=?, password=?, firstname=?, lastname=? WHERE id=?");
            ps.setInt(1, user.getRoleID());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstname());
            ps.setString(5, user.getLastname());
            ps.setInt(6, user.getId());
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Update was successful");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return false;
    }

    public static boolean deleteUser(User user) {
        Connection con = MySQL_utils.getConnection();
        String sql = ("DELETE FROM users WHERE id=?");
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getId());
            int i = ps.executeUpdate();

            if (i == 1) {
                System.out.println(user.getFirstname() + " " + user.getLastname() + " deleted!");;
            } else {
                System.out.println("Doesn't exist!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return false;
    }

}
