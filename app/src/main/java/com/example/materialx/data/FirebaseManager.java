package com.example.materialx.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class FirebaseManager {
    private static FirebaseManager instance;
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    // Inside FirebaseManager.java
    private FirebaseManager() {
        // Replace with the URL shown in your Firebase Console
        database = FirebaseDatabase.getInstance("https://materialx-6c351eca-default-rtdb.firebaseio.com/");
        auth = FirebaseAuth.getInstance();
    }

    public static synchronized FirebaseManager getInstance() {
        if (instance == null) {
            instance = new FirebaseManager();
        }
        return instance;
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public DatabaseReference getCoursesRef() {
        return database.getReference("courses");
    }

    public DatabaseReference getMaterialsRef() {
        return database.getReference("materials");
    }

    public interface DataCallback<T> {
        void onDataLoaded(List<T> data);
        void onError(String error);
    }

    public void getCourses(String department, int year, int semester, DataCallback<Course> callback) {
        getCoursesRef().orderByChild("department").equalTo(department)
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Course> courses = new ArrayList<>();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Course course = postSnapshot.getValue(Course.class);
                        if (course != null && course.getYear() == year && course.getSemester() == semester) {
                            courses.add(course);
                        }
                    }
                    callback.onDataLoaded(courses);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    callback.onError(error.getMessage());
                }
            });
    }

    public void getMaterials(String courseCode, DataCallback<Material> callback) {
        getMaterialsRef().orderByChild("courseCode").equalTo(courseCode)
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Material> materials = new ArrayList<>();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Material material = postSnapshot.getValue(Material.class);
                        if (material != null) {
                            materials.add(material);
                        }
                    }
                    callback.onDataLoaded(materials);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    callback.onError(error.getMessage());
                }
            });
    }

    // Helper method to upload local data to Firebase (use once to seed)
    public void seedFirebase(List<Course> courses, List<Material> materials) {
        for (Course course : courses) {
            getCoursesRef().child(course.getCode()).setValue(course);
        }
        for (Material material : materials) {
            getMaterialsRef().push().setValue(material);
        }
    }
}
