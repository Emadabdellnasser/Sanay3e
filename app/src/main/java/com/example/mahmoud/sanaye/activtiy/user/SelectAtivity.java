package com.example.mahmoud.sanaye.activtiy.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmoud.sanaye.AdapterItems;
import com.example.mahmoud.sanaye.EmployeeModel;
import com.example.mahmoud.sanaye.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectAtivity extends AppCompatActivity {
    public static final String TAG = "SelectAtivity";

    Spinner locationSpinner, jobSpinner;
    List<String> jobs = new ArrayList<>();
    List<String> areas = new ArrayList<>();
    List<EmployeeModel> employeeModels;
    String jobSelected;
    String locationSelected;
    int jobPosition = 0;
    int locationPosition = 0;
    DatabaseReference rootRef;

    RecyclerView recyclerView;
    AdapterItems adapter;

    RecyclerView.LayoutManager manager;
    TextView searchResult;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ativity);

        MobileAds.initialize(this, "ca-app-pub-4797650798439827~1471409016");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);


        locationSpinner = findViewById(R.id.user_location);
        jobSpinner = findViewById(R.id.user_job);
        searchResult=findViewById(R.id.search_result);


        rootRef = FirebaseDatabase.getInstance().getReference();

        // set up the RecyclerView
         recyclerView = findViewById(R.id.reycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        rootRef.child("jobs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                jobs = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String jobName = areaSnapshot.getKey();
                    jobs.add(jobName);
                    Log.e("city", " " + jobName);
                }

                ArrayAdapter<String> jobssAdapter = new ArrayAdapter<String>(SelectAtivity.this, android.R.layout.simple_spinner_item, jobs) {

                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
                    }


                };
                jobssAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                jobSpinner.setAdapter(jobssAdapter);


                jobSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItemText = (String) parent.getItemAtPosition(position);
                        // If user change the default selection
                        // First item is disable and it is used for hint
                        if (position > 0) {
                            // Notify the selected item text
                            jobSelected = selectedItemText;
                            jobPosition = position;
                            Toast.makeText
                                    (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        rootRef.child("area").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Is better to use a List, because you don't know the size
                // of the iterator returned by dataSnapshot.getChildren() to
                // initialize the array
                areas = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.getKey();
                    areas.add(areaName);
                    Log.e("areas", " " + areaName);
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(SelectAtivity.this, android.R.layout.simple_spinner_item, areas) {

                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
                    }


                };
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                locationSpinner.setAdapter(areasAdapter);


                locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItemText = (String) parent.getItemAtPosition(position);
                        // If user change the default selection
                        // First item is disable and it is used for hint
                        if (position > 0) {
                            // Notify the selected item text
                            locationSelected = selectedItemText;
                            locationPosition = position;

                            Toast.makeText
                                    (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void search(View view) {

        if (jobPosition == 0) {
            Toast.makeText(SelectAtivity.this, "ختار التخصص", Toast.LENGTH_LONG).show();
        } else if (locationPosition == 0) {
            Toast.makeText(SelectAtivity.this, "ختار المكان", Toast.LENGTH_LONG).show();
        } else {

            Query query = rootRef.child("user").orderByChild("job").equalTo(jobSelected);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    employeeModels = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));
                        String locationName = (String) snapshot.child("locationSelected").getValue();

                        if (locationName.equals(locationSelected)) {

                            String job = (String) snapshot.child("job").getValue();
                            String phone = (String) snapshot.child("phone").getValue();
                            String name = (String) snapshot.child("name").getValue();

                            EmployeeModel model = new EmployeeModel(locationName, job, phone, name);
                            employeeModels.add(model);

                        }



                    }

//
                    if (employeeModels.size()!=0){

                        recyclerView.setVisibility(View.VISIBLE);
                        searchResult.setVisibility(View.GONE);
                        adapter = new AdapterItems(employeeModels, SelectAtivity.this);
                        recyclerView.setAdapter(adapter);

                    }else {
                        recyclerView.setVisibility(View.GONE);
                        searchResult.setVisibility(View.VISIBLE);

                        // show no one
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    throw databaseError.toException();
                }
            });


        }


    }

}
