package com.example.blooddonar;

import com.network.status.DetectInternetConnectivity;
import com.parse.Parse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	DetectInternetConnectivity cd ; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cd=new DetectInternetConnectivity(getApplicationContext());
        Parse.initialize(this, "Ey5Af1XsFjdhk2woPGds4Hn4bNzZYqZtuyfhsDaq", "Q55SGuD0AsGeYeaNvrlUPVo93eqd7sT0vUxRY0gP"); 
        
        /*
         * Donar registration button listener
         */
        Button _donarRegistrationButton=(Button)findViewById(R.id._donar_registration_button);
        _donarRegistrationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(cd.isConnectingToInternet())
				{			
					Intent i=new Intent(getApplicationContext(), RegistrationActivity.class);
					startActivity(i);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please check Internet Settings", Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
        /*
         * find donar button listener
         */
        Button _findDonarButton=(Button)findViewById(R.id._find_a_donar);
        _findDonarButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(cd.isConnectingToInternet())
				{	
				Intent i=new Intent(getApplicationContext(), FindDonar.class);
				startActivity(i);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Please check Internet Settings", Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
