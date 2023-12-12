package model;



public class Jogador {
    private String CPF;
    private String nome;
    private String data_Nascimento;
    private String senha;

    public Jogador(){
        CPF = new String();
        nome = new String();
        data_Nascimento = new String();
        senha = new String();
    }

    public Jogador(String CPF, String nome, String data_Nascimento, String senha) {
        this.CPF = CPF;
        this.nome = nome;
        this.data_Nascimento = data_Nascimento;
        this.senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_Nascimento() {
        return data_Nascimento;
    }

    public void setData_Nascimento(String data_Nascimento) {
        this.data_Nascimento = data_Nascimento;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

}
