package com.samuelhindmarsh.ogam.january.managers;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.samuelhindmarsh.ogam.january.input.Action;

public enum InputManager {
	INSTANCE;
	
	private static float AXIS_DEADZONE = 0.2f;
	
	private Input input;
	
	private static final int MAXIMUM_SUPPORTED_AXES = 6;
	private float[] initialAxisValues = new float[MAXIMUM_SUPPORTED_AXES]; // do any controllers have more than 6 axes?
	private boolean[] axisHasBeenUsed = new boolean[MAXIMUM_SUPPORTED_AXES];
	
	private boolean lastInputFromController = false;
	
	public void init(Input input) throws SlickException {
		this.input = input;
		for(int i = 0; i < MAXIMUM_SUPPORTED_AXES; i++){
			System.out.println("axis" + i + " " + controllerAxisValue(i));
			initialAxisValues[i] = controllerAxisValue(i);
			axisHasBeenUsed[i] = false;
		}
		
		lastInputFromController = false;
	}

	public boolean isControlDown(Action action){
		// assume keyboard input for now
		// assumption will be corrected if it was actually a controller input

		boolean keyboard = false, controller = false;
		
		switch (action) {
		case LEFT:
			controller = controllerAxisValue(1) < -AXIS_DEADZONE || controllerLeftDown();
			keyboard = input.isKeyDown(Input.KEY_A);
			break;
		case RIGHT:
			controller = controllerAxisValue(1) > AXIS_DEADZONE || controllerRightDown();
			keyboard = input.isKeyDown(Input.KEY_D);
			break;
		case FIRE:
			controller = controllerAxisValue(4) < -AXIS_DEADZONE;
			keyboard = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
			break;
		case GRENADE:
			controller = controllerAxisValue(4) > AXIS_DEADZONE;
			keyboard = input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON);
			break;
		case JUMP:
			controller = input.isButtonPressed(5, Input.ANY_CONTROLLER);
			keyboard = input.isKeyDown(Input.KEY_W);
			break;
		case CROUCH:
			controller = controllerButtonDown(4);
			keyboard = input.isKeyDown(Input.KEY_S);
			break;
		case PICKUP:
			controller = controllerButtonDown(0);
			keyboard = input.isKeyDown(Input.KEY_SPACE);
			break;
		}
		
		if(controller) // we picked up 
			lastInputFromController = true;
		else if(keyboard)
			lastInputFromController = false;
		
		return controller || keyboard;
	}
	
	private boolean controllerButtonDown(int controllerButton){
		for(int i = 0; i < input.getControllerCount(); i++){
			if(input.isButtonPressed(controllerButton, i)){
				return true;
			}
		}
		return false;
	}
	
	private boolean controllerRightDown(){
		for(int i = 0; i < input.getControllerCount(); i++){
			if(input.isControllerRight(i)){
				return true;
			}
		}
		return false;
	}
	
	private boolean controllerLeftDown(){
		for(int i = 0; i < input.getControllerCount(); i++){
			if(input.isControllerLeft(i)){
				if(axisHasBeenUsed[1]){ // stupid "-1" bug
					return true;
				}
			}
		}
		return false;
	}
	
	
	private float controllerAxisValue(int controllerAxis){
		float best = 0f;
		for(int i = 0; i < input.getControllerCount(); i++){
			if(controllerAxis < input.getAxisCount(i)){
				float axisValue = input.getAxisValue(i, controllerAxis);
				if(axisValue < 0f){
					best = Math.min(axisValue, best);
				} else {
					best = Math.max(axisValue, best);
				}
			}
		}
		
		if(best == initialAxisValues[controllerAxis] && 
				!axisHasBeenUsed[controllerAxis]) 
			return 0f;
		
		axisHasBeenUsed[controllerAxis] = true;
		
		return best;
	}
	
	public Input getInput() {
		return input;
	}

	/**
	 * Was the last input received from the controller? (If so,
	 * hide the crosshairs which act as a mouse pointer)
	 * @return
	 */
	public boolean isLastInputFromController() {
		return lastInputFromController;
	}
	
}
