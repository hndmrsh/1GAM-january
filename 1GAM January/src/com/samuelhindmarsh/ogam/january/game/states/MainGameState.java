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
		
		String[] inputs = {"left_stick", "right_stick", "left_trigger", "right_trigger", "left_bumper", "right_bumper", "A", "B"};
		boolean[] pressed = new boolean[inputs.length];
		// TODO populate pressed
		
		pressed[0] = axisActive(input,1,0) || axisActive(input,1,1);
		pressed[1] = axisActive(input,1,2) || axisActive(input,1,3);
		pressed[2] = input.getAxisValue(1, 4) > deadzone;
		pressed[3] = input.getAxisValue(1, 4) < -deadzone;
		pressed[4] = input.isButtonPressed(4, 1);
		pressed[5] = input.isButtonPressed(5, 1);
		pressed[6] = input.isButtonPressed(0, 1);
		pressed[7] = input.isButtonPressed(1, 1);
		
		for(int i = 0; i < inputs.length / 2; i++){
			g.setColor(pressed[i*2] ? Color.green : Color.red);
			g.drawString(inputs[i*2], 0, i*20+100);
			g.setColor(pressed[i*2+1] ? Color.green : Color.red);
			g.drawString(inputs[i*2+1], 200, i*20+100);
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
