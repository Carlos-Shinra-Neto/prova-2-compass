package com.produtos.domain;

import java.util.Date;

public class Produto {
	
	public static final String TABLE_NAME = "produto";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NOME = "nome";
	public static final String COLUMN_DESCRICAO = "descricao";
	public static final String COLUMN_DESCONTO = "desconto";
	public static final String COLUMN_DATA_INICIO = "data_inicial";

	private Long id;
	private String nome;
	private String descricao;
	private Double desconto;
	private Date dataInicio;

	public Produto() {
		super();
	}

	public Produto(Long id, String nome, String descricao, Double desconto, Date dataInicio) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
		this.dataInicio = dataInicio;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id: " + getId() + " ? " + getNome();
	}

}
