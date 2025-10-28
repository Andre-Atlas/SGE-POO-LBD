package controller;

import java.sql.*;
import java.util.*;
import Model.Arbitro;
import Model.BancodeDados;

public class MetodoArbitro {

    // CREATE
    public void inserir(Arbitro arbitro) throws SQLException { //cria metodo para o inserir
        String sql = "INSERT INTO Arbitro (Partidas_Arbitradas, Pessoa, Competicoes_Participando) VALUES (?, ?, ?)"; //Codigo SQL("?" onde fica os valores)

        try (Connection conn = BancodeDados.conectar(); //
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, arbitro.getPartidasArbitradas());
            stmt.setInt(2, arbitro.getPessoa());

            if (arbitro.getCompeticoesParticipando() != null) { //Permite avaliar se tem ou não NULL
                stmt.setInt(3, arbitro.getCompeticoesParticipando());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER); //Se tiver seta NULL
            }

            stmt.executeUpdate();
            System.out.println("Árbitro inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir árbitro: " + e.getMessage());
            throw e; //Para o codigo no erro
        }
    }

    // READ - listar todos
    public List<Arbitro> listarTodos() throws SQLException {
        List<Arbitro> lista = new ArrayList<>(); //Cria lista temporaria para listar os itens
        String sql = "SELECT * FROM Arbitro";

        try (Connection conn = BancodeDados.conectar(); //Abre a conexão
             PreparedStatement stmt = conn.prepareStatement(sql); //Prepara o comando SQL
             ResultSet rs = stmt.executeQuery()) { //Executa o comando SQL

            while (rs.next()) {
                Arbitro a = new Arbitro();
                a.setIdArbitro(rs.getInt("ID_Arbitro"));
                a.setPartidasArbitradas(rs.getInt("Partidas_Arbitradas"));
                a.setPessoa(rs.getInt("Pessoa"));

                int comp = rs.getInt("Competicoes_Participando");
                if (rs.wasNull()) {
                    a.setCompeticoesParticipando(null);
                } else {
                    a.setCompeticoesParticipando(comp);
                }

                lista.add(a);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar árbitros: " + e.getMessage());
            throw e;
        }

        return lista;
    }

    // READ - buscar por ID
    public Arbitro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Arbitro WHERE ID_Arbitro = ?";
        Arbitro arbitro = null;

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); //Cria parametro para a busca por id

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    arbitro = new Arbitro();
                    arbitro.setIdArbitro(rs.getInt("ID_Arbitro"));
                    arbitro.setPartidasArbitradas(rs.getInt("Partidas_Arbitradas"));
                    arbitro.setPessoa(rs.getInt("Pessoa"));

                    int comp = rs.getInt("Competicoes_Participando");
                    if (rs.wasNull()) {
                        arbitro.setCompeticoesParticipando(null);
                    } else {
                        arbitro.setCompeticoesParticipando(comp);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar árbitro por ID: " + e.getMessage());
            throw e;
        }

        return arbitro;
    }

    // UPDATE
    public void atualizar(Arbitro arbitro) throws SQLException {
        String sql = "UPDATE Arbitro SET Partidas_Arbitradas = ?, Pessoa = ?, Competicoes_Participando = ? WHERE ID_Arbitro = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, arbitro.getPartidasArbitradas());
            stmt.setInt(2, arbitro.getPessoa());

            if (arbitro.getCompeticoesParticipando() != null) {
                stmt.setInt(3, arbitro.getCompeticoesParticipando());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }

            stmt.setInt(4, arbitro.getIdArbitro());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Árbitro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum árbitro encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar árbitro: " + e.getMessage());
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
                System.out.println("Árbitro deletado com sucesso!");
            } else {
                System.out.println("Nenhum árbitro encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar árbitro: " + e.getMessage());
            throw e;
        }
    }
}

//try-with-resources = garante os fechamentos dos comandos de banco
