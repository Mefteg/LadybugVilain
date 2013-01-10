package cam.ladybug.script;

import com.badlogic.gdx.math.Vector2;
import com.ladybug.engine.components.Collider;
import com.ladybug.engine.game.LayerManager;

public class SuperMario extends Enemies {
	
	
	
	@Override
	public void onCollisionEnter(Collider other){
		if(other.LAYER == LayerManager.GROUND ){
			getRigidbody().m_onGround = true;
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


