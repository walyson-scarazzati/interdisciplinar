/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MaqLab
 */
public class Categoria {
    private int id;
    private String descricao;
    private float  valor;
    
    
    // Construtores Com e Sem parâmetros
    public Categoria(){
      id = 0;
      descricao = "";
      valor     = 0;
     
      
    }
    public Categoria(int id, String descricao, float valor){
      this.id = id ;
      this.descricao = descricao;
      this.valor     = valor;
    
    }
    
    public Categoria(String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.valor = 0; // Default value
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
     public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return  + id + "-" + descricao + "- R$:" + valor;
    }

   
    
      
}
