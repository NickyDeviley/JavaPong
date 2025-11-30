package Principal;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
//import javax.swing.JPanel;

public class Placar extends JLabel {

	private int jogador1 = 0;
	private int jogador2 = 0;
	private String placar = jogador1 + " vs " + jogador2;
	private Font fontePlacar = new Font("Times New Roman", Font.PLAIN, 50);
	
	public Placar() {
		
		setText(placar);
		setForeground(Color.WHITE);
		setFont(fontePlacar);
		
	}
	
	public void pontoJogador1() {
		jogador1++;
		setText(jogador1 + " vs " + jogador2);
	}
	public void pontoJogador2() {
		jogador2++;
		setText(jogador1 + " vs " + jogador2);
	}
	
}
