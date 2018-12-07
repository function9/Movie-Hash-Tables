import java.io.*;
import java.util.*; 


class HashTable{
	//private variables
private int maxsize;
private Movie[] array;
private int collisions;
private int maxProbe = 0;
private int steps = 0;

    /// TO COMPLETE
	//constructor that takes a movie array and converts it to a hashtable
	public HashTable(MovieArray Array)
	{
		int temp = Array.getNbmovies() * 2;
		int prime = getPrime(temp);
		maxsize = prime;
		array = new Movie[maxsize];
		int arraysize = Array.getNbmovies();
		for(int i = 0; i < arraysize; i++)
		{
			insert(Array.delete());
		}
	}

    

    
    ///////////////////////////////////////////////////////////////
    /////////////// get the first prime number after number 'min' 
    ///////////////// (from TextBook Chapter 11) /////////////////
    //////////////////////////////////////////////////////////////

    
    private int getPrime(int min)
    // returns 1st prime > min
    {
	for(int j = min+1; true; j++)
	    // for all j > min
	    if( isPrime(j) )
		// is j prime?
		return j;
	// yes, return it
    }
    // -------------------------------------------------------------
    private boolean isPrime(int n)
    // is n prime?
    {
	for(int j=2; (j*j <= n); j++)
	    // for all j
	    if( n % j == 0)
		// divides evenly by j?
		return false;
	// yes, so not prime
	return true;
	// no, so prime
    }
    //hash 1 function
    public int hash1(String key)
    {
    	int z = 0;
    	for(int i = 0; i < key.length();i++)
    	{
    		int c = key.charAt(i) + 1;
    		z = (z*256+c)%maxsize;
    	}
    	return z;
    }
    //hash 2 function
    public int hash2(String key)
    {
    	int temp = 0;
    	for(int i = 0;i < key.length();i++)
    	{
    		int g = key.charAt(i)+1;
    		temp = (256*temp+g)%5;
    	}
    	return 5 - temp;
    }
    //insert method for the hash table
    public void insert(Movie item)
    {
    	int counter = 0;
    	int h = hash1(item.getTitle());
    	int h2 = hash2(item.getTitle());
    	while(array[h] != null)
    	{
    		h = (h+ h2)%maxsize;
    		counter++;
    	}
    	array[h] = item;
    	collisions = counter+collisions;
    	steps = counter + steps;
    	if(counter > maxProbe)
    	{
    		maxProbe = counter;
    	}
    }
    //returns number of collisions
    public int getCol()
    {
    	return collisions;
    }
    //returns the maxsize of the table
    public int getMaxSize()
    {
    	return maxsize;
    }
    //returns the highest number the probe got to
    public int getMaxProbe()
    {
    	return maxProbe;
    }
    //saves the hash table to a text file in a specific format
    public void saveHashTable(String filename)
    {
    	try
    	{
    		PrintWriter writer = new PrintWriter(filename + ".txt");
    		writer.println(maxsize);
    		for(int i = 0; i < maxsize; i++)
    		{
    			writer.print(i + " ");
    			if(array[i] == null)
    			{
    				writer.println("*****");
    			}
    			else
    			{
    				writer.println(array[i].getOGLine());
    			}
    		}
    		writer.close();
    	}
    	catch(IOException e)
    	{
    		
    	}
    }
    //returns number of steps
    public int getStep()
    {
    	return steps;
    }
    //returns true if it finds the movie title and false if it doesnt
    public Boolean find(String movieTitle)
    {
    	steps = 0;
    	int h = hash1(movieTitle);
    	int s = hash2(movieTitle);
    	while(array[h] != null)
    	{
    		steps++;
    		if(array[h].getTitle().compareToIgnoreCase(movieTitle) == 0)
    		{
    			return true;
    		}
    		h = (h+s)%maxsize;
    	}
    	return false;
    }
}
