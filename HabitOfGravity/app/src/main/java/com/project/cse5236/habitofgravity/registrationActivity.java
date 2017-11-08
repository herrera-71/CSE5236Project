package com.project.cse5236.habitofgravity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class registrationActivity extends AppCompatActivity {
    private Button registrationButton;
    private TextView nameTextView, emailTextView, usernameTextView, passwordTextView;
    private String name, username, email, password;
    private DatabaseReference ref;
    private DatabaseReference postRef;
    private DatabaseReference newPostRef;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();

        //Initialize text views
        emailTextView = (TextView) findViewById(R.id.RegisterEmail);
        passwordTextView = (TextView) findViewById(R.id.RegisterPassword);

        //Initialize button
        registrationButton = (Button) findViewById(R.id.RegistrationButton);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void registerUser(){
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailTextView.setError("Enter Email!");
            emailTextView.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailTextView.setError("Please enter a valid email");
            emailTextView.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordTextView.setError("Enter Password!");
            passwordTextView.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordTextView.setError("Password must be at least 6 characters");
            passwordTextView.requestFocus();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(registrationActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            postRef = FirebaseDatabase.getInstance().getReference().child("Users").push();
                            postRef.setValue(new UserStoredData(auth.getUid().toString(), "0", "0", "0"));
                            Intent intent = new Intent(registrationActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(registrationActivity.this, "Failed Registration", Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }

}
