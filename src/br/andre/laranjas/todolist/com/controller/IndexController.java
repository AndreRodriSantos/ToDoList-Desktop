package br.andre.laranjas.todolist.com.controller;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IndexController {

	@FXML
	private DatePicker dpData;

	@FXML
	private TextField tfTarefa;

	@FXML
	private TextField tfStatus;

	@FXML
	private TextArea taComentario;

	@FXML
	private Button btLixeira;

	@FXML
	private Button btApagar;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btAdiar;

	@FXML
	private Button btFinalizar;

	@FXML
	void btAdiarCiick(ActionEvent event) {

	}

	@FXML
	void btApagarClick(ActionEvent event) {

	}

	@FXML
	void btFinalizarClick(ActionEvent event) {

	}

	@FXML
	void btLixoClick(ActionEvent event) {

	}

	@FXML
    void btSalvarClick() {
    	if(dpData.getValue()== null) {
    		JOptionPane.showMessageDialog(null, "Informe a data Limite", "Informe", JOptionPane.ERROR_MESSAGE);
    		dpData.requestFocus();
    	}else if(tfTarefa.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Informe a data Limite", "Informe", JOptionPane.ERROR_MESSAGE);
    		dpData.requestFocus();
    	}else if(taComentario.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Informe a data Limite", "Informe", JOptionPane.ERROR_MESSAGE);
    		dpData.requestFocus();
    }else {
    	
    }
    }

	@FXML
	void data(ActionEvent event) {

	}

	@FXML
	void status(ActionEvent event) {

	}

	@FXML
	void tarefa(ActionEvent event) {

	}

}
