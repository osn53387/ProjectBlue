package com.example.searchprov01;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean progressToMain;
    boolean validTos;

    Button signUp;
    EditText email,password,confirmPassword;
    TextView emailError,passwordError,confirmPassError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agreeToToS();
        toLoginScreen();

        signUp=findViewById(R.id.button);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmpassword);

        //sends alert to user if they have an invalid input
        emailError=findViewById(R.id.emailAlert);
        passwordError=findViewById(R.id.passwordAlert);
        confirmPassError=findViewById(R.id.passwordAlert);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEmail();
                validatePassword();
            }
        });

    }

    /**
     * Takes the input from the user in the email textbox and turns it into a string.
     * Sends error if the textfield is empty or if the email is not valid.
     * @return
     */
    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            emailError.setText("Field can't be empty");
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailError.setText("Invalid Email");
            return false;
        } else {
            emailError.setError(null);
            return true;
        }
    }


    /**
     * Takes the input of the user and turns it into a string.
     * Sends error if textfield is empty,if the password is less than 5 characters,
     * or if the passwords do not match.
     * @return
     */
    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        String ConfitmpasswordInput = confirmPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            passwordError.setText("Field can't be empty");
            return false;
        }  if (passwordInput.length()<5) {
            passwordError.setText("Password must be at least 5 characters");
            return false;
        }
        if (!passwordInput.equals(ConfitmpasswordInput)) {
            confirmPassError.setText("Passwords do not match");
            return false;
        }else {
            confirmPassError.setText("");
            return true;
        }
    }



    private void agreeToToS() {
        TextView hyperLink = (TextView) findViewById(R.id.textView9);
        hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Pop.class));
            }
        });
    }

    private void toLoginScreen() {
        TextView hyperLink = (TextView) findViewById(R.id.textView2);
        hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login_page.class));
            }
        });
    }

}




