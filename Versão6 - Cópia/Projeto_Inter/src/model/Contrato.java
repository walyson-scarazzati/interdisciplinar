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
public class Contrato {
  private int    nro_contrato;
  private String data_contrato;
  private String data_cancelamento;
   private Funcionario funcionario;
   private Associado associado;
   private int status;
   private Categoria categoria;
  //mensalidade 
  
  // Construtores
  public Contrato(){
    nro_contrato = 0;
    data_contrato = " ";
    data_cancelamento = " ";
    associado = new Associado();
        funcionario = new Funcionario();
        status = 0;
        categoria = new Categoria();
    
  }
  public Contrato (int nro_contrato, String data_contrato, String data_cancelamento, Associado associado, Funcionario funcionario, Integer status,Categoria categoria ){
    this.nro_contrato = nro_contrato;
    this.data_contrato = data_contrato;
    this.data_cancelamento = data_cancelamento;
    this.associado = associado;
        this.funcionario = funcionario;
        this.status = status;
        this.categoria = categoria; 
   
  }
  
  // Método Getter e Setter
  public int getNro_contrato(){
    return nro_contrato;
  }
  public void setNro_contrato(int nro_contrato){
    this.nro_contrato = nro_contrato;
  }
  public String getData_contrato(){
    return data_contrato;
  }
  public void setData_contrato(String data_contrato){
    this.data_contrato = data_contrato;
  }
  public String getData_cancelamento(){
    return data_cancelamento;
  }
  public void setData_cancelamento(String data_cancelamento){
    this.data_cancelamento = data_cancelamento;
  }
  
  
 

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    


  

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
     // Método toString()
    @Override
  public String toString(){
    return 
           "Nro contrato: " + getNro_contrato();
  
           
       
  }   

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
