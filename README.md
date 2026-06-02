# Result Verification System

A JavaFX desktop application for submitting, verifying, and checking course results.

### Features
- **Submit Course**: Add a new course with code, title, and submitted score
- **Verify Score**: Update a course with the verified score and change status to APPROVED  
- **Check Status**: View submitted vs verified score and current status
- **Validation**: Uses assertions for data integrity and exception handling for user errors
- **Javadoc**: Full API documentation included

### Requirements
- **JDK**: 17 or 21 LTS
- **JavaFX SDK**: 21.0.4
- **IDE**: Eclipse IDE for Java Developers
- **OS**: Windows 10/11

### How to Run

1. **repository**
git clone https://github.com/Abdulprogrammer/result-verification-system.git

### Running a JavaFX application Prerequisites
-Java Development Kit (JDK) - Java 11 or later
-JavaFX SDK - Download from openjfx.io
### Steps to Run

**Option 1**: Using Command Line (with JavaFX SDK)
bash

# Compile the application
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls src/com/verification/ui/VerificationApp.java

# Run the application
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp src com.verification.ui.VerificationApp

Replace /path/to/javafx-sdk with your actual JavaFX SDK path.

**Option 2**: Using an IDE (IntelliJ IDEA or Eclipse)

### IntelliJ IDEA:
File → Project Structure → Libraries → Add (+) → Java
Select your JavaFX SDK folder
Go to Run → Edit Configurations
Add VM options: --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls
Run the application.

### Eclipse:
Right-click project → Build Path → Configure Build Path.

Add External JARs from JavaFX SDK lib folder.

Add VM arguments in Run Configurations.
