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
   private String    rg;
   private String    cpf;
   private String    data_nasc;
   // Construtores Com e Sem parâmetros
   public Pessoa(){
    id        = 0;
    nome      = " ";
    endereco  = " ";
    telefone  = " ";
    email     = " ";
    rg        = " ";
    cpf       = " ";
    data_nasc = " ";
   }
   public Pessoa(int id, String nome, String endereco, String telefone, String email, String rg, String cpf, String data_nasc){
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
   public String getRG(){
    return rg;
   }
   public void setRG(String rg){
    this.rg = rg;
   }
   public String getCpf(){
    return cpf;
   }
   public void setCpf(String cpf){
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
