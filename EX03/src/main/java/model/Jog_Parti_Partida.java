package model;

public class Jog_Parti_Partida {
    private int idJog_Participa_Partida;
    private String Jogador_CPF;
    private int idPartida_IdPartida;

    public Jog_Parti_Partida(int idJog_Participa_Partida, String Jogador_CPF, int idPartida_IdPartida) {
        this.idJog_Participa_Partida = idJog_Participa_Partida;
        this.Jogador_CPF = Jogador_CPF;
        this.idPartida_IdPartida = idPartida_IdPartida;
    }

    public void setIdjog_participa_partida(int idJog_Participa_Partida) {
        this.idJog_Participa_Partida = idJog_Participa_Partida;
    }

    public void setJogador_cpf(String Jogador_CPF) {
        this.Jogador_CPF = Jogador_CPF;
    }

    public void setIdpartida_idpartida(int idPartida_IdPartida) {
        this.idPartida_IdPartida = idPartida_IdPartida;
    }

    public int getIdjog_participa_partida() {
        return idJog_Participa_Partida;
    }

    public String getJogador_cpf() {
        return Jogador_CPF;
    }

    public int getIdpartida_idpartida() {
        return idPartida_IdPartida;
    }
}
