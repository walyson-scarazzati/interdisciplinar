/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.Associado;
import model.Categoria;
import model.Contrato;
import model.Funcionario;

/**
 *
 * @author MaqLab
 */
public class ContratoData {

    public boolean incluir(Contrato obj) throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Insert into  Contratos_Titulos values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1,obj.getNro_contrato());
        pstmt.setString(2,obj.getData_contrato());
        pstmt.setString(3,obj.getData_cancelamento());
        pstmt.setInt(4,obj.getStatus());
        pstmt.setInt(5,obj.getAssociado().getId());
        pstmt.setInt(6,obj.getFuncionario().getId());
        pstmt.setInt(7,obj.getCategoria().getId());
        int registros = pstmt.executeUpdate();
        if (registros>0){

            return true;}
        else
            return false;
 
    }
    
    public boolean excluir(int id)throws Exception{
        Conexao objConexao = new Conexao();
        String SQL2 = "Delete from Mensalidades  where contrato_id= ?";
          PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL2);
          pstmt.setInt(1, id);
             int registros = pstmt.executeUpdate();  
    if(registros > 0){
          String SQL = "Delete from  Contratos_Titulos where id = ?"; 
        PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL);
        pstmt.setInt(1, id);
          int registros2 = pstmt2.executeUpdate();
          if(registros2 >0){
            objConexao.getConexao().commit();
          }
          return true;}
          else
           return false;
       }
    
//        public boolean excluir(int id)throws Exception{
//        Conexao objConexao = new Conexao();
//        String SQL = "Delete from  Contratos_Titulos where id = ?"; 
//        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
//        pstmt.setInt(1, id);
//        int registros = pstmt.executeUpdate();      
//      if(registros > 0){
//          String SQL = "Delete from  Contratos_Titulos where id = ?"; 
//        PreparedStatement pstmt2 = objConexao.getConexao().prepareStatement(SQL);
//        pstmt.setInt(1, id);
//          int registros2 = pstmt2.executeUpdate();
//          if(registros2 >0){
//            objConexao.getConexao().commit();
//          }
//          return true;}
//          else
//           return false;
//       }
           public boolean editar(Contrato obj) throws Exception{
        Conexao objConexao = new Conexao();
        String SQL = "Update  Contratos_Titulos set status = ?, data_cancel  = ?, data_contrato = ? where id = ?";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement (SQL);
                pstmt.setInt(1,obj.getStatus());
               pstmt.setString(2,obj.getData_cancelamento());
        pstmt.setString(3,obj.getData_contrato());
        int registros = pstmt.executeUpdate();
        if(registros>0)
            return true;
        else
            return false;
           }
           
           
            public Contrato pesquisar(int id) throws Exception{
        Contrato obj = null;
        Conexao objConexao = new Conexao();
        String SQL = " select data_contrato, data_cancel, status, a.associado_id, f.funcionario_id , c.categorias_id "+  
" from  Contratos_Titulos c Join  Funcionarios f on c.funcionario_id = f.funcionario_id " +
 " Join Associados a on c.associado_id = a.associado_id " +
 " Join Categorias ca on c.categorias_id = ca.id " +
    "where c.id = ?" ;
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement (SQL);
        pstmt.setInt(1,id);
        ResultSet rs= pstmt.executeQuery();
        if(rs.next()){
            obj= new Contrato();
            obj.setData_contrato(rs.getString("data_contrato"));
            obj.setData_cancelamento(rs.getString("data_cancel"));
            obj.setStatus (rs.getInt("status")); 
            Associado obj2 = new Associado();
            obj2.setId(rs.getInt("associado_id"));
            obj.setAssociado(obj2);
            
            Funcionario obj3 = new Funcionario();
            obj3.setId(rs.getInt("funcionario_id"));
            obj.setFuncionario(obj3);
            
            Categoria obj4 = new Categoria();
              obj4.setId(rs.getInt("categorias_id"));
            obj.setCategoria(obj4);
        }
        return obj;
    }
            
        public Vector<Contrato> listarContratos() throws Exception {
        Conexao objConexao = new Conexao ();
        Vector<Contrato> dados = new Vector<Contrato>();
        String SQL = "Select * from  Contratos_Titulos order by id";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        { Contrato obj = new Contrato();
        obj.setNro_contrato(rs.getInt("id"));
            obj.setData_contrato(rs.getString("data_contrato"));
            obj.setData_cancelamento(rs.getString("data_cancel"));
//            obj.setStatus (rs.getInt("status"));
        dados.add(obj);
        }
        return dados;
    }
    
     public Vector listarTodos() throws Exception{
       Conexao objConexao = new Conexao();
       Vector dados = new Vector();
       String SQL = "Select * from  Contratos_Titulos order by id";
       PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
       ResultSet rs = pstmt.executeQuery();
       while(rs.next()){
          Vector linha = new Vector();
          linha.add(rs.getInt("id"));
          linha.add (rs.getString("data_contrato"));
          linha.add(rs.getString("data_cancel"));
          linha.add(rs.getInt("status"));
          dados.add(linha);
       } 
       return dados;
     }
     
     
    
        public Vector<Contrato> carregarCombo() throws Exception {
        Vector<Contrato> dados = new Vector<Contrato>();
        Conexao objConexao = new Conexao();
        String SQL = "select id  from  Contratos_Titulos c ";
        PreparedStatement pstmt = objConexao.getConexao().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();
        dados.add(new Contrato(0,  "<Selecione>", "", null, null, 0, null));
        while(rs.next()){ 
             Contrato obj = new Contrato();
            obj.setNro_contrato(rs.getInt("id"));
            dados.add(obj);
        }
        return dados;
    } 
        
          
     
        
    }
    

