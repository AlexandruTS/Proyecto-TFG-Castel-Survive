package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import game.Demo;
import game.Parametros;
import managers.ResourceManager;

public class TitleScreen extends BScreen {
    private Texture backgroundTexture;
    private Table table;
    private SpriteBatch batch;

    public TitleScreen(Demo game, SpriteBatch batch) {
        super(game);
        this.batch = batch;

        table = new Table();
        table.setFillParent(true);
        this.uiStage.addActor(table);

        TextButton playButton = new TextButton("Jugar", ResourceManager.textButtonStyle);
        playButton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
                return false;
            this.dispose();
            game.setScreen(new GameScreen(game));
            return false;
        });
        table.add(playButton).row();

        TextButton optionsButton = new TextButton("Controles", ResourceManager.textButtonStyle);
        optionsButton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
                return false;
            game.setScreen(new OptionsScreen(game, batch));
            return false;
        });
        table.add(optionsButton).row();

        TextButton exitButton = new TextButton("Salir", ResourceManager.textButtonStyle);
        exitButton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
                return false;
            this.dispose();
            Gdx.app.exit();
            return false;
        });
        table.add(exitButton).row();

        backgroundTexture = new Texture(Gdx.files.internal("maps/images/fondoMenu.png"));
        TextureRegion backgroundRegion = new TextureRegion(backgroundTexture);
        TextureRegionDrawable backgroundDrawable = new TextureRegionDrawable(backgroundRegion);
        table.setBackground(backgroundDrawable);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        uiStage.act();
        uiStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
    }
}
