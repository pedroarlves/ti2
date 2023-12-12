package model;

public class Partida {
    private int id_partida;
    private String nome;
    private double valor_pago;
    private String hora_fim;
    private String hora_inicio;
    private String jog_max;
    private String tipo;
    private String esporte;
    private String data;
    private String end_num;
    private String end_bairro;
    private String end_logr;
    private String end_cidade;
    private String end_uf;


    public Partida()
    {
        int id = -1;
        nome = new String();
        valor_pago = 0.0;
        hora_fim = new String();
        hora_inicio = new String();
        jog_max = new String();
        tipo = new String();
        esporte = new String();
        data = new String();
        end_num = new String();
        end_bairro = new String();
        end_logr = new String();
        end_cidade = new String();
        end_uf = new String();
    }


    public Partida(int id_partida, String nome, double valor_pago, String hora_fim, String hora_inicio, String jog_max, String tipo, String esporte, String data, String end_num, String end_bairro, String end_logr, String end_cidade, String end_uf) {
        this.id_partida = id_partida;
        this.nome = nome;
        this.valor_pago = valor_pago;
        this.hora_fim = hora_fim;
        this.hora_inicio = hora_inicio;
        this.jog_max = jog_max;
        this.tipo = tipo;
        this.esporte = esporte;
        this.data = data;
        this.end_num = end_num;
        this.end_bairro = end_bairro;
        this.end_logr = end_logr;
        this.end_cidade = end_cidade;
        this.end_uf = end_uf;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
    }

    public String getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(String hora_fim) {
        this.hora_fim = hora_fim;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getJog_max() {
        return jog_max;
    }

    public void setJog_max(String jog_max) {
        this.jog_max = jog_max;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEsporte() {
        return esporte;
    }

    public void setEsporte(String esporte) {
        this.esporte = esporte;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

}