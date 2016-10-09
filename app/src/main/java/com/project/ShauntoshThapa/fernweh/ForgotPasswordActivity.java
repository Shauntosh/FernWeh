package com.project.ShauntoshThapa.fernweh;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.text.TextUtils;
        import android.view.Menu;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        inputEmail = (EditText) findViewById(R.id.tf_forgotpassword1);
        btnReset = (Button) findViewById(R.id.btnforgetpassword);
        progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
        progressDialog.setMessage("Please wait...");

        auth = FirebaseAuth.getInstance();











        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Please, enter your registered email", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.show();
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPasswordActivity.this, "Password reset link has been sent to your email", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                                } else {
                                    Toast.makeText(ForgotPasswordActivity.this, "Error! Only enter registered email address", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
}

