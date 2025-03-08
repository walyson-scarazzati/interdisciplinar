/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MaqLab
 */
public class Parentesco {

    private int id;
    private String descricao;

    // construtor Com e Sem parâmetros
    public Parentesco() {
        id = 0;
        descricao = "";
    }

    public Parentesco(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return +getId()
                + "- " + getDescricao();
    }

}
