package br.edu.up.view;

import java.util.List;
import java.util.Scanner;

import br.edu.up.dao.ProdutoDAO;
import br.edu.up.model.Produto;

public class ViewProduto {
	public static void cadastro(ProdutoDAO produtoDAO, Scanner sc) throws Exception {
		int opcao = 0;
		int id;

		do {
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("*              PRODUTO                  *");
			System.out.println("*                                       *");
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("* 1 - NOVO                              *");
			System.out.println("* 2 - ALTERAR                           *");
			System.out.println("* 3 - EXCLUIR                           *");
			System.out.println("* 4 - LISTAR                            *");
			System.out.println("* 5 - VOLTAR                            *");
			System.out.println("*                                       *");
			System.out.println("*****************************************");

			System.out.println("");
			System.out.print("Informe a opção: ");
			opcao = sc.nextInt();
			System.out.println("");

			switch (opcao) {
			case 1:
				Produto produtoNovo = new Produto();

				sc.nextLine();

				String descricao;
				System.out.print("Informe o Descrição: ");
				descricao = sc.nextLine();
				produtoNovo.setDescricao(descricao);

				double valor;
				System.out.print("Informe o valor: ");
				valor = sc.nextDouble();
				produtoNovo.setValor(valor);

				produtoDAO.salvar(produtoNovo);

				break;
			case 2:
				System.out.print("Informe o código do produto a ser alterado: ");
				id = sc.nextInt();

				sc.nextLine();
				
				Produto produtoAlterar = produtoDAO.buscarPorId(id);
				
				if (produtoAlterar != null) {
					String opSN = "";
					System.out.print("Deseja altera a descrição " + produtoAlterar.getDescricao() + " S/N?: ");
					opSN = sc.next();
					
					sc.nextLine();

					if (opSN.equals("S")) {
						String novaDescricao = "";
						System.out.print("Alterar para: ");
						novaDescricao = sc.nextLine();
						produtoAlterar.setDescricao(novaDescricao);
					}

					System.out.print("Deseja altera o valor S/N?: ");
					opSN = sc.next();
					
					sc.nextLine();
					
					if (opSN.equals("S")) {
						double novoValor;
						System.out.print("Alterar para: ");
						novoValor = sc.nextDouble();
						produtoAlterar.setValor(novoValor);
					}
					
					produtoDAO.atualizar(produtoAlterar);
				} else {
					System.out.println("Produto não encontrado.");
				}

				break;
			case 3:
				System.out.print("Informe o código do produto a ser excluído: ");
				id = sc.nextInt();
				
				sc.nextLine();

				Produto servico = produtoDAO.buscarPorId(id);

				if (servico != null) {
					String opSN = "";
					System.out.print("Deseja realmente excluir o produto " + servico.getDescricao() + " S/N?: ");
					opSN = sc.next();
					
					sc.nextLine();

					if (opSN.equals("S")) {
						produtoDAO.apagar(servico.getIdProduto());
					}
				} else {
					System.out.println("Produto não encontrado.");
				}

				break;
			case 4:
				System.out.println("LISTA DE PRODUTOS");

				List<Produto> listaDeProdutos = produtoDAO.listar();

				for (Produto p : listaDeProdutos) {
					System.out.println(p);
				}
				break;
			case 5:
				break;
			default:
				System.out.println("Opção informada inválida!");
			}

			System.out.println("");

		} while (opcao != 5);
	}

}
