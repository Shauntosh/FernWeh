package com.project.ShauntoshThapa.fernweh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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

public class SigninActivity extends AppCompatActivity {
    EditText username, password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();

        username = (EditText) findViewById(R.id.tf_username);
        password = (EditText) findViewById(R.id.tf_password);

        progressDialog = new ProgressDialog(SigninActivity.this);
        progressDialog.setMessage("Logging in...");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {


                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

                }

            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void login(View view) {



            if(TextUtils.isEmpty(username.getText().toString())){
                username.setError("Please..enter your email");
                return;
            }
        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("Please..enter your password");
            return;
        }

        String un = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(un, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SigninActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                                } else {

                                    SharedPreferences preferences= getSharedPreferences("settings",MODE_PRIVATE);
                                    final SharedPreferences.Editor edit = preferences.edit();
                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                                    ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("user_type").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            System.out.println(dataSnapshot.toString());
                                            edit.putString("user_type",dataSnapshot.getValue().toString());
                                            edit.apply();
                                            System.out.println("user type:"+dataSnapshot.getValue().toString());
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }

                            }

                        }
                );
    }


    public void signup(View view) {


        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }

    public void forgotpassword(View view) {
        startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
    }

}





