package com.samuelhindmarsh.ogam.january.graphics.hud;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class HudText extends HudObject {
	
	private String text;
	private Color colour;
	
	protected HudText(String text, Color colour, int x, int y, long expireTime) {
		super(x, y, expireTime);
		this.text = text;
		this.colour = colour;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(colour);
		g.drawString(text, x, y);
	}

}
