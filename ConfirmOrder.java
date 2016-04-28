import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*; 
import javax.swing.table.DefaultTableModel;

import java.util.*;	//For the Date
 
public class ConfirmOrder extends JFrame {

    JLabel customerName;
    JLabel email;
    JLabel shippingAddress;
    JLabel billingAddress;
    JLabel telephone;
    JLabel creditCarNo;
    JTextField customerNamet;		//text field for cutomer name	
    JTextField emailt;				//text field for cutomer email
    JTextField shippingAddresst;	//text field for cutomer shopping address
    JTextField billingAddresst;		//text field for cutomer billing address
    JTextField telephonet;			//text field for cutomer telephone number
    JTextField creditCarNot;		//text field for cutomer credit card Number
    	
	JButton confirmOrd;				//Comfirm order button
	
	JLabel img;
    
	ResultSetTableModelFactory factory; 
	Customer loggedcust;
	Orders myOrder;    
    
    public ConfirmOrder(ResultSetTableModelFactory f, Customer loggedcust, Orders myOrder) {
		
		//Initiates the values of the state variables of the object
		this.factory = f; 
		this.loggedcust = loggedcust;
		this.myOrder = myOrder;
		
		//Set the size and titles of the window.
		setBounds(200,200,500,300);		// x,y,width,height
		setTitle("Confirm Order");
		setBackground(Color.white);
    	
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
		
		setLayout(new BorderLayout());

		JPanel p1 = new JPanel(new BorderLayout());
		JPanel p2 = new JPanel(gridbag);
    	
    	/////////////////////////////////////////////////////
    	img = new JLabel(new ImageIcon("images/confirm.jpg"));
    	p1.add(img);
    	/////////////////////////////////////////////////////
		
		//Initiates the labels
	    customerName = new JLabel("Customer Name: ");
	    email = new JLabel("Email: ");
	    shippingAddress = new JLabel("Shipping Address: ");
	    billingAddress = new JLabel("Billing Address: ");
	    telephone = new JLabel("Telephone: ");
	    creditCarNo = new JLabel("Credit Card No: ");
	    customerNamet = new JTextField(15);
	    emailt = new JTextField(15);
	    shippingAddresst = new JTextField(15);
	    billingAddresst = new JTextField(15);
	    telephonet = new JTextField(15);
	    creditCarNot = new JTextField(15);		
		
		//================================================================
		//get the logged customers details and display them in the the test fields
		customerNamet.setText(this.loggedcust.getName());
	    emailt.setText(this.loggedcust.getEmail());
	    shippingAddresst.setText(this.loggedcust.getShippingAddress());
	    billingAddresst.setText(this.loggedcust.getBillingAddress());
	    telephonet.setText(this.loggedcust.getTelephoneNumber());
	    creditCarNot.setText(this.loggedcust.setCreditCardDetails());
	    
	    emailt.setEditable(false);	//the email should not be changed, make it uneditable
		//================================================================
		
		c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0,0,0,0);
        gridbag.setConstraints(customerName, c);
        p2.add(customerName);

		c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(customerNamet, c);	    
	    p2.add(customerNamet);

		c.gridx = 0;
        c.gridy = 1;
        gridbag.setConstraints(email, c);	    
	    p2.add(email);

		c.gridx = 1;
        c.gridy = 1;
        gridbag.setConstraints(emailt, c);	    
	    p2.add(emailt);

		c.gridx = 0;
        c.gridy = 2;
        gridbag.setConstraints(shippingAddress, c);	    
	    p2.add(shippingAddress);

		c.gridx = 1;
        c.gridy = 2;
        gridbag.setConstraints(shippingAddresst, c);	    
	    p2.add(shippingAddresst);

		c.gridx = 0;
        c.gridy = 3;
        gridbag.setConstraints(billingAddress, c);	    
	    p2.add(billingAddress);
	    
		c.gridx = 1;
        c.gridy = 3;
        gridbag.setConstraints(billingAddresst, c);
	    p2.add(billingAddresst);
	    
		c.gridx = 0;
        c.gridy = 4;
        gridbag.setConstraints(telephone, c);
	    p2.add(telephone);
	    
		c.gridx = 1;
        c.gridy = 4;
        gridbag.setConstraints(telephonet, c);
	    p2.add(telephonet);
	    
		c.gridx = 0;
        c.gridy = 5;
        gridbag.setConstraints(creditCarNo, c);
	    p2.add(creditCarNo);
	    
		c.gridx = 1;
        c.gridy = 5;
        gridbag.setConstraints(creditCarNot, c);
	    p2.add(creditCarNot);	  
	    	 		
		/////////////////////////////////////////////////////
		confirmOrd = new JButton("Confirm Order");
		confirmOrd.setBackground(Color.white);
		
		c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(30,30,30,30);
        gridbag.setConstraints(confirmOrd, c);
	    p2.add(confirmOrd);
	    /////////////////////////////////////////////////////
		//When the confirm order button is pressed, preform the following actions 
		confirmOrd.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				try{
				
					Customer comfercust = getLoggedCust();		//get the logged in customer
					
					//Update the details of the logged customer to whatever the user has changed.
					ResultSetTableModelFactory fact2 = new ResultSetTableModelFactory("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:mdbTEST", "", "");
					String query = "UPDATE Customers SET Name='"+customerNamet.getText()+"', ShippingAddress='"+shippingAddresst.getText()+"', BillingAddress='"+billingAddresst.getText()+"', TelephoneNo='"+telephonet.getText()+"', CreditCardNo='"+creditCarNot.getText()+"' WHERE Email = '"+comfercust.getEmail()+"'";
					int rows = fact2.excuteQueryNoRe(query);
					
					//Get Customers ID:
					query = "SELECT CustomerID FROM Customers WHERE Email='"+emailt.getText()+"'";
					ResultSet custid = fact2.excuteQuery(query);
					int cid = custid.getInt("CustomerID");
					//System.out.println("Customer ID: "+cid);
					
					//Insert into Orders
					java.sql.Date temp=new java.sql.Date(new java.util.Date().getTime());

					//insert new orders in Orders witht the customers ID 
					query = "INSERT INTO Orders (OrderDate, ShippingDate, Status, CustomerID) " + "VALUES(#"+ temp+"#, #"+ temp+"#, 'Undelivered', '"+ cid + "')";
					rows = fact2.excuteQueryNoRe(query);

					//get the order number of the order just inserted.
					query = "SELECT OrderNo FROM Orders WHERE CustomerID="+cid+"";
					ResultSet recustord = fact2.excuteQuery(query);
					recustord.last();
					int cordno = recustord.getInt("OrderNo");
					//System.out.println("Order No: "+cordno);
					
					//Insert into OrderDetails table with the order Id just retrived. 
					Orders newOrder = getMyOrder();
					OrdersDetails enterDetail = new OrdersDetails();
					
					for (int j=0; j < newOrder.itemslist.size(); j++) 
					{
						//insert the details of the order in the OrdersDetails
						enterDetail = (OrdersDetails) newOrder.itemslist.get(j);
						query = "INSERT INTO OrderDetails (OrderNo, ISBN, Quantity, Price, Tax, Postage) " + "VALUES("+ cordno+", '"+ enterDetail.singleitem.getIsbn()+"', "+enterDetail.quantity+", "+ enterDetail.singleitem.getPrice()+", "+ newOrder.tax+", "+ newOrder.postage+")";
						rows = fact2.excuteQueryNoRe(query);
					}
					//System.out.println("--------------");

					//Then go back to LoggedinScreen
					LoggedinScreen loggedin = new LoggedinScreen(getLoggedCust(), getMyOrder(), cid, cordno);
					
					dispose();
					
				}
				catch(Exception ex)
				{
					System.out.println("Invalid Input 0"+ ex. getMessage());
				}
				
    		}});
		/////////////////////////////////////////////////////
		//Set the backgrounf of the panels to white and add them to the container.
		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		add(p1, BorderLayout.WEST);
		add(p2, BorderLayout.CENTER);
		
		//pack();
		setVisible(true);
    	
    }
    //Returns the values of the state variables of the object 
    public Orders getMyOrder()
    {
    	return this.myOrder;
    }    
    public Customer getLoggedCust()
    {
    	return this.loggedcust;
    }    
}