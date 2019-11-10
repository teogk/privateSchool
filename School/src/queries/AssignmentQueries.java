package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import mySQL_Connection.MySQL_utils;

public class AssignmentQueries {

    public static List<Assignment> getAllAssignments() {
        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT * from assignments;";
        PreparedStatement ps = null;
        List<Assignment> assignments = new ArrayList();

        try {
            ps = con.prepareStatement(selectSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String submissionDateAndTime = rs.getString(4);
                Assignment assignment = new Assignment(id, title, description, submissionDateAndTime);
                assignments.add(assignment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return assignments;
    }

    public static boolean insertAssignment(Assignment assignment) {

        Connection con = MySQL_utils.getConnection();

        String sql = "insert into assignments(title,description,submissionDateAndTime) values(?,?,?);";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, assignment.getTitle());
            ps.setString(2, assignment.getDescription());
            ps.setString(3, assignment.getSubmissionDateAndTime());

            int i = ps.executeUpdate();

            if (i == 1) {
                System.out.println("Assignment created");
            }

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1292) {
                System.out.println("Wrong date, try again (example data 2019-05-29 12:00:00)");
            } else {
                System.out.println(ex.getLocalizedMessage());
            }
        } finally {
            MySQL_utils.close(con);
        }
        return false;
    }

    public static void updateAssignment(Assignment assignment) {

        Connection con = MySQL_utils.getConnection();

        String sql = "UPDATE assignments SET title = ?, description = ? ,submissionDateAndTime = ? WHERE id=?;";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, assignment.getTitle());
            ps.setString(2, assignment.getDescription());
            ps.setString(3, assignment.getSubmissionDateAndTime());
            ps.setInt(4, assignment.getId());

            int i = ps.executeUpdate();

            if (i == 1) {
                System.out.println("Update was successful");
            }

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1292) {
                System.out.println("Wrong date, try again (example data 2019-05-29 12:00:00)");
            } else {
                System.out.println(ex.getLocalizedMessage());
            }
        } finally {
            MySQL_utils.close(con);
        }
        
    }

    public static List<Assignment> deleteAssignment(Assignment assignment) {

        Connection con = MySQL_utils.getConnection();

        String sql = ("DELETE FROM assignments WHERE id=?;");

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, assignment.getId());
            int i = ps.executeUpdate();

            if (i == 1) {
                System.out.println("Assignment deleted");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return getAllAssignments();
    }

}
