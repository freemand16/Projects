// Damaion Freeman
// CSCI 2540 Section 1

package assg8_Freeman;

import java.util.Scanner;

public class Event{
	
	 private boolean arrival;
     private int start;
     private int time;
    
     /**
      * Default constructor setting the arrival to true and the start and time to zero.
      */
     public Event() 
     {
         arrival = true;
         start = 0;
         time = 0;
     }
    
     /**
      * Three parameter Constructor setting all parameters to the given value.
      * @param isArrival Set to the arrival of the event.
      * @param startTime Set to the start of the event.
      * @param span Set to the time of the event.
      */
     public Event(boolean isArrival, int startTime, int span) 
     {
         arrival = isArrival;
         start = startTime;
         time = span;
     }
    
     /**
      * Get method for the start of an event.
      * @return The starting time of the event.
      */
     public int getStart()
     { 
    	 return start; 
     }
     
     /**
      * Get method for the arrival of an event.
      * @return Whether an arrival occurred or not.
      */
     public boolean getArrival() 
     { 
    	 return arrival; 
     }
     
     /**
      * Get method for the time of an event.
      * @return The amount of time an event took to complete.
      */
     public int getTime() 
     { 
    	 return time; 
     }
    
     /**
      * This method gets the whole event, setting the start to the first integer and the 
      * time to the next.
      * @param arrivalFile This marks the arrival of an event, stored in a scanner file, 
      * allowing to read the integers.
      */
     public void getArrivalEvent(Scanner arrivalFile) 
     {
         arrival = true;
         start = arrivalFile.nextInt();
         time = arrivalFile.nextInt();
     }
}
