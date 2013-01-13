package com.samuelhindmarsh.ogam.january.input;

public enum Action {
	JUMP, FIRE, PICKUP, GRENADE, LEFT, RIGHT, CROUCH;
	
	public String toString() {
		return name().charAt(0) + name().substring(1).toLowerCase();
	}
}
