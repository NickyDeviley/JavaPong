package Principal;

public class Game implements Runnable {

	private Window janela;			// Criando um objeto janela
	private Painel painel;			// Criando um objeto painel
	private Thread ThreadJogo;		// Criando um objeto Thread
	private final int FPS = 60;		// Determinando o FPS do jogo
	
	
	// Método construtor da classe Game
	public Game() {
		
		// Instanciando os objetos criados
		painel = new Painel();
		janela = new Window(painel);
		
		painel.requestFocus();		// Método que torna o painel alvo dos inputs
		comecarLoopJogo();			// Método para iniciar o GameLoop
	}

	// Método para iniciar o GameLoop
	public void comecarLoopJogo() {
		
		// Instanciando a Thread e passando o objeto Game
		// que é instanciado na classe Program 
		ThreadJogo = new Thread(this);
		ThreadJogo.start();			// Iniciando a Thread e o Runnable
		
	}
	
	@Override
	public void run() {
		
		// Dividindo 1 bilhão pelo FPS do jogo para
		// armazená-lo em nano-segundos.
		// 1seg = 1 bilhão nanoseg.
		// 1 bilhão / 60 = 1.6666666666666666E7
		// 1.6666666666666666E7 = 16.666.666,666666666
		double tempoPorFrame = 1000000000.0 / FPS;

		
		// Criando uma variável que recebe o horário atual
		// em nano segundos.
		// Caso seja 00:01
		// lastFrame = 60 bilhões
		long lastFrame = System.nanoTime();
		
		long agora;										// Declarando a variável long que será usada depois
		//int frame = 0;									// Variável para contar quantos Frames por segundo
		//long ultimoCheck = System.currentTimeMillis();	// Guardando o tempo do sistema em milissegundos
		
		// Laço principal do jogo
		while(true) {
			
			// Atribuindo o tempo do sistema em NANOsegundos para a variável long criada
			agora = System.nanoTime();
			
			/*
				O ultimoFrame terminou em X nanoSSegundos;
				SE o momento atual - o ultimoFrame for
				maior ou igual o tempo de duração de 1 Frame;
				
				Tudo na tela é atualizado;
				atualiza o ultimoFrame;
				soma 1 a variável frame;
			*/
			if (agora - lastFrame >= tempoPorFrame) {
				
				painel.repaint();	// Pinta tudo o que está na tela novamente
				lastFrame = agora;	// Atualiza o momento de atualização do ultimoFrame
				//frame++;			// Soma 1 a variável Frame
				
			}
			/*
				Aqui a lógica é parecida, porém em MILISSEGUNDOS
				
				Se o momento atual do sistema em MILISSEGUNDOS 
				menos o momento da últimaVerificação for maior
				ou igual a 1000;
				
				Atualiza o momento da última verificação;
				Printa quantas vezes por segundo o jogo está atualizando;
				reseta a variável Frame para contar novamente;
			*/
			/*
			if (System.currentTimeMillis() - ultimoCheck >= 1000) {
				
				ultimoCheck = System.currentTimeMillis();
				//System.out.println("FPS: " + frame);
				frame = 0;
			}
			*/
		}
	}
	
}
