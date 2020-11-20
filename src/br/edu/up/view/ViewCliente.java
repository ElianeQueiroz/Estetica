package br.edu.up.view;

import java.util.List;
import java.util.Scanner;

import br.edu.up.dao.ClienteDAO;
import br.edu.up.model.Cliente;

public class ViewCliente {
	public static void cadastro(ClienteDAO clienteDAO, Scanner sc) throws Exception {
		int opcao = 0;
		int id;

		do {			
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("*              CLIENTE                  *");
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
				Cliente clienteNovo = new Cliente();
				
				sc.nextLine();
				
				String nome;
				System.out.print("Nome: ");
				nome=sc.nextLine();
				clienteNovo.setNome(nome);
				
				String fone;
				System.out.print("Telefone: ");
				fone=sc.nextLine();
				clienteNovo.setfone(fone);
				
				int idade;
				System.out.print("Idade: ");
				idade=sc.nextInt();
				clienteNovo.setIdade(idade);
				
				clienteDAO.salvar(clienteNovo);
				
				break;
			case 2:
				System.out.print("Informe o código do cliente a ser alterado: ");
				id = sc.nextInt();
				
				sc.nextLine();
                
				Cliente clienteAlterar = clienteDAO.buscarPorId(id);
				
				if ( clienteAlterar != null ) {
					String opSN = "";
					System.out.print("Deseja altera o nome " + clienteAlterar.getNome() + " S/N?: ");
                    opSN = sc.next();
                    
                    sc.nextLine();
                    
                    if ( opSN.equals("S") ) {
                    	String novoNome="";
                    	System.out.print("Alterar para: ");
                    	novoNome= sc.nextLine();
                    	clienteAlterar.setNome(novoNome);
                    }
                    
					System.out.print("Deseja altera o telefone " + clienteAlterar.getfone() + " S/N?: ");
                    opSN = sc.next();
                    
                    sc.nextLine();
                    
                    if ( opSN.equals("S") ) {
                    	String novoFone="";
                    	System.out.print("Alterar para: ");
                    	novoFone= sc.nextLine();
                    	clienteAlterar.setfone(novoFone);
                    }
                    
					System.out.print("Deseja altera a idade " + clienteAlterar.getIdade() + " S/N?: ");
                    opSN = sc.next();
                    
                    sc.nextLine();
                    
                    if ( opSN.equals("S") ) {
                    	int novaIdade;
                    	System.out.print("Alterar para: ");
                    	novaIdade= sc.nextInt();
                    	clienteAlterar.setIdade(novaIdade);
                    }                        
                    
                    clienteDAO.atualizar(clienteAlterar);
				} else {
					System.out.println("Cliente não encontrado.");
				}
				
				break;
			case 3:
				System.out.print("Informe o código do cliente a ser excluído: ");
				id = sc.nextInt();
				
				sc.nextLine();
                
				Cliente cliente = clienteDAO.buscarPorId(id);
				
				if ( cliente != null ) {
					String opSN = "";
					System.out.print("Deseja realmente excluir o cliente " + cliente.getNome() + " S/N?: ");
                    opSN = sc.next();
                    
                    sc.nextLine();
                    
                    if ( opSN.equals("S") ) {
                    	clienteDAO.apagar(cliente.getIdCliente());
                    }
				} else {
					System.out.println("Cliente não encontrado.");					
				}
				
				break;
			case 4:
				System.out.println("LISTA DE CLIENTES");
				
				List<Cliente> listaDeClientes = clienteDAO.listar();
				
				for (Cliente c : listaDeClientes) {
					System.out.println(c);
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
