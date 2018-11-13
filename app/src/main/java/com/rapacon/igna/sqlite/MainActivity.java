package com.rapacon.igna.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    EditText eFname, eLname, ePoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        eFname = findViewById(R.id.etFname);
        eLname = findViewById(R.id.etLname);
        ePoints = findViewById(R.id.etPoints);
    }

    public void insertRecord (View v){

        String firstname = eFname.getText().toString().trim();
        String lastname = eLname.getText().toString().trim();
        int points = Integer.parseInt(ePoints.getText().toString());
        boolean isInserted = helper.insert(firstname, lastname, points);

        if(isInserted == true){
            Toast.makeText(this, "RECORD SAVED", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "RECORD SAVED FAILED", Toast.LENGTH_LONG).show();
        }

    }


}
