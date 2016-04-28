/**
 * @(#)Customer.java
 *
 *
 * @author 
 * @version 1.00 2008/3/25
 */
    class Customer
    {
		private String eMail; 				//Email address of the person 
		private String password; 			//Password of person 
		private String name; 				//Name of the person 
		private String shippingAddress; 	//Address to which goods will be shipped 
		private String billingAddress; 		//address of credit card billing 
		private String telephoneNumber; 		//Day telephone number of person 
		private String creditCardDetails; 	//Credit card type and last 4 digits of number 

		public Customer(){}

		//Constructor – creates a new object of the class Customer
		public Customer(String eMail, String password, String name, String shippingAddress, String billingAddress, String telephoneNumber, String creditCardDetails) 	
		{
			this.eMail = eMail; 
			this.password = password; 
			this.name = name;
			this.shippingAddress = shippingAddress; 
			this.billingAddress = billingAddress; 
			this.telephoneNumber = telephoneNumber; 
			this.creditCardDetails = creditCardDetails;  

		}
		
		//Returns the values of the state variables of the object 
		public String getEmail()
		{ return eMail; }
		public String getPassword()
		{ return password; }
		public String getName()
		{ return name; }
		public String getShippingAddress()
		{ return shippingAddress; }
		public String getBillingAddress()
		{ return billingAddress; }
		public String getTelephoneNumber() 	
		{ return telephoneNumber; }
		public String setCreditCardDetails() 
		{ return creditCardDetails; }
		
		//Changes the values of the state variables of the object
		public void setEmail(String eMail)
		{ this.eMail=eMail; }
		public void setPassword(String password)
		{ this.password=password; }
		public void setName(String name)
		{ this.name=name; }
		public void setBillingAddress(String billingAddress)
		{  this.billingAddress=billingAddress; }
		public void setShippingAddress(String shippingAddress)
		{  this.shippingAddress=shippingAddress; }
		public void setTelephoneNumber(String telephoneNumber)
		{  this.telephoneNumber=telephoneNumber; }
		public void setCreditCardDetails(String creditCardDetails) 	 
		{  this.creditCardDetails=creditCardDetails; }
		//------------------------------------------------------
		
		//Returns both name, eMail and telephoneNumber as strings 
		public String getCustomerDetails() 	
		{
			String cdetails = "Name: "+getName()+", eMail: "+getEmail()+", TelephoneNumber: "+getTelephoneNumber();
			return cdetails; 
		}
		
		//Returns a string representation of the object 
		public String toString() 	
		{
			return "EMail: " + this.eMail + "\nPassword: " + this.password + "\nName: " + this.name + "\nBilling Address: "+this.billingAddress+"\nShippingAddress: "+this.shippingAddress+"\nTelephoneNumber: "+this.telephoneNumber+"\nCredit Card Details: "+this.creditCardDetails+ "\n";
		}
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////

class Individual extends Customer
{
	//Constructors – create a new object of the class Individual
	public Individual()
	{
		this(null, null, null, null, null, null, null);
	}
	public Individual(String eMail, String password, String name, String shippingAddress, String billingAddress, String telephoneNumber, String creditCardDetails)
	{
		super(eMail, password, name, shippingAddress, billingAddress, telephoneNumber, creditCardDetails);	
	}	
}

class Retailer extends Customer  
{
	private String companyname;				//company name, 
	private String contactname;				//name of contact person, 
	private int accountnumber;				//account number. 
	
	//Constructors – create a new object of the class Retailer
	public Retailer()
	{
		this(null, null, null, null, null, null, null, null, null, -1);
	}
	public Retailer(String eMail, String password, String name, String shippingAddress, String billingAddress, String telephoneNumber, String creditCardDetails, String companyname, String contactname, int accountnumber)
	{
		super(eMail, password, name, shippingAddress, billingAddress, telephoneNumber, creditCardDetails);
		this.setCompanyName(companyname);
		this.setContactName(contactname);
		this.setAccountNumber(accountnumber);
	}
	
	//Returns the values of the state variables of the object
	public String getCompanyName() 	
	{ return companyname; }
	public String getContactName() 	
	{ return contactname; }
	public int getAccountNumber() 	
	{ return accountnumber; }
	
	//Changes the values of the state variables of the object
	public void setCompanyName(String companyname)
	{  this.companyname=companyname; }
	public void setContactName(String contactname)
	{  this.contactname=contactname; }
	public void setAccountNumber(int accountnumber)
	{  this.accountnumber=accountnumber; }	
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
