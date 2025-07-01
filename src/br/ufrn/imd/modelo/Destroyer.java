package br.ufrn.imd.modelo;

public class Destroyer extends Barco {
	
	public Destroyer() {
		this.tamanho = 4;
		this.vida = this.tamanho;
	}
	
	public void sofreDano() {
		if(this.vida > 0) {
			vida --;
			System.out.println("Destroyer sofreu dano!");
			//faz animacao de morte
		}
	}
	public boolean estaDestruido() {
		return this.vida == 0;
	}


	
}
