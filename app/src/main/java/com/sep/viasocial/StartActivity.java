package com.sep.viasocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.sep.viasocial.AccountAuthentication.LoginActivity;
import com.sep.viasocial.AccountAuthentication.RegisterActivity;

public class StartActivity extends AppCompatActivity {

    private Intent goToRegister;
    private Intent goToLogin;
    private Intent goToMainActivity;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is null
        if (firebaseUser != null){
            Intent goToMainActivity = new Intent(StartActivity.this, MainActivity.class);
            startActivity(goToMainActivity);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        goToRegister = new Intent(StartActivity.this,RegisterActivity.class);
        goToLogin = new Intent(StartActivity.this, LoginActivity.class);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public void goToRegister(View v){
        startActivity(goToRegister);
        finish();
    }
    public void goToLogin(View v){
        startActivity(goToLogin);
        finish();
    }
}
