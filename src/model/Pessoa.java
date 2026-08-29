/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public abstract class Pessoa {

    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String rg;
    private String cpf;
    private String dataNasc;
    // Construtores Com e Sem parâmetros

    public Pessoa() {
        id = 0;
        nome = " ";
        endereco = " ";
        telefone = " ";
        email = " ";
        rg = " ";
        cpf = " ";
        dataNasc = " ";
    }

    public Pessoa(int id, String nome, String endereco, String telefone, String email, String rg, String cpf, String dataNasc) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    // Métodos Get e Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRG() {
        return rg;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
    // Método toString

    @Override
    public String toString() {
        return " Clube Naútico"
                + "\n Id: " + getId()
                + "\n Nome: " + getNome()
                + "\n Endereço: " + getEndereco()
                + "\n Telefone: " + getTelefone()
                + "\n Email: " + getEmail()
                + "\n RG: " + getRG()
                + "\n Cpf: " + getCpf()
                + "\n Data de Nascimento: " + getDataNasc();
    }
}
