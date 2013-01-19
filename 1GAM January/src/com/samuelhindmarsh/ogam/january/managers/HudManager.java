package com.samuelhindmarsh.ogam.january.managers;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import com.samuelhindmarsh.ogam.january.graphics.hud.HudObject;

public enum HudManager {
	INSTANCE;
	
	ArrayList<HudObject> hudObjects = new ArrayList<HudObject>();
	ArrayList<HudObject> toRemove;
	
	public void tick(Graphics g){
		toRemove = new ArrayList<HudObject>();
		
		for(HudObject hudObject : hudObjects){
			
			hudObject.render(g);
			
			if(hudObject.getExpireTime() < System.currentTimeMillis()){
				toRemove.add(hudObject);
			}
		}
		
		// remove all unregistered objects
		for(HudObject hudObject : toRemove){
			hudObjects.remove(hudObject);
		}
	}
	
	public void register(HudObject hudObject){
		hudObjects.add(hudObject);
	}
	
	public void unregister(HudObject hudObject){
		hudObjects.remove(hudObject);
	}
}
