package edu.virginia.cs.sgd.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import edu.virginia.cs.sgd.GameOfSwords;
import edu.virginia.cs.sgd.controller.Controller;
import edu.virginia.cs.sgd.game.Level;
import edu.virginia.cs.sgd.game.view.LevelRenderer;


public class MapScreen extends AbstractScreen {

	private LevelRenderer renderer;
	private Level level;
	private Menu m;
	private Controller c;
	
	public MapScreen(Menu m, GameOfSwords game) {
		super();
		this.m = m;
		
		GameOfSwords.getManager().load("data/samplesprite.png", Texture.class);
		GameOfSwords.getManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		GameOfSwords.getManager().load("data/sample_map.tmx", TiledMap.class);
		Texture.setEnforcePotImages(false);
		GameOfSwords.getManager().finishLoading();

		level = new Level();
		renderer = new LevelRenderer(level);

		c = new Controller(this, level);
		//c.run();

	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderer.render();

		level.update();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		renderer.resize(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Texture.setEnforcePotImages(false);

		renderer.show();
	}

	public void dispose() {
		level.dispose();
	}

	@Override
	public void keyDown(int keyCode) {
		
	}

	@Override
	public void keyUp(int keyCode) {
		
	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
//		Point coords = renderer.getCoord(screenX, screenY);
		
		//System.out.println(coords.x + ", " + coords.y);
		
		//level.touchDown(coords, pointer, button);
	}

	@Override
	public void touchUp(int screenX, int screenY, int pointer, int button, boolean dragging) {
		
		if(dragging) {
			return;
		}
		
		Vector2 coords = renderer.getCoord(screenX, screenY);
		
		if(button == Buttons.LEFT) {
			level.select((int)coords.x, (int)coords.y);
		}
	}

	@Override
	public void scrolled(int amount) {
		renderer.zoomMap(amount == 1);
	}
	
	@Override
	public void touchDragged(int screenX, int screenY, int pointer, int deltaX, int deltaY) {
		renderer.moveMap(deltaX, deltaY);
	}
}
