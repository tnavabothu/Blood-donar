package com.example.blooddonar;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.network.status.DetectInternetConnectivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegistrationActivity extends Activity {
	
	EditText _firstNameTextField,_lastNameTextField,_phoneNumberTextField,_emailIdTextField,_pincodeTextField;
	Button   _registerDonarButton;
	RadioGroup _radioGroup;
	RadioButton _radioButton;
	int _selectedRadioButton;
	 ProgressDialog mProgressDialog;
	 String _mTimeStamp;
	 SimpleDateFormat mSimpleDateFormat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		//time stamp 
		mSimpleDateFormat=new SimpleDateFormat("yyyyMMddhhmmss");
		String _getDateAndTime=mSimpleDateFormat.format(new Date());
		
		//to get milliseconds
		Long mMilliSeconds = System.currentTimeMillis()/1000;
		String _getMillisonds = mMilliSeconds.toString();
		_mTimeStamp=_getDateAndTime+_getMillisonds;
		
		Toast.makeText(this, ""+_mTimeStamp,Toast.LENGTH_LONG).show();
		
		Log.d("Time Stamp", _mTimeStamp);
		
		mProgressDialog=new ProgressDialog(this);
		mProgressDialog.setTitle("Loading");
		mProgressDialog.setMessage("Please Wait...");
		mProgressDialog.setCanceledOnTouchOutside(false);
		/*
		 * textfield references
		 */
		_firstNameTextField=(EditText)findViewById(R.id._first_name);
		_lastNameTextField=(EditText)findViewById(R.id._last_name);
		_phoneNumberTextField=(EditText)findViewById(R.id._phone_number);
		_emailIdTextField=(EditText)findViewById(R.id._email_id);
		_pincodeTextField=(EditText)findViewById(R.id._pincode);
		
		/*
		 * registration button reference
		 */
		_registerDonarButton=(Button)findViewById(R.id._registerDetailsButton);
		
		
		/*
		 * radio group
		 */
		_radioGroup=(RadioGroup)findViewById(R.id._bloodGroup);
		
	
		
	     /*
	      * registration button
	      */
		_registerDonarButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DetectInternetConnectivity cd=new DetectInternetConnectivity(getApplicationContext());
				if(cd.isConnectingToInternet())
				{
				mProgressDialog.show();
				_selectedRadioButton=_radioGroup.getCheckedRadioButtonId();
				_radioButton=(RadioButton)findViewById(_selectedRadioButton);
				RegistrationValidation mRegistrationValidation=new RegistrationValidation();
				if(_selectedRadioButton==-1||_firstNameTextField.getText().length()==0||_lastNameTextField.getText().length()==0||_phoneNumberTextField.getText().length()==0||_emailIdTextField.getText().length()==0||_pincodeTextField.getText().length()==0)
				{
					Toast.makeText(getApplicationContext(), "Fields should not be empty",Toast.LENGTH_SHORT).show();
					mProgressDialog.dismiss();
				}
				else
				{
				mRegistrationValidation.checkOnRegistrationClicked(mProgressDialog,getApplicationContext(),_firstNameTextField.getText().toString(),_lastNameTextField.getText().toString(),_phoneNumberTextField.getText().toString(),_emailIdTextField.getText().toString(),_pincodeTextField.getText().toString(),_radioButton.getText().toString(),_mTimeStamp);
				//mProgressDialog.dismiss();
				}
			}
				else {
					Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
				}
			}
		});
		

	}
}
