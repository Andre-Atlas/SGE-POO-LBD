package Model;

public class Equipe {
    private int ID_Equipe;
    private int Atleta;
    private int Tecnico;
    private int Equipe_Tecnica;
    private int Modalidade;
    private int Sede_Numero;
    private int Sede_Quadra;
    private int Sede_Cidade;

    public Equipe(int atleta, int tecnico, int equipe_Tecnica, int modalidade, int Sede_Numero, int Sede_Quadra, int Sede_Cidade) {
        this.Atleta = atleta;
        this.Tecnico = tecnico;
        this.Equipe_Tecnica = equipe_Tecnica;
        this.Modalidade = modalidade;
        this.Sede_Numero = Sede_Numero;
        this.Sede_Quadra = Sede_Quadra;
        this.Sede_Cidade = Sede_Cidade;
    }

    public String mostrarEquipe() {
        return "Equipe ID_Equipe=" + ID_Equipe +
                ", Atleta=" + Atleta +
                ", Tecnico=" + Tecnico +
                ", Equipe_Tecnica=" + Equipe_Tecnica +
                ", Modalidade=" + Modalidade +
                ", Sede_Numero=" + Sede_Numero +
                ", Sede_Quadra=" + Sede_Quadra +
                ", Sede_Cidade=" + Sede_Cidade + "]";
    }
    public int getID_Equipe() {
        return ID_Equipe;
    }
    public void setID_Equipe(int iD_Equipe) {
        ID_Equipe = iD_Equipe;
    }
    public int getAtleta() {
        return Atleta;
    }
    public void setAtleta(int atleta) {
        Atleta = atleta;
    }
    public int getTecnico() {
        return Tecnico;
    }
    public void setTecnico(int tecnico) {
        Tecnico = tecnico;
    }
    public int getEquipe_Tecnica() {
        return Equipe_Tecnica;
    }
    public void setEquipe_Tecnica(int equipe_Tecnica) {
        Equipe_Tecnica = equipe_Tecnica;
    }
    public int getModalidade() {
        return Modalidade;
    }
    public void setModalidade(int modalidade) {
        Modalidade = modalidade;
    }
    public int getSede_Numero() {
        return Sede_Numero;
    }
    public void setSede_Numero(int Sede_Numero) {
        Sede_Numero = Sede_Numero;
    }
    public int getSede_Quadra() {
        return Sede_Quadra;
    }
    public void setSede_Quadra(int Sede_Quadra) {
        Sede_Quadra = Sede_Quadra;
    }
    public int getSede_Cidade() {
        return Sede_Cidade;
    }
    public void setSede_Cidade(int Sede_Cidade) {
        Sede_Cidade = Sede_Cidade;
    }
}