/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public final class Dependente extends Pessoa {

    private int idAssociado;
    private Parentesco parentesco;
    // Construtores

    public Dependente() {
        super();
        parentesco = new Parentesco();
        idAssociado = 0;
    }

    public Dependente(int id, String nome, String dataNasc, String endereco, String telefone, String email, String rg, String cpf, Parentesco parentesco, int idAssociado) {
        super(id, nome, endereco, telefone, email, rg, cpf, dataNasc);
        this.parentesco = parentesco;
        this.idAssociado = idAssociado;
    }
    // Método Getter e Setter

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    // Método toString()
    @Override
    public String toString() {
        return "Dependente{" + ", parentesco=" + parentesco + '}';
    }

    public int getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(int idAssociado) {
        this.idAssociado = idAssociado;
    }

}
