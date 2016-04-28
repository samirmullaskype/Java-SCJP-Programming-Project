import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
 
public class OrderReport extends JFrame {

    JLabel customerName;
    JLabel email;
    JLabel shippingAddress;
    JLabel billingAddress;
    JLabel telephone;
    JLabel creditCardNo;
    JLabel orderNo;
    JLabel orderDate;
    JLabel orderStatus;
    JLabel shippingDate;
    
    JLabel customerNamet;		//label for the customers name
    JLabel emailt;				//label for the customers email	
    JLabel shippingAddresst;	//label for the customers shipping address
    JLabel billingAddresst;		//label for the customers billing address	
    JLabel telephonet;			//label for the customers telephone number
    JLabel creditCardNot;		//label for the customers credit card details
    JLabel orderNot;			//label for the customers order number
    JLabel orderDatet;			//label for the customers order date
    JLabel orderStatust;		//label for the customers order status
    JLabel shippingDatet;		//label for the customers order shipping date

	JLabel img;
	JTable table;
	JButton close;

	Customer loggedcust;		//Object of Cutomer class with the cutomers details
	Orders myOrd; 				//Object of Orders class with the orders class
	int CustID, OrderNumber;	//the current customers ID and order number
	
	private boolean DEBUG = false;/////////////////////////////////////////////////////

    public OrderReport(Customer loggedcust, Orders myOrd, int CustID, int OrderNumber) 
    {
    	//Initiates the values of the state variables of the object 
		this.loggedcust = loggedcust;
		this.myOrd = myOrd; 
		this.CustID = CustID;
		this.OrderNumber = OrderNumber;		
		
		//Set the size and titles of the window.
		setBounds(200,200,600,550);		// x,y,width,height
		setTitle("Order Report");
		setBackground(Color.white);
    	
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
		
		setLayout(new BorderLayout());

		JPanel p1 = new JPanel(gridbag);
		JPanel p2 = new JPanel(gridbag);
		JPanel p3 = new JPanel(new BorderLayout());
    	
    	/////////////////////////////////////////////////////
    	img = new JLabel(new ImageIcon("images/report_header.jpg"));
    	p1.add(img);
    	///////////////////////////////////////////////////// 
    	
		customerName = new JLabel("Customer Name: ");
    	email = new JLabel("Email: ");
    	shippingAddress = new JLabel("Shipping Address: ");
    	billingAddress = new JLabel("Billing Address: ");
    	telephone = new JLabel("Telephone: ");    	
    	creditCardNo = new JLabel("Credit Card No: ");
    	orderNo = new JLabel("Order No: ");
    	orderDate = new JLabel("Order Date: ");
    	orderStatus = new JLabel("Order Status: ");
    	shippingDate = new JLabel("Shipping Date: ");
    	
		customerNamet = new JLabel("                                       ");
    	emailt = new JLabel("                                       ");
    	shippingAddresst = new JLabel("                                       ");
    	billingAddresst = new JLabel("                                       ");
    	telephonet = new JLabel("                                       ");
    	creditCardNot = new JLabel("                                       ");
    	orderNot = new JLabel("                                       ");
    	orderDatet = new JLabel("                                       ");
    	orderStatust = new JLabel("                                       ");
    	shippingDatet = new JLabel("                                       ");
    		
		//=================================================== 	
		try{
			//Get the cutomers details
			ResultSetTableModelFactory fact = new ResultSetTableModelFactory("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:mdbTEST", "", "");		
			String query = "SELECT * FROM Customers WHERE CustomerID="+this.CustID;
			ResultSet displayRS1 = fact.excuteQuery(query);
			
			//display the cutomer's details
			customerNamet.setText(displayRS1.getString("Name"));
			emailt.setText(displayRS1.getString("Email"));
    		shippingAddresst.setText(displayRS1.getString("ShippingAddress"));
    		billingAddresst.setText(displayRS1.getString("BillingAddress"));
    		telephonet.setText(displayRS1.getString("TelephoneNo"));
    		creditCardNot.setText(displayRS1.getString("CreditCardNo"));
    		
 			//Get the customer's Order information
 			query = "SELECT * FROM Orders WHERE OrderNo="+this.OrderNumber;
			displayRS1 = fact.excuteQuery(query);   
			
			//Format the retrived date
			Date retrnedDate = new Date();
			retrnedDate	= displayRS1.getDate("OrderDate");
			SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
 			StringBuilder stringdate = new StringBuilder( dateformat.format( retrnedDate ) );
        
        	//Display the cutomer's order information
			orderNot.setText(Integer.toString(displayRS1.getInt("OrderNo")));
	    	orderDatet.setText(stringdate.toString());
	    	orderStatust.setText(displayRS1.getString("Status"));
			
			retrnedDate	= displayRS1.getDate("ShippingDate");
 			stringdate = new StringBuilder( dateformat.format( retrnedDate ) );	    	
	    	shippingDatet.setText(stringdate.toString());
    		
 			//Get the Order details
 			query = "SELECT Products.ISBN, Products.Title, Products.Publisher, Products.Author, OrderDetails.Quantity, OrderDetails.Price, OrderDetails.Tax, OrderDetails.Postage FROM Products, OrderDetails WHERE OrderDetails.ISBN=Products.ISBN And OrderDetails.OrderNo="+this.OrderNumber;
			displayRS1 = fact.excuteQuery(query);   
			OrderTableModel orderTab = new OrderTableModel(displayRS1);
			
			//display the order details in the table provided
			table = new JTable(orderTab);
    		table.setPreferredScrollableViewportSize(new Dimension(400, 230));
		}
		catch(Exception ex)
		{
			System.out.println("Invalid Input 0"+ ex. getMessage());
		}
		//=================================================== 		
		
		c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0,0,5,5);
        gridbag.setConstraints(customerName, c);
        p2.add(customerName);

		c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(customerNamet, c);	    
	    p2.add(customerNamet);

		c.gridx = 3;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(email, c);	    
	    p2.add(email);

		c.gridx = 4;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(emailt, c);	    
	    p2.add(emailt);

		c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(shippingAddress, c);	    
	    p2.add(shippingAddress);

		c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(shippingAddresst, c);	    
	    p2.add(shippingAddresst);

		c.gridx = 3;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(billingAddress, c);	    
	    p2.add(billingAddress);
	    
		c.gridx = 4;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(billingAddresst, c);
	    p2.add(billingAddresst);
	    
		c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(telephone, c);
	    p2.add(telephone);
	    
		c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(telephonet, c);
	    p2.add(telephonet);
	    
		c.gridx = 3;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(creditCardNo, c);
	    p2.add(creditCardNo);
	    
		c.gridx = 4;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(creditCardNot, c);
	    p2.add(creditCardNot);
	    
	   	/////////////////////////////////////////////////////	   	
		c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(30,0,5,5);
        gridbag.setConstraints(orderNo, c);
        p2.add(orderNo);

		c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(orderNot, c);	    
	    p2.add(orderNot);

		c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(0,0,5,5);
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(orderDate, c);	    
	    p2.add(orderDate);

		c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(orderDatet, c);	    
	    p2.add(orderDatet);

		c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(orderStatus, c);	    
	    p2.add(orderStatus);

		c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(orderStatust, c);	    
	    p2.add(orderStatust);

		c.gridx = 3;
        c.gridy = 5;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(shippingDate, c);	    
	    p2.add(shippingDate);
	    
		c.gridx = 4;
        c.gridy = 5;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(shippingDatet, c);
	    p2.add(shippingDatet);

		/////////////////////////////////////////////////////
    	
    	JScrollPane scrollPane = new JScrollPane(table);
        p3.add(scrollPane, BorderLayout.CENTER);
		
		/////////////////////////////////////////////////////
		//Create the close button 
		close = new JButton("Close");
		JPanel cp = new JPanel(gridbag);
		
		c.insets = new Insets(0,0,15,15);
		gridbag.setConstraints(close, c);
		
		cp.setBackground(Color.white);
		cp.add(close);
		p3.add(cp, BorderLayout.SOUTH);		
		
		//When close is pressed close the program
		close.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{				
				dispose();
    		}});
		/////////////////////////////////////////////////////
		//make the background for t panels while and add them to the container
		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		p3.setBackground(Color.white);
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);
		
		//pack();
		setVisible(true);    
    }   
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
    class OrderTableModel extends AbstractTableModel {
        private String[] columnNames = {"ISBN",
                                        "Title",
                                        "Publisher",
                                        "Author",
                                        "Quantity",
                                        "Price",
                                        "Tax",
                                        "Postage"};
        private Object[][] data;
		
		public OrderTableModel(ResultSet orderRep)
		{
			
			try
			{	//get the number of items in the result set. 
				orderRep.last();
				int rscount = 0;
				rscount = orderRep.getRow();
				data = new Object[rscount][8];
				
				orderRep.first(); 	//return to the begining of the result set
				
					
				for (int j=0; j < rscount; j++) {
					//initialize the data array, to the values retrived from the result set  
	            	data[j][0] = orderRep.getString("ISBN");
	            	data[j][1] = orderRep.getString("Title");
	            	data[j][2] = orderRep.getString("Publisher");
	            	data[j][3] = orderRep.getString("Author");
	            	data[j][4] = new Integer(orderRep.getInt("Quantity"));
	            	data[j][5] = new Double(orderRep.getDouble("Price"));
	            	data[j][6] = new Double(orderRep.getDouble("Tax"));
	            	data[j][7] = new Double(orderRep.getDouble("Postage"));
	            	//System.out.println("Output: "+data[j][0]+" "+data[j][1]+" "+data[j][2]+" "+data[j][3]+" "+data[j][4]+" "+data[j][5]+" "+data[j][6]+" "+data[j][7]+" ");
	            	orderRep.next();
				}
			}
			catch(Exception ex)
			{
				System.out.println("Invalid Input 0"+ ex. getMessage());
			}
            	//System.out.print("My Quant is: "+holddata.quantity);
        }
		
		//Returns the values of the state variables of the object 
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

		//Make column editable
        public boolean isCellEditable(int row, int col) {
            //make the 6th and above columns and editable.
            if (col < 5) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            //fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------        	  
    public static void main(String[] args) 
    {

    }   
}