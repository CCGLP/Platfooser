package com.moonwindgames.platfooser;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;
import com.moonwindgames.platfooser.levels.MenuScreen;
import com.moonwindgames.platfooser.levels.level;
import com.moonwindgames.platfooser.game.Constants;


public class Platfooser_Main extends Game {
	String nombre = Constants.inicio;
	level level;
	Array <String> buenos;
	Array <String> malos;
	int contadorb = 0;
	int contadorm = -1;
	public boolean menu = false;
	@Override
	public void create() {	
		buenos = new Array <String>();
		malos = new Array <String>();
		createArrays();
		if (menu){
		level = new level (nombre);
		
		setScreen(level);
		}
		else{
			setScreen(new MenuScreen(this));
		}
	}
	
	public void createArrays (){
		
		buenos.add(Constants.inicio);
		buenos.add(Constants.mapa2good);
		buenos.add(Constants.mapa3good);
		buenos.add(Constants.mapa4good);
		malos.add(Constants.badfinalmap);
		
	}
	@Override
	public void dispose() {
		super.dispose();
		
	}
	
	@Override
	public void render() {		
		super.render();
		if (menu)
			cambiodeNivel();
		
			
	}
	
	private void cambiodeNivel(){
		if (level.player.setColisionX("buena")){
			contadorb += 1;
			nombre = buenos.get(contadorb);
			if (contadorb == 0)
				contadorm = -1;
			create();
		}
		if (level.player.setColisionX("mala")){
			contadorb-=1;
			System.out.println(contadorb);
			if (contadorb == -1){
				contadorm ++;
				nombre = malos.get(contadorm);
				create();
				if (nombre.equals(Constants.badfinalmap))
					level.player.setY(20*20);
				
			}
			else{
			nombre = buenos.get (contadorb);
			create();
			}
		}
	}
	@Override
	public void resize(int width, int height) {
		super.resize (width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
