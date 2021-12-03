package br.andre.laranjas.todolist.com.model;

public enum StatusTarefa {
	ABERTA("Aberta"), CONCLUIDA("Conclu�da"), ADIADA("Adiada");
	
	String descricao;
	
	private StatusTarefa(String desc) {
		this.descricao = desc;
	}
	@Override
	public String toString() {
		return descricao;
	}
}
