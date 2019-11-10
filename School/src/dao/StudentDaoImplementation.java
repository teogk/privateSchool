package dao;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;
import static queries.CourseQueries.getAllCourses;
import mySQL_Connection.MySQL_utils;
import static util.Pick.pickCourse;
import model.*;
import queries.ScheduleQueries;

public class StudentDaoImplementation implements StudentDaoInterface {

    @Override
    public void enrollToCourse() {
        User user = User.getInstance();
        List<Course> courses = getAllCourses();
        System.out.println("Press");
        int count = 1;
        for (Course course : courses) {
            System.out.println(count + " for " + course.getTitle());
            count++;
        }

        if (courses.size() > 0) {
            int courseIndex = Util.getPositiveInteger(1, courses.size()) - 1;
            Connection con = MySQL_utils.getConnection();
            String sql = "insert into users_courses(userID,CourseID) values(?,?)";
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, user.getId());
                ps.setInt(2, courses.get(courseIndex).getId());
                int i = ps.executeUpdate();
                if (i == 1) {
                    System.out.println("You enrolled in " + courses.get(courseIndex).getTitle());
                }
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                    System.out.println("Student is already enrolled to " + courses.get(courseIndex).getTitle());
                } else {
                    System.out.println(ex.getLocalizedMessage());
                }
            } finally {
                MySQL_utils.close(con);
            }
        } else {
            System.out.println("There are no Courses to enroll!");
        }
    }

    @Override
    public void seeSchedulePerCourse() {
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
                String date = rs.getString(2);
                String courseTitle = rs.getString(3);
                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                System.out.println("Date: " + date + " Course:" + courseTitle + " Trainer:" + firstname + " " + lastname);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleQueries.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }

    }

    @Override
    public Course viewAssignmentSubmissionDatesPerCourse(boolean shouldPrint) {
        User user = User.getInstance();
        Connection con = MySQL_utils.getConnection();
        String selectSQL = "SELECT c.id , c.Title ,a.id,a.Title, a.Description , a.SubmissionDateAndTime\n"
                + "FROM assignments a\n"
                + "INNER JOIN courses_assignments ca ON a.ID = ca.AssignmentID\n"
                + "INNER JOIN Courses c ON c.ID = ca.CourseID\n"
                + "INNER JOIN users_courses uc ON c.ID = uc.CourseID\n"
                + "where uc.userid = ?\n"
                + "ORDER BY c.ID,a.ID\n"
                + ";";
        PreparedStatement ps = null;
        ArrayList<Course> courses = new ArrayList<>();
        try {
            ps = con.prepareStatement(selectSQL);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt(1);
                String courseTitle = rs.getString(2);
                int assignmentId = rs.getInt(3);
                String aTitle = rs.getString(4);
                String aDescription = rs.getString(5);
                String aSubmissionDateAndTime = rs.getString(6);

                Course course = new Course(courseId, courseTitle);
                boolean isCourseContained = false;

                for (Course tempCourse : courses) {
                    if (tempCourse.getId() == courseId) {
                        isCourseContained = true;
                    }
                }
                if (!isCourseContained) {
                    courses.add(course);
                }
                Assignment assignment = new Assignment(assignmentId, aTitle, aDescription, aSubmissionDateAndTime);
                int count = 0;
                for (Course tempCourse : courses) {
                    if (tempCourse.getId() == courseId) {
                        courses.get(count).addAssignment(assignment);
                        break;
                    }
                    count++;
                }
            }
            user.setCourses(courses);

            Course selectedCourse = pickCourse();

            if (shouldPrint) {
                int count = 1;
                for (Assignment assignment : selectedCourse.getAssignments()) {
                    System.out.println(count + " " + assignment.getTitle() + ", " + assignment.getDescription()
                            + ", Date of submission: " + assignment.getSubmissionDateAndTime());
                    count++;
                }
            }

            return selectedCourse;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }

        return null;
    }

    @Override
    public boolean submitAssignment() {
        User user = User.getInstance();
        Course selectedCourse = viewAssignmentSubmissionDatesPerCourse(false);

        Connection con = MySQL_utils.getConnection();
        String sql = "UPDATE students_courses_assignments SET Submit = 1  WHERE studentid=? and courseID=? and assignmentID=?";
        PreparedStatement ps = null;

        int count = 1;
        System.out.println("Press");
        for (Assignment assignment : selectedCourse.getAssignments()) {
            System.out.println(count + " " + assignment.getTitle());
            count++;
        }
        int assignmentID = Util.getPositiveInteger(1, selectedCourse.getAssignments().size()) - 1;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setInt(2, selectedCourse.getId());
            ps.setInt(3, selectedCourse.getAssignments().get(assignmentID).getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                System.out.println("Assignment submited");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImplementation.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQL_utils.close(con);
        }
        return true;
    }
}
