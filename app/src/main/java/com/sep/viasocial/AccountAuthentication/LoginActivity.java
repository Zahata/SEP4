package com.sep.viasocial.AccountAuthentication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sep.viasocial.MainActivity;
import com.sep.viasocial.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailLoginText;
    private EditText passwordLoginText;
    private Intent profileIntent;
    private Intent notRegisteredIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailLoginText = findViewById(R.id.emailLoginText);
        passwordLoginText = findViewById(R.id.passwordLoginText);

        profileIntent = new Intent(this,MainActivity.class);
        notRegisteredIntent = new Intent(this,RegisterActivity.class);
    }
    public void signInWithEmailAndPassword(){
        String email = emailLoginText.getText().toString().trim();
        String password = passwordLoginText.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(profileIntent);
                }
            }
        });
    }

    public void login(View v){
        signInWithEmailAndPassword();
    }
    public void notRegistered(View v){
        finish();
        startActivity(notRegisteredIntent);
    }

}
