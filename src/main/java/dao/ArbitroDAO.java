package dao;

import Model.Arbitro;
import Model.BancodeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArbitroDAO {

    public void inserir(Arbitro arbitro) throws SQLException {
        String sql = "INSERT INTO Arbitro (Partidas_Arbitradas, Pessoa, Competicoes_Participando) VALUES (?, ?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, arbitro.getPartidasArbitradas());
            stmt.setInt(2, arbitro.getPessoa());

            if (arbitro.getCompeticoesParticipando() != null) {
                stmt.setInt(3, arbitro.getCompeticoesParticipando());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    arbitro.setIdArbitro(rs.getInt(1));
                }
            }
        }
    }

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

                int comp = rs.getInt("Competicoes_Participando");
                if (rs.wasNull()) {
                    a.setCompeticoesParticipando(null);
                } else {
                    a.setCompeticoesParticipando(comp);
                }
                lista.add(a);
            }
        }
        return lista;
    }

    public Arbitro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Arbitro WHERE ID_Arbitro = ?";
        Arbitro arbitro = null;

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
        }
        return arbitro;
    }

    public boolean atualizar(Arbitro arbitro) throws SQLException {
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
            return linhasAfetadas > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM Arbitro WHERE ID_Arbitro = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }
}