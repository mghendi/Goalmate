package com.suluhu.goalmate;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private Button mRegister;
    private CardView mGoToLogin;
    private EditText mEmail, mPassword, mName;

    private RadioGroup mRadioGroup;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    // boolean backPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user !=null){
                    Intent intent = new Intent(RegistrationActivity.this, AssembleActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mRegister = findViewById(R.id.btnSignUp);
        mGoToLogin = findViewById(R.id.cardGoToLogin);
        mName = findViewById(R.id.etUsername);
        mEmail = findViewById(R.id.etEmail);
        mPassword = findViewById(R.id.etPassword);
        mRadioGroup = findViewById(R.id.radioGroup);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectId = mRadioGroup.getCheckedRadioButtonId();
                final RadioButton radioButton = findViewById(selectId);

                if(radioButton.getText() == null) return;

                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                final String name = mName.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "Sign Up Error!", Toast.LENGTH_SHORT).show();
                        }else{
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                            Map userInfo = new HashMap<>();
                            userInfo.put("name", name);
                            userInfo.put("sex", radioButton.getText().toString());
                            userInfo.put("profileImageUrl", "default");
                            currentUserDb.updateChildren(userInfo);
                        }
                    }
                });
            }
        });

        mGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
