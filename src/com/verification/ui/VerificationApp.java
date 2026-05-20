package com.verification.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class VerificationApp extends Application {

    // Store all result details per student+course
    private Map<String, Result> results = new HashMap<>();

    // Helper class to hold result data
    public static class Result {
        String studentId;
        String studentName;
        String courseCode;
        String courseTitle;
        int score;
        boolean verified;

        public Result(String studentId, String studentName, String courseCode, String courseTitle, int score) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.courseCode = courseCode;
            this.courseTitle = courseTitle;
            this.score = score;
            this.verified = false;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab submitTab = new Tab("Submit Score", createSubmitTab());
        Tab verifyTab = new Tab("Verify Score", createVerifyTab());
        Tab checkTab = new Tab("Check Status", createCheckTab());

        tabPane.getTabs().addAll(submitTab, verifyTab, checkTab);

        Scene scene = new Scene(tabPane, 520, 450);
        primaryStage.setTitle("Result Verification System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSubmitTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Submit Course Score");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField studentIdField = new TextField();
        studentIdField.setPromptText("e.g. CIS/STE/22/1088");

        TextField studentNameField = new TextField();
        studentNameField.setPromptText("e.g. John Doe");

        TextField courseCodeField = new TextField();
        courseCodeField.setPromptText("e.g. SEN301");

        TextField courseTitleField = new TextField();
        courseTitleField.setPromptText("e.g. Software Engineering");

        TextField scoreField = new TextField();
        scoreField.setPromptText("e.g. 75");

        grid.add(new Label("Student ID:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("Student Name:"), 0, 1);
        grid.add(studentNameField, 1, 1);
        grid.add(new Label("Course Code:"), 0, 2);
        grid.add(courseCodeField, 1, 2);
        grid.add(new Label("Course Title:"), 0, 3);
        grid.add(courseTitleField, 1, 3);
        grid.add(new Label("Score:"), 0, 4);
        grid.add(scoreField, 1, 4);

        Label message = new Label();
        message.setStyle("-fx-text-fill: green;");

        Button submitBtn = new Button("Submit");
        submitBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 8 20;");
        submitBtn.setOnAction(e -> {
            String key = studentIdField.getText().trim() + ":" + courseCodeField.getText().trim();
            try {
                int score = Integer.parseInt(scoreField.getText().trim());
                if (studentIdField.getText().trim().isEmpty() || studentNameField.getText().trim().isEmpty()) {
                    message.setStyle("-fx-text-fill: red;");
                    message.setText("Student ID and Name are required");
                    return;
                }
                Result r = new Result(
                    studentIdField.getText().trim(),
                    studentNameField.getText().trim(),
                    courseCodeField.getText().trim(),
                    courseTitleField.getText().trim(),
                    score
                );
                results.put(key, r);
                message.setStyle("-fx-text-fill: green;");
                message.setText("Score submitted successfully!");
                studentIdField.clear();
                studentNameField.clear();
                courseCodeField.clear();
                courseTitleField.clear();
                scoreField.clear();
            } catch (NumberFormatException ex) {
                message.setStyle("-fx-text-fill: red;");
                message.setText("Please enter a valid number for score");
            }
        });

        vbox.getChildren().addAll(title, grid, submitBtn, message);
        return vbox;
    }

    private VBox createVerifyTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Verify Student Score");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField studentIdField = new TextField();
        studentIdField.setPromptText("Student ID");

        TextField courseCodeField = new TextField();
        courseCodeField.setPromptText("Course Code");

        TextField verifiedScoreField = new TextField();
        verifiedScoreField.setPromptText("Verified Score");

        grid.add(new Label("Student ID:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("Course Code:"), 0, 1);
        grid.add(courseCodeField, 1, 1);
        grid.add(new Label("Verified Score:"), 0, 2);
        grid.add(verifiedScoreField, 1, 2);

        Label message = new Label();

        Button verifyBtn = new Button("Verify");
        verifyBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 8 20;");
        verifyBtn.setOnAction(e -> {
            String key = studentIdField.getText().trim() + ":" + courseCodeField.getText().trim();
            if (results.containsKey(key)) {
                try {
                    int score = Integer.parseInt(verifiedScoreField.getText().trim());
                    Result r = results.get(key);
                    r.score = score;
                    r.verified = true;
                    message.setStyle("-fx-text-fill: green;");
                    message.setText("Verification updated for " + r.studentName);
                    verifiedScoreField.clear();
                } catch (NumberFormatException ex) {
                    message.setStyle("-fx-text-fill: red;");
                    message.setText("Enter a valid score");
                }
            } else {
                message.setStyle("-fx-text-fill: red;");
                message.setText("No submission found for this student/course");
            }
        });

        vbox.getChildren().addAll(title, grid, verifyBtn, message);
        return vbox;
    }

    private VBox createCheckTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Check Verification Status");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        TextField studentIdField = new TextField();
        studentIdField.setPromptText("Student ID");

        TextField courseCodeField = new TextField();
        courseCodeField.setPromptText("Course Code");

        grid.add(new Label("Student ID:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("Course Code:"), 0, 1);
        grid.add(courseCodeField, 1, 1);

        Button checkBtn = new Button("Check Status");
        checkBtn.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-padding: 8 20;");

        // Result display area
        GridPane resultGrid = new GridPane();
        resultGrid.setVgap(8);
        resultGrid.setHgap(10);
        resultGrid.setPadding(new Insets(15));
        resultGrid.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5;");
        resultGrid.setVisible(false);

        Label lblStudentId = new Label();
        Label lblStudentName = new Label();
        Label lblCourseCode = new Label();
        Label lblCourseTitle = new Label();
        Label lblScore = new Label();
        Label lblStatus = new Label();

        resultGrid.add(new Label("Student ID:"), 0, 0);
        resultGrid.add(lblStudentId, 1, 0);
        resultGrid.add(new Label("Student Name:"), 0, 1);
        resultGrid.add(lblStudentName, 1, 1);
        resultGrid.add(new Label("Course Code:"), 0, 2);
        resultGrid.add(lblCourseCode, 1, 2);
        resultGrid.add(new Label("Course Title:"), 0, 3);
        resultGrid.add(lblCourseTitle, 1, 3);
        resultGrid.add(new Label("Verified Score:"), 0, 4);
        resultGrid.add(lblScore, 1, 4);
        resultGrid.add(new Label("Status:"), 0, 5);
        resultGrid.add(lblStatus, 1, 5);

        Label errorMsg = new Label();
        errorMsg.setStyle("-fx-text-fill: red;");

        checkBtn.setOnAction(e -> {
            String key = studentIdField.getText().trim() + ":" + courseCodeField.getText().trim();
            if (results.containsKey(key)) {
                Result r = results.get(key);
                lblStudentId.setText(r.studentId);
                lblStudentName.setText(r.studentName);
                lblCourseCode.setText(r.courseCode);
                lblCourseTitle.setText(r.courseTitle);
                lblScore.setText(String.valueOf(r.score));
                lblStatus.setText(r.verified ? "Verified" : "Pending Verification");
                lblStatus.setStyle(r.verified ? "-fx-text-fill: green;" : "-fx-text-fill: orange;");
                
                resultGrid.setVisible(true);
                errorMsg.setText("");
            } else {
                resultGrid.setVisible(false);
                errorMsg.setText("No result found for this Student ID and Course Code");
            }
        });

        vbox.getChildren().addAll(title, grid, checkBtn, errorMsg, resultGrid);
        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}