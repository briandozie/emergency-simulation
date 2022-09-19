/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Abstract class representing a flood state
*/

package edu.curtin.emergencysim.controller;

import edu.curtin.emergencysim.model.Emergency;
import java.util.Random;

public abstract class Flood implements EmergencyState
{
    protected static final double FLOOD_CASUALTY_PROB = 0.1;
    protected static final double FLOOD_DAMAGE_PROB = 0.3;
    protected static final long FLOOD_END_TIME = 10;
    protected Random rand;

    /* SUBMODULE: Flood
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes the class fields*/
    public Flood()
    {
        rand = new Random();
    }

    @Override
    public void simulateDamage(Emergency emergency)
    {
        if(rand.nextDouble() <= FLOOD_DAMAGE_PROB)
        {
            emergency.addDamage();
        }
    }

    /* SUBMODULE: toStringState
	   IMPORT: none
	   EXPORT: (String)
	   ASSERTION: Returns a String representation of the state */
    @Override
    public String toStringState()
    {
        return "flood";
    }

    /* SUBMODULE: simulateContam
	   IMPORT: emergency (Emergency)
	   EXPORT: none
	   ASSERTION: Performs simulation for whether a contamination occurs based on the probability */
    @Override
    public void simulateContam(Emergency emergency) // NOPMD, method does not require and implementation in this class and its subclasses
    {
        // ignore
    }

    /* SUBMODULE: run
	   IMPORT: emergency (Emergency), curTime (long)
	   EXPORT: none
	   ASSERTION: Determine if state needs to be changed or ended */
    @Override
    public void run(Emergency emergency, long curTime)
    {
        if(curTime - emergency.getStartTime() == FLOOD_END_TIME)
        {
            emergency.setEnd(); // flood has dissipated
        }
    }

    @Override
    public abstract void simulateCasualty(Emergency emergency);
    
    @Override
    public abstract void updateState(Emergency emergency, long curTime, String type, String arriving);
}