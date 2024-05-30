package screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import game.Demo;
import managers.ResourceManager;

public class BScreen implements Screen, InputProcessor{
	final Demo game;
	public ResourceManager resourceManager;
	public Stage actualStage;
	public LabelStyle uiStyle;
	public LabelStyle dialogStyle;
	
	InputMultiplexer im;
	
	
	
	Stage uiStage;
	public BScreen(Demo game){
		
		this.uiStage=new Stage();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("sans.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 10;
		BitmapFont customFont = generator.generateFont(parameter);
		generator.dispose();
		customFont.getData().setScale(3);

		uiStyle=new LabelStyle(customFont,Color.BLACK);
		BitmapFont dialogFont=new BitmapFont();
		dialogStyle=new LabelStyle(dialogFont, Color.RED);
		this.game=game; 
		this.resourceManager=game.resourceManager;
		
		
		 
	
		
	
		
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		im=(InputMultiplexer)Gdx.input.getInputProcessor();
		im.addProcessor(this);
		im.addProcessor(this.uiStage);
		
	}

	public void render(float delta) {
	    Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    if (actualStage != null) {
	        actualStage.act(delta);
	        actualStage.draw();
	    }
	    if (uiStage != null) {
	        uiStage.act(delta);
	        uiStage.draw();
	    }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		InputMultiplexer im=(InputMultiplexer)Gdx.input.getInputProcessor();
		im.removeProcessor(this);
		im.removeProcessor(this.uiStage);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
