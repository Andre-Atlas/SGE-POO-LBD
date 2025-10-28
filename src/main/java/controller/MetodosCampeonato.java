package controller;

// 1. Importar os Modelos
import Model.*;

// 2. Importar TODOS os DAOs que você acabou de criar
import dao.ArbitroDAO;
import dao.AtletaDAO;
import dao.CompeticaoDAO;
import dao.EquipeDAO;
import dao.ModalidadeDAO;
import dao.PartidaDAO;
import dao.PessoaDAO;
import dao.PremiacaoDAO;
import dao.Regras_ModalidadeDAO;

// 3. Importar utilitários e a exceção SQL
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MetodosCampeonato {

    // 4. REMOVER a conexão global.
    // private Connection connection;

    // 5. DECLARAR uma variável para cada DAO
    private PessoaDAO pessoaDAO;
    private ModalidadeDAO modalidadeDAO;
    private PremiacaoDAO premiacaoDAO;
    private Regras_ModalidadeDAO regrasModalidadeDAO;
    private EquipeDAO equipeDAO;
    private ArbitroDAO arbitroDAO;
    private AtletaDAO atletaDAO;
    private CompeticaoDAO competicaoDAO;
    private PartidaDAO partidaDAO;


    public MetodosCampeonato() {
        // 6. REMOVER a lógica de conexão do construtor
        // this.connection = BancodeDados.conectar(); ...

        // 7. INSTANCIAR cada DAO no construtor
        this.pessoaDAO = new PessoaDAO();
        this.modalidadeDAO = new ModalidadeDAO();
        this.premiacaoDAO = new PremiacaoDAO();
        this.regrasModalidadeDAO = new Regras_ModalidadeDAO();
        this.equipeDAO = new EquipeDAO();
        this.arbitroDAO = new ArbitroDAO();
        this.atletaDAO = new AtletaDAO();
        this.competicaoDAO = new CompeticaoDAO();
        this.partidaDAO = new PartidaDAO();
    }

    // --- Métodos Pessoa (Refatorados) ---

    public void inserirPessoa(Pessoa pessoa) {
        try {
            // 8. DELEGAR a lógica para o DAO
            pessoaDAO.inserir(pessoa);
            System.out.println("Pessoa '" + pessoa.getNome() + "' inserida com sucesso! ID=" + pessoa.getIdParticipante());
        } catch (SQLException e) {
            // 9. TRATAR o erro vindo do DAO
            System.err.println("Erro ao inserir pessoa: " + e.getMessage());
        }
    }

    public List<Pessoa> listarPessoas() {
        try {
            return pessoaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar pessoas: " + e.getMessage());
            return Collections.emptyList(); // Retorna uma lista vazia em caso de erro
        }
    }

    public void atualizarPessoa(Pessoa pessoa) {
        try {
            boolean atualizou = pessoaDAO.atualizar(pessoa);
            if (atualizou) {
                System.out.println("Pessoa atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma pessoa encontrada com o ID " + pessoa.getIdParticipante());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pessoa: " + e.getMessage());
        }
    }

    public void deletarPessoa(int id) {
        try {
            boolean deletou = pessoaDAO.deletar(id);
            if (deletou) {
                System.out.println("Pessoa deletada com sucesso!");
            } else {
                System.out.println("Nenhuma pessoa encontrada com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pessoa: " + e.getMessage());
        }
    }

    // --- Métodos Modalidade (Refatorados) ---

    public void inserirModalidade(Modalidade modalidade) {
        try {
            modalidadeDAO.inserir(modalidade);
            System.out.println("Modalidade " + modalidade.getNome() + " inserida com sucesso! ID=" + modalidade.getID_Modalidade());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir modalidade: " + e.getMessage());
        }
    }

    public List<Modalidade> listarModalidade() {
        try {
            return modalidadeDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar modalidades: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deletarModalidade(int id) {
        try {
            if (modalidadeDAO.deletar(id)) {
                System.out.println("Modalidade deletada com sucesso");
            } else {
                System.out.println("Nenhuma modalidade encontrada com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar modalidade: " + e.getMessage());
        }
    }

    public void atualizarModalidade(Modalidade modalidade) {
        try {
            if (modalidadeDAO.atualizar(modalidade)) {
                System.out.println("Modalidade atualizada com sucesso");
            } else {
                System.out.println("Nenhuma modalidade encontrada com ID " + modalidade.getID_Modalidade());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar modalidade " + e.getMessage());
        }
    }

    // --- Métodos Premiação (Refatorados) ---

    public void inserirPremiacao(Premiacao premiacao) {
        try {
            premiacaoDAO.inserir(premiacao);
            System.out.println("Premiação inserida com sucesso! ID=" + premiacao.getID_Premiacao());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir premiação: " + e.getMessage());
        }
    }

    public List<Premiacao> listarPremiacao() {
        try {
            return premiacaoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar premiações: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deletarPremiacao(int id) {
        try {
            if (premiacaoDAO.deletar(id)) {
                System.out.println("Premiação deletada com sucesso");
            } else {
                System.out.println("Nenhuma premiação encontrada com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar premiação: " + e.getMessage());
        }
    }

    public void atualizarPremiacao(Premiacao premiacao) {
        try {
            if (premiacaoDAO.atualizar(premiacao)) {
                System.out.println("Premiação atualizada com sucesso");
            } else {
                System.out.println("Nenhuma premiação encontrada com ID " + premiacao.getID_Premiacao());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar premiação " + e.getMessage());
        }
    }

    // --- Métodos Regras_Modalidade (Refatorados) ---

    public void inserirRegra(Regras_Modalidade regras) {
        try {
            regrasModalidadeDAO.inserir(regras);
            System.out.println("Regra inserida com sucesso! ID=" + regras.getCodigo_Regra());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir regras: " + e.getMessage());
        }
    }

    public List<Regras_Modalidade> listarRegra() {
        try {
            return regrasModalidadeDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar regras: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deletarRegras(int id) {
        try {
            if (regrasModalidadeDAO.deletar(id)) {
                System.out.println("Regra deletada com sucesso");
            } else {
                System.out.println("Nenhuma regra encontrada com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar regra: " + e.getMessage());
        }
    }

    public void atualizarRegra(Regras_Modalidade regras) {
        try {
            if (regrasModalidadeDAO.atualizar(regras)) {
                System.out.println("Regra atualizada com sucesso");
            } else {
                System.out.println("Nenhuma regra encontrada com ID " + regras.getCodigo_Regra());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar regra " + e.getMessage());
        }
    }

    public List<Regras_Modalidade> listarRegrasPorModalidade(int modalidadeId) {
        try {
            return regrasModalidadeDAO.buscarPorModalidadeId(modalidadeId);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar regras por modalidade: " + e.getMessage());
            // Retorna uma lista vazia em caso de erro
            return Collections.emptyList();
        }
    }

    // --- Métodos Equipe (Refatorados) ---

    public void inserirEquipe(Equipe equipe) {
        try {
            equipeDAO.inserir(equipe);
            System.out.println("Equipe inserida com sucesso! ID=" + equipe.getID_Equipe());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir equipe: " + e.getMessage());
        }
    }

    public List<Equipe> listarEquipes() {
        try {
            return equipeDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar equipes: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Equipe buscarEquipePorId(int id) {
        try {
            return equipeDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar equipe por ID: " + e.getMessage());
            return null;
        }
    }

    public void atualizarEquipe(Equipe equipe) {
        try {
            if (equipeDAO.atualizar(equipe)) {
                System.out.println("Equipe atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma equipe encontrada com o ID " + equipe.getID_Equipe());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar equipe: " + e.getMessage());
        }
    }

    public void deletarEquipe(int id) {
        try {
            if (equipeDAO.deletar(id)) {
                System.out.println("Equipe deletada com sucesso!");
            } else {
                System.out.println("Nenhuma equipe encontrada com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar equipe: " + e.getMessage());
        }
    }

    // --- Métodos Arbitro (Refatorados e Renomeados para consistência) ---

    public void inserirArbitro(Arbitro arbitro) {
        try {
            arbitroDAO.inserir(arbitro);
            System.out.println("Árbitro inserido com sucesso! ID=" + arbitro.getIdArbitro());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir árbitro: " + e.getMessage());
        }
    }

    public List<Arbitro> listarArbitros() {
        try {
            return arbitroDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar árbitros: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Arbitro buscarArbitroPorId(int id) {
        try {
            return arbitroDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar árbitro por ID: " + e.getMessage());
            return null;
        }
    }

    public void atualizarArbitro(Arbitro arbitro) {
        try {
            if (arbitroDAO.atualizar(arbitro)) {
                System.out.println("Árbitro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum árbitro encontrado com esse ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar árbitro: " + e.getMessage());
        }
    }

    public void deletarArbitro(int id) {
        try {
            if (arbitroDAO.deletar(id)) {
                System.out.println("Árbitro deletado com sucesso!");
            } else {
                System.out.println("Nenhum árbitro encontrado com esse ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar árbitro: " + e.getMessage());
        }
    }

    // --- Métodos para os DAOs restantes (você pode adicionar no seu menu se quiser) ---

    // Atleta
    public void inserirAtleta(Atleta atleta) {
        try {
            atletaDAO.inserir(atleta);
            System.out.println("Atleta inserido com sucesso! ID=" + atleta.getID_Atleta());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir atleta: " + e.getMessage());
        }
    }

    public List<Atleta> listarAtletas() {
        try {
            return atletaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar atletas: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void atualizarAtleta(Atleta atleta) {
        try {
            if (atletaDAO.atualizar(atleta)) {
                System.out.println("Atleta atualizado com sucesso!");
            } else {
                System.out.println("Nenhum atleta encontrado com o ID " + atleta.getID_Atleta());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar atleta: " + e.getMessage());
        }
    }

    public void deletarAtleta(int id) {
        try {
            if (atletaDAO.deletar(id)) {
                System.out.println("Atleta deletado com sucesso!");
            } else {
                System.out.println("Nenhum atleta encontrado com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar atleta: " + e.getMessage());
        }
    }

    // Competicao
    public void inserirCompeticao(Competicao competicao) {
        try {
            competicaoDAO.inserir(competicao);
            System.out.println("Competição inserida com sucesso! ID=" + competicao.getID_Competicao());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir competição: " + e.getMessage());
        }
    }

    public List<Competicao> listarCompeticoes() {
        try {
            return competicaoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar competições: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void atualizarCompeticao(Competicao competicao) {
        try {
            if (competicaoDAO.atualizar(competicao)) {
                System.out.println("Competição atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma competição encontrada com o ID " + competicao.getID_Competicao());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar competição: " + e.getMessage());
        }
    }

    public void deletarCompeticao(int id) {
        try {
            if (competicaoDAO.deletar(id)) {
                System.out.println("Competição deletada com sucesso!");
            } else {
                System.out.println("Nenhuma competição encontrada com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar competição: " + e.getMessage());
        }
    }

    // Partida
    public void inserirPartida(Partida partida) {
        try {
            partidaDAO.inserir(partida);
            System.out.println("Partida inserida com sucesso! ID=" + partida.getID_Partida());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir partida: " + e.getMessage());
        }
    }

    public List<Partida> listarPartidas() {
        try {
            return partidaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar partidas: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void atualizarPartida(Partida partida) {
        try {
            if (partidaDAO.atualizar(partida)) {
                System.out.println("Partida atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma partida encontrada com o ID " + partida.getID_Partida());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar partida: " + e.getMessage());
        }
    }

    public void deletarPartida(int id) {
        try {
            if (partidaDAO.deletar(id)) {
                System.out.println("Partida deletada com sucesso!");
            } else {
                System.out.println("Nenhuma partida encontrada com o ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar partida: " + e.getMessage());
        }
    }
}