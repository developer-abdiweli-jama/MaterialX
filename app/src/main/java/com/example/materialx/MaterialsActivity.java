package com.example.materialx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.materialx.data.FirebaseManager;
import com.example.materialx.data.Material;
import com.example.materialx.databinding.ActivityMaterialsBinding;
import com.google.android.material.tabs.TabLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MaterialsActivity extends AppCompatActivity {

    private ActivityMaterialsBinding binding;
    private String courseCode;
    private List<Material> allMaterials = new ArrayList<>();

    private int currentTab = 0;
    private String currentSearchQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMaterialsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        courseCode = getIntent().getStringExtra("courseCode");
        String courseName = getIntent().getStringExtra("courseName");

        binding.toolbar.setTitle(courseName != null ? courseName : "Course Materials");
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.materialsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadMaterials();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab = tab.getPosition();
                applyFilters();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentSearchQuery = query;
                applyFilters();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentSearchQuery = newText;
                applyFilters();
                return true;
            }
        });
    }

    private void loadMaterials() {
        binding.progressBar.setVisibility(View.VISIBLE);
        FirebaseManager.getInstance().getMaterials(courseCode, new FirebaseManager.DataCallback<Material>() {
            @Override
            public void onDataLoaded(List<Material> data) {
                binding.progressBar.setVisibility(View.GONE);
                allMaterials = data;
                applyFilters();
            }

            @Override
            public void onError(String error) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(MaterialsActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void applyFilters() {
        List<Material> filtered = new ArrayList<>();
        String query = currentSearchQuery.toLowerCase().trim();

        for (Material m : allMaterials) {
            boolean matchesTab = false;
            if (currentTab == 0)
                matchesTab = true;
            else if (currentTab == 1 && m.getType() == Material.Type.PPT)
                matchesTab = true;
            else if (currentTab == 2 && m.getType() == Material.Type.PDF)
                matchesTab = true;

            boolean matchesSearch = m.getName().toLowerCase().contains(query);

            if (matchesTab && matchesSearch) {
                filtered.add(m);
            }
        }

        updateUI(filtered);
    }

    private void updateUI(List<Material> list) {
        if (list.isEmpty() && !allMaterials.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.materialsRecyclerView.setVisibility(View.GONE);
        } else if (!allMaterials.isEmpty()){
            binding.emptyState.setVisibility(View.GONE);
            binding.materialsRecyclerView.setVisibility(View.VISIBLE);
        } else if (allMaterials.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.materialsRecyclerView.setVisibility(View.GONE);
        }
        updateAdapter(list);
    }

    private void updateAdapter(List<Material> list) {
        binding.materialsRecyclerView
                .setAdapter(new MaterialAdapter(list, new MaterialAdapter.OnMaterialActionListener() {
                    @Override
                    public void onView(Material material) {
                        if (material.getType() == Material.Type.PDF) {
                            Intent intent = new Intent(MaterialsActivity.this, ViewerActivity.class);
                            intent.putExtra("fileName", material.getName());
                            intent.putExtra("filePath", material.getFilePath());
                            startActivity(intent);
                        } else {
                            downloadAndOpenFile(material);
                        }
                    }

                    @Override
                    public void onDownload(Material material) {
                        downloadAndOpenFile(material);
                    }
                }));
    }

    private void downloadAndOpenFile(Material material) {
        // Implementation remains same for local assets or could be updated for Firebase Storage
        // For now, keeping it as is to avoid breaking local file logic if paths are relative
        Toast.makeText(this, "Opening: " + material.getName(), Toast.LENGTH_SHORT).show();
    }
}
