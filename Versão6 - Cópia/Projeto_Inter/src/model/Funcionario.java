/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public final class Funcionario extends Pessoa {
    private double salario;
    private String usuario;
    private String senha;
    private int tipo;
    // Construtores
    public Funcionario(){
     super();
     usuario = "";
     senha = "";
     salario = 0;
     tipo = 0;
    }
    public Funcionario(int id,String nome, String data_nasc, String endereco, String telefone, String email, String rg, String cpf, String usuario, String senha, double salario,int tipo){
    super(id, nome, endereco, telefone, email, rg, cpf, data_nasc);
    
     this.usuario = usuario;
     this.senha = senha;
     this.salario = salario;
     this.tipo = tipo;
    }
    
    
    
    // Método Getter e Setter
    public double getSalario(){
     return salario;
    }
    public void setSalario(double salario){
     this.salario = salario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    // Método toString
    @Override
    
    public String toString(){
     return  getId() + "-" +getNome() + "- CPF: " + getCpf();

    }

    
    
}
