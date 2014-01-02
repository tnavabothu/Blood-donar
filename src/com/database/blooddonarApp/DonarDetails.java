package com.database.blooddonarApp;

public class DonarDetails {
	
	//private variables
	String _name;
	String _bloodGroup;
	String _phoneNumber;
	String _email;
	String _pincode;
	
	//Empty constructor
	public DonarDetails() {
		// TODO Auto-generated constructor stub
	}
	
	//Constructor
	public DonarDetails(String name,String bloodGroup,String phoneNumber,String email,String pincode)
	{
		this._name=name;
		this._bloodGroup=bloodGroup;
		this._phoneNumber=phoneNumber;
		this._email=email;
		this._pincode=pincode;
	}
	
	
	//getter and setter methods
	
	
	//get the name of the donar
	public String getName()
	{
		return this._name;
	}
	
	//set the name of the donar
	public void setName(String name)
	{
		this._name=name;
	}
	
	//get the blood group of the donar
	public String getBloodgroup()
	{
		return this._bloodGroup;
	}
	
	//set the name of the donar
	public void setBloodgroup(String bloodGroup)
	{
		this._bloodGroup=bloodGroup;
	}
	
	//get the phone number of the donar
	public String getPhoneNumber()
	{
		return this._phoneNumber;
	}
	
	//set the phone number of the donar
	public void setPhoneNumber(String phoneNumber)
	{
		this._phoneNumber=phoneNumber;
	}
	
	//get the email of the donar
	public String getEmail()
	{
		return this._email;
	}
	
	//set the email of the donar
	public void setEmail(String email)
	{
		this._email=email;
	}

	//get the pincode of the donar
	public String getPincode()
	{
		return this._email;
	}
	
	//set the pincode of the donar
	public void setPincode(String pincode)
	{
		this._pincode=pincode;
	}
	
}
