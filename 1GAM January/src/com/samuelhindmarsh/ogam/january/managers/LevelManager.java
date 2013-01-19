package com.samuelhindmarsh.ogam.january.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.samuelhindmarsh.ogam.january.camera.Coord;
import com.samuelhindmarsh.ogam.january.level.Level;

public enum LevelManager {
	INSTANCE;

	private static final String DIRECTORY_LEVELS = "levels";
	private static final String FILE_LIST = "levels"; // yes these are the same shut up.
	private static final String FILENAME_EXTENSION = "png";
	
	private ArrayList<Level> levels = new ArrayList<Level>();
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
		
		while(listScanner.hasNextLine()){
			// parse the level definition
			String[] tokens = listScanner.nextLine().split(",");
			
			if(tokens[0].startsWith("#")){
				// commented line
				continue;
			}
			
			// filter for selecting only background files
			FilenameFilter bgFilesFilter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith("bg");
				}
			};

			File levelDirectory = new File (DIRECTORY_LEVELS + File.separator + tokens[0]);

			Image fg = ResourceManager.INSTANCE.loadImageResource(levelDirectory + File.separator + "fg." + FILENAME_EXTENSION);
			Image mask = ResourceManager.INSTANCE.loadImageResource(levelDirectory + File.separator + "mask." + FILENAME_EXTENSION);
			
			File[] bgFiles = levelDirectory.listFiles(bgFilesFilter);
			Image bgs[] = new Image[bgFiles.length];
			for(int f = 0; f < bgs.length; f++){
				bgs[f] = ResourceManager.INSTANCE.loadImageResource(levelDirectory + File.separator + "bg" + f + "." + FILENAME_EXTENSION);
			}
			
			
			Coord playerStart = new Coord(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
			
			levels.add(new Level(tokens[1], mask, fg, bgs, playerStart));
		}
	}
	
	/**
	 * @return the next {@link Level}, {@code null} if no more levels
	 */
	public Level next(){
		if(currentLevel == levels.size()){
			// we are at the last level
			return null;
		}
		
		currentLevel++;
		return levels.get(currentLevel-1);
	}
	
	public int levelNumber(){
		return currentLevel + 1;
	}
	
	
	
}
