//Author Miki Rezentes
//Coding Exercise 3

import java.util.Scanner;


public class NumberSpiralTester {

	public static void main(String[] args) {
		
		
		System.out.println("Please enter a positive integer in numeric format : ");		 
		int target;
		Scanner scanIn = new Scanner(System.in);
		try
		{
		target = scanIn.nextInt();
		if(target < 0 )
		{
			throw new Exception();
		}
		NumberSpiral n = new NumberSpiral(target);
		System.out.println(n.toString());
		}
		catch(Exception e)
		{
			System.out.println("Input was not a positive integer in numeric form");
		}
		finally
		{
	    scanIn.close(); 
		}
	}

}
