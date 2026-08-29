/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MaqLab
 */
public class Mensalidade {

    private int id;
    private float preco;
    private String mesRef;
    private String dataVenc;
    private String dataPgto;
    private float valor;
    private Contrato contrato;

    public Mensalidade() {
        id = 0;
        preco = 0;
        dataPgto = "";
        dataVenc = "";
        valor = 0;
        mesRef = "";
        contrato = new Contrato();
    }

    public Mensalidade(int id, float preco, String dataPgto, String dataVenc, String mesRef, float valor, Contrato contrato) {
        this.id = id;
        this.preco = preco;
        this.dataPgto = dataPgto;
        this.dataVenc = dataVenc;
        this.mesRef = mesRef;
        this.valor = valor;
        this.contrato = contrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getMesRef() {
        return mesRef;
    }

    public void setMesRef(String mesRef) {
        this.mesRef = mesRef;
    }

    public String getDataVenc() {
        return dataVenc;
    }

    public void setDataVenc(String dataVenc) {
        this.dataVenc = dataVenc;
    }

    public String getDataPgto() {
        return dataPgto;
    }

    public void setDataPgto(String dataPgto) {
        this.dataPgto = dataPgto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    @Override
    public String toString() {
        return +id + "- R$:" + preco + "-Data de vencimento:" + dataVenc;
    }

}
