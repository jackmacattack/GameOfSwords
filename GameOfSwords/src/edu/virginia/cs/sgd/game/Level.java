package edu.virginia.cs.sgd.game;

import java.util.ArrayList;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

import edu.virginia.cs.sgd.GameOfSwords;
import edu.virginia.cs.sgd.game.model.EntityFactory;
import edu.virginia.cs.sgd.game.model.PositionManager;
import edu.virginia.cs.sgd.game.model.components.Damage;
import edu.virginia.cs.sgd.game.model.components.MapPosition;
import edu.virginia.cs.sgd.game.model.components.Stats;
import edu.virginia.cs.sgd.game.model.systems.DamageSystem;
import edu.virginia.cs.sgd.game.view.SpriteMaker;

public class Level {

	private World world;
	private TiledMap m_Map;

	private int selectedId;
	
	private ArrayList<SpriteMaker> addList;
	private ArrayList<Integer> removeList;

	private DamageSystem damageSystem;
	
	public Level() {

		m_Map = GameOfSwords.getManager().get("data/sample_map.tmx");
		
		addList = new ArrayList<SpriteMaker>();
		removeList = new ArrayList<Integer>();

		initialize_world();
		
		Entity e = world.createEntity();
		e.addComponent(new MapPosition(1,3));
		e.addToWorld();
		
		world.process();
		
		addList.add(new SpriteMaker(e.getId(), "sample"));
		
		selectedId = -1;
		
//		testDamage();
	}

	public TiledMap getMap() {
		// TODO Auto-generated method stub
		return m_Map;
	}

	public Vector2 getPosition(int modelId) {
		// TODO Auto-generated method stub
		Entity e = world.getEntity(modelId);
		MapPosition m = e.getComponent(MapPosition.class);
		
		return new Vector2(m.getX(), m.getY());
	}
	
	public ArrayList<SpriteMaker> getAddList() {
		return addList;
	}
	
	public ArrayList<Integer> getRemoveList() {
		return removeList;
	}

	public void clearSpriteUpdates() {
		addList.clear();
		removeList.clear();
	}
	
	public void testDamage() {

		int[][] actorMap = new int[5][5];
		initialize_world();
		Entity e = EntityFactory.createActor(world,0,0, actorMap);
		
		System.out.println(actorMap[0][0]);
		
		Stats s = e.getComponent(Stats.class);
		
		System.out.println(s);
		
		s.setDefense(s.getDefense()+1);
		
		System.out.println(s.getDefense());
		s = e.getComponent(Stats.class);
		System.out.println(s.getDefense());
		
		
		MapPosition m = e.getComponent(MapPosition.class);
		e.addComponent(new Damage(30));
		System.out.println(m);

	}

	public void update() {

		processSystems();
		
	}

	private int getMapWidth() {
		// TODO Auto-generated method stub
		MapProperties prop = m_Map.getProperties();
		int mapWidth = prop.get("width", Integer.class);
		return mapWidth;
	}
	
	private int getMapHeight() {
		// TODO Auto-generated method stub
		MapProperties prop = m_Map.getProperties();
		int mapHeight = prop.get("height", Integer.class);
		return mapHeight;
	}
	
	private void initialize_world() {
		world = new World();
		//damageSystem = world.setSystem(new DamageSystem(), true);
		
		PositionManager pos = new PositionManager();
		pos.setWorld(getMapWidth(), getMapHeight());
		world.setManager(pos);
		world.initialize();
		System.out.println("The world is initialized");

	}

	public void dispose() {

//		world.deleteSystem(damageSystem);
	}

	public void processSystems()
	{
		//System.out.println("process");
//		damageSystem.process();
	}
	
    public void addComponent(Component component, int entityId)
    {
        if (entityId < 0) return;
        Entity e = world.getEntity(entityId);
        e.addComponent(component);
        e.changedInWorld();
    }
    
    public Entity getEntityAt(int x, int y) {
    	PositionManager pos = world.getManager(PositionManager.class);

    	int id = pos.getEntityAt(x, y);
    	
    	if(id == -1) {
    		return null;
    	}
    	
    	return world.getEntity(id);
    }

    public void select(int x, int y) {
    	Entity e = getEntityAt(x, y);
    	System.out.println(e);
    	if(e == null) {
    		if(selectedId != -1) {
    			e = world.getEntity(selectedId);
    			
    			MapPosition m = e.getComponent(MapPosition.class);
    			
    			m.setX(x);
    			m.setY(y);
    		}
    	}
    	else {
    		selectedId = e.getId();
    	}
    }
}
