package br.ufrn.imd.modelo;

import java.util.List;

import br.ufrn.imd.controle.TabuleiroController;
import br.ufrn.imd.util.IAtaque;

public abstract class Jogador implements IAtaque {

	protected TabuleiroController tabuleiro;
	protected List<Barco> naviosDoJogador;
	
	public boolean TemNaviosVivos() {
		for(var n : naviosDoJogador) {
			if(!n.estaDestruido()) {
				return true;
			}
		}
		
		return false;
	}
	
	public TabuleiroController getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract void Atacar();
	
}
