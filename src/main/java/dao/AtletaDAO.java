package dao;

import Model.Atleta;
import Model.BancodeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtletaDAO {

    public void inserir(Atleta atleta) throws SQLException {
        String sql = "INSERT INTO Atleta (Peso, Altura, Modalidade, Pessoa) VALUES (?, ?, ?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDouble(1, atleta.getPeso());
            pstmt.setDouble(2, atleta.getAltura());
            pstmt.setString(3, atleta.getModalidade());
            pstmt.setInt(4, atleta.getPessoa());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    atleta.setID_Atleta(rs.getInt(1));
                }
            }
        }
    }

    public List<Atleta> listarTodos() throws SQLException {
        List<Atleta> atletas = new ArrayList<>();
        String sql = "SELECT * FROM Atleta";

        try (Connection conn = BancodeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Atleta a = new Atleta(
                        rs.getDouble("Peso"),
                        rs.getDouble("Altura"),
                        rs.getString("Modalidade"),
                        rs.getInt("Pessoa")
                );
                a.setID_Atleta(rs.getInt("ID_Atleta"));
                atletas.add(a);
            }
        }
        return atletas;
    }

    public boolean atualizar(Atleta atleta) throws SQLException {
        String sql = "UPDATE Atleta SET Peso = ?, Altura = ?, Modalidade = ?, Pessoa = ? WHERE ID_Atleta = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, atleta.getPeso());
            pstmt.setDouble(2, atleta.getAltura());
            pstmt.setString(3, atleta.getModalidade());
            pstmt.setInt(4, atleta.getPessoa());
            pstmt.setInt(5, atleta.getID_Atleta());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM Atleta WHERE ID_Atleta = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
