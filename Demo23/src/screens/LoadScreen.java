package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.Demo;
import managers.ResourceManager;

public class LoadScreen extends BScreen{
	private float loadDelay=5;
	private float loadCount=0;
	private Texture backgroundTexture;

	private SpriteBatch batch;
	
	public LoadScreen(Demo game, SpriteBatch batch){
		
	super(game);
	this.batch=batch;
	
	ResourceManager.loadAllResources();
	
	backgroundTexture=new Texture("maps/images/pantallaDeCarga.jpg");
	
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		loadCount += delta;
		
		if(loadCount >= loadDelay && ResourceManager.update()) {
			ResourceManager.botones();
			game.setScreen(new TitleScreen(game, batch));
			
		}
		
		
		
	}
	
	
	
}
