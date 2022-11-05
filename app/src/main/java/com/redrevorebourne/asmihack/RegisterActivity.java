package com.redrevorebourne.asmihack;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.security.auth.login.LoginException;

public class RegisterActivity extends Activity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    private TextView emailbox;
    private TextView passwordbox;
    private TextView name;
    private TextView phoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailbox = findViewById(R.id.emailBoxRegister);
        passwordbox = findViewById(R.id.passwordBoxRegister);
        name = findViewById(R.id.nameBoxRegister);
        phoneNumber = findViewById(R.id.phoneBoxRegister);

        Button registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener(view -> {
            createAccount(emailbox.getText().toString(), passwordbox.getText().toString(),
                    name.getText().toString(), phoneNumber.getText().toString());
        });

        TextView moveToLogin = findViewById(R.id.moveToLogin);

        moveToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

            startActivity(intent);
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void reload() { }

    private void createAccount(String email, String password, String phoneNumber, String name) {
        if (email.equals("") || password.equals("") || phoneNumber.equals("") || name.equals("")) {
            Toast.makeText(RegisterActivity.this, "Account failed to register.",
                    Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(RegisterActivity.this, "Account failed to register.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            });
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

            startActivity(intent);
            finish();
        }
    }
}
