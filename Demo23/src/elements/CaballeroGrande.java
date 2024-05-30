package elements;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import screens.GameScreen;

public class CaballeroGrande extends Enemy {

    private Animation<TextureRegion> walkDerecha;
    private Animation<TextureRegion> walkIzquierda;
    private Element pie;
    private Element cabeza;
    private int direccion;
    private boolean pisa, choca;
    private boolean muerto = false;
    
    public CaballeroGrande(float x, float y, Stage s, GameScreen nivel) {
    	super(x, y, s, nivel);
        walkDerecha = loadFullAnimation("enemies/caballeroGrande/caballeroGrandeDerecha.png", 3, 1, 0.15f, true);
        walkIzquierda = loadFullAnimation("enemies/caballeroGrande/caballeroGrandeIzquierda.png", 3, 1, 0.15f, true);
        pie = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 4);
        cabeza = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 4);
        pie.setRectangle();
        cabeza.setRectangle();
        ajustarPie();
        ajustarCabeza();
        this.setRectangle(this.getWidth() * 2 / 3, this.getHeight(), this.getWidth() * 1 / 6, 0);
        this.vida = 5;
        this.direccion = -1;
        this.velocidad = 30;
        this.dano = 2;
    }

    public void act(float delta) {
        super.act(delta);
        comprobarColisiones();
        moveBy(direccion * velocidad * delta, 0);
        ajustarPie();
        ajustarCabeza();
        actualizarAnimacion();
        if(vida<= 0 && !muerto) {
        	Parametros.puntuacion += 200;
        	muerto = true;
        }
    }

    private void ajustarPie() {
        if (direccion == -1) {
            pie.setPosition(this.getX(), this.getY() - this.getHeight() / 8);
        } else {
            pie.setPosition(this.getX() + this.getWidth() * 3 / 4, this.getY() - this.getHeight() / 8);
        }
    }

    private void ajustarCabeza() {
        if (direccion == -1) {
            cabeza.setPosition(this.getX() - this.getWidth() / 8, this.getY() + this.getHeight() * 5 / 8);
        } else {
            cabeza.setPosition(this.getX() + this.getWidth() * 7 / 8, this.getY() + this.getHeight() * 5 / 8);
        }
    }

    private void comprobarColisiones() {
        pisa = false;
        choca = false;
        for (Solid solido : this.nivel.suelo) {
            if (pie.overlaps(solido)) {
                pisa = true;
            }
            if (cabeza.overlaps(solido)) {
                choca = true;
            }
        }
        if (!pisa || choca) {
            direccion *= -1;
        }
    }

    private void actualizarAnimacion() {
        if (direccion == 1) {
            this.setAnimation(walkDerecha);
        } else if (direccion == -1) {
            this.setAnimation(walkIzquierda);
        }
    }
}
