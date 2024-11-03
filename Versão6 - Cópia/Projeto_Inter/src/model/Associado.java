/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Vector;

/**
 *
 * @author user
 */
public final class Associado extends Pessoa{
    private String profissao;
    private Vector<Dependente> dependentes;

    // Construtores
    public Associado(){
     super();
     profissao = "";
     dependentes = new Vector<Dependente>();

    }
    public Associado(int id, String nome, String data_nasc, String endereco, String telefone, String email,String rg, String cpf,String profissao,Vector<Dependente> dependentes){
     super(id, nome, endereco, telefone, email, rg, cpf, data_nasc);
     this.profissao = profissao;
      this.dependentes = dependentes;
     
    }
    // Método Getter e Setter
    public String getProfissao(){
     return profissao;
    }
    public void setProfissao(String profissao){
     this.profissao = profissao;
    }

     public Vector<Dependente> getDependente() {
        return dependentes;
    }

    public void setDependente(Vector<Dependente> dependentes) {
        this.dependentes = dependentes;
    }
    
        public void adicionarDependente(Dependente obj){
    dependentes.add(obj);}
    
    
    
    
    // Métdodo toString
//    @Override
//    public String toString(){
//     return " Associado " + super.toString();
//
//    }
    
     // Métdodo toString
    @Override
    public String toString(){
     return  getId() + "-" +getNome() + "- CPF: " + getCpf();

    }

   
    
}
