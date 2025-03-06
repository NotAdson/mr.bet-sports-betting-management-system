package MrBet;

import java.util.HashSet;

public class Time {
    private String id;
    private String nome;
    private String mascote;
    private HashSet<String> campeonatosParticipando;

    public Time(String id, String nome, String mascote) {
        this.id = id;
        this.nome = nome;
        this.mascote = mascote;
        this.campeonatosParticipando = new HashSet<>();
    }

    public void adicionaCampeonato(String nomeCampeonato) {
        campeonatosParticipando.add(nomeCampeonato);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMascote() {
        return mascote;
    }

    public HashSet<String> getCampeonatosParticipando() {
        return campeonatosParticipando;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Time time = (Time) obj;
        return id.equals(time.id);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " / " + mascote;
    }
}