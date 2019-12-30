// Damaion Freeman
// CSCI 2540 Section 1

package assg8_Freeman;

import java.io.*;
import java.util.*;

public class Eventlist {

	private String arrivalFilePath;
	private int currentTime;
	
	/**
	 * Constructor that sets the current time to zero, and sets the arrival path to the
	 * given arrival path.
	 * @param arrivalFilePath The arrival path of the file.
	 */
	public Eventlist(String arrivalFilePath) 
	{
        this.arrivalFilePath = arrivalFilePath;
        currentTime = 0;
    }
   
	/**
	 * This method adds an event to the event list.
	 * @param event The event that will be added to the event list.
	 * @param eventList List of events that events are added to.
	 */
    private void addEventToList(Event event, List<Event> eventList) 
    {
        if (eventList.isEmpty()) 
        {
        	eventList.add(0, event);
        }
        else if (eventList.get(0).getStart() < event.getStart()) 
        {
        	eventList.add(event);
        }
        else
        {
        	eventList.add(0, event);
        }
    }
   
    /**
     * The simulation to process the arrival and departure times.
     * @throws IOException 
     */
    public void bankSimulation() throws IOException 
    {
        Queue<Event> bankQueue = new LinkedList<Event>();
        List<Event> eventList = new LinkedList<Event>();
        Scanner arrivalFile = new Scanner(new File(arrivalFilePath));
        
        Event newEvent = new Event();
        newEvent.getArrivalEvent(arrivalFile);
        addEventToList(newEvent, eventList);
        BufferedReader processed = new BufferedReader(new FileReader("input.txt"));
        int lines = 0;
        while(processed.readLine() != null)
        {
        	lines++;
        }
        System.out.println("Simulation Begins");
        while (!eventList.isEmpty()) 
        {
            newEvent = eventList.get(0);
            if (newEvent.getArrival()) 
            {
                System.out.printf("Processing an arrival event at time: %d\n", newEvent.getStart());
                processArrival(newEvent, arrivalFile, eventList, bankQueue);
            }
            else 
            {
                System.out.printf("Processing an departure event at time: %d\n", newEvent.getStart());
                processDeparture(newEvent, eventList, bankQueue);
            }
        }
        System.out.println("Simulation Ends\n");
        System.out.println("Final Statistics:");
        System.out.println("Total number of people processed: " + lines);
        processed.close();
    }
   
    /**
     * This method processes the arrival of an event.
     * @param newEvent The new event being processed.
     * @param arrivalFile The arrival file.
     * @param eventList The list of events.
     * @param bankQueue The queue of the bank
     */
    private void processArrival(Event newEvent, Scanner arrivalFile, List<Event> eventList, Queue<Event> bankQueue) 
    {
        boolean atFront = bankQueue.isEmpty();
        bankQueue.add(newEvent);
        eventList.remove(0);
        if (currentTime < newEvent.getStart()) 
        {
        	currentTime = newEvent.getStart();
        }
       
        if (atFront) 
        {
            addEventToList(new Event(false, currentTime + newEvent.getTime(), 0), eventList);
        }
       
        if (arrivalFile.hasNext()) 
        {
            Event event = new Event();
            event.getArrivalEvent(arrivalFile);
            addEventToList(event, eventList);
        }
    }
   
    /**
     * This method processes the departure of an event.
     * @param newEvent The new event being processed.
     * @param eventList The list of events.
     * @param bankQueue The queue of the bank.
     */
    private void processDeparture(Event newEvent, List<Event> eventList, Queue<Event> bankQueue) 
    {
        bankQueue.remove();
        eventList.remove(0);
        if (currentTime < newEvent.getStart()) 
        {
        	currentTime = newEvent.getStart();
        }
       
        if (!bankQueue.isEmpty()) 
        {
            if (currentTime < bankQueue.peek().getStart()) 
            {
            	currentTime = bankQueue.peek().getStart();
            }
            addEventToList(new Event(false, currentTime + bankQueue.element().getTime(), 0), eventList);
        }
    }
	
}

