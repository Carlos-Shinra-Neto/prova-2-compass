package com.produtos.enums;

public enum SentimentoEnum {

	FElIZ("divertido"), CHATEADO("chateado"), NEUTRO("neutro");

	private String descricao;

	private SentimentoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static SentimentoEnum getEnumByDescricao(String descricao) {
		for (SentimentoEnum e : values()) {
			if (e.getDescricao().equals(descricao)) {
				return e;
			}
		}
		return null;
	}

}
