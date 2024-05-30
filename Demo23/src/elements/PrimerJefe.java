package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import game.Parametros;
import screens.GameScreen;
public class PrimerJefe extends Enemy {
	
	private Array<Fuego> balas;
	private int numBalas = 5;
	private int balaActual = 0;
	private float cadencia = 3;
	private float tiempoDisparo = 0;
	private float rangoVision = 90;
    private Animation<TextureRegion> animacion;
    private Element pie;
    private Element cabeza;
    private Element espalda;
    private int direccion;
    private GameScreen nivel;
    private boolean muerto = false;
    
    public PrimerJefe(float x, float y, Stage s, GameScreen nivel) {
        super(x, y, s, nivel);
        this.nivel = nivel;
        animacion = loadFullAnimation("enemies/primerJefe.png", 3, 1, 0.15f, true);
        pie = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 4);
        cabeza = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 4);
        espalda = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 4);
        balas = new Array<Fuego>();
		for (int i = 0; i < numBalas; i++) {
			balas.add(new Fuego(0, 0, s, nivel, false));
		}
        pie.setRectangle();
        cabeza.setRectangle();
        espalda.setRectangle();
        ajustarEspalda();
        this.setRectangle(this.getWidth() * 2 / 3, this.getHeight(), this.getWidth() * 1 / 6, 0);
        this.vida = 1;
        this.dano = 1;
    }

    public void act(float delta) {
        super.act(delta);
        ajustarEspalda();
        tiempoDisparo -= delta;
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
        }
        if(vida<= 0 && !muerto) {
        	Parametros.puntuacion += 1000;
        	Parametros.jefe--;
        	muerto = true;
        }
    }

    private void ajustarEspalda() {
        if (direccion == -1) {
            espalda.setPosition(this.getX() - this.getWidth() / 8, this.getY() + this.getHeight() / 2);
        } else {
            espalda.setPosition(this.getX() + this.getWidth() * 7 / 8, this.getY() + this.getHeight() / 2);
        }
    }


    private void disparar() {
		Vector2 direccion = new Vector2(nivel.player.getX() - this.getX(), nivel.player.getY() - this.getY());

		balas.get(balaActual).disparar(direccion.x, direccion.y, this.getX(), this.getY());
		balaActual = (balaActual + 1) % numBalas;
		tiempoDisparo = cadencia;
	}
}