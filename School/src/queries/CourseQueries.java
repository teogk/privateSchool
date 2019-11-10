package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import mySQL_Connection.MySQL_utils;

public class CourseQueries {

    public static List<Course> getAllCourses() {
        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT * from courses";
        PreparedStatement ps = null;
        List<Course> courses = new ArrayList();
        try {
            ps = con.prepareStatement(selectSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                String startDate = rs.getString(5);
                String endDate = rs.getString(6);
                Course course = new Course(id, title, stream, type, startDate, endDate);
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return courses;
    }

    public static boolean insertCourse(Course course) {

        Connection con = MySQL_utils.getConnection();

        String sql = "insert into courses(title,stream,type) values(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getStream());
            ps.setString(3, course.getType());

            int i = ps.executeUpdate();

            if (i == 1) {
                System.out.println("Course created");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return false;
    }

    public static void updateCourse(Course course) {
        Connection con = MySQL_utils.getConnection();
        String sql = "UPDATE courses SET title = ?, stream = ? ,type = ? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getStream());
            ps.setString(3, course.getType());
            ps.setInt(4, course.getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Update was successful");
            } else {
                System.out.println("Update was unsuccessful");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }

    public static void deleteCourse(Course course) {

        Connection con = MySQL_utils.getConnection();
        String sql = ("DELETE FROM courses WHERE id=?");
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, course.getId());
            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Course " + course.getTitle() + " deleted!");;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
    }
}
