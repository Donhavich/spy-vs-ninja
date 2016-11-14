package edu.cpp.cs.cs141.team_proj_personal;

public class Bullet extends Item {

	public Bullet() {
	}

	@Override
	public String toString(boolean isDebug) {
		if(isDebug||isVisiable())
			return "[b]";
		else
			return "[*]";
	}

}
