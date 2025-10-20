package View;

import controller.MetodosCampeonato;
import Model.Pessoa;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MetodosCampeonato controller = new MetodosCampeonato();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Sistema de Gerenciamento Esportivo ---");
            System.out.println("1. Adicionar Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Atualizar Pessoa");
            System.out.println("4. Deletar Pessoa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    adicionarPessoa(scanner, controller);
                    break;
                case 2:
                    listarPessoas(controller);
                    break;
                case 3:
                    atualizarPessoa(scanner, controller);
                    break;
                case 4:
                    deletarPessoa(scanner, controller);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        scanner.close();
    }

    private static void adicionarPessoa(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Nacionalidade: ");
            String nacionalidade = scanner.nextLine();
            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            Date dataNasc = Date.valueOf(scanner.nextLine());
            System.out.print("ID da Modalidade: ");
            int modId = Integer.parseInt(scanner.nextLine());
            System.out.print("Email: ");
            String email = scanner.nextLine();

            Pessoa novaPessoa = new Pessoa(nome, nacionalidade, dataNasc, modId, email);
            controller.inserirPessoa(novaPessoa);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void listarPessoas(MetodosCampeonato controller) {
        List<Pessoa> pessoas = controller.listarPessoas();
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("\n--- Lista de Pessoas ---");
            for (Pessoa p : pessoas) {
                System.out.println(p);
            }
        }
    }

    private static void atualizarPessoa(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da pessoa a ser atualizada: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Nova Nacionalidade: ");
            String nacionalidade = scanner.nextLine();
            System.out.print("Nova Data de Nascimento (AAAA-MM-DD): ");
            Date dataNasc = Date.valueOf(scanner.nextLine());
            System.out.print("Novo ID da Modalidade: ");
            int modId = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo Email: ");
            String email = scanner.nextLine();

            Pessoa pessoaAtualizada = new Pessoa(nome, nacionalidade, dataNasc, modId, email);
            pessoaAtualizada.setIdParticipante(id);
            controller.atualizarPessoa(pessoaAtualizada);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarPessoa(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da pessoa a ser deletada: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarPessoa(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }
}
