package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import screens.GameScreen;

public class Totem extends Enemy{
	
	private Animation<TextureRegion> animacion;
	 
	public Totem(float x, float y, Stage s, GameScreen nivel) {
		 super(x, y, s, nivel);
	        this.nivel = nivel;
	        animacion = loadFullAnimation("enemies/totemSegundoJefe.png", 1, 1, 0.15f, true);
	        this.setRectangle();
	        this.setPolygon(5,5,5,5,5);
	        this.vida = 1000000000;
	}
	
	public void act(float delta) {
		super.act(delta);
		this.applyPhysics(delta);
	}
	
}
