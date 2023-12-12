package model;

public class Jog_Segue_Jog {
    private int idJog_Segue_Jog;
    private String jogador1_CPF;
    private String jogador2_CPF;

    public Jog_Segue_Jog(int idJog_Segue_Jog, String jogador1_CPF, String jogador2_CPF) {
        this.idJog_Segue_Jog = idJog_Segue_Jog;
        this.jogador1_CPF = jogador1_CPF;
        this.jogador2_CPF = jogador2_CPF;
    }

    public int getIdJog_Segue_Jog() {
        return idJog_Segue_Jog;
    }

    public void setIdJog_Segue_Jog(int idJog_Segue_Jog) {
        this.idJog_Segue_Jog = idJog_Segue_Jog;
    }

    public String getJogador1_CPF() {
        return jogador1_CPF;
    }

    public void setJogador1_CPF(String jogador1_CPF) {
        this.jogador1_CPF = jogador1_CPF;
    }

    public String getJogador2_CPF() {
        return jogador2_CPF;
    }

    public void setJogador2_CPF(String jogador2_CPF) {
        this.jogador2_CPF = jogador2_CPF;
    }
}
