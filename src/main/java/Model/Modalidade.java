package Model;

public class Modalidade {
    private int ID_Modalidade;
    private String Nome;
    private String Tipo;
    private int Regras_Basicas;

    public String mostrarModalidade() {
        return "Modalidade [ID_Modalidade=" + ID_Modalidade + 
                ", Nome=" + Nome + 
                ", Tipo=" + Tipo + 
                ", Regras_Basicas=" + Regras_Basicas + "]";
    }

    // construtor
    public Modalidade(String nome, String tipo, int regras){
        this.Nome = nome;
        this.Tipo = tipo;
        this.Regras_Basicas = regras;
    }

    // getters 

    public int getID_Modalidade() {
        return ID_Modalidade;
    }
    
    public String getNome() {
        return Nome;
    }
    
    public String getTipo() {
        return Tipo;
    }

    public int getRegras_Basicas() {
        return Regras_Basicas;
    }

    // setters

    public void setID_Modalidade(int iD_Modalidade) {
        ID_Modalidade = iD_Modalidade;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
    
    public void setRegras_Basicas(int regras_Basicas) {
        Regras_Basicas = regras_Basicas;
    }
}
