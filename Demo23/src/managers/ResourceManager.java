package managers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public final class ResourceManager {
	private ResourceManager() {}
	public static AssetManager assets=new AssetManager();
	public static LabelStyle buttonStyle;
	public static TextButtonStyle textButtonStyle;
	
	

	
	public static void loadAllResources(){

		//mapas
		assets.setLoader(TiledMap.class, new TmxMapLoader());
		assets.load("maps/Entrada.tmx",TiledMap.class);
		assets.load("maps/Castillo.tmx", TiledMap.class);
		assets.load("maps/Mazmorra.tmx", TiledMap.class);
		assets.load("maps/Techo.tmx",TiledMap.class);
        //elementos de mapa
        assets.load("maps/images/corazon.png", Texture.class);
        assets.load("maps/images/Pocion.png", Texture.class);
        assets.load("maps/images/end.png", Texture.class);
        //enemigos
		assets.load("enemies/arquero/arqueroDerecha.png",Texture.class);
		assets.load("enemies/arquero/arqueroIzquierda.png",Texture.class);
		assets.load("enemies/caballero/caballeroEnemigoDerecha.png",Texture.class);
		assets.load("enemies/caballero/caballeroEnemigoIzquierda.png",Texture.class);
		assets.load("enemies/caballeroGrande/caballeroGrandeDerecha.png",Texture.class);
		assets.load("enemies/caballeroGrande/caballeroGrandeIzquierda.png",Texture.class);
		assets.load("enemies/esqueleto/esqueletoDerecha.png",Texture.class);
		assets.load("enemies/esqueleto/esqueletoIzquierda.png",Texture.class);
		assets.load("enemies/gargola/Gargola.png",Texture.class);
		assets.load("enemies/mago/MagoDerecha.png",Texture.class);
		assets.load("enemies/mago/MagoIzquierda.png",Texture.class);
		assets.load("enemies/primerJefe.png",Texture.class);
		assets.load("enemies/segundoJefe.png",Texture.class);
		assets.load("enemies/tercerJefe.png",Texture.class);
		assets.load("enemies/cuartoJefe.png",Texture.class);
		assets.load("enemies/totemSegundoJefe.png",Texture.class);
        //jugador
        assets.load("player/quieto.png",Texture.class);
        assets.load("player/andarDerecha.png",Texture.class);
        assets.load("player/andarIzquierda.png",Texture.class);
        assets.load("player/esquiveDerecha.png",Texture.class);
        assets.load("player/esquiveIzquierda.png",Texture.class);
        assets.load("player/impulsoAireIzquierda.png",Texture.class);
        assets.load("player/impulsoAireDerecha.png",Texture.class);
        assets.load("player/muerte.png",Texture.class);
        assets.load("player/atacarDerecha.png",Texture.class);
        assets.load("player/atacarIzquierda.png",Texture.class);
        assets.load("player/lanzaIzquierda.png",Texture.class);
        assets.load("player/lanzaDerecha.png",Texture.class);
        assets.load("player/dagasDerecha.png",Texture.class);
        assets.load("player/dagasIzquierda.png",Texture.class);
        assets.load("player/Bola_Fuego.png",Texture.class);
        assets.load("player/Bola_Hielo.png",Texture.class);
        assets.load("player/Rayo_Arriba.png",Texture.class);
        assets.load("player/Rayo_Derecha.png",Texture.class);
        assets.load("player/Rayo_Izquierda.png",Texture.class);
        
        //objetos
        assets.load("maps/images/iconoMenu.png", Texture.class);
        assets.load("maps/images/fondo.jpg", Texture.class);
        assets.load("maps/images/finalMalo.jpg",Texture.class);
        assets.load("maps/images/finalBueno.jpg",Texture.class); 
        //Audio
        assets.load("audio/sounds/ataque.mp3",Sound.class);
        assets.load("audio/sounds/fuego.mp3",Sound.class); 
        assets.load("audio/sounds/hielo.mp3",Sound.class); 
        assets.load("audio/sounds/rayo.mp3",Sound.class);
        //musica
        assets.load("audio/music/entrada.mp3",Music.class);
        assets.load("audio/music/castillo.mp3",Music.class);
        assets.load("audio/music/mazmorra.mp3",Music.class);
        assets.load("audio/music/techo.mp3",Music.class);
        //UI
        assets.load("ui/vida/vida0.png",Texture.class);
        assets.load("ui/vida/vida1.png",Texture.class);
        assets.load("ui/vida/vida2.png",Texture.class);
        assets.load("ui/vida/vida3.png",Texture.class);
        assets.load("ui/vida/vida4.png",Texture.class);
        assets.load("ui/vida/vida5.png",Texture.class);
        assets.load("ui/vida/vida6.png",Texture.class); 
        assets.load("ui/vida/vida7.png",Texture.class);
        assets.load("ui/vida/vida8.png",Texture.class);
        assets.load("ui/vida/vida9.png",Texture.class);
        assets.load("ui/vida/vida10.png",Texture.class);
        assets.load("ui/vida/vida8_0.png",Texture.class);
        assets.load("ui/vida/vida8_1.png",Texture.class);
        assets.load("ui/vida/vida8_2.png",Texture.class);
        assets.load("ui/vida/vida8_3.png",Texture.class);
        assets.load("ui/vida/vida8_4.png",Texture.class);
        assets.load("ui/vida/vida8_5.png",Texture.class);
        assets.load("ui/vida/vida8_6.png",Texture.class);
        assets.load("ui/vida/vida10_0.png",Texture.class);
        assets.load("ui/vida/vida10_1.png",Texture.class);
        assets.load("ui/vida/vida10_2.png",Texture.class);
        assets.load("ui/vida/vida10_3.png",Texture.class);
        assets.load("ui/vida/vida10_4.png",Texture.class);
        assets.load("ui/vida/vida10_5.png",Texture.class);
        assets.load("ui/vida/vida10_6.png",Texture.class);
        assets.load("ui/vida/vida10_7.png",Texture.class);
        assets.load("ui/vida/vida10_8.png",Texture.class);
        
        assets.load("ui/mana/mana0.png",Texture.class);
        assets.load("ui/mana/mana1.png",Texture.class);
        assets.load("ui/mana/mana2.png",Texture.class);
        assets.load("ui/mana/mana3.png",Texture.class);
        assets.load("ui/mana/mana4.png",Texture.class);
        assets.load("ui/mana/mana5.png",Texture.class);
        assets.load("ui/mana/mana6.png",Texture.class);
        assets.load("ui/mana/mana6_0.png",Texture.class);
        assets.load("ui/mana/mana6_1.png",Texture.class);
        assets.load("ui/mana/mana6_2.png",Texture.class);
        assets.load("ui/mana/mana6_3.png",Texture.class);
        assets.load("ui/mana/mana6_4.png",Texture.class);
        assets.load("ui/mana/mana7.png",Texture.class);
        assets.load("ui/mana/mana8.png",Texture.class);
        assets.load("ui/mana/mana8_0.png",Texture.class);
        assets.load("ui/mana/mana8_1.png",Texture.class);
        assets.load("ui/mana/mana8_2.png",Texture.class);
        assets.load("ui/mana/mana8_3.png",Texture.class);
        assets.load("ui/mana/mana8_4.png",Texture.class);
        assets.load("ui/mana/mana8_5.png",Texture.class);
        assets.load("ui/mana/mana8_6.png",Texture.class);
        //NPC
        assets.load("npc/Herrero.png",Texture.class);
        assets.load("npc/Hechicero.png",Texture.class);
        assets.load("npc/Vendedor.png",Texture.class);
        
        
		
	
	}
	
	public static boolean update(){
		return assets.update();
	}
	public static void botones() {
		
		//estilo para botones
        FreeTypeFontGenerator ftfg= new FreeTypeFontGenerator(Gdx.files.internal("sans.ttf"));
		FreeTypeFontParameter ftfp= new FreeTypeFontParameter();
		
		ftfp.size=36;
		ftfp.color=Color.WHITE;
		ftfp.borderColor=Color.BLACK;
		ftfp.borderWidth=2;
		
		BitmapFont fuentePropia=ftfg.generateFont(ftfp);
		buttonStyle=new LabelStyle();
		buttonStyle.font=fuentePropia;
		textButtonStyle=new TextButtonStyle();
		Texture buttonText = ResourceManager.getTexture("maps/images/iconoMenu.png");
		
		TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonText));
	    buttonDrawable.setMinWidth(300);
	    buttonDrawable.setMinHeight(200);
	    textButtonStyle.up = buttonDrawable;
	    textButtonStyle.font = fuentePropia;
		
		
	}
	
	
	public static Texture getTexture(String path) {
		return assets.get(path, Texture.class);
	}
	
	public static Music getMusic(String path){
		return assets.get(path, Music.class);
	}
	
	public static Sound getSound(String path)
	{
		return assets.get(path, Sound.class);
	}
	
	public static TiledMap getMap(String path){
		return assets.get(path, TiledMap.class);
	}

	public static void dispose(){
		assets.dispose();
	}
}
