package com.example.blooddonar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class RegistrationValidation extends Activity {
	private String _mFirstName,_mLastName,_mPhoneNumber,_mEmailId,_mPincode,_mRadioButtonSelected,_mTimeStamp;
	Pattern pattern;
	Matcher matcher;
	Activity mActivity=new Activity();
	//ProgressDialog mProgressDialog;
	public void checkOnRegistrationClicked(final ProgressDialog mProgressDialog,final Context mApplicaContext,String mFirstname,String mLastname,String mPhonenumber,String mEmailId,String mPincode,String mRadioButtonSelected,String mTimeStamp)
	{	
	_mFirstName=mFirstname;
	_mLastName=mLastname;
	_mPhoneNumber=mPhonenumber;
	_mEmailId=mEmailId;
	_mPincode=mPincode;
	_mRadioButtonSelected=mRadioButtonSelected;
	_mTimeStamp=mTimeStamp;
       
	if(isEmailValid(_mEmailId)==false)
	{
		Toast.makeText(mApplicaContext, "Invalid Email",Toast.LENGTH_LONG).show();
	}
	else if(isPhoneNumberVAlid(_mPhoneNumber)==false)
	{
		Toast.makeText(mApplicaContext, "Invalid Phone Number",Toast.LENGTH_LONG).show();
	}
	else
	{
		ParseObject mParseDonarDetails=new ParseObject("DonarDetails");
		mParseDonarDetails.put("FirstName",_mFirstName);
		mParseDonarDetails.put("LastName", _mLastName);
		mParseDonarDetails.put("PhoneNumber", _mPhoneNumber);
		mParseDonarDetails.put("Email", _mEmailId);
		mParseDonarDetails.put("Pincode", _mPincode);
		mParseDonarDetails.put("BloodGroup", _mRadioButtonSelected);
		mParseDonarDetails.put("TimeStamp", _mTimeStamp);
		mParseDonarDetails.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				// TODO Auto-generated method stub
				if(e==null)
				{
					
					//mProgressDialog.dismiss();
					//Intent i=new Intent(mApplicaContext, MainActivity.class);
					//startActivity(i);
					mProgressDialog.dismiss();
					Toast.makeText(mApplicaContext, "Registered Successfully", Toast.LENGTH_SHORT).show();
					mActivity.finish();
				}
				else
				{
					//mProgressDialog.dismiss();
					Toast.makeText(mApplicaContext, "Registration Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
	
	}
	
	//method to check the email validation
	public boolean isEmailValid(String email)
	{
		 String regExpn =
                 "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                     +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                       +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                       +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                       +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                       +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

         CharSequence inputStr = email;

         pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
         matcher = pattern.matcher(inputStr);
         if(matcher.matches())
          return true;
         else
		return false;
	}
	
	//method to check the phone number validation
	public boolean isPhoneNumberVAlid(String phoneno)
	{
		if(phoneno.length()>0&&phoneno.length()==10)
		{
			return true;
		}
		else
		{		
		return false;
		}	
	}
	
}
