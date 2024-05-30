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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import game.Demo;
import managers.ResourceManager;

public class OptionsScreen extends BScreen {
    private Texture imageTexture;
    private Table table;
    private SpriteBatch batch;

    public OptionsScreen(Demo game, SpriteBatch batch) {
        super(game);
        this.batch = batch;

        table = new Table();
        table.setFillParent(true);
        this.uiStage.addActor(table);

        imageTexture = new Texture(Gdx.files.internal("maps/images/fondo.jpg"));
        TextureRegion imageRegion = new TextureRegion(imageTexture);
        Drawable imageDrawable = new TextureRegionDrawable(imageRegion);

        // Display image
        table.setBackground(imageDrawable);

        // Back button
        TextButton backButton = new TextButton("Volver", ResourceManager.textButtonStyle);
        backButton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
                return false;
            game.setScreen(new TitleScreen(game, batch));
            return false;
        });

        table.add(backButton).expandY().bottom().left().pad(18);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        uiStage.act();
        uiStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        imageTexture.dispose();
    }
}
