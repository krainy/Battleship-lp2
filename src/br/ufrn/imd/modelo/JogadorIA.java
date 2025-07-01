package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufrn.imd.controle.TabuleiroController;
import br.ufrn.imd.excecoes.PosicaoInvalidaException;

public class JogadorIA extends Jogador {

	Jogo jControl = Jogo.getInstancia();

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

		try {
			jControl.realizarAtaque(r1, r2);
		} catch (PosicaoInvalidaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			Atacar();
		}
	}

	public void setTabuleiro(TabuleiroController t) {
		this.tabuleiro = t;

	}

	public void setBarcos(List<Barco> barcos) {
		naviosDoJogador = barcos;
	}

}
