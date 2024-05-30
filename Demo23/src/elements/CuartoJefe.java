package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import screens.GameScreen;

public class CuartoJefe extends Enemy {
    private Animation<TextureRegion> animacion;
    private Array<Fuego> balas;
    private float tiempoDisparo = 0;
    private int numBalas = 100;
    private int balaActual = 0;
    private float cadencia = 0.5f;
    private boolean muerto = false;
    
    public CuartoJefe(float x, float y, Stage s, GameScreen nivel) {
        super(x, y, s, nivel);
        this.nivel = nivel;
        animacion = loadFullAnimation("enemies/cuartoJefe.png", 3, 1, 0.15f, true);
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
            if (tiempoDisparo <= 0) {
                disparar();
            }
        }
        if(vida<= 0 && !muerto) {
        	Parametros.puntuacion += 4000;
        	Parametros.jefe--;
        	muerto = true;
        }
    }

    private void disparar() {

        float direccionX = 1;
        float direccionY = 0;

        balas.get(balaActual).disparar(direccionX, direccionY, this.getX(), this.getY());
        balaActual = (balaActual + 1) % numBalas;
        tiempoDisparo = cadencia;
    }
}