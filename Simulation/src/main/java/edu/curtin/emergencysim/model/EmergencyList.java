/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A class that represents a list of emergencies
*/

package edu.curtin.emergencysim.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class EmergencyList extends EmergencyObservable
{
    private Map<String,Emergency> emergencies;

    /* SUBMODULE: EmergencyList
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes class fields */
    public EmergencyList()
    {
        super();
        emergencies = new HashMap<>();
    }

    /* SUBMODULE: addEmergency
	   IMPORT: emergency (Emergency)
	   EXPORT: none
	   ASSERTION: Adds an emergency object into the list of emergencies, notifies observers */
    public void addEmergency(Emergency emergency)
    {
        String type = emergency.toStringState().split(" ")[0];
        String location = emergency.getLocation();
        emergencies.put(type + " " + location, emergency);

        notifyObs(type + " start " + location);
    }

    /* SUBMODULE: removeEmergency
	   IMPORT: type (String), location (String)
	   EXPORT: none
	   ASSERTION: Removes an emergency object from the list of emergencies, notifies observers */
    public void removeEmergency(String type, String location)
    {
        if(emergencies.containsKey(type + " " + location))
        {
            emergencies.remove(type + " " + location);
            notifyObs(type + " end " + location);
        }
    }

    /* SUBMODULE: hasEmergency
	   IMPORT: type (String), location (String)
	   EXPORT: (boolean)
	   ASSERTION: Returns true if emergency exist, false otherwise */
    public boolean hasEmergency(String type, String location)
    {
        return emergencies.containsKey(String.format("%s %s", type, location));
    }

    /* SUBMODULE: getEmergency
	   IMPORT: type (String), location (String)
	   EXPORT: (Emergency)
	   ASSERTION: Returns an emergency object */
    public Emergency getEmergency(String type, String location)
    {
        return emergencies.get(String.format("%s %s", type, location));
    }

    /* SUBMODULE: getList
	   IMPORT: none
	   EXPORT: (MAP OF String key AND Emergency value)
	   ASSERTION: Returns the "emergencyList" map */
    public Map<String,Emergency> getList()
    {
        return Collections.unmodifiableMap(emergencies);
    }
}