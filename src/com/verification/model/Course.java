package com.verification.model;

/**
 * Represents a course result submitted for verification.
 */
public class Course {
    private final String courseCode;
    private final String courseTitle;
    private final double submittedScore;
    private double verifiedScore;
    private String status;

    /**
     * Creates a new course for verification.
     * @param courseCode the course code, must not be empty
     * @param courseTitle the course title, must not be empty
     * @param submittedScore the score from 0 to 100
     */
    public Course(String courseCode, String courseTitle, double submittedScore) {
        assert courseCode != null && !courseCode.isBlank() : "Course code cannot be empty";
        assert courseTitle != null && !courseTitle.isBlank() : "Course title cannot be empty";
        assert submittedScore >= 0 && submittedScore <= 100 : "Score must be 0-100";
        
        this.courseCode = courseCode.trim().toUpperCase();
        this.courseTitle = courseTitle.trim();
        this.submittedScore = submittedScore;
        this.verifiedScore = submittedScore;
        this.status = "PENDING";
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseTitle() { return courseTitle; }
    public double getSubmittedScore() { return submittedScore; }
    public double getVerifiedScore() { return verifiedScore; }
    public String getStatus() { return status; }

    /**
     * Updates the verified score.
     * @param newScore the new score from 0 to 100
     * @throws IllegalArgumentException if score is invalid
     */
    public void updateVerification(double newScore) {
        if (newScore < 0 || newScore > 100) {
            throw new IllegalArgumentException("Verified score must be 0-100");
        }
        this.verifiedScore = newScore;
        this.status = "APPROVED";
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseTitle + 
               " | Submitted: " + submittedScore + 
               " | Verified: " + verifiedScore + 
               " | Status: " + status;
    }
}