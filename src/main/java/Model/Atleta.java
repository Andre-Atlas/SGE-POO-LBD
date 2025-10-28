package Model;

public class Atleta {
    private int ID_Atleta;
    private double Peso;
    private double Altura;
    private String Modalidade; // Corresponde ao VARCHAR(100) no SQL
    private int Pessoa; // Corresponde à FK Pessoa

    public Atleta(double peso, double altura, String modalidade, int pessoa) {
        this.Peso = peso;
        this.Altura = altura;
        this.Modalidade = modalidade;
        this.Pessoa = pessoa;
    }

    public String mostrarAtleta() {
        return "Atleta [ID_Atleta = " + ID_Atleta +
                ", Pessoa_ID = " + Pessoa +
                ", Peso = " + Peso +
                ", Altura = " + Altura +
                ", Modalidade = " + Modalidade + "]";
    }

    // Getters e Setters
    public int getID_Atleta() { return ID_Atleta; }
    public void setID_Atleta(int iD_Atleta) { ID_Atleta = iD_Atleta; }
    public double getPeso() { return Peso; }
    public void setPeso(double peso) { Peso = peso; }
    public double getAltura() { return Altura; }
    public void setAltura(double altura) { Altura = altura; }
    public String getModalidade() { return Modalidade; }
    public void setModalidade(String modalidade) { Modalidade = modalidade; }
    public int getPessoa() { return Pessoa; }
    public void setPessoa(int pessoa) { Pessoa = pessoa; }
}