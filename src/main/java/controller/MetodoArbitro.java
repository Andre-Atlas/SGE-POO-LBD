package controller;

import java.sql.*;
import java.util.*;
import Model.Arbitro;
import Model.BancodeDados;

public class MetodoArbitro {
    private Connection connection;

    public MetodoArbitro() {
        this.connection = BancodeDados.conectar();
        if (this.connection == null) {
            throw new RuntimeException("Falha na conexão com o banco de dados");
        }
    }

    // CREATE
    public void inserir(Arbitro arbitro) {
        String sql = "INSERT INTO Arbitro (Partidas_Arbitradas, Pessoa, Competicoes_Participando) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, arbitro.getPartidasArbitradas());
            stmt.setInt(2, arbitro.getPessoa());

            if (arbitro.getCompeticoesParticipando() != null) {
                stmt.setInt(3, arbitro.getCompeticoesParticipando());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }

            stmt.executeUpdate();
            System.out.println("Árbitro inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir árbitro: " + e.getMessage());
            // NÃO relançar a exceção - seguir o mesmo padrão do MetodosCampeonato
        }
    }

    // READ - listar todos
    public List<Arbitro> listarTodos() {
        List<Arbitro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Arbitro";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
        }

        return lista;
    }

    // READ - buscar por ID
    public Arbitro buscarPorId(int id) {
        String sql = "SELECT * FROM Arbitro WHERE ID_Arbitro = ?";
        Arbitro arbitro = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

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
        }

        return arbitro;
    }

    // UPDATE
    public void atualizar(Arbitro arbitro) {
        String sql = "UPDATE Arbitro SET Partidas_Arbitradas = ?, Pessoa = ?, Competicoes_Participando = ? WHERE ID_Arbitro = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
        }
    }

    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM Arbitro WHERE ID_Arbitro = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Árbitro deletado com sucesso!");
            } else {
                System.out.println("Nenhum árbitro encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar árbitro: " + e.getMessage());
        }
    }
}