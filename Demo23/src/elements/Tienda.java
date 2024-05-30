package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import screens.GameScreen;

public class Tienda extends Npc{
	private Animation<TextureRegion> animacion;
	private Label dialogo;
	private Element pie;
    private Element cabeza;
    private boolean pisa,choca;
	public Tienda(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s, nivel);
		animation = loadFullAnimation("npc/Vendedor.png", 4, 1, 0.5f, true);
		pie=new Element(0,0,s,this.getWidth()/4, this.getHeight()/4);
        cabeza=new Element(0,0,s,this.getWidth()/4, this.getHeight()/4);
        pie.setRectangle();
        cabeza.setRectangle();
        
        dialogo=new Label("",nivel.dialogStyle);
        s.addActor(dialogo);
        ajustarPie();
        ajustarCabeza();
        
	}
	public void act(float delta) {

        super.act(delta);
        ajustarPie();
        ajustarCabeza();
        comprobarColisiones();
    }
	 private void ajustarPie() {
	        pie.setPosition(this.getX()+this.getWidth()*3/4,this.getY()-this.getHeight()/8);
	    }
	 private void ajustarCabeza() {
	        cabeza.setPosition(this.getX()+this.getWidth()*7/8,this.getY()+this.getHeight()*5/8);
	        dialogo.setPosition(this.getX()+this.getWidth()*7/8,this.getY()+this.getHeight()*5/8);

	    }
	 private void comprobarColisiones() {
	        pisa=false;
	        choca=false;
	        for(Solid solido:this.nivel.suelo) {
	            if(pie.overlaps(solido)) {
	                pisa=true;
	            }
	            if(cabeza.overlaps(solido)) {
	                choca=true;

	            }

	        }
	        if(this.overlaps(nivel.player)) {
	            dialogo.setText("curar y llenar mana (Q)");
	            if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
	            	if (Parametros.puntuacion >= 300 && Parametros.vida == 1 && Parametros.mana == 0) {
	                	 Parametros.llenarVida();
	                	 Parametros.llenarMana();
	                	 Parametros.puntuacion -=300;
	                }
	            	if (Parametros.puntuacion >= 300 && Parametros.mana == 0) {
	                	 Parametros.llenarMana();
	                	 Parametros.puntuacion -=300;
	                }
	            	if (Parametros.puntuacion >= 300 && Parametros.vida == 1) {
	                	 Parametros.llenarVida();
	                	 Parametros.puntuacion -=300;
	                }
	            }
	        }else {
	            dialogo.setText("");
	        }

	    }
}

