package Model;

public class Arbitro {
    private int idArbitro;
    private int partidasArbitradas;
    private int pessoa; // Chave primaria

    public Arbitro() {} //Padrão

    public Arbitro(int partidasArbitradas, int pessoa) { //Pois nenhum pode ser nulo
        this.partidasArbitradas = partidasArbitradas;
        this.pessoa = pessoa;
    }

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

    @Override
    public String toString() {
        return "Arbitro [ID=" + idArbitro +
                ", Partidas=" + partidasArbitradas +
                ", Pessoa=" + pessoa + "]";
    } //Como deve imprimir quando chamado
}
