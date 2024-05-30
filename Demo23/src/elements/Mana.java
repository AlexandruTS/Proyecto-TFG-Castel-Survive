package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import screens.GameScreen;

public class Mana extends Enemy {
	private Animation<TextureRegion> animacion;
	
	public Mana(float x, float y, Stage s, GameScreen nivel) {
		 super(x, y, s, nivel);
	        this.nivel = nivel;
	        animacion = loadFullAnimation("maps/images/Pocion.png", 1, 1, 0.15f, true);
	        this.setRectangle();
	        this.setPolygon(5,5,5,5,5);
	        this.vida = 1000000000;
	}
	
	public void act(float delta) {
		super.act(delta);
		this.applyPhysics(delta);
	}
	
	public void aumentarParametroMana() {
		Parametros.aumentarMana++;
	}
}
