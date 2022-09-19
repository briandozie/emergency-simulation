/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A common superclass for the subjects of the observer pattern
*/

package edu.curtin.emergencysim.model;

import java.util.List;
import java.util.ArrayList;

public abstract class EmergencyObservable // NOPMD, acts as a superclass for subjects in the observer pattern, allows for reuse of add, remove, and notify observers */
{
    private List<EmergencyObserver> observers;

    /* SUBMODULE: EmergencyObservable
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes class field */
    public EmergencyObservable()
    {
        observers = new ArrayList<>();
    }
    
    /* SUBMODULE: addObs
	   IMPORT: ob (EmergencyObserver)
	   EXPORT: none
	   ASSERTION: Registers a respnder as an observer */
    public void addObs(EmergencyObserver ob)
    {
        observers.add(ob);
    }

    /* SUBMODULE: removeObs
	   IMPORT: ob (EmergencyObserver)
	   EXPORT: none
	   ASSERTION: Unregisters a respnder as an observer */
    public void removeObs(EmergencyObserver ob)
    {
        observers.remove(ob);
    }

    /* SUBMODULE: notifyObs
	   IMPORT: message (String)
	   EXPORT: none
	   ASSERTION: Notifies all observers */
    public void notifyObs(String message)
    {
        for(EmergencyObserver ob: observers)
        {
            ob.send(message);
        }
    }
}