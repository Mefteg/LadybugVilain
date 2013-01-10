package cam.ladybug.script;

import java.util.ArrayList;
import java.util.Random;

import com.ladybug.engine.components.BoxCollider;
import com.ladybug.engine.components.Script;
import com.ladybug.engine.game.Scene;
import com.ladybug.engine.gameobject.GameObject;

public class EnemiesManager extends Script {
	
	//ENEMIES
	ArrayList<Enemies> m_enemies;
	int enemyCount, enemyDelay;
	int nextDelay;
	
	protected int count = 0;
	protected int firstApp = 120;
	
	protected PopType m_popType;
	
	Scene m_scene;
	
	public EnemiesManager(Scene scene, PopType enemyType,int first,int delay){
		m_scene = scene;
		m_popType = enemyType;
		firstApp = first;
		enemyDelay = delay;
		computeNextDelay();
		createEnemies();
	}
	
	private void computeNextDelay(){
		nextDelay = (int) ( enemyDelay * ( 0.8 + (Math.random() * ((1.2 - 0.8) + 1))));
	}
	
	@Override
	public void update(){
		count++;		
		//LUIGI MANAGEMENT
		if(count > firstApp){
			enemyCount ++;
			if(enemyCount > nextDelay){
				computeNextDelay();
				enemyCount = 0;
				m_enemies.get(getFreeEnemy()).pop(-30, 100);
			}
		}
		
		if(count == 200){
			enemyDelay -= 20;
		}
	}
	
	private void createEnemies(){
		m_enemies = new ArrayList<Enemies>();
		switch(m_popType){
			case MARIO : createMarios(); break;
			case LUIGI : createLuigis(); break;
		}
	}
	
	private void createMarios(){
		for(int i=0; i<10; i++){
			//MARIOS
			GameObject mario = new GameObject(-1000,100,"data/mario.png",24,24,"Mario");
			mario.addComponent(new BoxCollider(20,20));
			Mario e = new Mario();
			mario.addComponent(e);
			m_enemies.add(e);
			m_scene.addObject(mario);
			e.setDead(true);
		}
	}
	
	private void createLuigis(){
		for(int i=0; i<10; i++){
			//MARIOS
			GameObject luigi = new GameObject(-1000,100,"data/luigi.png",24,24,"Luigi");
			luigi.addComponent(new BoxCollider(20,20));
			Luigi e = new Luigi();
			luigi.addComponent(e);
			m_enemies.add(e);
			m_scene.addObject(luigi);
			e.setDead(true);
		}
	}
		
	private int getFreeEnemy(){
		for(int i=0 ; i < m_enemies.size(); i++){
			if(m_enemies.get(i).isDead())
				return i;
		}
		return 0;
	}
	
	public enum PopType{
		MARIO,LUIGI,SUPERMARIO,SUPERLUIGI
	}
}
