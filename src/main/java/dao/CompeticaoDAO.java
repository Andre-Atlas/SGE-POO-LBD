package dao;

import Model.BancodeDados;
import Model.Competicao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompeticaoDAO {

    public void inserir(Competicao competicao) throws SQLException {
        // CORRIGIDO: "Competição" com "ç" e colunas do SQL
        String sql = "INSERT INTO Competição (Nome, Local_Numero, Local_Quadra, Local_Cidade, Entidade_Organizadora, Ano, Temporada, Arbitro, Atleta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, competicao.getNome());
            pstmt.setInt(2, competicao.getLocal_Numero());
            pstmt.setInt(3, competicao.getLocal_Quadra());
            pstmt.setInt(4, competicao.getLocal_Cidade());
            pstmt.setString(5, competicao.getEntidade_Organizadora());
            pstmt.setInt(6, competicao.getAno());
            pstmt.setInt(7, competicao.getTemporada());
            pstmt.setInt(8, competicao.getArbitro());
            pstmt.setInt(9, competicao.getAtleta());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    competicao.setID_Competicao(rs.getInt(1));
                }
            }
        }
    }

    public List<Competicao> listarTodos() throws SQLException {
        List<Competicao> competicoes = new ArrayList<>();
        // CORRIGIDO: "Competição" com "ç"
        String sql = "SELECT * FROM Competição";

        try (Connection conn = BancodeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Competicao c = new Competicao(
                        rs.getString("Nome"),
                        rs.getInt("Local_Numero"),
                        rs.getInt("Local_Quadra"),
                        rs.getInt("Local_Cidade"),
                        rs.getString("Entidade_Organizadora"),
                        rs.getInt("Ano"),
                        rs.getInt("Temporada"),
                        rs.getInt("Arbitro"),
                        rs.getInt("Atleta")
                );
                c.setID_Competicao(rs.getInt("ID_Competição"));
                competicoes.add(c);
            }
        }
        return competicoes;
    }

    public boolean atualizar(Competicao competicao) throws SQLException {
        // CORRIGIDO: "Competição" com "ç" e colunas do SQL
        String sql = "UPDATE Competição SET Nome = ?, Local_Numero = ?, Local_Quadra = ?, Local_Cidade = ?, Entidade_Organizadora = ?, Ano = ?, Temporada = ?, Arbitro = ?, Atleta = ? WHERE ID_Competição = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, competicao.getNome());
            pstmt.setInt(2, competicao.getLocal_Numero());
            pstmt.setInt(3, competicao.getLocal_Quadra());
            pstmt.setInt(4, competicao.getLocal_Cidade());
            pstmt.setString(5, competicao.getEntidade_Organizadora());
            pstmt.setInt(6, competicao.getAno());
            pstmt.setInt(7, competicao.getTemporada());
            pstmt.setInt(8, competicao.getArbitro());
            pstmt.setInt(9, competicao.getAtleta());
            pstmt.setInt(10, competicao.getID_Competicao());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        // CORRIGIDO: "Competição" com "ç"
        String sql = "DELETE FROM Competição WHERE ID_Competição = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}