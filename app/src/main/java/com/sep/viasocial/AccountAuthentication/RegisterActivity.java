package com.sep.viasocial.AccountAuthentication;

import android.content.Intent;
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
import com.sep.viasocial.Chat.ChatActivity;
import com.sep.viasocial.MainActivity;
import com.sep.viasocial.R;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailText;
    private EditText passwordText;
   // private EditText nameText;
    private Intent chatIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        //nameText = findViewById(R.id.nameText);

        chatIntent = new Intent(this, ChatActivity.class);
    }
    public void createUserWithEmailAndPassword(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"You have registered successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(chatIntent);
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Registration unsuccessful, please try again!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void register(View v){
       /* String name = nameText.getText().toString();*/
        createUserWithEmailAndPassword();
        }

}
