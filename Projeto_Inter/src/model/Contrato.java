/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class Contrato {

    private int nroContrato;
    private String dataContrato;
    private String dataCancelamento;
    private Funcionario funcionario;
    private Associado associado;
    private int status;
    private Categoria categoria;
    //mensalidade

    // Construtores
    public Contrato() {
        nroContrato = 0;
        dataContrato = " ";
        dataCancelamento = " ";
        associado = new Associado();
        funcionario = new Funcionario();
        status = 0;
        categoria = new Categoria();

    }

    public Contrato(int nroContrato, String dataContrato, String dataCancelamento, Associado associado, Funcionario funcionario, Integer status, Categoria categoria) {
        this.nroContrato = nroContrato;
        this.dataContrato = dataContrato;
        this.dataCancelamento = dataCancelamento;
        this.associado = associado;
        this.funcionario = funcionario;
        this.status = status;
        this.categoria = categoria;

    }

    // Método Getter e Setter
    public int getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(int nroContrato) {
        this.nroContrato = nroContrato;
    }

    public String getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(String dataContrato) {
        this.dataContrato = dataContrato;
    }

    public String getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
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
    public String toString() {
        return "Nro contrato: " + getNroContrato();

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
