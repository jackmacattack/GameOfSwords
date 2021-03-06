package edu.virginia.cs.sgd.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class MenuScreen extends AbstractScreen {

	private Table table;
	
	public MenuScreen() {
		super();
	}
	
	@Override
	public void show() {
		super.show();
		
        table = new Table(skin);

		table.setFillParent(true);
		stage.addActor(table);
        
        table.add( "Welcome to AetherForge" ).spaceBottom( 50 );
        table.row();
        
        //start game
        TextButton startGameButton = new TextButton( "Start game", skin);
        startGameButton.addListener( new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
            	changeScreen(MapScreen.class);
            }
        } );
        
        table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();

        // register the button "options"
        TextButton creditsButton = new TextButton( "Credits", skin);

        creditsButton.addListener( new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
            	changeScreen(CreditsScreen.class);
            }
        } );
        
        table.add( creditsButton ).uniform().fill().spaceBottom( 10 );
        table.row();
	}

	@Override
	public void touchDown(int screenX, int screenY, int pointer, int button) {
		stage.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public void touchUp(int screenX, int screenY, int pointer, int button, boolean dragging) {
		stage.touchUp(screenX, screenY, pointer, button);
	}
}
