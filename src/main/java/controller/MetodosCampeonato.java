package controller;

import Model.BancodeDados;
import Model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodosCampeonato {

    private Connection connection;

    public MetodosCampeonato() {
        this.connection = BancodeDados.conectar();
        if (this.connection == null) {
            System.exit(1); // Encerra se não conseguir conectar
        }
    }

    public void inserirPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoa (Nome, Nacionalidade, Data_Nascimento, Modalidade, Email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getNacionalidade());
            pstmt.setDate(3, pessoa.getDataNascimento());
            pstmt.setInt(4, pessoa.getModalidadeId());
            pstmt.setString(5, pessoa.getEmail());
            pstmt.executeUpdate();
            System.out.println("Pessoa '" + pessoa.getNome() + "' inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir pessoa: " + e.getMessage());
        }
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa";
        try (Statement stmt = connection.createStatement();
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
        } catch (SQLException e) {
            System.err.println("Erro ao listar pessoas: " + e.getMessage());
        }
        return pessoas;
    }

    public void atualizarPessoa(Pessoa pessoa) {
        String sql = "UPDATE Pessoa SET Nome = ?, Nacionalidade = ?, Data_Nascimento = ?, Modalidade = ?, Email = ? WHERE ID_Participante = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getNacionalidade());
            pstmt.setDate(3, pessoa.getDataNascimento());
            pstmt.setInt(4, pessoa.getModalidadeId());
            pstmt.setString(5, pessoa.getEmail());
            pstmt.setInt(6, pessoa.getIdParticipante());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Pessoa atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma pessoa encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pessoa: " + e.getMessage());
        }
    }

    /**
     * Deleta uma pessoa pelo seu ID.
     * @param id O ID da pessoa a ser deletada.
     */
    public void deletarPessoa(int id) {
        String sql = "DELETE FROM Pessoa WHERE ID_Participante = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Pessoa deletada com sucesso!");
            } else {
                System.out.println("Nenhuma pessoa encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pessoa: " + e.getMessage());
        }
    }

    // OBS: Implementar aqui os métodos CRUD para Atleta, Arbitro, Equipe, Competição, etc.
}
