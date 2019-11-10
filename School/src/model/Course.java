package model;

import java.util.ArrayList;

public class Course {

    private int id;
    private String title;
    private String stream;
    private String type;
    private String startDate;
    private String endDate;
    private ArrayList<Assignment> assignments = new ArrayList<>();

    public Course() {
    }

    public Course(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Course(String title, String stream, String type) {
        this.title = title;
        this.stream = stream;
        this.type = type;
    }

    public Course(int id, String title, String stream, String type) {
        this.id = id;
        this.title = title;
        this.stream = stream;
        this.type = type;
    }

    public Course(int id, String title, String stream, String type, String startDate, String endDate) {
        this.id = id;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title=" + title + ", stream=" + stream + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

}
