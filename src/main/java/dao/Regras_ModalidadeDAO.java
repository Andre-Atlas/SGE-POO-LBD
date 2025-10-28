package dao;

import Model.BancodeDados;
import Model.Regras_Modalidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Regras_ModalidadeDAO {

    public void inserir(Regras_Modalidade regras) throws SQLException {
        // CORREÇÃO: Removido Codigo_Regra, assumindo que é AUTO_INCREMENT.
        // O construtor do seu Model também não o inclui.
        String sql = "INSERT INTO regras_modalidade (Inciso, Regra, Modalidade) VALUES (?, ?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, regras.getInciso());
            pstmt.setString(2, regras.getRegra()); // CORREÇÃO: Estava getInciso() no seu controller
            pstmt.setInt(3, regras.getModalidade());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    regras.setCodigo_Regra(rs.getInt(1));
                }
            }
        }
    }

    public List<Regras_Modalidade> listarTodos() throws SQLException {
        String sql = "SELECT * FROM regras_modalidade";
        List<Regras_Modalidade> regras = new ArrayList<>();

        try (Connection conn = BancodeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Regras_Modalidade r = new Regras_Modalidade(
                        rs.getString("Inciso"),
                        rs.getString("Regra"),
                        rs.getInt("Modalidade")
                );
                r.setCodigo_Regra(rs.getInt("Codigo_Regra"));
                regras.add(r);
            }
        }
        return regras;
    }

    public boolean atualizar(Regras_Modalidade regras) throws SQLException {
        String sql = "UPDATE regras_modalidade SET Inciso = ?, Regra = ?, Modalidade = ? WHERE Codigo_Regra = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, regras.getInciso());
            pstmt.setString(2, regras.getRegra());
            pstmt.setInt(3, regras.getModalidade());
            pstmt.setInt(4, regras.getCodigo_Regra());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM regras_modalidade WHERE Codigo_Regra = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public List<Regras_Modalidade> buscarPorModalidadeId(int modalidadeId) throws SQLException {
        List<Regras_Modalidade> regras = new ArrayList<>();
        String sql = "SELECT * FROM regras_modalidade WHERE Modalidade = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, modalidadeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Regras_Modalidade r = new Regras_Modalidade(
                            rs.getString("Inciso"),
                            rs.getString("Regra"),
                            rs.getInt("Modalidade")
                    );
                    r.setCodigo_Regra(rs.getInt("Codigo_Regra"));
                    regras.add(r);
                }
            }
        }
        return regras;
    }
}
