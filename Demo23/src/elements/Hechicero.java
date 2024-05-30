package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import game.Parametros;
import screens.GameScreen;

public class Hechicero extends Npc{
	private Animation<TextureRegion> animacion;
	private Label dialogo;
	private Element pie;
    private Element cabeza;
    private boolean pisa,choca;
    public boolean todo = false;
	public Hechicero(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s, nivel);
		animation = loadFullAnimation("npc/Hechicero.png", 5, 1, 0.15f, true);
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
	        if (this.overlaps(nivel.player)) {
	            dialogo.setText("aprender hechizo (Q)");
	            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
	            	if (Parametros.puntuacion >= 3000 && Parametros.hechizo == 2) {
	            		Parametros.hechizo++;
	                    Parametros.puntuacion -= 3000;
	                } else if (Parametros.puntuacion >= 2000 && Parametros.hechizo == 1) {
	                	Parametros.hechizo++;
	                    Parametros.puntuacion -= 2000;
	                } else if (Parametros.puntuacion >= 1000 && Parametros.hechizo == 0) {
	                	Parametros.hechizo++;
	                    Parametros.puntuacion -= 1000;
	                }
	            }
	        } else {
	            dialogo.setText("");
	        }

	    }
}

