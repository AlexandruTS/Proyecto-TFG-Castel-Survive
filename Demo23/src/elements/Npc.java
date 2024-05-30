package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;
import game.Parametros;
import screens.GameScreen;

public class Npc extends Element{
	public GameScreen nivel;
	
	    public Npc(float x, float y, Stage s, GameScreen nivel) {
	        super(x,y,s);
	        this.nivel=nivel;
	        this.setEnabled(true);
	    }
	    public void act(float delta) {
	        super.act(delta);
	    }
	    public void collide() {
	        if(this.overlaps(this.nivel.player)) {
	            nivel.actualizarInterfaz();
	            
	        }

	    }
	
}
