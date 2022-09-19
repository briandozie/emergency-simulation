/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A class that defines a custom exception for the simulation application
*/

package edu.curtin.emergencysim.model;

public class EmergencySimException extends Exception
{
    public EmergencySimException(String message)
    {
        super(message);
    }
}