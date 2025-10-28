package Model;

public class Regras_Modalidade {
    private int Codigo_Regra;
    private String Inciso;
    private String Regra;
    private int Modalidade;

    @Override
    public String toString() {
        return "Regras_Modalidade [Codigo_Regra=" + Codigo_Regra +
                ", Inciso=" + Inciso + 
                ", Regra=" + Regra
                + ", Modalidade=" + Modalidade + "]";
    }

    //construtor
    public Regras_Modalidade(String inciso, String regra, int modalidade){
        this.Inciso = inciso;
        this.Regra = regra;
        this.Modalidade = modalidade;
    }

    // getters 
    public int getCodigo_Regra() {
        return Codigo_Regra;
    }
    public String getInciso() {
        return Inciso;
    }
    public String getRegra() {
        return Regra;
    }
    public int getModalidade() {
        return Modalidade;
    }

    // setters 
    public void setCodigo_Regra(int codigo_Regra) {
        Codigo_Regra = codigo_Regra;
    }
    public void setInciso(String inciso) {
        Inciso = inciso;
    }
    public void setRegra(String regra) {
        Regra = regra;
    }
    public void setModalidade(int modalidade) {
        Modalidade = modalidade;
    }

    

}
