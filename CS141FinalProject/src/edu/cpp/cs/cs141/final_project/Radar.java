/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

/**
 * @author
 *
 */
public class Radar extends Item {

	public Radar(){}
	
	@Override
	public String toString(boolean isDebug) {

		if(isDebug)
			return "[r]";
		else 
			return "[*]";
	}
	
}
