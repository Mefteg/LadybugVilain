package com.ladybug.vilain;

import cam.ladybug.script.ObstacleScript;
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
import com.ladybug.engine.components.Collider;
import com.ladybug.engine.components.Rigidbody;
import com.ladybug.engine.game.Game2D;
import com.ladybug.engine.game.Scene;
import com.ladybug.engine.gameobject.GameObject;

public class LadybugVilain extends Game2D {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	public LadybugVilain() {
		super();
		
		Scene scene = new Scene();
		
		this.addScene(scene);
		
		GameObject go = new GameObject("Player");
		go.addComponent(new Rigidbody());
		go.addComponent(new BoxCollider(32, 32));
		go.addComponent(new PlayerScript());
		go.setPosition(30, 60);
		scene.addObject(go);
		
		GameObject bridge = new GameObject("Player");
		bridge.addComponent(new Rigidbody());
		bridge.addComponent(new BoxCollider(128, 32));
		bridge.addComponent(new ObstacleScript());
		bridge.setPosition(0, 0);
		scene.addObject(bridge);
	}
}
