package MrBet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class MrBetSistema {
    private HashMap<String, Time> times;
    private HashMap<String, Campeonato> campeonatos;
    private ArrayList<Aposta> apostas;

    public MrBetSistema() {
        this.times = new HashMap<>();
        this.campeonatos = new HashMap<>();
        this.apostas = new ArrayList<>();
    }

    public void cadastraTime(String id, String nome, String mascote) {
        if (times.containsKey(id)) {
            throw new IllegalArgumentException("TIME JÁ EXISTE!");
        }
        times.put(id, new Time(id, nome, mascote));
    }

    public void cadastraCampeonato(String nome, int qntdParticipantes) {
        String nomeLower = nome.toLowerCase();
        if (campeonatos.containsKey(nomeLower)) {
            throw new IllegalArgumentException("CAMPEONATO JÁ EXISTE!");
        }
        campeonatos.put(nomeLower, new Campeonato(nome, qntdParticipantes));
    }

    public void adicionaTimeNoCampeonato(String idTime, String nomeCampeonato) {
        String nomeLower = nomeCampeonato.toLowerCase();
        if (!times.containsKey(idTime)) {
            throw new IllegalArgumentException("O TIME NÃO EXISTE!");
        }
        if (!campeonatos.containsKey(nomeLower)) {
            throw new IllegalArgumentException("O CAMPEONATO NÃO EXISTE!");
        }
        Campeonato campeonato = campeonatos.get(nomeLower);
        if (campeonato.getParticipantes().size() >= campeonato.getMaxParticipantes()) {
            throw new IllegalArgumentException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
        }
        campeonato.adicionaTime(idTime);
        times.get(idTime).adicionaCampeonato(nomeCampeonato);
    }

    public boolean verificaTimeEmCampeonato(String idTime, String nomeCampeonato) {
        String nomeLower = nomeCampeonato.toLowerCase();
        if (!times.containsKey(idTime)) {
            throw new IllegalArgumentException("O TIME NÃO EXISTE!");
        }
        if (!campeonatos.containsKey(nomeLower)) {
            throw new IllegalArgumentException("O CAMPEONATO NÃO EXISTE!");
        }
        return campeonatos.get(nomeLower).verificaTime(idTime);
    }

    public String getHistorico() {
        StringBuilder historico = new StringBuilder();
        historico.append("Participação mais frequente em campeonatos\n");
        // Implementar lógica para encontrar o time mais frequente
        historico.append("\nAinda não participou de campeonato\n");
        // Implementar lógica para encontrar times que não participaram
        historico.append("\nPopularidade em apostas\n");
        // Implementar lógica para popularidade em apostas
        return historico.toString();
    }

    public String getTime(String id) {
        if (!times.containsKey(id)) {
            throw new IllegalArgumentException("TIME NÃO EXISTE!");
        }
        return times.get(id).toString();
    }
}