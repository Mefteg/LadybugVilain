package cam.ladybug.script;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ladybug.engine.components.Collider;
import com.ladybug.engine.components.Script;

public class ObstacleScript extends Script {

	public ObstacleScript() {
		super();
		
		Vector2 u1 = new Vector2(0, -1);
		Vector2 u2 = new Vector2(0, 1);
		Vector2 v1 = new Vector2(-1, 1);
		Vector2 v2 = new Vector2(1, 0);
		
		Vector2 intersect = this.intersectPos(u1, u2, v1, v2);
		
		if ( intersect != null ) {
			System.out.println(intersect.toString());
		}
		else {
			System.out.println("null");
		}
	}

	@Override
	public void start() {
		super.start();
	}
	
	@Override
	public void onCollisionEnter(Collider _collider) {
	}
	
	// retourne la position de l'intersection si deux vecteurs se croisent
	// null sinon
	public Vector2 intersectPos(Vector2 _u1, Vector2 _u2, Vector2 _v1, Vector2 _v2) {
		Vector2 intersectPos = null;
		
		// première droite
		Vector2 d1 = new Vector2();
		float d1x = 0;
		if ( (_u2.x - _u1.x) != 0 ) {
			d1x = (_u2.y - _u1.y) / (_u2.x - _u1.x);
		}
		float d1y = _u1.y - (d1.x * _u1.x);
		d1.set(d1x, d1y);
		
		// deuxieme droite
		Vector2 d2 = new Vector2();
		float d2x = 0;
		if ( (_v2.x - _v1.x) != 0 ) {
			d2x = (_v2.y - _v1.y) / (_v2.x - _v1.x);
		}
		float d2y = _v1.y - (d2.x * _v1.x);
		d2.set(d2x, d2y);
		
		// si les droites se croisent
		if ( d1.x != d2.x ) {
			intersectPos = new Vector2();
			float x = (d2.y - d1.y) / (d1.x - d2.x);
			float y = d1.x * intersectPos.x + d1.y;
			intersectPos.set(x, y);
		}
		
		return intersectPos;
	}
}
