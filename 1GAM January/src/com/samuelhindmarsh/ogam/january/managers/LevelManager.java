package com.samuelhindmarsh.ogam.january.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.samuelhindmarsh.ogam.january.level.Level;

public enum LevelManager {
	INSTANCE;

	private static final String DIRECTORY_LEVELS = "levels";
	private static final String FILE_LIST = "levels"; // yes these are the same shut up.
	private static final String FILENAME_EXTENSION = "png";
	
	private Level[] levels;
	private int currentLevel = 0;
	
	public void init() {
		loadLevels();
	}

	
	private void loadLevels() {
		File list = new File(DIRECTORY_LEVELS + File.separator + FILE_LIST);
		
		
		Scanner listScanner = null;
		try {
			listScanner = new Scanner(list);
		} catch (FileNotFoundException e) {
			System.err.println("could not open levels list at " + list.getPath());
			e.printStackTrace();
			System.exit(1);
		}
		
		int levelNumber = 0;
		while(listScanner.hasNextLine()){
			// parse the level definition
			String[] tokens = listScanner.nextLine().split(",");
			
			if(tokens[0].startsWith("#")){
				// commented line
				continue;
			}
			
			File levelDirectory = new File (DIRECTORY_LEVELS + File.separator + tokens[0]);
			
			// foreground image
			Image fg = null;
			try {
				fg = new Image(DIRECTORY_LEVELS + File.separator + "fg." + FILENAME_EXTENSION);
			} catch (SlickException e) {
				System.err.println("couldn't load foreground image");
				e.printStackTrace();
				System.exit(1);
			}
			
			// collision mask
			Image mask = null;
			try {
				mask = new Image(DIRECTORY_LEVELS + File.separator + "mask." + FILENAME_EXTENSION);
			} catch (SlickException e) {
				System.err.println("couldn't load collision mask");
				e.printStackTrace();
				System.exit(1);
			}
			
			// backgrounds
			FilenameFilter bgFilesFilter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith("bg");
				}
			};
			
			File[] bgFiles = levelDirectory.listFiles(bgFilesFilter);
			Image bgs[] = new Image[bgFiles.length];
			for(int f = 0; f < bgs.length; f++){
				try {
					bgs[f] = new Image(DIRECTORY_LEVELS + File.separator + "bg" + f + "." + FILENAME_EXTENSION);
				} catch (SlickException e) {
					System.err.println("couldn't load background image");
					e.printStackTrace();
					System.exit(1);
				}
			}
			
			levels[levelNumber] = new Level(tokens[1], mask, fg, bgs);
			
		}
	}
	
	/**
	 * @return the next {@link Level}, {@code null} if no more levels
	 */
	public Level next(){
		if(currentLevel == levels.length - 1){
			// we are at the last level
			return null;
		}
		
		currentLevel++;
		return levels[currentLevel];
	}
	
	public int levelNumber(){
		return currentLevel + 1;
	}
	
	
	
}
