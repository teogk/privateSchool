package dao;

import java.sql.*;
import model.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;
import queries.AssignmentsPerCoursesQueries;
import mySQL_Connection.MySQL_utils;
import static util.Pick.*;

public class TrainerDaoImplementation implements TrainerDaoInterface {

    @Override
    public void viewTrainerCourses(boolean shouldPrint) {
        User user = User.getInstance();

        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT c.id,c.title,c.stream,c.type,c.startDate,c.endDate\n"
                + "FROM courses c\n"
                + "INNER JOIN users_courses uc ON c.id = uc.courseid\n"
                + "INNER JOIN users u ON u.id = uc.userid\n"
                + "where u.id =?\n"
                + "order by c.id\n"
                + ";";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int courseID = rs.getInt(1);
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                String startDate = rs.getString(5);
                String endDate = rs.getString(6);
                if (shouldPrint) {
                    System.out.println("Title:" + title + ", Stream:" + stream + ", Type:" + type + "Start Date:" + startDate + ", End Date:" + endDate);
                }
                Course course = new Course(courseID, title, stream, type, startDate, endDate);
                user.addCourse(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }

    }

    @Override
    public void viewStudentsPerCourses() {
        viewTrainerCourses(false); //Get courses.

        Course selectedCourse = pickCourse();

        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT u.FirstName, u.LastName, u.username\n"
                + "FROM users u\n"
                + "INNER JOIN users_courses uc ON u.id = uc.userid\n"
                + "INNER JOIN courses c ON c.id = uc.courseid\n"
                + "where u.roleid=3 and c.id=?\n"
                + ";";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, selectedCourse.getId());
            ResultSet rs = ps.executeQuery();

            int count = 1;
            while (rs.next()) {
                String firstName = rs.getString(1);
                String lastName = rs.getString(2);
                String username = rs.getString(3);
                System.out.println(count + " " + firstName + " " + lastName + " with username: " + username);
                count++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }

    }

    @Override
    public void viewAssignmentsPerStudentPerCourses() {
        viewTrainerCourses(false); //Retrieve courses and save them to user.

        Course selectedCourse = pickCourse();
        int studentId = pickStudentId(selectedCourse.getId());

        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT a.Title \n"
                + "FROM assignments a\n"
                + "INNER JOIN students_courses_assignments sca ON sca.assignmentid = a.id\n"
                + "where sca.studentid=? and sca.courseid = ?\n"
                + "ORDER BY a.ID\n"
                + ";";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, studentId);
            ps.setInt(2, selectedCourse.getId());
            ResultSet rs = ps.executeQuery();
            int count = 1;
            while (rs.next()) {
                String assignmentTitle = rs.getString(1);
                System.out.println(count + " " + assignmentTitle);
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

    @Override
    public void markAssignmentsPerStudentPerCourses() {
        User user = User.getInstance();

        viewTrainerCourses(false);
        Course selectedCourse = pickCourse();
        int studentId = pickStudentId(selectedCourse.getId());

        readAssignmentsPerCourse();

        System.out.println("Press");
        int count = 1;
        for (Assignment assignment : selectedCourse.getAssignments()) {
            System.out.println(count + " " + assignment.getTitle());
            count++;
        }

        int assignmentID = Util.getPositiveInteger(1, selectedCourse.getAssignments().size()) - 1;

        System.out.print("Mark the Assignment(0-100):");
        int assignmentMark = Util.getPositiveInteger(0, 100);

        Connection con = MySQL_utils.getConnection();
        String sql = "UPDATE students_courses_assignments SET totalmark = ?  WHERE studentid=? and courseID=? and assignmentID=?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, assignmentMark);
            ps.setInt(2, studentId);
            ps.setInt(3, selectedCourse.getId());
            ps.setInt(4, selectedCourse.getAssignments().get(assignmentID).getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Mark completed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static void readAssignmentsPerCourse() {
        User user = User.getInstance();
        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT c.id , c.Title , a.*\n"
                + "FROM assignments a\n"
                + "INNER JOIN courses_assignments ca ON a.ID = ca.AssignmentID\n"
                + "INNER JOIN Courses c ON c.ID = ca.CourseID\n"
                + "ORDER BY c.ID,a.ID\n"
                + ";";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(selectSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int id2 = rs.getInt(3);
                String aTitle = rs.getString(4);
                String aDes = rs.getString(5);
                String aDate = rs.getString(6);
                Assignment assignment = new Assignment(id2, aTitle, aDes, aDate);
                int count = 0;
                for (Course tempCourse : user.getCourses()) {
                    if (tempCourse.getId() == id) {
                        user.getCourses().get(count).addAssignment(assignment);
                        break;
                    }
                    count++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentsPerCoursesQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }
}
