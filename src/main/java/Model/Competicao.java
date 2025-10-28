package Model;

public class Competicao {
    private int ID_Competicao;
    private String Nome;
    private String Cidade;
    private String Entidade_Organizadora;
    private int Ano;

    public Competicao(String nome, String cidade, String entidade_organizadora, int ano) {
        this.Nome = nome;
        this.Cidade = cidade;
        this.Entidade_Organizadora = entidade_organizadora;
        this.Ano = ano;
    }

    public String mostrarCompeticao() {
        return "Competicao [ID_Competicao = " + ID_Competicao +
                ",Nome = " + Nome +
                ",Cidade = " + Cidade +
                ",Entidade_Organizadora = " + Entidade_Organizadora +
                ",Ano = " + Ano + "]";
    }

    public int getID_Competicao() {
        return ID_Competicao;
    }

    public void setID_Competicao(int iD_Competicao) {
        ID_Competicao = iD_Competicao;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getEntidade_Organizadora() {
        return Entidade_Organizadora;
    }

    public void setEntidade_Organizadora(String entidade_organizadora) {
        Entidade_Organizadora = entidade_organizadora;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int ano) {
        Ano = ano;
    }
}
