// Damaion Freeman
// CSCI 2540 Section 1

package assg8_Freeman;

import java.io.*;

/**
 * This program runs the event-driven simulation of a bank.
 * @author Damaion Freeman
 *
 */
public class Simulation {
	
	public static void main(String[] args) throws IOException
	{
		Eventlist simulation = new Eventlist("input.txt");
        try 
        {
            simulation.bankSimulation();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println(e);
        }
	}
}
