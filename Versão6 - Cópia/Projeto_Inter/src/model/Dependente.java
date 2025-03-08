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

    private int IdAssociado;// associado;
    private Parentesco parentesco;
    // Construtores

    public Dependente() {
        super();
        parentesco = new Parentesco();
        IdAssociado = 0;
    }

    public Dependente(int id, String nome, String data_nasc, String endereco, String telefone, String email, String rg, String cpf, Parentesco parentesco, int IdAssociado) {
        super(id, nome, endereco, telefone, email, rg, cpf, data_nasc);
        this.parentesco = parentesco;
        this.IdAssociado = IdAssociado;
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
        return IdAssociado;
    }

    public void setIdAssociado(int associado) {
        this.IdAssociado = associado;
    }

}
