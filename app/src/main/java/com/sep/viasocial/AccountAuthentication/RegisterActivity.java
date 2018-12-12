package com.sep.viasocial.AccountAuthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sep.viasocial.R;
import com.sep.viasocial.SetupProfile;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailText;
    private EditText passwordText;
    private Intent setupProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        setupProfile = new Intent(this, SetupProfile.class);
    }

    public void register(View v){
        String txt_email = emailText.getText().toString();
        String txt_password = passwordText.getText().toString();

        if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
            Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else if (txt_password.length() < 6 ){
            Toast.makeText(RegisterActivity.this, "password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        } else {
            createUserWithEmailAndPassword(txt_email, txt_password);
        }
    }

    public void createUserWithEmailAndPassword(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"You have registered successfully",Toast.LENGTH_SHORT).show();
                    setupProfile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(setupProfile);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Registration unsuccessful, please try again!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
