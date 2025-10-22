package controller;

import java.sql.*;
import java.util.*;
import Model.Arbitro;
import Model.BancodeDados;

public class MetodoArbitro {

    // CREATE
    public void inserir(Arbitro arbitro) throws SQLException {
        String sql = "INSERT INTO Arbitro (Partidas_Arbitradas, Pessoa) VALUES (?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, arbitro.getPartidasArbitradas());
            stmt.setInt(2, arbitro.getPessoa());

            stmt.executeUpdate();
            System.out.println("✅ Árbitro inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir árbitro: " + e.getMessage());
            throw e;
        }
    }

    // READ - listar todos
    public List<Arbitro> listarTodos() throws SQLException {
        List<Arbitro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Arbitro";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Arbitro a = new Arbitro();
                a.setIdArbitro(rs.getInt("ID_Arbitro"));
                a.setPartidasArbitradas(rs.getInt("Partidas_Arbitradas"));
                a.setPessoa(rs.getInt("Pessoa"));
                lista.add(a);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar árbitros: " + e.getMessage());
            throw e;
        }

        return lista;
    }

    // READ - buscar por ID
    public Arbitro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Arbitro WHERE ID_Arbitro = ?";
        Arbitro a = null;

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    a = new Arbitro();
                    a.setIdArbitro(rs.getInt("ID_Arbitro"));
                    a.setPartidasArbitradas(rs.getInt("Partidas_Arbitradas"));
                    a.setPessoa(rs.getInt("Pessoa"));
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar árbitro por ID: " + e.getMessage());
            throw e;
        }

        return a;
    }

    // UPDATE
    public void atualizar(Arbitro arbitro) throws SQLException {
        String sql = "UPDATE Arbitro SET Partidas_Arbitradas = ?, Pessoa = ? WHERE ID_Arbitro = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, arbitro.getPartidasArbitradas());
            stmt.setInt(2, arbitro.getPessoa());
            stmt.setInt(3, arbitro.getIdArbitro());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("✅ Árbitro atualizado com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum árbitro encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar árbitro: " + e.getMessage());
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
                System.out.println("✅ Árbitro deletado com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum árbitro encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao deletar árbitro: " + e.getMessage());
            throw e;
        }
    }
}
