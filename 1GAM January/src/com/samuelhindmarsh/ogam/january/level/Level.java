package com.samuelhindmarsh.ogam.january.level;

import org.newdawn.slick.Image;

public class Level {
	
	private String name;
	private Image collisionMask;
	private Image foreground;
	private Image[] backgrounds;
	
	public Level(String name, Image collisionMask, Image foreground, Image[] backgrounds) {
		this.name = name;
		this.collisionMask = collisionMask;
		this.foreground = foreground;
		this.backgrounds = backgrounds;
	}
	
	public Image getCollisionMask() {
		return collisionMask;
	}
	
	public Image getForeground() {
		return foreground;
	}
	
	public Image getBackground(int i) {
		return backgrounds[i];
	}
}
