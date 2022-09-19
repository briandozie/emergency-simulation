/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A class containing methods to display to terminal
*/

package edu.curtin.emergencysim.view;

public class Display
{
    public Display() {}

    /* SUBMODULE: displayTime
	   IMPORT: time (long)
	   EXPORT: none
	   ASSERTION: Displays the current simulation time */
    public void displayTime(long time)
    {
        System.out.println("\nTime: " + time);
    }

    /* SUBMODULE: noArgDetected
	   IMPORT: none
	   EXPORT: prompt (String)
	   ASSERTION: Returns a prompt String notifying that no arguments were detected*/
    public String noArgDetected()
    {
        String prompt;
        prompt = "No arguments detected\n";
        prompt += "Please run with this format: ./gradlew run --args=\"[filename]\"";
        
        return prompt;
    }

    /* SUBMODULE: displayTime
	   IMPORT: time (long)
	   EXPORT: none
	   ASSERTION: Displays the current simulation time */
       public void displayEnd()
       {
           System.out.println("\n~ Simulation End ~");
       }
}