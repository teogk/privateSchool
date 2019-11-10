package util;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;
import mySQL_Connection.MySQL_utils;
import static queries.AssignmentQueries.getAllAssignments;
import static queries.CourseQueries.getAllCourses;
import static queries.UserQueries.getUsersByRoleID;

public class Pick {

    // Returns the course id of the selected course
    public static Course pickCourse() {
        User user = User.getInstance();

        System.out.println("Press");
        int count = 1;
        for (Course course : user.getCourses()) {
            System.out.println(count + " " + course.getTitle());
            count++;
        }
        int selectedIndex = Util.getPositiveInteger(1, user.getCourses().size()) - 1;

        return user.getCourses().get(selectedIndex);
    }

    public static Course pickCourseForHeadMaster() {
        List<Course> courses = getAllCourses();

        int count = 1;
        System.out.println("Select course \nPress");
        for (Course course : courses) {
            System.out.println(count + " " + course.getTitle());
            count++;
        }
        int selectedIndex = Util.getPositiveInteger(1, courses.size()) - 1;
        return courses.get(selectedIndex);
    }

    public static Assignment pickAssignmentForHeadMaster() {
        List<Assignment> assignments = getAllAssignments();

        int count = 1;
        System.out.println("Press");
        for (Assignment assignment : assignments) {
            System.out.println(count + " " + assignment.getTitle());
            count++;
        }

        int selectedIndex = Util.getPositiveInteger(1, assignments.size()) - 1;
        return assignments.get(selectedIndex);
    }

    public static User pickStudentForHeadMaster() {
        List<User> students = getUsersByRoleID(3);

        int count = 1;
        System.out.println("Select student \nPress");
        for (User student : students) {
            System.out.println(count + " " + student.getFirstname() + " " + student.getLastname() + " with username: " + student.getUsername());
            count++;
        }

        int selectedIndex = Util.getPositiveInteger(1, students.size()) - 1;
        return students.get(selectedIndex);
    }

    public static User pickTrainerForHeadMaster() {
        List<User> trainers = getUsersByRoleID(2);

        int count = 1;
        System.out.println("Select Trainer \nPress");
        for (User trainer : trainers) {
            System.out.println(count + " " + trainer.getFirstname() + " " + trainer.getLastname() + " with username: " + trainer.getUsername());
            count++;
        }

        int selectedIndex = Util.getPositiveInteger(1, trainers.size()) - 1;
        return trainers.get(selectedIndex);
    }

    public static int pickStudentId(int courseId) {

        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT u.id, u.FirstName, u.LastName, u.username\n"
                + "FROM users u\n"
                + "INNER JOIN users_courses uc ON u.id = uc.userid\n"
                + "INNER JOIN courses c ON c.id = uc.courseid\n"
                + "where u.roleid=3 and c.id=?\n"
                + ";";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();

            ArrayList<User> students = new ArrayList<>();
            int count = 1;
            System.out.println("Press");
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String username = rs.getString(4);
                User tempStudent = new User(id, firstName, lastName, username);
                students.add(tempStudent);
                System.out.println(count + " " + firstName + " " + lastName + " with username: " + username);
                count++;
            }

            int selectedStudent = Util.getPositiveInteger(1, students.size()) - 1;
            return students.get(selectedStudent).getId();

        } catch (SQLException ex) {
            Logger.getLogger(Pick.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return -1;
    }

    public static void viewUserCourses(int id) {

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
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                String startDate = rs.getString(5);
                String endDate = rs.getString(6);
                System.out.println("Title:" + title + ", Stream:" + stream + ", Type:" + type + " Start Date:" + startDate + ", End Date:" + endDate);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        } finally {
            MySQL_utils.close(con);
        }

    }
}
