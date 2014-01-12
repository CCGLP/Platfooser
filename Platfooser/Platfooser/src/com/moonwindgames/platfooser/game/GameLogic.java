package com.moonwindgames.platfooser.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.moonwindgames.platfooser.levels.level;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Application;
public class GameLogic extends InputAdapter {
	
// Es una idea tan "curiosa" que merece ser guardada en comentario	private float acelerateY = 0.0f;
	
	public GameLogic (){
		init();
	}
	
	private void init () {
		Gdx.input.setInputProcessor(this);
	
	}
	
	public void handleInputGame(float deltaTime, level level) {
		
	
	if (Gdx.app.getType() == Application.ApplicationType.Desktop){
	  
		
		if (Gdx.input.isKeyPressed(Keys.D) || (Gdx.input.isKeyPressed(Keys.RIGHT) )){
			if (level.player.collisionY)
			level.player.increaseVelocityX(Constants.accelerationX);
		
			else {
				
				level.player.setVelocityX(Constants.velocityXjumping);
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)|| (Gdx.input.isKeyPressed(Keys.LEFT))){
			if (level.player.collisionY)
			level.player.decreaseVelocityX(Constants.accelerationX);
			else{
				
				level.player.setVelocityX (-Constants.velocityXjumping);
			}
			
		}
	
	}
	
	if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE) ) {
		// Enved de pelearme con el techo puedo poner el techo más alto y pista
		if (level.player.canUseJump){
			level.player.setVelocityY (Constants.jump);
			level.jumpSound.play();
		}
		level.player.canUseJump = false;
			
		
			
	}
		
	
		
		 
		// Esto estaba en el libro. Wtf. if (Application.ApplicationType.Android == Application.ApplicationType.valueOf("Android")){
			if (Gdx.app.getType() == Application.ApplicationType.Android){
			if (Gdx.input.getAccelerometerY() > Constants.accelerometermargin) {
				
				if (level.player.collisionY)
				level.player.increaseVelocityX (Constants.accelerationX);
				
				else 
					level.player.setVelocityX (Constants.velocityXjumping);
			}
			else if (Gdx.input.getAccelerometerY() < -Constants.accelerometermargin){
				if (level.player.collisionY)
				level.player.decreaseVelocityX(Constants.accelerationX);
				
				else 
					level.player.setVelocityX(-Constants.velocityXjumping);
			}
				
			
			}
			
		}
	
			
	
	
	
	
	
}

