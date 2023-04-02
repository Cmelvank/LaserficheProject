/**
 * Code for local entry (ENGG1420 Final Project Group 10)
 * Programmer: Joaquin Fernandez
 */
package Testing;

import java.util.*;
import java.io.File;

public class Testing {
    
    public static void main(String[] args) 
    {        
        int option = 0;//input menu option
        String entry = "c:\\sample\\txt\\addresses.txt";//entry given by user
        
        ArrayList<String> localEntries = new ArrayList<>();//to store local entries
        
        Scanner scanN = new Scanner(System.in);
        
        do 
        {
            System.out.println("\n**INPUT MENU**");
            System.out.println("(1): Enter local path\n(2): Exit");
            option = scanN.nextInt();
            scanN.nextLine(); // consume newline character
            
            switch(option) 
            {
                
                case(1)://To enter a local directory
                {
                    System.out.println("\nPlease enter the local path:");
                    entry = scanN.nextLine();
                    
                    File file = new File(entry);
                    
                    if(file.exists()) {
                            localEntries.add(entry);
                            //entries.add(entry)
                        } else {
                        System.out.println("\nI'm sorry. That file/directory "
                                + "does not exist.");
                    }
      
                    break;       
                }
                
                case(2)://To exit the input menu
                {
                    System.out.println("Exiting input menu...");
                    break;
                }
                
                default://If the user chooses an invalid option
                {
                    System.out.println("I'm sorry. That choice is invalid. "
                            + "Please try again.");
                    break;
                }
            }
        } 
        while(option != 2);//end when user decides too
        
        /*
        for loop just checks if the entries are being stored. So you could just
        delete it ig
        */
        for(int a = 0; a < localEntries.size(); a++) {
            System.out.println(localEntries.get(a));
        }
        
    }//end of main
    
}//end of class
