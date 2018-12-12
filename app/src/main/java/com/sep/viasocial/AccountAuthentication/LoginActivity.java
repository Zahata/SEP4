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
import com.sep.viasocial.MainActivity;
import com.sep.viasocial.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailLoginText;
    private EditText passwordLoginText;
    private Intent mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailLoginText = findViewById(R.id.emailLoginText);
        passwordLoginText = findViewById(R.id.passwordLoginText);

        mainActivity = new Intent(this,MainActivity.class);
    }

    public void login(View v){
        String txt_email = emailLoginText.getText().toString();
        String txt_password = passwordLoginText.getText().toString();

        if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
            Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else {
            signInWithEmailAndPassword(txt_email, txt_password);
        }
    }

    public void signInWithEmailAndPassword(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(mainActivity);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
