package com.verification.model;

/**
 * Represents a course result submitted for verification.
 * 
 * This class encapsulates all information related to a course submission,
 * including the course code, title, submitted score, and verification status.
 * It manages the verification workflow by tracking both the original submitted
 * score and the verified score after review.
 * 
 * <p><b>Thread Safety:</b> This class is not thread-safe. External synchronization
 * is required if used in a multi-threaded environment.</p>
 * 
 * <p><b>Constraints:</b>
 * <ul>
 *   <li>Course code and title must not be empty or blank</li>
 *   <li>Scores must be in the range [0, 100]</li>
 *   <li>Course code is normalized to uppercase</li>
 *   <li>Course title and code are trimmed of leading/trailing whitespace</li>
 * </ul>
 * </p>
 * 
 * @author Abdulprogrammer
 * @version 1.0
 * @since 1.0
 */
public class Course {
    private final String courseCode;
    private final String courseTitle;
    private final double submittedScore;
    private double verifiedScore;
    private String status;

    /**
     * Creates a new course for verification.
     * 
     * <p>Initializes a new {@code Course} object with the provided parameters.
     * The course code is converted to uppercase and both code and title are trimmed.
     * The verified score is initially set to the submitted score, and the status
     * is set to "PENDING".</p>
     * 
     * @param courseCode the course code, must not be empty or blank
     * @param courseTitle the course title, must not be empty or blank
     * @param submittedScore the initial score in the range [0, 100]
     * 
     * @throws AssertionError if courseCode is null, empty, or blank
     * @throws AssertionError if courseTitle is null, empty, or blank
     * @throws AssertionError if submittedScore is not in the range [0, 100]
     * 
     * @see #updateVerification(double)
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

    /**
     * Returns the course code.
     * 
     * <p>The course code is returned in uppercase and without leading or trailing
     * whitespace.</p>
     * 
     * @return the course code in uppercase
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Returns the course title.
     * 
     * @return the course title without leading or trailing whitespace
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * Returns the score originally submitted for verification.
     * 
     * <p>This value is immutable and represents the initial submission score.</p>
     * 
     * @return the submitted score in the range [0, 100]
     */
    public double getSubmittedScore() {
        return submittedScore;
    }

    /**
     * Returns the verified score after review.
     * 
     * <p>Initially, this value equals the submitted score. It is updated when
     * {@link #updateVerification(double)} is called.</p>
     * 
     * @return the verified score in the range [0, 100]
     * 
     * @see #updateVerification(double)
     */
    public double getVerifiedScore() {
        return verifiedScore;
    }

    /**
     * Returns the current verification status of this course.
     * 
     * <p>Possible status values are:
     * <ul>
     *   <li>"PENDING" - awaiting verification</li>
     *   <li>"APPROVED" - verification has been completed</li>
     * </ul>
     * </p>
     * 
     * @return the current status
     * 
     * @see #updateVerification(double)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the verified score and sets the status to "APPROVED".
     * 
     * <p>This method is called when a verification officer reviews and confirms
     * or modifies the submitted score. Upon successful update, the status
     * automatically changes to "APPROVED".</p>
     * 
     * @param newScore the new verified score in the range [0, 100]
     * 
     * @throws IllegalArgumentException if the score is outside the range [0, 100]
     * 
     * @see #getVerifiedScore()
     * @see #getStatus()
     */
    public void updateVerification(double newScore) {
        if (newScore < 0 || newScore > 100) {
            throw new IllegalArgumentException("Verified score must be 0-100");
        }
        this.verifiedScore = newScore;
        this.status = "APPROVED";
    }

    /**
     * Returns a string representation of this course.
     * 
     * <p>The string includes all relevant course information in the following format:
     * <br>
     * {@code COURSECODE - Course Title | Submitted: X.X | Verified: Y.Y | Status: STATUS}
     * </p>
     * 
     * @return a formatted string containing course details
     */
    @Override
    public String toString() {
        return courseCode + " - " + courseTitle + 
               " | Submitted: " + submittedScore + 
               " | Verified: " + verifiedScore + 
               " | Status: " + status;
    }
}
