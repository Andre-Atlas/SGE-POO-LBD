package dao;

import Model.BancodeDados;
import Model.Modalidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModalidadeDAO {

    public void inserir(Modalidade modalidade) throws SQLException {
        String sql = "INSERT INTO Modalidade (Nome, Tipo, Regras_Basicas) VALUES (?, ?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, modalidade.getNome());
            pstmt.setString(2, modalidade.getTipo());
            pstmt.setInt(3, modalidade.getRegras_Basicas());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    modalidade.setID_Modalidade(rs.getInt(1));
                }
            }
        }
    }

    public List<Modalidade> listarTodos() throws SQLException {
        List<Modalidade> modalidades = new ArrayList<>();
        String sql = "SELECT * FROM Modalidade";

        try (Connection conn = BancodeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Modalidade m = new Modalidade(
                        rs.getString("Nome"),
                        rs.getString("Tipo"),
                        rs.getInt("Regras_Basicas")
                );
                m.setID_Modalidade(rs.getInt("ID_Modalidade"));
                modalidades.add(m);
            }
        }
        return modalidades;
    }

    public boolean atualizar(Modalidade modalidade) throws SQLException {
        String sql = "UPDATE Modalidade SET Nome = ?, Tipo = ?, Regras_Basicas = ? WHERE ID_Modalidade = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, modalidade.getNome());
            pstmt.setString(2, modalidade.getTipo());
            pstmt.setInt(3, modalidade.getRegras_Basicas());
            pstmt.setInt(4, modalidade.getID_Modalidade());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM Modalidade WHERE ID_Modalidade = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
