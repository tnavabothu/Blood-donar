package com.example.blooddonar;

import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SearchFunctionality extends Application {
	//method to handle the search operations
	private Context mContext;
	public  void PerformSearchForSelectedDetails(Context mgetContext,String mselectedItem,String mPinCode)
	{
		mContext=mgetContext;
		//initialize the parse query
		ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("DonarDetails");
		
		//constraints for the query which works as AND condition
		parseQuery.whereEqualTo("BloodGroup", mselectedItem);
		parseQuery.whereEqualTo("Pincode", mPinCode);
		
	    //mehtod to send the parse request and get the call back function
		parseQuery.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> DonarList, ParseException e) {
				// TODO Auto-generated method stub
				if(e==null)
				{
					Log.d("tagore", "success "+DonarList.size());
					//call the method to handle the DonarList
					parseListtObjects(DonarList);
									
				}
				else {
					Log.d("tagore", "failure"+e.getMessage());
				}
			}
		});
		
		
	}
	
	//method to handle the DoanrList
	public void parseListtObjects(List<ParseObject> mDonarList)
	{
		String[] mBloodGroup=new String[mDonarList.size()];
		String[] mEmail=new String[mDonarList.size()];
		String[] mFirstNameLastName=new String[mDonarList.size()];
		String[] mPhoneNumber=new String[mDonarList.size()];
		String[] mPincode=new String[mDonarList.size()];
		
		for(int i=0;i<mDonarList.size();i++)
		{
			Log.d("List Value", "-------------------------------------");
			Log.d("value",mDonarList.get(i).getString("BloodGroup"));
			mBloodGroup[i]=mDonarList.get(i).getString("BloodGroup");
			
			Log.d("value",mDonarList.get(i).getString("Email"));
			mEmail[i]=mDonarList.get(i).getString("Email");
			
			Log.d("value",mDonarList.get(i).getString("FirstName"));
			mFirstNameLastName[i]=mDonarList.get(i).getString("FirstName")+""+mDonarList.get(i).getString("LastName");
			
			Log.d("value",mDonarList.get(i).getString("LastName"));
			
			
			Log.d("value",mDonarList.get(i).getString("PhoneNumber"));
			mPhoneNumber[i]=mDonarList.get(i).getString("PhoneNumber");
			
			Log.d("value",mDonarList.get(i).getString("Pincode"));
			mPincode[i]=mDonarList.get(i).getString("Pincode");
			
			Log.d("List Value", "#######################################");
		}
		
		Intent mIntent=new Intent(mContext, ShowDonarDetailsActivity.class);
		startActivity(mIntent);
	}
}
