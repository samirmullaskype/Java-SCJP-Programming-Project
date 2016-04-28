/**
 * @(#)Books.java
 *
 *
 * @author 
 * @version 1.00 2008/3/25
 */

    public class Books
    {
		private String title; 			//Title of the book 
		private String author; 			//Name of author(s) 
		private String publisher; 		//Publisher details 
		private int yearPublished; 		//Year of publication 
		private int edition; 			//Edition number 
		private String scn; 			//International Standard Book Number 
		private double price; 			//Price in £ 
		
		public Books(){}
		
		public Books(String title, String author, String publisher, int yearPublished, int edition, String scn, double price) 	
		{//Constructor – creates a new object of the class Books 
			this.title = title; 
			this.author = author; 
			this.publisher = publisher; 
			this.yearPublished = yearPublished;
			this.edition = edition; 
			this.scn = scn;
			this.price = price;
		}
		
		//Returns the values of the state variables of the object 
		public String getTitle()  	
		{ return title; }
		public String getAuthor()
		{ return author; }
		public String getPublisher()
		{ return publisher; }
		public int getYearPublished()
		{ return yearPublished; }
		public int getEdition()
		{ return edition; }
		public String getIsbn()
		{ return scn; }
		public double getPrice()
		{ return price; }
		//----------------------------------------------------------
		
		//Changes the values of the state variables of the object	
		public void setTitle(String title)
		{ this.title = title; }
		public void setAuthor(String author)
		{ this.author = author; }
		public void setPublisher(String publisher)
		{ this.publisher = publisher; }
		public void setYearPublished(int yearPublished)
		{ this.yearPublished = yearPublished; }
		public void setEdition(int edition)
		{ this.edition = edition; }
		public void setIsbn(String isbn)
		{ this.scn = isbn; }
		public void setPrice(double price) 	 
		{ this.price = price; }
		//----------------------------------------------------------
		
		//Returns the title, author and price as a string 
		public String getBookDetails() 	
		{
			String bdetails = "Title: " + getTitle() + ",  Author: " + getAuthor() + ", Price: " + getPrice();
			return bdetails;
		}
		
		//Returns a string representation of the object 
		public String toString() 	
		{
			return "Title: " + this.title + "\nAuthor: " + this.author + "\nPublisher: " + this.publisher + "\nYear Published: "+this.yearPublished+"\nEdition: "+this.edition+"\nISBN: "+this.scn+"\nPrice: "+this.price+ "\n";
		}
		
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////

class Book extends Books
{
	private int volume; 			//volume 
	
	//Constructors – create a new object of the class Book
	public Book()
	{
		this(null, null, null, -1, -1, null, -1.0, -1);
	}
	public Book(String title, String author, String publisher, int yearPublished, int edition, String scn, double price, int volume)
	{
		super(title, author, publisher, yearPublished, edition, scn, price);
		this.setVolume(volume);
	}
	
	//Returns the values of the state variables of the object 
	public int getVolume() 	
	{ return volume; }
	
	public void setVolume(int volume)
	{  this.volume=volume; }
	
}

class MusicCD extends Books
{
	private int volume; 		//volume 
	private String artist;		//artist

	//Constructors – create a new object of the class MusicCD
	public MusicCD()
	{
		this(null, null, null, -1, -1, null, -1.0, -1, null);
	}
	public MusicCD(String title, String author, String publisher, int yearPublished, int edition, String scn, double price, int volume, String artist)
	{
		super(title, author, publisher, yearPublished, edition, scn, price);
		this.setVolume(volume);
		this.setArtist(artist);
	}
	
	//Returns the values of the state variables of the object 
	public int getVolume() 	
	{ return volume; }
	public String getArtist() 	
	{ return artist; }
	
	//Changes the values of the state variables of the object
	public void setVolume(int volume)
	{  this.volume=volume; }
	public void setArtist(String artist)
	{  this.artist=artist; }
}

class DVD extends Books
{
	private String director;		//director, 
	private String[] actors;		//actor(s) and/or actress(es), 
	private int runningtime;		//running time in mins 

	//Constructors – create a new object of the class DVD
	public DVD()
	{
		this(null, null, null, -1, -1, null, -1.0, null, null, -1);
	}
	public DVD(String title, String author, String publisher, int yearPublished, int edition, String scn, double price, String director, String[] actors, int runningtime)
	{
		super(title, author, publisher, yearPublished, edition, scn, price);
		this.setDirector(director);
		this.setActors(actors);
		this.setRunningTime(runningtime);
	}
	
	//Returns the values of the state variables of the object 
	public String getDirector() 	
	{ return director; }
	public String[] getActors() 	
	{ return actors; }
	public int getRunningTime() 	
	{ return runningtime; }
	
	//Changes the values of the state variables of the object
	public void setDirector(String director)
	{  this.director=director; }
	public void setActors(String[] actors)
	{  this.actors=actors; }
	public void setRunningTime(int runningtime)
	{  this.runningtime=runningtime; }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////    