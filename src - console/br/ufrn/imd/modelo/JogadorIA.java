package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufrn.imd.controle.JogoController;
import br.ufrn.imd.controle.TabuleiroController;

public class JogadorIA extends Jogador {
	
	JogoController jControl = JogoController.getInstancia();
	
	public JogadorIA() {
		tabuleiro = new TabuleiroController();
		this.naviosDoJogador = new ArrayList<>();
	}
	
	public void Atacar() {
		System.out.println("O bot esta atacando!");
		
		Random r = new Random();
		
		int r1 = r.nextInt(10);
		int r2 = r.nextInt(10);
		
		System.out.println("O bot vai atacar na casa " + r1 + " " + r2);
		
		jControl.processarAtaque(r1, r2);
	}

	public void setTabuleiro(TabuleiroController t) {
		this.tabuleiro = t;
		
	}
	
	public void setBarcos(List<Barco> barcos) {
		naviosDoJogador = barcos;
	}
	
}
