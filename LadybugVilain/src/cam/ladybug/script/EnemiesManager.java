package cam.ladybug.script;

import java.util.ArrayList;

import com.ladybug.engine.components.BoxCollider;
import com.ladybug.engine.components.Script;
import com.ladybug.engine.game.Scene;
import com.ladybug.engine.gameobject.GameObject;

public class EnemiesManager extends Script {
	
	//MARIOS
	ArrayList<Enemies> m_marios;
	int marioCount, marioDelay = 100;
	//LUIGIS
	ArrayList<Enemies> m_luigis;
	int luigiCount, luigiDelay = 100;
	
	Scene m_scene;
	
	public EnemiesManager(Scene scene){
		m_scene = scene;
		createMarios();
		createLuigis();
	}
	
	@Override
	public void update(){
		//MARIOS MANAGEMENT
		marioCount ++;
		if(marioCount > marioDelay){
			marioCount = 0;
			m_marios.get(getFreeEnemy(m_marios)).pop(0, 100);
		}
		
		//LUIGI MANAGEMENT
		luigiCount ++;
		if(luigiCount > luigiDelay){
			luigiCount = 0;
			m_luigis.get(getFreeEnemy(m_luigis)).pop(0, 100);
		}
	}
	
	private void createMarios(){
		m_marios = new ArrayList<Enemies>();
		for(int i=0; i<10; i++){
			//MARIOS
			GameObject mario = new GameObject(-1000,100,"data/mario.png",24,24,"Mario");
			mario.addComponent(new BoxCollider(24,24));
			Mario e = new Mario();
			mario.addComponent(e);
			m_marios.add(e);
			m_scene.addObject(mario);
			e.setDead(true);
		}
	}
	
	private void createLuigis(){
		m_luigis = new ArrayList<Enemies>();
		for(int i=0; i<10; i++){
			//MARIOS
			GameObject luigi = new GameObject(-1000,100,"data/luigi.png",24,24,"Luigi");
			luigi.addComponent(new BoxCollider(24,24));
			Luigi e = new Luigi();
			luigi.addComponent(e);
			m_luigis.add(e);
			m_scene.addObject(luigi);
			e.setDead(true);
		}
	}
	
	private int getFreeEnemy(ArrayList<Enemies> e){
		for(int i=0 ; i < e.size(); i++){
			if(e.get(i).isDead())
				return i;
		}
		return 0;
	}
}
