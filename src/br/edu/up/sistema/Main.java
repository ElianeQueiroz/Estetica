package br.edu.up.sistema;

import java.util.Locale;
import java.util.Scanner;

import br.edu.up.view.ViewAgendamento;
import br.edu.up.view.ViewCadastro;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		int opcao = 0;

		do {
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("*               ESTÉTICA                *");
			System.out.println("*                                       *");
			System.out.println("*****************************************");
			System.out.println("*                                       *");
			System.out.println("* 1 - CADASTRO                          *");
			System.out.println("* 2 - AGENDAMENTO                       *");
			System.out.println("* 3 - SAIR                              *");
			System.out.println("*                                       *");
			System.out.println("*****************************************");

			System.out.println("");
			System.out.print("Informe a opção: ");
			opcao = sc.nextInt();
			System.out.println("");

			switch (opcao) {
			case 1:
				ViewCadastro.cadastro(sc);
				break;
			case 2:
				ViewAgendamento.cadastro(sc);
				break;
			case 3:
				break;
			default:
				System.out.println("Opção informada inválida!");
			}

			System.out.println("");

		} while (opcao != 3);

		sc.close();
	}
}
