package com.example.mahmoud.sanaye.activtiy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahmoud.sanaye.Constants;
import com.example.mahmoud.sanaye.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRegisteration extends AppCompatActivity {

    EditText ed_name, ed_phone, ed_Id_national, edOtherLocation, edMessure, edPrice, edPriceOutLocation;
    Spinner jobSpinner, locationSpinner;
    HashMap<String, String> map;
    DatabaseReference database;
    List<String> jobs = new ArrayList<>();
    List<String> areas = new ArrayList<>();
    String jobSelected;
    String locationSelected;
    int jobPosition = 0;
    int locationPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registeration);

        // bindviews
        // main informations
        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);
        ed_Id_national = findViewById(R.id.ed_id_national);
        // work inforamtions
        jobSpinner = findViewById(R.id.spinner_job_employee);
        locationSpinner = findViewById(R.id.spinner_location_employee);
        edOtherLocation = findViewById(R.id.other_locations);
        // payment information
        edMessure = findViewById(R.id.ed_messure);
        edPrice = findViewById(R.id.ed_price);
        edPriceOutLocation = findViewById(R.id.ed_price_out_location);


        ed_phone.setText(Constants.phoneNumber);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("jobs").addValueEventListener(new ValueEventListener() {
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

                ArrayAdapter<String> jobssAdapter = new ArrayAdapter<String>(UserRegisteration.this, android.R.layout.simple_spinner_item, jobs) {

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
        database.child("area").addValueEventListener(new ValueEventListener() {
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

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(UserRegisteration.this, android.R.layout.simple_spinner_item, areas) {

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


    public void UserDone(View view) {

        String name, phone, id;
        name = ed_name.getText().toString();
        phone = ed_phone.getText().toString();
        id = ed_Id_national.getText().toString();


        if (name.equals("") && name.length() == 0) {
            Toast.makeText(UserRegisteration.this, "من فضلك ادخل اسمك", Toast.LENGTH_LONG).show();
        } else if (phone.equals("") && phone.length() == 0) {
            Toast.makeText(UserRegisteration.this, "من فضلك ادخل رقم التلفون", Toast.LENGTH_LONG).show();
        } else if (id.length() != 14) {
            Toast.makeText(UserRegisteration.this, "من فضلك ادخل الرقم القومي", Toast.LENGTH_LONG).show();
        } else if (jobPosition == 0) {
            Toast.makeText(UserRegisteration.this, "من فضلك اختار الوظيفه", Toast.LENGTH_LONG).show();
        } else if (locationPosition == 0 && edOtherLocation.getText().toString().isEmpty()) {
            Toast.makeText(UserRegisteration.this, "من فضلك اختار المكان او ادخل مكان اخر", Toast.LENGTH_LONG).show();
        } else if (locationPosition == 0 && !edOtherLocation.getText().toString().isEmpty()) {

            locationSelected = edOtherLocation.getText().toString();
            map = new HashMap<>();
            map.put("name", String.valueOf(name));
            map.put("phone", String.valueOf(phone));
            map.put("idNatoinal", String.valueOf(id));
            map.put("job", String.valueOf(jobSelected));
            map.put("locationSelected", String.valueOf(locationSelected));
            database.child("user").child(String.valueOf(ed_Id_national.getText())).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isComplete()) {
                        Intent intent = new Intent(UserRegisteration.this, EmployeeDone.class);
                        startActivity(intent);
                        finish();
                    }

                }
            });

        } else {

            map = new HashMap<>();
            map.put("name", String.valueOf(name));
            map.put("phone", String.valueOf(phone));
            map.put("idNatoinal", String.valueOf(id));
            map.put("job", String.valueOf(jobSelected));
            map.put("locationSelected", String.valueOf(locationSelected));
            database.child("user").child(String.valueOf(ed_Id_national.getText())).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isComplete()) {
                        Intent intent = new Intent(UserRegisteration.this, EmployeeDone.class);
                        startActivity(intent);
                        finish();
                    }

                }
            });


        }

    }

}
