package com.project.ShauntoshThapa.fernweh;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailAddress = (EditText) findViewById(R.id.tf_forgotpassword1);
    }

    public void forgetpassword (View view){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String email = emailAddress.getText().toString().trim(); ;

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPasswordActivity.this, "Password reset link has been sent to your Email.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),SigninActivity.class));
                        }
                    }
                });


    }
}
