package com.example.materialx;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PdfPageAdapter extends RecyclerView.Adapter<PdfPageAdapter.ViewHolder> {

    private PdfRenderer renderer;

    public PdfPageAdapter(PdfRenderer renderer) {
        this.renderer = renderer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pdf_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PdfRenderer.Page page = renderer.openPage(position);

        // Create a bitmap with the size of the page
        Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);

        // Render the page into the bitmap
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

        holder.imageView.setImageBitmap(bitmap);

        page.close();
    }

    @Override
    public int getItemCount() {
        return renderer != null ? renderer.getPageCount() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pdfPageImage);
        }
    }
}
