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
   private int    id;
   private String nome;
   private String endereco;
   private String    telefone;
   private String email;
   private int    rg;
   private int    cpf;
   private String    data_nasc;
   // Construtores Com e Sem parâmetros
   public Pessoa(){
    id        = 0;
    nome      = " ";
    endereco  = " ";
    telefone  = " ";
    email     = " ";
    rg        = 0;
    cpf       = 0;
    data_nasc = " ";
   }
   public Pessoa(int id, String nome, String endereco, String telefone, String email, int rg, int cpf, String data_nasc){
    this.id   = id;
    this.nome = nome;
    this.endereco = endereco;
    this.telefone = telefone;
    this.email = email;
    this.rg = rg;
    this.cpf = cpf;
    this.data_nasc = data_nasc;
   }

    // Métodos Get e Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
   public String getNome(){
    return nome;
   }
   public void setNome(String nome){
    this.nome = nome;
   }
   public String getEndereco(){
    return endereco;
   }
   public void setEndereco(String endereco){
    this.endereco = endereco;
   }
   public String getTelefone(){
    return telefone;
   }
   public void setTelefone(String telefone){
    this.telefone = telefone;
   }
   public String getEmail(){
    return email;
   }
   public void setEmail(String email){
    this.email = email;
   }
   public int getRG(){
    return rg;
   }
   public void setRG(int rg){
    this.rg = rg;
   }
   public int getCpf(){
    return cpf;
   }
   public void setCpf(int cpf){
    this.cpf = cpf;
   }
   public String getData_nasc(){
    return data_nasc;
   }
   public void setData_nasc(String data_nasc){
    this.data_nasc = data_nasc;
   }
   // Método toString
    @Override
   public String toString(){
    return " Clube Naútico" +
           "\n Id: " + getId() +
           "\n Nome: "      + getNome()     +
           "\n Endereço: "  + getEndereco() +
           "\n Telefone: "  + getTelefone() +
           "\n Email: "     + getEmail()    +
           "\n RG: "        + getRG()       +
           "\n Cpf: "       + getCpf()      +
           "\n Data de Nascimento: " + getData_nasc();
   }
}
