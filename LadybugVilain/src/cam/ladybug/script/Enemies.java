package cam.ladybug.script;

import com.badlogic.gdx.math.Vector2;
import com.ladybug.engine.components.Collider;
import com.ladybug.engine.components.Rigidbody;
import com.ladybug.engine.components.Script;
import com.ladybug.engine.game.LayerManager;
import com.ladybug.vilain.GlobalVilain;

public class Enemies extends Script {

	protected float jumpForce = 3;
	protected boolean touched = false;
	protected boolean dead = true;
	
	protected float m_velocity = 0.5f;
	
	@Override 
	public void awake(){
		m_object.addComponent(new Rigidbody());
	}
	
	@Override
	public void update(){
		if(touched && getObject().getY() < 0){
			die();
		}
	}
	
	public void pop(float _x, float _y){
		getObject().setPosition(_x, _y);
		getRenderer().setFrame(0,0);
		getRigidbody().setVelocity(new Vector2(m_velocity,0.0f));	
		getCollider().enabled = true;
		touched = false;
		dead = false;
	}
	
	public void die(){
		GlobalVilain.SCORE += 10;
		getRigidbody().setVelocity(new Vector2());	
		touched = false;
		dead = true;
		getObject().setPosition(-1000, -1000);
	}
	
	public void setDead(boolean a_dead){
		dead = a_dead;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void jump(){
		if(getObject().rigidbody.m_onGround){
			getObject().rigidbody.setAcceleration(-jumpForce);
			getObject().rigidbody.m_onGround = false;
		}
	}
		
	@Override
	public void OnCollisionEnter(Collider other){
		if(other.LAYER == LayerManager.GROUND ){
			getRigidbody().m_onGround = true;
			jump();
		}
		if(other.LAYER == LayerManager.PLAYER){
			getRenderer().setFrame(1, 0);
			getRigidbody().setVelocity(new Vector2());
			getObject().rigidbody.setAcceleration(-1);
			getCollider().enabled = false;
			touched = true;
			PlayerScript.instance.killBullet(other.getObject());
		}
	}

}
