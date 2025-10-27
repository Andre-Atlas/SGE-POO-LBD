package controller;

import java.sql.*; //Importação para usar a comandos SQL
import java.util.*; //Importação para usar a Array
import Model.Arbitro; //Importação para usar meu model
import Model.BancodeDados; //Importação para usar a conexão

public class MetodoArbitro {

    // CREATE - INSERT INTO
    public void inserir(Arbitro arbitro) throws SQLException { //Prepara para usar o SQL
        String sql = "INSERT INTO Arbitro (Partidas_Arbitradas, Pessoa) VALUES (?, ?)"; //Comando SQL (? - onde vai ser substituido)

        try (Connection conn = BancodeDados.conectar(); //Abre a conexão com o BD
             PreparedStatement stmt = conn.prepareStatement(sql)) { //Prepara o camando SQL (o comando prepareStatement exige uma valor em ?)

            stmt.setInt(1, arbitro.getPartidasArbitradas()); //Define os valores
            stmt.setInt(2, arbitro.getPessoa());

            stmt.executeUpdate(); //Executa o SQL
            System.out.println("Árbitro inserido com sucesso!!!"); //Retorno para o usuario
        } catch (SQLException e) {
            System.err.println("Não foi possivel inserir o árbitro" + e.getMessage());
            throw e; //Repasse de erro (para fazer o sistema parar no erro)
        }
    }

    // READ - listar todos
    public List<Arbitro> listarTodos() throws SQLException {
        List<Arbitro> lista = new ArrayList<>(); //Cria uma lista para listar os dados
        String sql = "SELECT * FROM Arbitro"; //Cria o comando SQL

        try (Connection conn = BancodeDados.conectar(); //Abre a conexão com o BD
             PreparedStatement stmt = conn.prepareStatement(sql); //Prepara o comando
             ResultSet rs = stmt.executeQuery()) { //Aplica o SQL

            while (rs.next()) { //Faz um SELECT e percorre o resultado
                Arbitro a = new Arbitro();
                a.setIdArbitro(rs.getInt("ID_Arbitro")); // Lê o registro e mostra
                a.setPartidasArbitradas(rs.getInt("Partidas_Arbitradas"));
                a.setPessoa(rs.getInt("Pessoa"));
                lista.add(a); //Coloca cada Árbitro na lista
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage()); //Retorno de erro
            throw e;
        }

        return lista; //Retorno para o usuario
    }

    // READ - buscar por ID
    public Arbitro buscarPorId(int id) throws SQLException { //Mesma coisa do anterior mas com filtro
        String sql = "SELECT * FROM Arbitro WHERE ID_Arbitro = ?";
        Arbitro arbitro = null; //Onde será armazenado

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); //Parametro de contagem para achar o ID

            try (ResultSet rs = stmt.executeQuery()) { //Executa o SQL
                if (rs.next()) {
                    arbitro = new Arbitro(); //Cria objeto para mostragem (evita a sobreescrita)
                    arbitro.setIdArbitro(rs.getInt("ID_Arbitro"));
                    arbitro.setPartidasArbitradas(rs.getInt("Partidas_Arbitradas"));
                    arbitro.setPessoa(rs.getInt("Pessoa"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar IDs: " + e.getMessage());
            throw e;
        }

        return arbitro;
    }

    // UPDATE
    public void atualizar(Arbitro arbitro) throws SQLException {
        String sql = "UPDATE Arbitro SET Partidas_Arbitradas = ?, Pessoa = ? WHERE ID_Arbitro = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, arbitro.getPartidasArbitradas());
            stmt.setInt(2, arbitro.getPessoa());
            stmt.setInt(3, arbitro.getIdArbitro());

            int linhasAfetadas = stmt.executeUpdate(); //Executa o SQL e retorna as linhas
            if (linhasAfetadas > 0) {
                System.out.println("Árbitro atualizado com sucesso!!!");
            } else {
                System.out.println("Nenhum ID encontrado");
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel atualizar: " + e.getMessage());
            throw e;
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Arbitro WHERE ID_Arbitro = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Árbitro deletado com sucesso!!!");
            } else {
                System.out.println("Nenhum ID encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel deletar: " + e.getMessage());
            throw e;
        }
    }
}
