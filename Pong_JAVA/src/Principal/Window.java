package Principal;

import java.awt.Color;
import javax.swing.JFrame;

public class Window {

	private JFrame janela;
	
	public Window(Painel painel) {
		
		janela = new JFrame();
		janela.setName("Pong JAVA");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.add(painel);
		janela.setBackground(Color.BLACK);
		janela.setResizable(false);
		janela.pack();
		janela.setVisible(true);
		
	}
	
	public void resolverBugs() {
		
		janela.revalidate();
		janela.repaint();
		
	}
	
}
