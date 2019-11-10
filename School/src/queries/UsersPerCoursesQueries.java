package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.User;
import mySQL_Connection.MySQL_utils;

public class UsersPerCoursesQueries {

    public static void enrollUserToCourse(User user, Course course) {

        Connection con = MySQL_utils.getConnection();

        String sql = "insert into users_courses(userID,CourseID) values(?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setInt(2, course.getId());
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Enrollment success");;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static void readUsersPerCourses(int roleId) {

        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT sc.CourseID, c.Title, c.Stream, u.id, u.firstname ,u.lastname ,sc.id\n"
                + "FROM users u \n"
                + "INNER JOIN users_courses sc ON u.id = sc.userid\n"
                + "INNER JOIN courses c ON c.id = sc.courseid\n"
                + "where u.roleid=?\n"
                + "ORDER BY c.id,u.id\n"
                + ";";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String stream = rs.getString(3);
                int id2 = rs.getInt(4);
                String sf = rs.getString(5);
                String sl = rs.getString(6);
                int idsc = rs.getInt(7);
                System.out.println("[User_Course id:" + idsc + "]"
                        + " Course id:" + id + ", Title:" + title + ", Stream:" + stream + "User id:" + id2 + ", Firstname:" + sf + ", Lastname:" + sl + "\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static void updateEnrollToCourse(int userID, int courseID, int id) {

        Connection con = MySQL_utils.getConnection();

        String sql = "UPDATE users_courses SET UserID = ?, CourseID = ?  WHERE ID = ?;";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, userID);
            ps.setInt(2, courseID);
            ps.setInt(3, id);

            int i = ps.executeUpdate();

            if (i == 1) {
                System.out.println("Update was successful");;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }

    }

    public static void deleteEnrollToCourse(int id) {

        Connection con = MySQL_utils.getConnection();

        String sql = ("DELETE FROM users_courses WHERE ID=?");

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Delete was successful");;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

}
