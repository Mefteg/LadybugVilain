package cam.ladybug.script;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.ladybug.engine.components.BoxCollider;
import com.ladybug.engine.components.Collider;
import com.ladybug.engine.components.Rigidbody;
import com.ladybug.engine.components.Script;
import com.ladybug.engine.game.LayerManager;
import com.ladybug.engine.gameobject.GameObject;

public class PlayerScript extends Script {

	float jumpForce = 3;
	ArrayList<FireScript> m_fsList;

	public PlayerScript() {
		super();
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
		//input
		if(Gdx.input.justTouched()){
			//jump();
			m_fsList.get(getBullet()).shoot(getObject().getX()-16, getObject().getY()+20);
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
	
	@Override
	public void OnCollisionEnter(Collider other){
		if(other.LAYER == LayerManager.GROUND){
			getRigidbody().m_onGround = true;
		}
	}

}