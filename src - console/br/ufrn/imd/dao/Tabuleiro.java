package br.ufrn.imd.dao;

import java.util.Map;

import br.ufrn.imd.modelo.Celula;

public class Tabuleiro {
	
	private Celula[][] tabuleiro;
	private int tamanho;
	
	public Tabuleiro() {
		this.tamanho = 10;
		tabuleiro = new Celula[tamanho][tamanho];
		resetarTabuleiro();
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	public void criarCelula(int x, int y) {
		tabuleiro[x][y] = new Celula();
		tabuleiro[x][y].setTemBarco(false);
	}
	
	public void setCelula(int x, int y, boolean value) {
		tabuleiro[x][y].setTemBarco(value);
	}
	
	public void setCelula(Map<Integer, Integer> map, boolean value) {
		for(Integer x : map.keySet()) {
			tabuleiro[x][map.get(x)].setTemBarco(value);
		}
	}
	
	public Celula getCelula(int x, int y) {
		
		return tabuleiro[x][y];
	}
	
	
	
	public void resetarTabuleiro() {		
		int profundidade = this.tamanho;
		int largura = this.tamanho;
		
		for(int i = 0; i < profundidade; i++) {
			for(int j = 0; j < largura; j++) {
				criarCelula(i, j);
			}
		}
	}
	
	public void printTabuleiro() {
		int profundidade = this.tamanho;
		int largura = this.tamanho;
		
		System.out.print("  | ");
		for(int i = 0; i < this.tamanho; i++) {
			System.out.print(i + " ");
		}
		System.out.print("|\n");
		
		System.out.println("—————————————————————————|");
		
		for(Integer i = 0; i < profundidade; i++) {
			System.out.printf("%2d| ", i);
			for(Integer j = 0; j < largura; j++) {
				if(this.getCelula(j, i).isTemBarco()) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.print(" |\n");
		}
		System.out.println("—————————————————————————|");
	}
	
	
}
