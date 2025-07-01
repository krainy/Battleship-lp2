package br.ufrn.imd.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.ufrn.imd.dao.Tabuleiro;
import br.ufrn.imd.excecoes.PosicaoInvalidaException;
import br.ufrn.imd.modelo.Barco;
import br.ufrn.imd.modelo.Contratorpedeiro;
import br.ufrn.imd.modelo.Destroyer;
import br.ufrn.imd.modelo.PortaAvioes;
import br.ufrn.imd.modelo.Submarino;
import br.ufrn.imd.util.Posicao;

public class TabuleiroController {

	Tabuleiro tabuleiro;
	Map<Posicao, Barco> barcos;
	
	public TabuleiroController() {
		tabuleiro = new Tabuleiro();
		barcos = new HashMap<Posicao, Barco>();
	}

	public Barco criarPortaAviao() {
		PortaAvioes p = new PortaAvioes(); 
		p.setOrientacao(getRandomOrientacao());

		coordenadasBarco(p);

		inserirBarco(p);
		return p;
	}
	
	public Barco criarDestroyer() {
		Destroyer d = new Destroyer(); 
		d.setOrientacao(getRandomOrientacao());

		coordenadasBarco(d);

		inserirBarco(d);
		return d;
	}
	
	public Barco criarContratorpedeiro() {
		Contratorpedeiro c = new Contratorpedeiro(); 
		c.setOrientacao(getRandomOrientacao());

		coordenadasBarco(c);

		inserirBarco(c);
		
		return c;
	}
	
	public Barco criarSubmarino() {
		Submarino s = new Submarino(); 
		s.setOrientacao(getRandomOrientacao());

		coordenadasBarco(s);

		inserirBarco(s);
		
		return s;
	}

	private int coordenadasBarco(Barco b){

		int tamanhoTab = tabuleiro.getTamanho();

		Random generator = new Random();
		int r1 = generator.nextInt(tamanhoTab - b.getTamanho() + 1);
		int r2 = generator.nextInt(tamanhoTab);

		if(b.getOrientacao() == "Horizontal") {
			for(int i =  r1; i < tamanhoTab; i++) {
				if(tabuleiro.getCelula(i, r2).isTemBarco()) {
					return coordenadasBarco(b);
				}
			}
		} else {
			for(int i =  r1; i < tamanhoTab; i++) {
				if(tabuleiro.getCelula(r2, i).isTemBarco()) {
					return coordenadasBarco(b);
				}
			}
		}

		if(b.getOrientacao() == "Horizontal") {
			b.setInicialX(r1);
			b.setInicialY(r2);
		} else {
			b.setInicialX(r2);
			b.setInicialY(r1);
		}

		return 1;
	}

	private void inserirBarco(Barco b) {
		
		if(b.getOrientacao() == "Horizontal") {
			for(int i = 0; i < b.getTamanho(); i++) {
				Posicao temp = new Posicao(b.getInicialX() + i, b.getInicialY());
				barcos.put(temp, b);
				tabuleiro.setCelula(b.getInicialX() + i, b.getInicialY(), true);
			}
		} else {
			for(int i = 0; i < b.getTamanho(); i++) {
				Posicao temp = new Posicao(b.getInicialX(), b.getInicialY() + i);
				barcos.put(temp, b);
				tabuleiro.setCelula(b.getInicialX(), b.getInicialY() + i, true);
			}
		}
		
		System.out.println(b.getNome() + " instanciado em " + b.getInicialX() + "/" + b.getInicialY());

	}

	private String getRandomOrientacao() {
		Random temp = new Random();
		if(temp.nextInt(2) == 0) {
			return "Horizontal";
		}

		return "Vertical";

	}
	
	public List<Barco> PreencherTabuleiro() {
		List<Barco> barcos = new ArrayList<>();
		
		barcos.add(this.criarPortaAviao());
		
		barcos.add(this.criarDestroyer());
		barcos.add(this.criarDestroyer());

		barcos.add(this.criarContratorpedeiro());
		barcos.add(this.criarContratorpedeiro());
		barcos.add(this.criarContratorpedeiro());
		
		barcos.add(this.criarSubmarino());
		barcos.add(this.criarSubmarino());
		barcos.add(this.criarSubmarino());
		barcos.add(this.criarSubmarino());
		
		return barcos;
	}
	
	public void printTabuleiro() {
		tabuleiro.printTabuleiro();
	}
	
	public boolean ataqueValido(int posX, int posY) throws PosicaoInvalidaException {
		if(posX < 0 || posX >= tabuleiro.getTamanho() || posY < 0 || posY >= tabuleiro.getTamanho()) {
			throw new PosicaoInvalidaException("Posição fora dos limites do tabuleiro!");
		}
		if(tabuleiro.getCelula(posX, posY).isFoiAtacada()) {
			throw new PosicaoInvalidaException("Esta célula já foi atacada!");
		}
		
		return true;
	}

	public void receberAtaque(int posX, int posY) {
		tabuleiro.getCelula(posX, posY).setFoiAtacada(true);
		if(tabuleiro.getCelula(posX, posY).isTemBarco()) {
			Posicao pos = new Posicao(posX, posY);
			
			barcos.get(pos).sofreDano();
			System.out.println(barcos.get(pos).getNome() + " atingido!");
		}
		tabuleiro.setCelula(posX, posY, false);
	}
	
}
