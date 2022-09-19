/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: A class to store information about an emergency
*/

package edu.curtin.emergencysim.model;

import edu.curtin.emergencysim.controller.EmergencyState;
import edu.curtin.emergencysim.EmergencySimApp;

public class Emergency extends EmergencyObservable
{
    private int casualty;
    private int damage;
    private int contam;
    private String location;
    private long startTime;
    private boolean end;
    private EmergencyState state;

    /* SUBMODULE: Emergency
	   IMPORT: startTime (long), state (EmergencyState), location (String)
	   EXPORT: none
	   ASSERTION: COnstructor for this class. Initializes class fields */
    public Emergency(long startTime, EmergencyState state, String location)
    {
        this.startTime = startTime;
        this.state = state;
        this.location = location;
        casualty = 0;
        damage = 0;
        end = false;
    }

    /* SUBMODULE: getStartTime
	   IMPORT: none
	   EXPORT: startTime (long)
	   ASSERTION: Returns the start time of the emergency */
    public long getStartTime()
    {
        return startTime;
    }

    /* SUBMODULE: getLocation 
	   IMPORT: none
	   EXPORT: location (String)
	   ASSERTION: Returns the location of the emergency */
    public String getLocation()
    {
        return location;
    }

    /* SUBMODULE: getEnd
	   IMPORT: none
	   EXPORT: end (boolean)
	   ASSERTION: Returns the boolean value of the "end" variable */
    public boolean getEnd()
    {
        return end;
    }

    /* SUBMODULE: updateState
	   IMPORT: curTime (long), type (String), arriving (String)
	   EXPORT: none
	   ASSERTION: Update the state with changes in responders if necessary */
    public void updateState(long curTime, String type, String arriving)
    {
        state.updateState(this, curTime, type, arriving);
    }

    /* SUBMODULE: setState
	   IMPORT: state (EmergencyState), notify (boolean)
	   EXPORT: none
	   ASSERTION: Set the state of the emergency, notifies observers if "notify" is true */
    public void setState(EmergencyState state, boolean notify)
    {
        this.state = state;
        if(notify)
        {
            notifyObs(String.format("%s %s", state.toStringState(), location));
        }
    }

    /* SUBMODULE: setStartTime
	   IMPORT: startTime (long)
	   EXPORT: none
	   ASSERTION: Set the start time of the emergency */
    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    /* SUBMODULE: setEnd
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Sets the "end" variable to true, indicating that the emergency has ended */
    public void setEnd()
    {
        end = true;
        EmergencySimApp.LOGGER.info(()->String.format("%s end %s",
            state.toStringState().split(" ")[0], location));
    }

    /* SUBMODULE: addCasualty
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Increments the casualty count, notifies observers */
    public void addCasualty()
    {
        casualty++;
        notifyObs(String.format("%s casualty %d %s",
            state.toStringState().split(" ")[0], casualty, location));

        EmergencySimApp.LOGGER.info(()->String.format("%s casualty %d %s",
            state.toStringState().split(" ")[0], casualty, location));
    }

    /* SUBMODULE: addDamage
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Increments the damage count, notifies observers */
    public void addDamage()
    {
        damage++;
        notifyObs(String.format("%s damage %d %s",
            state.toStringState().split(" ")[0], damage, location));

        EmergencySimApp.LOGGER.info(()->String.format("%s damage %d %s",
            state.toStringState().split(" ")[0], damage, location));
    }

    /* SUBMODULE: addContam
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Increments the contam counter, notifies observers */
    public void addContam()
    {
        contam++;
        notifyObs(String.format("%s contam %d %s",
            state.toStringState().split(" ")[0], contam, location));

        EmergencySimApp.LOGGER.info(()->String.format("%s contam %d %s",
            state.toStringState().split(" ")[0], contam, location));
    }

    /* SUBMODULE: toStringState
	   IMPORT: none
	   EXPORT: (String)
	   ASSERTION: Returns a String representation of the state */
    public String toStringState()
    {
        return state.toStringState();
    }

    /* SUBMODULE: simulateCasualty
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Run simulation to determine if casualty occurs */
    public void simulateCasualty()
    {
        state.simulateCasualty(this);
    }

    /* SUBMODULE: simulateDamage
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Run simulation to determine if damage occurs */
    public void simulateDamage()
    {
        state.simulateDamage(this);
    }

    /* SUBMODULE: simulateContam
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Run simulation to determine if contamination occurs */
    public void simulateContam()
    {
        state.simulateContam(this);
    }

    /* SUBMODULE: run
	   IMPORT: curTime (long)
	   EXPORT: none
	   ASSERTION: Run simulation to determine if state changes or ends */
    public void run(long curTime)
    {
        state.run(this, curTime);
    }
}