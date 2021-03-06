package cam.ladybug.script;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ladybug.engine.components.BoxCollider;
import com.ladybug.engine.components.Collider;
import com.ladybug.engine.components.Rigidbody;
import com.ladybug.engine.components.Script;
import com.ladybug.engine.game.LayerManager;
import com.ladybug.engine.gameobject.GameObject;

public class PlayerScript extends Script {
	
	public static PlayerScript instance;

	float jumpForce = 3;
	ArrayList<FireScript> m_fsList;
	
	int m_countFire = 0;
	int m_delayFire = 40;

	public PlayerScript() {
		super();
		instance = this;
	}
	
	@Override
	public void awake(){
		getObject().addComponent(new Rigidbody());
		getObject().addComponent(new BoxCollider(48,48));
	}
	
	@Override
	public void start() {
		super.start();
		
	}
	

	@Override
	public void update(){
		m_countFire ++;
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			jump();
		}
		//input
		if(Gdx.input.justTouched() && m_countFire >= m_delayFire){
			//jump();
			m_fsList.get(getBullet()).shoot(getObject().getX()-16, getObject().getY()+20);
			m_countFire = 0;
		}
		
	}
	
	public void jump(){
		if(getObject().rigidbody.m_onGround){
			getObject().rigidbody.setAcceleration(-jumpForce);
			getObject().rigidbody.m_onGround = false;
		}
	}
	
	public void setBullets(ArrayList<FireScript> fs){
		m_fsList = fs;
	}
	
	public int getBullet(){
		for(int i = 0 ; i<m_fsList.size();i++){
			if(m_fsList.get(i).isReady())
				return i;
		}
		return 0;
	}
	
	public void killBullet(GameObject go){
		for(int i=0; i< m_fsList.size(); i++){
			if(m_fsList.get(i).getObject().equals(go)){
				m_fsList.get(i).kill();
			}
		}
	}
	
	@Override
	public void onCollisionEnter(Collider other){
		if(other.LAYER == LayerManager.GROUND){
			getRigidbody().m_onGround = true;
		}
	}

}
