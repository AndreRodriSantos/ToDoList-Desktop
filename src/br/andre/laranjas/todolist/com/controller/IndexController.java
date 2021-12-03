package br.andre.laranjas.todolist.com.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.andre.laranjas.todolist.com.io.TarefaIO;
import br.andre.laranjas.todolist.com.model.StatusTarefa;
import br.andre.laranjas.todolist.com.model.Tarefa;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class IndexController implements Initializable, ChangeListener<Tarefa> {

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
	private TextField tfcodigo;
	// variável para guardadr a tarefa

	@FXML
	private Button btFinalizar;

	private Tarefa tarefa;
	@FXML
	private TableView<Tarefa> tvTarefa;

	@FXML
	private TableColumn<Tarefa, String> tcTarefa;

	@FXML
	private TableColumn<Tarefa, LocalDate> tcData;

	@FXML
	private Text dataTxt;

	private List<Tarefa> tarefas;

	@FXML
	void btAdiarCiick(ActionEvent event) {
		if (tarefa != null) {
			int dias = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos dias você deseja adiar?",
					"Informe quantos dias", JOptionPane.QUESTION_MESSAGE));
			LocalDate novaData = tarefa.getLimite().plusDays(dias);
			tarefa.setLimite(novaData);
			tarefa.setStatus(StatusTarefa.ADIADA);
			try {
				TarefaIO.atualizaTarefas(tarefas);
				// avisar ao usuario o adiamento da tarefa e a sua data
				JOptionPane.showMessageDialog(null, "Data Adiada com sucesso" + "\nAdiada para: " + novaData);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar a tarefa", "Erro",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	@FXML
	void btApagarClick(ActionEvent event) {
		limparCampos();
	}

	@FXML
	void btFinalizarClick(ActionEvent event) {
		if (tarefa != null) {
			tarefa.setStatus(StatusTarefa.CONCLUIDA);
			try {
				TarefaIO.atualizaTarefas(tarefas);
				JOptionPane.showMessageDialog(null, "Data Concluída. Parabéns!!!");
				tarefa.setFinalizada(LocalDate.now());
				limparCampos();
				carregarTarefas();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar a tarefa", "Erro",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	@FXML
	void btLixoClick(ActionEvent event) {
		if (tarefa != null) {
			int resposta = JOptionPane.showConfirmDialog(null,
					"Deseja realmente excluir a tarefa " + tarefa.getId() + "?", "Confirmar exclusão",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) {
				tarefas.remove(tarefa);
				try {
					TarefaIO.atualizaTarefas(tarefas);
					limparCampos();
					carregarTarefas();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir a tarefa", "Erro",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		}
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
				// verifica se é nova tarefa
				if (tarefa == null) {
					tarefa = new Tarefa();
					tarefa.setCriacao(LocalDate.now());
					tarefa.setStatus(StatusTarefa.ABERTA);
				}
				// popular a tarefa
				tarefa.setLimite(dpData.getValue());

				tarefa.setComentario(taComentario.getText());

				tarefa.setDescricao(tfTarefa.getText());
			}

			try {
				if (tarefa.getId() == 0) {
					TarefaIO.insert(tarefa);
				} else {
					TarefaIO.atualizaTarefas(tarefas);
				}

				// limpa os campos
				limparCampos();
				// carregar
				carregarTarefas();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Arquivo não encontrado" + e.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro de I/O" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
		dpData.setDisable(false);
		btFinalizar.setDisable(true);
		btAdiar.setDisable(true);
		btLixeira.setDisable(true);
		tvTarefa.getSelectionModel().clearSelection();
		tfStatus.setText("");
		tfcodigo.clear();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcData.setCellValueFactory(new PropertyValueFactory<>("limite"));
		tcTarefa.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tcData.setCellFactory(call -> {

			return new TableCell<Tarefa, LocalDate>() {
				@Override
				protected void updateItem(LocalDate item, boolean empty) {
					// TODO Auto-generated method stub
					super.updateItem(item, empty);
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
					if (!empty) {
						setText(item.format(fmt));
					} else {
						setText("");
					}
				}
			};
		});
		tvTarefa.setRowFactory(call -> new TableRow<Tarefa>(){
			protected void updateItem(Tarefa item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null) {
					setStyle("");
				}else if(item.getStatus() == StatusTarefa.CONCLUIDA){
					setStyle("-fx-background-color: #dffa66;");
				}else if(item.getLimite().isBefore(LocalDate.now())) {
					setStyle("-fx-background-color: tomato;");
				}else if(item.getStatus() == StatusTarefa.ADIADA){
					setStyle("-fx-background-color: #ff5;");
				}else {
					setStyle("-fx-background-color: lightblue;");
				}
			};
		});
		
		// evento de seleção de um item na tableView
		tvTarefa.getSelectionModel().selectedItemProperty().addListener(this);
		carregarTarefas();
	}

	private void carregarTarefas() {
		try {
			tarefas = TarefaIO.readTarefas();
			tvTarefa.setItems(FXCollections.observableArrayList(tarefas));
			// atualiza a tabela
			tvTarefa.refresh();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar tarefas", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void changed(ObservableValue<? extends Tarefa> observable, Tarefa oldValue, Tarefa newValue) {
		// passar a referencia da variavel local para a tarefa global
		tarefa = newValue;
		// se houver uma tarefa selecionada
		if (tarefa != null) {
			tfTarefa.setText(tarefa.getDescricao());
			dpData.setValue(tarefa.getLimite());
			taComentario.setText(tarefa.getComentario());
			tfStatus.setText(tarefa.getStatus() + "");
			dpData.setDisable(true);
			dpData.setOpacity(1);
			tfcodigo.setText(tarefa.getId() + "");
			if (tarefa.getStatus() == StatusTarefa.CONCLUIDA) {
				btFinalizar.setDisable(true);
				btAdiar.setDisable(true);
				btSalvar.setDisable(true);
				tfTarefa.setEditable(false);
				taComentario.setEditable(false);
				dataTxt.setText("Data que foi Concluída:");

			} else {
				btFinalizar.setDisable(false);
				btAdiar.setDisable(false);
				btSalvar.setDisable(false);
				tfTarefa.setEditable(true);
				taComentario.setEditable(true);
				dataTxt.setText("Data de Realização:");
			}
			btLixeira.setDisable(false);
		}
	}
}
