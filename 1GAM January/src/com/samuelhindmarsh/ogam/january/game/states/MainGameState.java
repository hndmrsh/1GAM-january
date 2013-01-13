package com.samuelhindmarsh.ogam.january.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.samuelhindmarsh.ogam.january.input.Action;
import com.samuelhindmarsh.ogam.january.input.InputManager;

public class MainGameState extends BasicGameState{

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		// TODO this is all temporary stuff
		Input input = container.getInput();
		
		// TODO bind commands to inputs as in http://www.slick2d.org/wiki/index.php/Input
		Action[] values = Action.values();
		for(int i = 0; i < values.length; i++){
			g.setColor(InputManager.getInstance().isControlDown(values[i]) ? Color.green : Color.red);
			g.drawString(values[i].toString(), 20, i*20+80);
		}
		
		
	}
	
	final float deadzone = 0.2f;
	
	private boolean axisActive(Input input, int controller, int axis) {
		
		float val = input.getAxisValue(controller, axis);
		return !(val > -deadzone && val < deadzone);
	}
	

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	} 

}
