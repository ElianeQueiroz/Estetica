package br.edu.up.view;

import java.util.List;
import java.util.Scanner;

import br.edu.up.dao.ServicoDAO;
import br.edu.up.model.Servico;

public class ViewServico {
	public static void cadastro(ServicoDAO servicoDAO, Scanner sc) throws Exception {
		int opcao = 0;
		int id;

		do {			
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("*              SERVIÇO                  *");
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
				Servico servicoNovo = new Servico();
				
				sc.nextLine();
				
				String descricao;
				System.out.print("Informe o Descrição: ");
				descricao=sc.nextLine();
				servicoNovo.setDescricao(descricao);
				
				double valor;
				System.out.print("Informe o valor: ");
				valor=sc.nextDouble();
				servicoNovo.setValor(valor);
				
				servicoDAO.salvar(servicoNovo);
				
				break;
			case 2:
				System.out.print("Informe o código do serviço a ser alterado: ");
				id = sc.nextInt();
                
				sc.nextLine();
				
				Servico servicoAlterar = servicoDAO.buscarPorId(id);
				
				if ( servicoAlterar != null ) {
					String opSN = "";
					System.out.print("Deseja altera a descrição " + servicoAlterar.getDescricao() + " S/N?: ");
                    opSN = sc.next();
                    
                    sc.nextLine();
                    
                    if ( opSN.equals("S") ) {
                    	String novaDescricao="";
                    	System.out.print("Alterar para: ");
                    	novaDescricao= sc.nextLine();
                    	servicoAlterar.setDescricao(novaDescricao);
                    }
                    
					System.out.print("Deseja altera o valor S/N?: ");
                    opSN = sc.next();
                    
                    sc.nextLine();
                    
                    if ( opSN.equals("S") ) {
                    	double novoValor;
                    	System.out.print("Alterar para: ");
                    	novoValor= sc.nextDouble();
                    	servicoAlterar.setValor(novoValor);
                    }
                    
                    servicoDAO.atualizar(servicoAlterar);
				} else {
					System.out.println("Serviço não encontrado.");				    	
				}			
				
				break;
			case 3:
				System.out.print("Informe o código do serviço a ser excluído: ");
				id = sc.nextInt();
				
				sc.nextLine();
                
				Servico servico = servicoDAO.buscarPorId(id);
				
				if ( servico != null ) {
					String opSN = "";
					System.out.print("Deseja realmente excluir o serviço " + servico.getDescricao() + " S/N?: ");
                    opSN = sc.next();
                    
                    sc.nextLine();
                    
                    if ( opSN.equals("S") ) {
                    	servicoDAO.apagar(servico.getIdServico());
                    }
				} else {
					System.out.println("Serviço não encontrado.");
				}
				
				break;
			case 4:
				System.out.println("LISTA DE SERVIÇOS");
			
				List<Servico> listaDeServicos = servicoDAO.listar();
				
				for (Servico s : listaDeServicos) {
					System.out.println(s);
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
