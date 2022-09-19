/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Class representing a flood state with responders present
*/

package edu.curtin.emergencysim.controller;

import edu.curtin.emergencysim.model.Emergency;

public class FloodWithResponder extends Flood
{    
    /* SUBMODULE: FloodWithResponder
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Calls superclass constructor */
    public FloodWithResponder()
    {
        super();
    }

    /* SUBMODULE: simulateCasualty
	   IMPORT: emergency (Emergency)
	   EXPORT: none
	   ASSERTION: Performs simulation for whether a casualty occurs based on the probrability */
    @Override
    public void simulateCasualty(Emergency emergency)
    {
        // ignore
    }

    /* SUBMODULE: updateState
	   IMPORT: emergency (Emergency), curTime (long), type (String), arriving (String)
	   EXPORT: none
	   ASSERTION: Determines if the state needs to change based on responder presence */
    @Override
    public void updateState(Emergency emergency, long curTime, String type, String arriving)
    {
        if(type.equals("flood") && arriving.equals("-"))
        {
            emergency.setState(new FloodNoResponder(), false);
        }
    }
}