import java.io.*;
import java.util.*; 


class HashTable{


    /// TO COMPLETE



    

    
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
    
}
