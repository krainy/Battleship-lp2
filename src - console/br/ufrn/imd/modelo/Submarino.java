package br.ufrn.imd.modelo;

public class Submarino extends Barco {

	public Submarino() {
		this.tamanho = 2;
		this.vida = this.tamanho;
	}
	
	public void sofreDano() {
		if(this.vida > 0) {
			vida --;
			
			//faz animacao de morte
		}
	}
	public boolean estaDestruido() {
		return this.vida == 0;
	}
}
