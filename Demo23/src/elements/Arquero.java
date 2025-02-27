package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import screens.GameScreen;

public class Arquero extends Enemy {
	
	private Array<Fuego> balas;
	private int numBalas = 5;
	private int balaActual = 0;
	private float cadencia = 1;
	private float tiempoDisparo = 0;
	private float rangoVision = 90;
    private Animation<TextureRegion> arqueroDerecha;
    private Animation<TextureRegion> arqueroIzquierda;
    private boolean muerto = false;
	public Arquero(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s, nivel);
		arqueroDerecha = loadFullAnimation("enemies/arquero/arqueroDerecha.png", 2, 1, 0.1f, true);
        arqueroIzquierda = loadFullAnimation("enemies/arquero/arqueroIzquierda.png", 2, 1, 0.1f, true);
        balas = new Array<Fuego>();
		for (int i = 0; i < numBalas; i++) {
			balas.add(new Fuego(0, 0, s, nivel, false));
		}
		this.vida = 1;
	}
	
	public void act(float delta) {
		 if (getEnabled()) {
	            super.act(delta);
	            float distancia = Vector2.dst(getX(), getY(), nivel.player.getX(), nivel.player.getY());
	            if (distancia < rangoVision) {
	                if (tiempoDisparo <= 0) {
	                    disparar();
	                } else {
	                    tiempoDisparo -= delta;
	                }
	                actualizarAnimacion();
	                if(vida<= 0 && !muerto) {
	                	Parametros.puntuacion += 100;
	                	muerto = true;
	                }
	            }
	        }
	}

	private void disparar() {
		Vector2 direccion = new Vector2(nivel.player.getX() - this.getX(), nivel.player.getY() - this.getY()).nor();

		balas.get(balaActual).disparar(direccion.x, direccion.y, this.getX(), this.getY());
		balaActual = (balaActual + 1) % numBalas;
		tiempoDisparo = cadencia;
	}

	private void actualizarAnimacion() {
        if (nivel.player.getX() > this.getX()) {
            this.setAnimation(arqueroDerecha);
        } else {
            this.setAnimation(arqueroIzquierda);
        }
    }
}
