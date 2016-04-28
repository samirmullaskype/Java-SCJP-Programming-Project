import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.DefaultTableModel;

import java.text.*;
 
public class ShoppingCart extends JFrame {

    private boolean DEBUG = false;

	private JTable table;
	MyTableModel model;

	static JLabel centerlable;								//label for messages
	DecimalFormat Currency = new DecimalFormat("#0.00");	//format numbers to 2 decimel places
	
	//buttons used.
	private JButton continueShopping;
    private JButton emptyBasket;
    private JButton checkOut;
	private JButton reCalculate;
	
	ResultSetTableModelFactory factory; 
	Customer loggedcust;
	Orders myOrder;
	boolean empty = false;

    public ShoppingCart(ResultSetTableModelFactory f, Customer loggedcust, Orders myOrder) {

		//Initiates the values of the state variables of the object 
		this.factory = f; 
		this.loggedcust = loggedcust;
		this.myOrder = myOrder;
		
			//Set the size and titles of the window.
		setBounds(200,200,610,400);		// x,y,width,height
		setTitle("Shoppping Cart");
		setBackground(Color.white);
    	
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
		
		setLayout(new BorderLayout());

		JPanel p1 = new JPanel(new BorderLayout());
		JPanel p2 = new JPanel(gridbag);
    	JPanel p3 = new JPanel(new BorderLayout());
    	
    	/////////////////////////////////////////////////////
        //get the order, create a MyTableModel object , display order in table.
        model = new MyTableModel(myOrder);
        table = new JTable(model);
    	table.setPreferredScrollableViewportSize(new Dimension(500, 300));
    	
    	JScrollPane scrollPane = new JScrollPane(table);
        p1.add(scrollPane, BorderLayout.CENTER);
    	/////////////////////////////////////////////////////
    	
    	continueShopping = new JButton("Continue Shopping", new ImageIcon("images/cart.jpg"));
   		emptyBasket = new JButton("Empty Basket", new ImageIcon("images/emptybasket.jpg"));
    	checkOut = new JButton("Checkout", new ImageIcon("images/checkout.jpg"));
    	reCalculate = new JButton("Recalculate");;
    	
    	continueShopping.setBackground(Color.white);	
   		emptyBasket.setBackground(Color.white);	
    	checkOut.setBackground(Color.white);
    	reCalculate.setBackground(Color.white);
    	
    	c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        gridbag.setConstraints(continueShopping, c);
		p2.add(continueShopping);
    	
    	c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(emptyBasket, c);
		p2.add(emptyBasket);
    	
    	c.gridx = 2;
        c.gridy = 0;
        gridbag.setConstraints(checkOut, c);
		p2.add(checkOut);
    	
    	c.gridx = 3;
        c.gridy = 0;
        gridbag.setConstraints(reCalculate, c);
		p2.add(reCalculate);
		
		/////////////////////////////////////////////////////
		//display the total of the order on the label
		centerlable = new JLabel(" ");
		centerlable.setText("Please, recalculate after changing the quantity. Pastage: "+getMyOrder().postage+" Tax: "+getMyOrder().tax+" Total Cost: "+ Currency.format(this.myOrder.caluclateTotal()));
		
		p3.add(centerlable, BorderLayout.CENTER);		//add label to the container
		/////////////////////////////////////////////////////
		//===================================================		
		//when the continueShopping button is pressed open the Shopping window and close this window.
		continueShopping.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				ShoppingTry qf = new ShoppingTry(getFactory(), getLoggedCust(), getMyOrder());
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				dispose();	
    		}});		
		//===================================================
		//When the empty button is press empty the order object,by assigning it a new orders object.
		//create an new MyTableModel object and fire table data change.
		//Then dipslay the total cost to be 0.00 on the label 
		emptyBasket.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				empty = true;
				Orders myOrder2 = new Orders();
				setMyOrder(myOrder2);
				MyTableModel tm = new MyTableModel(myOrder2);
				table.setModel(tm);
				tm.fireTableDataChanged();
				
				centerlable.setText("Please, recalculate after changing the quantity. Pastage: "+getMyOrder().postage+" Tax: "+getMyOrder().tax+" Total Cost: "+ Currency.format(0.00));
					
    		}});		
		//===================================================
		//When the check out button is press check if the basket (itemslist) is empty, then display message
		//if there are items then open the details confirmation window and close this window. 
		checkOut.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				Orders ordz1 = getMyOrder();
				
				if(ordz1.itemslist.size() == 0)
				{
					centerlable.setText("Basket is Empty. \n");
				}
				else
				{
					ConfirmOrder corder = new ConfirmOrder(getFactory(), getLoggedCust(), getMyOrder());
					dispose();
				}
				
    		}});
		//===================================================
		//When the recalculate button is pressed, preform the following
		reCalculate.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				int newquant = -1;
				int tsize = table.getRowCount();
				Orders myOrder3 = new Orders();
				myOrder3 = getMyOrder();						//get the customer's order
				OrdersDetails newBook = new OrdersDetails();
				
				//get all the new quantities edited by the user and add them to the order if
				//they are valid quantities. Otherwise display error message.
				for (int j=0; j < myOrder3.itemslist.size(); j++) {  //tsize; j++) {
					newBook = (OrdersDetails) myOrder3.itemslist.get(j);
					newquant = (Integer) table.getValueAt(j, 5);
					
					if(newquant >= 0)
					{
						newBook.quantity = newquant;
						myOrder3.itemslist.set( j , newBook);
						
					}else if (newquant < 0)
					{

						JOptionPane.showMessageDialog(null,"Please enter a positive number or zero.");	
					}	
				}				
				
				//find all the items with quantity 0, and remove them from the basket (itemslist).
				//Whenever an item is removed start the search again.
				for (int j=0; j < myOrder3.itemslist.size(); j++) {
					newBook = (OrdersDetails) myOrder3.itemslist.get(j);
					if(newBook.quantity == 0) 
					{
						myOrder3.itemslist.remove(j);
						j=0;
					}
				}
				
				//set the value for the new order after remove those with 0 value.
				setMyOrder(myOrder3);
				// close the current window and open the Shopping Cart window. 
				dispose();
				ShoppingCart nsc = new ShoppingCart(factory, getLoggedCust(), getMyOrder());
    		}});
		//===================================================
		//if the user closes this window terminate the program.
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
        }});
		
		//set the backgrounds for the panels to white, and add them to the container.
		p2.setBackground(Color.white);
		p3.setBackground(Color.white);
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH); 
		add(p3, BorderLayout.CENTER);
		
		//pack();
		setVisible(true);
    }
    
    //Returns the values of the state variables of the object 
    public Orders getMyOrder()
    {
    	return this.myOrder;
    }
    public ResultSetTableModelFactory getFactory()
    {
    	return this.factory;
    }
    public Customer getLoggedCust()
    {
    	return this.loggedcust;
    }
    public void setMyOrder(Orders neworder)
    {
    	this.myOrder = neworder;
    }
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"ISBN",
                                        "Title",
                                        "Publisher",
                                        "Author",
                                        "Price",
                                        "Quantity"};
        private Object[][] data;
		
		public MyTableModel(Orders od)
		{
			OrdersDetails holddata = new OrdersDetails();
			data = new Object[od.itemslist.size()][6];
			
			//initialize the data array with items from the Orders object.
			for (int j=0; j < od.itemslist.size(); j++) {
            	holddata = (OrdersDetails) od.itemslist.get(j);
            	
            	data[j][0] = holddata.singleitem.getIsbn(); 
            	data[j][1] = holddata.singleitem.getTitle();
            	data[j][2] = holddata.singleitem.getPublisher();
            	data[j][3] = holddata.singleitem.getAuthor();
            	data[j][4] = new Double(holddata.singleitem.getPrice());
            	data[j][5] = new Integer(holddata.quantity);
        	}
		}
		
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

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 5) {
                return false;
            } else {
                return true;
            }
        }

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
		//ShoppingCart qf = new ShoppingCart();
    }   
}