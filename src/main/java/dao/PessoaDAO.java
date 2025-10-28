// Este é um NOVO ARQUIVO: dao/PessoaDAO.java
package dao;

import Model.BancodeDados;
import Model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    /**
     * Insere uma pessoa no banco de dados.
     */
    public void inserir(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO Pessoa (Nome, Nacionalidade, Data_Nascimento, Modalidade, Email) VALUES (?, ?, ?, ?, ?)";

        // 1. A conexão é aberta e fechada aqui.
        try (Connection connection = BancodeDados.conectar();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getNacionalidade());
            pstmt.setDate(3, pessoa.getDataNascimento());
            pstmt.setInt(4, pessoa.getModalidadeId());
            pstmt.setString(5, pessoa.getEmail());
            pstmt.executeUpdate();
        }
        // 2. O 'catch' foi removido. A exceção será lançada.
    }

    /**
     * Lista todas as pessoas do banco de dados.
     */
    public List<Pessoa> listarTodos() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa";

        try (Connection connection = BancodeDados.conectar();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pessoa p = new Pessoa(
                        rs.getString("Nome"),
                        rs.getString("Nacionalidade"),
                        rs.getDate("Data_Nascimento"),
                        rs.getInt("Modalidade"),
                        rs.getString("Email")
                );
                p.setIdParticipante(rs.getInt("ID_Participante"));
                pessoas.add(p);
            }
        }
        return pessoas;
    }

    /**
     * Atualiza os dados de uma pessoa.
     */
    public boolean atualizar(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE Pessoa SET Nome = ?, Nacionalidade = ?, Data_Nascimento = ?, Modalidade = ?, Email = ? WHERE ID_Participante = ?";

        try (Connection connection = BancodeDados.conectar();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getNacionalidade());
            pstmt.setDate(3, pessoa.getDataNascimento());
            pstmt.setInt(4, pessoa.getModalidadeId());
            pstmt.setString(5, pessoa.getEmail());
            pstmt.setInt(6, pessoa.getIdParticipante());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Retorna true se atualizou, false se não
        }
    }

    /**
     * Deleta uma pessoa pelo seu ID.
     */
    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM Pessoa WHERE ID_Participante = ?";

        try (Connection connection = BancodeDados.conectar();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Retorna true se deletou, false se não
        }
    }
}
