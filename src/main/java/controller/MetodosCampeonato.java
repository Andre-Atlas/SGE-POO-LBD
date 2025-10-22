package controller;

import Model.BancodeDados;
import Model.Modalidade;
import Model.Pessoa;
import Model.Premiacao;
import Model.Regras_Modalidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Equipe;

import static Model.BancodeDados.connection;

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
    
    //Métodos Regras_Basicas

    public void inserirRegra(Regras_Modalidade regras){
        String sql = "INSERT INTO regras_modalidade (Codigo_Regra, Inciso, Regra, Modalidade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, regras.getCodigo_Regra());
            pstmt.setString(2, regras.getInciso());
            pstmt.setString(3, regras.getInciso()); 
            pstmt.setInt(4, regras.getModalidade()); 
            pstmt.executeUpdate();
            System.out.println("Regras inserida com sucesso");
            
        } catch (Exception e) {
            System.out.println("Erro ao inserir regras: " + e.getMessage());
        }
    }
    
    public List<Regras_Modalidade> listarRegra(){
        String sql = "SELECT * FROM regras_modalidade";
        List<Regras_Modalidade> regras = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
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
            } catch (SQLException e) {
            System.out.println("Erro ao listar regras: " + e.getMessage());
        }
        
        return regras;
    }
    
    public void deletarRegras(int id){
        String sql = "DELETE FROM regras_modalidade WHERE Codigo_Regra = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0){
                System.out.println("Regra deletada com sucesso");
            } else {
                System.out.println("Nenhuma regra encontrada com o ID fornecido");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar regra: " + e.getMessage());
        }
    }
    
    public void atualizarRegra(Regras_Modalidade regras){
        String sql = "UPDATE regras_modalidade SET Inciso = ?, Regra = ?, Modalidade = ? WHERE Codigo_Regra = ?";
         try (PreparedStatement pstmt = connection.prepareStatement(sql)){
             pstmt.setString(1, regras.getInciso());
             pstmt.setString(2, regras.getRegra());
             pstmt.setInt(3, regras.getModalidade());
             pstmt.setInt(4, regras.getCodigo_Regra());
             int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Regra atualizada com sucesso");
            }else {
                System.out.println("Nenhuma regra encontrada com ID fornecido");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar regra " + e.getMessage());
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
        pstmt.setInt(5, equipe.getSede_Numero());
        pstmt.setInt(6, equipe.getSede_Quadra());
        pstmt.setInt(7, equipe.getSede_Cidade());
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
        pstmt.setInt(5, equipe.getSede_Numero());
        pstmt.setInt(6, equipe.getSede_Quadra());
        pstmt.setInt(7, equipe.getSede_Cidade());
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