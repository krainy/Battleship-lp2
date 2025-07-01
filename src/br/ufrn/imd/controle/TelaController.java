package br.ufrn.imd.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.excecoes.PosicaoInvalidaException;
import br.ufrn.imd.modelo.Jogo;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TelaController implements Initializable {
	
	private Jogo jogo = Jogo.getInstancia();

	private final int tamanho = 10;

	@FXML
	public GridPane gridJogador;
	@FXML
	public GridPane gridInimigo;
	@FXML
	public VBox containerJogador;
	@FXML
	public VBox containerInimigo;
	@FXML
	public SplitPane tabuleiros;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	    criarTabuleiro();

	    tabuleiros.setDividerPosition(0, 0.6); 

	    containerJogador.widthProperty().addListener((obs, oldVal, newVal) -> ajustarTabuleiroJogador());
	    containerJogador.heightProperty().addListener((obs, oldVal, newVal) -> ajustarTabuleiroJogador());

	    containerInimigo.widthProperty().addListener((obs, oldVal, newVal) -> ajustarTabuleiroInimigo());
	    containerInimigo.heightProperty().addListener((obs, oldVal, newVal) -> ajustarTabuleiroInimigo());
	    
	    Platform.runLater(() -> {
	        for (Node dividerNode : tabuleiros.lookupAll(".split-pane-divider")) {
	            dividerNode.setOnMousePressed(event -> event.consume());
	            dividerNode.setOnMouseDragged(event -> event.consume());
	        }
	        
	        jogo.comecarJogo();
	        jogo.setListenerFimDeJogo(vencedor -> {
	        	System.out.println(vencedor);
	        	
	        	Platform.runLater(() ->{
	        		Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        		
	        		alert.setTitle("Fim de Jogo!");
	        		alert.setHeaderText(null);
	        		alert.setContentText(vencedor);
	        		alert.showAndWait();
	        	});
	        });
	        
	        jogo.setListenerTurno(Jogador -> {
	        	System.out.println("Turno mudou");
	        	jogadaRealizada();
	        });
	    });
	    
	}

	private void ajustarTabuleiroJogador() {
		double largura = containerJogador.getWidth();
		double altura = containerJogador.getHeight();
		double tamanho = Math.min(largura, altura) * 1;
		gridJogador.setPrefWidth(tamanho);
		gridJogador.setPrefHeight(tamanho);
	}

	private void ajustarTabuleiroInimigo() {
		double largura = containerInimigo.getWidth();
		double altura = containerInimigo.getHeight();
		double tamanho = Math.min(largura, altura) * 1;
		gridInimigo.setPrefWidth(tamanho);
		gridInimigo.setPrefHeight(tamanho);
	}

	private void animarDivisor(SplitPane splitPane, double novaPosicao, double duracaoMilissegundos) {
	    final SplitPane.Divider divider = splitPane.getDividers().get(0);


	    Timeline timeline = new Timeline();
	    KeyValue kv = new KeyValue(divider.positionProperty(), novaPosicao);
	    KeyFrame kf = new KeyFrame(Duration.millis(duracaoMilissegundos), kv);
	    timeline.getKeyFrames().add(kf);

	    timeline.play();
	}

	public void criarTabuleiro() {
		for(int y = 0; y < tamanho; y++) {
			for(int x = 0; x < tamanho; x++) {
				Button celula = new Button();
				celula.setMaxSize(Double.MAX_VALUE,  Double.MAX_VALUE);

				int finalRow = x;
				int finalCol = y;

				celula.setOnAction(e -> {
					System.out.println("Clicou na célula [" + finalRow + "] [" + finalCol + "]");

					try {
						
						jogo.realizarAtaque(finalRow, finalCol);
						jogadaRealizada();
						
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				});

				gridJogador.add(celula, finalRow, finalCol);
			}
		}
	}
	
	private void jogadaRealizada() {
		
		if(tabuleiros.getDividers().get(0).getPosition() > .5) {						
			animarDivisor(tabuleiros, 0.4, 500);
		} else {
			animarDivisor(tabuleiros, 0.6, 500);
		}
	}


}
