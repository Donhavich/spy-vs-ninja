package edu.cpp.cs.cs141.final_project;

public class Invinc extends Item {

	public Invinc() {
	}

	@Override
	public String toString(boolean isDebug) {
		if(isDebug)
			return "[i]";
		else
			return "[*]";
	}

}
