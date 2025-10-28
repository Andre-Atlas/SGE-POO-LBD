package dao;

import Model.BancodeDados;
import Model.Equipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {

    public void inserir(Equipe equipe) throws SQLException {
        // CORRIGIDO: "Cede_"
        String sql = "INSERT INTO Equipe (Atleta, Tecnico, Equipe_Tecnica, Modalidade, Cede_Numero, Cede_Quadra, Cede_Cidade) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, equipe.getAtleta());
            pstmt.setInt(2, equipe.getTecnico());
            pstmt.setInt(3, equipe.getEquipe_Tecnica());
            pstmt.setInt(4, equipe.getModalidade());
            pstmt.setInt(5, equipe.getCede_Numero()); // CORRIGIDO
            pstmt.setInt(6, equipe.getCede_Quadra()); // CORRIGIDO
            pstmt.setInt(7, equipe.getCede_Cidade()); // CORRIGIDO
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    equipe.setID_Equipe(rs.getInt(1));
                }
            }
        }
    }

    public List<Equipe> listarTodos() throws SQLException {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM Equipe";

        try (Connection conn = BancodeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipe e = new Equipe(
                        rs.getInt("Atleta"),
                        rs.getInt("Tecnico"),
                        rs.getInt("Equipe_Tecnica"),
                        rs.getInt("Modalidade"),
                        rs.getInt("Cede_Numero"), // CORRIGIDO
                        rs.getInt("Cede_Quadra"), // CORRIGIDO
                        rs.getInt("Cede_Cidade")  // CORRIGIDO
                );
                e.setID_Equipe(rs.getInt("ID_Equipe"));
                equipes.add(e);
            }
        }
        return equipes;
    }

    public boolean atualizar(Equipe equipe) throws SQLException {
        // CORRIGIDO: "Cede_"
        String sql = "UPDATE Equipe SET Atleta = ?, Tecnico = ?, Equipe_Tecnica = ?, Modalidade = ?, " +
                "Cede_Numero = ?, Cede_Quadra = ?, Cede_Cidade = ? WHERE ID_Equipe = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, equipe.getAtleta());
            pstmt.setInt(2, equipe.getTecnico());
            pstmt.setInt(3, equipe.getEquipe_Tecnica());
            pstmt.setInt(4, equipe.getModalidade());
            pstmt.setInt(5, equipe.getCede_Numero()); // CORRIGIDO
            pstmt.setInt(6, equipe.getCede_Quadra()); // CORRIGIDO
            pstmt.setInt(7, equipe.getCede_Cidade()); // CORRIGIDO
            pstmt.setInt(8, equipe.getID_Equipe());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // ... (métodos buscarPorId e deletar não precisam de mudança) ...
    public Equipe buscarPorId(int id) throws SQLException {
        Equipe equipe = null;
        String sql = "SELECT * FROM Equipe WHERE ID_Equipe = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    equipe = new Equipe(
                            rs.getInt("Atleta"),
                            rs.getInt("Tecnico"),
                            rs.getInt("Equipe_Tecnica"),
                            rs.getInt("Modalidade"),
                            rs.getInt("Cede_Numero"),
                            rs.getInt("Cede_Quadra"),
                            rs.getInt("Cede_Cidade")
                    );
                    equipe.setID_Equipe(rs.getInt("ID_Equipe"));
                }
            }
        }
        return equipe;
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM Equipe WHERE ID_Equipe = ?";

        try (Connection conn = BancodeDados.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}