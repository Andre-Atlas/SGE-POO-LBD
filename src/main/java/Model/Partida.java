package Model;

import java.sql.Date;
import java.sql.Time;

public class Partida {
    private int ID_Partida;
    private int Local_Numero;
    private int Local_Quadra;
    private int Local_Cidade;
    private Date Data_Partida;
    private Time Hora;
    private int Equipe; // Seu SQL só tem 1 equipe aqui
    private int Modalidade;
    private Integer Arbitro; // Integer para permitir nulo (UNIQUE)
    private Integer Ganhador; // Integer para permitir nulo

    // Construtor
    public Partida(int local_Numero, int local_Quadra, int local_Cidade, Date data_Partida,
                   Time hora, int equipe, int modalidade, Integer arbitro) {
        this.Local_Numero = local_Numero;
        this.Local_Quadra = local_Quadra;
        this.Local_Cidade = local_Cidade;
        this.Data_Partida = data_Partida;
        this.Hora = hora;
        this.Equipe = equipe;
        this.Modalidade = modalidade;
        this.Arbitro = arbitro;
    }

    // toString()
    public String mostrarPartida() {
        return "Partida [ID_Partida=" + ID_Partida +
                ", Local=" + Local_Cidade + "/" + Local_Quadra + "/" + Local_Numero +
                ", Data=" + Data_Partida + ", Hora=" + Hora +
                ", Equipe_ID=" + Equipe +
                ", Modalidade_ID=" + Modalidade +
                ", Arbitro_ID=" + Arbitro +
                ", Ganhador_ID=" + Ganhador + "]";
    }

    // Getters e Setters
    public int getID_Partida() { return ID_Partida; }
    public void setID_Partida(int iD_Partida) { ID_Partida = iD_Partida; }
    public int getLocal_Numero() { return Local_Numero; }
    public void setLocal_Numero(int local_Numero) { Local_Numero = local_Numero; }
    public int getLocal_Quadra() { return Local_Quadra; }
    public void setLocal_Quadra(int local_Quadra) { Local_Quadra = local_Quadra; }
    public int getLocal_Cidade() { return Local_Cidade; }
    public void setLocal_Cidade(int local_Cidade) { Local_Cidade = local_Cidade; }
    public Date getData_Partida() { return Data_Partida; }
    public void setData_Partida(Date data_Partida) { Data_Partida = data_Partida; }
    public Time getHora() { return Hora; }
    public void setHora(Time hora) { Hora = hora; }
    public int getEquipe() { return Equipe; }
    public void setEquipe(int equipe) { Equipe = equipe; }
    public int getModalidade() { return Modalidade; }
    public void setModalidade(int modalidade) { Modalidade = modalidade; }
    public Integer getArbitro() { return Arbitro; }
    public void setArbitro(Integer arbitro) { Arbitro = arbitro; }
    public Integer getGanhador() { return Ganhador; }
    public void setGanhador(Integer ganhador) { Ganhador = ganhador; }
}