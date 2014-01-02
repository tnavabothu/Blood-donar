/*
 * Activity on find a donar button clicked
 * get the values of the blood group and pin code and send a parse request
 */

package com.example.blooddonar;

import java.util.List;

import com.network.status.DetectInternetConnectivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FindDonar extends Activity{
	
	private Spinner mSpinner;
	private Button mButton;
	private EditText mEditText;
	public int mgetSelectedItemPosition;
	String mgetSelectedItem;
	 ProgressDialog mProgressDialog;
	SearchFunctionality mSearchFunctionality=new SearchFunctionality();
	
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_find_a_donar);
			mProgressDialog=new ProgressDialog(this);
			mProgressDialog.setTitle("Loading");
			mProgressDialog.setMessage("Please Wait...");
			mProgressDialog.setCanceledOnTouchOutside(false);
		//call the method for search button
		  addListenerOnSearchButton();
		  
		  //Reference for the Blood Group drop down
		  mSpinner=(Spinner)findViewById(R.id.select_bloodGroup_dropdown);
		  
		  //listener for the Blood Group drop down
			mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					// TODO Auto-generated method stub
					mgetSelectedItem=parent.getItemAtPosition(pos).toString();
					mgetSelectedItemPosition=parent.getSelectedItemPosition();
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
			});
		  
		}
		
		
		//method for search button
		public void addListenerOnSearchButton()
		{
			// reference for search button
			mButton=(Button)findViewById(R.id.Search_bloodgroup_button);
			
			//reference for edit text which gives the pincode value
			mEditText=(EditText)findViewById(R.id.search_pincode);
			
			//listener for the search button
			mButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					DetectInternetConnectivity cd=new DetectInternetConnectivity(getApplicationContext());
					if(cd.isConnectingToInternet())
					{
					mProgressDialog.show();
					 Log.d("tagore", mgetSelectedItem);
					 Log.d("tagore", mEditText.getText().toString());
					 
					 //calls the method in SearchFunctionality class to pass the request to parse and handle the response
					//  mSearchFunctionality.PerformSearchForSelectedDetails(FindDonar.this,mgetSelectedItem,mEditText.getText().toString());

					 
					 ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("DonarDetails");
						
						//constraints for the query which works as AND condition
						parseQuery.whereEqualTo("BloodGroup", mgetSelectedItem);
						parseQuery.whereEqualTo("Pincode", mEditText.getText().toString());
						
					    //mehtod to send the parse request and get the call back function
						parseQuery.findInBackground(new FindCallback<ParseObject>() {
							
							@Override
							public void done(List<ParseObject> DonarList, ParseException e) {
								// TODO Auto-generated method stub
								if(e==null)
								{
									Log.d("tagore", "success "+DonarList.size());
									//call the method to handle the DonarList
									if(DonarList.size()>0)
									{
									parseListtObjects(DonarList);
									}
									else {
										mProgressDialog.dismiss();
										Toast.makeText(getApplicationContext(), "No Results Found", Toast.LENGTH_LONG).show();
									}
													
								}
								else 
								{
									mProgressDialog.dismiss();
									Log.d("tagore", "failure"+e.getMessage());
								}
							}
						});
					 
				}
					else {
						Toast.makeText(getApplicationContext(), "Please Check Internet Connection", Toast.LENGTH_LONG).show();
					}
				}
			});
		}
		//method to handle the DoanrList
		public void parseListtObjects(List<ParseObject> mDonarList)
		{
			
			int size=mDonarList.size();
			String[] mBloodGroup=new String[size];
			String[] mEmail=new String[size];
			String[] mFirstNameLastName=new String[size];
			String[] mPhoneNumber=new String[size];
			String[] mPincode=new String[size];
			
			for(int i=0;i<mDonarList.size();i++)
			{
				Log.d("List Value", "-------------------------------------");
				Log.d("value",mDonarList.get(i).getString("BloodGroup"));
				mBloodGroup[i]=mDonarList.get(i).getString("BloodGroup");
				
				Log.d("value",mDonarList.get(i).getString("Email"));
				mEmail[i]=mDonarList.get(i).getString("Email");
				
				Log.d("value",mDonarList.get(i).getString("FirstName"));
				mFirstNameLastName[i]=mDonarList.get(i).getString("FirstName")+" "+mDonarList.get(i).getString("LastName");
				
				Log.d("value",mDonarList.get(i).getString("LastName"));
				
				
				Log.d("value",mDonarList.get(i).getString("PhoneNumber"));
				mPhoneNumber[i]=mDonarList.get(i).getString("PhoneNumber");
				
				Log.d("value",mDonarList.get(i).getString("Pincode"));
				mPincode[i]=mDonarList.get(i).getString("Pincode");
				
				Log.d("List Value", "#######################################");
			}
			
			Intent mIntent=new Intent(getApplicationContext(), ShowDonarDetailsActivity.class);
			
			mIntent.putExtra("bloodgroup",mBloodGroup);
			mIntent.putExtra("email", mEmail);
			mIntent.putExtra("donarName", mFirstNameLastName);
			mIntent.putExtra("phoneNo", mPhoneNumber);
			mIntent.putExtra("pinCode", mPincode);
			mProgressDialog.dismiss();
			startActivity(mIntent);
		}
		
		
}
