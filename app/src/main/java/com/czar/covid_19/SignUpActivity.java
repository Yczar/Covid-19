package com.czar.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText etfirst_name, et_last_name, etmail,etcc, etphone, etcountry, etstate, etpass, etconfpass;
    private Button signup;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        etfirst_name = findViewById(R.id.first_name);
        et_last_name = findViewById(R.id.last_name);
        etmail = findViewById(R.id.etmaill);
        etcc = findViewById(R.id.country_code);
        etphone = findViewById(R.id.phone_number);
        etcountry = findViewById(R.id.country);
        etstate = findViewById(R.id.et_state);
        etpass = findViewById(R.id.etPass);
        etconfpass = findViewById(R.id.etconfPass);
        signup = findViewById(R.id.signup_btn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp(etmail.getText().toString().trim(), etpass.getText().toString().trim(),
                        etconfpass.getText().toString().trim(), etstate.getText().toString().trim(),
                        etcc.getText().toString().trim(), etphone.getText().toString().trim(),
                        etcountry.getText().toString().trim());
            }
        });
    }

    private void SignUp(String email, String password, String confPassword, String state, String cc, String phone, String country) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if (email.isEmpty()){
            etmail.setError("Email Address is empty");
            etmail.requestFocus();
        }else if (password.isEmpty()){
            etpass.setError("Enter a password");
            etpass.requestFocus();

        } else if (confPassword.isEmpty()) {
            progressDialog.dismiss();
            etconfpass.setError("Confirm your Password");
            etconfpass.requestFocus();

        }else if (state.isEmpty()){
            progressDialog.dismiss();
            etstate.setError("Enter your state or province");
            etmail.requestFocus();

        }else  if (cc.isEmpty()){
            progressDialog.dismiss();
            etcc.setError("Kindly enter your country code");
            etcc.requestFocus();

        }else if (phone.isEmpty()){
            progressDialog.dismiss();
            etphone.setError("Kindly Enter your phone number");
            etphone.requestFocus();

        }else if (country.isEmpty()){
            progressDialog.dismiss();
            etcountry.setError("Please Enter your country");
            etcountry.requestFocus();

        }else if (password.length() < 6){
            progressDialog.dismiss();
            Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();

        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, "Authentication  Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
