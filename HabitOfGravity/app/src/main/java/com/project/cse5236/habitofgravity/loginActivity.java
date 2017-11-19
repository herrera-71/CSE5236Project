package com.project.cse5236.habitofgravity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView info;
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;
    private Button loginButton, registrationButton;
    private EditText emailEditText, passwordEditText;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private DatabaseReference postRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Firebase Authorization Instance
        auth = FirebaseAuth.getInstance();

        /*
        if (auth.getCurrentUser() != null) {
            goToMainActivity();
        }
        */

        //Facebook button
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        facebookLoginButton = (LoginButton) findViewById(R.id.FB_login_button);
        facebookLoginButton.setReadPermissions("email", "public_profile");
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }


            @Override
            public void onCancel() {
                return;
            }

            @Override
            public void onError(FacebookException e) {
                return;
            }
        });

        //Firebase Authorization Listener
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if(user != null){
                    goToMainActivity();
                }
            }
        };

        //Email edit text
        emailEditText = (EditText) findViewById(R.id.LoginEmail);

        //Password edit text
        passwordEditText = (EditText) findViewById(R.id.LoginPassword);

        //Registration button
        registrationButton = (Button) findViewById(R.id.Register);
        registrationButton.setOnClickListener(this);

        //Login button
        loginButton = (Button) findViewById(R.id.Login);
        loginButton.setOnClickListener(this);

        //Offline login
        Button offlineLoginButton = (Button) findViewById(R.id.OfflineLogin);
        offlineLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                LoggedInOnline.getInstance().LoggedIn = false;
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Error message
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error: Incorrect Login Credentials", Toast.LENGTH_LONG).show();
                }
                else{
                    postRef = FirebaseDatabase.getInstance().getReference().child("Users").push();
                    postRef.setValue(new UserStoredData(auth.getUid().toString(), "0", "0", "0"));
                    goToMainActivity();
                }
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.removeAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.Register:
                goToRegistrationActivity();
                break;
            case R.id.Login:
                //Extract email and password
                String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                //Check for valid email and password
                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Enter Email!");
                    emailEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Enter Password!");
                    passwordEditText.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    passwordEditText.setError("Password must be at least 6 characters");
                    passwordEditText.requestFocus();
                    return;
                }

                //Authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    goToMainActivity();
                                }
                                else {
                                    Toast.makeText(loginActivity.this, "Login Error: Incorrect Login Credentials", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;
        }
    }

    private void goToMainActivity(){
        Intent intent = new Intent(loginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToRegistrationActivity(){
        Intent intent = new Intent(loginActivity.this, registrationActivity.class);
        startActivity(intent);
        finish();
    }

}
