package com.moonwindgames.platfooser.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.moonwindgames.platfooser.gameObjects.Player;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.moonwindgames.platfooser.game.GameLogic;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
public class level implements Screen {
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	public OrthographicCamera camera;
	public Player player;
	private GameLogic logic;
	private String levelName;
	public Sound jumpSound; 
	public Music backgroundMusic;
	private boolean musicasuena = true;
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(camera);
		renderer.render();
		logic.handleInputGame(Gdx.graphics.getDeltaTime(), this);
		camera.position.x = player.getX() + player.getOriginX();
		camera.position.y = player.getY() + player.getOriginY();
		camera.update();
		renderer.getSpriteBatch().begin();
		player.draw(renderer.getSpriteBatch());
		renderer.getSpriteBatch().end();
		muerte();
		
		}
		
	
	public void muerte () {
		if (player.getY() < 0){
			player.setPosition(8*32, 20*32);
		}
	}
	
	public level (String name){
		levelName = name;
		
	}
	
	@Override
	public void resize(int width, int height) {
	    camera.setToOrtho( false);
		camera.update();
		}

	@Override
	public void show() {
		map = new TmxMapLoader().load(levelName);
		renderer = new OrthogonalTiledMapRenderer (map);
		camera = new OrthographicCamera ();
		player = new Player(new Texture ("images/Protagonista-1.png"), (TiledMapTileLayer)map.getLayers().get("Fondo"));
		camera.position.x = player.getX() + player.getOriginX();
		camera.position.y = player.getY() + player.getOriginY();
		logic = new GameLogic ();
		camera.zoom = 0.5f;
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("sonidos/Salto-Platfooser.wav"));
		backgroundMusic = Gdx.audio.newMusic (Gdx.files.internal ("musica/YouOnlyGetOneBeat.mp3"));
		// Cuando pasa de nivel suenan las dos a la vez 
		if (musicasuena){
		backgroundMusic.play();
		backgroundMusic.setLooping(true);
		musicasuena = false;
		}
	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
	map.dispose();
	renderer.dispose();
	backgroundMusic.dispose();
	jumpSound.dispose();
	}

}
