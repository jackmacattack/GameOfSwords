package edu.virginia.cs.sgd.game.view;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import edu.virginia.cs.sgd.game.model.components.MapPosition;
import edu.virginia.cs.sgd.game.model.components.TextureName;
import edu.virginia.cs.sgd.util.TextureRegionManager;


public class RenderSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<MapPosition> mapper;

	@Mapper
	ComponentMapper<TextureName> texMapper;
	
	private OrthogonalTiledMapRenderer renderer;
//	private OrthographicCamera camera;
	
	private TextureRegionManager texManager;
	
	@SuppressWarnings("unchecked")
	public RenderSystem(TiledMap map, int size, float scale) {
		
		
		
		super(Aspect.getAspectForAll(MapPosition.class, TextureName.class));
		
		this.renderer = new OrthogonalTiledMapRenderer(map, scale);	
		
//		manager = new SpriteManager(size, scale, renderer.getSpriteBatch());

		texManager = new TextureRegionManager("data/charactersheet.png", size, size);
		texManager.addRegion("swordsman", texManager.getTr()[0][0]);
		texManager.addRegion("spearman", texManager.getTr()[0][1]);
		texManager.addRegion("gunner", texManager.getTr()[0][2]);
		texManager.addRegion("cleric", texManager.getTr()[0][3]);
		texManager.addRegion("archer", texManager.getTr()[0][4]);
		texManager.addRegion("berserker", texManager.getTr()[0][5]);
		texManager.addRegion("sorc", texManager.getTr()[0][6]);
		texManager.addRegion("sample", texManager.getTr()[0][7]);
	}
	
	public void renderMap(OrthographicCamera camera) {
	    
		renderer.setView(camera);
		
	}
	
	@Override
	protected void begin() {
		super.begin();
		
		SpriteBatch batch = getSpriteBatch();

		renderer.render();
		batch.begin();
	}

	@Override
	protected void process(Entity e) {
		MapPosition pos = mapper.get(e);
		TextureName name = texMapper.get(e);
		TextureRegion tex = texManager.getRegion(name.getName());
		SpriteBatch batch = getSpriteBatch();
		float scale = renderer.getUnitScale();
		int width = texManager.getWidth();
		int height = texManager.getHeight();
		
		batch.draw(tex, (float) pos.getX() * width, (float) pos.getY() * height, 
				0, 0, width, height, scale, scale, 0);
	}
	
	@Override
	protected void end() {
		super.end();
		
		SpriteBatch batch = getSpriteBatch();
		
		batch.end();
	}

	public SpriteBatch getSpriteBatch() {
		return renderer.getSpriteBatch();
	}
	
}
