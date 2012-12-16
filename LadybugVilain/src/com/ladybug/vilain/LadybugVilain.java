package com.ladybug.vilain;

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
		
		this.WIDTH = 340;
		this.HEIGHT = 200;
		
		Scene scene = new Scene();
		scene.WIDTH = 340;
		scene.HEIGHT = 200;
		
		this.addScene(scene);
		
		//background image
		GameObject bg = new GameObject(0,0,"data/bg.png",340,200,"BG");
		scene.addObject(bg);
		
		
		GameObject go = new GameObject(0,0,"data/smb_enemies_sheet.png",32,32,"Player");
		go.addComponent(new PlayerScript());
		scene.addObject(go);
	}
}
