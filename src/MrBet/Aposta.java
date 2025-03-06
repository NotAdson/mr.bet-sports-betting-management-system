package MrBet;

public class Aposta {
    private String idTime;
    private double valor;
    private int colocacao;
    private String nomeCampeonato;

    public Aposta(String idTime, double valor, int colocacao, String nomeCampeonato) {
        this.idTime = idTime;
        this.valor = valor;
        this.colocacao = colocacao;
        this.nomeCampeonato = nomeCampeonato;
    }

    public String getIdTime() {
        return idTime;
    }

    public double getValor() {
        return valor;
    }

    public int getColocacao() {
        return colocacao;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    @Override
    public String toString() {
        return "[" + idTime + "] " + nomeCampeonato + " - " + colocacao + "º lugar - R$ " + valor;
    }
}