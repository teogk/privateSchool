package model;

import java.util.ArrayList;

public class User {

    private int id;
    private int roleID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private ArrayList<Course> courses = new ArrayList<>();

    private static User instance;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public User() {
    }

    public User(int id, String firstName, String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public User(int roleID, String username, String password, String firstname, String lastnmae) {
        this.roleID = roleID;
        this.username = username;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastnmae;
    }

    public User(int id, int roleID, String username, String password, String firstname, String lastnmae) {
        this.id = id;
        this.roleID = roleID;
        this.username = username;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastnmae;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return "username: " + username + ", firstname: " + firstName + ", lastname: " + lastName;
    }

}
