package View;

import controller.MetodosCampeonato;
import Model.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MetodosCampeonato controller = new MetodosCampeonato();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Sistema de Gerenciamento Esportivo (Menu Principal) ---");
            System.out.println("1. Gerenciar Pessoas");
            System.out.println("2. Gerenciar Modalidades");
            System.out.println("3. Gerenciar Premiações");
            System.out.println("4. Gerenciar Regras de Modalidade");
            System.out.println("5. Gerenciar Equipes");
            System.out.println("6. Gerenciar Árbitros");
            System.out.println("7. Gerenciar Atletas");
            System.out.println("8. Gerenciar Competições");
            System.out.println("9. Gerenciar Partidas"); // Tabela Partida_Disputa
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
                    menuPessoas(scanner, controller);
                    break;
                case 2:
                    menuModalidades(scanner, controller);
                    break;
                case 3:
                    menuPremiacoes(scanner, controller);
                    break;
                case 4:
                    menuRegras(scanner, controller);
                    break;
                case 5:
                    menuEquipes(scanner, controller);
                    break;
                case 6:
                    menuArbitros(scanner, controller);
                    break;
                case 7:
                    menuAtletas(scanner, controller);
                    break;
                case 8:
                    menuCompeticoes(scanner, controller);
                    break;
                case 9:
                    menuPartidas(scanner, controller);
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



    private static void menuPessoas(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Pessoas ---");
            System.out.println("1. Adicionar Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Atualizar Pessoa");
            System.out.println("4. Deletar Pessoa");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
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
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void menuModalidades(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Modalidades ---");
            System.out.println("1. Adicionar Modalidade");
            System.out.println("2. Listar Modalidades");
            System.out.println("3. Atualizar Modalidade");
            System.out.println("4. Deletar Modalidade");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
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
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void menuPremiacoes(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Premiações ---");
            System.out.println("1. Adicionar Premiação");
            System.out.println("2. Listar Premiações");
            System.out.println("3. Atualizar Premiação");
            System.out.println("4. Deletar Premiação");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
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
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void menuRegras(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Regras de Modalidade ---");
            System.out.println("1. Adicionar Regra");
            System.out.println("2. Listar Regras");
            System.out.println("3. Atualizar Regra");
            System.out.println("4. Deletar Regra");
            System.out.println("5. Buscar Regras por ID da Modalidade"); // <-- NOVA OPÇÃO
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
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
                    case 5:
                        buscarRegrasPorModalidade(scanner, controller);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void menuEquipes(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Equipes ---");
            System.out.println("1. Adicionar Equipe");
            System.out.println("2. Listar Equipes");
            System.out.println("3. Atualizar Equipe");
            System.out.println("4. Deletar Equipe");
            System.out.println("5. Buscar Equipe por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        adicionarEquipe(scanner, controller);
                        break;
                    case 2:
                        listarEquipes(controller);
                        break;
                    case 3:
                        atualizarEquipe(scanner, controller);
                        break;
                    case 4:
                        deletarEquipe(scanner, controller);
                        break;
                    case 5:
                        buscarEquipePorId(scanner, controller);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void menuArbitros(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Árbitros ---");
            System.out.println("1. Adicionar Árbitro");
            System.out.println("2. Listar Árbitros");
            System.out.println("3. Atualizar Árbitro");
            System.out.println("4. Deletar Árbitro");
            System.out.println("5. Buscar Árbitro por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        adicionarArbitro(scanner, controller);
                        break;
                    case 2:
                        listarArbitros(controller);
                        break;
                    case 3:
                        atualizarArbitro(scanner, controller);
                        break;
                    case 4:
                        deletarArbitro(scanner, controller);
                        break;
                    case 5:
                        buscarArbitroPorId(scanner, controller);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void menuAtletas(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Atletas ---");
            System.out.println("1. Adicionar Atleta");
            System.out.println("2. Listar Atletas");
            System.out.println("3. Atualizar Atleta");
            System.out.println("4. Deletar Atleta");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        adicionarAtleta(scanner, controller);
                        break;
                    case 2:
                        listarAtletas(controller);
                        break;
                    case 3:
                        atualizarAtleta(scanner, controller);
                        break;
                    case 4:
                        deletarAtleta(scanner, controller);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void menuCompeticoes(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Competições ---");
            System.out.println("1. Adicionar Competição");
            System.out.println("2. Listar Competições");
            System.out.println("3. Atualizar Competição");
            System.out.println("4. Deletar Competição");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        adicionarCompeticao(scanner, controller);
                        break;
                    case 2:
                        listarCompeticoes(controller);
                        break;
                    case 3:
                        atualizarCompeticao(scanner, controller);
                        break;
                    case 4:
                        deletarCompeticao(scanner, controller);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private static void menuPartidas(Scanner scanner, MetodosCampeonato controller) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Partidas (Partida_Disputa) ---");
            System.out.println("1. Adicionar Partida");
            System.out.println("2. Listar Partidas");
            System.out.println("3. Atualizar Partida");
            System.out.println("4. Deletar Partida");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        adicionarPartida(scanner, controller);
                        break;
                    case 2:
                        listarPartidas(controller);
                        break;
                    case 3:
                        atualizarPartida(scanner, controller);
                        break;
                    case 4:
                        deletarPartida(scanner, controller);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
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
            System.out.print("ID das Regras Básicas: ");
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
            System.out.print("Novo ID de Regras: ");
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
            System.out.print("ID do Tipo (inteiro): ");
            int tipo = Integer.parseInt(scanner.nextLine());
            System.out.print("Colocação (inteiro): ");
            int colocacao = Integer.parseInt(scanner.nextLine());
            System.out.print("Valor (ex: 1500.50): ");
            float valor = Float.parseFloat(scanner.nextLine());
            System.out.print("ID da Competição: ");
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
            System.out.print("Novo ID do Tipo (inteiro): ");
            int tipo = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Colocação (inteiro): ");
            int colocacao = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo Valor (ex: 1500.50): ");
            float valor = Float.parseFloat(scanner.nextLine());
            System.out.print("Novo ID da Competição: ");
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
            System.out.print("Inciso (ex: 'Art. 1'): ");
            String inciso = scanner.nextLine();
            System.out.print("Descrição da Regra: ");
            String regra = scanner.nextLine();
            System.out.print("ID da Modalidade: ");
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
            System.out.print("ID (Código) da regra a ser atualizada: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo Inciso (ex: 'Art. 1'): ");
            String inciso = scanner.nextLine();
            System.out.print("Nova Descrição da Regra: ");
            String regra = scanner.nextLine();
            System.out.print("Novo ID da Modalidade: ");
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
            System.out.print("ID (Código) da regra a ser deletada: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarRegras(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }

    private static void buscarRegrasPorModalidade(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("Digite o ID da Modalidade para buscar as regras: ");
            int modalidadeId = Integer.parseInt(scanner.nextLine());

            List<Regras_Modalidade> regras = controller.listarRegrasPorModalidade(modalidadeId);

            if (regras.isEmpty()) {
                System.out.println("Nenhuma regra encontrada para o ID de modalidade " + modalidadeId);
            } else {
                System.out.println("\n--- Regras para Modalidade ID " + modalidadeId + " ---");
                for (Regras_Modalidade r : regras) {

                    System.out.println(r);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("ID inválido. Por favor, insira um número.");
        }
    }

    private static void adicionarArbitro(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("Partidas Arbitradas: ");
            int partidas = Integer.parseInt(scanner.nextLine());
            System.out.print("ID da Pessoa: ");
            int pessoaId = Integer.parseInt(scanner.nextLine());
            System.out.print("ID da Competição (ou deixe em branco se nulo): ");
            String compIdStr = scanner.nextLine();

            Integer competicaoId = null;
            if (!compIdStr.trim().isEmpty()) {
                competicaoId = Integer.parseInt(compIdStr);
            }

            Arbitro novoArbitro = new Arbitro(partidas, pessoaId, competicaoId);
            controller.inserirArbitro(novoArbitro);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void listarArbitros(MetodosCampeonato controller) {
        List<Arbitro> arbitros = controller.listarArbitros();
        if (arbitros.isEmpty()) {
            System.out.println("Nenhum árbitro cadastrado.");
        } else {
            System.out.println("\n--- Lista de Árbitros ---");
            for (Arbitro a : arbitros) {
                System.out.println(a);
            }
        }
    }

    private static void atualizarArbitro(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID do Árbitro a ser atualizado: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novas Partidas Arbitradas: ");
            int partidas = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID da Pessoa: ");
            int pessoaId = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID da Competição (ou deixe em branco se nulo): ");
            String compIdStr = scanner.nextLine();

            Integer competicaoId = null;
            if (!compIdStr.trim().isEmpty()) {
                competicaoId = Integer.parseInt(compIdStr);
            }

            Arbitro arbitroAtt = new Arbitro(partidas, pessoaId, competicaoId);
            arbitroAtt.setIdArbitro(id);
            controller.atualizarArbitro(arbitroAtt);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarArbitro(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID do árbitro a ser deletado: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarArbitro(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }

    private static void buscarArbitroPorId(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID do árbitro a ser buscado: ");
            int id = Integer.parseInt(scanner.nextLine());
            Arbitro a = controller.buscarArbitroPorId(id);
            if (a != null) {
                System.out.println(a);
            } else {
                System.out.println("Árbitro não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }

    private static void adicionarEquipe(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID do Atleta: ");
            int atleta = Integer.parseInt(scanner.nextLine());
            System.out.print("ID do Tecnico (Pessoa): ");
            int tecnico = Integer.parseInt(scanner.nextLine());
            System.out.print("ID da Equipe Tecnica: ");
            int equipeTecnica = Integer.parseInt(scanner.nextLine());
            System.out.print("ID da Modalidade: ");
            int modalidade = Integer.parseInt(scanner.nextLine());
            // Corrigido para Cede_
            System.out.print("Número da Sede (Cede_Numero): ");
            int cedeNumero = Integer.parseInt(scanner.nextLine());
            System.out.print("Quadra da Sede (Cede_Quadra): ");
            int cedeQuadra = Integer.parseInt(scanner.nextLine());
            System.out.print("Cidade da Sede (Cede_Cidade): ");
            int cedeCidade = Integer.parseInt(scanner.nextLine());

            Equipe novaEquipe = new Equipe(atleta, tecnico, equipeTecnica, modalidade, cedeNumero, cedeQuadra, cedeCidade);
            controller.inserirEquipe(novaEquipe);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void listarEquipes(MetodosCampeonato controller) {
        List<Equipe> equipes = controller.listarEquipes();
        if (equipes.isEmpty()) {
            System.out.println("Nenhuma equipe cadastrada.");
        } else {
            System.out.println("\n--- Lista de Equipes ---");
            for (Equipe e : equipes) {
                System.out.println(e.mostrarEquipe());
            }
        }
    }

    private static void atualizarEquipe(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da equipe a ser atualizada: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo ID do Atleta: ");
            int atleta = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID do Tecnico (Pessoa): ");
            int tecnico = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID da Equipe Tecnica: ");
            int equipeTecnica = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID da Modalidade: ");
            int modalidade = Integer.parseInt(scanner.nextLine());
            // Corrigido para Cede_
            System.out.print("Novo Número da Sede (Cede_Numero): ");
            int cedeNumero = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Quadra da Sede (Cede_Quadra): ");
            int cedeQuadra = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Cidade da Sede (Cede_Cidade): ");
            int cedeCidade = Integer.parseInt(scanner.nextLine());

            Equipe equipeAtt = new Equipe(atleta, tecnico, equipeTecnica, modalidade, cedeNumero, cedeQuadra, cedeCidade);
            equipeAtt.setID_Equipe(id);
            controller.atualizarEquipe(equipeAtt);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarEquipe(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da equipe a ser deletada: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarEquipe(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }

    private static void buscarEquipePorId(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da equipe a ser buscada: ");
            int id = Integer.parseInt(scanner.nextLine());
            Equipe e = controller.buscarEquipePorId(id);
            if (e != null) {
                System.out.println(e.mostrarEquipe());
            } else {
                System.out.println("Equipe não encontrada.");
            }
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }

    private static void adicionarAtleta(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("Peso (ex: 75.5): ");
            double peso = Double.parseDouble(scanner.nextLine());
            System.out.print("Altura (ex: 1.82): ");
            double altura = Double.parseDouble(scanner.nextLine());
            System.out.print("Modalidade (String, ex: 'Futebol de Campo'): ");
            String modalidade = scanner.nextLine();
            System.out.print("ID da Pessoa: ");
            int pessoaId = Integer.parseInt(scanner.nextLine());

            Atleta novoAtleta = new Atleta(peso, altura, modalidade, pessoaId);
            controller.inserirAtleta(novoAtleta);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void listarAtletas(MetodosCampeonato controller) {
        List<Atleta> atletas = controller.listarAtletas();
        if (atletas.isEmpty()) {
            System.out.println("Nenhum atleta cadastrado.");
        } else {
            System.out.println("\n--- Lista de Atletas ---");
            for (Atleta a : atletas) {
                System.out.println(a.mostrarAtleta());
            }
        }
    }

    private static void atualizarAtleta(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID do Atleta a ser atualizado: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo Peso (ex: 75.5): ");
            double peso = Double.parseDouble(scanner.nextLine());
            System.out.print("Nova Altura (ex: 1.82): ");
            double altura = Double.parseDouble(scanner.nextLine());
            System.out.print("Nova Modalidade (String, ex: 'Futebol de Campo'): ");
            String modalidade = scanner.nextLine();
            System.out.print("Novo ID da Pessoa: ");
            int pessoaId = Integer.parseInt(scanner.nextLine());

            Atleta atletaAtt = new Atleta(peso, altura, modalidade, pessoaId);
            atletaAtt.setID_Atleta(id);
            controller.atualizarAtleta(atletaAtt);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarAtleta(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID do atleta a ser deletado: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarAtleta(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }

    private static void adicionarCompeticao(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Número do Local: ");
            int localNum = Integer.parseInt(scanner.nextLine());
            System.out.print("Quadra do Local: ");
            int localQuadra = Integer.parseInt(scanner.nextLine());
            System.out.print("Cidade do Local: ");
            int localCidade = Integer.parseInt(scanner.nextLine());
            System.out.print("Entidade Organizadora: ");
            String entidade = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Temporada: ");
            int temporada = Integer.parseInt(scanner.nextLine());
            System.out.print("ID do Árbitro: ");
            int arbitroId = Integer.parseInt(scanner.nextLine());
            System.out.print("ID do Atleta: ");
            int atletaId = Integer.parseInt(scanner.nextLine());

            Competicao novaComp = new Competicao(nome, localNum, localQuadra, localCidade, entidade, ano, temporada, arbitroId, atletaId);
            controller.inserirCompeticao(novaComp);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void listarCompeticoes(MetodosCampeonato controller) {
        List<Competicao> competicoes = controller.listarCompeticoes();
        if (competicoes.isEmpty()) {
            System.out.println("Nenhuma competição cadastrada.");
        } else {
            System.out.println("\n--- Lista de Competições ---");
            for (Competicao c : competicoes) {
                System.out.println(c.mostrarCompeticao());
            }
        }
    }

    private static void atualizarCompeticao(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da Competição a ser atualizada: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Número do Local: ");
            int localNum = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Quadra do Local: ");
            int localQuadra = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Cidade do Local: ");
            int localCidade = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Entidade Organizadora: ");
            String entidade = scanner.nextLine();
            System.out.print("Novo Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Temporada: ");
            int temporada = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID do Árbitro: ");
            int arbitroId = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID do Atleta: ");
            int atletaId = Integer.parseInt(scanner.nextLine());

            Competicao compAtt = new Competicao(nome, localNum, localQuadra, localCidade, entidade, ano, temporada, arbitroId, atletaId);
            compAtt.setID_Competicao(id);
            controller.atualizarCompeticao(compAtt);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarCompeticao(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da competição a ser deletada: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarCompeticao(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }

    private static void adicionarPartida(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("Número do Local: ");
            int localNum = Integer.parseInt(scanner.nextLine());
            System.out.print("Quadra do Local: ");
            int localQuadra = Integer.parseInt(scanner.nextLine());
            System.out.print("Cidade do Local: ");
            int localCidade = Integer.parseInt(scanner.nextLine());
            System.out.print("Data da Partida (AAAA-MM-DD): ");
            Date data = Date.valueOf(scanner.nextLine());
            System.out.print("Hora da Partida (HH:MM:SS): ");
            Time hora = Time.valueOf(scanner.nextLine());
            System.out.print("ID da Equipe: ");
            int equipeId = Integer.parseInt(scanner.nextLine());
            System.out.print("ID da Modalidade: ");
            int modalidadeId = Integer.parseInt(scanner.nextLine());
            System.out.print("ID do Árbitro (ou deixe em branco se nulo): ");
            String arbitroIdStr = scanner.nextLine();

            Integer arbitroId = null;
            if (!arbitroIdStr.trim().isEmpty()) {
                arbitroId = Integer.parseInt(arbitroIdStr);
            }

            Partida novaPartida = new Partida(localNum, localQuadra, localCidade, data, hora, equipeId, modalidadeId, arbitroId);
            controller.inserirPartida(novaPartida);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void listarPartidas(MetodosCampeonato controller) {
        List<Partida> partidas = controller.listarPartidas();
        if (partidas.isEmpty()) {
            System.out.println("Nenhuma partida cadastrada.");
        } else {
            System.out.println("\n--- Lista de Partidas ---");
            for (Partida p : partidas) {
                System.out.println(p.mostrarPartida());
            }
        }
    }

    private static void atualizarPartida(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da Partida a ser atualizada: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo Número do Local: ");
            int localNum = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Quadra do Local: ");
            int localQuadra = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Cidade do Local: ");
            int localCidade = Integer.parseInt(scanner.nextLine());
            System.out.print("Nova Data da Partida (AAAA-MM-DD): ");
            Date data = Date.valueOf(scanner.nextLine());
            System.out.print("Nova Hora da Partida (HH:MM:SS): ");
            Time hora = Time.valueOf(scanner.nextLine());
            System.out.print("Novo ID da Equipe: ");
            int equipeId = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID da Modalidade: ");
            int modalidadeId = Integer.parseInt(scanner.nextLine());
            System.out.print("Novo ID do Árbitro (ou deixe em branco se nulo): ");
            String arbitroIdStr = scanner.nextLine();

            Integer arbitroId = null;
            if (!arbitroIdStr.trim().isEmpty()) {
                arbitroId = Integer.parseInt(arbitroIdStr);
            }

            Partida partidaAtt = new Partida(localNum, localQuadra, localCidade, data, hora, equipeId, modalidadeId, arbitroId);
            partidaAtt.setID_Partida(id);
            controller.atualizarPartida(partidaAtt);
        } catch (Exception e) {
            System.err.println("Erro nos dados de entrada: " + e.getMessage());
        }
    }

    private static void deletarPartida(Scanner scanner, MetodosCampeonato controller) {
        try {
            System.out.print("ID da partida a ser deletada: ");
            int id = Integer.parseInt(scanner.nextLine());
            controller.deletarPartida(id);
        } catch (Exception e) {
            System.err.println("ID inválido: " + e.getMessage());
        }
    }
}