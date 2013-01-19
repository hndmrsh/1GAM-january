package com.samuelhindmarsh.ogam.january.graphics.hud;

import org.newdawn.slick.Graphics;


public abstract class HudObject {

	int x, y;
	long expireTime;

	protected HudObject(int x, int y, long expireTime) {
		this.x = x;
		this.y = y;
		this.expireTime = expireTime;
	}
	
	public long getExpireTime() {
		return expireTime;
	}
	
	public abstract void render(Graphics g);
}
