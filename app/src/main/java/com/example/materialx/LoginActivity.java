package com.example.materialx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.materialx.data.FirebaseManager;
import com.example.materialx.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private boolean isSignUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseManager.getInstance().getAuth();

        // Check if user is already signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            navigateToHome();
        }

        String mode = getIntent().getStringExtra("mode");
        isSignUp = "signup".equals(mode);
        updateUI();

        binding.switchModeButton.setOnClickListener(v -> {
            isSignUp = !isSignUp;
            updateUI();
        });

        binding.submitButton.setOnClickListener(v -> {
            String email = binding.idEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                showError("Please fill all fields");
                return;
            }

            // Simple validation: Firebase requires email format
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showError("Please enter a valid email address");
                return;
            }

            binding.progressBar.setVisibility(View.VISIBLE);
            binding.submitButton.setEnabled(false);

            if (isSignUp) {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        binding.progressBar.setVisibility(View.GONE);
                        binding.submitButton.setEnabled(true);
                        if (task.isSuccessful()) {
                            showSuccess("Registration successful!");
                            navigateToHome();
                        } else {
                            showError("Registration failed: " + task.getException().getMessage());
                        }
                    });
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        binding.progressBar.setVisibility(View.GONE);
                        binding.submitButton.setEnabled(true);
                        if (task.isSuccessful()) {
                            navigateToHome();
                        } else {
                            showError("Authentication failed: " + task.getException().getMessage());
                        }
                    });
            }
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateUI() {
        if (isSignUp) {
            binding.title.setText("Create Account");
            binding.submitButton.setText("Sign Up");
            binding.switchModeButton.setText("Already have an account? Login");
            binding.idEditText.setHint("Email Address");
        } else {
            binding.title.setText("Login");
            binding.submitButton.setText("Sign In");
            binding.switchModeButton.setText("Don't have an account? Sign Up");
            binding.idEditText.setHint("Email Address");
        }
        binding.errorMessage.setVisibility(View.GONE);
    }

    private void showError(String message) {
        binding.errorMessage.setText(message);
        binding.errorMessage.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        binding.errorMessage.setVisibility(View.VISIBLE);
    }

    private void showSuccess(String message) {
        binding.errorMessage.setText(message);
        binding.errorMessage.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        binding.errorMessage.setVisibility(View.VISIBLE);
    }
}
