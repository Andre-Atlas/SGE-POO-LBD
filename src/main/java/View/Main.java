package View;

import Model.Arbitro;
import controller.MetodosCampeonato;
import controller.MetodoArbitro;
import Model.Pessoa;
import controller.MetodoCompeticao;
import Model.Competicao;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MetodosCampeonato controller = new MetodosCampeonato();
        MetodoArbitro arbitroController = new MetodoArbitro();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Sistema de Gerenciamento Esportivo ---");
            System.out.println("1. Adicionar Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Atualizar Pessoa");
            System.out.println("4. Deletar Pessoa");
            System.out.println("5. Tabela de Arbitros");
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
                case 5:
                    menuArbitros(scanner, arbitroController, controller);
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

    // MENU ÁRBITRO
    private static void menuArbitros(Scanner scanner, MetodoArbitro arbitroController, MetodosCampeonato pessoaController) {
        int oparbitro = -1;
        while (oparbitro != 0) {
            System.out.println("\n--- Tabela de Árbitros ---");
            System.out.println("1 - Inserir novo árbitro");
            System.out.println("2 - Listar todos os árbitros");
            System.out.println("3 - Buscar árbitro por ID");
            System.out.println("4 - Atualizar árbitro");
            System.out.println("5 - Deletar árbitro");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                oparbitro = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                continue;
            }

            switch (oparbitro) {
                case 1:
                    inserirArbitro(scanner, arbitroController, pessoaController);
                    break;
                case 2:
                    listarArbitros(arbitroController);
                    break;
                case 3:
                    buscarArbitroPorId(scanner, arbitroController);
                    break;
                case 4:
                    atualizarArbitro(scanner, arbitroController, pessoaController);
                    break;
                case 5:
                    deletarArbitro(scanner, arbitroController);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    // CRUD ÁRBITRO

    // CREATE - Inserir Árbitro
    private static void inserirArbitro(Scanner scanner, MetodoArbitro arbitroController, MetodosCampeonato pessoaController) {
        try {
            System.out.print("Partidas Arbitradas: ");
            int partidas = Integer.parseInt(scanner.nextLine());

            // Mostrar Pessoas já registradas
            System.out.println("\nPessoas já registradas:");
            List<Pessoa> pessoas = pessoaController.listarPessoas();
            if (pessoas.isEmpty()) {
                System.out.println("Nenhuma pessoa encontrada! Cadastre primeiro.");
                return;
            }
            for (Pessoa p : pessoas) {
                System.out.println("ID: " + p.getIdParticipante() + " | Nome: " + p.getNome());
            }

            System.out.print("\nDigite o ID da Pessoa: ");
            int pessoaId = Integer.parseInt(scanner.nextLine());

            // Mostrar Competições disponíveis
            System.out.println("\nCompetições disponíveis:");
            MetodoCompeticao cController = new MetodoCompeticao();
            List<Competicao> competicoes = cController.listarCompeticoes();

            Integer idCompeticao = null;
            if (competicoes.isEmpty()) {
                System.out.println(" Nenhuma competição encontrada! Árbitro será criado sem competição.");
            } else {
                for (Competicao c : competicoes) {
                    System.out.println("ID: " + c.getID_Competicao() + " | Nome: " + c.getNome());
                }

                System.out.print("\nDigite o ID da competição (ou 0 para nenhuma): ");
                int escolhaComp = Integer.parseInt(scanner.nextLine());
                if (escolhaComp != 0) {
                    idCompeticao = escolhaComp;
                }
            }

            // Criação e inserção do árbitro
            Arbitro novoArbitro = new Arbitro(partidas, pessoaId, idCompeticao);
            arbitroController.inserir(novoArbitro);

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite apenas números.");
        }
    }

    // READ - Listar todos os Árbitros
    private static void listarArbitros(MetodoArbitro arbitroController) {
        List<Arbitro> arbitros = arbitroController.listarTodos();
        if (arbitros.isEmpty()) {
            System.out.println("Nenhum árbitro cadastrado.");
        } else {
            System.out.println("\n--- Lista de Árbitros ---");
            for (Arbitro a : arbitros) {
                System.out.println(a);
            }
        }
    }

    // READ - Buscar Árbitro por ID
    private static void buscarArbitroPorId(Scanner scanner, MetodoArbitro arbitroController) {
        try {
            System.out.print("Digite o ID do árbitro: ");
            int id = Integer.parseInt(scanner.nextLine());

            Arbitro arbitro = arbitroController.buscarPorId(id);
            if (arbitro != null) {
                System.out.println("\n--- Árbitro Encontrado ---");
                System.out.println(arbitro);
            } else {
                System.out.println("Árbitro não encontrado com ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite apenas números.");
        }
    }

    // UPDATE - Atualizar Árbitro
    private static void atualizarArbitro(Scanner scanner, MetodoArbitro arbitroController, MetodosCampeonato pessoaController) {
        try {
            System.out.print("Digite o ID do árbitro a ser atualizado: ");
            int id = Integer.parseInt(scanner.nextLine());

            // Verificar se o árbitro existe
            Arbitro arbitroExistente = arbitroController.buscarPorId(id);
            if (arbitroExistente == null) {
                System.out.println("Árbitro não encontrado com ID: " + id);
                return;
            }

            System.out.println("Árbitro atual: " + arbitroExistente);
            System.out.println("\nPreencha os novos dados:");

            System.out.print("Novas Partidas Arbitradas: ");
            int partidas = Integer.parseInt(scanner.nextLine());

            // Mostrar Pessoas disponíveis
            System.out.println("\nPessoas disponíveis:");
            List<Pessoa> pessoas = pessoaController.listarPessoas();
            for (Pessoa p : pessoas) {
                System.out.println("ID: " + p.getIdParticipante() + " | Nome: " + p.getNome());
            }

            System.out.print("\nNovo ID da Pessoa: ");
            int pessoaId = Integer.parseInt(scanner.nextLine());

            // Mostrar Competições disponíveis
            System.out.println("\nCompetições disponíveis:");
            MetodoCompeticao cController = new MetodoCompeticao();
            List<Competicao> competicoes = cController.listarCompeticoes();

            Integer idCompeticao = null;
            if (competicoes.isEmpty()) {
                System.out.println(" Nenhuma competição encontrada! Árbitro será atualizado sem competição.");
            } else {
                for (Competicao c : competicoes) {
                    System.out.println("ID: " + c.getID_Competicao() + " | Nome: " + c.getNome());
                }

                System.out.print("\nNovo ID da competição (ou 0 para nenhuma): ");
                int escolhaComp = Integer.parseInt(scanner.nextLine());
                if (escolhaComp != 0) {
                    idCompeticao = escolhaComp;
                }
            }

            // Atualizar árbitro
            Arbitro arbitroAtualizado = new Arbitro(partidas, pessoaId, idCompeticao);
            arbitroAtualizado.setIdArbitro(id);
            arbitroController.atualizar(arbitroAtualizado);

            // Buscar nome da pessoa selecionada para mostrar na mensagem final
            String nomePessoa = "Não encontrado";
            for (Pessoa p : pessoas) {
                if (p.getIdParticipante() == pessoaId) {
                    nomePessoa = p.getNome();
                    break;
                }
            }

            // Buscar nome da competição selecionada para mostrar na mensagem final
            String nomeCompeticao = "Nenhuma";
            if (idCompeticao != null) {
                for (Competicao c : competicoes) {
                    if (c.getID_Competicao() == idCompeticao) {
                        nomeCompeticao = c.getNome();
                        break;
                    }
                }
            }

            // Atualizar árbitro
            arbitroController.atualizar(arbitroAtualizado);

            // Mensagem final com os novos dados
            System.out.println("   Novos dados do árbitro:");
            System.out.println("   ID: " + id);
            System.out.println("   Partidas Arbitradas: " + partidas);
            System.out.println("   Pessoa: " + nomePessoa + " (ID: " + pessoaId + ")");
            System.out.println("   Competição: " + nomeCompeticao +
                    (idCompeticao != null ? " (ID: " + idCompeticao + ")" : ""));

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite apenas números.");
        }
    }

    // DELETE - Deletar Árbitro
    private static void deletarArbitro(Scanner scanner, MetodoArbitro arbitroController) {
        try {
            System.out.print("Digite o ID do árbitro a ser deletado: ");
            int id = Integer.parseInt(scanner.nextLine());

            // Confirmar deleção
            System.out.print("Tem certeza que deseja deletar o árbitro ID " + id + "? (s/n): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                arbitroController.deletar(id);
            } else {
                System.out.println("Deleção cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite apenas números.");
        }
    }

    // CRUD PESSOA

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