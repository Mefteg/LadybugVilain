package cam.ladybug.script;

import com.ladybug.engine.components.Script;

public class PlayerScript extends Script {

	public PlayerScript() {
		super();
	}
	
	@Override
	public void start() {
		super.start();
		
		this.getObject().m_textureName = "data/smb_enemies_sheet.png";
		this.getObject().m_textureWidth = 32;
		this.getObject().m_textureHeight = 32;
	}

}
