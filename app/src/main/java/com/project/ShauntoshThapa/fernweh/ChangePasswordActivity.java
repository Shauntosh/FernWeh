package com.project.ShauntoshThapa.fernweh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText newPass;
    ProgressDialog progressDialog;
    private Button btnChange;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        newPass = (EditText) findViewById(R.id.newpassword);
        btnChange = (Button) findViewById(R.id.btnchangepassword);
        progressDialog = new ProgressDialog(ChangePasswordActivity.this);
        progressDialog.setMessage("Please wait...");
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (TextUtils.isEmpty(newPass.getText().toString().trim())) {
                    Toast.makeText(getApplication(), "Password cannot be empty", Toast.LENGTH_SHORT).show();

                }else {


                //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                progressDialog.show();
                user.updatePassword(newPass.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(ChangePasswordActivity.this, "Password is changed successfully!", Toast.LENGTH_SHORT).show();
                                    //FirebaseAuth.getInstance().signOut();
                                    //finish();
                                    //startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                                } else {
                                    Toast.makeText(ChangePasswordActivity.this, "Failed to change password!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));

                                }
                            }
                        });

        }
        }});

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
}
