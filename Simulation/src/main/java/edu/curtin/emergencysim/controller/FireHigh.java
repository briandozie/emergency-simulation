/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Abstract class representing a fire high state
*/

package edu.curtin.emergencysim.controller;

import java.util.Random;
import edu.curtin.emergencysim.model.Emergency;

public abstract class FireHigh implements EmergencyState
{
    protected static final long FIRE_HIGH_TO_LOW_TIME = 10;
    protected static final double FIRE_HIGH_CASUALTY_PROB = 0.6;
    protected static final double FIRE_HIGH_DAMAGE_PROB = 0.9;
    protected Random rand;

    /* SUBMODULE: FireHigh
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes the class fields*/
    public FireHigh()
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
        if(rand.nextDouble() <= FIRE_HIGH_CASUALTY_PROB)
        {
            emergency.addCasualty();
        }
    }

    /* SUBMODULE: simulateDamage
	   IMPORT: emergency (Emergency)
	   EXPORT: none
	   ASSERTION: Performs simulation for wheter a property damage occurs based on the probability */
    @Override
    public void simulateDamage(Emergency emergency)
    {
        if(rand.nextDouble() <= FIRE_HIGH_DAMAGE_PROB)
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

    /* SUBMODULE: toStringState
	   IMPORT: none
	   EXPORT: (String)
	   ASSERTION: Returns a String representation of the state */
    @Override
    public String toStringState()
    {
        return "fire high";
    }
    
    @Override
    public abstract void run(Emergency emergency, long curTime);

    @Override
    public abstract void updateState(Emergency emergency, long curTime, String type, String arriving);
}