/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A class containing methods to run the simulation
*/

package edu.curtin.emergencysim.controller;

import edu.curtin.emergencysim.model.EmergencyList;
import edu.curtin.emergencysim.model.EmergencyObserver;
import edu.curtin.emergencysim.responders.ResponderComm;
import edu.curtin.emergencysim.model.Emergency;
import edu.curtin.emergencysim.model.Event;
import edu.curtin.emergencysim.model.EventList;
import edu.curtin.emergencysim.model.SimulationFactory;
import edu.curtin.emergencysim.EmergencySimApp;
import java.util.*;
import java.util.regex.*;

public class Simulation
{
    private EmergencyList emergencyList;
    private EventList eventList;
    private ResponderComm responder;
    private EmergencyObserver responderObs;
    private SimulationFactory simFactory;
    private List<Emergency> endedEmergency;
    private boolean end;
    private static final Pattern POLL_REGEX =
        Pattern.compile("(?<emergency>fire|flood|chemical) (?<arriving>[+-]) (?<location>.+)");
   
    /* SUBMODULE: Simulation
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initilizes class fields */
    public Simulation(EventList eventList, EmergencyList emergencyList,
        ResponderComm responder, EmergencyObserver responderObs,
        SimulationFactory simFactory)
    {
        this.emergencyList = emergencyList;
        this.eventList = eventList;
        this.responder = responder;
        this.responderObs = responderObs;
        this.simFactory = simFactory;
        endedEmergency = new ArrayList<>();
        end = false;
    }

    /* SUBMODULE: generateEmergency
	   IMPORT: event (Event)
	   EXPORT: emergency (Emergency)
	   ASSERTION: Generates an emergency object given an event */
    private Emergency generateEmergency(Event event)
    {
        Emergency emergency = simFactory.makeEmergency(event);
        emergency.addObs(responderObs);
        EmergencySimApp.LOGGER.info(()->String.format("Created emergency '%s' at '%s' during time '%d'",
            event.getType(), event.getLocation(), event.getTime()));

        return emergency;
    }

    /* SUBMODULE: run
	   IMPORT: curTime (long)
	   EXPORT: end (boolean)
	   ASSERTION: Runs the simulation by calling related methods */
    public boolean run(long curTime)
    {
        runEvents(curTime);
        runEmergencies(curTime);
        pollResponder(curTime);
        simulateLosses();
        
        return end;
    }

    /* SUBMODULE: removeEndedEmergencies
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Removes ended emergencies from the emergencyList */
    private void removeEndedEmergencies()
    {
        Emergency emergency;
        while(!endedEmergency.isEmpty())
        {
            emergency = endedEmergency.remove(0);
            emergencyList.removeEmergency(emergency.toStringState().split(" ")[0],
                emergency.getLocation());
        }
    }

    /* SUBMODULE: pollResponder
	   IMPORT: curTime (long)
	   EXPORT: none
	   ASSERTION: Receive poll messages from responders */
    private void pollResponder(long curTime)
    {   
        String message;
        List<String> pollList = responder.poll();
        if(!pollList.isEmpty())
        {   
            if(pollList.get(0).equals("end"))
            {
                end = true;
                EmergencySimApp.LOGGER.info(()->String.format
                    ("Received 'end' message during polling at time '%d'", curTime));
            }
            else
            {
                while(!pollList.isEmpty())
                {
                    try
                    {
                        message = pollList.get(0);

                        // validate message format
                        Matcher m = POLL_REGEX.matcher(message);
                        if(!m.matches())
                        {
                            throw new IllegalArgumentException("Invalid poll message: " + message);
                        }

                        String type = m.group("emergency");
                        String arriving = m.group("arriving");
                        String location = m.group("location");
                        
                        // check if emergency exists
                        if(!emergencyList.hasEmergency(type, location))
                        {
                            throw new IllegalArgumentException("Invalid poll message: " + message);
                        }
                        
                        // update the state if necessary
                        Emergency emergency = emergencyList.getEmergency(type, location);
                            emergency.updateState(curTime, type, arriving);
                        EmergencySimApp.LOGGER.info(()->String.format("'%s' response team arrived at '%s' during time '%d'",
                            type, location, curTime));
                    }
                    catch(IllegalArgumentException e)
                    {
                        EmergencySimApp.LOGGER.warning(()->e.getMessage());
                    }

                    pollList.remove(0);
                }
            }
        }
    }

    /* SUBMODULE: runEvents
	   IMPORT: curTime (long)
	   EXPORT: none
	   ASSERTION: Checks if an event has occured, generates an emregency if so. */
    private void runEvents(long curTime)
    {
        Emergency emergency;
        while(!eventList.isEmpty() && eventList.checkForEvent(curTime))
        {
            emergency = generateEmergency(eventList.getEvent());
            emergencyList.addEmergency(emergency);
            eventList.removeEvent();
        }
    }

    /* SUBMODULE: simulateEmergencies
	   IMPORT: curTime (long)
	   EXPORT: none
	   ASSERTION: Runs the simulation for losses for all emergencies in emergencyList */
    private void simulateLosses()
    {
        Emergency emergency;
        for(Map.Entry<String,Emergency> entry : emergencyList.getList().entrySet())
        {
                emergency = entry.getValue();    
                
                emergency.simulateCasualty();
                emergency.simulateDamage();
                emergency.simulateContam();
        }
    }

    /* SUBMODULE: runEmergencies
	   IMPORT: curTime (long)
	   EXPORT: none
	   ASSERTION: Runs the simulation all emergencies in emergencyList */
    private void runEmergencies(long curTime)
    {
        Emergency emergency;
        for(Map.Entry<String,Emergency> entry : emergencyList.getList().entrySet())
        {
            emergency = entry.getValue();
            emergency.run(curTime);

            if(emergency.getEnd())
            {
                endedEmergency.add(emergency);
            }
        }
        removeEndedEmergencies();
    }
}