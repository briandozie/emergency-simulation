/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Abstract class representing a chemical spill state
*/

package edu.curtin.emergencysim.controller;

import edu.curtin.emergencysim.model.Emergency;

import java.util.Random;

public abstract class ChemicalSpill implements EmergencyState
{
    protected static final double CHEM_CASUALTY_PROB = 0.7;
    protected static final double CHEM_CONTAM_PROB = 0.7;
    protected static final long CHEM_CLEANUP_TIME = 5;
    private Random rand;

    /* SUBMODULE: ChemicalSpill
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes the class fields */
    public ChemicalSpill()
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
        if(rand.nextDouble() <= CHEM_CASUALTY_PROB)
        {
            emergency.addCasualty();
        }
    }

    /* SUBMODULE: simulateContam
	   IMPORT: emergency (Emergency)
	   EXPORT: none
	   ASSERTION: Performs simulation for whether a contamination occurs based on the probability */
    @Override
    public void simulateContam(Emergency emergency)
    {
        if(rand.nextDouble() <= CHEM_CONTAM_PROB)
        {
            emergency.addContam();
        }
    }
    
    /* SUBMODULE: simulateDamage
	   IMPORT: emergency (Emergency)
	   EXPORT: none
	   ASSERTION: Performs simulation for wheter a property damage occurs based on the probability */
    @Override
    public void simulateDamage(Emergency emergency) // NOPMD, method does not require and implementation in this class and its subclasses
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
        return "chemical";
    }
    
    @Override
    public abstract void run(Emergency emergency, long curTime);

    @Override
    public abstract void updateState(Emergency emergency, long curTime, String type, String arriving);
}