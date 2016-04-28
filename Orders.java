/**
 * @(#)Orders.java
 *
 *
 * @author 
 * @version 1.00 2008/3/30
 */
import java.util.*;

public class Orders {

	ArrayList itemslist;			//List of titles (and numbers) ordered. 
	Date orderplaced;				//Date the order was placed. 
	double totalcost;				//Cost of all the titles ordered, postage, tax and total cost of the order. 
	double tax = 17.0;				//Tax.
	double postage = 20.0;			//Postage.
	boolean statusOfOrder=false;	//Status of the order. The status of an order is not yet shipped or shipped. 
	Date shipping;					//Expected date or actual date of shipping. 

	//Constructors – create a new object of the class Orders
    public Orders() {
    	this.itemslist = new ArrayList();		
		this.orderplaced = new Date();		
		this.totalcost = 0.0;			
		this.shipping = new Date();
    }
    public Orders(ArrayList itemslist, Date orderplaced, double totalcost, boolean statusOfOrder, Date shipping) 
    {
		this.itemslist = itemslist;		
		this.orderplaced = orderplaced;		
		this.totalcost = totalcost;			
		this.statusOfOrder = statusOfOrder;
		this.shipping = shipping;    	
    }
    
	//Returns the values of the state variables of the object 
	public ArrayList getItemslist()  	
	{ return this.itemslist; }    
	public Date getOrderplaced()  	
	{ return this.orderplaced; }  
	public double getTotalcost()  	
	{ return this.totalcost; }
	public double getTax()  	
	{ return this.tax; }
	public double getPostage()  	
	{ return this.postage; }	  
	public boolean getStatusOfOrder()  	
	{ return this.statusOfOrder; }  
	public Date getShipping()  	
	{ return this.shipping; } 
	/////////////////////////////// 
	
	//Changes the values of the state variables of the object
   	public void setItemslist(ArrayList itemslist)
	{ this.itemslist = itemslist; } 
   	public void setOrderplaced(Date orderplaced)
	{ this.orderplaced = orderplaced; } 
   	public void setTotalcost(double totalcost)
	{ this.totalcost = totalcost; } 
   	public void setStatusOfOrder(boolean statusOfOrder)
	{ this.statusOfOrder = statusOfOrder; } 
   	public void setShipping(Date shipping)
	{ this.shipping = shipping; } 
    ///////////////////////////////
    
    //Calculates the total vale of the orders in the basket.
    public double caluclateTotal()
	{ 
		double totalCostOfOrder = 0;

		for (int j=0; j < itemslist.size(); j++) {
			totalCostOfOrder += ((OrdersDetails) itemslist.get(j)).singleitem.getPrice() * ((OrdersDetails) itemslist.get(j)).quantity;
        }
        
        if(itemslist.size() > 0)
        {
        	totalCostOfOrder += getPostage();
        	totalCostOfOrder += (getTax() * totalCostOfOrder) / 100;
        }
           
        setTotalcost(totalCostOfOrder);
        
        return totalCostOfOrder;
	}
	
	//Display the orders in the basket on the tset screen.
	public void displayOrd()
	{ 
		ListIterator it = this.itemslist.listIterator();
		while(it.hasNext())
		{
			System.out.println( it.next() );
		}
	}
	
	//Check if an OrdersDetails Object has already been added to the itemslist
	public Boolean checkPresent(Object obj)
	{ 
		OrdersDetails bk1 = (OrdersDetails) obj;
  
		if(this.itemslist.contains(bk1))
		{
			return true;	//if the OrdersDetails object already exists in the itemslist return true
		}
		else
		{
			return false;	//else return true
		}  

	}
	
	//Check if a book title has already been added to the basket, and return its quantity.
	public int checkTitle(String tit)
	{ 
		OrdersDetails bkfind = new OrdersDetails();
		
		//check all the titles in the ArrayList
		for (int j=0; j < this.itemslist.size(); j++) {
            bkfind = (OrdersDetails) this.itemslist.get(j);
            if(bkfind.singleitem.getTitle().equals(tit))
            {
            	//if the title exists, return it's quantity 
            	return bkfind.quantity;
            }
        }
        //if the title does not exist in the list return 0.
        return 0;
	}
	
	//Find the index of a title on the ArrayList from it's title
	public int findIndex(String tit)
	{ 
		OrdersDetails bkfind = new OrdersDetails();
		
		//check all the titles in the ArrayList
		for (int j=0; j < this.itemslist.size(); j++) {
            bkfind = (OrdersDetails) this.itemslist.get(j);
            if(bkfind.singleitem.getTitle().equals(tit))
            {
            	//return the index of the ArrayList for the item with the given title.
            	return j;
            }
        }
        //return -1 if the title is not prest in the ArrayList.
        return -1;
	}
	
	//sort the list
	public void sortList()
	{ 
		Collections.sort(this.itemslist);
	}
	
	//delete and object of OrdersDetails from the ArrayList.
	public void deleteListItem(OrdersDetails ordde)
	{ 
		this.itemslist.remove(this.itemslist.indexOf(ordde));
	}
	
	//returns the size of the ArrayList.
	public int checksize()
	{ 
        return this.itemslist.size();
	}
	
	//Adds an object of OrdersDetails to the array list.
    public void addToItemList(OrdersDetails ord1)
	{ 
		this.itemslist.add( ord1 ); 
		this.totalcost = this.totalcost + ord1.singleitem.getPrice();
		//this.sortList();///////////////////////////////////////////////////////////////////////////////
	}
}

class OrdersDetails
{
	Books singleitem;	//an Object of the Book class, which hold all the information about the book.
	int quantity;		//the quantity of the Book in the customer basket.
	
	//Constructors – create a new object of the class OrdersDetails
	public OrdersDetails() 
	{
		singleitem = new Books();
		quantity = 0;		
    }
    public OrdersDetails(Books obj) 
	{
		this.singleitem = obj; //(Books) obj;
		this.quantity = 1;		
    }
    public OrdersDetails(Books obj, int quantity) 
	{
		this.singleitem = obj; //(Books) obj;
		this.quantity = quantity;		
    }
    
    //adds 1 to the quanity.
	public void addQuant() 
	{
		this.quantity++;		
    }
    
    //Returns a string representation of the object. 
    public String toString()
	{
		return "Title: " + this.singleitem.getTitle() + ", Author: " + this.singleitem.getAuthor() + ", Quantity: " + this.quantity;	
	}	
}
