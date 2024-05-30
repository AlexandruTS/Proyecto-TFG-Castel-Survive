package elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import screens.GameScreen;

public class Gargola extends Enemy {

	private Array<Fuego> balas;
	private int numBalas = 5;
	private int balaActual = 0;
	private float cadencia = 1;
	private float tiempoDisparo = 0;
	private float rangoVision = 90;
	private boolean muerto = false;
	
	public Gargola(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s, nivel);
		loadFullAnimation("enemies/gargola/Gargola.png", 2, 1, 0.1f, true);
		balas = new Array<Fuego>();
		for (int i = 0; i < numBalas; i++) {
			balas.add(new Fuego(0, 0, s, nivel, false));
		}
		this.vida = 5;
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
			}
			if(vida<= 0 && !muerto) {
	        	Parametros.puntuacion += 50;
	        	muerto = true;
	        }

		}
	}

	private void disparar() {
		Vector2 direccion = new Vector2(nivel.player.getX() - this.getX(), nivel.player.getY() - this.getY()).nor();

		balas.get(balaActual).disparar(direccion.x, direccion.y, this.getX(), this.getY());
		balaActual = (balaActual + 1) % numBalas;
		tiempoDisparo = cadencia;
	}
}
