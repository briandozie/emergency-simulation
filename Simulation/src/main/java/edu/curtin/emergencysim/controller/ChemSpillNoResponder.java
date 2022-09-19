/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Class representing a chemical spill state with responders absent
*/

package edu.curtin.emergencysim.controller;

import edu.curtin.emergencysim.model.Emergency;

public class ChemSpillNoResponder extends ChemicalSpill
{
    /* SUBMODULE: ChemSpillNoResponder
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Calls superclass constructor */
    public ChemSpillNoResponder()
    {
        super();
    }

    /* SUBMODULE: updateState
	   IMPORT: emergency (Emergency), curTime (long), type (String), arriving (String)
	   EXPORT: none
	   ASSERTION: Determines if the state needs to change based on responder presence */
    @Override
    public void updateState(Emergency emergency, long curTime, String type, String arriving)
    {
        if(type.equals("chemical") && arriving.equals("+"))
        {
            emergency.setState(new ChemSpillWithResponder(), false);
            emergency.setStartTime(curTime);
        }
    }

    /* SUBMODULE: run
	   IMPORT: emergency (Emergency), curTime (long)
	   EXPORT: none
	   ASSERTION: Determine if state needs to be changed or ended */
    @Override
    public void run(Emergency emergency, long curTime)
    {
        // ignore
    }
}