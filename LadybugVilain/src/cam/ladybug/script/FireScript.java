package cam.ladybug.script;

import com.badlogic.gdx.math.Vector2;
import com.ladybug.engine.components.Rigidbody;
import com.ladybug.engine.components.Script;
import com.ladybug.engine.game.LayerManager;

public class FireScript extends Script {
	
	boolean ready = true;
	
	@Override
	public void awake(){
		getObject().addComponent(new Rigidbody());
		getRigidbody().gravity = false;
		getCollider().LAYER = LayerManager.PLAYER;
	}
	
	public void shoot(float _x, float _y){
		getObject().setPosition(_x, _y);
		getRigidbody().setVelocity(new Vector2(-2.5f,0.0f));
		ready = false;
	}
	
	public boolean isReady(){
		return ready;
	}
	
	@Override
	public void update(){
		if(this.getObject().getX() < -10)
			ready = true;
	}

}
