/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A class to store information about an event read from file
*/

package edu.curtin.emergencysim.model;

public class Event
{
    private long time;
    private String type;
    private String location;

    /* SUBMODULE: Event
	   IMPORT: time (long), type (String), location (String)
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes class fields */
    public Event(long time, String type, String location)
    {
        this.time = time;
        this.type = type;
        this.location = location;
    }

    /* SUBMODULE: getTime
	   IMPORT: none
	   EXPORT: time (long)
	   ASSERTION: Returns the time */
    public long getTime()
    {
        return time;
    }

    /* SUBMODULE: getType
	   IMPORT: none
	   EXPORT: type (String)
	   ASSERTION: Returns the type of event */
    public String getType()
    {
        return type;
    }

    /* SUBMODULE: getLocation
	   IMPORT: none
	   EXPORT: location (String)
	   ASSERTION: Returns the location of the event */
    public String getLocation()
    {
        return location;
    }
}