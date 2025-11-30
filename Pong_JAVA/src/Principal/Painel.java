package Principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class Painel extends JPanel {

	/*
		Classe que cria um painel para o jogo.
		A classe extende (HERDA) a classe JPanel.-
	*/
	
	private final int sizeX_Rect = 30, sizeY_Rect = 250;			// Tamanho das barras 50 na horizontal e 250 na vertical							
	private final int size_Bola = 50;								// Tamanho da Bola 50 na horizontal e na vertical
	private final int bordaSuperior = 0, bordaInferior = 720,		// Variáveis para determinar as bordas
					  bordaEsquerda = 0, bordaDireita = 1280;		// do painel
	private float moveBolaX = 5f;
	private float moveBolaY = 5f;
	
	private Random geradorNumAleatorio;
	private Placar placar;
	
	// Posição inicial da bola e do retângulo 1 e 2
	private float posX_Bola = 615, posY_Bola = 335;			// 615 pixels na horizontal 335 pixels na vertical
	private float posX_Rect1 = 100f;						// 100 Pixels na horizontal
	private float posY_Rect1 = 245f;						// 260 na vertical
	private float posX_Rect2 = 1130f;						// 1130 pixels na horizontal	
	private float posY_Rect2 = 245f;						// 260 na vertical
	
	// Hitbox das barras e da bola
	private float hitBoxDirBola = posX_Bola + size_Bola;
	private float hitBoxEsqBola = posX_Bola;
	private float hitBoxSupBola = posY_Bola;
	private float hitBoxInfBola = posY_Bola + size_Bola;

	private float hitBoxDirBarra1 = posX_Rect1 + sizeX_Rect;
	private float hitBoxEsqBarra1 = posX_Rect1;
	private float hitBoxSupBarra1 = posY_Rect1;
	private float hitBoxInfBarra1 = posY_Rect1 + sizeY_Rect;

	private float hitBoxDirBarra2 = posX_Rect2 + sizeX_Rect;
	private float hitBoxEsqBarra2 = posX_Rect2;
	private float hitBoxSupBarra2 = posY_Rect2;
	private float hitBoxInfBarra2 = posY_Rect2 + sizeY_Rect;
	
	// Construtor da classe Painel
	public Painel() {
		
		geradorNumAleatorio = new Random();
		setPanelSize();										// Determinando um tamanho para o painel
		setBackground(Color.BLACK);							// Pintando o plano de fundo
		addKeyListener(new EntradaTeclado(this));			// Adicionando um identificador de teclas ao painel
															// uma instancia da classe que criamos como parâmetro
															// e enviando o painel como parâmetro desta instância
		placar = new Placar();
		add(placar);
	}
	
	// Métodos para mover os retângulos
	public void subirRect1(int valor) {
		if (posY_Rect1 > bordaSuperior) {
			posY_Rect1 += valor;
			hitBoxSupBarra1 = posY_Rect1;
			hitBoxInfBarra1 = posY_Rect1 + sizeY_Rect;
		}
	}
	public void descerRect1(int valor) {
		if ((posY_Rect1 + 250) < bordaInferior) {
			posY_Rect1 += valor;
			hitBoxSupBarra1 = posY_Rect1;
			hitBoxInfBarra1 = posY_Rect1 + sizeY_Rect;
		}
	}
	public void subirRect2(int valor) {
		if (posY_Rect2 > bordaSuperior) {
			posY_Rect2 += valor;
			hitBoxSupBarra2 = posY_Rect2;
			hitBoxInfBarra2 = posY_Rect2 + 250;
		}
	}
	public void descerRect2(int valor) {
		if ((posY_Rect2 + 250) < bordaInferior) {
			posY_Rect2 += valor;
			hitBoxSupBarra2 = posY_Rect2;
			hitBoxInfBarra2 = posY_Rect2 + 250;
		}
	}
	
	// Método da superclasse que PRECISA ser reescrito
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		// Determinando a cor dos objetos criados
		g.setColor(Color.WHITE);
		
		// Método para movimentar a bola
		atualizarBola();
		
		// Criando os Retângulos [Bola, barra1, barra2]
		// Posição X, PosiçãoY, largura, altura
		// Convertendo o valor para inteiro
		g.fillRect((int) posX_Bola, (int) posY_Bola, size_Bola, size_Bola);
		g.fillRect((int) posX_Rect1, (int) posY_Rect1, sizeX_Rect, sizeY_Rect);
		g.fillRect((int) posX_Rect2, (int) posY_Rect2, sizeX_Rect, sizeY_Rect);
	}
	
	public void atualizarBola() {	
		posX_Bola += moveBolaX;
		hitBoxDirBola = posX_Bola + size_Bola;
		hitBoxEsqBola = posX_Bola;
		if ((hitBoxDirBola >= hitBoxEsqBarra1 && hitBoxEsqBola <= hitBoxDirBarra1)) {
			if ((hitBoxInfBola >= hitBoxSupBarra1 && hitBoxSupBola <= hitBoxInfBarra1)) {
				moveBolaX *= -1;
				posX_Bola = hitBoxDirBarra1;
				
				if (moveBolaX > 0 && moveBolaX <= 20) {
					moveBolaX++;
				}
				else if (moveBolaX < 0 && moveBolaX >= -20) {
					moveBolaX--;
				}
			}
		}
		
		posY_Bola += moveBolaY;
		hitBoxSupBola = posY_Bola;
		hitBoxInfBola = posY_Bola + size_Bola;
		
		if (hitBoxEsqBola <= hitBoxDirBarra2 && hitBoxDirBola >= hitBoxEsqBarra2) {
			if (hitBoxInfBola >= hitBoxSupBarra2 && hitBoxSupBola <= hitBoxInfBarra2) {
				moveBolaX *= -1;
				posX_Bola = hitBoxEsqBarra2 - size_Bola;
				
				if (moveBolaX > 0 && moveBolaX <= 20) { moveBolaX++; }
				else if (moveBolaX < 0 && moveBolaX >= -20) { moveBolaX--; }
			
			}
		}

		if ((posY_Bola + size_Bola) >= bordaInferior || posY_Bola <= bordaSuperior) {
			if (moveBolaY > 0 && moveBolaY <= 20) {
				moveBolaY++;
			}
			else if (moveBolaY < 0 && moveBolaY >= -20) {
				moveBolaY--;
			}
			moveBolaY *= -1;
		}
		
		if ((posX_Bola + size_Bola) >= bordaDireita) {
			placar.pontoJogador1();
			int num = geradorNumAleatorio.nextInt(2);
			switch (num) {
				case 0:
					moveBolaX = -5f;
					moveBolaY = 5f;
					break;
				case 1:
					moveBolaX = 5f;
					moveBolaY = -5f;
					break;
			}
			posX_Bola = 615f;
			posY_Bola = 335f;
		}
		if (posX_Bola <= bordaEsquerda) {
			placar.pontoJogador2();
			int num = geradorNumAleatorio.nextInt(2);
			switch (num) {
				case 0:
					moveBolaX = -5f;
					moveBolaY = 5f;
					break;
				case 1:
					moveBolaX = 5f;
					moveBolaY = -5;
					break;
			}
			posX_Bola = 615f;
			posY_Bola = 335f;
		}
	}
	
	public void setPanelSize() {
		
		Dimension tamanho = new Dimension(1280, 720);
		setMinimumSize(tamanho);
		setPreferredSize(tamanho);
		setMaximumSize(tamanho);
		
	}
	
	
}
