package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import screens.GameScreen;

public class SegundoJefe extends Enemy {

    private Array<Fuego> balas;
    private int numBalas = 5;
    private int balaActual = 0;
    private float cadencia = 1;
    private float tiempoDisparo = 0;
    private float rangoVision = 200;
    private Animation<TextureRegion> animacion;
    private boolean vulnerable = false; 
    private GameScreen nivel;

    public SegundoJefe(float x, float y, Stage s, GameScreen nivel) {
        super(x, y, s, nivel);
        this.nivel = nivel;
        animacion = loadFullAnimation("enemies/segundoJefe.png", 2, 1, 0.15f, true);

        balas = new Array<Fuego>();
        for (int i = 0; i < numBalas; i++) {
            balas.add(new Fuego(0, 0, s, nivel, false));
        }

        this.vida = 1;
        this.dano = 1;
        this.velocidad = 70;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getEnabled()) {
            float distancia = Vector2.dst(getX(), getY(), nivel.player.getX(), nivel.player.getY());
            if (distancia < rangoVision) {
                seguirJugador(delta);
                if (tiempoDisparo <= 0) {
                    disparar();
                } else {
                    tiempoDisparo -= delta;
                }
            }
        }
        morir();
    }

    private void seguirJugador(float delta) {
        float jugadorX = nivel.player.getX();
        float jugadorY = nivel.player.getY();

        Vector2 direccion = new Vector2(jugadorX - this.getX(), jugadorY - this.getY()).nor();
        moveBy(direccion.x * velocidad * delta, direccion.y * velocidad * delta);
    }

    private void disparar() {

        Vector2 direccion = new Vector2(nivel.player.getX() - this.getX(), nivel.player.getY() - this.getY()).nor();


        Fuego bala = balas.get(balaActual);
        bala.setPosition(this.getX(), this.getY()); 
        bala.disparar(direccion.x, direccion.y, this.getX(), this.getY());

        balaActual = (balaActual + 1) % numBalas;
        tiempoDisparo = cadencia;
    }

    @Override
    public void dano(int dano) {
        if (vulnerable) {
            this.vida -= dano;
            if (this.vida <= 0) {
                Parametros.jefe--;
                setPolygon(5, 0, 0, 0, 0);
                remove();
            }
        }
    }

    public void morir() {
        if (Parametros.matarSegundoJefe >= 1) {
            vulnerable = true;
            this.vida = 0;
            this.remove();
            Parametros.puntuacion += 2000;
            Parametros.jefe--;
        }
    }
}