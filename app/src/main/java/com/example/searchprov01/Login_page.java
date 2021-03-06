package com.example.searchprov01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Login_page extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText emailInput, passwordInput;
    TextView toRegister;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();

        emailInput = findViewById(R.id.editTextTextEmailAddress);
        passwordInput = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.button2);

        toRegister = findViewById(R.id.BackToRegister);

        registerHyperLink();

        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmail();
                validatePassword();
                checkUser();
            }
        });

    }

    private void validateEmail() {
        String email = emailInput.getText().toString().trim();
        if (email.isEmpty()) {
            emailInput.setError("Email is missing");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Invalid Email");
            return;
            }
        }

    private void validatePassword() {
        String password = passwordInput.getText().toString().trim();
        if (password.isEmpty()) {
            passwordInput.setText("Field can't be empty");
            return;
        }
        if (password.length() < 5) {
            passwordInput.setText("Password must be at least 5 characters");
            return;
        }
    }

    private void registerHyperLink() {
        toRegister.setMovementMethod(LinkMovementMethod.getInstance());
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_page.this, MainActivity.class));
            }
        });
    }

    private void checkUser() {
        String realEmail, realPassword;
        realEmail = emailInput.getText().toString().trim();
        realPassword = passwordInput.getText().toString().trim();
        if (realEmail.isEmpty() || realPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(realEmail, realPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login_page.this, MainScreen.class));
                    finish();
                } else {
                    Toast.makeText(Login_page.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}



