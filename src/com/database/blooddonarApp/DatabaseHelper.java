package com.database.blooddonarApp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	//static variables
	
	//column names of DonarDetails Table
	
	private static final String DONAR_NAME="DonarName";               
	private static final String DONAR_BLOODGROUP="DonarBloodGroup";   
	private static final String DONAR_PHONENUMBER="DonarPhoneNumber";
	private static final String DONAR_EMAIL="Donaremail";
	private static final String DONAR_PINCODE="DonarDonarpincode";
	
	//name of the table
	private static final String TABLE_NAME="DONARDETAILS";
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_DONARDETAILS_TABLE="CREATE TABLE "+ TABLE_NAME +"("+ DONAR_NAME +" TEXT, "+ DONAR_BLOODGROUP +" TEXT, "+ DONAR_PHONENUMBER +" TEXT, "+ DONAR_EMAIL +" TEXT, "+ DONAR_PINCODE +" TEXT "+")";
		Log.d("create query:", CREATE_DONARDETAILS_TABLE);
		db.execSQL(CREATE_DONARDETAILS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
 
        // Create tables again
        onCreate(db);
	}
	
	
	/*
	 * operations on the database to retrive the doanr details
	 * 
	 */
	 // Adding new contact
   public void addDoanr(DonarDetails mDonarDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(DONAR_NAME, mDonarDetails.getName()); // Donar Name
        values.put(DONAR_BLOODGROUP, mDonarDetails.getBloodgroup()); // Donar BloodGroup
        values.put(DONAR_PHONENUMBER,mDonarDetails.getPhoneNumber());
        values.put(DONAR_EMAIL, mDonarDetails.getEmail());
        values.put(DONAR_PINCODE, mDonarDetails.getPincode());
 
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
 
     
    // Getting All Contacts
    public List<DonarDetails> getAllContacts() {
        List<DonarDetails> contactList = new ArrayList<DonarDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DonarDetails donarDetails = new DonarDetails();
                donarDetails.setName(cursor.getString(0));
                donarDetails.setBloodgroup(cursor.getString(1));
                donarDetails.setPhoneNumber(cursor.getString(2));
                donarDetails.setEmail(cursor.getString(3));
                donarDetails.setPincode(cursor.getString(4));
                // Adding contact to list
                contactList.add(donarDetails);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
  
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
	
}
