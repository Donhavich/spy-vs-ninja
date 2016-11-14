package edu.cpp.cs.cs141.team_proj_personal;

public class Invinc extends Item {

	public Invinc() {
	}

	@Override
	public String toString(boolean isDebug) {
		if(isDebug||isVisiable())
			return "[i]";
		else
			return "[*]";
	}

}
