package com.example.blooddonar;

import java.util.Vector;

import com.database.blooddonarApp.DatabaseHelper;
import com.database.blooddonarApp.DonarDetails;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ShowDonarDetailsActivity extends Activity{
	private String[] bloodGroup;
	private String[] email;
	private String[] donarName;
	private String[] PhoneNumber;
	private String[] PinCode;
	private int donarCount;
	private Vector<Integer> saveddonars_variable;
	
	
	TextView mShowName;
	TextView mShowBloodGroup;
	TextView mShowPhoneNumber;
	TextView mShowEmailId;
	TextView mDonarcount;
	
	ImageButton mButtonleft;
	ImageButton mButtonRight;
	Button mSaveDetailsButton;
	
	int i=0;
	DatabaseHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdonardetails_activity);
		saveddonars_variable=new Vector<Integer>();
		
		// get the values from the intent which are sent from the FindDoanr class
		Bundle extras=getIntent().getExtras();
		bloodGroup=(String[]) extras.get("bloodgroup");
		email=(String[]) extras.get("email");
		donarName=(String[]) extras.get("donarName");
		PhoneNumber=(String[]) extras.get("phoneNo");
		PinCode=(String[]) extras.get("pinCode");
		donarCount=bloodGroup.length;
		
		Log.d("find count","this is the total count:--"+donarCount);
		
		//initialize the database handler class to perform the operations on the data base
		db=new DatabaseHelper(this, "DONAR_DETAILS", null, 1);

		
//				for(int count=0;count<donarCount;count++)
//				{
//					db.addDoanr(new DonarDetails(donarName[count].toString(),bloodGroup[count].toString(), PhoneNumber[count].toString(), email[count].toString(),PinCode[count].toString()));
//				}
//		
		
		//get the first donar details from parse
		getAllDonarDetails(i);
		
		//references for the left and right buttons
		mButtonleft=(ImageButton)findViewById(R.id.details_leftButton);
		mButtonRight=(ImageButton)findViewById(R.id.details_rightButton);
		mSaveDetailsButton=(Button)findViewById(R.id.saveDonarDetails);
		//listener for the left button
		mButtonleft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//method to perform the operation on button click
				onLeftButtonClicked();
				
				//save button 
				if(mSaveDetailsButton.isEnabled()==false)
				{
					mSaveDetailsButton.setEnabled(true);
				}
			}
		});
		
		
	
		//listener for the right button
		mButtonRight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//method to perform the operation on button click
				onRightButtonClicked();
				
				//save button 
				if(mSaveDetailsButton.isEnabled()==false)
				{	
					mSaveDetailsButton.setEnabled(true);
				}
			}
		});
		
		mSaveDetailsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				
				//insert the donar details into DONAR_DETAILS database
				insertDonarDetails(i);
				saveddonars_variable.add(i);
				if(mSaveDetailsButton.isEnabled()==true)
				{
				mSaveDetailsButton.setEnabled(false);
				}
			}
		});
		
	}
	
	public void onLeftButtonClicked()
	{
		if(i>0)
		{
			i=i-1;
			getAllDonarDetails(i);
		}
	}
	public void onRightButtonClicked()
	{
		if(i<donarCount-1)
		{
			i=i+1;
			getAllDonarDetails(i);
		}
	}
	
	public void insertDonarDetails(int count)
	{
		db.addDoanr(new DonarDetails(donarName[count].toString(),bloodGroup[count].toString(), PhoneNumber[count].toString(), email[count].toString(),PinCode[count].toString()));	
	}
    public void getAllDonarDetails(final int pos)
    {
		Log.d("length", "the length after activty : "+bloodGroup.length);
		
		mShowName=(TextView)findViewById(R.id.details_donarfirstandlastname);
		mShowName.setText(donarName[pos].toString());
		
		mShowBloodGroup=(TextView)findViewById(R.id.details_donarBloodGroup);
		mShowBloodGroup.setText(bloodGroup[pos].toString());
		
		mShowPhoneNumber=(TextView)findViewById(R.id.details_donarPhoneNumber);
		mShowPhoneNumber.setText(PhoneNumber[pos].toString());
		
		mShowPhoneNumber.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				makePhoneCall(PhoneNumber[pos].toString());
			}
		});
		
		
		mShowEmailId=(TextView)findViewById(R.id.details_donarcontactemailId);
		mShowEmailId.setText(email[pos].toString());
		
		mShowEmailId.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   sendEmail(email[pos].toString());	
			}
		});
		
		
		mDonarcount=(TextView)findViewById(R.id.donarCount);
		
		int presentcount=i+1;
		
		mDonarcount.setText(presentcount+"/"+donarCount);
    }

	public void makePhoneCall(String phoneNumber) {
		// TODO Auto-generated method stub
		Intent mPhoneCallIntent=new Intent(Intent.ACTION_CALL);
		mPhoneCallIntent.setData(Uri.parse("tel:"+phoneNumber));
		mPhoneCallIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.startActivity(mPhoneCallIntent);
	}
	
	public void sendEmail(String email)
	{
		Intent mSendEmail=new Intent(Intent.ACTION_SEND);
		mSendEmail.setType("plain/text");
		mSendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
		mSendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, "(Blood Donar) Need Help");

		/* Send it off to the Activity-Chooser */
		this.startActivity(Intent.createChooser(mSendEmail, "Send mail..."));
	}
}
