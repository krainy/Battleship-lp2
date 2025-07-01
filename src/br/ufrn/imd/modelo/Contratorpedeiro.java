package br.ufrn.imd.modelo;

public class Contratorpedeiro extends Barco {


	public Contratorpedeiro() {
		this.tamanho = 3;
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
