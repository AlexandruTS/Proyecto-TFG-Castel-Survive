package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import screens.GameScreen;

public class QuintoJefe extends Enemy {
    private float rangoVision = 180;
    private Animation<TextureRegion> animacion;
    private GameScreen nivel;
    private boolean muerto = false;
    
    public QuintoJefe(float x, float y, Stage s, GameScreen nivel) {
        super(x, y, s, nivel);
        this.nivel = nivel;
        animacion = loadFullAnimation("npc/Vendedor.png", 4, 1, 0.15f, true);
        this.vida = 20;  
        this.dano = 1;  
        this.velocidad = 50; 
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getEnabled()) {
            float distancia = Vector2.dst(getX(), getY(), nivel.player.getX(), nivel.player.getY());
            if (distancia < rangoVision) {
                seguirJugador(delta);
            }
            if (colisionaConJugador()) {
                atacarJugador();
            }
            System.out.println(this.vida);
        }
        if (vida <= 0 && !muerto) {
            Parametros.puntuacion += 100000;
            Parametros.jefe--; 
            muerto = true;  
        }
    }

    private void seguirJugador(float delta) {
        float jugadorX = nivel.player.getX();
        float jugadorY = nivel.player.getY();

        Vector2 direccion = new Vector2(jugadorX - this.getX(), jugadorY - this.getY()).nor();
        moveBy(direccion.x * velocidad * delta, direccion.y * velocidad * delta);
    }
    
    private boolean colisionaConJugador() {
        float jugadorX = nivel.player.getX();
        float jugadorY = nivel.player.getY();
        float jugadorAncho = nivel.player.getWidth();
        float jugadorAlto = nivel.player.getHeight();

        float jefeX = this.getX();
        float jefeY = this.getY();
        float jefeAncho = this.getWidth();
        float jefeAlto = this.getHeight();

        return (jefeX < jugadorX + jugadorAncho &&
                jefeX + jefeAncho > jugadorX &&
                jefeY < jugadorY + jugadorAlto &&
                jefeY + jefeAlto > jugadorY);
    }

    private void atacarJugador() {
        Parametros.puntuacion = 0;
    }
}
