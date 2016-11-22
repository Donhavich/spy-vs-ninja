/**
 * 
 */
package edu.cpp.cs.cs141.final_project;

import java.io.*;
/**
 * @author
 *
 */
public class DataSave implements Serializable {
	
	
	try{
		FileOutputStream dataSave = new FileOutputStream("filename.dat");
		
		ObjectOutputStream save  = new ObjectOutputStream(dataSave);
		
		save.writeObject(player)
		save.writeObject(ninjas);
		save.writeObject(items);
		save.writeObject(rooms);
		
		
		save.close();
	}
	catch (IO Exception e){
		e.printStackTrace();
	}
}
