package com.produtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.produtos.dao.ProdutoDao;
import com.produtos.domain.Produto;

//QUESTÃO 9
public class ApplicationProduto {

	private static final String MAIN = "-1";
	private static final String INSERT = "1";
	private static final String UPDATE = "2";
	private static final String DELETE = "3";
	private static final String LIST = "4";

	private ProdutoDao dao;
	private Scanner input;
	private Random random;

	public ApplicationProduto() {
		dao = new ProdutoDao();
		input = new Scanner(System.in);
		random = new Random();
	}

	public static void main(String[] args) {
		new ApplicationProduto().init();
	}

	private void init() {
		callAction(MAIN);
	}

	private void callAction(String action) {
		switch (action) {
		case MAIN:
			mainMenu();
			break;
		case INSERT:
			insertAction();
			break;
		case UPDATE:
			updateAction();
			break;
		case DELETE:
			deleteAction();
			break;
		case LIST:
			listAction();
			break;
		default:
			System.out.println("\n============= Fim de execução =============");
		}
	}

	private boolean between(String value, int min, int max) {
		try {
			return Integer.parseInt(value) >= min && Integer.parseInt(value) <= max;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void mainMenu() {
		System.out.println("\n=============== XPTO System ===============");
		System.out.println("Digite a opção desejada:");
		System.out.println("1 - para INSERIR uma nova oferta");
		System.out.println("2 - para ATUALIZAR uma nova oferta");
		System.out.println("3 - para DELETAR uma oferta");
		System.out.println("4 - para listar as palavras que contem ?");
		System.out.println("0 - para SAIR");

		String option;
		do {
			option = input.nextLine();
		} while (!between(option, 0, 4));

		callAction(option);
	}

	private void insertAction() {
		List<Produto> produtos = dao.findAll();
		if (!produtos.isEmpty()) {
			int max = produtos.size() > 3 ? 3 : produtos.size() - 1;
			List<Produto> produtosUpdate = new ArrayList<>();
			for (int i = 0; i < max; i++) {
				Produto produto = null;
				do {
					produto = produtos.get(random.nextInt(produtos.size()));
					produto.setDesconto(Double
							.parseDouble(String.format("%.2f", random.nextInt(10000) / 100.0).replaceAll(",", ".")));
				} while (produtosUpdate.contains(produto));
				produtosUpdate.add(produto);
			}
			dao.update(produtosUpdate);
			System.out.println("Ofertas inseridas com sucesso!\n");
		}
		callAction(MAIN);
	}

	private void updateAction() {
		Long id = null;
		Double valor = null;
		try {
			System.out.println("Digite o id do produto:");
			id = Long.parseLong(input.nextLine());
		} catch (NumberFormatException e) {
			System.err.println("Id inválido");
			id = null;
		}
		if (id != null) {
			try {
				System.out.println("Digite o novo valor da oferta:");
				valor = Double.parseDouble(input.nextLine().replaceAll(",", "."));
			} catch (NumberFormatException e) {
				System.err.println("Valor inválido");
				valor = null;
			}

			if (valor != null) {
				Produto produto = dao.findById(id);
				if (produto != null) {
					produto.setDesconto(valor);
					dao.update(produto);
					System.out.println("Oferta atualizada com sucesso!");
				} else {
					System.err.println("Id não encontrado");
				}
			}
		}
		System.out.println();
		callAction(MAIN);
	}

	private void deleteAction() {
		Long id = null;
		try {
			System.out.println("Digite o id do produto:");
			id = Long.parseLong(input.nextLine());
		} catch (NumberFormatException e) {
			System.err.println("Id inválido");
			id = null;
		}
		if (id != null) {
			Produto produto = dao.findById(id);
			if (produto != null) {
				dao.deleteDesconto(produto);
				System.out.println("Oferta removida com sucesso!");
			} else {
				System.err.println("Id não encontrado");
			}
		}
		System.out.println();
		callAction(MAIN);
	}

	private void listAction() {
		System.out.println("Digite o nome do produto:");
		String nome = input.nextLine();
		List<Produto> produtos = dao.findByNome(nome);
		System.out.println(produtos.size() + " Resultado(s) encontrados\n");
		if (!produtos.isEmpty()) {
			printListProdutos(produtos, 0);
		}
		System.out.println();
		callAction(MAIN);
	}

	private void printListProdutos(List<Produto> produtos, int page) {
		int maxItensPerPage = 3;
		boolean prevPage = page > 0;
		boolean nextPage = page + maxItensPerPage < produtos.size() - 1;
		for (int i = page; i < page + maxItensPerPage; i++) {
			if (i > produtos.size() - 1) {
				break;
			}
			System.out.println(produtos.get(i));
		}
		System.out.println("\nAções:");
		if (nextPage) {
			System.out.println("1 - para próxima página");
		}
		if (prevPage) {
			System.out.println("2 - para página anterior");
		}
		System.out.println("0 - para voltar ao menu");

		String option;
		do {
			option = input.nextLine();
		} while (!between(option, 0, 2) || option.equals("1") && !nextPage || option.equals("2") && !prevPage);

		switch (option) {
		case "1":
			printListProdutos(produtos, ++page);
			break;
		case "2":
			printListProdutos(produtos, --page);
			break;
		}
	}

}
