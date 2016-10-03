package com.project.ShauntoshThapa.fernweh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.project.ShauntoshThapa.fernweh.models.Trip;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TripListActivity extends AppCompatActivity {

    RecyclerView trip_list;
    FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        trip_list = (RecyclerView) findViewById(R.id.trip_list_recycler);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        trip_list.setHasFixedSize(true);
        trip_list.setLayoutManager(new LinearLayoutManager(this));


        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        String dt = sf.format(new Date());
        Query query = ref.child("expenses").startAt(dt).endAt(dt);

        adapter = new FirebaseRecyclerAdapter<Trip, TripListActivity.TripHolder>(Trip.class, R.layout.row_trip, TripListActivity.TripHolder.class, ref.child("trips").child(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            @Override
            public void populateViewHolder(final TripListActivity.TripHolder tripHolder, final Trip trip, int position) {
                tripHolder.destination.setText(trip.getDestination());
                tripHolder.interest.setText(trip.getInterest());
                tripHolder.bungee.setText(trip.getBungee());
                tripHolder.rafting.setText(trip.getRafting());
                tripHolder.canyoing.setText(trip.getCanyoing());
                tripHolder.hiking.setText(trip.getHiking());
                tripHolder.paragliding.setText(trip.getParagliding());
                tripHolder.cycling.setText(trip.getCycling());
                tripHolder.kids.setText(trip.getKids());
                tripHolder.adults.setText(trip.getAdults());
                tripHolder.budget.setText(trip.getBudget());
                tripHolder.duration.setText(trip.getDuration());

                tripHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        PopupMenu popup = new PopupMenu(TripListActivity.this, tripHolder.destination);
                        PopupMenu popup1 = new PopupMenu(TripListActivity.this, tripHolder.interest);
                        final MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.staff_menu, popup.getMenu());
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.del:
                                        adapter.getRef(tripHolder.getAdapterPosition()).setValue(null);
                                        break;
                                    case R.id.edit:
                                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                                        intent.putExtra("trip", trip);
                                        intent.putExtra("id", adapter.getRef(tripHolder.getAdapterPosition()).getKey());
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
        trip_list.setAdapter(adapter);
    }
    public static class TripHolder extends RecyclerView.ViewHolder {
        View mView;

        TextView destination,interest,bungee,rafting,canyoing,hiking,paragliding,cycling,duration,kids,adults,budget ;

        View view;

        public TripHolder(View itemView) {
            super(itemView);
            mView = itemView;

            destination= (TextView) itemView.findViewById(R.id.tf_destination);
            bungee= (TextView) itemView.findViewById(R.id.tf_bungee);
            interest= (TextView) itemView.findViewById(R.id.tf_interest);
            rafting= (TextView) itemView.findViewById(R.id.tf_rafting);
            canyoing= (TextView) itemView.findViewById(R.id.tf_canyoing);
            hiking= (TextView) itemView.findViewById(R.id.tf_hiking);
            paragliding= (TextView) itemView.findViewById(R.id.tf_paragliding);
            cycling= (TextView) itemView.findViewById(R.id.tf_cycling);
            duration= (TextView) itemView.findViewById(R.id.tf_duration);
            kids= (TextView) itemView.findViewById(R.id.tf_kids);
            adults= (TextView) itemView.findViewById(R.id.tf_adults);
            budget= (TextView) itemView.findViewById(R.id.tf_budget);
            view= itemView.findViewById(R.id.row_trip);

        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


    }

