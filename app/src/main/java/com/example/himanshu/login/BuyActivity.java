package com.example.himanshu.login;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.widget.AdapterView.OnItemSelectedListener;

public class BuyActivity extends Activity implements OnItemSelectedListener{

    private Button btnLogin1;
    String item1;
    String item2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        btnLogin1 = (Button) findViewById(R.id.btnLogin);
        // Spinner element
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> department = new ArrayList<String>();
        department.add("Information Science");
        department.add("Computer Science");
        department.add("Mechanical");
        department.add("Electronics");
        department.add("Electrical");
        department.add("Civil");
        department.add("Biotechnology");
        department.add("Telecommunication");

        List<String> semester = new ArrayList<String>();
        semester.add("First");
        semester.add("Second");
        semester.add("Third");
        semester.add("Fourth");
        semester.add("Fifth");
        semester.add("Sixth");
        semester.add("Seventh");
        semester.add("Eighth");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, department);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semester);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);
        spinner2.setAdapter(dataAdapter2);

        btnLogin1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(BuyActivity.this, MainActivity.class);
                i.putExtra("get_department", item1);
                i.putExtra("get_semester", item2);
                startActivity(i);
            }
        });

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch(parent.getId())
        {
            case R.id.spinner1:
                // On selecting a spinner item
                 item1 = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item1, Toast.LENGTH_LONG).show();
                break;
            case R.id.spinner2:
                // On selecting a spinner item
                item2 = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item2, Toast.LENGTH_LONG).show();
                break;
        }


    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "You selected nothing", Toast.LENGTH_LONG).show();

    }



}
