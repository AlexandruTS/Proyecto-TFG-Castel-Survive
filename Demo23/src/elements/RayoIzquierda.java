package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.GameScreen;

public class RayoIzquierda extends Element {
	private GameScreen nivel;
	public int dano;
	public float velocidad;
	public float duracionBala;
	private float tiempoActiva;
	private boolean balaPlayer;
	private Animation<TextureRegion> animacion;
	public RayoIzquierda(float x, float y, Stage s, GameScreen nivel, boolean balaPlayer) {
		super(x, y, s);
		this.nivel = nivel;
		animacion = this.loadFullAnimation("player/Rayo_Izquierda.png", 1, 1, 1, false);
		this.setRectangle();
		this.setEnabled(false);
		this.duracionBala = 4;
		this.velocidad = 170;
		this.dano = 3;
		this.balaPlayer = balaPlayer;
	}

	public void act(float delta) {
		if (this.getEnabled()) {
			super.act(delta);
			this.applyPhysics(delta);
			if (this.tiempoActiva >= this.duracionBala) {
				this.setEnabled(false);
			} else {
				this.tiempoActiva += delta;
			}
			colide();

		}

	}

	private void colide() {

		if (balaPlayer) {
			for (Enemy e : this.nivel.enemigos) {
				if (e.getEnabled() && this.overlaps(e)) {
					e.dano(this.dano);
					this.setEnabled(false);

				}

			}
		} else {
			if (this.overlaps(nivel.player)) {
				nivel.player.dano(this.dano);
				this.setEnabled(false);

			}

		}
		for (Solid s : this.nivel.suelo) {
			if (this.overlaps(s)) {
				this.setEnabled(false);

			}
		}
	}

	public void disparar(float dirX, float dirY, float x, float y) {
		this.setEnabled(true);
		this.tiempoActiva = 0;
		this.setPosition(x, y);
		this.velocity.x = dirX * velocidad;
		this.velocity.y = dirY * velocidad;

	}

}
