package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.Course;
import mySQL_Connection.MySQL_utils;

public class AssignmentsPerCoursesQueries {

    public static boolean appointAssignmentToCourse(Course course, Assignment assignment) {

        Connection con = MySQL_utils.getConnection();
        String sql = "insert into courses_assignments(courseID,assignmentID) values(?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, course.getId());
            ps.setInt(2, assignment.getId());
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Appointment success");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return false;
    }

    public static void readAssignmentsPerCoursesQ() {

        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT ca.id,c.id, c.Title,c.Stream, a.id, a.Title, a.Description,a.submissionDateAndTime\n"
                + "FROM assignments a\n"
                + "INNER JOIN courses_assignments ca ON a.ID = ca.AssignmentID\n"
                + "INNER JOIN Courses c ON c.ID = ca.CourseID\n"
                + "ORDER BY c.ID\n"
                + ";";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(selectSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int caid = rs.getInt(1);
                int cId = rs.getInt(2);
                String cTitle = rs.getString(3);
                String cStream = rs.getString(4);
                int aId = rs.getInt(5);
                String at = rs.getString(6);
                String ad = rs.getString(7);
                String sdat = rs.getString(8);

                System.out.println("[Course_Assignment ID:" + caid + "]"
                        + "\n Course id:" + cId + ", Title:" + cTitle + ", Stream:" + cStream
                        + " \n  Assignment id:" + aId + ", Title:" + at + ", Description:" + ad + ", Submission Date and Time:" + sdat + "\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static void updateCourses_Assignments(int courseId, int assignmentId, int id) {

        Connection con = MySQL_utils.getConnection();

        String sql = "UPDATE courses_assignments SET  CourseID = ?, AssignmentID =?  WHERE ID = ?;";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, courseId);
            ps.setInt(2, assignmentId);
            ps.setInt(3, id);
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Update was successful");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static void deleteCourses_Assignments(int id) {

        Connection con = MySQL_utils.getConnection();

        String sql = ("DELETE FROM courses_assignments WHERE ID=? ;");

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
             ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Delete was successful");;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }
}
