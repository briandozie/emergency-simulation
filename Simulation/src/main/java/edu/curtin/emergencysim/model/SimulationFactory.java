/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A factory class to create new emergency and event objects
*/

package edu.curtin.emergencysim.model;

import edu.curtin.emergencysim.controller.ChemSpillNoResponder;
import edu.curtin.emergencysim.controller.FireLowNoResponder;
import edu.curtin.emergencysim.controller.FloodNoResponder;

public class SimulationFactory
{
    public SimulationFactory() {}

    /* SUBMODULE: makeEvent
	   IMPORT: time (long), type (String), location (String)
	   EXPORT: (Event)
	   ASSERTION: Creates a new Event object */
    public Event makeEvent(long time, String type, String location)
    {
        return new Event(time, type, location);
    }
    
    /* SUBMODULE: makeEmergency
	   IMPORT: event (Event)
	   EXPORT: emergency (Emergency)
	   ASSERTION: Creates a new Emergency object given an event */
    public Emergency makeEmergency(Event event)
    {
        Emergency emergency = null;
        
        switch(event.getType()) // NOPMD, the statement will fall into one of the cases as the input has already been validated
        {
            case "fire":
                emergency = new Emergency(event.getTime(),
                    new FireLowNoResponder(), event.getLocation());
                break;

            case "flood":
                emergency = new Emergency(event.getTime(),
                    new FloodNoResponder(), event.getLocation());
                break;

            case "chemical":
                emergency = new Emergency(event.getTime(),
                    new ChemSpillNoResponder(), event.getLocation());
                break;
        }

        return emergency;
    }
}