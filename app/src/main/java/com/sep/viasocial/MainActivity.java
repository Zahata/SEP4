package com.sep.viasocial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sep.viasocial.AccountAuthentication.LoginActivity;
import com.sep.viasocial.AccountAuthentication.RegisterActivity;
import com.sep.viasocial.Chat.ChatActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Intent sendToChat;
    private Intent goToRegister;
    private Intent goToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        sendToChat = new Intent(MainActivity.this,ChatActivity.class);

        goToRegister = new Intent(MainActivity.this,RegisterActivity.class);
        goToLogin = new Intent(MainActivity.this, LoginActivity.class);

    }
    /*@Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            finish();
            startActivity(sendToChat);
        }
    }*/
    public void goToRegister(View v){
        finish();
        startActivity(goToRegister);
    }
    public void goToLogin(View v){
        finish();
        startActivity(goToLogin);
    }
}
