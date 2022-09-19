/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Acts as an observer interface for the Observer Pattern
*/

package edu.curtin.emergencysim.model;

public interface EmergencyObserver
{
    void send(String message);
}