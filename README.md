# MaterialX

Student Learning Materials App

## Description

MaterialX is an Android application designed for students of Arba Minch General Hospital to access and manage educational learning materials. The app organizes course materials by department, year, and semester, providing easy access to lecture notes, slides, PDFs, and presentations.

## Features

- **Course & Material Browser**: Browse courses and materials by department, year, and semester
- **PDF/PPT Viewer**: View lecture slides and documents directly in the app
- **Firebase Integration**: User authentication and real-time database for course and material data
- **Search**: Find materials quickly across all courses
- **Offline Support**: Access materials stored locally on the device
- **Organized by Discipline**: Electrical Engineering and Software Engineering materials

## Tech Stack

- **Language**: Java
- **SDK**: Android (minSdk 24, targetSdk 36, compileSdk 36)
- **Build**: Gradle (AGP 8.13.2)
- **Backend**: Firebase Authentication, Firebase Realtime Database, Firebase Storage
- **UI**: Material Design, ViewBinding, RecyclerView

## Project Structure

```
app/src/main/java/com/example/materialx/
├── activities/          - Home, Login, CourseList, Materials, Viewer
├── fragments/           - Home, Profile, Search
├── adapters/            - MaterialAdapter, CourseAdapter, PdfPageAdapter
├── data/                - FirebaseManager, DatabaseHelper, Data models (Course, Material)
└── MainActivity.java    - Entry point
```

## Getting Started

### Prerequisites

- Android Studio (latest version recommended)
- Android SDK (API 24+)
- Java 11+
- A Firebase project

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/developer-abdiweli-jama/MaterialX.git
   ```

2. Open in Android Studio

3. Add your Firebase configuration:
   - Create a Firebase project at https://console.firebase.google.com
   - Register your Android app with package name `com.example.materialx`
   - Download `google-services.json` and place it in `app/`

4. Sync Gradle and build the project:
   ```bash
   ./gradlew assembleDebug
   ```

## Firebase Setup

The app uses Firebase for:
- **Authentication**: User login and registration
- **Realtime Database**: Course and material metadata
- **Storage**: File hosting for PDFs and presentations

Configure `FirebaseManager.java` with your Firebase Realtime Database URL.

## Repository Details

**Description**: Android app for Arba Minch General Hospital students to browse and access course materials organized by department, year, and semester, with Firebase authentication and real-time database integration.

**Topics**: android, java, firebase, education, student-materials, arba-minch, hospital

## License

This project is under progress.
