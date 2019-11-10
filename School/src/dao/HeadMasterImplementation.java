package dao;

import java.util.List;
import java.util.Scanner;
import util.Util;
import model.*;
import static util.Pick.*;
import static queries.AssignmentQueries.*;
import static queries.CourseQueries.*;
import static queries.UserQueries.*;
import static queries.UsersPerCoursesQueries.*;
import static util.Util.getPositiveInteger;
import static queries.AssignmentsPerCoursesQueries.*;
import static queries.UsersPerCoursesQueries.deleteEnrollToCourse;
import static queries.ScheduleQueries.*;

import static util.PasswordHashing.encodePassword;

public class HeadMasterImplementation {

    //CRUD_OnCourses
    public static void createCourseHeadMaster() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input Course ");
        System.out.print(" title: ");
        String title = input.nextLine();
        System.out.print(" stream: ");
        String stream = input.nextLine();
        System.out.print(" type: ");
        String type = input.nextLine();
        Course newCourse = new Course(title, stream, type);
        insertCourse(newCourse);
    }

    public static void readCourseHeadMaster() {
        List<Course> courses = getAllCourses();
        for (Course course : courses) {
            System.out.println(course.getId() + " " + course.getTitle());
        }
        System.out.println(" ");
    }

    public static void updateCourseHeadMaster() {
        Course selectedCourse = pickCourseForHeadMaster();
        Scanner input = new Scanner(System.in);
        System.out.print(" Change title (" + selectedCourse.getTitle() + ") to: ");
        String title = input.nextLine();
        System.out.print(" Change stream (" + selectedCourse.getStream() + ") to: ");
        String stream = input.nextLine();
        System.out.print(" Change type (" + selectedCourse.getType() + ") to: ");
        String type = input.nextLine();
        Course updateCourse = new Course(selectedCourse.getId(), title, stream, type);
        updateCourse(updateCourse);

    }

    public static void deleteCourseHeadMaster() {
        Course selectedCourse = pickCourseForHeadMaster();
        deleteCourse(selectedCourse);
    }

    //CRUD_OnAssignments
    public static void createAssignmentHeadMaster() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input Assignment ");
        System.out.print(" Title: ");
        String title = input.nextLine();
        System.out.print(" Description: ");
        String description = input.nextLine();
        System.out.print(" Submission Date and Time(yyyy-mm-dd hh:mm:ss): ");
        String SubmissionDateandTime = input.nextLine();
        Assignment assignment = new Assignment(title, description, SubmissionDateandTime);
        insertAssignment(assignment);
    }

    public static void readAssignmentHeadMaster() {
        List<Assignment> assignments = getAllAssignments();
        for (Assignment assignment : assignments) {
            System.out.println(assignment);
        }
        System.out.println(" ");
    }

    public static void updateAssignmentHeadMaster() {
        Assignment selectedAssignment = pickAssignmentForHeadMaster();
        Scanner input = new Scanner(System.in);
        System.out.println("Input Assignment changes ");
        System.out.print(" Change title (" + selectedAssignment.getTitle() + ") to: ");
        String title = input.nextLine();
        System.out.print(" Change description (" + selectedAssignment.getDescription() + ") to: ");
        String description = input.nextLine();
        System.out.print(" Change submission Date and Time (" + selectedAssignment.getSubmissionDateAndTime() + ") to: ");
        String submissionDateandTime = input.nextLine();
        Assignment assignment = new Assignment(selectedAssignment.getId(), title, description, submissionDateandTime);
        updateAssignment(assignment);
    }

    public static void deleteAssignmentHeadMaster() {
        Assignment selectedAssignment = pickAssignmentForHeadMaster();
        deleteAssignment(selectedAssignment);
    }

    //CRUD_OnStudents
    public static void createStudentHeadMaster() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input Student ");
        int roleId = 3;
        System.out.print(" Username: ");
        String username = input.nextLine();
        System.out.print(" Password: ");
        String password = input.nextLine();
        System.out.print(" First Name: ");
        String firstName = input.nextLine();
        System.out.print(" Last Name: ");
        String lastName = input.nextLine();
        User user = new User(roleId, username, encodePassword(password), firstName, lastName);
        insertUser(user);
    }

    public static void readStudentsHeadMaster() {
        System.out.println("Students: ");
        List<User> users = getUsersByRoleID(3);
        int count = 1;
        for (User student : users) {
            System.out.println(count + " " + student.getFirstname() + " " + student.getLastname() + " with username: " + student.getUsername());
            count++;
        }
    }

    public static void updateStudentHeadMaster() {
        User selectedStudent = pickStudentForHeadMaster();
        Scanner input = new Scanner(System.in);
        System.out.println("Input Student changes ");
        System.out.print(" change role id (" + selectedStudent.getRoleID() + "): ");
        int roleId = getPositiveInteger();
        System.out.print(" Change Username (" + selectedStudent.getUsername() + "): ");
        String userName = input.nextLine();
        System.out.print(" Change Password: ");
        String password = input.nextLine();
        System.out.print(" Change First Name (" + selectedStudent.getFirstname() + "): ");
        String firstName = input.nextLine();
        System.out.print(" Change Last Name (" + selectedStudent.getLastname() + "): ");
        String lastName = input.nextLine();
        User user = new User(selectedStudent.getId(), roleId, userName, encodePassword(password), firstName, lastName);
        updateUser(user);
    }

    public static void deleteStudentHeadMaster() {
        User selectedStudent = pickStudentForHeadMaster();
        deleteUser(selectedStudent);
    }

    //CRUD_OnTrainers
    public static void createTrainerHeadMaster() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input Trainer ");
        int roleId = 2;
        System.out.print(" Username: ");
        String username = input.nextLine();
        System.out.print(" Password: ");
        String password = input.nextLine();
        System.out.print(" First Name: ");
        String firstName = input.nextLine();
        System.out.print(" Last Name: ");
        String lastName = input.nextLine();
        User user = new User(roleId, username, encodePassword(password), firstName, lastName);
        insertUser(user);
    }

    public static void readTrainersHeadMaster() {
        List<User> users = getUsersByRoleID(2);
        System.out.println("Trainers: ");
        for (User trainer : users) {
            System.out.println(trainer);
        }
        System.out.println(" ");
    }

    public static void updateTrainerHeadMaster() {
        User trainer = pickTrainerForHeadMaster();
        Scanner input = new Scanner(System.in);
        System.out.println("Input Trainer changes ");
        System.out.print(" For role id (" + trainer.getRoleID() + "): ");
        int roleId = getPositiveInteger();
        System.out.print(" Change Username (" + trainer.getUsername() + "): ");
        String userName = input.nextLine();
        System.out.print(" Change Password: ");
        String password = input.nextLine();
        System.out.print(" Change First Name (" + trainer.getFirstname() + "): ");
        String firstName = input.nextLine();
        System.out.print(" Change Last Name (" + trainer.getLastname() + "): ");
        String lastName = input.nextLine();
        User user = new User(trainer.getId(), roleId, userName, encodePassword(password), firstName, lastName);
        updateUser(user);
    }

    public static void deleteTrainerHeadMaster() {
        User trainer = pickTrainerForHeadMaster();
        deleteUser(trainer);
    }

    //CRUD_OnStudentsPerCourses
    public static void createStudentPerCourseEnrollment() {
        List<User> students = getUsersByRoleID(3);
        System.out.println("Press");
        int count = 1;
        for (User student : students) {
            System.out.println(count + " for " + student.getFirstname() + " " + student.getLastname());
            count++;
        }

        int studentIndex = getPositiveInteger(1, students.size()) - 1;

        List<Course> courses = getAllCourses();
        System.out.println("Press");
        int count2 = 1;
        for (Course course : courses) {
            System.out.println(count2 + " for " + course.getTitle());
            count2++;
        }
        int courseIndex = getPositiveInteger(1, courses.size()) - 1;

        enrollUserToCourse(students.get(studentIndex), courses.get(courseIndex));
    }

    public static void readStudentsPerCourses() {
        readUsersPerCourses(3);
    }

    public static void updateStudentPerCourse() {
        readUsersPerCourses(3);
        System.out.println("\nUpdate on Student per course\n"
                + "Input the [User_Course id] that you want to make changes: ");
        int id = getPositiveInteger();
        System.out.print("Input Course id:");
        int courseId = getPositiveInteger();
        System.out.print("Input Student id:");
        int studentId = getPositiveInteger();
        updateEnrollToCourse(studentId, courseId, id);
    }

    public static void deleteStudentPerCourse() {
        readUsersPerCourses(3);
        System.out.print("Input the [User_Course id] you want to Delete: ");
        int id = getPositiveInteger();
        deleteEnrollToCourse(id);
    }

    //CRUD_OnTrainersPerCourses
    public static void createTrainerPerCourseEnrollment() {
        List<User> trainers = getUsersByRoleID(2);
        System.out.println("Press");
        int count = 1;
        for (User trainer : trainers) {
            System.out.println(count + " for " + trainer.getFirstname() + " " + trainer.getLastname());
            count++;
        }

        int trainerIndex = getPositiveInteger(1, trainers.size()) - 1;

        List<Course> courses = getAllCourses();
        readCourseHeadMaster();

        int courseIndex = getPositiveInteger(1, courses.size()) - 1;

        enrollUserToCourse(trainers.get(trainerIndex), courses.get(courseIndex));

    }

    public static void readTrainersPerCourses() {
        readUsersPerCourses(2);
    }

    public static void updateTrainerPerCourse() {
        readUsersPerCourses(2);
        System.out.println("\nUpdate on Trainer per course\n"
                + "Input the [User_Course id] that you want to make changes: ");
        int id = getPositiveInteger();
        System.out.print("Input Trainer id:");
        int trainerId = getPositiveInteger();
        System.out.print("Input Course id:");
        int courseId = getPositiveInteger();
        updateEnrollToCourse(trainerId, courseId, id);
    }

    public static void deleteTrainerPerCourse() {
        readUsersPerCourses(2);
        System.out.print("Input the [User_Course id] you want to Delete: ");
        int id = getPositiveInteger();
        deleteEnrollToCourse(id);
    }

    //CRUD_OnAssignmentsPerCourses
    public static void createAssignmentPerCourseAppointment() {
        List<Course> courses = getAllCourses();
        String choose1 = " \n Press button: ";
        int count1 = 1;
        for (Course course : courses) {
            choose1 += "  " + count1 + " for " + course.getTitle() + ",";
            count1++;
        }
        System.out.println(Util.removeLastChar(choose1));
        int courseIndex = getPositiveInteger(1, courses.size());

        List<Assignment> assignments = getAllAssignments();
        String choose2 = " \n Press button: ";
        int count2 = 1;
        for (Assignment assignment : assignments) {
            choose2 += "  " + count2 + " for " + assignment.getTitle() + " " + assignment.getDescription() + ",";
            count2++;
        }
        System.out.println(Util.removeLastChar(choose2));
        int assignmentIndex = getPositiveInteger(1, assignments.size());

        appointAssignmentToCourse(courses.get(courseIndex - 1), assignments.get(assignmentIndex - 1));

    }

    public static void readAssignmentsPerCourses() {
        readAssignmentsPerCoursesQ();
    }

    public static void updateAssignmentPerCourse() {
        readAssignmentsPerCoursesQ();
        System.out.println("\nUpdate on Assignment per course\n"
                + "Input the [Course_Assignment id] that you want to make changes: ");
        int id = getPositiveInteger();
        System.out.print("Input Course id:");
        int courseId = getPositiveInteger();
        System.out.print("Input Assignment id:");
        int assignmentId = getPositiveInteger();
        updateCourses_Assignments(courseId, assignmentId, id);
    }

    public static void deleteAssignmentPerCourse() {
        readAssignmentsPerCoursesQ();
        System.out.print("Input the [Course_Assignment id] you want to Delete: ");
        int id = getPositiveInteger();
        deleteCourses_Assignments(id);
    }

    //CRUD_OnSchedulePerCourses
    public static void createSchedulePerCourse() {
        Scanner input = new Scanner(System.in);
        System.out.print("Insert tha date you want in the Schedule((yyyy-mm-dd):");
        String date = input.nextLine();
        Course selectedCourse = pickCourseForHeadMaster();
        User selectedTrainer = pickTrainerForHeadMaster();

        CreateOnSchedule(date, selectedCourse, selectedTrainer);
    }

    public static void readSchedulePerCourses() {
        readSchedule();
    }

    public static void updateSchedulePerCourse() {
        readSchedule();
        Scanner input = new Scanner(System.in);
        System.out.print("\nUpdate Schedule per course\n"
                + "Input the [Schedule id] that you want to make changes: ");
        int id = getPositiveInteger();
        System.out.print("Insert tha date you want in the Schedule((yyyy-mm-dd):");
        String date = input.nextLine();
        Course selectedCourse = pickCourseForHeadMaster();
        User selectedTrainer = pickTrainerForHeadMaster();

        updateSchedule(date, selectedCourse, selectedTrainer, id);
    }

    public static void deleteSchedulePerCourse() {
        readSchedule();
        System.out.print("\nDelete Schedule per course\n"
                + "Input the [Schedule id] that you want to delete: ");
        int id = getPositiveInteger();
        deleteSchedule(id);

    }

}
