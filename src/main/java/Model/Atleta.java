package Model;

public class Atleta {
    private int ID_Atleta;
    private double Peso;
    private double Altura;

    public Atleta(double peso, double altura) {
        this.Peso = peso;
        this.Altura = altura;
    }

    public String mostrarAtleta() {
        return "Atleta [ID_Atleta = " + ID_Atleta +
                ",Peso = " + Peso +
                ",Altura = " + Altura + "]";
    }

    public int getID_Atleta() {
        return ID_Atleta;
    }

    public void setID_Atleta(int iD_Atleta) {
        ID_Atleta = iD_Atleta;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double peso) {
        Peso = peso;
    }

    public double getAltura() {
        return Altura;
    }

    public void setAltura(double altura) {
        Altura = altura;
    }
}