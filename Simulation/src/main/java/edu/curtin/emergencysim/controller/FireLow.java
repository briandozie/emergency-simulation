/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Abstract class representing a fire low state
*/

package edu.curtin.emergencysim.controller;

import java.util.Random;
import edu.curtin.emergencysim.model.Emergency;

public abstract class FireLow implements EmergencyState
{
    protected static final long FIRE_LOW_TO_HIGH_TIME = 7;
    protected static final long FIRE_LOW_CLEANUP_TIME = 5;
    protected static final double FIRE_LOW_CASUALTY_PROB = 0.2;
    protected static final double FIRE_LOW_DAMAGE_PROB = 0.4;
    protected Random rand;

    /* SUBMODULE: FireLow
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes the class fields*/
    public FireLow()
    {
        rand = new Random();
    }

    /* SUBMODULE: simulateCasualty
	   IMPORT: emergency (Emergency)
	   EXPORT: none
	   ASSERTION: Performs simulation for whether a casualty occurs based on the probrability */
    @Override
    public void simulateCasualty(Emergency emergency)
    {
        if(rand.nextDouble() <= FIRE_LOW_CASUALTY_PROB)
        {
            emergency.addCasualty();        
        }        
    }

    @Override
    public void simulateDamage(Emergency emergency)
    {
        if(rand.nextDouble() <= FIRE_LOW_DAMAGE_PROB)
        {
            emergency.addDamage();
        }
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

    @Override
    public String toStringState()
    {
        return "fire low";
    }
    
    /* SUBMODULE: toStringState
	   IMPORT: none
	   EXPORT: (String)
	   ASSERTION: Returns a String representation of the state */
    @Override
    public abstract void run(Emergency emergency, long curTime);

    @Override
    public abstract void updateState(Emergency emergency, long curTime, String type, String arriving);
}