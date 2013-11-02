//Author Miki Rezentes
//Coding Exercise 3

import java.util.ArrayList;


public class NumberSpiral {
	
	private ArrayList<int[]> numberSpiral;
	private int target; // maximum number of the spiral
	private int totalColumns; // number of columns in the spiral
	private int totalRows; // number of rows in the spiral
	private int formatLength;  // the length of the target.  Needed to ensure the spiral keeps presentation symmetrical
	
	
	/**
	 * Constructs a new NumberSpiral
	 * @param t - maximum number displayed in the spiral
	 */
	public NumberSpiral(int t)
	{
		//highest number whose square is less than or = to the target
		int squareRoot = (int) Math.floor(Math.sqrt(t));
		
		//Initialize the class variables.
		target = t;		
		formatLength = String.valueOf(target).length();
		totalColumns = squareRoot + 1;
		totalRows = 1;
		//Initialize the numberSpiral
		numberSpiral = new ArrayList<>();
		if ( target >= squareRoot * (squareRoot - 1))
		{
			totalRows = squareRoot + 1;
		}
		else 
		{
			totalRows = squareRoot;
		}
		
		for (int i = 0; i < totalRows; i++)
		{
			numberSpiral.add(i, new int[totalColumns]);
			for (int j = 0; j < totalColumns ; j ++)
			{
				//all contents are all initialized to -1
				numberSpiral.get(i)[j] = -1;
			}
		}
		
		//populate the numberSpiral
		populateSpiral(squareRoot);
	}
	
	/**
	 * Populates the spiral with numbers
	 * @param squareRoot  - highest number whose square is less than or = to the target
	 */
	public void populateSpiral(int squareRoot)
	{
		int rowIndex = 0;
		int columnIndex = 0;
		
		/*
		 * Set the indexes to the center of the spiral
		 */
		if ( (totalRows & 1) == 0 ) //if the totalRows is an even number
		{
			rowIndex = totalRows / 2 - 1;
		}
		else
		{
			rowIndex = totalRows / 2;
		}
		
		if ( (totalColumns & 1) == 0 )//if totalColumns is an even number
		{
			columnIndex = totalColumns / 2 - 1;
		}
		else
		{
			columnIndex = totalColumns / 2 ;
		}
		
		/*counter used to represent the numbers filling the spiral
		*starts at 0 and counts up to the target
		*/
		int counter = 0;
		
		/*
		 * rows and columns increment differently depending on placement within the spiral
		 */
		int columnDelta;//increment value for the columns
		int rowDelta;//increment value for the rows
		
		//must add one to the center index because the loop will move 1 to the left prior to the insertion of zero.
		columnIndex++;
		
		/*
		 * Populate the number spiral
		 * i represents the current square
		 * spiral is constructed starting with even squares in the lower right corner and odd squares in the upper left corner
		 */
		for (int i = 0; i <= squareRoot; i ++)
		{
			//even squares represent lower right corner, numbers progress with rows decreasing or columns increasing
			if ((i & 1) == 0 )
			{
				columnDelta = 1;
				rowDelta = -1;
			}
			//odd squares represented in upper left corner, numbers progress with rows increasing or columns decreasing
			else
			{
				columnDelta = -1;
				rowDelta = 1;
			}
			
			//prepare for first insertion
			columnIndex = columnIndex - columnDelta;
			
			//Insert the values progressing vertically
			for (int j = 0; j < i + 1 && counter <= target; j++)
			{
				numberSpiral.get(rowIndex)[columnIndex] = counter;
				if( j < i)
				{
				rowIndex = rowIndex + rowDelta;
				}
				counter++;
			}
			
			//Insert the values progressing horizontally
			for (int j = 0; j < i && counter <= target; j++)
			{
				columnIndex = columnIndex + columnDelta;
				numberSpiral.get(rowIndex)[columnIndex] = counter;
				counter++;
			}
		}
	}
	
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		
		//loop through each row
		for (int i = 0; i < totalRows; i ++)
		{
			//loop through each column
			for (int j = 0; j < totalColumns; j++ )
			{
				//add each number to the result string
				result.append(getCellContents(i,j));
			}
			//Add new line character
			result.append("\n");
		}
		return result.toString();
	}
	
	/**
	 * Add adequate spaces to contents so all values take up the same amount of space in display
	 * @param row
	 * @param column
	 * @return formatted contents of the cell
	 */
	private StringBuilder getCellContents(int row, int column)
	{
		int cellValue = numberSpiral.get(row)[column];
		
		StringBuilder contents = new StringBuilder("");//default value in case there is no value for the cell
		
		if (cellValue != -1) // values of -1 mean that there is not value to display for that cell.
		{
			 contents.append(String.valueOf(cellValue));
		}
		
		while (contents.length() < formatLength)//ensure all values are the same length
		{
			contents.append(" ");
		}
		
		contents.append("   "); //add spaces in between the different values
		
		return contents;
	}
	

}
