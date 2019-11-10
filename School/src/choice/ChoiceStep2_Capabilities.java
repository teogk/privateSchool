package choice;

import static choice.ChoiceStep3_HeadMasterMenu.*;
import dao.StudentDaoImplementation;
import dao.TrainerDaoImplementation;
import util.Util;

public class ChoiceStep2_Capabilities {

    static void headMasterCapabilities() {
        System.out.println("Press: \n1 to CRUD on Courses \n2 to CRUD on Students "
                + "\n3 to CRUD on Assignments \n4 to CRUD on Trainers \n5 to CRUD on Students per Courses"
                + "\n6 to CRUD on Trainers per Courses \n7 to CRUD on Assignments per Course \n8 to CRUD on Schedule per Courses");

        switch (Util.getPositiveInteger()) {
            case 1:
                CRUD_OnCourses();
                break;
            case 2:
                CRUD_OnStudents();
                break;
            case 3:
                CRUD_OnAssignments();
                break;
            case 4:
                CRUD_OnTrainers();
                break;
            case 5:
                CRUD_OnStudentsPerCourses();
                break;
            case 6:
                CRUD_OnTrainersPerCourses();
                break;
            case 7:
                CRUD_OnAssignmentsPerCourses();
                break;
            case 8:
                CRUD_OnSchedulePerCourses();
                break;
            default:
                System.out.println("Program ended");
                break;
        }
    }

    static void trainerCapabilities() {
        TrainerDaoImplementation tDao = new TrainerDaoImplementation();
        System.out.println("Press: \n1 View all the Courses you are enrolled \n2 View all the Students per Course "
                + "\n3 View all the Assignments per Student per Course \n4 Mark all the Assignments per Student per Course ");

        switch (Util.getPositiveInteger()) {
            case 1:
                tDao.viewTrainerCourses(true);
                break;
            case 2:
                tDao.viewStudentsPerCourses();
                break;
            case 3:
                tDao.viewAssignmentsPerStudentPerCourses();
                break;
            case 4:
                tDao.markAssignmentsPerStudentPerCourses();
                break;
            default:
                System.out.println("Program ended");
                break;
        }
    }

    static void studentCapabilities() {
        StudentDaoImplementation sDao = new StudentDaoImplementation();
        System.out.println("Press: \n1 to Enroll to any Course \n2 to see your daily Schedule per Course "
                + "\n3 to see the dates of submission of the Assignments per Course \n4 to Submit any Assignments ");

        switch (Util.getPositiveInteger()) {
            case 1:
                sDao.enrollToCourse();
                break;
            case 2:
                sDao.seeSchedulePerCourse();
                break;
            case 3:
                sDao.viewAssignmentSubmissionDatesPerCourse(true);
                break;
            case 4:
                sDao.submitAssignment();
                break;
            default:
                System.out.println("Program ended");
                break;
        }
    }

}
