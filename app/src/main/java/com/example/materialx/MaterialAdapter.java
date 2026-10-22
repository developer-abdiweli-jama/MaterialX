package com.example.materialx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.materialx.data.Material;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {

    public interface OnMaterialActionListener {
        void onView(Material material);

        void onDownload(Material material);
    }

    private List<Material> materials;
    private OnMaterialActionListener listener;

    public MaterialAdapter(List<Material> materials, OnMaterialActionListener listener) {
        this.materials = materials;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Material m = materials.get(position);
        holder.name.setText(m.getName());
        holder.meta.setText(m.getType().name() + " • " + m.getUploadDate());

        if (m.getType() == Material.Type.PDF) {
            holder.icon.setImageResource(R.drawable.ic_pdf);
        } else if (m.getType() == Material.Type.PPT) {
            holder.icon.setImageResource(R.drawable.ic_ppt);
        } else {
            holder.icon.setImageResource(R.drawable.ic_file);
        }

        holder.viewBtn.setOnClickListener(v -> listener.onView(m));
        holder.downloadBtn.setOnClickListener(v -> listener.onDownload(m));
    }

    @Override
    public int getItemCount() {
        return materials.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, meta;
        View viewBtn, downloadBtn;
        android.widget.ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.fileName);
            meta = itemView.findViewById(R.id.fileMeta);
            viewBtn = itemView.findViewById(R.id.viewButton);
            downloadBtn = itemView.findViewById(R.id.downloadButton);
            icon = itemView.findViewById(R.id.fileIcon);
        }
    }

}
