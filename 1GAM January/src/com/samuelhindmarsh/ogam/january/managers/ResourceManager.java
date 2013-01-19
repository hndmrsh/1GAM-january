package com.samuelhindmarsh.ogam.january.managers;

import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum ResourceManager{
	INSTANCE;
	
	public Image loadImageResource(String filename){
		try {
			System.out.println("loading level resource from " + filename);
			return new Image(filename, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			System.err.println("couldn't load " + filename + " image");
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
