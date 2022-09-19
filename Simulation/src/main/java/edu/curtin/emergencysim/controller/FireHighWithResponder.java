/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Class representing a fire high state with responders present
*/

package edu.curtin.emergencysim.controller;

import edu.curtin.emergencysim.model.Emergency;

public class FireHighWithResponder extends FireHigh
{
    /* SUBMODULE: FireHighWithResponder
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Calls superclass constructor */
    public FireHighWithResponder()
    {
        super();
    }

    /* SUBMODULE: run
	   IMPORT: emergency (Emergency), curTime (long)
	   EXPORT: none
	   ASSERTION: Determine if state needs to be changed or ended */
    @Override
    public void run(Emergency emergency, long curTime)
    {
        if(curTime - emergency.getStartTime() == FIRE_HIGH_TO_LOW_TIME)
        {
            emergency.setState(new FireLowWithResponder(), true);
            emergency.setStartTime(curTime);
        }
    }

    /* SUBMODULE: updateState
	   IMPORT: emergency (Emergency), curTime (long), type (String), arriving (String)
	   EXPORT: none
	   ASSERTION: Determines if the state needs to change based on responder presence */
    @Override
    public void updateState(Emergency emergency, long curTime, String type, String arriving)
    {
        if(type.equals("fire") && arriving.equals("-"))
        {
            emergency.setState(new FireHighNoResponder(), false);
            emergency.setStartTime(curTime);
        }
    }
}