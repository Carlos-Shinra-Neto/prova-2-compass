package com.produtos;

import java.util.Scanner;

import com.produtos.dao.FuncionarioDao;
import com.produtos.domain.Funcionario;
import com.produtos.enums.SentimentoEnum;

//QUESTÃO 10
public class AplicacaoSentimento {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		FuncionarioDao dao = new FuncionarioDao();
		Scanner input = new Scanner(System.in);
		System.out.println("informe o nome do funcionário");
		String nome = input.nextLine();

		Funcionario funcionario = null;
		try {
			System.out.println("digite uma frase");
			funcionario = new Funcionario(nome, saida(input.nextLine()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		if (funcionario != null) {
			dao.insert(funcionario);
			System.out.println("operação realizada com sucesso!");
		} else {
			System.err.println("operação não realizada");
		}
	}

	public static SentimentoEnum saida(String txt) {
		switch (Integer.compare(count(txt, true), count(txt, false))) {
		case 0:
			return SentimentoEnum.NEUTRO;
		case 1:
			return SentimentoEnum.FElIZ;
		case -1:
			return SentimentoEnum.CHATEADO;
		}
		throw new IllegalArgumentException("erro inesperado!");
	}

	public static int count(String txt, boolean happy) {
		int count = 0, aux = 0;
		for (char c : txt.toCharArray()) {
			if (aux == 0) {
				if (c == ':') {
					aux++;
				} else {
					aux = 0;
				}
			} else if (aux == 1) {
				if (c == '-') {
					aux++;
				} else {
					aux = 0;
				}
			} else if (aux == 2) {
				if ((happy && c == ')') || (!happy && c == '(')) {
					count++;
				}
				aux = 0;
			}
		}
		return count;
	}

}
