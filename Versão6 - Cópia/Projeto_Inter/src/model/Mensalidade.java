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
    private String mes_ref; 
    private String Data_venc;
    private String Data_pgto;
    private float valor;
    private Contrato contrato;


    public Mensalidade (){
        id = 0;
        preco = 0;
        Data_pgto = "";
       Data_venc = "";
        valor = 0;
        mes_ref = "";
        contrato = new Contrato();
    }

    public Mensalidade(int id, float preco, String Data_pgto, String Data_venc, String mes_ref, float valor, Contrato contrato) {
        this.id = id;
        this.preco = preco;
        this.Data_pgto = Data_pgto;
        this.Data_venc = Data_venc;
        this.mes_ref = mes_ref;
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

    public String getMes_ref() {
        return mes_ref;
    }

    public void setMes_ref(String mes_ref) {
        this.mes_ref = mes_ref;
    }

    public String getData_venc() {
        return Data_venc;
    }

    public void setData_venc(String Data_venc) {
        this.Data_venc = Data_venc;
    }

    public String getData_pgto() {
        return Data_pgto;
    }

    public void setData_pgto(String Data_pgto) {
        this.Data_pgto = Data_pgto;
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
        return  + id + "- R$:" + preco + "-Data de vencimento:" + Data_venc;
    }

        
    
}
