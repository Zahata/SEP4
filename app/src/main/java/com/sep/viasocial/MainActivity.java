package com.sep.viasocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.sep.viasocial.AccountAuthentication.LoginActivity;
import com.sep.viasocial.AccountAuthentication.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private Intent goToRegister;
    private Intent goToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToRegister = new Intent(MainActivity.this,RegisterActivity.class);
        goToLogin = new Intent(MainActivity.this, LoginActivity.class);

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
