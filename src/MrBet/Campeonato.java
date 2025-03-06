package MrBet;

import java.util.HashSet;

public class Campeonato {
    private String nome;
    private HashSet<String> participantes;
    private int maxParticipantes;

    public Campeonato(String nome, int qntdParticipantes) {
        this.nome = nome;
        this.participantes = new HashSet<>();
        this.maxParticipantes = qntdParticipantes;
    }

    public void adicionaTime(String idTime) {
        if (participantes.size() >= maxParticipantes) {
            throw new IllegalArgumentException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
        }
        participantes.add(idTime);
    }

    public boolean verificaTime(String idTime) {
        return participantes.contains(idTime);
    }

    public String getNome() {
        return nome;
    }

    public HashSet<String> getParticipantes() {
        return participantes;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Campeonato campeonato = (Campeonato) obj;
        return nome.equalsIgnoreCase(campeonato.nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return nome + " - " + participantes.size() + "/" + maxParticipantes;
    }
}