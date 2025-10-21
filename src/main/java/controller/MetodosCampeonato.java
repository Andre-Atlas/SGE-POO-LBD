package controller;

import Model.BancodeDados;
import Model.Modalidade;
import Model.Pessoa;
import Model.Premiacao;

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

    // Métodos Pessoa

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

    
    //Métodos Modalidade

    public void inserirModalidade(Modalidade modalidade){
        String sql = "INSERT INTO Modalidade (Nome, Tipo, Regras_Basicas) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, modalidade.getNome());
            pstmt.setString(2, modalidade.getTipo());
            pstmt.setInt(3, modalidade.getRegras_Basicas()); 
            pstmt.executeUpdate();
            System.out.println("Modalidade " + modalidade.getNome() + " inserida com sucesso");
            
        } catch (Exception e) {
            System.out.println("Erro ao inserir modalidade: " + e.getMessage());
        }
    }
    
    public List<Modalidade> listarModalidade() {
        List<Modalidade> modalidades = new ArrayList<>();
        String sql = "SELECT * FROM Modalidade";
        try (Statement stmt = connection.createStatement();
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
            } catch (SQLException e) {
            System.out.println("Erro ao listar modalidades: " + e.getMessage());
        }
        
        return modalidades;
    } 
    
    public void deletarModalidade(int id){
        String sql = "DELETE FROM Modalidade WHERE ID_Modalidade = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0){
                System.out.println("Modalidade deletada com sucesso");
            } else {
                System.out.println("Nenhuma modalidade encontrada com o ID fornecido");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar modalidade: " + e.getMessage());
        }
    }

    public void atualizarModalidade(Modalidade modalidade){
        String sql = "UPDATE Modalidade SET Nome = ?, Tipo = ?, Regras_Basicas = ? WHERE ID_Modalidade = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, modalidade.getNome());
            pstmt.setString(2, modalidade.getTipo());
            pstmt.setInt(3, modalidade.getRegras_Basicas());
            pstmt.setInt(4, modalidade.getID_Modalidade());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Modalidade atualizada com sucesso");
            }else {
                System.out.println("Nenhuma modalidade encontrada com ID fornecido");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar modalidade " + e.getMessage());
        }
    }
    
    //Métodos Premiação
    
    public void inserirPremiacao(Premiacao premiacao){
        String sql = "INSERT INTO Premiação (Tipo, Colocação, Valor, Competição) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, premiacao.getTipo());
            pstmt.setInt(2, premiacao.getColocação());
            pstmt.setFloat(3, premiacao.getValor()); 
            pstmt.setInt(4, premiacao.getCompetição()); 
            pstmt.executeUpdate();
            System.out.println("Premiação inserida com sucesso");
            
        } catch (Exception e) {
            System.out.println("Erro ao inserir premiação: " + e.getMessage());
        }
    }
    
    public List<Premiacao> listarPremiacao(){
        String sql = "SELECT * FROM Premiação";
        List<Premiacao> premiacoes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Premiacao pre = new Premiacao(
                    rs.getInt("Tipo"),
                    rs.getInt("Colocação"),
                    rs.getFloat("Valor"),
                    rs.getInt("Competição")
                    );
                    pre.setID_Premiacao(rs.getInt("ID_Premiação"));
                    premiacoes.add(pre);
                }
            } catch (SQLException e) {
            System.out.println("Erro ao listar premiações: " + e.getMessage());
        }
        
        return premiacoes;
    }
    
    public void deletarPremiacao(int id){
        String sql = "DELETE FROM Premiação WHERE ID_Premiação = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0){
                System.out.println("Premiação deletada com sucesso");
            } else {
                System.out.println("Nenhuma premiação encontrada com o ID fornecido");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar premiação: " + e.getMessage());
        }
    }
    
    public void atualizarPremiacao(Premiacao premiacao){
        String sql = "UPDATE Premiação SET Tipo = ?, Colocação = ?, Valor = ?, Competição = ? WHERE Competição = ?";
         try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, premiacao.getTipo());
            pstmt.setInt(2, premiacao.getColocação());
            pstmt.setFloat(3, premiacao.getValor());
            pstmt.setInt(4, premiacao.getCompetição());
            pstmt.setInt(5, premiacao.getID_Premiacao());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Premiação atualizada com sucesso");
            }else {
                System.out.println("Nenhuma premiação encontrada com ID fornecido");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar premiação " + e.getMessage());
        }
    }
}

// OBS: Implementar aqui os métodos CRUD para Atleta, Arbitro, Equipe, Competição, etc.