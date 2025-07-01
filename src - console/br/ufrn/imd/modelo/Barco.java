package br.ufrn.imd.modelo;

public abstract class Barco {

	protected int inicialX;
	protected int inicialY;
	protected String orientacao;
	protected Integer tamanho;
	protected Integer vida;
	
	public int getInicialX() {
		return inicialX;
	}
	public void setInicialX(int inicialX) {
		this.inicialX = inicialX;
	}
	public int getInicialY() {
		return inicialY;
	}
	public void setInicialY(int inicialY) {
		this.inicialY = inicialY;
	}
	public String getOrientacao() {
		return orientacao;
	}
	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}
	public Integer getTamanho() {
		return tamanho;
	}
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
	
	public abstract void sofreDano();
	public abstract boolean estaDestruido();
	
	public String getNome() {
		return this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1);
	}
	
	
}
