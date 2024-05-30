package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import screens.GameScreen;

public class TercerJefe extends Enemy {
    private Animation<TextureRegion> animacion;
    private Array<Fuego> balas;
    private float tiempoDisparo = 0;
    private int numBalas = 5;
    private float rangoVision = 290;
    private int balaActual = 0;
    private float cadencia = 2;
    private boolean muerto = false;
    
    public TercerJefe(float x, float y, Stage s, GameScreen nivel) {
        super(x, y, s, nivel);
        this.nivel = nivel;
        animacion = loadFullAnimation("enemies/tercerJefe.png", 2, 1, 0.15f, true);
        balas = new Array<Fuego>();
        for (int i = 0; i < numBalas; i++) {
            balas.add(new Fuego(0, 0, s, nivel, false));
        }
        this.vida = 1;  
        this.dano = 1;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        tiempoDisparo -= delta;
        if (getEnabled()) {
            float distancia = Vector2.dst(getX(), getY(), nivel.player.getX(), nivel.player.getY());
            if (distancia < rangoVision) {
                if (tiempoDisparo <= 0) {
                    disparar();
                }
            }
        }
        if(vida<= 0 && !muerto) {
        	Parametros.puntuacion += 3000;
        	Parametros.jefe--;
        	muerto = true;
        }
    }

    private void disparar() {
        Vector2 direccion = new Vector2(nivel.player.getX() - this.getX(), nivel.player.getY() - this.getY()).nor();

        balas.get(balaActual).disparar(direccion.x, direccion.y, this.getX(), this.getY());
        balaActual = (balaActual + 1) % numBalas;
        tiempoDisparo = cadencia;
    }
}