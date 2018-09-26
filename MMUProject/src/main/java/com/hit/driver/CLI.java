package com.hit.driver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI 
{
	Scanner in;
	PrintWriter out;
	Scanner scanner;
	
	public CLI(InputStream in, OutputStream out) 
	{
		this.in = new Scanner(in);
		this.out = new PrintWriter(out);
	}
	
	public String[] getConfiguration()
	{
		String userInput;
		boolean isValid;
		do 
		{
			isValid = true;
			write("Enter 'start' or 'stop'");
			userInput = in.nextLine();
			if (!userInput.toLowerCase().equals("start") && !userInput.toLowerCase().equals("stop"))
			{
				write("Invalid command. Please enter 'start' or 'stop'");
				isValid = false;
			}
		}
		while (!isValid);
		
		if (userInput.equals("start"))
		{
			int ramCapacityIndex = 0;
			String[] algoChoiceAndCapacity;
			String algo;
			do 
			{
				isValid = true;
				write("Please enter required algorithm and RAM capacity:");
				write("(Ener 'LRU' or 'NFU' or 'Random')");
				userInput = in.nextLine();
				ramCapacityIndex = 1;
				algoChoiceAndCapacity = userInput.split(" ");
				algo = algoChoiceAndCapacity[0].toLowerCase();
		
				if (!algo.equals("lru")&& !algo.equals("nfu") && !algo.equals("random"))
				{
					write("Not a valid command");
					isValid = false;
				}
				
				if (isValid)
				{
					if (algoChoiceAndCapacity.length > ramCapacityIndex + 1)
					{
						write("Too many arguments");
						isValid = false;
					}
					
					if (isValid)
					{
						try
						{
							Integer.parseInt(algoChoiceAndCapacity[ramCapacityIndex]);
						}
						catch (NumberFormatException exception)
						{
							write("ERROR! Not a valid value for RAM capacity");
							isValid = false;
						}
					}
				}
			}
			while (!isValid);
			
			write("Processing...");
			
			return algoChoiceAndCapacity;
		}
		else
		{
			return null;
		}
	}
	
	public void write(String string)
	{
		out.println(string);
		out.flush();
	}
	
}
