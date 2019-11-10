package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Course;
import model.User;
import mySQL_Connection.MySQL_utils;
import static queries.AssignmentQueries.getAllAssignments;

public class ScheduleQueries {

    public static void CreateOnSchedule(String date, Course course, User user) {

        Connection con = MySQL_utils.getConnection();

        String sql = "insert into schedule(Date,CourseID,TrainerID) values(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, date);
            ps.setInt(2, course.getId());
            ps.setInt(3, user.getId());
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Created");;
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1292) {
                System.out.println("Wrong date, try again (example data 2019-05-29)");
            } else {
                System.out.println(ex.getLocalizedMessage());
            }
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static void readSchedule() {
        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT s.id ,s.date,c.title, u.firstName, u.lastName\n"
                + "FROM school.schedule s\n"
                + "INNER JOIN Users u  ON  u.id = s.TrainerID\n"
                + "INNER JOIN Courses c ON c.id = s.CourseID\n"
                + ";";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(selectSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int scheduleId = rs.getInt(1);
                String date = rs.getString(2);
                String courseTitle = rs.getString(3);
                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                System.out.println("[Schedule id: " + scheduleId + "] Date: " + date + " Course:" + courseTitle + " Trainer:" + firstname + " " + lastname);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }

    }

    public static void updateSchedule(String date, Course course, User trainer, int id) {

        readSchedule();

        Connection con = MySQL_utils.getConnection();

        String sql = "UPDATE schedule SET Date = ?, CourseID = ? ,TrainerID = ? WHERE id=?";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, date);
            ps.setInt(2, course.getId());
            ps.setInt(3, trainer.getId());
            ps.setInt(4, id);
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Update was successful");
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1292) {
                System.out.println("Wrong date, try again (example data 2019-05-29)");
            } else {
                System.out.println(ex.getLocalizedMessage());
            }
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static List<Assignment> deleteSchedule(int id) {

        Connection con = MySQL_utils.getConnection();

        String sql = ("DELETE FROM Schedule WHERE id=?;");

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();

            if (i == 1) {
                System.out.println("Deleted");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ScheduleQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return getAllAssignments();
    }
}
