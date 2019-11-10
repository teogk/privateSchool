package mySQL_Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL_utils {

    private static final String USERNAME = "root";
    private static final String PASS = "12345";
    private static final String MYSQLURL = "jdbc:mysql://localhost:3306/school?zeroDateTimeBehavior=CONVERT_TO_NULL"
            + "&useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC"
            + "&allowPublicKeyRetrieval=true"
            + "&useSSL=false";

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger("Something went wrong with connection closing with error: " + ex.getLocalizedMessage());
        }
    }

}
