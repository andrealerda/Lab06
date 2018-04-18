package it.polito.tdp.meteo;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class MeteoController {

	private Model model;
	List<Integer> mesi= new LinkedList<Integer>();
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<Integer> boxMese;

	@FXML
	private Button btnCalcola;

	@FXML
	private Button btnUmidita;

	@FXML
	private TextArea txtResult;

	@FXML
	void doCalcolaSequenza(ActionEvent event) {

	}

	@FXML
	void doCalcolaUmidita(ActionEvent event) {
		txtResult.clear();
		
		if(boxMese.getValue()== null) {
			txtResult.setText("Scegliere mese");
			return;
		}
		
		txtResult.setText(model.getUmiditaMedia(boxMese.getValue()));
	}

	@FXML
	void initialize() {
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Meteo.fxml'.";
	}

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
		setBoxMese();
	}
	
	private void setBoxMese() {
		
		for(int i=1 ; i<13; i++) {
			mesi.add(i);
		}

		boxMese.getItems().addAll(mesi);
	}
	

}
