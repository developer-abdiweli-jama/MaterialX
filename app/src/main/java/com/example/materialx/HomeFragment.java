package com.example.materialx;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        MaterialCardView cardSwe = view.findViewById(R.id.card_swe);
        MaterialCardView cardCs = view.findViewById(R.id.card_cs);
        MaterialCardView cardEe = view.findViewById(R.id.card_ee);
        MaterialCardView cardMe = view.findViewById(R.id.card_me);
        MaterialCardView cardCe = view.findViewById(R.id.card_ce);
        
        cardSwe.setOnClickListener(v -> showYearSemesterDialog("SWE"));
        cardCs.setOnClickListener(v -> showYearSemesterDialog("CS"));
        cardEe.setOnClickListener(v -> showYearSemesterDialog("EE"));
        cardMe.setOnClickListener(v -> showYearSemesterDialog("ME"));
        cardCe.setOnClickListener(v -> showYearSemesterDialog("CE"));
        
        return view;
    }
    
    private void showYearSemesterDialog(String department) {
        if (getContext() == null) return;
        
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_year_semester_selection, null);
        dialog.setContentView(dialogView);
        
        Spinner spinnerYear = dialogView.findViewById(R.id.spinner_year);
        Spinner spinnerSemester = dialogView.findViewById(R.id.spinner_semester);
        MaterialButton btnContinue = dialogView.findViewById(R.id.btn_continue);
        
        // Setup Year Spinner (Years 2, 3, 4, 5)
        String[] years = {"Year 2", "Year 3", "Year 4", "Year 5"};
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, years);
        spinnerYear.setAdapter(yearAdapter);
        
        // Setup Semester Spinner (Semesters 1, 2)
        String[] semesters = {"Semester 1", "Semester 2"};
        ArrayAdapter<String> semAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, semesters);
        spinnerSemester.setAdapter(semAdapter);
        
        btnContinue.setOnClickListener(v -> {
            int selectedYear = spinnerYear.getSelectedItemPosition() + 2; // Position 0 -> Year 2
            int selectedSemester = spinnerSemester.getSelectedItemPosition() + 1; // Position 0 -> Sem 1
            
            dialog.dismiss();
            
            Intent intent = new Intent(getActivity(), CourseListActivity.class);
            intent.putExtra("department", department);
            intent.putExtra("year", selectedYear);
            intent.putExtra("semester", selectedSemester);
            startActivity(intent);
        });
        
        dialog.show();
    }
}
