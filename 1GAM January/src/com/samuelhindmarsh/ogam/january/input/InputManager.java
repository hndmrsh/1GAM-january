package com.samuelhindmarsh.ogam.january.input;

import org.newdawn.slick.Input;

public class InputManager {
	
	/////////////////////////////////////////////////////////
	private static InputManager INSTANCE;
	
	/** Override default constructor so can't instantiate */
	private InputManager(){}
	
	public static InputManager getInstance(){
		if(INSTANCE == null){
			INSTANCE = new InputManager();
		}
		
		return INSTANCE;
	}
	//////////////////////////////////////////////////////////
	
	
	private static float AXIS_DEADZONE = 0.2f;
	
	private Input input;
	
	
	public void init(Input input) {
		this.input = input;
	}

	public boolean isControlDown(Action action){
		switch (action) {
		case LEFT:
			return input.isControllerLeft(Input.ANY_CONTROLLER) || input.isKeyDown(Input.KEY_A);
		case RIGHT:
			return controllerRightDown() || input.isKeyDown(Input.KEY_D);
		case FIRE:
			return controllerAxisValue(4) < -AXIS_DEADZONE || input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		case GRENADE:
			return controllerAxisValue(4) > AXIS_DEADZONE || input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON);
		case JUMP:
			return input.isButtonPressed(5, Input.ANY_CONTROLLER) || input.isKeyDown(Input.KEY_W);
		case CROUCH:
			return controllerButtonDown(4) || input.isKeyDown(Input.KEY_S);
		case PICKUP:
			return controllerButtonDown(0) || input.isKeyDown(Input.KEY_SPACE);
		default:
			return false;
		}
	}
	
	private boolean controllerButtonDown(int controllerButton){
		for(int i = 0; i < input.getControllerCount(); i++){
			if(input.isButtonPressed(controllerButton, i)){
				return true;
			}
		}
		return false;
	}
	
	private boolean controllerLeftDown(){
		for(int i = 0; i < input.getControllerCount(); i++){
			if(input.isControllerLeft(i)){
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
	
	private float controllerAxisValue(int controllerAxis){
		float best = 0f;
		for(int i = 0; i < input.getControllerCount(); i++){
			if(controllerAxis < input.getAxisCount(i)){
				float axisValue = input.getAxisValue(i, controllerAxis);
				System.out.println(axisValue);
				if(axisValue < 0f){
					best = Math.min(axisValue, best);
				} else {
					best = Math.max(axisValue, best);
				}
			}
		}
		return best;
	}
	
}
