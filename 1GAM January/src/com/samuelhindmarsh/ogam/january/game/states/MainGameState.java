package com.samuelhindmarsh.ogam.january.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.samuelhindmarsh.ogam.january.camera.Camera;
import com.samuelhindmarsh.ogam.january.game.JanuaryGame;
import com.samuelhindmarsh.ogam.january.input.Action;
import com.samuelhindmarsh.ogam.january.level.Level;
import com.samuelhindmarsh.ogam.january.managers.InputManager;
import com.samuelhindmarsh.ogam.january.managers.LevelManager;

public class MainGameState extends BasicGameState{

	private Level level;
	private Camera camera;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		level = LevelManager.INSTANCE.next();
		camera = new Camera(level.getPlayerStart());
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		level.render(camera, g);
		
		if(JanuaryGame.DEBUG){
			debug(g);
		}
	}
	
	private void debug(Graphics g){
		// TODO bind commands to inputs as in http://www.slick2d.org/wiki/index.php/Input
		Action[] values = Action.values();
		for(int i = 0; i < values.length; i++){
			g.setColor(InputManager.INSTANCE.isControlDown(values[i]) ? Color.green : Color.red);
			g.drawString(values[i].toString(), 20, i*20+80);
		}
		
		g.setColor(InputManager.INSTANCE.isLastInputFromController()? Color.green : Color.red);
		g.drawString("Last input was from controller", 20, 400);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		camera.tick(delta);
		
		if(InputManager.INSTANCE.isControlDown(Action.FIRE)){
			camera.zoom(1.0, 100);
		} else if(InputManager.INSTANCE.isControlDown(Action.GRENADE)){
			camera.zoom(-1.0, 100);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	} 

}
