package controller;

import Model.BancodeDados;
import Model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Equipe;

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




public void inserirEquipe(Equipe equipe) {

    String sql = "INSERT INTO Equipe (Atleta, Tecnico, Equipe_Tecnica, Modalidade, Cede_Numero, Cede_Quadra, Cede_Cidade) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        pstmt.setInt(1, equipe.getAtleta());
        pstmt.setInt(2, equipe.getTecnico());
        pstmt.setInt(3, equipe.getEquipe_Tecnica());
        pstmt.setInt(4, equipe.getModalidade());
        pstmt.setInt(5, equipe.getCede_Numero());
        pstmt.setInt(6, equipe.getCede_Quadra());
        pstmt.setInt(7, equipe.getCede_Cidade());
        pstmt.executeUpdate();
        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
                equipe.setID_Equipe(rs.getInt(1));
                System.out.println("Equipe inserida com sucesso! ID=" + equipe.getID_Equipe());
            }
        }

    } catch (SQLException e) {
        System.err.println("Erro ao inserir equipe: " + e.getMessage());
    }
}
public List<Equipe> listarEquipes() {
    List<Equipe> equipes = new ArrayList<>();
    String sql = "SELECT * FROM Equipe";
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Equipe e = new Equipe(
                    rs.getInt("Atleta"),
                    rs.getInt("Tecnico"),
                    rs.getInt("Equipe_Tecnica"),
                    rs.getInt("Modalidade"),
                    rs.getInt("Cede_Numero"),
                    rs.getInt("Cede_Quadra"),
                    rs.getInt("Cede_Cidade")
            );
            e.setID_Equipe(rs.getInt("ID_Equipe"));
            equipes.add(e);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar equipes: " + e.getMessage());
    }
    return equipes;
}
public Equipe buscarEquipePorId(int id) {
    Equipe equipe = null;
    String sql = "SELECT * FROM Equipe WHERE ID_Equipe = ?";
    Connection connection;
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
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
    } catch (SQLException e) {
        System.err.println("Erro ao buscar equipe por ID: " + e.getMessage());
    }
    return equipe;
}
public void atualizarEquipe(Equipe equipe) {
    String sql = "UPDATE Equipe SET Atleta = ?, Tecnico = ?, Equipe_Tecnica = ?, Modalidade = ?, " +
            "Cede_Numero = ?, Cede_Quadra = ?, Cede_Cidade = ? WHERE ID_Equipe = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, equipe.getAtleta());
        pstmt.setInt(2, equipe.getTecnico());
        pstmt.setInt(3, equipe.getEquipe_Tecnica());
        pstmt.setInt(4, equipe.getModalidade());
        pstmt.setInt(5, equipe.getCede_Numero());
        pstmt.setInt(6, equipe.getCede_Quadra());
        pstmt.setInt(7, equipe.getCede_Cidade());
        pstmt.setInt(8, equipe.getID_Equipe());
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Equipe atualizada com sucesso!");
        } else {
            System.out.println("Nenhuma equipe encontrada com o ID " + equipe.getID_Equipe());
        }
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar equipe: " + e.getMessage());
    }
}
public void deletarEquipe(int id) {
    String sql = "DELETE FROM Equipe WHERE ID_Equipe = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Equipe deletada com sucesso!");
        } else {
            System.out.println("Nenhuma equipe encontrada com o ID " + id);
        }
    } catch (SQLException e) {
        System.err.println("Erro ao deletar equipe: " + e.getMessage());

    }
}