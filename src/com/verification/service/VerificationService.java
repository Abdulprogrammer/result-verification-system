package com.verification.service;

import com.verification.model.Course;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles all business logic for course verification.
 */
public class VerificationService {
    private final Map<String, Course> courseDatabase = new HashMap<>();

    /**
     * Submits a course for verification.
     * @param code course code
     * @param title course title
     * @param score submitted score
     * @throws IllegalArgumentException if data is invalid or course exists
     */
    public void submitCourse(String code, String title, double score) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Course code is required");
        }
        String key = code.trim().toUpperCase();
        if (courseDatabase.containsKey(key)) {
            throw new IllegalArgumentException("Course already submitted");
        }
        courseDatabase.put(key, new Course(key, title, score));
    }

    /**
     * Verifies a course by updating its score.
     * @param code course code
     * @param newScore verified score
     * @throws IllegalArgumentException if course not found or score invalid
     */
    public void verifyCourse(String code, double newScore) {
        Course course = courseDatabase.get(code.toUpperCase());
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }
        course.updateVerification(newScore);
    }

    /**
     * Checks the verification status of a course.
     * @param code course code
     * @return the Course object or null if not found
     */
    public Course checkStatus(String code) {
        return courseDatabase.get(code.toUpperCase());
    }
}