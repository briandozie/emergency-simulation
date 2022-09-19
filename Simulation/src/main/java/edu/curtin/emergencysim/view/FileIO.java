/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 17 May 2022
	Modification Date: 30 May 2022
	Purpose: Contains methods to read and import event data from a text file
*/

package edu.curtin.emergencysim.view;

import java.io.*;
import java.util.regex.*;

import edu.curtin.emergencysim.model.EmergencySimException;
import edu.curtin.emergencysim.model.EventList;
import edu.curtin.emergencysim.model.SimulationFactory;
import edu.curtin.emergencysim.model.Event;
import edu.curtin.emergencysim.EmergencySimApp;

@SuppressWarnings({"PMD.PreserveStackTrace"})
// PreserveStackTrace (throwing custom define exception)
public class FileIO
{
    private SimulationFactory simFactory;
	private static final Pattern FILE_REGEX =
		Pattern.compile("(?<time>[0-9]+) (?<emergency>fire|flood|chemical) (?<location>.+)");
	
	/* SUBMODULE: FileIO
	   IMPORT: eventFactory (EventFactory)
	   EXPORT: none
	   ASSERTION: Constructor for this class. Initializes the class fields */
	public FileIO(SimulationFactory simFactory)
	{
		this.simFactory = simFactory;
	}

	/* SUBMODULE: readFile
	   IMPORT: filename (String), eventList (EventList)
	   EXPORT: none
	   ASSERTION: Reads a file and loads the events into the program */
	public void readFile(String filename, EventList eventList) throws EmergencySimException
	{
		String line;
		Matcher m;
		Event event;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
		{
			line = reader.readLine();

			while(line != null)
			{
				// check if the format of the line is valid
				m = FILE_REGEX.matcher(line);
				if(!m.matches())
				{
					throw new IllegalArgumentException(line);
				}
				
				String time = m.group("time");
				String emergency = m.group("emergency");
				String location = m.group("location");

				// check if event already occur at a location
				if(eventList.containsEvent(emergency, location))
				{
					throw new IllegalArgumentException(line);
				}

				// create event object
				event = simFactory.makeEvent(Long.parseLong(time), emergency, location);
				eventList.addEvent(event); // add event into eventList

				String log = String.format("Created event '%s' at '%s' during time '%d'",
				event.getType(), event.getLocation(), event.getTime());
				EmergencySimApp.LOGGER.info(()->log);

				line = reader.readLine(); // read next line
			}

			eventList.sort();
		}
		catch(IOException e)
		{
			EmergencySimApp.LOGGER.severe(()->String.format("Error during file read: %s", e.getMessage()));
			throw new EmergencySimException("Error during file read: " + e.getMessage());
		}
		catch(IllegalArgumentException e)
		{
			EmergencySimApp.LOGGER.severe(()->String.format("Invalid file format: %s", e.getMessage()));
			throw new EmergencySimException("Invalid file format: " + e.getMessage());
		}

		EmergencySimApp.LOGGER.info(()->String.format("Successfully read events from '%s'", filename));
	}
}