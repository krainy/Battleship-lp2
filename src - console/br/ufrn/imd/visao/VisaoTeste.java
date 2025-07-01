package br.ufrn.imd.visao;

import br.ufrn.imd.controle.JogoController;

public class VisaoTeste {

	static JogoController jController = JogoController.getInstancia();
	
	public static void main(String... args) {
		
		jController.iniciarJogo();
		
	}
	
}
