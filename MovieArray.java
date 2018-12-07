import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////////////////////////
class MovieArray{
	private Movie[] array;
	private int nbmovies = 0;
    private int maxsize;
    private int steps;
    //constructor to make a movie array, takes a filename and a maxsize as parameters
	public MovieArray(String filename, int MaxSize)
	   {
		steps = 0;
		int count = 0;
		   maxsize = MaxSize;
		   array = new Movie[maxsize];
		   try
		   {
		   File file = new File(filename);
		   Scanner scanner = new Scanner(file);
		   while(scanner.hasNextLine() && count < maxsize)
		   {
			   String line = scanner.nextLine();
			   insert(line);
			   //System.out.println(line);
			   count++;
		   }
		   }
		   catch(FileNotFoundException e)
		   {
			 e.printStackTrace();  
		   }
	}
	//insert method for the array
	public void insert(String line)
	{
		String[] linearray = line.split("[|]");
		Movie newmovie = new Movie(linearray[0],linearray[1],linearray[2],linearray[3],linearray[4],linearray[5],line);
		array[nbmovies] = newmovie;
		nbmovies++;
	}
	//returns maxsize
	public int getSize()
	{
		return maxsize;
	}
	//saves the movie array to a text file
	public void save(String filename)
	{
		try 
		{
			PrintWriter writer = new PrintWriter(filename + ".csv");
			for(int i = 0; i < nbmovies;i++)
			{
				writer.println(array[i].getOGLine());
			}
			writer.close();
		}
		catch(IOException e)
		{
			
		}
	}
	
	//insertion sort method
	public void insertionSort()
	{
		int in,out;
		Movie temp;
		for(out = 1; out < nbmovies;out++)
		{
			temp = array[out];
			in = out;
			while(in>0 && array[in-1].getTitle().compareToIgnoreCase(temp.getTitle()) >= 0)
			{
				array[in] = array[in-1];
				in--;
			}
			array[in] = temp;
			//System.out.println(temp.getTitle());
		}
	}
	//shell sort method
	public void shellSort()
	{
		int n = array.length;
		int inner,outer;
		Movie temp;
		int h=1;
		while(h<=n/3)
		{
			h=(h*3)+1;
		}
		while(h>0)
		{
			for(outer=h;outer<n;outer++)
			{
				temp=array[outer];
				inner=outer;
				while(inner>h-1 && array[inner-h].getTitle().compareToIgnoreCase(temp.getTitle()) >= 0)
				{
					array[inner] = array[inner-h];
					inner = inner -h;
				}
				array[inner] = temp;
			}
			h=(h-1)/3;
		}
	}
	// merge sort method
	public void mergeSort() {
		Movie[] workSpace = new Movie[nbmovies];
		recMergeSort(workSpace,0,nbmovies-1);
	}
	//helper method for merge sort
	public void recMergeSort(Movie[] workSpace, int lower, int upper) {
		if (lower==upper)
			return;
		else {
			int mid=(lower+upper)/2;
			recMergeSort(workSpace,lower,mid);
			recMergeSort(workSpace,mid+1,upper);
			merge(workSpace,lower,mid+1,upper);
			 }
	}
	//helper method for recMergeSort
	public void merge(Movie[] workSpace, int lowPtr, int highPtr, int upperBound) {
		int j = 0;
		int lowerBound = lowPtr;
		int mid = highPtr-1;
		int n = upperBound-lowerBound+1;
		
		while(lowPtr <= mid && highPtr <= upperBound)
		if(array[lowPtr].getTitle().compareTo(array[highPtr].getTitle())<0)
			workSpace[j++] = array[lowPtr++];
		else
			workSpace[j++] = array[highPtr++];
		
			while(lowPtr <= mid)
				workSpace[j++] = array[lowPtr++];
			
			while(highPtr <= upperBound)
				workSpace[j++] = array[highPtr++];
			
			for(j=0; j<n; j++)
				array[lowerBound+j] = workSpace[j];
	}
	
	//quicksort method
	public void quickSort() {
		recQuickSort(0, nbmovies-1);
	}
	//helper method for quick sort
	public void recQuickSort(int left,int right) {
		if(right-left<=0) return;
		else {
		int pivotIndex=partition(left,right,array[right]);
		recQuickSort(left,pivotIndex-1);
		recQuickSort(pivotIndex+1,right);
		}
	}
	//partition method for quicksort
	public int partition(int left, int right, Movie pivot) {
		int leftPtr = left-1;
		int rightPtr = right;
		while(true) {
			while(array[++leftPtr].getTitle().compareTo(pivot.getTitle())<0);
			while(rightPtr>0 && array[--rightPtr].getTitle().compareTo(pivot.getTitle())>0);
			if(leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right);
		return leftPtr;
	}
	//swap method for partition
	public void swap(int dex1, int dex2) {
		Movie temp = array[dex1];
		array[dex1] = array[dex2];
		array[dex2] = temp;
	}
	// heap sort method
	public void heapSort() {
		for (int i=nbmovies/2-1; i>=0;i--)
		{
			heapify(array, nbmovies, i);
		}
		for(int j = 0; j < nbmovies;j++)
		{
			System.out.println(j + " " + array[j].getOGLine());
		}
		for (int i=nbmovies-1; i>=0; i--) 
		{
			Movie temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			heapify(array, i, 0);
		}
		
	}
 
	//heap sort helper method
	public void heapify(Movie arr[], int n, int i) {
		int largest = i;
		int l = 2*i + 1;
		int r = 2*i + 2;
		if(l < n && arr[l].getTitle().compareTo(arr[largest].getTitle())>0)
			largest = l;
		if(r < n && arr[r].getTitle().compareTo(arr[largest].getTitle())>0)
			largest = r;
		if(largest != i) {
			Movie swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			heapify(arr, n, largest);
		}
		
	}

	//searches for a movie and returns true or false based on if it is found
	public Boolean binarySearch(String word)
	{
		steps = 0;
		int low = 0;
		int high = nbmovies - 1;
		int curin;
		while(true)
		{
			steps++;
			curin = (low + high)/2;
			if(array[curin].getTitle() == word)
			{
				return true;
			}
			else if(low > high)
			{
				return false;
			}
			else
			{
				if(array[curin].getTitle().compareToIgnoreCase(word) <= 0)
				{
					low = curin + 1;
				}
				else
				{
					high = curin - 1;
				}
			}
		}
		
	}
	//returns steps
	public int getStep()
	{
		return steps;
	}
	//returns a random movie title
	public String getRandomTitle()
	{
		Random randy = new Random();
		int random = randy.nextInt(nbmovies);
		return array[random].getTitle();
	}
	//deletes and returns the last movie
	public Movie delete()
	{
		Movie temp = array[nbmovies - 1];
		array[nbmovies - 1] = null;
		nbmovies--;
		return temp;
	}
	//returns the number of movies
	public int getNbmovies()
	{
		return nbmovies;
	}
}
