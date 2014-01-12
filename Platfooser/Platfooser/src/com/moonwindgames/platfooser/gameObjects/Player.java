package com.moonwindgames.platfooser.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.moonwindgames.platfooser.game.Constants;

public class Player extends Sprite{
	//Velocidad en un vector de dos campos
	private Vector2 velocity= new Vector2() ;
	//Velocidad límite y gravedad
	private float gravity = Constants.gravity;
	//Antigua posición en el eje X y en el eje Y
	private float oldX, oldY;
	//Todo lo relacionado con el TiledMap, para sacar la colisión
	private TiledMapTileLayer collisionLayer;
	private float tileWidth = 32;//collisionLayer.getTileWidth(); Medido en pixeles
	private float tileHeight = 32;//collisionLayer.getTileHeight(); Medido en pixeles
	
	//Unos boolean que decidiran si se produce la colisión o no
	public boolean collisionX = false, collisionY = false;
	//Track this.
	//Todo lo relacionado con el salto
	private boolean collisionroof = false;
	private float timeJumping = 0;
	public boolean canUseJump = true;
	

	
	
	
	
	public Player (Texture texture, TiledMapTileLayer collisionLayer){
		super (texture);
		this.setPosition(8*32, 20*32);
		this.collisionLayer = collisionLayer;
		
	
	}
	private void handletimeJumping (float deltaTime){
		timeJumping += deltaTime;
		if (timeJumping > 1.0f){
			canUseJump = false;
			setVelocityY (- getGravity());
			
		}
		if (collisionY && !collisionroof){
				
				timeJumping = 0.0f;
				canUseJump = true;
			}
			if (collisionroof){
				collisionroof = false;
			}
	}
	
	public float getVelocityX (){
		return this.velocity.x;
	}
	
	public float getVelocityY (){
		return this.velocity.y;
	}
	public void setVelocityX (float number){
		this.velocity.x = number;
		
	}
	public void setVelocityY (float number){
		this.velocity.y = number;
	}
	
	public void increaseVelocityX (float number){
		this.velocity.x += number;
	}
	public void decreaseVelocityX (float number){
		this.velocity.x -= number;
	}
	
	public void backToNormal (){
		this.gravity = Constants.gravity;
		
	}
	public float getGravity () {
		return gravity;
	}
	public boolean setColisionX (String key){
		
		if (velocity.x<0){
			collisionX = collisionLayer.getCell((int)(getX()/tileWidth), (int) ((getY()+getHeight())/ tileHeight))
					.getTile().getProperties().containsKey(key);
			
			if (!collisionX)
			collisionX = collisionLayer.getCell((int)(getX()/tileWidth), (int) ((getY()+getHeight()/2)/ tileHeight))
					.getTile().getProperties().containsKey(key);
			if (!collisionX)
			collisionX = collisionLayer.getCell((int)(getX()/tileWidth), (int) ((getY())/ tileHeight))
					.getTile().getProperties().containsKey(key);
		}
		
		else if (velocity.x>0){
		
			collisionX = collisionLayer.getCell((int)((getX() + getWidth())/tileWidth), (int) ((getY()+getHeight())/ tileHeight))
					.getTile().getProperties().containsKey(key);
			if (!collisionX)
			collisionX = collisionLayer.getCell((int)((getX()+ getWidth())/tileWidth), (int) ((getY()+getHeight()/2)/ tileHeight))
					.getTile().getProperties().containsKey(key);
			if (!collisionX)
			collisionX = collisionLayer.getCell((int)((getX()+ getWidth())/tileWidth), (int) ((getY())/ tileHeight))
					.getTile().getProperties().containsKey(key);
			
		}

		else if (velocity.x == 0)
			collisionX = false;
		
		return collisionX;
	}
	
	
	public boolean setColisionY(String key){
		if (velocity.y < 0){
			canUseJump = false;
			
			collisionY = collisionLayer.getCell((int)(getX() /tileWidth), (int) ((getY())/ tileHeight))
					.getTile().getProperties().containsKey(key);
			
			if (!collisionY)
				
			collisionY = collisionLayer.getCell((int)((getX()+ getWidth()/2)/tileWidth), (int) ((getY())/ tileHeight))
					.getTile().getProperties().containsKey(key);
			
			if (!collisionY)
			
			collisionY = collisionLayer.getCell((int)((getX()+ getWidth())/tileWidth), (int) ((getY())/ tileHeight))
					.getTile().getProperties().containsKey(key);
			
		}
		else if (velocity.y > 0){
			
			collisionY = collisionLayer.getCell((int)(getX() /tileWidth), (int) ((getY()+getHeight())/ tileHeight))
					.getTile().getProperties().containsKey(key);
			if (!collisionY)
			collisionY = collisionLayer.getCell((int)((getX()+ getWidth()/2)/tileWidth), (int) ((getY()+getHeight()  )/ tileHeight))
					.getTile().getProperties().containsKey(key);
			if (!collisionY)
			collisionY = collisionLayer.getCell((int)((getX()+ getWidth())/tileWidth), (int) ((getY()+getHeight())/ tileHeight))
					.getTile().getProperties().containsKey(key);
		collisionroof = collisionY;	
		}
		
		return collisionY;
	}
	
	private void rozamientoaire (){
		//La pobre velocidad me da un poco de pena, que mareo
		//Y no estoy muy seguro de mantener esto
		velocity.y -= 2.0f;
		
	}
@Override
	public void draw (Batch spritebatch){
		super.draw(spritebatch);
		this.update(Gdx.graphics.getDeltaTime());
		
	}
	private void rozamiento () {
		//El rozamiento necesita de trabajo. Si acelera tan brutamente... ._.
		if (velocity.x > 0){ 

		velocity.x-= Constants.rozamiento;
		}
		
		if (velocity.x < 0)
		velocity.x+= Constants.rozamiento;
	}
	private void update(float deltaTime) {
		
		velocity.y -= gravity*deltaTime;
		
		// if (velocity.y >speed)
			//velocity.y = speed;
		//else if (velocity.y < speed)
		//	velocity.y = -speed;
		
		rozamientoaire ();
		oldX = getX();
		oldY = getY();
		
		rozamiento();
		setX (getX()+ velocity.x *deltaTime);
		setColisionX ("colision");
		
		if (collisionX){
			
			setX (oldX);
			velocity.x = 0;
		}
		
		
		setY (getY()+ velocity.y *deltaTime);
		setColisionY ("colision");
		if (collisionY){
			setY (oldY);
			velocity.y = 0;
		}
		
		//Aun no hace nada, tengo que modificar el tiempo de salto
		// Para que la gravedad tenga efecto
		
	handletimeJumping (Gdx.graphics.getDeltaTime());
		// Rotar. Rota 10 grados cada frame en el sentido que tiene sentido para cada dirección
		if (velocity.x > 0)
		this.rotate(-10);
		
		if (velocity.x < 0)
		this.rotate (10);
		
		
	}
	
}
