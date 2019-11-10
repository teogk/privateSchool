/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package choice;

import static dao.HeadMasterImplementation.*;
import util.Util;

/**
 *
 * @author Teo
 */
public class ChoiceStep3_HeadMasterMenu {

//1
    public static void CRUD_OnCourses() {

        System.out.println("Press: \n1 to Create a Course \n2 to Read the Courses "
                + "\n3 to Update a Course \n4 to Delete a Course ");
        switch (Util.getPositiveInteger()) {
            case 1:
                createCourseHeadMaster();
                break;
            case 2:
                readCourseHeadMaster();
                break;
            case 3:
                updateCourseHeadMaster();
                break;
            case 4:
                deleteCourseHeadMaster();
                break;
            default:
                System.out.println("Program ended");
                break;
        }
    }
//2

    public static void CRUD_OnStudents() {

        System.out.println("Press: \n1 to Create a Student \n2 to Read Students "
                + "\n3 to Update a Student \n4 to Delete a Student ");
        switch (Util.getPositiveInteger()) {
            case 1:
                createStudentHeadMaster();
                break;
            case 2:
                readStudentsHeadMaster();
                break;
            case 3:
                updateStudentHeadMaster();
                break;
            case 4:
                deleteStudentHeadMaster();
                break;
            default:
                System.out.println("Program ended");
                break;
        }
    }
//3

    public static void CRUD_OnAssignments() {

        System.out.println("Press: \n1 to Create an Assignment \n2 to Read the Assignments "
                + "\n3 to Update an Assignment \n4 to Delete an Assignment ");
        switch (Util.getPositiveInteger()) {
            case 1:
                createAssignmentHeadMaster();
                break;
            case 2:
                readAssignmentHeadMaster();
                break;
            case 3:
                updateAssignmentHeadMaster();
                break;
            case 4:
                deleteAssignmentHeadMaster();
                break;
            default:
                System.out.println("Program ended");
                break;
        }
    }
//4

    public static void CRUD_OnTrainers() {

        System.out.println("Press: \n1 to Create a Trainer \n2 to Read Trainers "
                + "\n3 to Update a Trainer \n4 to Delete a Trainer ");
        switch (Util.getPositiveInteger()) {
            case 1:
                createTrainerHeadMaster();
                break;
            case 2:
                readTrainersHeadMaster();
                break;
            case 3:
                updateTrainerHeadMaster();
                break;
            case 4:
                deleteTrainerHeadMaster();
                break;
            default:
                System.out.println("Program ended");
                break;
        }
    }
//5

    public static void CRUD_OnStudentsPerCourses() {

        System.out.println("Press: \n1 to Create a Student per Course enrollment \n2 to Read the Students per Courses"
                + "\n3 to Update an enrollment \n4 to Delete an enrollment ");
        switch (Util.getPositiveInteger()) {
            case 1:
                createStudentPerCourseEnrollment();
                break;
            case 2:
                readStudentsPerCourses();
                break;
            case 3:
                updateStudentPerCourse();
                break;
            case 4:
                deleteStudentPerCourse();
                break;
            default:
                System.out.println("Program ended");
                break;
        }
    }
//6

    public static void CRUD_OnTrainersPerCourses() {
        System.out.println("Press: \n1 to Create a Trainer per Course enrollment \n2 to Read the Trainers per Courses "
                + "\n3 to Update an enrollment \n4 to Delete an enrollment ");
        switch (Util.getPositiveInteger()) {
            case 1:
                createTrainerPerCourseEnrollment();
                break;
            case 2:
                readTrainersPerCourses();
                break;
            case 3:
                updateTrainerPerCourse();
                break;
            case 4:
                deleteTrainerPerCourse();
                break;
            default:
                System.out.println("Program ended");
                break;
        }

    }
//7

    public static void CRUD_OnAssignmentsPerCourses() {

        System.out.println("Press: \n1 to Create an Assignment per Course appointment \n2 to Read all Assignments per Courses"
                + "\n3 to Update an Assignment per Course \n4 to Delete an Assignment per Course ");
        switch (Util.getPositiveInteger()) {
            case 1:
                createAssignmentPerCourseAppointment();
                break;
            case 2:
                readAssignmentsPerCourses();
                break;
            case 3:
                updateAssignmentPerCourse();
                break;
            case 4:
                deleteAssignmentPerCourse();
                break;
            default:
                System.out.println("Program ended");
                break;
        }

    }
//8

    public static void CRUD_OnSchedulePerCourses() {

        System.out.println("Press: \n1 to Create a Schedule per Course \n2 to Read all Schedules per Courses"
                + "\n3 to Update a Schedule per Course \n4 to Delete a Schedule per Course ");
        switch (Util.getPositiveInteger()) {
            case 1:
                createSchedulePerCourse();
                break;
            case 2:
                readSchedulePerCourses();
                break;
            case 3:
                updateSchedulePerCourse();
                break;
            case 4:
                deleteSchedulePerCourse();
                break;
            default:
                System.out.println("Program ended");
                break;
        }

    }

}
