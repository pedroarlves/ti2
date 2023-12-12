package model;

public class Endereco {
    private int idEndereco;
    private String jogador_CPF;
    private String num;
    private String bairro;
    private String logradouro;
    private String cidade;
    private String uni_Federativa;

    public Endereco(int idEndereco, String jogador_CPF, String num, String bairro, String logradouro, String cidade, String uni_Federativa) {
        this.idEndereco = idEndereco;
        this.jogador_CPF = jogador_CPF;
        this.num = num;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uni_Federativa = uni_Federativa;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getJogador_CPF() {
        return jogador_CPF;
    }

    public void setJogador_CPF(String jogador_CPF) {
        this.jogador_CPF = jogador_CPF;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUni_Federativa() {
        return uni_Federativa;
    }

    public void setUni_Federativa(String uni_Federativa) {
        this.uni_Federativa = uni_Federativa;
    }
}
