package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Demo;
import screens.GameScreen;

public class Fin extends Enemy{
	private Animation<TextureRegion> fin;
	public Demo game;
	
	public Fin(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s, nivel);
		fin=loadFullAnimation("maps/images/end.png",1,1,0.15f,false);
		this.setRectangle();			
		this.setPolygon(5,5,5,5,5);
		this.vida = 1000000000;
	}
	
	public void act(float delta) {
		super.act(delta);
		this.applyPhysics(delta);
	}		

}
