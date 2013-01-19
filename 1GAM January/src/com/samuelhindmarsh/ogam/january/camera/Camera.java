package com.samuelhindmarsh.ogam.january.camera;

import com.samuelhindmarsh.ogam.january.JanuaryWindow;

public class Camera {

	private Coord pos;
	private int width, height;
	
	private double zoom = 1.0;
	
	private boolean zooming = false;
	private long zoomEnd;
	private int zoomDuration;
	private double deltaZoom;
	
	public Camera(Coord pos) {
		this.pos = pos;
	}
	
	public void tick(int delta){
		if(zooming){
			// this ensures we do not zoom too far by overshooting the zoom end time
			long timeToZoomEndAfterThisTick = delta + (zoomEnd - System.currentTimeMillis());
			if(timeToZoomEndAfterThisTick <= 0){
				delta += timeToZoomEndAfterThisTick;
				zooming = false;
			}
			
			// now, apply the correct amount of zoom change and update the camera size
			zoom += deltaZoom * ((double)delta / (double)zoomDuration);
		}
		
		// sanity check
		if(zoom < 0.01)
			zoom = 0.01;
		
		width = (int)(JanuaryWindow.WIDTH * (1.0 / zoom));
		height = (int)(JanuaryWindow.HEIGHT * (1.0 / zoom));
		
		System.out.println("current zoom at " + zoom);
	}
	
	public void zoom(double deltaZoom, int durationInMillis){
		// don't interrupt a zoom or results will be unpredictable
		if(!zooming){
			zoomDuration = durationInMillis;
			zoomEnd = System.currentTimeMillis() + durationInMillis;
			this.deltaZoom = deltaZoom;
			zooming = true;
		}
	}
	
	public Coord getPos() {
		return pos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

}
