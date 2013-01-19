package com.samuelhindmarsh.ogam.january.level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.samuelhindmarsh.ogam.january.JanuaryWindow;
import com.samuelhindmarsh.ogam.january.camera.Camera;
import com.samuelhindmarsh.ogam.january.camera.Coord;
import com.samuelhindmarsh.ogam.january.game.JanuaryGame;

public class Level {
	
	private String name;
	
	private Image collisionMask;
	private Image foreground;
	private Image[] backgrounds;
	
	private Coord playerStart;
	
	public Level(String name, Image collisionMask, Image foreground, Image[] backgrounds, Coord playerStart) {
		this.name = name;
		this.collisionMask = collisionMask;
		this.foreground = foreground;
		this.backgrounds = backgrounds;
		this.setPlayerStart(playerStart);
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

	public void render(Camera c, Graphics g) {
		Coord srcTopLeft = new Coord((int)(c.getPos().getX() - (c.getWidth() / 2.0)), (int)(c.getPos().getY() - (c.getHeight() / 2.0)));
		Coord srcBottomRight = new Coord((int)(c.getPos().getX() + (c.getWidth() / 2.0)), (int)(c.getPos().getY() + (c.getHeight() / 2.0)));
		
		for(Image bg : backgrounds){
			g.drawImage(bg, 0, 0, JanuaryWindow.WINDOW_WIDTH, JanuaryWindow.WINDOW_HEIGHT, 
					srcTopLeft.getX(), srcTopLeft.getY(), srcBottomRight.getX(), srcBottomRight.getY());	
		}
		
		g.drawImage(foreground, 0, 0, JanuaryWindow.WINDOW_WIDTH, JanuaryWindow.WINDOW_HEIGHT, 
				srcTopLeft.getX(), srcTopLeft.getY(), srcBottomRight.getX(), srcBottomRight.getY());
		
	}

	public Coord getPlayerStart() {
		return playerStart;
	}

	public void setPlayerStart(Coord playerStart) {
		this.playerStart = playerStart;
	}
}
