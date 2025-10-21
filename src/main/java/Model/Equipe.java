package Model;

public class Equipe {
    private int ID_Equipe;
    private int Atleta;
    private int Tecnico;
    private int Equipe_Tecnica;
    private int Modalidade;
    private int Cede_Numero;
    private int Cede_Quadra;
    private int Cede_Cidade;
    public Equipe(int atleta, int tecnico, int equipe Tecnica, int modalidade, int cede_Numero, int cede_Quadra, int cede_Cidade) {
        this.Atleta = atleta;
        this.Tecnico = tecnico;
        this.Equipe_Tecnica = equipe_Tecnica;
        this.Modalidade = modalidade;
        this.Cede_Numero = cede_Numero;
        this.Cede_Quadra = cede_Quadra;
        this.Cede_Cidade = cede_Cidade;
    }

    public String mostrarEquipe() {
        return "Equipe ID_Equipe=" + ID_Equipe +
                ", Atleta=" + Atleta +
                ", Tecnico=" + Tecnico +
                ", Equipe_Tecnica=" + Equipe_Tecnica +
                ", Modalidade=" + Modalidade +
                ", Cede_Numero=" + Cede_Numero +
                ", Cede_Quadra=" + Cede_Quadra +
                ", Cede_Cidade=" + Cede_Cidade + "]";
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
    public int getCede_Numero() {
        return Cede_Numero;
    }
    public void setCede_Numero(int cede_Numero) {
        Cede_Numero = cede_Numero;
    }
    public int getCede_Quadra() {
        return Cede_Quadra;
    }
    public void setCede_Quadra(int cede_Quadra) {
        Cede_Quadra = cede_Quadra;
    }
    public int getCede_Cidade() {
        return Cede_Cidade;
    }
    public void setCede_Cidade(int cede_Cidade) {
        Cede_Cidade = cede_Cidade;
    }
}