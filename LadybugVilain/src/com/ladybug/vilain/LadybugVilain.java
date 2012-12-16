package com.ladybug.vilain;

import java.awt.Color;
import java.util.ArrayList;

import cam.ladybug.script.Enemies;
import cam.ladybug.script.EnemiesManager;
import cam.ladybug.script.FireScript;
import cam.ladybug.script.PlayerScript;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.esotericsoftware.tablelayout.Cell;
import com.ladybug.engine.components.BoxCollider;
import com.ladybug.engine.game.Game2D;
import com.ladybug.engine.game.Global;
import com.ladybug.engine.game.LayerManager;
import com.ladybug.engine.game.Scene;
import com.ladybug.engine.gameobject.GameObject;

public class LadybugVilain extends Game2D {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	protected Stage m_stage;
	protected Table m_table;
	protected Label m_score;
	protected Cell m_scoreCell;
	
	public LadybugVilain() {
		super();
		
		this.WIDTH = 340;
		this.HEIGHT = 200;
		
		Scene scene = new Scene();
		scene.WIDTH = 340;
		scene.HEIGHT = 200;
		
		this.addScene(scene);
		
		//background image
		GameObject bg = new GameObject(0,0,"data/bg.png",340,200,"BG");
		scene.addObject(bg);
		//collider on ground
		GameObject ground = new GameObject(0,20,"Ground");
		ground.addComponent(new BoxCollider(400,48));
		ground.collider.LAYER = LayerManager.GROUND;
		scene.addObject(ground);
		
		
		GameObject go = new GameObject(240,100,"data/bowser.png",42,42,"Player");
		PlayerScript ps = new PlayerScript();
		
		ArrayList<FireScript> bullets = new ArrayList<FireScript>();
		//create fire bullets
		for(int i=0; i < 4; i++){
			GameObject fire = new GameObject(0,0,"data/fire.png",32,16,"FireBullet");
			FireScript fs = new FireScript();
			fire.addComponent(fs);
			fire.addComponent(new BoxCollider(24,10));
			bullets.add(fs);
			scene.addObject(fire);
		}
		ps.setBullets(bullets);
		go.addComponent(ps);
		scene.addObject(go);
		
		GameObject manager = new GameObject();
		manager.addComponent(new EnemiesManager(scene));
		scene.addObject(manager);
	}
	
	@Override
	public void create() {
		super.create();
		
		Scene currentScene = Global.currentScene;
		
		m_stage = new Stage(LadybugVilain.WIDTH, LadybugVilain.HEIGHT, true, Scene.batch);
		Gdx.input.setInputProcessor(m_stage);
		m_table = new Table();
		m_table.setFillParent(true);
		m_table.setPosition(0, 0);
		m_table.pack();
		m_stage.addActor(m_table);
		LabelStyle style = new LabelStyle(new BitmapFont(), com.badlogic.gdx.graphics.Color.WHITE);
		
		m_score = new Label("Score", style);
		
		m_scoreCell = m_table.add(m_score);
		m_scoreCell.expandX();
		m_scoreCell.expandY();
		m_scoreCell.right();
		m_scoreCell.top();
		
		currentScene.addStage(m_stage);
	}
	
	@Override
	public void render() {
		update();
		super.render();
		Table.drawDebug(m_stage);
	}
	
	public void update() {
		m_score.setText("Score : " + String.valueOf(10));
		m_table.debug();
	}
}
