/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.example.himanshu.login;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_LOGIN = "login";
    private static final String TABLE_SYLLABUS = "syllabus";
    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_SUBJECTS = "subjects";

    // Login Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USN= "USN";
    private static final String KEY_CONTACT= "contact";
    private static final String KEY_DEPARTMENT = "department";
    private static final String KEY_SEMESTER= "semester";

    //Syllabus Table Columns names
    private static final String KEY_DEPARTMENT1 = "department";
    private static final String KEY_SEMESTER1 = "semester";
    private static final String KEY_LINK = "link";

    //Books Table Column Names
    private static final String KEY_BOOKAUTHOR = "bookauthor";
    private static final String KEY_BOOKNAME = "bookname";
    private static final String KEY_BOOKSUBJECT = "booksubject";
    private static final String KEY_BOOKPRICE = "bookprice";
    private static final String KEY_SELLERNAME = "name";
    private static final String KEY_SEMESTER1 = "contact";

    //Subjects Table Column Names
    private static final String KEY_DEPARTMENT2 = "department";
    private static final String KEY_SUBJECT = "subject";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                +  KEY_NAME + " TEXT,"	+ KEY_EMAIL + " TEXT UNIQUE," + KEY_PASSWORD + "TEXT, " + KEY_USN + " TEXT PRIMARY KEY,"
                + KEY_CONTACT + " TEXT," + KEY_DEPARTMENT + "TEXT," + KEY_SEMESTER + " TEXT ," + ")";
        db.execSQL(CREATE_LOGIN_TABLE);


        String CREATE_SYLLABUS_TABLE = " CREATE TABLE" + TABLE_SYLLABUS + "("
                + KEY_DEPARTMENT1 +" TEXT, " + KEY_SEMESTER1 + " TEXT, " + KEY_LINK + " TEXT, " + ")";
        db.execSQL(CREATE_SYLLABUS_TABLE);


        String CREATE_BOOKS_TABLE = " CREATE TABLE " + TABLE_BOOKS + "("
                + KEY_BOOKNAME + " TEXT,"	+ KEY_BOOKAUTHOR + " TEXT UNIQUE," + KEY_SUBJECT + "TEXT, " + KEY_BOOKPRICE + " TEXT,"
                + KEY_SELLERNAME + " TEXT," + KEY_SEMESTER1 + "TEXT," + ")";
        db.execSQL(CREATE_BOOKS_TABLE);


        String CREATE_SUBJECTS_TABLE = " CREATE TABLE" + TABLE_SUBJECTS + "("
                + KEY_DEPARTMENT2 +" TEXT, " + KEY_SUBJECT + " TEXT, " + ")";
        db.execSQL(CREATE_SUBJECTS_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYLLABUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String name, String email, String password, String USN, String contact, String department, String semester) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_PASSWORD, password); // Email
        values.put(KEY_USN, USN); // USN
        values.put(KEY_CONTACT, contact); // contact
        values.put(KEY_DEPARTMENT, department); // department
        value.put(KEY_SEMESTER, semester);  //semester

        // Inserting Row
        long id = db.insert(TABLE_LOGIN, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("password", cursor.getString(3));
            user.put("USN", cursor.getString(4));
            user.put("contact", cursor.getString(5));
            user.put("department", cursor.getString(6));
            user.put("semester", cursor.getString(7));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Getting user login status return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.delete(TABLE_SYLLABUS, null, null);
        db.delete(TABLE_BOOKS, null, null);
        db.delete(TABLE_SUBJECTS, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
