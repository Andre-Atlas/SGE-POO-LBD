package Model;

public class Partida {
    private int ID_Partida;
    private String Cidade;

    public Partida(String cidade) {
        this.Cidade = cidade;
    }

    public String mostrarPartida() {
        return "Partida [ID_Partida = " + ID_Partida +
                ",Cidade = " + Cidade + "]";
    }

    public int getID_Partida() {
        return ID_Partida;
    }

    public void setID_Partida(int iD_Partida) {
        ID_Partida = iD_Partida;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }
}
