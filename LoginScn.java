import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;

public class LoginScn extends JFrame {

    public static JTextField t1 = new JTextField(20);			//Text field for the users name
	public static JPasswordField t2 = new JPasswordField(20);	//Password field for the users password 
	public static JLabel z5;									//Label for massages for the use
    public static boolean flag = false;
    	 
    public LoginScn() {
        	
        JButton button;
        
        //Set containers background, and layout.
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.white);
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        contentPane.setLayout(gridbag);
   
        JLabel img = new JLabel(new ImageIcon("images/loginsd.jpg"));

        c.gridheight = 4;
        c.gridx = 0;
        c.gridy = 0;
        
        gridbag.setConstraints(img, c);
        contentPane.add(img);
		////////////////////////////////////////////////////////
		JLabel z1 = new JLabel("Email :");
        c.anchor = GridBagConstraints.EAST;
        c.gridheight = 1;
        c.insets = new Insets(100,10,10,10);  //top padding
        c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(z1, c);
        contentPane.add(z1);
        		
		t1 = new JTextField(10);
        c.gridheight = 1;
        c.insets = new Insets(100,10,10,10);  //top padding
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        c.gridy = 0;
        gridbag.setConstraints(t1, c);
        contentPane.add(t1);
		////////////////////////////////////////////////////////        
		JLabel z2 = new JLabel("Password :");
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
		JButton z3 = new JButton("  Login   ");
        c.gridheight = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5,10,10,10); 
        c.gridx = 1;
        c.gridy = 2;
        gridbag.setConstraints(z3, c);
        contentPane.add(z3);
        		
        		
		//////////////////////////////////////////////////////// 
		z5 = new JLabel();
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 4;
        gridbag.setConstraints(z5, c);
        contentPane.add(z5);

		////////////////////////////////////////////////////////  
		JButton z4 = new JButton("Register");
        c.gridheight = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 3;
        gridbag.setConstraints(z4, c);
        contentPane.add(z4);
        		
		//////////////////////////////////////////////////////// 
        setTitle("Login");
        pack();
        setVisible(true);
        setLocation(250,200);
 		////////////////////////////////////////////////////////    
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    dispose();
            }
        });
 		////////////////////////////////////////////////////////
 		//Log in
 		z3.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				//get the name and password the user entered.
				String name = t1.getText();
				String pass = new String(t2.getPassword());
				
				//System.out.println(name);
				//System.out.println(pass);
				try{
				
					ResultSetTableModelFactory factory = new ResultSetTableModelFactory("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:mdbTEST", "", "");
					Boolean checkDetails = factory.checkLogin(name, pass);//search for the name and password in the the database.
					//System.out.println(checkDetails);
					
					//if the user does exist and the passowrd matches	
					if(checkDetails)
					{	
						dispose();		//close the current window
						
						//retrive the useres details from the database 
						String logcustomer = "SELECT * FROM Customers WHERE Email='"+name+"'";
						ResultSet rs = factory.excuteQuery(logcustomer);
						Customer cust1 = new Customer();
						
						int id = rs.getInt("CustomerID");
         				cust1.setName(rs.getString("Name"));		
         				cust1.setPassword(rs.getString("Password"));
         				cust1.setEmail(rs.getString("Email"));
         				cust1.setShippingAddress(rs.getString("ShippingAddress"));
         				cust1.setBillingAddress(rs.getString("BillingAddress"));
         				cust1.setTelephoneNumber(rs.getString("TelephoneNo"));
         				cust1.setCreditCardDetails(rs.getString("CreditCardNo"));
						
						factory.close();
						
						Orders order1 = new Orders();
						LoggedinScreen f1 = new LoggedinScreen(cust1, order1, -1, -1); //return to the logged in screen.
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Wrong Password !!!");
						//LoginScn.z5.setText("Wrong Password !!!");
					}
				}
				catch(Exception ex)
				{
					System.out.println("Invalid Input 0"+ ex. getMessage());
				}
			}			
    	});
		////////////////////////////////////////////////////////
 		//Open the register new user screen if the Rgistation button is pressed.
 		z4.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{ 	
				NewReg nr1 = new NewReg();
				dispose();
			
			}});
			
    }
    public static void main(String args[]) {
        LoginScn window = new LoginScn();
    }
}