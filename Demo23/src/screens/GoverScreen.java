package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.audio.Music;
import game.Demo;
import game.Parametros;
import managers.AudioManager;
import managers.ResourceManager;

public class GoverScreen extends BScreen{
	private Texture fondo;
	private SpriteBatch spritebatch;
	private Sprite sprite;
	
	private Table tabla;


	public GoverScreen(Demo game) {
		super(game);
		if(Parametros.jefe <= 0) {
			fondo = new Texture("maps/images/finalBueno.jpg");
		} else {
			fondo = new Texture("maps/images/finalMalo.jpg");
		}
		sprite = new Sprite(fondo);
		spritebatch = new SpriteBatch();
		Parametros.nivel=0;
		Parametros.jefe=5;
		tabla = new Table();
		tabla.setFillParent(true);

		this.uiStage.addActor(tabla);

		TextButton botonSalir = new TextButton("Menu", ResourceManager.textButtonStyle);
				botonSalir.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			game.setScreen(new TitleScreen(game, spritebatch));

			return false;
		});
		tabla.add(botonSalir);

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		spritebatch.begin();
		sprite.draw(spritebatch);
		spritebatch.end();

		uiStage.act();
		uiStage.draw();

	}

	public void dispose() {
		super.dispose();		
	}
}
