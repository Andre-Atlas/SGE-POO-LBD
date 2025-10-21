package Model;

public class Premiacao {
    private int ID_Premiacao;
    private int Tipo;
    private int Colocação;
    private float Valor;
    private int Competição;

    @Override
    public String toString() {
        return "Premiacao [Tipo=" + Tipo + 
                ", Colocação=" + Colocação + 
                ", Valor=" + Valor + 
                ", Competição=" + Competição + "]";
    }

    //Construtor
    public Premiacao(int tipo, int colocacao, float valor, int competicao){
        this.Tipo = tipo;
        this.Colocação = colocacao;
        this.Valor = valor;
        this.Competição = competicao;
    }

    //Getters
    public int getID_Premiacao() {
        return ID_Premiacao;
    }
    public int getTipo() {
        return Tipo;
    }
    public int getColocação() {
        return Colocação;
    }
    public float getValor() {
        return Valor;
    }
    public int getCompetição() {
        return Competição;
    }
    

    //Setters
    public void setTipo(int tipo) {
        Tipo = tipo;
    }
    public void setColocação(int colocação) {
        Colocação = colocação;
    }
    public void setValor(float valor) {
        Valor = valor;
    }
    public void setCompetição(int competição) {
        Competição = competição;
    }
    public void setID_Premiacao(int iD_Premiacao) {
        ID_Premiacao = iD_Premiacao;
    }

    
}
