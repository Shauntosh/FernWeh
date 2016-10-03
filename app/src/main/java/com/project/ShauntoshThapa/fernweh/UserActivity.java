package com.project.ShauntoshThapa.fernweh;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ShauntoshThapa.fernweh.models.Trip;
import com.project.ShauntoshThapa.fernweh.models.User;

import static com.project.ShauntoshThapa.fernweh.R.id.cbbungee;
import static com.project.ShauntoshThapa.fernweh.R.id.editText2;
import static com.project.ShauntoshThapa.fernweh.R.id.editText5;
import static com.project.ShauntoshThapa.fernweh.R.id.fill;
import static com.project.ShauntoshThapa.fernweh.R.id.name;
import static com.project.ShauntoshThapa.fernweh.R.id.place_autocomplete_fragment;
import static com.project.ShauntoshThapa.fernweh.R.id.place_autocomplete_powered_by_google;
import static com.project.ShauntoshThapa.fernweh.R.id.spinner_adults;
import static com.project.ShauntoshThapa.fernweh.R.id.spinner_duration;
import static com.project.ShauntoshThapa.fernweh.R.id.spinner_interests;
import static com.project.ShauntoshThapa.fernweh.R.id.tfDestination;


public class UserActivity extends AppCompatActivity {

    CheckBox rafting,bungee,canyoing,hiking,paragliding,cycling;
   Spinner interests,duration,kids,adults, budget;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialogUser;
    TextView package1,tfDestination;
    Trip trip;
    String key;
    String place;
    Spinner spinnerIntrest,spinnerDuration,spinnnerKids,spinnerAdults,spinnerBudget;
    PlaceAutocompleteFragment autocomplete;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        interests = (Spinner) findViewById(R.id.spinner_interests);
        duration = (Spinner) findViewById(R.id.spinner_duration);
        kids = (Spinner) findViewById(R.id.spinner_kids);
        adults = (Spinner) findViewById(R.id.spinner_adults);
        budget = (Spinner) findViewById(R.id.spinner_budget);
        rafting = (CheckBox) findViewById(R.id.cbrafting);
        bungee = (CheckBox) findViewById(R.id.cbbungee);
        canyoing = (CheckBox) findViewById(R.id.cbcanyoing);
        hiking = (CheckBox) findViewById(R.id.cbhiking);
        paragliding = (CheckBox) findViewById(R.id.cbparagliding);
        cycling = (CheckBox) findViewById(R.id.cbcycling);
        trip = (Trip) getIntent().getSerializableExtra("trip");

        mDatabase= FirebaseDatabase.getInstance().getReference();

        key = getIntent().getStringExtra("id");

        mAuth = FirebaseAuth.getInstance();
        progressDialogUser = new ProgressDialog(UserActivity.this);
        progressDialogUser.setMessage("Your Trip is planned..Please Wait");
        progressDialogUser.setTitle("Thank You !!!");


         spinnerIntrest = (Spinner) findViewById(R.id.spinner_interests);
        bungee= (CheckBox) findViewById(R.id.cbbungee);
        rafting= (CheckBox) findViewById(R.id.cbrafting);
        canyoing= (CheckBox) findViewById(R.id.cbcanyoing);
        hiking= (CheckBox) findViewById(R.id.cbhiking);
        paragliding= (CheckBox) findViewById(R.id.cbparagliding);
        cycling= (CheckBox) findViewById(R.id.cbcycling);
        package1= (TextView) findViewById(R.id.editText5);
        tfDestination =(TextView) findViewById(R.id.tfDestination) ;
        autocomplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.interests_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
       // spinner.setPrompt("Interests");
       // spinner.setAdapter(adapter);


        spinnerDuration= (Spinner) findViewById(R.id.spinner_duration);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.NoOfDays_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerDuration.setPrompt("Interests");
        spinnerDuration.setAdapter(adapter1);


        spinnnerKids = (Spinner) findViewById(R.id.spinner_kids);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterKids = ArrayAdapter.createFromResource(this,
                R.array.NoOfKids_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterKids.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnnerKids.setPrompt("Interests");
        spinnnerKids.setAdapter(adapterKids);

        spinnerAdults = (Spinner) findViewById(R.id.spinner_adults);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterAdult = ArrayAdapter.createFromResource(this,
                R.array.NoOfAdults_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterAdult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerAdults.setPrompt("Interests");
        spinnerAdults.setAdapter(adapterAdult);

        spinnerBudget = (Spinner) findViewById(R.id.spinner_budget);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterBudget = ArrayAdapter.createFromResource(this,
                R.array.budget_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterBudget.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerBudget.setPrompt("Interests");
        spinnerBudget.setAdapter(adapterBudget);



        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setHint("Choose Destination");

        if (!autocompleteFragment.isAdded()) {
            tfDestination.setError("Please, choose your destination");
            return;}




        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place p) {
                place= p.getName().toString();


            }

            @Override
            public void onError (Status status) {
                tfDestination.setError("Please Select place");

            }







        });



    }

    public void addTrip(View view) {

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        //package1.setVisibility(view.getVisibility());

        if (!bungee.isChecked()&& (!rafting.isChecked()) && (!canyoing.isChecked())&&(!hiking.isChecked())
                &&(!paragliding.isChecked())&&(!cycling.isChecked())){
            package1.setError("Please, select atleast one recreational package");
            return;
        }

       // if (automcomplete.is){

        //}







        Trip trip = new Trip();

        trip.setInterest(spinnerIntrest.getSelectedItem().toString());
        trip.setDuration(spinnerDuration.getSelectedItem().toString());
        trip.setKids(spinnnerKids.getSelectedItem().toString());
        trip.setAdults(spinnerAdults.getSelectedItem().toString());
        trip.setBudget(spinnerBudget.getSelectedItem().toString());


        if(bungee.isChecked()) {
            trip.setBungee("Done");
        }else {
            trip.setBungee("Undone");

        }

        if(rafting.isChecked()){
            trip.setRafting("Done");
        }else {
            trip.setRafting("Undone");

        }

        if(canyoing.isChecked()){
            trip.setCanyoing("Done");
        }else {
            trip.setCanyoing("Undone");

        }

        if(hiking.isChecked()){
            trip.setHiking("Done");
        }else {
            trip.setHiking("Undone");

        }

        if(paragliding.isChecked()){
            trip.setParagliding("Done");
        }else {
            trip.setParagliding("Undone");

        }

        if(cycling.isChecked()){
            trip.setCycling("Done");
        }else {
            trip.setCycling("Undone");

        }




        trip.setDestination(place);

        mDatabase.child("trips").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .push().setValue(trip);

        Toast.makeText(UserActivity.this, "Congratulations! Your Trip has been planned.", Toast.LENGTH_SHORT).show();





        startActivity(new Intent(getApplicationContext(),TripListActivity.class));
        finish();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







    @Override
    public void onBackPressed() {
        super.onBackPressed();



     

  }}




