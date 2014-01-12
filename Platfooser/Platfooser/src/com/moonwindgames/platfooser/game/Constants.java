package com.moonwindgames.platfooser.game;

public class Constants {
	//Estos 3 van en razón del rozamiento. Tienen que ser multiplos de 7
	static public float velocityXjumping = 238;
	static public float accelerationX = 14;
	static public float rozamiento = 7;
	
	// El salto puede tener la velocidad que quiera
	static public float jump = 382;
	static public float gravity = 300*1.8f;
	//Es el margen que le dejamos al acelerometro. Del -2 al 2 hará como si no hubiera recogido dato alguno.
	static public float accelerometermargin = 2;
	//Texture atlas-ui, skins
	static public String TEXTURE_ATLAS_UI=
			"images/asset_menu.pack";
	static public String TEXTURE_ATLAS_LIBGDX_UI =
			"images/uiskin.atlas";
	static public String SKIN_LIBGDX_UI = 
			"images/uiskin.json";
	static public String SKIN_UI = 
			"images/ui.json";
	//Mapas
	static public String inicio = "map/mapa1.tmx";
	static public String mapa2good = "map/mapa2-good.tmx";
	static public String mapa3good = "map/mapa3-good.tmx";
	static public String mapa4good = "map/mapa4-good.tmx";
	static public String badfinalmap = "map/finalbadmap.tmx";
	}
