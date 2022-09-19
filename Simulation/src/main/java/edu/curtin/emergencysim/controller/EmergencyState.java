/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: An interface representing the state of an emergency
*/

package edu.curtin.emergencysim.controller;

import edu.curtin.emergencysim.model.Emergency;

public interface EmergencyState
{
    void run(Emergency emergency, long curTime);
    void simulateCasualty(Emergency emergency);
    void simulateDamage(Emergency emergency);
    void simulateContam(Emergency emergency);
    void updateState(Emergency emergency, long curTime, String type, String arriving);
    String toStringState();
}