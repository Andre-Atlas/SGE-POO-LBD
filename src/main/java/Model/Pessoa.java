package Model;

import java.sql.Date;

public class Pessoa {
    private int idParticipante;
    private String nome;
    private String nacionalidade;
    private Date dataNascimento;
    private int modalidadeId;
    private String email;

    // Construtores, Getters e Setters

    public Pessoa(String nome, String nacionalidade, Date dataNascimento, int modalidadeId, String email) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
        this.modalidadeId = modalidadeId;
        this.email = email;
    }

    // Getters
    public int getIdParticipante() { return idParticipante; }
    public String getNome() { return nome; }
    public String getNacionalidade() { return nacionalidade; }
    public Date getDataNascimento() { return dataNascimento; }
    public int getModalidadeId() { return modalidadeId; }
    public String getEmail() { return email; }

    // Setters
    public void setIdParticipante(int idParticipante) { this.idParticipante = idParticipante; }
    public void setNome(String nome) { this.nome = nome; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setModalidadeId(int modalidadeId) { this.modalidadeId = modalidadeId; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Pessoa [" +
                "ID=" + idParticipante +
                ", Nome='" + nome + '\'' +
                ", Nacionalidade='" + nacionalidade + '\'' +
                ", Data Nasc.=" + dataNascimento +
                ", Email='" + email + '\'' +
                ", ID Modalidade=" + modalidadeId +
                ']';
    }
}
