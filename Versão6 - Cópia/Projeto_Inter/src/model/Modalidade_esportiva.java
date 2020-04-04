/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MaqLab
 */
public class Modalidade_esportiva {
    private int id;
    private String descricao;
    private Categoria categoria;
    // Construtores Com e Sem parâmetros
    public Modalidade_esportiva(){
        id = 0;
        descricao = "";
        categoria = new Categoria();
    }
    public Modalidade_esportiva(int id,String descricao, Categoria categoria){
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
    }
    
    

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString(){
       return " Modalidade Esportiva: " +
               "\n Id" + getId() +
               "\nDescrição; " + getDescricao();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
