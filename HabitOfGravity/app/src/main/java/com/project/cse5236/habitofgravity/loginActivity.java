package com.project.cse5236.habitofgravity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private DatabaseReference ref;
    private DatabaseReference usersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        info = (TextView)findViewById(R.id.registrationInfo);
        loginButton = (LoginButton) findViewById(R.id.FB_login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                startActivity(intent);
            }


            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });


        Button registrationButton = (Button) findViewById(R.id.Register);
        registrationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Non FB login

                //New user info
                EditText usernameEditText = (EditText) findViewById(R.id.Username);
                final String username = usernameEditText.getText().toString();
                EditText passwordEditText = (EditText) findViewById(R.id.Password);
                final String password = passwordEditText.getText().toString();
                User newUser = new User(username, password);

                //Firebase Ref
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference postRef = userRef.push();
                String uniqueId = postRef.getKey();
                Map<String, String> userInformation = new HashMap<>();
                userInformation.put("Username", username);
                userInformation.put("Password", password);
                postRef.setValue(userInformation);
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void cacheLoginInfo(View view){
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("")
    }

}
