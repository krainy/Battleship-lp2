package br.ufrn.imd.modelo;

import java.util.function.Consumer;

import br.ufrn.imd.controle.TabuleiroController;
import br.ufrn.imd.excecoes.PosicaoInvalidaException;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Jogo {

	private static Jogo instancia;
	
	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorAtual;
	private Jogador jogadorAlvo;
	
	private boolean jogoFinalizado;
	
	private Consumer<String> listenerFimDeJogo;
	private Consumer<Jogador> listenerTurno;
	
	private Jogo() {
		
	}
	
	public static Jogo getInstancia() {
		if(instancia == null) {
			instancia = new Jogo();
		}
		
		return instancia;
	}
	
	public void resetarJogo() {
		instancia = new Jogo();
	}
	
	public void comecarJogo() {
		jogoFinalizado = false;
		
		TabuleiroController t = new TabuleiroController();
		jogador1 = new JogadorUsuario();
		jogador1.setTabuleiro(t);
		
		t = new TabuleiroController();
		jogador2 = new JogadorIA();
		jogador2.setTabuleiro(t);
		
		jogador1.setBarcos(jogador1.getTabuleiro().PreencherTabuleiro());
		jogador2.setBarcos(jogador2.getTabuleiro().PreencherTabuleiro());
		
		jogadorAtual = jogador1;
		jogadorAlvo = jogador2;
	}
	
	public void setListenerFimDeJogo(Consumer<String> listener) {
		this.listenerFimDeJogo = listener;
	}
	
	public void setListenerTurno(Consumer<Jogador> listener) {
		this.listenerTurno = listener;
	}
	
	//regras do jogo
	public void realizarAtaque(int x, int y) throws PosicaoInvalidaException {
			validarAtaque(x, y, jogadorAlvo);
			jogadorAlvo.getTabuleiro().receberAtaque(x, y);
			verificarFimDeJogo();
			proximoTurno();
	}
	
	public void validarAtaque(int posX, int posY, Jogador alvo) throws PosicaoInvalidaException {
		if(!alvo.getTabuleiro().ataqueValido(posX, posY)) {
			throw new PosicaoInvalidaException("Posição inválida para ataque!");
		}
	}
	
	private void verificarFimDeJogo() {
		if(!jogador1.TemNaviosVivos()) {
			listenerFimDeJogo.accept("Jogador 2 ganhou a partida!");
		} else if (!jogador2.TemNaviosVivos()) {
			listenerFimDeJogo.accept("Jogador 1 ganhou a partida!");
		}

	}
	
	public void proximoTurno() {
		jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
		jogadorAlvo = (jogadorAlvo == jogador1) ? jogador2 : jogador1;
		
		listenerTurno.accept(jogadorAtual);
		
		if(jogadorAtual instanceof JogadorIA) {
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			
			pause.setOnFinished(event -> {
				
				jogadorAtual.Atacar();
				
			});
			
			pause.play();
		}
	}
	
}
