package br.ufrn.imd.modelo;

public class PortaAvioes extends Barco {

	public PortaAvioes() {
		this.tamanho = 5;
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
