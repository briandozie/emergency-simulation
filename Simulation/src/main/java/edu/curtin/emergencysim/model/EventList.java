/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A class that represents a list of events
*/

package edu.curtin.emergencysim.model;

import java.util.*;

public class EventList
{
    private List<Event> events;

    /* SUBMODULE: EventList
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes class fields */
    public EventList()
    {
        events = new ArrayList<>();
    }

    /* SUBMODULE: checkForEvent
	   IMPORT: time (long)
	   EXPORT: eventOccur (boolean)
	   ASSERTION: Returns true if and event occurs at the given time, false otherwise */
    public boolean checkForEvent(long time)
    {
        boolean eventOccur = false;
        
        if(events.get(0).getTime() == time)
        {
            eventOccur = true;
        }

        return eventOccur;
    }

    /* SUBMODULE: addEvent
	   IMPORT: event (Event)
	   EXPORT: none
	   ASSERTION: Add and event into the list of events */
    public void addEvent(Event event)
    {
        events.add(event);
    }

    /* SUBMODULE: removeEvent
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Remvoe and event from the list of events */
    public void removeEvent()
    {
        events.remove(0);
    }

    /* SUBMODULE: getEvent
	   IMPORT: none
	   EXPORT: Event
	   ASSERTION: Return the first event in the list */
    public Event getEvent()
    {
        return events.get(0);
    }

    /* SUBMODULE: containsEvent
	   IMPORT: type (String), location (String)
	   EXPORT: found (boolean)
	   ASSERTION: Returns true if the event exist, false otherwise */
    public boolean containsEvent(String type, String location)
    {
        boolean found = false;
        
        for(Event event: events)
        {
            if(event.getType().equals(type)
            && event.getLocation().equals(location))
            {
                found = true;
            }
        }

        return found;
    }

    /* SUBMODULE: sort
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Sort the list of events according to time (ascending order) */
    public void sort()
    {
        events.sort(Comparator.comparing(s -> s.getTime()));
    }

    /* SUBMODULE: isEmpty
	   IMPORT: none
	   EXPORT: (boolean)
	   ASSERTION: Returns true if list of events is empty, false otherwise */
    public boolean isEmpty()
    {
        return events.isEmpty();
    }
}