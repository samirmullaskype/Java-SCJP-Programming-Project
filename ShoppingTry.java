import java.awt.*;				// color, font
import javax.swing.*;			// All swing components are here
import javax.swing.border.*;	


import javax.swing.table.AbstractTableModel;
import javax.swing.event.*;

import java.awt.Component;
import java.sql.SQLException;
import java.awt.event.*;

public class ShoppingTry extends JFrame {
    
    ResultSetTableModelFactory factory; 	// A factory to obtain our table data
    Customer loggedcust;					//Logged in customer	
    Orders myOrder;							//customer's order
    
    private boolean DEBUG = false;
    private JTable table;
    private JTextField statusText;
        
    JLabel searchTitle;
    JTextField searchTitlet;

    JLabel isbn;			//Label for isbn number.
    JLabel publisher;		//Label for publisher of the book.
    JLabel price;			//Label for price of the book.
    JLabel edition;			//Label for edition of the book.
    JLabel title;			//Label for title of the book.
    JLabel author;			//Label for author of the book.
    JLabel yearPub;			//Label for the year the book was published.
    JLabel volume;			//Label for volume of the book.
    JTextField isbnt;		//Text field for the isbn number.
    JTextField publishert;	//Text field for the publisher of the book.
    JTextField pricet;		//Text field for the price of the book.
    JTextField editiont;	//Text field for the edition of the book.
    JTextField titlet;		//Text field for the title of the book.
    JTextField authort;		//Text field for the author of the book.
    JTextField yearPubt;	//Text field for the year the book was published.
    JTextField volumet;     //Text field for the volume of the book.
    
    JButton	search; 
    JButton displayall;    
    JButton addtocart;
    JButton close;
    
    Boolean cselected = false;
     
    public ShoppingTry(ResultSetTableModelFactory f, Customer loggedcust, Orders myOrd) {
		
		//Initiates the values of the state variables of the object 
		this.factory = f;
		this.loggedcust = loggedcust;
		this.myOrder = myOrd;
		
		//Set the size and titles of the window.
		setBounds(200,200,810,440);		// x,y,width,height
		setTitle("Search Products");
		setBackground(Color.white);

		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
		
		setLayout(new BorderLayout());
		
		//Panels
		JPanel p1 = new JPanel(gridbag);
		JPanel p2 = new JPanel(new BorderLayout());
		JPanel p3 = new JPanel(gridbag);
		JPanel p4 = new JPanel(gridbag);
		
    	/////////////////////////////////////////////////////
    	searchTitle = new JLabel("Search Title or ISBN: ");
    	searchTitlet = new JTextField(20);
    	search = new JButton("Search", new ImageIcon("images/search.jpg")); 
    	displayall = new JButton("Display all", new ImageIcon("images/table.jpg"));

		search.setBackground(Color.white);	
		displayall.setBackground(Color.white);	
		
		c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        gridbag.setConstraints(searchTitle, c);
		p1.add(searchTitle);
		
		c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(searchTitlet, c);
		p1.add(searchTitlet);
		
		c.gridx = 2;
        c.gridy = 0;
        gridbag.setConstraints(search, c);
		p1.add(search);
		
		c.gridx = 3;
        c.gridy = 0;
        gridbag.setConstraints(displayall, c);
		p1.add(displayall);
		
		
		//==================================================
		//when the search button is pressed, display the results returned from the quere 
		//entered by the user in the table, if the quere enter was not empty.
		//Otherwise do nothing.
		search.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{

				if(!(searchTitlet.getText()).equals(""))
				{
					displayMyResults(searchTitlet.getText());
				}
				else
				{
					//System.out.println("Empty");
				}
    		}});
    	//When the display all button is pressed then display all the books in the database on the table.
		displayall.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				displayMyResults(" ");
    		}});
		//==================================================
		////////////////////////////////////////////////////
       	//Create a scrollable table abd add it to the panel
       	table = new JTable();		
        JScrollPane scrollPane = new JScrollPane(table);
        p2.add(scrollPane, BorderLayout.CENTER);
		
		////////////////////////////////////////////////////
		isbn = new JLabel("ISBN: ");
	    publisher = new JLabel("Publisher: ");
	    price = new JLabel("Price: ");
	    edition = new JLabel("Edition: ");
	    title = new JLabel("Title: ");
	    author = new JLabel("Author: ");
	    yearPub = new JLabel("Year Published: ");
	    volume = new JLabel("Volume: ");
	    isbnt = new JTextField(15);
	    publishert = new JTextField(15);
	    pricet = new JTextField(15);
	    editiont = new JTextField(15);
	    titlet = new JTextField(15);
	    authort = new JTextField(15);
	    yearPubt = new JTextField(15);
	    volumet = new JTextField(15);

		c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0,0,0,0);
        gridbag.setConstraints(isbn, c);
        p3.add(isbn);

		c.gridx = 1;
        c.gridy = 0;
        gridbag.setConstraints(isbnt, c);	    
	    p3.add(isbnt);

		c.gridx = 2;
        c.gridy = 0;
        gridbag.setConstraints(title, c);	    
	    p3.add(title);

		c.gridx = 3;
        c.gridy = 0;
        gridbag.setConstraints(titlet, c);	    
	    p3.add(titlet);

		c.gridx = 0;
        c.gridy = 1;
        gridbag.setConstraints(publisher, c);	    
	    p3.add(publisher);

		c.gridx = 1;
        c.gridy = 1;
        gridbag.setConstraints(publishert, c);	    
	    p3.add(publishert);

		c.gridx = 2;
        c.gridy = 1;
        gridbag.setConstraints(author, c);	    
	    p3.add(author);
	    
		c.gridx = 3;
        c.gridy = 1;
        gridbag.setConstraints(authort, c);
	    p3.add(authort);
	    
		c.gridx = 0;
        c.gridy = 2;
        gridbag.setConstraints(price, c);
	    p3.add(price);
	    
		c.gridx = 1;
        c.gridy = 2;
        gridbag.setConstraints(pricet, c);
	    p3.add(pricet);
	    
		c.gridx = 2;
        c.gridy = 2;
        gridbag.setConstraints(yearPub, c);
	    p3.add(yearPub);
	    
		c.gridx = 3;
        c.gridy = 2;
        gridbag.setConstraints(yearPubt, c);
	    p3.add(yearPubt);
	    
		c.gridx = 0;
        c.gridy = 3;
        gridbag.setConstraints(edition, c);
	    p3.add(edition);
	    
		c.gridx = 1;
        c.gridy = 3;
        gridbag.setConstraints(editiont, c);
	    p3.add(editiont);
	    
		c.gridx = 2;
        c.gridy = 3;
        gridbag.setConstraints(volume, c);
	    p3.add(volume);
	    
		c.gridx = 3;
        c.gridy = 3;
        gridbag.setConstraints(volumet, c);
	    p3.add(volumet);
	    
		////////////////////////////////////////////////////
		addtocart = new JButton("Add to Cart", new ImageIcon("images/cart.jpg"));
		
		addtocart.setBackground(Color.white);	
			
		c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(30,0,50,50);
        gridbag.setConstraints(addtocart, c);
		p3.add(addtocart);		
		//===================================================

		myOrder = getMyOrder();
		
		//When the add to cart button is pressed, preform the following.
	
		addtocart.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{	//check that fisrt that a row is selected.
				if(cselected == true)
				{
					//Create an object of the class OrdersDetails, and initialise it with details of the the selected book.
					Books chosenbook = new Books(titlet.getText(), authort.getText(), publishert.getText(), Integer.parseInt(yearPubt.getText()), Integer.parseInt(editiont.getText()), isbnt.getText(), Double.parseDouble(pricet.getText()));
					OrdersDetails ordde = new OrdersDetails(chosenbook);
				
					//If the customers order basket is empty, then just add the new OrdersDetails object.
					if(myOrder.checksize() == 0)
					{ 
						myOrder.addToItemList(ordde);
					
					}
					else //else if the customers order basket already contains objects
					{
						//Check if the title is already in the orders basket. 
						int quant = myOrder.checkTitle(titlet.getText());
						
						//if the title is not alread in the orders basket, then add it.
						if( quant == 0)
						{	 
							myOrder.addToItemList(ordde);
							
						}
						else
						{ 	//Otherwise, find the titles in index in the list (basket).
							//remove the title form list.
							//then add an object of the same title but the quantity added 1.
							int rindex = myOrder.findIndex(titlet.getText());
							OrdersDetails ordde2 = (OrdersDetails) myOrder.itemslist.get(rindex);
							myOrder.itemslist.remove(rindex);
															
							OrdersDetails ordde3 = new OrdersDetails(chosenbook, quant+1);
							myOrder.addToItemList(ordde3);

						} 
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please select a product.");	
				}		
    		}});
		//===================================================
		close = new JButton("Close", new ImageIcon("images/close.jpg"));
		
		close.setBackground(Color.white);	
		
		c.gridx = 3;
        c.gridy = 4;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(close, c);
		p3.add(close);
		//===================================================
		//When the close butten is presses open the logged in window and close this window.
		close.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e)
			{
				LoggedinScreen lins = new LoggedinScreen(getLoggedCust(), getMyOrder(),-1,-1);
				///////////////////////////////////////////////////////////					
				dispose();
		
    		}});
    	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
        }});
		////////////////////////////////////////////////////
		
		p1.setBackground(Color.white);
		p3.setBackground(Color.white);
		p4.setBackground(Color.white);
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);
		
		//pack();
		setVisible(true);
    }
    
    //Returns the values of the state variables of the object 
    public Orders getMyOrder()
    {
    	return myOrder;
    }
    /*public ResultSetTableModelFactory getFactory()
    {
    	return factory;
    }*/
    public Customer getLoggedCust()
    {
    	return loggedcust;
    }
//-------------------------------------------------------------------------------
    public void mydisplayQueryResults(final String q) {
	
	// In order to allow the feedback message to be displayed, we don't
	// run the query directly, but instead place it on the event queue
	// to be run after all pending events and redisplays are done.
	EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			
			// Use the factory object
			// to obtain a TableModel object for the query results
			// and display that model in the JTable component.
			table.setModel(factory.getResultSetTableModel(q));
			// We're done, so clear the feedback message 
		    }
		    catch (Exception ex) {
			// If something goes wrong, clear the message line
			// Then display the error in a dialog box
			JOptionPane.showMessageDialog(ShoppingTry.this,
			          new String[] {  // Display a 2-line message
				      ex.getClass().getName() + ": ",
				      ex.getMessage()
				  });
		    }
		}
	    });
    }
//-------------------------------------------------------------------------------
    public void displayMyResults(String q1) {

        // Query the database for the ISBN or Title entered by the user
        String q = "SELECT * FROM Products WHERE ISBN like '%"+q1+"%' OR Title like '%"+q1+"%'";
        mydisplayQueryResults(q);
        
        // Set the dimensions of the table to display the results in.
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));
        // Allow table single row selection. 
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// add a listener to display the details of the selected row in the appropriate text field.
        table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = table.getSelectedRow();
                        if (viewRow < 0) {
                            //statusText.setText("");
                        } else {
                            
                            cselected = true;
                            
                            int modelRow = 
                                table.convertRowIndexToModel(viewRow);

                            isbnt.setText(String.format((String)table.getValueAt(modelRow, 0)));
                            titlet.setText(String.format((String)table.getValueAt(modelRow, 1)));
                            publishert.setText(String.format((String)table.getValueAt(modelRow, 2)));
                            authort.setText(String.format((String)table.getValueAt(modelRow, 3)));
                            pricet.setText(String.format((String)table.getValueAt(modelRow, 4)));
                            yearPubt.setText(String.format((String)table.getValueAt(modelRow, 5)));
                            editiont.setText(String.format((String)table.getValueAt(modelRow, 6)));
                            volumet.setText(String.format((String)table.getValueAt(modelRow, 7)));					
                        }
                    }
                }
        );		
    }
    
    public static void main(String[] args) 
    {
	
    }    
}