package br.edu.up.view;

import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import br.edu.up.dao.AgendamentoDAO;
import br.edu.up.dao.AgendamentoProdutoDAO;
import br.edu.up.dao.AgendamentoServicoDAO;
import br.edu.up.dao.ClienteDAO;
import br.edu.up.dao.ProdutoDAO;
import br.edu.up.dao.ServicoDAO;
import br.edu.up.model.Agendamento;
import br.edu.up.model.AgendamentoProduto;
import br.edu.up.model.AgendamentoServico;
import br.edu.up.model.Cliente;
import br.edu.up.model.Produto;
import br.edu.up.model.Servico;

public class ViewAgendamento {
	public static void cadastro(Scanner sc) throws Exception {
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		AgendamentoServicoDAO agendamentoServicoDAO = new AgendamentoServicoDAO();
		AgendamentoProdutoDAO agendamentoProdutoDAO = new AgendamentoProdutoDAO();
		ServicoDAO servicoDAO = new ServicoDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO(); 
		int opcao = 0;

		do {
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("*            AGENDAMENTO                *");
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
				Agendamento novo = new Agendamento();
				ClienteDAO clienteDAO = new ClienteDAO();

				int idCliente;
				System.out.print("Informe o código do cliente: ");
				idCliente = sc.nextInt();
				novo.setCliente(clienteDAO.buscarPorId(idCliente));
				System.out.println("Cliente: " + novo.getCliente().getNome());

				sc.nextLine();
				System.out.print("Digite uma datae hora de início: ");
				String dataStrInicio = sc.nextLine();
				DateFormat dataI = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date dataInicio = dataI.parse(dataStrInicio);
				novo.setDataIni(dataInicio);

				System.out.print("Digite uma datae hora de finalização: ");
				String dataStrFim = sc.nextLine();
				DateFormat dataF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date dataFim = dataF.parse(dataStrFim);
				novo.setDataFim(dataFim);

				novo.setSituacao(1);

				String opSN = "";
				System.out.print("Deseja inserir serviços S/N?: ");
				opSN = sc.next();

				sc.nextLine();

				if (opSN.equals("S")) {
					int opcaoServico = 0;
					do {
						System.out.println("");
						System.out.println("*****************************************");
						System.out.println("*                                       *");
						System.out.println("*               SERVIÇOS                *");
						System.out.println("*                                       *");
						System.out.println("*****************************************");
						System.out.println("*                                       *");
						System.out.println("* 1 - ADICIONAR                         *");
						System.out.println("* 2 - REMOVER                           *");
						System.out.println("* 3 - LISTA DE SERVIÇOS                 *");
						System.out.println("* 4 - VOLTAR                            *");
						System.out.println("*                                       *");
						System.out.println("*****************************************");

						System.out.println("");
						System.out.print("Informe a opção: ");
						opcaoServico = sc.nextInt();
						System.out.println("");

						sc.nextLine();

						switch (opcaoServico) {
						case 1:
							int idServico;
							System.out.print("Informe o código do serviço: ");
							idServico = sc.nextInt();
							
							Servico servicoNovo = servicoDAO.buscarPorId(idServico);
							
							if ( servicoNovo != null ) {
								AgendamentoServico agendamentoServico = new AgendamentoServico();
								agendamentoServico.setSeq(novo.getProximaSeqServico());
								agendamentoServico.setServico(servicoNovo);
								agendamentoServico.setValor(agendamentoServico.getServico().getValor());
								novo.getServicos().add(agendamentoServico);

								System.out.println("");
								System.out.println(
										"Serviço " + agendamentoServico.getServico().getDescricao() + " adicionado!");
								System.out.println("");	
							} else {
								System.out.println("Serviço não encontrada.");
							}
							
							break;
						case 2:
							int seq;
							System.out.print("Informe a sequência do serviço a ser removido: ");
							seq = sc.nextInt();

							for (int i = 0; i < novo.getServicos().size(); i++) {
								if (novo.getServicos().get(i).getSeq() == seq) {
									novo.getServicos().remove(i);
									System.out.println("Serviço removido.");
								}
							}

							break;
						case 3:
							System.out.println("");
							System.out.println("SERVIÇOS");
							for (int i = 0; i < novo.getServicos().size(); i++) {
								System.out.println("Sequência: " + novo.getServicos().get(i).getSeq());
								System.out.println(novo.getServicos().get(i).getServico().toString());
							}
							System.out.println("");
							break;
						case 4:
							break;
						default:
							System.out.println("Opção informada inválida!");
						}
					} while (opcaoServico != 4);
				}

				System.out.print("Deseja inserir produtos S/N?: ");
				opSN = sc.next();

				sc.nextLine();

				if (opSN.equals("S")) {
					int opcaoProduto = 0;
					do {
						System.out.println("");
						System.out.println("*****************************************");
						System.out.println("*                                       *");
						System.out.println("*               PRODUTOS                *");
						System.out.println("*                                       *");
						System.out.println("*****************************************");
						System.out.println("*                                       *");
						System.out.println("* 1 - ADICIONAR                         *");
						System.out.println("* 2 - REMOVER                           *");
						System.out.println("* 3 - LISTA DE SERVIÇOS                 *");
						System.out.println("* 4 - VOLTAR                            *");
						System.out.println("*                                       *");
						System.out.println("*****************************************");

						System.out.println("");
						System.out.print("Informe a opção: ");
						opcaoProduto = sc.nextInt();
						System.out.println("");

						sc.nextLine();

						switch (opcaoProduto) {
						case 1:
							int idProduto;
							System.out.print("Informe o código do produto: ");
							idProduto = sc.nextInt();
							
							Produto produtoNovo = produtoDAO.buscarPorId(idProduto);
							
							if ( produtoNovo != null ) {
								AgendamentoProduto agendamentoProduto = new AgendamentoProduto();
								agendamentoProduto.setSeq(novo.getProximaSeqProduto());
								agendamentoProduto.setProduto(produtoNovo);
								agendamentoProduto.setValor(agendamentoProduto.getProduto().getValor());
								novo.getProdutos().add(agendamentoProduto);

								System.out.println("");
								System.out.println(
										"Produto " + agendamentoProduto.getProduto().getDescricao() + " adicionado!");
								System.out.println("");								
							} else {
								System.out.println("Produto não encontrado.");
							}
							
							break;
						case 2:
							int seq;
							System.out.print("Informe a sequência do produto a ser removido: ");
							seq = sc.nextInt();

							for (int i = 0; i < novo.getProdutos().size(); i++) {
								if (novo.getProdutos().get(i).getSeq() == seq) {
									novo.getProdutos().remove(i);
									System.out.println("Produto removido.");
								}
							}

							break;
						case 3:
							System.out.println("");
							System.out.println("PRODUTOS");
							for (int i = 0; i < novo.getProdutos().size(); i++) {
								System.out.println("Sequência: " + novo.getProdutos().get(i).getSeq());
								System.out.println(novo.getProdutos().get(i).getProduto().toString());
							}
							System.out.println("");
							break;
						case 4:
							break;
						default:
							System.out.println("Opção informada inválida!");
						}
					} while (opcaoProduto != 4);

				}

				agendamentoDAO.salvar(novo);
				break;
			case 2:
				int idAlterar;
				System.out.print("Informe o código do agendamento a ser alterado: ");
				idAlterar = sc.nextInt();

				sc.nextLine();

				Agendamento agendamentoAlterar = agendamentoDAO.buscarPorId(idAlterar);

				if (agendamentoAlterar != null) {

					String opASN = "";
					System.out
							.print("Deseja altera o cliente " + agendamentoAlterar.getCliente().getNome() + " S/N?: ");
					opASN = sc.next();

					sc.nextLine();

					if (opASN.equals("S")) {
						int idNovoCliente;
						System.out.print("Informe o código do novo cliente: ");
						idNovoCliente = sc.nextInt();
						ClienteDAO novoClienteDAO = new ClienteDAO();
						Cliente clienteNovo = novoClienteDAO.buscarPorId(idNovoCliente);
						if ( clienteNovo != null ) {
							agendamentoAlterar.setCliente(clienteNovo);
						} else {
							System.out.println("Cliente não encontrado. Alteração não efetuda.");
						}
					}

					System.out.print("Deseja altera data e hora de início e fim S/N?: ");
					opASN = sc.next();

					sc.nextLine();

					if (opASN.equals("S")) {
						System.out.print("Digite a nova data e hora de início: ");
						String dataNStrInicio = sc.nextLine();
						DateFormat dataNI = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date dataNInicio = dataNI.parse(dataNStrInicio);
						agendamentoAlterar.setDataIni(dataNInicio);

						System.out.print("Digite uma datae hora de finalização: ");
						String dataNStrFim = sc.nextLine();
						DateFormat dataNF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date dataNFim = dataNF.parse(dataNStrFim);
						agendamentoAlterar.setDataFim(dataNFim);
					}

					System.out.print("Deseja altera os serviços S/N?: ");
					opASN = sc.next();

					sc.nextLine();

					if (opASN.equals("S")) {
						int opcaoServico = 0;
						do {
							System.out.println("");
							System.out.println("*****************************************");
							System.out.println("*                                       *");
							System.out.println("*               SERVIÇOS                *");
							System.out.println("*                                       *");
							System.out.println("*****************************************");
							System.out.println("*                                       *");
							System.out.println("* 1 - ADICIONAR                         *");
							System.out.println("* 2 - REMOVER                           *");
							System.out.println("* 3 - LISTA DE SERVIÇOS                 *");
							System.out.println("* 4 - VOLTAR                            *");
							System.out.println("*                                       *");
							System.out.println("*****************************************");

							System.out.println("");
							System.out.print("Informe a opção: ");
							opcaoServico = sc.nextInt();
							System.out.println("");

							sc.nextLine();

							switch (opcaoServico) {
							case 1:
								int idServico;
								System.out.print("Informe o código do serviço: ");
								idServico = sc.nextInt();
								AgendamentoServico agendamentoServico = new AgendamentoServico();
								agendamentoServico.setSeq(agendamentoAlterar.getProximaSeqServico());
								agendamentoServico.setServico(servicoDAO.buscarPorId(idServico));
								agendamentoServico.setValor(agendamentoServico.getServico().getValor());
								agendamentoAlterar.getServicos().add(agendamentoServico);

								System.out.println("");
								System.out.println(
										"Serviço " + agendamentoServico.getServico().getDescricao() + " adicionado!");
								System.out.println("");
								break;
							case 2:
								int seq;
								System.out.print("Informe a sequência do serviço a ser removido: ");
								seq = sc.nextInt();

								for (int i = 0; i < agendamentoAlterar.getServicos().size(); i++) {
									if (agendamentoAlterar.getServicos().get(i).getSeq() == seq) {
										agendamentoServicoDAO.apagar(agendamentoAlterar.getServicos().get(i).getId());
										agendamentoAlterar.getServicos().remove(i);
										System.out.println("Serviço removido.");
									}
								}

								break;
							case 3:
								System.out.println("");
								System.out.println("SERVIÇOS");
								for (int i = 0; i < agendamentoAlterar.getServicos().size(); i++) {
									System.out
											.println("Sequência: " + agendamentoAlterar.getServicos().get(i).getSeq());
									System.out.println(agendamentoAlterar.getServicos().get(i).getServico().toString());
								}
								System.out.println("");
								break;
							case 4:
								break;
							default:
								System.out.println("Opção informada inválida!");
							}
						} while (opcaoServico != 4);
					}

					System.out.print("Deseja altera os produtos S/N?: ");
					opASN = sc.next();

					sc.nextLine();

					if (opASN.equals("S")) {
						int opcaoProduto = 0;
						do {
							System.out.println("");
							System.out.println("*****************************************");
							System.out.println("*                                       *");
							System.out.println("*               PRODUTOS                *");
							System.out.println("*                                       *");
							System.out.println("*****************************************");
							System.out.println("*                                       *");
							System.out.println("* 1 - ADICIONAR                         *");
							System.out.println("* 2 - REMOVER                           *");
							System.out.println("* 3 - LISTA DE SERVIÇOS                 *");
							System.out.println("* 4 - VOLTAR                            *");
							System.out.println("*                                       *");
							System.out.println("*****************************************");

							System.out.println("");
							System.out.print("Informe a opção: ");
							opcaoProduto = sc.nextInt();
							System.out.println("");
							
							sc.nextLine();

							switch (opcaoProduto) {
							case 1:
								int idProduto;
								System.out.print("Informe o código do produto: ");
								idProduto = sc.nextInt();
								AgendamentoProduto agendamentoProduto = new AgendamentoProduto();
								agendamentoProduto.setSeq(agendamentoAlterar.getProximaSeqProduto());
								agendamentoProduto.setProduto(produtoDAO.buscarPorId(idProduto));
								agendamentoProduto.setValor(agendamentoProduto.getProduto().getValor());
								agendamentoAlterar.getProdutos().add(agendamentoProduto);

								System.out.println("");
								System.out.println(
										"Produto " + agendamentoProduto.getProduto().getDescricao() + " adicionado!");
								System.out.println("");
								break;
							case 2:
								int seq;
								System.out.print("Informe a sequência do produto a ser removido: ");
								seq = sc.nextInt();

								for (int i = 0; i < agendamentoAlterar.getProdutos().size(); i++) {
									if (agendamentoAlterar.getProdutos().get(i).getSeq() == seq) {
										agendamentoProdutoDAO.apagar(agendamentoAlterar.getProdutos().get(i).getId());
										agendamentoAlterar.getProdutos().remove(i);
										System.out.println("Produto removido.");
									}
								}

								break;
							case 3:
								System.out.println("");
								System.out.println("PRODUTOS");
								for (int i = 0; i < agendamentoAlterar.getProdutos().size(); i++) {
									System.out
											.println("Sequência: " + agendamentoAlterar.getProdutos().get(i).getSeq());
									System.out.println(agendamentoAlterar.getProdutos().get(i).getProduto().toString());
								}
								System.out.println("");
								break;
							case 4:
								break;
							default:
								System.out.println("Opção informada inválida!");
							}
						} while (opcaoProduto != 4);
					}

					agendamentoDAO.atualizar(agendamentoAlterar);
				} else {
					System.out.println("Agendamento não encontrado.");
				}

				break;
			case 3:
				int idExcluir;
				System.out.print("Informe o código do agendamento a ser excluído: ");
				idExcluir = sc.nextInt();
				
				Agendamento AgendamentoExcluir = agendamentoDAO.buscarPorId(idExcluir);

				if ( AgendamentoExcluir != null ) {
					agendamentoDAO.apagar(idExcluir);
				} else {
					System.out.println("Agendamento não encontrado.");
				}
				
				break;
			case 4:
				System.out.println("LISTA DE AGENDAMENTOS");

				List<Agendamento> listaDeAgendamentos = agendamentoDAO.listar();

				for (Agendamento c : listaDeAgendamentos) {
					System.out.println("");
					System.out.println("Código: " + c.getIdAgendamento());
					System.out.println("Cliente: " + c.getCliente().getNome());
					System.out.println("Telefone: " + c.getCliente().getfone() );
					System.out.println("Data hora início: " + c.getDataIni());
					System.out.println("Data hora final: " + c.getDataFim());

					System.out.println("Serviço(s): ");
					for (int i = 0; i < c.getServicos().size(); i++) {
						System.out.println(c.getServicos().get(i).getServico().toString());
					}

					System.out.println("Produto(s): ");
					for (int i = 0; i < c.getProdutos().size(); i++) {
						System.out.println(c.getProdutos().get(i).getProduto().toString());
					}
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
