package com.example.blood_bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;

public class dbhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Mydata15.db";
    public static final String TABLE_NAME = "Donors";
    public static final String COLOUMN_ID = "id";
    public static final String COLOUMN_NAME = "name";
    public static final String COLOUMN_GENDER = "gender";
    public static final String COLOUMN_EMAIL = "email";
    public static final String COLOUMN_PHONE = "phone";
    public static final String COLOUMN_ADDRESS = "address";
    public static final String COLOUMN_CITY = "city";
    public static final String COLOUMN_BLOODGROUP = "blood_group";
    public static final String COLOUMN_USERNAME = "username";
    public static final String COLOUMN_PASSWORD = "password";

    public static final String TABLE_NAME2 = "Patient";
    //public static final String COLOUMN_PID = "pid";
    public static final String COLOUMN_TYPE = "type";
    public static final String COLOUMN_RELATION = "relation";
    public static final String COLOUMN_PNAME = "pname";
    public static final String COLOUMN_PBGROUP = "pblood_group";
    public static final String COLOUMN_CONTACT = "contact";
    public static final String COLOUMN_CONDITION = "condition";

    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME2 +
                "(type text,relation text,pname text,pblood_group text,contact text,condtion text)"
        );
        db.execSQL("create table " + TABLE_NAME +
                "(id integer ,name text,gender text,email text,phone text,address text,city text,blood_group text,username text,password text)"
        );


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
        //onCreate2(db);
    }

    public boolean insertDon(String name, String gender, String email, String phone, String address, String city, String blood_group, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("gender", gender);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("address", address);
        cv.put("city", city);
        cv.put("blood_group", blood_group);
        cv.put("username", username);
        cv.put("password", password);
        long ins = db.insert("Donors", null, cv);
        if (ins == -1) return false;
        else return true;
    }

    public String insertPatient(String type, String relation, String pname, String pblood_group, String contact, String condition) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("insert into " + TABLE_NAME2 + " values('" + type + "','" + relation + "','" + pname + "','" + pblood_group + "','" + contact + "','" + condition + "')");
            return "yes";
        } catch (Exception ex) {
            return "" + ex;
        }
    }

    public boolean chkusername(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from Donors where username = ? and password = ?", new String[]{username, password});
        if (c.getCount() > 0) return false;
        else return true;
    }

    public boolean updateDon(String name, String gender, String email, String phone, String address, String city, String blood_group, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("gender", gender);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("address", address);
        cv.put("city", city);
        cv.put("blood_group", blood_group);
        cv.put("username", username);
        cv.put("password", password);
        db.update("Donors", cv, "name=?", new String[]{name, gender, email, phone, address, city, blood_group, username, password});
        return true;
    }

    public boolean updatePatient(String type, String relation, String pname, String pblood_group, String contact, String condition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type", type);
        cv.put("relation", relation);
        cv.put("pname", pname);
        cv.put("pblood_group", pblood_group);
        cv.put("contact", contact);
        cv.put("condition", condition);
        db.update("Patient", cv, "name=?", new String[]{type, relation, pname, pblood_group, contact, condition});
        return true;
    }

    public Integer deleteDon(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Donors", "id = ?",
                new String[]{Integer.toString(id)});
    }

    public Integer deletePatient(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Patient", "id = ?",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllDonors(String id) {

        ArrayList<String> array_list = new ArrayList<String>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from Donors where blood_group='" + id + "'", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(COLOUMN_NAME)));
                res.moveToNext();
            }
        } catch (Exception es) {

        }
        return array_list;
    }

    public ArrayList<String> getAllDonors() {

        ArrayList<String> array_list = new ArrayList<String>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from Donors", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(COLOUMN_NAME)));
                res.moveToNext();
            }
        } catch (Exception es) {

        }
        return array_list;
    }

    public ArrayList<String> getAllUsers() {

        ArrayList<String> array_list = new ArrayList<String>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from Patient", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(COLOUMN_PNAME)));
                res.moveToNext();
            }
        } catch (Exception es) {
            array_list.add(es.toString());
        }
        return array_list;
    }

    public Cursor getDon_details(String nm) {

        Cursor c = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            c = db.rawQuery("select * from Donors where name = '" + nm + "'", null);
        }
        catch (Exception e)
        {

        }
        return c;
    }

    public Cursor getUser_details(String nm) {

        Cursor c = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            c = db.rawQuery("select * from Patient where pname = '" + nm + "'", null);
        }
        catch (Exception e)
        {

        }
        return c;
    }

    public Cursor getDon_details1(String nm) {

        Cursor c = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            c = db.rawQuery("select * from Donors where name = '" + nm + "'", null);
        }
        catch (Exception e)
        {

        }
        return c;
    }
}

