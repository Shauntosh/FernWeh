package com.project.ShauntoshThapa.fernweh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.project.ShauntoshThapa.fernweh.models.Staff;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlaceListActivity extends AppCompatActivity {

    RecyclerView staff_list;
    FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        staff_list= (RecyclerView) findViewById(R.id.staff_list_recycler);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        staff_list.setHasFixedSize(true);
        staff_list.setLayoutManager(new LinearLayoutManager(this));



        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        String dt = sf.format(new Date());
        Query query= ref.child("expenses").startAt(dt).endAt(dt);

        adapter = new FirebaseRecyclerAdapter<Staff, StaffHolder>(Staff.class, R.layout.row_staff, StaffHolder.class, ref.child("staffs").child(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            @Override
            public void populateViewHolder(final StaffHolder staffHolder, final Staff staff, int position) {
                    staffHolder.name.setText(staff.getName());
                    staffHolder.address.setText(staff.getAddress());
                  staffHolder.designation.setText(staff.getDesignation());

                staffHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        PopupMenu popup = new PopupMenu(PlaceListActivity.this, staffHolder.name);
                        final MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.staff_menu, popup.getMenu());
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case  R.id.del:
                                        adapter.getRef(staffHolder.getAdapterPosition()).setValue(null);
                                        break;
                                    case R.id.edit:
                                        Intent intent = new Intent(getApplicationContext(),AddPlaceActivity.class);
                                        intent.putExtra("staff",staff);
                                        intent.putExtra("id",adapter.getRef(staffHolder.getAdapterPosition()).getKey());
                                        startActivity(intent);
                                        break;
                                }
                                return false;
                            }
                        });
                        popup.show();
                        return false;
                    }
                });
            }
        };
        staff_list.setAdapter(adapter);

    }


    public static class StaffHolder extends RecyclerView.ViewHolder {
        View mView;

        TextView name;
        TextView address;
        TextView designation;
        View view;

        public StaffHolder(View itemView) {
            super(itemView);
            mView = itemView;

            name= (TextView) itemView.findViewById(R.id.tf_destination);
            address= (TextView) itemView.findViewById(R.id.tf_interest);
            designation= (TextView) itemView.findViewById(R.id.tf_bungee);
            view= itemView.findViewById(R.id.row_staff);

        }

    }
}
