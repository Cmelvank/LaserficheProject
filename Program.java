//imports
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Program 
{
	//Master ArrayList with all file paths and directories
	static ArrayList<String> entries = new ArrayList<String>();

	public static void main(String[] args) 
	{
		//Add entries
		entries.add("C:\\Users\\m\\OneDrive - University of Guelph\\Classes\\Year 1\\Sem 2\\ENGG1420\\Project\\Programv1\\src\\Files\\Joaquin.txt");
		entries.add("C:\\Users\\m\\OneDrive - University of Guelph\\Classes\\Year 1\\Sem 2\\ENGG1420\\Project\\Programv1\\src\\Files\\Max.txt");
		entries.add("C:\\Users\\m\\OneDrive - University of Guelph\\Classes\\Year 1\\Sem 2\\ENGG1420\\Project\\Programv1\\src\\Files\\Moe.txt");
		entries.add("C:\\Users\\m\\OneDrive - University of Guelph\\Classes\\Year 1\\Sem 2\\ENGG1420\\Project\\Programv1\\src\\Files\\Kevin.txt");
		entries.add("C:\\Users\\m\\OneDrive - University of Guelph\\Classes\\Year 1\\Sem 2\\ENGG1420\\Project\\Programv1\\src\\Files\\testDirectory");
		
		//Run filters stored in Filters class
		Filters.count("great", 4);
		
		System.out.print(entries); //Print filtered entries to make sure code worked properly
	}

}

class Filters 
{
	String filePath;

	static void name(String key) {
		ArrayList<String> tempEntries = new ArrayList<String>(); // create new array list
		
		for (int i = 0; i < Program.entries.size(); i++) 
		{
			 // check if the entries contains the key word
			if (Program.entries.get(i).contains(key)) 
			{
				tempEntries.add(Program.entries.get(i)); // add to the temp list if it does
			}
		}
		Program.entries = tempEntries; // override main list with the temp one

	}

	static void length(long length, String operator) 
	{
		ArrayList<String> tempEntries = new ArrayList<String>(); // create new array list
		File file = null;
		
		for (int i = 0; i < Program.entries.size(); i++) 
		{
			//distinguish between file and directory (presence of .txt at end)
			if (Program.entries.get(i).charAt((Program.entries.get(i).length()) - 1) == 't'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 2) == 'x'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 3) == 't'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 4) == '.') 
			{
				file = new File((Program.entries.get(i))); //Create file for entry
				
				//check if file exists
				if(file.exists() == false)
				{
					System.out.println("File does not exist!");
					return;
				}
				
				//operator logic (if condition true, add to new ArrayList)
				//size in bytes
				if (operator == "LT" && file.length() < length) 
				{
					tempEntries.add(Program.entries.get(i));
				}
				if (operator == "LTE" && file.length() <= length) 
				{
					tempEntries.add(Program.entries.get(i));
				}
				if (operator == "GT" && file.length() > length) 
				{
					tempEntries.add(Program.entries.get(i));
				}
				if (operator == "GTE" && file.length() <= length) 
				{
					tempEntries.add(Program.entries.get(i));
				}
				if (operator == "EQ" && file.length() == length) 
				{
					tempEntries.add(Program.entries.get(i));
				}
				if (operator == "NEQ" && file.length() != length) 
				{
					tempEntries.add(Program.entries.get(i));
				}
			}

		}
		Program.entries = tempEntries; // overide the main list with all the temp one

	}

	static void content(String Key) 
	{
		ArrayList<String> tempEntries = new ArrayList<String>(); // create new array list
		
		File file = null;
		Scanner scanner = null;
		
		for (int i = 0; i < Program.entries.size(); i++) 
		{
			//distingush between file and directory
			if (Program.entries.get(i).charAt((Program.entries.get(i).length()) - 1) == 't'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 2) == 'x'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 3) == 't'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 4) == '.') {
				file = new File((Program.entries.get(i)));
				try 
				{
					scanner = new Scanner(file);
					// now read the file line by line...
					while (scanner.hasNextLine()) 
					{
						String line = scanner.nextLine();
						
						//if file contains key string
						if (line.contains(Key))
						{
							tempEntries.add(Program.entries.get(i));
							break; //no need to waste time
						}
					}
				} catch (FileNotFoundException e) 
				{
					System.out.print("File does not exist.");
				}

			}
		}
		Program.entries = tempEntries; // overide the main list with all the temp one
		scanner.close();
	}
	
	static void count(String key, int min) 
	{
		//make sure min is greater than zero
		if (min <= 0) 
		{
			System.out.print("Min should be greater than 0!");
			return;
		}
		
		int count = 0; //keeps track of key instances in entire file
		ArrayList<String> tempEntries = new ArrayList<String>(); // create new array list
		File file = null;
		Scanner scanner = null;
		
		for (int i = 0; i < Program.entries.size(); i++) 
		{
			//distingush file from directory
			if (Program.entries.get(i).charAt((Program.entries.get(i).length()) - 1) == 't'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 2) == 'x'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 3) == 't'
					&& Program.entries.get(i).charAt((Program.entries.get(i).length()) - 4) == '.') 
			{
				file = new File((Program.entries.get(i)));
				try 
				{
					scanner = new Scanner(file);
					// now read the file line by line...
					while (scanner.hasNextLine()) 
					{
						String line = scanner.nextLine();
						int count2 = line.split(key).length -1; //get number of instances of key per line
						count += count2;
						//System.out.print(count);
						
						//if key is present in file at least min times
						if (count >= min) 
						{
							tempEntries.add(Program.entries.get(i));
							break; 
						}
					}
				} 
				catch (FileNotFoundException e) 
				{
					System.out.print("File does not exist.");
				}

			}
		count = 0;
		}
		
		Program.entries = tempEntries; // overide the main list with all the temp one
		scanner.close();
		
		
	}
}
