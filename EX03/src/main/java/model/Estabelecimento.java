package model;

public class Estabelecimento {
    private String estab_cnpj;
    private String nome;
    private String descricao;
    private String tipo;
    private String end_num;
    private String end_bairro;
    private String end_logr;
    private String end_cidade;
    private String end_uf;
    private String senha;


    public Estabelecimento(){
        this.estab_cnpj = new String();
        this.nome = new String();
        this.descricao = new String();
        this.tipo = new String();
        this.end_num = new String();
        this.end_bairro = new String();
        this.end_logr = new String();
        this.end_cidade = new String();
        this.end_uf = new String(); 
        this.senha = new String();
    }

    public Estabelecimento(String estab_cnpj, String nome, String descricao, String tipo, String end_num, String end_bairro, String end_logr, String end_cidade, String end_uf, String senha) {
        this.estab_cnpj = estab_cnpj;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.end_num = end_num;
        this.end_bairro = end_bairro;
        this.end_logr = end_logr;
        this.end_cidade = end_cidade;
        this.end_uf = end_uf;
        this.senha = senha;
    }

    public String getEstab_cnpj() {
        return estab_cnpj;
    }

    public void setEstab_cnpj(String estab_cnpj) {
        this.estab_cnpj = estab_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEnd_num() {
        return end_num;
    }

    public void setEnd_num(String end_num) {
        this.end_num = end_num;
    }

    public String getEnd_bairro() {
        return end_bairro;
    }

    public void setEnd_bairro(String end_bairro) {
        this.end_bairro = end_bairro;
    }

    public String getEnd_logr() {
        return end_logr;
    }

    public void setEnd_logr(String end_logr) {
        this.end_logr = end_logr;
    }

    public String getEnd_cidade() {
        return end_cidade;
    }

    public void setEnd_cidade(String end_cidade) {
        this.end_cidade = end_cidade;
    }

    public String getEnd_uf() {
        return end_uf;  
    }

    public void setEnd_uf(String end_uf) {
        this.end_uf = end_uf;
    }

    public String getSenha() {
        return senha;  
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
