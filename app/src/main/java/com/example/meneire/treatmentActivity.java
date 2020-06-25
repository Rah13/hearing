package com.example.meneire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class treatmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);


        Button nextBtn = (Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(getApplicationContext(),vertigoActivity.class);
                // pass info
                startActivity(nextIntent);
            }
        });

        Button backBtn = (Button)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(),MainActivity.class);
                // pass info
                startActivity(backIntent);
            }
        });



        Spinner treatmentsSpinner = findViewById(R.id.treatmentSpinner);
        ArrayAdapter<CharSequence> treatmentsAdapter = ArrayAdapter.createFromResource(this,R.array.treatmentOptions,android.R.layout.simple_spinner_item);
        treatmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        treatmentsSpinner.setAdapter(treatmentsAdapter);
        treatmentsSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


