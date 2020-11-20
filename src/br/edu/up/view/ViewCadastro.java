package br.edu.up.view;

import java.util.Scanner;

import br.edu.up.dao.ClienteDAO;
import br.edu.up.dao.ProdutoDAO;
import br.edu.up.dao.ServicoDAO;

public class ViewCadastro {
	public static void cadastro( Scanner sc ) throws Exception {
		ClienteDAO clienteDAO = new ClienteDAO();
		ServicoDAO servicoDAO = new ServicoDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		int opcao = 0;

		do {
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("*              CADASTRO                 *");
			System.out.println("*                                       *");
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("* 1 - CLIENTE                           *");
			System.out.println("* 2 - SERVIÇO                           *");
			System.out.println("* 3 - PRODUTO                           *");
			System.out.println("* 4 - VOLTAR                            *");
			System.out.println("*                                       *");
			System.out.println("*****************************************");
			
			System.out.println("");
			System.out.print("Informe a opção: ");
			opcao = sc.nextInt();
			System.out.println("");

			switch (opcao) {
			case 1:
				ViewCliente.cadastro(clienteDAO, sc);
				break;
			case 2:
				ViewServico.cadastro(servicoDAO, sc);
				break;
			case 3:
				ViewProduto.cadastro(produtoDAO, sc);
				break;
			case 4:
				break;				
			default:
				System.out.println("Opção informada inválida!");
			}
			
			System.out.println("");
			
		} while (opcao != 4);
	
	}
}
