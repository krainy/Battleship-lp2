package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.imd.controle.TabuleiroController;

public class JogadorUsuario extends Jogador {

	Jogo jControl = Jogo.getInstancia();
	
	public JogadorUsuario() {
		tabuleiro = new TabuleiroController();
		naviosDoJogador = new ArrayList<>();
	}
	
	public void setTabuleiro(TabuleiroController t) {
		this.tabuleiro = t;
	}
	
	public void Atacar() {
		System.out.println("O usuário está atacando!");
		
		//jControl.realizarAtaque(x, y);
		
	}
	
	public void setBarcos(List<Barco> barcos) {
		naviosDoJogador = barcos;
	}
	
	
}
