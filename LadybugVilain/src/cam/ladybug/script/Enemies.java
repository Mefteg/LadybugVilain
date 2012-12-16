package cam.ladybug.script;

import com.badlogic.gdx.math.Vector2;
import com.ladybug.engine.components.Collider;
import com.ladybug.engine.components.Rigidbody;
import com.ladybug.engine.components.Script;
import com.ladybug.engine.game.LayerManager;

public class Enemies extends Script {

	float jumpForce = 3;
	boolean touched = false;
	
	@Override 
	public void awake(){
		m_object.addComponent(new Rigidbody());
		getRenderer().setFrame(0,0);
		getRigidbody().setVelocity(new Vector2(0.5f,0.0f));
	}
	
	public void pop(float _x, float _y){
		
	}
	
	public void jump(){
		if(getObject().rigidbody.m_onGround){
			getObject().rigidbody.setAcceleration(-jumpForce);
			getObject().rigidbody.m_onGround = false;
		}
	}
		
	@Override
	public void OnCollisionEnter(Collider other){
		if(other.LAYER == LayerManager.GROUND && !touched){
			getRigidbody().m_onGround = true;
			jump();
		}
		if(other.LAYER == LayerManager.PLAYER){
			getRenderer().setFrame(1, 0);
			getRigidbody().setVelocity(new Vector2());
			getObject().rigidbody.setAcceleration(-1);
			getCollider().enabled = false;
			touched = true;
		}
	}

}
