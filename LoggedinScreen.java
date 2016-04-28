import java.awt.*;				// color, font
import javax.swing.*;			// All swing components are here
import javax.swing.border.*;	// 

import java.awt.event.*;		//For LoginFrame class

class LoggedinScreen extends JFrame
{
	Customer loggedcust;			//the logged in Customer
	Orders myOrder;					//the customers order
	int CustID, OrderNumber = -1;	//the customers Id, and Order Number.

	public LoggedinScreen(Customer loggedcust, Orders myOrd, int CustID, int OrderNumber)
	{
		//Initiates the values of the state variables of the object 
		this.loggedcust = loggedcust;
		this.myOrder = myOrd;
		this.CustID = CustID;
		this.OrderNumber = OrderNumber; 
		
		//Set the size and titles of the window.
		setBounds(150,100,700,600);		// x,y,width,height
		setTitle("Mail Order Project");
		
		//JFrame has 3 layers the top one is Container.
		Container c = getContentPane();
		c.setBackground(Color.white);		//set the background to white.
		c.setLayout(new FlowLayout());		//set the layout of the container.
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//The avaliable buttons
		JButton b1 = new JButton("Log In", new ImageIcon("images/login.jpg"));
		JButton b2 = new JButton("Shopping", new ImageIcon("images/shopping.jpg"));
		JButton b3 = new JButton("View Basket", new ImageIcon("images/basket.jpg"));
		JButton b4 = new JButton("Order Report", new ImageIcon("images/report.jpg"));
		JButton b5 = new JButton("Log Out", new ImageIcon("images/logout.jpg"));
		
		b1.setBounds(0,0,100,35);
		b1.setMargin(new Insets(0,0,0,0));
		b1.setVerticalTextPosition(SwingConstants.BOTTOM);
		b1.setHorizontalTextPosition(SwingConstants.CENTER);
		b1.setOpaque(false);
		b1.setBackground(Color.white);
		b1.setForeground(Color.black);
		b1.setEnabled(false);
		
		b2.setBounds(0,0,100,35);
		b2.setMargin(new Insets(0,0,0,0));
		b2.setVerticalTextPosition(SwingConstants.BOTTOM);
		b2.setHorizontalTextPosition(SwingConstants.CENTER);
		b2.setOpaque(true);
		b2.setBackground(Color.white);
		b2.setForeground(Color.black);
		b2.setEnabled(true);
		
		b3.setBounds(0,0,100,35);
		b3.setMargin(new Insets(0,0,0,0));
		b3.setVerticalTextPosition(SwingConstants.BOTTOM);
		b3.setHorizontalTextPosition(SwingConstants.CENTER);
		b3.setOpaque(true);
		b3.setBackground(Color.white);
		b3.setForeground(Color.black);
		b3.setEnabled(true);
		
		b4.setBounds(0,0,100,35);
		b4.setMargin(new Insets(0,0,0,0));
		b4.setVerticalTextPosition(SwingConstants.BOTTOM);
		b4.setHorizontalTextPosition(SwingConstants.CENTER);
		b4.setOpaque(false);
		b4.setBackground(Color.white);
		b4.setForeground(Color.black);
		
		//Only activate this button when the customer has made an order.
		if(this.OrderNumber == -1)
		{
			b4.setEnabled(false);
		}
		else{
			b4.setEnabled(true);
		}
		
		b5.setBounds(0,0,100,35);
		b5.setMargin(new Insets(0,0,0,0));
		b5.setVerticalTextPosition(SwingConstants.BOTTOM);
		b5.setHorizontalTextPosition(SwingConstants.CENTER);
		b5.setOpaque(true);
		b5.setBackground(Color.white);
		b5.setForeground(Color.black);		
		b5.setEnabled(true);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel img = new JLabel(new ImageIcon("images/mailordersystem.jpg"));
		add(img, BorderLayout.CENTER);
		
		//Shopping Button
		b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Customer lc = getLoggedCustomer();	//get the current customer
                Orders mo = new Orders();			//get the customers Order
                dispose();							//close the current window
                try{
						//**********************************************************************************
						ResultSetTableModelFactory factory = new ResultSetTableModelFactory("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:mdbTEST", "", "");
						
						//create an object of the ShoppingTry class
						ShoppingTry qf = new ShoppingTry(factory, lc, mo);	
					}
					catch(Exception ex)
					{
						System.out.println("Invalid Input 0"+ ex. getMessage());
					}//end of try and catch
            }
        });
		////////////////////////////////////////////////////////////////////////////////////////////////////
        //Basket view Button
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try{
					//myOrder.displayOrd();
					//**********************************************************************************
					ResultSetTableModelFactory factory = new ResultSetTableModelFactory("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:mdbTEST", "", "");
	
					///////////////////////////////////////////////////////////
					ShoppingCart nsc = new ShoppingCart(factory, getLoggedCustomer(), getMyOrder());
					///////////////////////////////////////////////////////////					
					dispose();  
				}
				catch(Exception ex)
				{
					System.out.println("Invalid Input 0"+ ex. getMessage());
				}              
            }
        });
		////////////////////////////////////////////////////////////////////////////////////////////////////
        //Order Report Button
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
           	OrderReport neworderReport = new OrderReport(getLoggedCustomer(), getMyOrder(), getCutomerID(), getOrderNumber());
            dispose();	             
        }});
		//////////////////////////////////////////////////////// 
		//Logout Button
		b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
		}); 
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                    dispose();
            }
        });
////////////////////////////////////////////////////////	
		setVisible(true);		
	}
	
    //Returns the values of the state variables of the object 
    public int getCutomerID()
    {
    	return this.CustID;
    }
     public int getOrderNumber()
    {
    	return this.OrderNumber;
    }   	
    public Orders getMyOrder()
    {
    	return this.myOrder;
    } 	
	public Customer getLoggedCustomer()
	{
		return(this.loggedcust);
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
