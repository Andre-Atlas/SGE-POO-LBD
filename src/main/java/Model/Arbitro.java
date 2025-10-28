package Model;

public class Arbitro {
    private int idArbitro;
    private int partidasArbitradas;
    private int pessoa; // FK -> Pessoa(ID_Participante)
    private Integer competicoesParticipando; // FK -> Competição(ID_Competição) (pode ser nulo)

    // Construtor padrão
    public Arbitro() {}

    // Construtor com tudo (não tem id por ser AI)
    public Arbitro(int partidasArbitradas, int pessoa, Integer competicoesParticipando) {
        this.partidasArbitradas = partidasArbitradas;
        this.pessoa = pessoa;
        this.competicoesParticipando = competicoesParticipando;
    }

    // Getters e Setters
    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public int getPartidasArbitradas() {
        return partidasArbitradas;
    }

    public void setPartidasArbitradas(int partidasArbitradas) {
        this.partidasArbitradas = partidasArbitradas;
    }

    public int getPessoa() {
        return pessoa;
    }

    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getCompeticoesParticipando() {
        return competicoesParticipando;
    }

    public void setCompeticoesParticipando(Integer competicoesParticipando) {
        this.competicoesParticipando = competicoesParticipando;
    }

    @Override
    public String toString() {
        return "Arbitro [ID=" + idArbitro +
                ", Partidas_Arbitradas=" + partidasArbitradas +
                ", Pessoa=" + pessoa +
                ", Competicoes_Participando=" + competicoesParticipando + "]";
    }//Como de aparecer se for chamado para aparecer
}
