package com.project.ShauntoshThapa.fernweh;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ShauntoshThapa.fernweh.models.Staff;

public class AddPlaceActivity extends AppCompatActivity {

    TextInputEditText name;

    private DatabaseReference mDatabase;

    Staff staff;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        name= (TextInputEditText) findViewById(R.id.txtName);

        staff= (Staff) getIntent().getSerializableExtra("staff");

        if(staff!=null){
            name.setText(staff.getName());
        }
        key= getIntent().getStringExtra("id");
    }

    public void addStaff(View view){
        String n= name.getText().toString();

        Staff s = new Staff(n);

        if(staff!=null){
            mDatabase.child("staffs").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key).setValue(s);
            Toast.makeText(AddPlaceActivity.this, "Edited successfully", Toast.LENGTH_SHORT).show();
        }else {

            mDatabase.child("staffs").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(s);
            Toast.makeText(AddPlaceActivity.this, "added successfully", Toast.LENGTH_SHORT).show();
        }

        finish();

    }
}
