package edu.cpp.cs.cs141.team_proj_personal;

public class Radar extends Item {

	public Radar() {
	}

	@Override
	public String toString(boolean isDebug) {
		if(isDebug||isVisiable())
			return "[r]";
		else
			return "[*]";
	}

}
