package Principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EntradaTeclado implements KeyListener {

	private Painel painel;
	
	public EntradaTeclado(Painel painel) {
		this.painel = painel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				this.painel.subirRect2(-20);
				break;
			case KeyEvent.VK_DOWN:
				this.painel.descerRect2(20);
				break;
			case KeyEvent.VK_W:
				this.painel.subirRect1(-20);
				break;
			case KeyEvent.VK_S:
				this.painel.descerRect1(20);
				break;
			default:
				break;
		}
	}

	
	
}
