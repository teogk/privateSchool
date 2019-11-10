
package dao;

import model.Course;


public interface StudentDaoInterface {
   
    public void enrollToCourse();
    public  void seeSchedulePerCourse();
    public Course viewAssignmentSubmissionDatesPerCourse(boolean shouldPrint);
    public boolean submitAssignment();
}
