package com.example.materialx.data;

import android.content.Context;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import java.util.List;

public class DataMigrationManager {
    private static final String TAG = "DataMigrationManager";

    public static void migrateToFirebase(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        FirebaseManager firebaseManager = FirebaseManager.getInstance();

        Log.d(TAG, "Starting migration...");

        // Migrate Courses
        // We need a way to get ALL courses from DatabaseHelper. 
        // Let's assume we fetch them by iterating through known filters or adding a getAllCourses method.
        // For simplicity, let's migrate by department/year/semester combinations known in the seeder.
        
        String[] departments = {"SWE", "EE", "CS", "ME", "CE"};
        int[] years = {1, 2, 3, 4};
        int[] semesters = {1, 2};

        for (String dept : departments) {
            for (int year : years) {
                for (int sem : semesters) {
                    List<Course> courses = dbHelper.getCourses(dept, year, sem);
                    if (!courses.isEmpty()) {
                        for (Course course : courses) {
                            firebaseManager.getCoursesRef().child(course.getCode()).setValue(course)
                                .addOnSuccessListener(aVoid -> {
                                    Log.d(TAG, "Migrated course: " + course.getCode());
                                    // After course is migrated, migrate its materials
                                    migrateMaterialsForCourse(dbHelper, firebaseManager, course.getCode());
                                })
                                .addOnFailureListener(e -> Log.e(TAG, "Failed to migrate course: " + course.getCode(), e));
                        }
                    }
                }
            }
        }
    }

    private static void migrateMaterialsForCourse(DatabaseHelper dbHelper, FirebaseManager firebaseManager, String courseCode) {
        List<Material> materials = dbHelper.getMaterials(courseCode);
        for (Material material : materials) {
            // Use a unique key for materials to avoid duplicates, or push()
            // Using courseCode + materialName as a simple unique-ish key for seeding
            String materialKey = (courseCode + "_" + material.getName()).replaceAll("[^a-zA-Z0-9]", "_");
            firebaseManager.getMaterialsRef().child(materialKey).setValue(material)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Migrated material: " + material.getName()))
                .addOnFailureListener(e -> Log.e(TAG, "Failed to migrate material: " + material.getName(), e));
        }
    }
}
