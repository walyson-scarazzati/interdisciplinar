/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.Associado;
import model.Funcionario;


/**
 *
 * @author MaqLab
 */
public class FuncionarioData {
   
    public boolean incluir(Funcionario obj) throws Exception{
       Conexao objConexao = new Conexao();
       objConexao.getConexao().setAutoCommit(false);
       String SQL = "Insert into Pessoas values (?,?,?,?,?,?,?,?)";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       pstmt.setInt(1, obj.getId());
       pstmt.setString(2, obj.getNome());
       pstmt.setString(3, obj.getData_nasc());
       pstmt.setString(4, obj.getEndereco());
       pstmt.setString(5, obj.getTelefone());
       pstmt.setString(6, obj.getEmail());
       pstmt.setInt(7, obj.getRG());
       pstmt.setInt(8, obj.getCpf());
       int registros = pstmt.executeUpdate();
       if(registros > 0){
          String SQL2 = "Insert into Funcionarios values (?,?,?,?,?)";
          PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
          pstmt2.setInt(1, obj.getId());
          pstmt2.setString(2, obj.getUsuario());
          pstmt2.setString(3, obj.getSenha());
          pstmt2.setDouble(4, obj.getSalario());
          pstmt2.setInt(5, obj.getTipo());
          int registros2 = pstmt2.executeUpdate();
          if(registros2 > 0){
            objConexao.getConexao().commit();
            objConexao.getConexao().setAutoCommit(true);
            return true;
          }
          else;{
           objConexao.getConexao().rollback();
           objConexao.getConexao().setAutoCommit(true);
           return false;
           }
         }
          else;
       return false;
    }
    public boolean editar (Funcionario obj) throws Exception{
      Conexao objConexao = new Conexao();
      String SQL = "Update Funcionarios set tipo = ?, salario = ?, usuario = ?, senha = ? where funcionario_id= ?";
      PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
      pstmt.setInt(1, obj.getTipo());
      pstmt.setDouble(2, obj.getSalario());
      pstmt.setString(3, obj.getUsuario());
      pstmt.setString(4, obj.getSenha());
      pstmt.setInt(5,obj.getId());
      int registros = pstmt.executeUpdate();
      if(registros >0 ){
        String SQL2 = "Update Pessoas set nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ?, rg = ?, cpf = ?"
                + " Where id = ?";
        PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
        pstmt2.setString(1, obj.getNome());
        pstmt2.setString(2, obj.getData_nasc());
        pstmt2.setString(3, obj.getEndereco());
        pstmt2.setString(4, obj.getTelefone());
        pstmt2.setString(5, obj.getEmail());
        pstmt2.setInt(6, obj.getRG());
        pstmt2.setInt(7, obj.getCpf());
        pstmt2.setInt(8, obj.getId());
        int registros2 = pstmt2.executeUpdate();
        if(registros2 >0){
            return true;}
            else;}
        return false;
    }
    public boolean excluir (int id) throws Exception{
       Conexao objConexao = new Conexao();
       String SQL = "Delete from Contratos_Titulos where funcionario_id = ?";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       pstmt.setInt(1, id);
       int registros = pstmt.executeUpdate();
       if(registros > 0){  
       String SQL2 = "Delete from Funcionarios where funcionario_id = ?";
       PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
       pstmt2.setInt(1, id);
       int registros2 = pstmt.executeUpdate();
       if(registros2 > 0){
         String SQL3 = "Delete from Pessoas where id = ?";
         PreparedStatement pstmt3 = objConexao.getConexao().prepareStatement(SQL3);
         pstmt3.setInt(1, id);
         int registros3 = pstmt3.executeUpdate();
         if(registros3 > 0){
           return true;
         }
         else;
       }
       return false;
    }
       return true;
       }

       
     public Funcionario pesquisarFuncionarios (int id) throws Exception{
       Conexao objConexao = new Conexao();
       Funcionario obj = null;
       String SQL = "select id, nome, data_nasc, endereco, telefone, email, rg, cpf, usuario, senha, salario, tipo from Funcionarios f, Pessoas p"
               + " where p.id = f.funcionario_id and  p.id = ? ";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       pstmt.setInt(1, id);
       ResultSet rs = pstmt.executeQuery();
       if(rs.next()){
         obj = new Funcionario();
         obj.setId(rs.getInt("id"));
         obj.setNome(rs.getString("nome"));
         obj.setData_nasc(rs.getString("data_nasc"));
         obj.setEndereco(rs.getString("endereco"));
         obj.setTelefone(rs.getString("telefone"));
         obj.setEmail(rs.getString("email"));
         obj.setRG(rs.getInt("rg"));
         obj.setCpf(rs.getInt("cpf"));
         obj.setUsuario(rs.getString("usuario"));
         obj.setSenha(rs.getString("senha"));
         obj.setSalario(rs.getDouble("salario"));
         obj.setTipo(rs.getInt("tipo"));
         
       }
       return obj;
    }
     
       public Funcionario validarUsuario (String usuario, String senha) throws Exception {
        
        Conexao objConexao = new Conexao();
        Funcionario obj = null;
        String SQL = "Select * from Funcionarios where usuario = ? and senha = ? ";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setString(1, usuario);
         pstmt.setString(2, senha);
         ResultSet rs = pstmt.executeQuery();
         if(rs.next()){
             obj = new  Funcionario();
             obj.setUsuario(rs.getString("usuario"));
             obj.setSenha(rs.getString("senha"));
             obj.setTipo(rs.getInt("tipo"));
         }
         
         return obj;
         
    }
    
        
         public Vector<Funcionario> carregarCombo() throws Exception {
        Vector<Funcionario> dados = new Vector<Funcionario>();
        Conexao objConexao = new Conexao();
        String SQL = "select id, nome, cpf from Pessoas p, Funcionarios f" +
                     " where p.id = f.funcionario_id"; 
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Funcionario(0, "<Selecione>", "", "", "", "", 0, 0, "", "", 0, 0));
        while(rs.next()){     
            Funcionario obj = new Funcionario();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome").trim());
            obj.setCpf(rs.getInt("CPF"));
            dados.add(obj);
        }
        return dados;
    }
         
      
       
}
      