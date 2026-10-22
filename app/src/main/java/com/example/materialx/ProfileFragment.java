package com.example.materialx;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.materialx.data.DataMigrationManager;
import com.example.materialx.data.FirebaseManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        
        TextView emailText = view.findViewById(R.id.profile_email);
        Button logoutButton = view.findViewById(R.id.logout_button);
        Button migrateButton = view.findViewById(R.id.migrate_button);
        
        FirebaseAuth auth = FirebaseManager.getInstance().getAuth();
        FirebaseUser user = auth.getCurrentUser();
        
        if (user != null) {
            emailText.setText(user.getEmail());
        } else {
            emailText.setText("Not signed in");
        }
        
        logoutButton.setOnClickListener(v -> {
            auth.signOut();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        
        migrateButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Starting migration...", Toast.LENGTH_SHORT).show();
            DataMigrationManager.migrateToFirebase(getContext());
        });
        
        return view;
    }
}
