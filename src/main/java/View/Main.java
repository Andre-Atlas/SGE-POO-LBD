package View;

import controller.MetodosCampeonato;
import Model.Pessoa;
import Model.Modalidade;
import Model.Premiacao;
import Model.Regras_Modalidade;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MetodosCampeonato controller = new MetodosCampeonato();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        // menu

        while (opcao != 0) {
            System.out.println("\n--- Sistema de Gerenciamento Esportivo ---");
            System.out.println("1. Pessoa");
            System.out.println("2. Modalidade");
            System.out.println("3. Premiação");
            System.out.println("4. Regras");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                continue;
            }

            int op = -1;
            switch (opcao) {
                case 1:
                    while (op != 0){
                        System.out.println("--------- Gerenciamento de Pessoa ----------");
                        System.out.println("1. Adicionar Pessoa");
                        System.out.println("2. Listar Pessoas");
                        System.out.println("3. Atualizar Pessoa");
                        System.out.println("4. Deletar Pessoa");
                        System.out.println("0. Voltar ao menu principal");
                        System.out.print("Escolha uma opção: ");

                        try {
                            op = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opção inválida. Por favor, insira um número.");
                            continue;
                        }

                        switch (op) {
                            case 0:
                                System.out.println("Saindo...");
                                break;
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
                            default:
                                System.out.println("Opção Inválida!");
                                break;
                            }
                    }
                    break;
                case 2:
                    while (op != 0){
                        System.out.println("--------- Gerenciamento de Modalidade ----------");
                        System.out.println("1. Adicionar Modalidade");
                        System.out.println("2. Listar Modalidades");
                        System.out.println("3. Atualizar Modalidade");
                        System.out.println("4. Deletar Modalidade");
                        System.out.println("0. Voltar ao menu principal");
                        System.out.print("Escolha uma opção: ");

                        try {
                            op = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opção inválida. Por favor, insira um número.");
                            continue;
                        }

                        switch (op) {
                            case 0:
                                System.out.println("Saindo...");
                                break;
                            case 1:
                                adicionarModalidade(scanner, controller);
                                break;
                            case 2:
                                listarModalidade(controller);
                                break;
                            case 3:
                                atualizarModalidade(scanner, controller);
                                break;
                            case 4:
                                deletarModalidade(scanner, controller);
                                break;
                            default:
                                System.out.println("Opção Inválida!");
                                break;
                        }
                    }
                    break;
                case 3:
                    while (op != 0){
                        System.out.println("--------- Gerenciamento de Premiação ----------");
                        System.out.println("1. Adicionar Premiação");
                        System.out.println("2. Listar Premiações");
                        System.out.println("3. Atualizar Premiação");
                        System.out.println("4. Deletar Premiação");
                        System.out.println("0. Voltar ao menu principal");
                        System.out.print("Escolha uma opção: ");

                        try {
                            op = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opção inválida. Por favor, insira um número.");
                            continue;
                        }

                        switch (op) {
                            case 0:
                                System.out.println("Saindo...");
                                break;
                            case 1:
                                adicionarPremiacao(scanner, controller);
                                break;
                            case 2:
                                listarPremiacao(controller);
                                break;
                            case 3:
                                atualizarPremiacao(scanner, controller);
                                break;
                            case 4:
                                deletarPremiacao(scanner, controller);
                                break;
                            default:
                                System.out.println("Opção Inválida!");
                                break;
                        }
                    }
                    break;
                case 4:
                    while (op != 0){
                        System.out.println("--------- Gerenciamento de Regras ----------");
                        System.out.println("1. Adicionar Regra");
                        System.out.println("2. Listar Regras");
                        System.out.println("3. Atualizar Regra");
                        System.out.println("4. Deletar Regra");
                        System.out.println("0. Voltar ao menu principal");
                        System.out.print("Escolha uma opção: ");

                        try {
                            op = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opção inválida. Por favor, insira um número.");
                            continue;
                        }

                        switch (op) {
                            case 0:
                                System.out.println("Saindo...");
                                break;
                            case 1:
                                adicionarRegra(scanner, controller);
                                break;
                            case 2:
                                listarRegra(controller);
                                break;
                            case 3:
                                atualizarRegra(scanner, controller);
                                break;
                            case 4:
                                deletarRegra(scanner, controller);
                                break;
                            default:
                                System.out.println("Opção Inválida!");
                                break;
                        }
                    }
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

    private static void adicionarModalidade(Scanner scanner, MetodosCampeonato controller){
        try {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Tipo: ");
                String tipo = scanner.nextLine();
                System.out.print("Regras Básicas: ");
                int ID_Regras_Basicas = Integer.parseInt(scanner.nextLine());

                Modalidade novaModalidade = new Modalidade(nome, tipo, ID_Regras_Basicas);
                controller.inserirModalidade(novaModalidade);
            } catch (Exception e) {
                System.err.println("Erro nos dados de entrada: " + e.getMessage());
            }
    }

    private static void listarModalidade(MetodosCampeonato controller){
        List<Modalidade> modalidades = controller.listarModalidade();
        if (modalidades.isEmpty()) {
            System.out.println("Nenhuma modalidade cadastrada.");
        } else {
            System.out.println("\n--- Lista de Modalidades ---");
            for (Modalidade m : modalidades) {
                System.out.println(m);
            }
        }
    }

    private static void atualizarModalidade(Scanner scanner, MetodosCampeonato controller){
        try {
            System.out.print("ID da modalidade a ser atualizada: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Tipo: ");
            String tipo = scanner.nextLine();
            System.out.print("Novas Regrase: ");
            int Regras_Basicas = Integer.parseInt(scanner.nextLine());

            Modalidade ModalidadeAtualizada = new Modalidade(nome, tipo, Regras_Basicas);
            ModalidadeAtualizada.setID_Modalidade(id);
            controller.atualizarModalidade(ModalidadeAtualizada);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarModalidade(Scanner scanner, MetodosCampeonato controller){
        try {
            System.out.print("ID da modalidade a ser deletada: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarModalidade(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }

    private static void adicionarPremiacao(Scanner scanner, MetodosCampeonato controller){
        try {
                System.out.print("Tipo: ");
                int tipo = Integer.parseInt(scanner.nextLine());
                System.out.print("Colocação: ");
                int colocacao = Integer.parseInt(scanner.nextLine());
                System.out.print("Valor: ");
                float valor = Float.parseFloat(scanner.nextLine());
                System.out.print("Competição: ");
                int competicao = Integer.parseInt(scanner.nextLine());

                Premiacao novaPremiacao = new Premiacao(tipo, colocacao, valor, competicao);
                controller.inserirPremiacao(novaPremiacao);
            } catch (Exception e) {
                System.err.println("Erro nos dados de entrada: " + e.getMessage());
            }
    }

    private static void listarPremiacao(MetodosCampeonato controller){
        List<Premiacao> premiacoes = controller.listarPremiacao();
        if (premiacoes.isEmpty()) {
            System.out.println("Nenhuma premiação cadastrada.");
        } else {
            System.out.println("\n--- Lista de Premiações ---");
            for (Premiacao p : premiacoes) {
                System.out.println(p);
            }
        }
    }

    private static void atualizarPremiacao(Scanner scanner, MetodosCampeonato controller){
        try {
            System.out.print("ID da premiação a ser atualizada: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo Tipo: ");
            int tipo = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Colocação: ");
            int colocacao = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo Valor: ");
            float valor = Float.parseFloat(scanner.nextLine());
            System.out.print("Nova Competição: ");
            int competicao = Integer.parseInt(scanner.nextLine());

            Premiacao PremiacaoAtualizada = new Premiacao(tipo, colocacao, valor, competicao);
            PremiacaoAtualizada.setID_Premiacao(id);
            controller.atualizarPremiacao(PremiacaoAtualizada);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarPremiacao(Scanner scanner, MetodosCampeonato controller){
        try {
            System.out.print("ID da premiação a ser deletada: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarPremiacao(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }


    private static void adicionarRegra(Scanner scanner, MetodosCampeonato controller){
        try {
                System.out.print("Inciso: ");
                String inciso = scanner.nextLine();
                System.out.print("Regra: ");
                String regra = scanner.nextLine();
                System.out.print("Modalidade: ");
                int modalidade = Integer.parseInt(scanner.nextLine());

                Regras_Modalidade novaRegra = new Regras_Modalidade(inciso, regra, modalidade);
                controller.inserirRegra(novaRegra);
            } catch (Exception e) {
                System.err.println("Erro nos dados de entrada: " + e.getMessage());
            }
    }

    private static void listarRegra(MetodosCampeonato controller){
        List<Regras_Modalidade> regras = controller.listarRegra();
        if (regras.isEmpty()) {
            System.out.println("Nenhuma regra cadastrada.");
        } else {
            System.out.println("\n--- Lista de Regras ---");
            for (Regras_Modalidade r : regras) {
                System.out.println(r);
            }
        }
    }

    private static void atualizarRegra(Scanner scanner, MetodosCampeonato controller){
        try {
            System.out.print("ID da regra a ser atualizada: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo Inciso: ");
            String inciso = scanner.nextLine();
            System.out.print("Nova Regra: ");
            String regra = scanner.nextLine();
            System.out.print("Nova Modalidade: ");
            int modalidade = Integer.parseInt(scanner.nextLine());

            Regras_Modalidade RegraAtualizada = new Regras_Modalidade(inciso, regra, modalidade);
            RegraAtualizada.setCodigo_Regra(id);
            controller.atualizarRegra(RegraAtualizada);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarRegra(Scanner scanner, MetodosCampeonato controller){
        try {
            System.out.print("ID da regra a ser deletada: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarRegras(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }
}
