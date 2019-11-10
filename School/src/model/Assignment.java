package model;

public class Assignment {

    private int id;
    private String title;
    private String description;
    private String submissionDateAndTime;
    private int oralMark;
    private int totalMark;

    public Assignment(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Assignment(String title, String description, String submissionDateAndTime) {
        this.title = title;
        this.description = description;
        this.submissionDateAndTime = submissionDateAndTime;

    }

    public Assignment(int id, String title, String description, String submissionDateAndTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.submissionDateAndTime = submissionDateAndTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmissionDateAndTime() {
        return submissionDateAndTime;
    }

    public void setSubmissionDateAndTime(String submissionDateAndTime) {
        this.submissionDateAndTime = submissionDateAndTime;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        return "Assignment " + title + " with description: " + description + " and submissionDateAndTime: " + submissionDateAndTime;
    }

}
