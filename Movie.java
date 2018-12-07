import java.io.*;
import java.util.Scanner;

class Movie{

    // Common private variables 
	private String title,studio,price,rating,year,genre, ogline;
	
    // Constructor: construct the Movie class with the 3 given parameters 
   
	public Movie(String Title, String Studio, String Price, String Rating, String Year, String Genre, String OGLine)
   {
	   title = Title;
	   studio = Studio;
	   price = Price;
	   rating = Rating;
	   year = Year;
	   genre = Genre;
	   ogline = OGLine;
   }
	
	
    /////////////////////////////////////////////////////////
    // methods 
    /////////////////////////////////////////////////////////
   //returns a movies title
	public String getTitle()
	{
		return title;
	}
	//returns the movies studio
	public String getStudio()
	{
		return studio;
	}
	//returns the movies rating
	public String getRating()
	{
		return rating;
	}
	//returns the movies price
	public String getPrice()
	{
		return price;
	}
	//returns the movies year
	public String getYear()
	{
		return year;
	}
	//returns the movies genre
	public String getGenre()
	{
		return genre;
	}
	//returns the original line with all information about the movie
	public String getOGLine()
	{
		return ogline;
	}
	
}