package br.andre.laranjas.todolist.com.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import br.andre.laranjas.todolist.com.model.StatusTarefa;
import br.andre.laranjas.todolist.com.model.Tarefa;

public class TarefaIO {
	private static final String FOLDER = System.getProperty("user.home") + "/todolist";
	private static final String FILE_IDS = FOLDER + "/id.csv";
	private static final String FILE_TAREFA = FOLDER + "/tarefas.csv";

	public static void createFiles() {
		try {
			File pasta = new File(FOLDER);
			File arquivoIds = new File(FILE_IDS);
			File arquivoTarefas = new File(FILE_TAREFA);
			if (!pasta.exists()) {
				pasta.mkdir();
				arquivoIds.createNewFile();
				arquivoTarefas.createNewFile();
				FileWriter writer = new FileWriter(arquivoIds);
				writer.write("1");
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void insert(Tarefa tarefa) throws FileNotFoundException, IOException {
		File arquivoTarefas = new File(FILE_TAREFA);
		File arquivoIds = new File(FILE_IDS);
		// Ler o ultimo id no file
		Scanner leitor = new Scanner(arquivoIds);
		tarefa.setId(leitor.nextLong());
		leitor.close();
		// Grava Tarefa
		FileWriter writer = new FileWriter(arquivoTarefas, true);
		writer.append(tarefa.formatSave());
		writer.close();
		// Grava o novo ID no arquivo
		writer = new FileWriter(arquivoIds);
		writer.write(tarefa.getId() + 1 + "");
		writer.close();
	}

	public static List<Tarefa> readTarefas() throws IOException {
		File arquivoTarefas = new File(FILE_TAREFA);
		List<Tarefa> tarefas = new ArrayList<>();
		FileReader reader = new FileReader(arquivoTarefas);
		BufferedReader buff = new BufferedReader(reader);
		String linha;
		while ((linha = buff.readLine()) != null) {
			// transforma linha em uma tarefa
			String[] vetor = linha.split(";");
			// cria Tarefa
			Tarefa t = new Tarefa();
			t.setId(Long.parseLong(vetor[0]));
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
			t.setCriacao(LocalDate.parse(vetor[1], fmt));
			t.setDescricao(vetor[4]);
			t.setComentario(vetor[5]);
			if (!vetor[3].isEmpty()) {
				t.setFinalizada(LocalDate.parse(vetor[3], fmt));
			}
			t.setLimite(LocalDate.parse(vetor[2], fmt));
			int indiceStatus = Integer.parseInt(vetor[6]);
			t.setStatus(StatusTarefa.values()[indiceStatus]);
			tarefas.add(t);
		}
		buff.close();
		reader.close();
		Collections.sort(tarefas);
		return tarefas;
	}
	public static void atualizaTarefas(List <Tarefa> tarefas) throws IOException {
		File arquivoTarefas = new File (FILE_TAREFA);
		FileWriter write = new FileWriter(arquivoTarefas);
		for(Tarefa t: tarefas) {
			write.append(t.formatSave());
		}
		write.close();
	}
}
