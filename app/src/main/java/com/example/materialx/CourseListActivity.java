package com.example.materialx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import com.example.materialx.data.Course;
import com.example.materialx.data.FirebaseManager;
import com.example.materialx.databinding.ActivityCourseListBinding;
import java.util.List;
import java.util.ArrayList;

public class CourseListActivity extends AppCompatActivity {

    private ActivityCourseListBinding binding;
    private List<Course> allCourses = new ArrayList<>();
    private String department;
    private int year, semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        department = getIntent().getStringExtra("department");
        year = getIntent().getIntExtra("year", 1);
        semester = getIntent().getIntExtra("semester", 1);

        binding.toolbar.setTitle("Engineering · Year " + year);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.courseRecyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));

        loadCourses();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCourses(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCourses(newText);
                return true;
            }
        });
    }

    private void loadCourses() {
        binding.progressBar.setVisibility(View.VISIBLE);
        FirebaseManager.getInstance().getCourses(department, year, semester, new FirebaseManager.DataCallback<Course>() {
            @Override
            public void onDataLoaded(List<Course> data) {
                binding.progressBar.setVisibility(View.GONE);
                allCourses = data;
                if (allCourses.isEmpty()) {
                    binding.emptyState.setVisibility(View.VISIBLE);
                    binding.courseRecyclerView.setVisibility(View.GONE);
                } else {
                    binding.emptyState.setVisibility(View.GONE);
                    binding.courseRecyclerView.setVisibility(View.VISIBLE);
                    updateAdapter(allCourses);
                }
            }

            @Override
            public void onError(String error) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(CourseListActivity.this, "Error loading courses: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterCourses(String query) {
        String q = query.toLowerCase().trim();
        List<Course> filtered = new ArrayList<>();
        for (Course c : allCourses) {
            if (c.getName().toLowerCase().contains(q) || c.getCode().toLowerCase().contains(q)) {
                filtered.add(c);
            }
        }

        if (filtered.isEmpty() && !allCourses.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.courseRecyclerView.setVisibility(View.GONE);
        } else if (!allCourses.isEmpty()) {
            binding.emptyState.setVisibility(View.GONE);
            binding.courseRecyclerView.setVisibility(View.VISIBLE);
        }

        updateAdapter(filtered);
    }

    private void updateAdapter(List<Course> courses) {
        binding.courseRecyclerView.setAdapter(new CourseAdapter(courses, course -> {
            Intent intent = new Intent(CourseListActivity.this, MaterialsActivity.class);
            intent.putExtra("courseCode", course.getCode());
            intent.putExtra("courseName", course.getName());
            startActivity(intent);
        }));
    }
}
