package com.moonwindgames.platfooser.levels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.moonwindgames.platfooser.Platfooser_Main;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.moonwindgames.platfooser.game.Constants;

public class MenuScreen implements Screen {
	private Stage stage;
	private Skin skin;
	//menu
	private Image imgLogo;
	private Image imgTitle;
	private Image imgMoon;
	private Button btnMenuPlay;
	private Button btnMenuOptions;
	
	//options
	private Window winOptions;
	private TextButton btnWinOptSave;
	private TextButton btnWinOptCancel;
	private CheckBox chkSound;
	private Slider sldSound;
	private CheckBox chkMusic;
	private Slider sldMusic;
	private CheckBox chkShowFpsCounter;
	
	// Creo que va a haber que borrarlo
	private SpriteBatch batch;
	private Platfooser_Main game;
	private Texture textura;
	private OrthographicCamera camera;
	private Sprite sprite;
	
	public MenuScreen (Platfooser_Main game) {
		this.game = game;
	}
	@Override
	public void render(float delta) {
			Gdx.gl.glClearColor(255.0f, 255.0f, 255.0f, 1.0f);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			stage.act(delta);
			stage.draw();
		
	}

	private void rebuildStage () {
		skin = new Skin (Gdx.files.internal(Constants.SKIN_UI),
				new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
		//Construimos las capas.
		Table layerObjects = buildObjectsLayer();
		Table layerLogos = buildLogosLayer();
		Table layerControls = buildControlsLayer();
		Table layerOptionsWindow = buildOptionsWindowLayer();
		//construimos el stage
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
		stack.add(layerObjects);
		stack.add(layerControls);
		stack.add(layerLogos);
		stack.add(layerOptionsWindow);
		
	}
	
	private Table buildObjectsLayer() {
		Table layer = new Table();
		return layer;
	}
	private Table buildLogosLayer() {
		Table layer = new Table();
		layer.left().top();
		
		//+ Game Logo
		imgLogo = new Image (skin,"title");
		layer.add(imgLogo);
		layer.row().expandY();
		//
		return layer;
	}
	private Table buildControlsLayer() {
		Table layer = new Table();
		layer.right().bottom();
		// + Play button
		btnMenuPlay = new Button(skin, "play");
		layer.add(btnMenuPlay);
		btnMenuPlay.addListener(new ChangeListener(){
			

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onPlayClicked();
				
			}
		});
		layer.row();
		//Options Button
		btnMenuOptions = new Button (skin, "options");
		layer.add(btnMenuOptions);
		btnMenuOptions.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onOptionsClicked();
			}
			
		});
		return layer;
	}
	private Table buildOptionsWindowLayer() {
		Table layer = new Table();
		return layer;
	}
	private void onPlayClicked() {
		game.menu = true;
		game.create();
		
	}
	private void onOptionsClicked() {}
	@Override
	public void resize(int width, int height) {

		stage.setViewport(width, height,false);
		
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
		
		
	}

	@Override
	public void hide() {
		stage.dispose();
		skin.dispose();
		
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
		// TODO Auto-generated method stub
		
	}

}
