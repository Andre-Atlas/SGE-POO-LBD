package dao;

import Model.BancodeDados;
import Model.Premiacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PremiacaoDAO {

    public void inserir(Premiacao premiacao) throws SQLException {
        // CORRIGIDO: "Premiação" com "ç"
        String sql = "INSERT INTO Premiação (Tipo, Colocação, Valor, Competição) VALUES (?, ?, ?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, premiacao.getTipo());
            pstmt.setInt(2, premiacao.getColocação());
            pstmt.setFloat(3, premiacao.getValor());
            pstmt.setInt(4, premiacao.getCompetição());
            pstmt.executeUpdate();

            // Busca o ID gerado, já que a PK é adicionada via ALTER TABLE
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    premiacao.setID_Premiacao(rs.getInt(1));
                }
            }
        }
    }

    public List<Premiacao> listarTodos() throws SQLException {
        // CORRIGIDO: "Premiação" com "ç"
        String sql = "SELECT * FROM Premiação";
        List<Premiacao> premiacoes = new ArrayList<>();

        try (Connection conn = BancodeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Premiacao pre = new Premiacao(
                        rs.getInt("Tipo"),
                        rs.getInt("Colocação"),
                        rs.getFloat("Valor"),
                        rs.getInt("Competição")
                );
                pre.setID_Premiacao(rs.getInt("ID_Premiação")); // Corrigido nome da coluna
                premiacoes.add(pre);
            }
        }
        return premiacoes;
    }

    public boolean atualizar(Premiacao premiacao) throws SQLException {
        // CORRIGIDO: "Premiação" com "ç"
        String sql = "UPDATE Premiação SET Tipo = ?, Colocação = ?, Valor = ?, Competição = ? WHERE ID_Premiação = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, premiacao.getTipo());
            pstmt.setInt(2, premiacao.getColocação());
            pstmt.setFloat(3, premiacao.getValor());
            pstmt.setInt(4, premiacao.getCompetição());
            pstmt.setInt(5, premiacao.getID_Premiacao());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        // CORRIGIDO: "Premiação" com "ç"
        String sql = "DELETE FROM Premiação WHERE ID_Premiação = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}