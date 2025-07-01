package br.ufrn.imd.util;

public class Posicao {

	private int x;
	private int y;
	
	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true; // Mesmo objeto na memória
	    if (obj == null || getClass() != obj.getClass()) return false; // Tipos diferentes ou nulo

	    Posicao other = (Posicao) obj;
	    return this.x == other.x && this.y == other.y;
	}

	@Override
	public int hashCode() {
	    int result = Integer.hashCode(x);
	    result = 31 * result + Integer.hashCode(y);
	    return result;
	}

	
	
	
}
