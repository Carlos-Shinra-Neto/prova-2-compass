package com.produtos.domain;

import com.produtos.enums.SentimentoEnum;

public class Funcionario {

	public static final String TABLE_NAME = "funcionarios";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NOME = "nome";
	public static final String COLUMN_SENTIMENTO = "sentimento";

	private Long id;
	private String nome;
	private SentimentoEnum sentimento;

	public Funcionario() {
		super();
	}

	public Funcionario(String nome, SentimentoEnum sentimento) {
		this();
		this.nome = nome;
		this.sentimento = sentimento;
	}

	public Funcionario(Long id, String nome, SentimentoEnum sentimento) {
		this(nome, sentimento);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SentimentoEnum getSentimento() {
		return sentimento;
	}

	public void setSentimento(SentimentoEnum sentimento) {
		this.sentimento = sentimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", sentimento=" + sentimento + "]";
	}

}
