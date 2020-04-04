/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Associado;
import model.Dependente;
import model.Parentesco;

/**
 *
 * @author MaqLab
 */
public class DependenteData {

    

    
        public boolean incluir (Dependente obj) throws Exception{
       Conexao objConexao = new Conexao();
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
       if(registros >0){
       String SQL2 = "Insert into Dependentes values (?,?,?)";
       PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
           pstmt2.setInt(1, obj.getId());
       pstmt2.setInt(2, obj.getParentesco().getId());
       pstmt2.setInt(3, obj.getIdAssociado());
          int registros2 = pstmt2.executeUpdate();
          if(registros2 >0){
              objConexao.getConexao().setAutoCommit(true);
              objConexao.getConexao().commit();
              return true;
          }else {
                      objConexao.getConexao().setAutoCommit(true);
                      objConexao.getConexao().rollback();
                      return false;
                      }
       } else {
           return false;
       }
        }

   
     public boolean editar(Dependente obj) throws Exception{
       Conexao objConexao = new Conexao();
       String SQL = "Update Associados set associado_id = ?, parentesco_id  = ? where associado_id = ?";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL); 
       pstmt.setInt(1,obj.getIdAssociado());
       pstmt.setInt(2,obj.getParentesco().getId());
       pstmt.setInt(3, obj.getId());
       int registros = pstmt.executeUpdate();
       if(registros>0){
         String SQL2 = "Update Pessoas set nome = ?, data_nasc = ?, endereco = ?, telefone = ?, email = ?, rg = ?, cpf = ?"
                 + " where id = ?";
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
        return true;}
       else
           return false;
    }
    
    public boolean excluir(int id) throws Exception{
      Conexao objConexao = new Conexao();
      String SQL = "Delete from Dependentes where dependente_id  = ?";
      PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
      pstmt.setInt(1, id);
      int registros = pstmt.executeUpdate();
      if(registros > 0){
        String SQL2 = "Delete from Pessoas where id = ?";
        PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL2);
        pstmt2.setInt(1, id);
        int registros2 = pstmt2.executeUpdate();
        if(registros2 > 0){
            
          return true;
        }
        else;
      }
      return false;
    }
    
    
    public Dependente pesquisar (int id) throws Exception{
      Dependente obj = null;
      Conexao objConexao = new Conexao();
      String SQL = "Select id, nome, data_nasc, endereco, telefone, email, rg, cpf, d.parentesco_id,  d.associado_id "
              + " from Dependentes d, Pessoas p, Parentescos pe, Associados a "  
              + " where p.id = d.dependente_id and "
              + " d.associado_id = a.associado_id and "
            + " pe.parentesco_id = d.parentesco_id and p.id = ?";
      PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if(rs.next()){
        obj = new Dependente();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setData_nasc(rs.getString("data_nasc"));
        obj.setEndereco(rs.getString("endereco"));
        obj.setTelefone(rs.getString("telefone"));
        obj.setEmail(rs.getString("email"));
        obj.setRG(rs.getInt("rg"));
        obj.setCpf(rs.getInt("cpf"));
            Parentesco obj2 = new  Parentesco ();
            obj2.setId(rs.getInt("parentesco_id"));
            obj.setParentesco (obj2);
            obj.setIdAssociado(rs.getInt("associado_id"));
            
      }
      return obj;
    }
    
       
      public Vector Listar(String arg) throws SQLException {

        Vector dados = new Vector();
        Conexao objConexao = new Conexao();
        String SQL =      "select DISTINCT(A.dependente_id),B.nome, B.CPF, " +
      "CASE WHEN D.data_pgto > data_venc THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade " +
       "  from Dependentes A "+
         " JOIN Pessoas B On B.id = A.dependente_id " +
			" JOIN Associados  E on E.associado_id = A.associado_id  " +
			" JOIN Contratos_Titulos C on E.associado_id  = C.associado_id " +
              "  JOIN Mensalidades D ON C.id = D.contrato_id " +
                " where B.nome like '%" + arg + "%' "+
              " GROUP BY A.dependente_id,B.nome, B.CPF, D.data_pgto, D.data_venc ";
        
//              "SELECT dependente_id , nome,  cpf FROM Dependentes d, Pessoas p where d.dependente_id  = p.id  and p.nome like '%" + arg + "%' order by  d.dependente_id  ";
        PreparedStatement pstmt =objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();

        // percorre a lista de resultado da query:
        while (rs.next()) {
            Vector linha = new Vector();
            linha.addElement(rs.getInt("dependente_id"));
            linha.addElement(rs.getString("nome"));
            linha.addElement(rs.getString("CPF"));
               linha.addElement(rs.getString("status_da_mensalidade"));
            dados.addElement(linha);
       
        }

        return dados;


    }
    
          
       public Vector listar() throws SQLException {
        Vector dados = new Vector();
        Conexao objConexao = new Conexao();
        String SQL =  
          "select DISTINCT(A.dependente_id),B.nome, B.CPF, " +
      "CASE WHEN D.data_pgto > data_venc THEN 'DEVENDO' ELSE 'PAGO' END status_da_mensalidade " +
       "  from Dependentes A "+
         " JOIN Pessoas B On B.id = A.dependente_id " +
			" JOIN Associados  E on E.associado_id = A.associado_id  " +
			" JOIN Contratos_Titulos C on E.associado_id  = C.associado_id " +
              "  JOIN Mensalidades D ON C.id = D.contrato_id " +
              " GROUP BY A.dependente_id,B.nome, B.CPF, D.data_pgto, D.data_venc ";
//        String SQL = "SELECT dependente_id , nome,  cpf  FROM Dependentes d, Pessoas p where d.dependente_id  = p.id order by  associado_id ";
        PreparedStatement pstmt =objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Vector linha = new Vector();
            linha.addElement(rs.getInt("dependente_id"));
            linha.addElement(rs.getString("nome"));
            linha.addElement(rs.getString("CPF"));
            linha.addElement(rs.getString("status_da_mensalidade"));
            dados.addElement(linha);

        }
        return dados;

    }
      
}
