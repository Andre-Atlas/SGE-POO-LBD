package View;

import Model.Arbitro;
import controller.MetodosCampeonato;
import controller.MetodoArbitro;
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
        MetodoArbitro arbitroController = new MetodoArbitro();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Sistema de Gerenciamento Esportivo ---");
            System.out.println("1. Adicionar Pessoa");
            System.out.println("2. Listar Pessoas");
            System.out.println("3. Atualizar Pessoa");
            System.out.println("4. Deletar Pessoa");
            System.out.println("5. Adicionar Modalidade");
            System.out.println("6. Listar Modalidade");
            System.out.println("7. Atualizar Modalidade");
            System.out.println("8. Deletar Modalidade");
            System.out.println("9. Adicionar Premiação");
            System.out.println("10. Listar Premiação");
            System.out.println("11. Atualizar Premiação");
            System.out.println("12. Deletar Premiação");
            System.out.println("17. Tabela de Arbitros");
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
                    adicionarModalidade(scanner,controller);
                    break;
                case 6:
                    listarModalidade(controller);
                    break;
                case 7:
                    atualizarModalidade(scanner, controller);
                    break;
                case 8:
                    deletarModalidade(scanner, controller);
                    break;
                case 9:
                    adicionarPremiacao(scanner, controller);
                    break;
                case 10:
                    listarPremiacao(controller);
                    break;
                case 11:
                    atualizarPremiacao(scanner, controller);
                    break;
                case 12:
                    deletarPremiacao(scanner, controller);
                    break;
                case 13:
                    adicionarRegra(scanner, controller);
                    break;
                case 14:
                    listarRegra(controller);
                    break;
                case 15:
                    atualizarRegra(scanner, controller);
                    break;
                case 16:
                    deletarRegra(scanner, controller);
                    break;
                case 17:
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
