package com.ladybug.vilain;

import java.util.ArrayList;

import cam.ladybug.script.Enemies;
import cam.ladybug.script.FireScript;
import cam.ladybug.script.PlayerScript;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ladybug.engine.components.BoxCollider;
import com.ladybug.engine.game.Game2D;
import com.ladybug.engine.game.LayerManager;
import com.ladybug.engine.game.Scene;
import com.ladybug.engine.gameobject.GameObject;

public class LadybugVilain extends Game2D {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
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
			fire.addComponent(new BoxCollider(32,16));
			bullets.add(fs);
			scene.addObject(fire);
		}
		ps.setBullets(bullets);
		go.addComponent(ps);
		scene.addObject(go);
		
		//MARIOS
		GameObject mario = new GameObject(0,100,"data/mario.png",24,24,"Mario");
		mario.addComponent(new BoxCollider(24,24));
		mario.addComponent(new Enemies());
		scene.addObject(mario);
	}
}
