package com.rapacon.igna.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    EditText eFname, eLname, ePoints;
    Cursor res;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        eFname = findViewById(R.id.etFname);
        eLname = findViewById(R.id.etLname);
        ePoints = findViewById(R.id.etPoints);

        res = helper.selectRecords();
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

    public void moveFirst (View v){

        res.moveToFirst();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);

        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);
    }

    public void moveLast (View v){

        res.moveToLast();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);

        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);
    }

    public void movePrevious (View v){

        res.moveToPrevious();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);

        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);

    }

    public void moveNext (View v){

        res.moveToNext();
        String fname = res.getString(1);
        String lname = res.getString(2);
        String point = res.getString(3);

        eFname.setText(fname);
        eLname.setText(lname);
        ePoints.setText(point);

    }

    public void editRecord (View v) {
        String fname = eFname.getText().toString().trim();
        String lname = eLname.getText().toString().trim();
        int points = Integer.parseInt(ePoints.getText().toString());

        //Toast.makeText(this, " " + res.getPosition(),Toast.LENGTH_LONG).show();

        int id = res.getPosition() +1;

        boolean isUpdated = helper.update("" + id, fname,lname,points );

        if(isUpdated == true){
            Toast.makeText(this, "RECORD UPDATED", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "RECORD UPDATE FAILED", Toast.LENGTH_LONG).show();
        }
}
    public void deleteRecord (View v) {
        String id = res.getString(0);

        Integer countRow = helper.delete("" + id);

        if (countRow == 1) {
            Toast.makeText(this, "DELETED 1 ROW", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "DELETED FAILED", Toast.LENGTH_LONG).show();
        }

    }



}
