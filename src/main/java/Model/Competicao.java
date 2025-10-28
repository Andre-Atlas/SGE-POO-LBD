package Model;

public class Competicao {
    private int ID_Competicao;
    private String Nome;
    private int Local_Numero;
    private int Local_Quadra;
    private int Local_Cidade;
    private String Entidade_Organizadora;
    private int Ano;
    private int Temporada;
    private int Arbitro;
    private int Atleta;

    // Construtor atualizado para corresponder ao SQL
    public Competicao(String nome, int local_Numero, int local_Quadra, int local_Cidade,
                      String entidade_Organizadora, int ano, int temporada, int arbitro, int atleta) {
        this.Nome = nome;
        this.Local_Numero = local_Numero;
        this.Local_Quadra = local_Quadra;
        this.Local_Cidade = local_Cidade;
        this.Entidade_Organizadora = entidade_Organizadora;
        this.Ano = ano;
        this.Temporada = temporada;
        this.Arbitro = arbitro;
        this.Atleta = atleta;
    }

    // toString() atualizado
    public String mostrarCompeticao() {
        return "Competicao [ID_Competicao=" + ID_Competicao +
                ", Nome=" + Nome +
                ", Local=" + Local_Cidade + "/" + Local_Quadra + "/" + Local_Numero +
                ", Entidade_Organizadora=" + Entidade_Organizadora +
                ", Ano=" + Ano +
                ", Temporada=" + Temporada +
                ", Arbitro_ID=" + Arbitro +
                ", Atleta_ID=" + Atleta + "]";
    }

    // Getters e Setters
    public int getID_Competicao() { return ID_Competicao; }
    public void setID_Competicao(int iD_Competicao) { ID_Competicao = iD_Competicao; }
    public String getNome() { return Nome; }
    public void setNome(String nome) { Nome = nome; }
    public int getLocal_Numero() { return Local_Numero; }
    public void setLocal_Numero(int local_Numero) { Local_Numero = local_Numero; }
    public int getLocal_Quadra() { return Local_Quadra; }
    public void setLocal_Quadra(int local_Quadra) { Local_Quadra = local_Quadra; }
    public int getLocal_Cidade() { return Local_Cidade; }
    public void setLocal_Cidade(int local_Cidade) { Local_Cidade = local_Cidade; }
    public String getEntidade_Organizadora() { return Entidade_Organizadora; }
    public void setEntidade_Organizadora(String entidade_Organizadora) { Entidade_Organizadora = entidade_Organizadora; }
    public int getAno() { return Ano; }
    public void setAno(int ano) { Ano = ano; }
    public int getTemporada() { return Temporada; }
    public void setTemporada(int temporada) { Temporada = temporada; }
    public int getArbitro() { return Arbitro; }
    public void setArbitro(int arbitro) { Arbitro = arbitro; }
    public int getAtleta() { return Atleta; }
    public void setAtleta(int atleta) { Atleta = atleta; }
}