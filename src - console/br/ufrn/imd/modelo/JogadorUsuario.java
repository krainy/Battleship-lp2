package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufrn.imd.controle.JogoController;
import br.ufrn.imd.controle.TabuleiroController;

public class JogadorUsuario extends Jogador {

	JogoController jControl = JogoController.getInstancia();
	
	public JogadorUsuario() {
		tabuleiro = new TabuleiroController();
		naviosDoJogador = new ArrayList<>();
	}
	
	public void setTabuleiro(TabuleiroController t) {
		this.tabuleiro = t;
	}
	
	public void Atacar() {
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Você quer atacar a casa X: ");
		int x = entrada.nextInt();
		System.out.print("Você quer atacar a casa Y: ");
		int y = entrada.nextInt();
		
		jControl.processarAtaque(x, y);
		
	}
	
	public void setBarcos(List<Barco> barcos) {
		naviosDoJogador = barcos;
	}
	
	
}
