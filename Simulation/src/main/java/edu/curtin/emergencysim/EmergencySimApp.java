/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Contains the main method to run the simulation application
*/

package edu.curtin.emergencysim;

import java.util.logging.Logger;
import edu.curtin.emergencysim.model.*;
import edu.curtin.emergencysim.responders.ResponderComm;
import edu.curtin.emergencysim.responders.ResponderCommImpl;
import edu.curtin.emergencysim.view.*;
import edu.curtin.emergencysim.controller.*;

public class EmergencySimApp
{
    // logger variable
    public static final Logger LOGGER = Logger.getLogger(EmergencySimApp.class.getName());
    
    public static void main(String[] args)
    {
        EventList eventList = new EventList();
        SimulationFactory simFactory = new SimulationFactory();
        ResponderComm responder = new ResponderCommImpl();
        EmergencyObserver responderObs = (EmergencyObserver) responder;
        EmergencyList emergencyList = new EmergencyList();
        FileIO file = new FileIO(simFactory);
        Display display = new Display();
        boolean end = false;
        long time = 0;
        emergencyList.addObs(responderObs);
        
        try
        {
            if(args.length == 0)
            {
                throw new EmergencySimException(display.noArgDetected());
            }
        
            file.readFile(args[0], eventList); // read file
            Simulation sim = new Simulation(eventList, emergencyList, responder,
                responderObs,simFactory);
                
            while(!end)
            {
                display.displayTime(time);
                end = sim.run(time); // run simulation

                try 
                {
                    Thread.sleep(1000); // sleep for 1 second
                    time++;
                }
                catch(InterruptedException e) {}
            }

            display.displayEnd();
        }
        catch(EmergencySimException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
