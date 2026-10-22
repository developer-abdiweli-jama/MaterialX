package com.example.materialx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.materialx.data.Course;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    public interface OnCourseClickListener {
        void onCourseClick(Course course);
    }

    private List<Course> courses;
    private OnCourseClickListener listener;

    public CourseAdapter(List<Course> courses, OnCourseClickListener listener) {
        this.courses = courses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.code.setText(course.getCode());
        holder.name.setText(course.getName());

        // Consistent with Material View, allow click on item or explicit button
        View.OnClickListener clickListener = v -> listener.onCourseClick(course);
        holder.itemView.setOnClickListener(clickListener);
        holder.openBtn.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView code, name;
        View openBtn;
        android.widget.ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            code = itemView.findViewById(R.id.courseCode);
            name = itemView.findViewById(R.id.courseName);
            openBtn = itemView.findViewById(R.id.openButton);
            icon = itemView.findViewById(R.id.courseIcon);
        }
    }

}
