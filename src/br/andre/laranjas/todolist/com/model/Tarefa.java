package br.andre.laranjas.todolist.com.model;

import java.time.LocalDate;

public class Tarefa {
	private long id;
	private LocalDate criacao;
	private LocalDate limite;
	private LocalDate finalizada;
	private String descricao;
	private String comentario;
	private StatusTarefa status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getCriacao() {
		return criacao;
	}
	public void setCriacao(LocalDate criacao) {
		this.criacao = criacao;
	}
	public LocalDate getLimite() {
		return limite;
	}
	public void setLimite(LocalDate limite) {
		this.limite = limite;
	}
	public LocalDate getFinalizada() {
		return finalizada;
	}
	public void setFinalizada(LocalDate finalizada) {
		this.finalizada = finalizada;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public StatusTarefa getStatus() {
		return status;
	}
	public void setStatus(StatusTarefa status) {
		this.status = status;
	}
	
	
}