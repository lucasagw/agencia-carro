package br.ucsal.pooa.concesionaria.test;

import java.util.List;
import java.util.Scanner;

import br.ucsal.pooa.concesionaria.db.CompradorDAOImpl;
import br.ucsal.pooa.concesionaria.interfaces.CompradorDAO;
import br.ucsal.pooa.concesionaria.model.Comprador;

public class CompradorCRUD {

	private static Scanner teclado = new Scanner(System.in);
	private static CompradorDAO dao = new CompradorDAOImpl();

	public static void executar(int op) {
		switch (op) {
		case 1:
			inserir();
			break;
		case 2:
			atualizar();
			break;
		case 3:
			listar();
			break;
		case 4:
			System.out.println("Digite o Nome: ");
			buscarPorNome(teclado.nextLine());
			break;
		case 5:
			deletar();
			break;

		}

	}

	private static void inserir() {
		Comprador c = new Comprador();
		System.out.println("Nome: ");
		c.setNome(teclado.nextLine());
		System.out.println("CPF: ");
		c.setCpf(teclado.nextLine());
		dao.save(c);
	}

	private static void atualizar() {
		System.out.println("Selecione um dos Compradores abaixo: ");
		List<Comprador> compradorList = listar();
		Comprador c = compradorList.get(Integer.parseInt(teclado.nextLine()));
		System.out.println("Novo Nome ou Enter para manter o mesmo: ");
		String nome = teclado.nextLine();
		System.out.println("Novo CPF ou Enter para manter o mesmo: ");
		String cpf = teclado.nextLine();
		if (!nome.isEmpty()) {
			c.setNome(nome);
		}
		if (!cpf.isEmpty()) {
			c.setCpf(cpf);
		}
		dao.update(c);

	}

	public static List<Comprador> listar() {
		List<Comprador> compradorList = dao.selectAll();
		for (int i = 0; i < compradorList.size(); i++) {
			Comprador c = compradorList.get(i);
			System.out.println("[" + i + "]" + c.getNome() + " " + c.getCpf());

		}
		return compradorList;
	}

	private static void buscarPorNome(String nome) {
		List<Comprador> compradorList = dao.searchByName(nome);
		for (int i = 0; i < compradorList.size(); i++) {
			Comprador c = compradorList.get(i);
			System.out.println("[" + i + "]" + c.getNome() + " " + c.getCpf());

		}

	}

	private static void deletar() {
		System.out.println("Selecione um dos Compradores abaixo para Deletar");
		List<Comprador> compradorList = listar();
		int index = Integer.parseInt(teclado.nextLine());
		System.out.println("Tem certeza? S/N");
		String op = teclado.nextLine();
		if (op.startsWith("s")) {
			dao.delete(compradorList.get(index));
		}

	}
}
