package com.example.materialx;

import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.materialx.databinding.ActivityViewerBinding;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ViewerActivity extends AppCompatActivity {

    private ActivityViewerBinding binding;
    private PdfRenderer pdfRenderer;
    private ParcelFileDescriptor parcelFileDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String fileName = getIntent().getStringExtra("fileName");
        String filePath = getIntent().getStringExtra("filePath");

        binding.viewerToolbar.setTitle(fileName);
        binding.viewerToolbar.setNavigationOnClickListener(v -> finish());

        if (filePath != null) {
            setupPdfRenderer(filePath);
        } else {
            Toast.makeText(this, "Error: File path is missing", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupPdfRenderer(String filePath) {
        try {
            // 1. Copy asset to temp file (PdfRenderer needs a descriptor)
            File tempFile = new File(getCacheDir(), "temp_viewer.pdf");
            try (InputStream in = getAssets().open(filePath)) {
                try (FileOutputStream out = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[8192];
                    int read;
                    while ((read = in.read(buffer)) != -1) {
                        out.write(buffer, 0, read);
                    }
                }
            }

            // 2. Open Descriptor
            parcelFileDescriptor = ParcelFileDescriptor.open(tempFile, ParcelFileDescriptor.MODE_READ_ONLY);

            // 3. Initialize Renderer
            pdfRenderer = new PdfRenderer(parcelFileDescriptor);

            // 4. Setup RecyclerView
            binding.pdfRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.pdfRecyclerView.setAdapter(new PdfPageAdapter(pdfRenderer));

        } catch (IOException e) {
            Toast.makeText(this, "Failed to load PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (pdfRenderer != null)
                pdfRenderer.close();
            if (parcelFileDescriptor != null)
                parcelFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
