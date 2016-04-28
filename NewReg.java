
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

import java.util.regex.*; //to use Regular Expression

public class NewReg extends JFrame {
    boolean inAnApplet = true;
    final boolean shouldFill = false;
    final boolean shouldWeightX = false;
    
    JLabel z1,z2,z3,z4,z5,z6,z7;
    JTextField t1,t3,t4,t5,t6,t7;
    JPasswordField t2;
    JLabel y1;
    JButton x1;
    
    public NewReg() {
        
        JButton button;
        
        //Set the size and titles of the window.
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.white);
        //set the layout for the container.
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        contentPane.setLayout(gridbag);
        
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.NONE; 
        }
   
        JLabel img = new JLabel(new ImageIcon("images/registerationsd.jpg"));
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.gridheight = 8;
        c.gridx = 0;
        c.gridy = 0;
        
        gridbag.setConstraints(img, c);
        contentPane.add(img);
////////////////////////////////////////////////////////
		z1 = new JLabel("Customer Name :");
        c.anchor = GridBagConstraints.EAST;
        c.gridheight = 1;
        c.insets = new Insets(50,10,10,10);  //top padding
        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(z1, c);
        contentPane.add(z1);
        		
		t1 = new JTextField(10);
        c.gridheight = 1;
        c.insets = new Insets(50,10,10,10);  //top padding
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        c.gridy = 0;
        gridbag.setConstraints(t1, c);
        contentPane.add(t1);
////////////////////////////////////////////////////////        
		z2 = new JLabel("Password :");
        c.anchor = GridBagConstraints.EAST;
        c.gridheight = 1;        
        c.insets = new Insets(0,10,10,10); 
        c.gridx = 1;
        c.gridy = 1;
        gridbag.setConstraints(z2, c);
        contentPane.add(z2);
        		
		t2 = new JPasswordField(10);
        c.gridheight = 1;
        c.insets = new Insets(0,10,10,10);
        c.anchor = GridBagConstraints.WEST; 
        c.gridx = 2;
        c.gridy = 1;
        gridbag.setConstraints(t2, c);
        contentPane.add(t2);
////////////////////////////////////////////////////////        
		z3 = new JLabel("Email :");
        c.anchor = GridBagConstraints.EAST;
        c.gridheight = 1;        
        c.insets = new Insets(0,10,10,10); 
        c.gridx = 1;
        c.gridy = 2;
        gridbag.setConstraints(z3, c);
        contentPane.add(z3);
        		
		t3 = new JTextField(10);
        c.gridheight = 1;
        c.insets = new Insets(0,10,10,10);
        c.anchor = GridBagConstraints.WEST; 
        c.gridx = 2;
        c.gridy = 2;
        gridbag.setConstraints(t3, c);
        contentPane.add(t3);
 ////////////////////////////////////////////////////////        
		z4 = new JLabel("Shipping Address :");
        c.anchor = GridBagConstraints.EAST;
        c.gridheight = 1;        
        c.insets = new Insets(0,10,10,10); 
        c.gridx = 1;
        c.gridy = 3;
        gridbag.setConstraints(z4, c);
        contentPane.add(z4);
        		
		t4 = new JTextField(10);
        c.gridheight = 1;
        c.insets = new Insets(0,10,10,10);
        c.anchor = GridBagConstraints.WEST; 
        c.gridx = 2;
        c.gridy = 3;
        gridbag.setConstraints(t4, c);
        contentPane.add(t4);
////////////////////////////////////////////////////////        
		z5 = new JLabel("Billing Address :");
        c.anchor = GridBagConstraints.EAST;
        c.gridheight = 1;        
        c.insets = new Insets(0,10,10,10); 
        c.gridx = 1;
        c.gridy = 4;
        gridbag.setConstraints(z5, c);
        contentPane.add(z5);
        		
		t5 = new JTextField(10);
        c.gridheight = 1;
        c.insets = new Insets(0,10,10,10);
        c.anchor = GridBagConstraints.WEST; 
        c.gridx = 2;
        c.gridy = 4;
        gridbag.setConstraints(t5, c);
        contentPane.add(t5);
////////////////////////////////////////////////////////        
		z6 = new JLabel("Telephone No :");
        c.anchor = GridBagConstraints.EAST;
        c.gridheight = 1;        
        c.insets = new Insets(0,10,10,10); 
        c.gridx = 1;
        c.gridy = 5;
        gridbag.setConstraints(z6, c);
        contentPane.add(z6);
        		
		t6 = new JTextField(10);
        c.gridheight = 1;
        c.insets = new Insets(0,10,10,10);
        c.anchor = GridBagConstraints.WEST; 
        c.gridx = 2;
        c.gridy = 5;
        gridbag.setConstraints(t6, c);
        contentPane.add(t6);
        
////////////////////////////////////////////////////////        
		z7 = new JLabel("Credit Card No :");
        c.anchor = GridBagConstraints.EAST;
        c.gridheight = 1;        
        c.insets = new Insets(0,10,10,10); 
        c.gridx = 1;
        c.gridy = 6;
        gridbag.setConstraints(z7, c);
        contentPane.add(z7);
        		
		t7 = new JTextField(10);
        c.gridheight = 1;
        c.insets = new Insets(0,10,10,10);
        c.anchor = GridBagConstraints.WEST; 
        c.gridx = 2;
        c.gridy = 6;
        gridbag.setConstraints(t7, c);
        contentPane.add(t7); 
        	                              
//////////////////////////////////////////////////////// 
		y1 = new JLabel();
        c.gridheight = 1;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 7;
        gridbag.setConstraints(y1, c);
        contentPane.add(y1);
        		
////////////////////////////////////////////////////////
		x1 = new JButton("Register");
        c.gridheight = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,10,10,10); 
        c.gridx = 1;
        c.gridy = 8;
        gridbag.setConstraints(x1, c);
        contentPane.add(x1);
        		
        		
//////////////////////////////////////////////////////// 
        setTitle("New Registration");
        pack();
        setVisible(true);
 
 ////////////////////////////////////////////////////////    
		//prefoem the following when the register button is pressed
		x1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				////check the form, if valid then continue
				if(checkForm())
				{	
					try{
						//check to see if the email provided is already registered.
						//Otherwise display message.
						ResultSetTableModelFactory fact2 = new ResultSetTableModelFactory("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:mdbTEST", "", "");
						String query1 = "SELECT * FROM Customers WHERE Email='"+t3.getText()+"'";
						ResultSet returnedrs = fact2.excuteQuery(query1);
						returnedrs.last();
						int rscount = returnedrs.getRow();
						
						//if the email does not already exist, then add the new user to the database.
						//Open the Login screen and close this window.
						if(rscount == 0)
						{
							String query2 = "INSERT INTO Customers (Name, Password, Email, ShippingAddress, BillingAddress, TelephoneNo, CreditCardNo) " + "VALUES('" + t1.getText() + "', '"+ t2.getText() + "', '"+ t3.getText() + "', '"+ t4.getText() + "', '"+ t5.getText() + "', '"+ t6.getText() + "', '" + t7.getText() + "')";
							int rows = fact2.excuteQueryNoRe(query2);
							
							fact2.close();
							LoginScn nlins = new LoginScn();
							dispose();
							
						}
						else{
							y1.setText("This email is already registered.");
						}
					}
					catch(Exception ex)
					{
						System.out.println("Invalid Input 0"+ ex. getMessage());
					}
				}
				
    		}});
    	////////////////////////////////////////////////////////////////////////
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    dispose();
            }
        });
    }
    
    //Validate the form
    public Boolean checkForm()
    {
    	//Check that all the fields have been filled. If not not then display massage.
    	if(!t1.getText().equals("") && !t2.getText().equals("") && !t3.getText().equals("") && !t4.getText().equals("") && !t5.getText().equals("") && !t6.getText().equals("") && !t7.getText().equals(""))
    	{
    		//Check if the email is correct. Otherwise display message.
    		if(checkEmail(t3.getText()))
    		{
    			//Check if the telephone and credit card numbers are both numbers. Otherwise display message.
    			if(hasNumber(t6.getText()) && hasNumber(t7.getText()))
    			{
    				return true;
    			}
    			else
    			{
	    			y1.setText("Please enter a valid Number.");
	    			return false;
    			}
    			
    		}
    		else
    		{
    			y1.setText("Please enter a valid Email.");
    			return false;
    		}
    	}
    	else{
    		y1.setText("Please enter a value for all the entries.");
    		return false;
    	}
    }
    
    //Validate the email
    public Boolean checkEmail(String email)
    {
      //Set the email pattern string
      Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

      //Match the given string with the pattern
      Matcher m = p.matcher(email);

      //check whether match is found 
      boolean matchFound = m.matches();

      if (matchFound)
      {
      	return true;
      }
      else
      {
      	return false;
      }
    }
    
    //Check that a String has a number. If it does return true if not return false.
    public boolean hasNumber(String s) 
    {
		for (int j = 0;j < s.length();j++) {
			if (Character.isDigit(s.charAt(j))) {
				return true;
			}
		}
		return false;
	}

    public static void main(String args[]) {
        NewReg window = new NewReg();
    }
}