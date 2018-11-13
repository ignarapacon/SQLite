package com.rapacon.igna.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "pupils.db";
    final static int VER = 1;
    final static String TABLE = "points";

    //cursor factory - specific set of table contents

    public DBHelper(Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE points (ID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT, Lname TEXT, Point INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS points";
        db.execSQL(dropTable);
        onCreate(db);
    }

    public boolean insert(String fname, String lname, int point) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fname", fname);
        values.put("Lname", lname);
        values.put("Point", point);
        long isInserted = db.insert(TABLE, null, values);

        if (isInserted == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor selectRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM points",null);
    }

    public boolean update(String id, String fname, String lname, int point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Fname", fname);
        cv.put("Lname", lname);
        cv.put("Point", point);

        db.update(TABLE, cv,"ID =?", new String[] {id});
        return true;

    }

    public void delete (String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE,"ID=?", new String[] {id});




    }
}
