package br.andre.laranjas.todolist.com.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.andre.laranjas.todolist.com.io.TarefaIO;
import br.andre.laranjas.todolist.com.model.StatusTarefa;
import br.andre.laranjas.todolist.com.model.Tarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IndexController implements Initializable {

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
	// variável para guardadr a tarefa
	private Tarefa tarefa;

	@FXML
	private Button btFinalizar;

	@FXML
	void btAdiarCiick(ActionEvent event) {

	}

	@FXML
	void btApagarClick(ActionEvent event) {
		limparCampos();
	}

	@FXML
	void btFinalizarClick(ActionEvent event) {

	}

	@FXML
	void btLixoClick(ActionEvent event) {

	}

	@FXML
	void btSalvarClick() {
		if (dpData.getValue() == null) {
			JOptionPane.showMessageDialog(null, "Informe a data Limite", "Informe", JOptionPane.ERROR_MESSAGE);
			dpData.requestFocus();
		} else if (tfTarefa.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a Tarefa", "Informe", JOptionPane.ERROR_MESSAGE);
			dpData.requestFocus();
		} else if (taComentario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Comentário", "Informe", JOptionPane.ERROR_MESSAGE);
			dpData.requestFocus();
		} else {
			// verifica se a data informada já foi passada
			if (dpData.getValue().isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "Data inválida", "Informe", JOptionPane.ERROR_MESSAGE);
				dpData.requestFocus();
			} else {
				// instacia uma tarefa
				tarefa = new Tarefa();
				// popular a tarefa
				tarefa.setLimite(dpData.getValue());

				tarefa.setCriacao(LocalDate.now());

				tarefa.setStatus(StatusTarefa.ABERTA);

				tarefa.setComentario(taComentario.getText());

				tarefa.setDescricao(tfTarefa.getText());
				// TODO salvar no banco de dados
				try {
					TarefaIO.insert(tarefa);
					// limpa os campos
					limparCampos();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Arquivo não encontrado" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro de I/O" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				System.out.println(tarefa.formatSave());

			}
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

	private void limparCampos() {
		dpData.setValue(null);
		taComentario.setText(null);
		tfTarefa.setText(null);
		tfTarefa.requestFocus();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
