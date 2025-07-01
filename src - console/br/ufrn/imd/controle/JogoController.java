package br.ufrn.imd.controle;

import br.ufrn.imd.excecoes.PosicaoInvalidaException;
import br.ufrn.imd.modelo.Jogador;
import br.ufrn.imd.modelo.JogadorIA;
import br.ufrn.imd.modelo.JogadorUsuario;

public class JogoController {

	private static JogoController instancia;

	private JogadorUsuario j1;
	private JogadorIA j2;

	private Jogador jogadorAtual;
	private Jogador jogadorAlvo;

	private JogoController() {}

	public static JogoController getInstancia() {
		if(instancia == null) {
			instancia = new JogoController();
		}

		return instancia;
	}

	public void iniciarJogo() {
		j1 = new JogadorUsuario();
		TabuleiroController t = new TabuleiroController();
		j1.setTabuleiro(t);
		
		j2 = new JogadorIA();
		t = new TabuleiroController();
		j2.setTabuleiro(t);

		j1.setBarcos(j1.getTabuleiro().PreencherTabuleiro());
		j2.setBarcos(j2.getTabuleiro().PreencherTabuleiro());
		
		j1.getTabuleiro().printTabuleiro();
		j2.getTabuleiro().printTabuleiro();


		jogadorAtual = j1;
		jogadorAlvo = j2;

		while(j1.TemNaviosVivos() && j2.TemNaviosVivos()) {

			jogadorAtual.Atacar();

			j1.getTabuleiro().printTabuleiro();
			j2.getTabuleiro().printTabuleiro();

			//Troca o turno dos jogadores
			jogadorAtual = (jogadorAtual == j1) ? j2 : j1;
			jogadorAlvo = (jogadorAtual == j1) ? j2 : j1;
		}




	}

	public void processarAtaque(int posX, int posY) {
		try {
			ValidarAtaque(posX, posY, jogadorAlvo);
			jogadorAlvo.getTabuleiro().receberAtaque(posX, posY);
			verificarFimDeJogo();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			jogadorAtual.Atacar();
		}

		
	}

	private void verificarFimDeJogo() {
		if(!j1.TemNaviosVivos()) {
			System.out.println("Jogador 2 ganhou a partida!");
		} else if (!j2.TemNaviosVivos()) {
			System.out.println("Jogador 1 ganhou a partida!");
		}

	}

	public boolean ValidarAtaque(int posX, int posY, Jogador alvo) throws PosicaoInvalidaException {
		if(alvo.getTabuleiro().ataqueValido(posX, posY)) {
			return true;
		}
		throw new PosicaoInvalidaException("Posição inválida para ataque!");
	}

}
